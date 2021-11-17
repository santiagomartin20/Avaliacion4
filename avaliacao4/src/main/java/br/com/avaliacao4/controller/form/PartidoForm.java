package br.com.avaliacao4.controller.form;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.avaliacao4.model.Ideologia;
import br.com.avaliacao4.model.Partido;
import br.com.avaliacao4.repository.PartidoRepository;

public class PartidoForm {

	@NotNull
	@NotEmpty
	private String nome;
	@NotNull
	@NotEmpty
	private String sigla;
	private Ideologia ideologia;
	private Date dataFundacao;

	public PartidoForm() {

	}

	public PartidoForm(String nome, String sigla, Ideologia ideologia, Date dataFundacao) {
		this.nome = nome;
		this.sigla = sigla;
		this.ideologia = ideologia;
		this.dataFundacao = dataFundacao;
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

	public Partido convertir() {
		return new Partido(nome, sigla, ideologia, dataFundacao);
	}

	public Partido actualizar(Long id, PartidoRepository partidoRepository) {
		Partido partido = partidoRepository.getOne(id);
		partido.setNome(this.nome);
		partido.setSigla(this.sigla);
		partido.setIdeologia(this.ideologia);
		partido.setDataFundacao(this.dataFundacao);

		return partido;
	}

}
