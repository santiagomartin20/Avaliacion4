package br.com.avaliacao4.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import br.com.avaliacao4.model.Partido;


public class AssociadoPartidoDto {


	private List<AssociadoDto> associados;

	
	public AssociadoPartidoDto(Partido partido) {
		this.associados = new ArrayList<>();
		this.associados.addAll(partido.getAssociados().stream().map(AssociadoDto::new).collect(Collectors.toList()));
		

	}


	public List<AssociadoDto> getAssociados() {
		return associados;
	}
	

}
