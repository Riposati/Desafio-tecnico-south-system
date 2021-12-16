package com.cooperativismo.cooperativismo;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cooperativismo.APICooperativismo.CooperativismoApplication;
import com.cooperativismo.APICooperativismo.pauta.Pauta;
import com.cooperativismo.APICooperativismo.pauta.PautaRepository;
import com.cooperativismo.APICooperativismo.pauta.PautaService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(
	    classes = CooperativismoApplication.class,
	    webEnvironment = WebEnvironment.RANDOM_PORT
	)
@AutoConfigureMockMvc

class CooperativismoApplicationTests {
	
	@Test
	public void contextLoads() {
	}
	
	@Mock
	private PautaService pautaService;
	
	@Mock
	private PautaRepository pautaRepository;

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
    ObjectMapper mapper;
	
	@Test
	public void getAllSchedule() throws Exception {
		mockMvc.perform(get("/cooperativismo/pauta"))
        .andExpect(status().isOk());
	}
	
	@Test
	public void getOneScheduleById() throws Exception {
		mockMvc.perform(get("/cooperativismo/pauta/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.idPauta", is(1)));
	}
	
	@Test
	public void testFindAll() {
		Mockito.when(pautaService.getPautas()).thenReturn(getMockPauta());
		List<Pauta> pautas = pautaService.getPautas();
		Assert.assertEquals(pautas, getMockPauta());		
		verify(pautaService).getPautas();
	}
	
	@Test
	public void testInsert() throws Exception {
		Mockito.when(pautaRepository.save(getMockPauta().get(0))).thenReturn(getMockPauta().get(0));
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/cooperativismo/pauta")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON)
	            .content(this.mapper.writeValueAsString(getMockPauta().get(0)));

	    mockMvc.perform(mockRequest)
	            .andExpect(status().isOk());	            
	}
	
	private List<Pauta> getMockPauta() {
		Pauta p1 = new Pauta("P1 teste");
		Pauta p2 = new Pauta("P2 teste");
		Pauta p3 = new Pauta("P3 teste");
		
		List<Pauta> list = new ArrayList<>();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		
		return list;
	}

}
