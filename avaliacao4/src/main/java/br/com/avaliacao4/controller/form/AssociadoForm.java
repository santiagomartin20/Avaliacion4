package br.com.avaliacao4.controller.form;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import br.com.avaliacao4.model.Associado;
import br.com.avaliacao4.model.Cargo;
import br.com.avaliacao4.model.Partido;
import br.com.avaliacao4.model.Sexo;
import br.com.avaliacao4.repository.AssociadoRepository;

public class AssociadoForm {

	@NotNull
	@NotEmpty
	private String nome;
	@NotNull
	private Cargo cargo;
	@NotNull
	private Sexo sexo;
	private Date dataNacimento;
	private Partido partido;
	
	
	public AssociadoForm() {

	}

	public AssociadoForm(String nome, Cargo cargo, Sexo sexo, Date dataNacimento, Partido partido) {
		this.nome = nome;
		this.cargo = cargo;
		this.sexo = sexo;
		this.dataNacimento = dataNacimento;
		this.partido= partido;
		
	}



	public String getNome() {
		return nome;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public Date getDataNacimento() {
		return dataNacimento;
	}

	public Associado convertir(Partido partido) {
		this.partido=partido;
		return new Associado(nome, cargo, sexo, dataNacimento, this.partido);
	}

	public Associado actualizar(Long id, AssociadoRepository associadoRepository) {
		Associado associado = associadoRepository.getOne(id);
		associado.setNome(this.nome);
		associado.setCargo(this.cargo);
		associado.setSexo(this.sexo);
		associado.setDataNacimento(this.dataNacimento);

		return associado;
	}
	

}
