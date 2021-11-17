package br.com.avaliacao4.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Associado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@Enumerated(EnumType.STRING)
	private Cargo cargo;
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
    @Temporal(TemporalType.DATE)
	private Date DataNacimento;
    
    @ManyToOne
    private Partido partido;
    
	public Associado() {

	}

	public Associado(String nome, Cargo cargo, Sexo sexo, Date DataNacimento, Partido partido ) {
		this.nome = nome;
		this.cargo = cargo;
		this.sexo = sexo;
		this.DataNacimento = DataNacimento;
		this.partido= partido;
	}
	
	

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
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

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Date getDataNacimento() {
		return DataNacimento;
	}

	public void setDataNacimento(Date DataNacimento) {
		this.DataNacimento = DataNacimento;
	}

	
	
}
