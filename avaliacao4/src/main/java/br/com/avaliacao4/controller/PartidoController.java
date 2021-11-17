package br.com.avaliacao4.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.avaliacao4.controller.dto.AssociadoPartidoDto;
import br.com.avaliacao4.controller.dto.PartidoDto;
import br.com.avaliacao4.controller.form.PartidoForm;
import br.com.avaliacao4.model.Partido;
import br.com.avaliacao4.model.PartidoFiltro;
import br.com.avaliacao4.repository.PartidoRepository;

@RestController
@RequestMapping("/partido")
public class PartidoController {
	@Autowired
	private PartidoRepository partidoRepository;

	@PostMapping
	@Transactional
	public ResponseEntity<PartidoDto> cadastrarPartido(@RequestBody @Valid PartidoForm form,
			UriComponentsBuilder uriBuilder) {
		Partido partido = form.convertir();
		if (partido.getNome() == null) {
			return ResponseEntity.notFound().build();
		} else {
			partidoRepository.save(partido);
			URI uri = uriBuilder.path("partido/{id}").buildAndExpand(partido.getId()).toUri();
			return ResponseEntity.created(uri).body(new PartidoDto(partido));
		}
	}

	@GetMapping
	public Page<PartidoDto> lista(@RequestParam(required = false) PartidoFiltro filtro) {

		Pageable paginacion = PageRequest.of(0, 15);
		

		if (filtro != null) {
			String filtroString = filtro.getName();
			System.out.println(filtroString);
			paginacion = PageRequest.of(0, 15, Direction.DESC, filtroString);
		}
		Page<Partido> partido = partidoRepository.findAll(paginacion);
		return PartidoDto.convertir(partido);
		
	}

	@GetMapping("/{id}/associados")
	public ResponseEntity<AssociadoPartidoDto> buscaAssociadoPartido(@PathVariable Long id) {
		Optional<Partido> partido = partidoRepository.findById(id);
		if (partido.isPresent()) {
			return ResponseEntity.ok(new AssociadoPartidoDto(partido.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PartidoDto> buscaId(@PathVariable Long id) {
		Optional<Partido> partido = partidoRepository.findById(id);
		if (partido.isPresent()) {
			return ResponseEntity.ok(new PartidoDto(partido.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PartidoDto> actualizarPartido(@PathVariable Long id, @RequestBody @Valid PartidoForm form,
			UriComponentsBuilder uriBuilder) {

		Optional<Partido> verificador = partidoRepository.findById(id);
		if (verificador.isPresent()) {
			try {
				Partido partido = form.actualizar(id, partidoRepository);
				return ResponseEntity.ok(new PartidoDto(partido));
			} catch (Exception e) {
				return ResponseEntity.notFound().build();
			}
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> DeletarPartido(@PathVariable Long id) {

		Optional<Partido> verificador = partidoRepository.findById(id);
		if (verificador.isPresent()) {
			partidoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();

	}

}
