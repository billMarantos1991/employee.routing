package com.bill.employee.routing.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bill.employee.routing.exceptions.ResourceNotFoundException;
import com.bill.employee.routing.model.Attribute;
import com.bill.employee.routing.servicesa.AttributeService;


@RestController
@RequestMapping("/api/attributes")
public class AttributeController {

    @Autowired
    AttributeService service;
 
    //get attributes
    @GetMapping
    public ResponseEntity<List<Attribute>> getAllAttributes()  {
        List<Attribute> list = service.getAllAttributes();
      
        return new ResponseEntity<List<Attribute>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    //get attribute by id
    @GetMapping("/{id}")
    public ResponseEntity<Attribute> getAttributeById(@PathVariable("id") Long id) throws ResourceNotFoundException {
    	Attribute attribute = service.getAttributeById(id);
 
        return new ResponseEntity<Attribute>(attribute, new HttpHeaders(), HttpStatus.OK);
    }
 
    //create new attribute
    @PostMapping("/create_new")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Attribute> createAttribute(  @RequestBody Attribute attribute)
                                                    throws ResourceNotFoundException {

    	Attribute newAttribute = service.createAttribute(attribute);
        return new ResponseEntity<Attribute>(newAttribute, new HttpHeaders(), HttpStatus.OK);
    }
    //update  attribute
    @PutMapping("/update")
    public ResponseEntity<Attribute> updateAttribute(  @RequestBody Attribute updatedAttribute)
                                                    throws ResourceNotFoundException {
    	Attribute updated = service.updateAttribute(updatedAttribute);
    	
    	return new ResponseEntity<Attribute>(updated, new HttpHeaders(), HttpStatus.OK);
    }  
 
    //delete  attribute
    @DeleteMapping("/{id}")
    public HttpStatus deleteAttributeById(@PathVariable("id") Long id) 
                                                    throws ResourceNotFoundException {
        service.deleteAttributeById(id);
        
        return HttpStatus.FORBIDDEN;
    }	
	
}
