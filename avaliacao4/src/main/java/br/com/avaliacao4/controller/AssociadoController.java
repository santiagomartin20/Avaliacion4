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

import br.com.avaliacao4.controller.dto.AssociadoDto;
import br.com.avaliacao4.controller.form.AssociadoForm;
import br.com.avaliacao4.controller.form.AssociadoPartidoForm;
import br.com.avaliacao4.model.Associado;
import br.com.avaliacao4.model.AssociadoFiltro;
import br.com.avaliacao4.model.Partido;
import br.com.avaliacao4.repository.AssociadoRepository;
import br.com.avaliacao4.repository.PartidoRepository;

@RestController
@RequestMapping("/associados")
public class AssociadoController {

	@Autowired
	private AssociadoRepository associadoRepository;
	@Autowired
	private PartidoRepository partidoRepository;

	@PostMapping
	@Transactional
	public ResponseEntity<AssociadoDto> cadastrarAssociado(@RequestBody @Valid AssociadoForm form,
			UriComponentsBuilder uriBuilder) {
		
		Partido partido = partidoRepository.getOne((long) 0);
		Associado associado = form.convertir(partido);

		if (associado.getNome() == null) {
			return ResponseEntity.notFound().build();
		} else {

			associadoRepository.save(associado);
			URI uri = uriBuilder.path("associados/{id}").buildAndExpand(associado.getId()).toUri();
			return ResponseEntity.created(uri).body(new AssociadoDto(associado));
		}
	}

	@PostMapping("/partidos")
	@Transactional
	public ResponseEntity<AssociadoDto> cadastrarAssociadoPartido(@RequestBody @Valid AssociadoPartidoForm form,
			UriComponentsBuilder uriBuilder) {
		Optional<Associado> verificadorAssociado = associadoRepository.findById(form.getIdAssociado());
		Optional<Partido> verificadorPartido = partidoRepository.findById(form.getIdPartido());
		if (verificadorAssociado.isPresent() && verificadorPartido.isPresent()) {
			try {
				Associado associado = form.actualizar(form.getIdAssociado(), form.getIdPartido(), associadoRepository,
						partidoRepository);
				return ResponseEntity.ok(new AssociadoDto(associado));
			} catch (Exception e) {
				return ResponseEntity.notFound().build();
			}
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping
	public Page<AssociadoDto> lista(@RequestParam(required = false) AssociadoFiltro filtro) {

		Pageable paginacion = PageRequest.of(0, 15);
		

		if (filtro != null) {
			String filtroString = filtro.getName();
			System.out.println(filtroString);
			paginacion = PageRequest.of(0, 15, Direction.DESC, filtroString);
		}
		Page<Associado> associado = associadoRepository.findAll(paginacion);
		return AssociadoDto.convertir(associado);
		
	}

	@GetMapping("/{id}")
	public ResponseEntity<AssociadoDto> buscaId(@PathVariable Long id) {
		Optional<Associado> associado = associadoRepository.findById(id);
		if (associado.isPresent()) {
			return ResponseEntity.ok(new AssociadoDto(associado.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<AssociadoDto> actualizarAssociado(@PathVariable Long id,
			@RequestBody @Valid AssociadoForm form, UriComponentsBuilder uriBuilder) {

		Optional<Associado> verificador = associadoRepository.findById(id);
		if (verificador.isPresent()) {
			try {
				Associado associado = form.actualizar(id, associadoRepository);
				return ResponseEntity.ok(new AssociadoDto(associado));
			} catch (Exception e) {
				return ResponseEntity.notFound().build();
			}
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> DeletarAssociado(@PathVariable Long id) {

		Optional<Associado> verificador = associadoRepository.findById(id);
		if (verificador.isPresent()) {
			associadoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();

	}
	
	@DeleteMapping("/{idAssociado}/partidos/{idPartido}}")
	@Transactional
	public ResponseEntity<?> DeletarAssociadoPartido(@PathVariable Long idAssociado,@PathVariable Long idPartido) {

		System.out.println(idAssociado);
		System.out.println(idPartido);
		Optional<Associado> verificador = associadoRepository.findById(idAssociado);
		if (verificador.isPresent()) {
			Associado associado= associadoRepository.getOne(idAssociado);
			associado.setPartido(partidoRepository.getOne((long) 0));
			
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();

	}

}
