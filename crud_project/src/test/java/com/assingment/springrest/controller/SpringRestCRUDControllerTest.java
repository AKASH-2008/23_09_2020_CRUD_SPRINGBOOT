package com.assingment.springrest.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.assingment.springrest.dto.BusinessRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import brave.Span;
import brave.Tracer;
import brave.propagation.TraceContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class SpringRestCRUDControllerTest {
	
	@InjectMocks
	SpringRestCRUDController springRestCRUDController;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@MockBean
	private Tracer tracer;
	
	@MockBean
	private Span span;

	@Autowired
	MockMvc mockMvc;
	
	private TraceContext context;

	
	 @Before
     public void setup() {

		 mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		 context = TraceContext.newBuilder().traceId(1).spanId(3).build();
		 when(tracer.currentSpan()).thenReturn(span);
		 when(span.context()).thenReturn(context);
	 
	 }
	
	@Test
	void SaveBusinessTest() throws Exception{
		
		String json = "{\r\n"
				+ "  \"business\": {\r\n"
				+ "    \"business_id\": \"200\",\r\n"
				+ "    \"business_name\": \"FACT1\",\r\n"
				+ "    \"contact_no\": \"500000650\",\r\n"
				+ "    \"pan\": \"963369\",\r\n"
				+ "    \"branches\": [\r\n"
				+ "      {\r\n"
				+ "        \"branch_id\": \"100\",\r\n"
				+ "        \"business_id\": \"200\",\r\n"
				+ "        \"branch_address\": \"NEW WORLD\",\r\n"
				+ "        \"branch_contact\": \"963369\",\r\n"
				+ "        \"active_ind\": \"true\"\r\n"
				+ "      },\r\n"
				+ "      {\r\n"
				+ "        \"branch_id\": \"200\",\r\n"
				+ "        \"business_id\": \"200\",\r\n"
				+ "        \"branch_address\": \"NEW WORLD DINO\",\r\n"
				+ "        \"branch_contact\": \"963369\",\r\n"
				+ "        \"active_ind\": \"false\"\r\n"
				+ "      }\r\n"
				+ "    ]\r\n"
				+ "  }\r\n"
				+ "}";
			    
		Gson gson = new Gson();
			    
		BusinessRequest businessRequestObject = gson.fromJson(json, BusinessRequest.class);
		
		this.mockMvc.perform(post("/springrest/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(businessRequestObject))
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk());
	}

	@Test
	void UpdateBusinessTest() throws JsonProcessingException, Exception {

		String json = "{\"business\":{\"business_id\":\"100\",\"business_name\":\"FACT\",\"contact_no\":\"5000000\",\"pan\":\"963369963\",\"branches\":[{\"branch_id\":\"10\",\"business_id\":\"100\",\"branch_address\":\"NEW WORLD\",\"branch_contact\":\"9633699631\",\"active_ind\":\"true\"},{\"branch_id\":\"20\",\"business_id\":\"100\",\"branch_address\":\"NEW WORLD DINO\",\"branch_contact\":\"9633699632\",\"active_ind\":\"false\"}]}}";
	    
		Gson gson = new Gson();
			    
		BusinessRequest businessRequestObject = gson.fromJson(json, BusinessRequest.class);
		
		this.mockMvc.perform(put("/springrest/update/{id}",100)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(businessRequestObject))
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk());
		
	}

	@Test
	void SearchByKeyTest() throws Exception {
		
		/*
		 * String business_name = "FACT"; Integer pan = 963369963; String branch_address
		 * = "NEW WORLD"; Boolean active_ind = true; LocalDate creation_date =
		 * LocalDate.of(2020, Month.SEPTEMBER, 23);
		 */
		
		this.mockMvc.perform(get("/springrest/search")
				
				.contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk());
	}

	private String asJsonString(final Object obj) throws JsonProcessingException {
		
		final ObjectMapper mapper = new ObjectMapper();
		String jsonContent = mapper.writeValueAsString(obj);
		return jsonContent;
	}
	
}
