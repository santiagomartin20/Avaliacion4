package br.com.avaliacao4.controller.dto;

import java.util.Date;

import org.springframework.data.domain.Page;

import br.com.avaliacao4.model.Ideologia;
import br.com.avaliacao4.model.Partido;

public class PartidoDto {

	private Long id;
	private String nome;
	private String sigla;
	private Ideologia ideologia;
	private Date dataFundacao;

	public PartidoDto(Partido partido) {
		this.id= partido.getId();
		this.nome = partido.getNome();
		this.sigla = partido.getSigla();
		this.ideologia = partido.getIdeologia();
		this.dataFundacao = partido.getDataFundacao();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getSigla() {
		return sigla;
	}

	public Ideologia getIdeologia() {
		return ideologia;
	}

	public Date getDataFundacao() {
		return dataFundacao;
	}

	public static Page<PartidoDto> convertir(Page<Partido> partido) {
		return  partido.map(PartidoDto::new);
	}

}
