package com.bill.employee.routing.employee.routing;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bill.employee.routing.Application;
import com.bill.employee.routing.model.Attribute;
import com.bill.employee.routing.service.AttributeService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes =Application.class)
class AttributeServiceTest {

	@Autowired
	AttributeService attributeService;
	
	@Test
	void getAttributeByIdTest() {
		
		Attribute attribute = attributeService.getAttributeById(39L);
		
		assertEquals("Ιδιότητα",attribute.getDescription());
	}

}
