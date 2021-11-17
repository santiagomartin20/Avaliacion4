package br.com.avaliacao4.model;

import java.util.Arrays;
import java.util.stream.Stream;

public enum Cargo {
	
	VEREADOR("Vereador"), 
	PREFEITO("Prefeito"),
	DEPUTADO_ESTADUAL("Deputado Estadual"),
	DEPUTADO_FEDERAL("Deputado Federal"),
	SENADOR("Senador"), 
	GOVERNADOR("Governador"),
	PRESIDENTE("Presidente"), 
	NENHUM("Nenhum");

	public static Stream<Cargo> stream() {
		return Arrays.stream(Cargo.values());
	}

	private final String name;

	private Cargo(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
