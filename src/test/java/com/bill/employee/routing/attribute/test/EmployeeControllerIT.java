package com.bill.employee.routing.attribute.test;




import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
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
	
	private TestRestTemplate restTemplate;
	private HttpHeaders headers;
	
	@Before
	public void before() {
		restTemplate = new TestRestTemplate();
		headers = new HttpHeaders();
		
		List<MediaType> mediaTypes =new ArrayList<>();
		mediaTypes.add(MediaType.APPLICATION_JSON);
		headers.setAccept( mediaTypes );
		
	}

	@Test
	public void retrieveEmployeeTest() throws JSONException {
	
		String retrieveEmployeePath = "/33";
		String url = createUrlWithPort(retrieveEmployeePath); 
		
		HttpEntity<String> httpEntity = new HttpEntity<String>("parameters",headers);
		ResponseEntity<String> response = restTemplate.exchange(url,HttpMethod.GET,httpEntity,String.class);

		String expected = "{employeeId:33,firstName:Χρήστος}";
		JSONAssert.assertEquals(expected, response.getBody(), false);

	}

	@Test
	public void retrieveEmployeesFromConnectedAttributeTest() throws JSONException {
	
		String retrieveEmployeePath = "/connected_attribute/39";
		String url = createUrlWithPort(retrieveEmployeePath); 
		
		HttpEntity<String> httpEntity = new HttpEntity<String>("parameters",headers);
		ResponseEntity<String> response = restTemplate.exchange(url,HttpMethod.GET,httpEntity,String.class);


		String expected = "{employeeId:34}";
		expected += ",{employeeId:39}";
		expected += ",{employeeId:40}";
		expected += ",{employeeId:42}";
		expected += ",{employeeId:45}";
		expected += ",{employeeId:48}";
		expected += ",{employeeId:33}";
		expected = "["+expected+"]";
		
		JSONAssert.assertEquals(expected, response.getBody(), false);

	}	
	
	@Test
	public void retrieveEmployeesNotContainSpecificEmployee() throws JSONException {
	
		String retrieveEmployeePath = "/not_contnain_employee/39";
		String url = createUrlWithPort(retrieveEmployeePath); 
		
		HttpEntity<String> httpEntity = new HttpEntity<String>("parameters",headers);
		ResponseEntity<String> response = restTemplate.exchange(url,HttpMethod.GET,httpEntity,String.class);

		String expected = "{employeeId:33}";
		expected += ",{employeeId:34}";
		expected += ",{employeeId:35}";
		expected += ",{employeeId:36}";
		expected += ",{employeeId:38}";
		expected += ",{employeeId:40}";
		expected += ",{employeeId:41}";
		expected += ",{employeeId:42}";
		expected += ",{employeeId:44}";
		expected += ",{employeeId:45}";
		expected += ",{employeeId:47}";
		expected += ",{employeeId:48}";
		expected = "["+expected+"]";
		
		JSONAssert.assertEquals(expected, response.getBody(), false);

	}	
	private String createUrlWithPort(String retrieveEmployee) {
		return "http://localhost:"+port+"/api/employees" + retrieveEmployee;
	}
 
}
