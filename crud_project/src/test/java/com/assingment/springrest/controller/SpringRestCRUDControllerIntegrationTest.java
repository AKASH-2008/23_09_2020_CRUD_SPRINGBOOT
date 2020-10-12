package com.assingment.springrest.controller;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;
import org.springframework.web.util.UriComponentsBuilder;

import com.assingment.springrest.dto.BusinessRequest;
import com.assingment.springrest.entity.Business;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringRestCRUDControllerIntegrationTest {

	@LocalServerPort
	private int port;
	
	@Autowired
	TestRestTemplate restTemplate;
	
	HttpHeaders headers = new HttpHeaders();
	
	@Test
	void SaveBusinessTest() throws IOException {
		String fileName = "TestFileJSONAdd.json";
		Gson gson = new Gson();
		BusinessRequest businessRequestObject = gson.fromJson(jsonFileTestasString(fileName), BusinessRequest.class);
		HttpEntity<BusinessRequest> entity = new HttpEntity<BusinessRequest>(businessRequestObject, headers);
		
		ResponseEntity<Business> response = restTemplate.exchange(
				createURLWithPort("/springrest/add", ""),HttpMethod.POST, entity, Business.class);
		//System.out.println(createURLWithPort("/springrest/add", null));
		//System.out.println(response.getBody().getBusiness_name().toString());
		
		assertThat(response.getStatusCode().toString(), (Matcher) is("200 OK"));
		assertThat(response.getBody().getBusiness_name(), (Matcher) is("FACT1"));
		
	}

	
	@Test
	void UpdateBusinessTest() throws JsonSyntaxException, IOException {
		String fileName = "TestFileJSONUpdate.json";
		Gson gson = new Gson();
		BusinessRequest businessRequestObject = gson.fromJson(jsonFileTestasString(fileName), BusinessRequest.class);
		HttpEntity<BusinessRequest> entity = new HttpEntity<BusinessRequest>(businessRequestObject, headers);
		
		//System.out.println(createURLWithPort("/springrest/update/","100"));
		
		ResponseEntity<Business> response = restTemplate.exchange(
				createURLWithPort("/springrest/update/","100"),HttpMethod.PUT, entity, Business.class);	
		System.out.println(response.toString());
	
		assertThat(response.getStatusCode().toString(), (Matcher) is("200 OK"));
		assertThat(response.getBody().getBusiness_name(), (Matcher) is("FACT"));
		
	}
	
	@Test
	void SearchByKeyTest() throws IOException {
		
		String active_ind = "active_ind";
		
		String branch_address = "branch_address";
		
		String business_name = "business_name";
		
		String pan = "pan";

        //POSITIVE TEST

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        
		ResponseEntity<Business[]> response1 = restTemplate.exchange(querySearchBuilder(this.port,
				"active_ind", "true"),
				HttpMethod.GET, entity, Business[].class);
        assertThat(response1.getStatusCode().toString(), (Matcher) is("200 OK"));
        
        ResponseEntity<Business[]> response2 = restTemplate.exchange(querySearchBuilder(this.port,
				"branch_address", "NEW WORLD"),
				HttpMethod.GET, entity, Business[].class);
        assertThat(response2.getStatusCode().toString(), (Matcher) is("200 OK"));
        
        ResponseEntity<Business[]> response3 = restTemplate.exchange(querySearchBuilder(this.port,
				"business_name", "FACT"),
				HttpMethod.GET, entity, Business[].class);
        assertThat(response3.getStatusCode().toString(), (Matcher) is("200 OK"));
       
      //NEGATIVE TEST
        
        ResponseEntity<Business> response4 = restTemplate.exchange(querySearchBuilder(this.port,
				"pan", "asadsw"),
				HttpMethod.GET, entity, Business.class);
        assertThat(response4.getStatusCode().toString(), (Matcher) is("400 BAD_REQUEST"));
	}
	
	private String jsonFileTestasString(String fileName) throws IOException {
		File file = ResourceUtils.getFile("classpath:"+fileName);
		String content = new String(Files.readAllBytes(file.toPath()));
		return content;
	}
	
	private String asJsonString(final Object obj) throws JsonProcessingException {
		final ObjectMapper mapper = new ObjectMapper();
		String jsonContent = mapper.writeValueAsString(obj);
		return jsonContent;
	}

	private String createURLWithPort(String uri, String id) {
		return "http://localhost:" + port + uri +id;
	}
	
	private URI querySearchBuilder(int port, String searchToken, String searchValue) {
		
        String url = "http://localhost:" + port;
        
        String path = "/springrest/search";
		
		return UriComponentsBuilder.fromHttpUrl(url).path(path)
				.queryParam(searchToken, searchValue).build().toUri();
	}
}
