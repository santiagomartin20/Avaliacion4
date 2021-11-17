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
public class AssociadoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void deveriaDevolver201CasoDadosDeAutenticacaoEstejamCorretos() throws Exception {
		URI uri = new URI("/associados");
		String json = "{\r\n" + "  \"cargo\": \"VEREADOR\",\r\n"
				+ "  \"dataNacimento\": \"2021-11-17T04:17:49.448Z\",\r\n" + "  \"nome\": \"Juan\",\r\n"
				+ "  \"sexo\": \"MASCULINO\"\r\n" + "}";

		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(201));
	}

	@Test
	public void deveriaDevolver400CasoDadosDeAutenticacaoEstejamNull() throws Exception {
		URI uri = new URI("/associados");
		String json = "{\r\n" + "  \"cargo\": \"\",\r\n" + "  \"dataNacimento\": \"\",\r\n" + "  \"nome\": \"\",\r\n"
				+ "  \"sexo\": \"\"\r\n" + "}";

		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void deveriaDevolver400CasoDadosDeAutenticacaoEstejamIncorretos() throws Exception {
		URI uri = new URI("/associados");
		String json = "{\r\n" + "  \"cargo\": \"NOvlido\",\r\n"
				+ "  \"dataNacimento\": \"202ef1-11-17T04:17:49.448Z\",\r\n" + "  \"nome\": \"sdasda\",\r\n"
				+ "  \"sexo\": \"MASCsULINO\"\r\n" + "}";

		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void deveriaDevolver200ComPartidoEAssociadoCorrecto() throws Exception {
		URI uri = new URI("/associados/partidos");
		String json = "{\r\n" + "  \"idAssociado\": 0,\r\n" + "  \"idPartido\": 0\r\n" + "}";

		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	public void deveriaDevolver400PorPartidoEAssicuadoIncorrecto() throws Exception {
		URI uri = new URI("/associados/partidos");
		String json = "{\r\n" + "  \"idAssociado\": 1,\r\n" + "  \"idPartido\": 2\r\n" + "}";

		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(404));
	}
	
	@Test
	public void get200BusquedaSinFiltro() throws Exception {
		URI uri = new URI("/associados");
		String json = "{}";

		mockMvc.perform(MockMvcRequestBuilders.get(uri).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}
	@Test
	public void get200ComFiltroCorrecto() throws Exception {
		URI uri = new URI("/associados?filtro=CARGO");
	

		mockMvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}
	@Test
	public void get400ComFiltroIncorrecto() throws Exception {
		URI uri = new URI("/associados?filtro=CARGOs");

		mockMvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}
	
	@Test
	public void delete200byId() throws Exception {
		URI uri = new URI("/associados/0");

		mockMvc.perform(MockMvcRequestBuilders.delete(uri).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void delete404byId() throws Exception {
		URI uri = new URI("/associados/10");

		mockMvc.perform(MockMvcRequestBuilders.delete(uri).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(404));
	}
}
