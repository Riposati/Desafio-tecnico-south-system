package com.cooperativismo.cooperativismo;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.cooperativismo.APICooperativismo.CooperativismoApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(
	    classes = CooperativismoApplication.class,
	    webEnvironment = WebEnvironment.RANDOM_PORT
	)
@AutoConfigureMockMvc //need this in Spring Boot test
class CooperativismoApplicationTests {

	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void getAllSchedule() throws Exception {
		mockMvc.perform(get("/cooperativismo/pauta"))
//		.andDo(print())
        .andExpect(status().isOk());
	}
	
	@Test
	public void getOneScheduleById() throws Exception {
		mockMvc.perform(get("/cooperativismo/pauta/1"))
//		.andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.idPauta", is(1)));
	}

}
