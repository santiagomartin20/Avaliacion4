package br.com.avaliacao4.model;

import java.util.Arrays;
import java.util.stream.Stream;

public enum PartidoFiltro {
	IDEOLOGIA("ideologia");

	public static Stream<PartidoFiltro> stream() {
		return Arrays.stream(PartidoFiltro.values());
	}

	private final String name;

	private PartidoFiltro(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}


