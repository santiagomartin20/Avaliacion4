package br.com.avaliacao4.model;

import java.util.Arrays;
import java.util.stream.Stream;

public enum AssociadoFiltro {
	CARGO("cargo");

	public static Stream<AssociadoFiltro> stream() {
		return Arrays.stream(AssociadoFiltro.values());
	}

	private final String name;

	private AssociadoFiltro(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}


