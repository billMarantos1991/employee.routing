package com.bill.employee.routing.attribute.test;




import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.bill.employee.routing.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes =Application.class,webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIT {
	
	@LocalServerPort
	private int port;

	@Test
	public void test() {
	
		String url =  "http://localhost:"+port+"/api/employees";
		url = url + "/33"; 
		TestRestTemplate restTemplate = new TestRestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		
		List<MediaType> mediaTypes =new ArrayList<>();
		mediaTypes.add(MediaType.APPLICATION_JSON);
		headers.setAccept( mediaTypes );
		
		HttpEntity<String> httpEntity = new HttpEntity<String>("parameters",headers);

		ResponseEntity<String> response = restTemplate.exchange(url,HttpMethod.GET,httpEntity,String.class);
		
		System.out.println("response: " +response.getBody());
		
		assertTrue(response.getBody().contains("\"firstName\":\"Χρήστος\""));
	}

}
