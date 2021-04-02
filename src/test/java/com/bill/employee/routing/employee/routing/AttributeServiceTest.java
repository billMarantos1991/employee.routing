package com.bill.employee.routing.employee.routing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.bill.employee.routing.Application;
import com.bill.employee.routing.model.Attribute;
import com.bill.employee.routing.service.AttributeService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes =Application.class)

class AttributeServiceTest {

	@Autowired
	AttributeService attributeService;
	

	@Test
	void getAttributeByIdTest() {

		Attribute attribute = attributeService.getAttributeById(39L);
		assertEquals("Ιδιότητα",attribute.getDescription());
	}	

	@Test
	void deleteAttributeByIdTest() {
	
		attributeService.deleteAttributeById(59L);
		assertNull(  attributeService.getAttributeById(59L) );
		
	}
	
	
	
	@Test
	void updateAttributeByIdTest() {
		

		Attribute attribute = attributeService.getAttributeById(39L);
		
		//update values
		attribute.setDescription("Ιδιότητα new aaaa");
		attribute.setValue("Αποθηκάριος a a");
		attributeService.updateAttribute(attribute);
		
		//check if updated		
		assertEquals("Ιδιότητα new aa",attribute.getDescription());
		assertEquals("new value a",attribute.getValue());
		
	}

}
