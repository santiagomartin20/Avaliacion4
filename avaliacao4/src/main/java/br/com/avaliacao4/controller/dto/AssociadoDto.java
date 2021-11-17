package br.com.avaliacao4.controller.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.domain.Page;

import br.com.avaliacao4.model.Associado;


public class AssociadoDto {

	private Long id;
	private String nome;
	private String cargo;
	private String sexo;
	private String DataNacimento;
	private Long partido;
	
	public AssociadoDto(Associado associado) {
		this.id= associado.getId();
		this.nome = associado.getNome();
		this.cargo = associado.getCargo().getName();
		this.sexo = associado.getSexo().getName();
		Date dataIso = associado.getDataNacimento();
		this.DataNacimento =new SimpleDateFormat("dd-MM-yyyy").format(dataIso);
		this.partido= associado.getPartido().getId();
		
		System.out.println(associado.getId());

	}
	

	public String getDataNacimento() {
		return DataNacimento;
	}


	public Long getPartido() {
		return partido;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public static Page<AssociadoDto> convertir(Page<Associado> associado) {
		return associado.map(AssociadoDto::new);

	}
}
