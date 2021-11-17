package br.com.avaliacao4.model;

import java.util.Arrays;
import java.util.stream.Stream;

public enum Ideologia {

	DIREITA("Direita"), 
	CENTRO("Centro"), 
	ESQUERDA("Esquerda"), 
	NULL("null") ;

	
	public static Stream<Ideologia> stream() {
		return Arrays.stream(Ideologia.values());
	} 
	
	private final String name;
	
	private Ideologia(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}

	

