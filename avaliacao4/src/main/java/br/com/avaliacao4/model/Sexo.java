package br.com.avaliacao4.model;

import java.util.Arrays;
import java.util.stream.Stream;

public enum Sexo {

	MASCULINO("Masculino"), FEMININO("Feminino"),NULL("null");

	public static Stream<Sexo> stream() {
		return Arrays.stream(Sexo.values());
	}

	private final String name;

	private Sexo(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
