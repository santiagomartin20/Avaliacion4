package br.com.avaliacao4.controller;


import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PartidoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void deveriaDevolver201CasoDadosDeAutenticacaoEstejamCorretos() throws Exception {
		URI uri = new URI("/partido");
		String json = "{\"dataFundacao\": \"2021-11-17T04:25:49.174Z\",\r\n"
				+ "  \"ideologia\": \"DIREITA\",\r\n"
				+ "  \"nome\": \"string\",\r\n"
				+ "  \"sigla\": \"string\"\r\n"
				+ "}";

		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(201));
	}

	@Test
	public void deveriaDevolver400CasoDadosDeAutenticacaoEstejamNull() throws Exception {
		URI uri = new URI("/partido");
		String json = "{}";

		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}
	
	@Test
	public void deveriaDevolver400CasoDadosDeAutenticacaoEstejamIncorretos() throws Exception {
		URI uri = new URI("/partido");
		String json = "{\r\n" + "  \"cargo\": \"NOvlido\",\r\n"
				+ "  \"dataNacimento\": \"202ef1-11-17T04:17:49.448Z\",\r\n" + "  \"nome\": \"sdasda\",\r\n"
				+ "  \"sexo\": \"MASCsULINO\"\r\n" + "}";

		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}
	
	@Test
	public void get200BusquedaSinFiltro() throws Exception {
		URI uri = new URI("/partido");
		String json = "{}";

		mockMvc.perform(MockMvcRequestBuilders.get(uri).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void get200ComFiltroCorrecto() throws Exception {
		URI uri = new URI("/partido?filtro=IDEOLOGIA");
	

		mockMvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}
	@Test
	public void get400ComFiltroIncorrecto() throws Exception {
		URI uri = new URI("/associados?filtro=IDEOLOGIAs");

		mockMvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}
	
	@Test
	public void delete200byId() throws Exception {
		URI uri = new URI("/partido/0");

		mockMvc.perform(MockMvcRequestBuilders.delete(uri).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void delete404byId() throws Exception {
		URI uri = new URI("/partido/10");

		mockMvc.perform(MockMvcRequestBuilders.delete(uri).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(404));
	}
	
	
	



}



