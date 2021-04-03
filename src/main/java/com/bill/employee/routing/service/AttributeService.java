package com.bill.employee.routing.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.bill.employee.routing.exceptions.ResourceNotFoundException;
import com.bill.employee.routing.model.Attribute;
import com.bill.employee.routing.model.Employee;
import com.bill.employee.routing.repositories.AttributeRepository;
import com.bill.employee.routing.repositories.EmployeeRepository;



@Service
public class AttributeService {

	@Autowired
	AttributeRepository repository;

	@Autowired
	EmployeeRepository employeeRepository;
	 
	public List<Attribute> getAllAttributes(){
		
		List<Attribute> attributes = repository.findAll();

		if(attributes.size() > 0) {
			return attributes;
		}else {
			return new ArrayList<Attribute>();
		}
	}

	public Attribute getAttributeById(Long id) throws ResourceNotFoundException {

		
		Optional<Attribute> attribute = repository.findById(id);
		
		if(attribute.isPresent()) {
			
			return attribute.get();
		}
		else {
			
			
			throw new ResourceNotFoundException("No attribute record exist for given id");
		}
	}
	@Transactional
	public Attribute createAttribute(Attribute attribute) {
		
		return repository.save(attribute);
	}
	
	@Transactional
	public Attribute updateAttribute(Attribute attributeEntity) throws ResourceNotFoundException {
		
		Optional<Attribute> attribute = repository.findById(attributeEntity.getAttributeId());
		
		if(attribute.isPresent()) {
			Attribute newEntity =  attribute.get();
			//
			newEntity.setDescription(attributeEntity.getDescription());
			newEntity.setValue(attributeEntity.getValue());
			newEntity = repository.save(newEntity);
			return newEntity;
		}else {
			throw new ResourceNotFoundException("No attribute record exist for given id");
		}
		
	}
	
	@Transactional
	public void deleteAttributeById(Long id)throws ResourceNotFoundException {
		Optional<Attribute> attribute = repository.findById(id);
		
		if(attribute.isPresent()) {
			
			repository.deleteAttributeByAttibuteId(id);
		}
		else {
			throw new ResourceNotFoundException("No attribute record exist for given id");
		}
	}
	
}
