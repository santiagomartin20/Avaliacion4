package br.com.avaliacao4.controller.form;



import br.com.avaliacao4.model.Associado;
import br.com.avaliacao4.model.Partido;
import br.com.avaliacao4.repository.AssociadoRepository;
import br.com.avaliacao4.repository.PartidoRepository;

public class AssociadoPartidoForm {

	private Long idAssociado;
	private Long idPartido;

	public AssociadoPartidoForm() {

	}

	public AssociadoPartidoForm(Long idAssociado, Long idPartido) {
		
		this.idAssociado= idAssociado;
		this.idPartido = idPartido;
	}
	
	

	public Long getIdAssociado() {
		return idAssociado;
	}

	public Long getIdPartido() {
		return idPartido;
	}

	public Associado actualizar(Long idAssociado, Long idPartido,  AssociadoRepository associadoRepository, PartidoRepository partidoRepository ) {
		Associado associado = associadoRepository.getOne(idAssociado);
		Partido partido = partidoRepository.getOne(idPartido); 
		associado.setPartido(partido);
		return associado;
	}

}
