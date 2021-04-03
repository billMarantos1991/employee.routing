package com.bill.employee.routing.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bill.employee.routing.exceptions.ResourceNotFoundException;
import com.bill.employee.routing.model.Address;
import com.bill.employee.routing.model.Attribute;
import com.bill.employee.routing.model.Employee;
import com.bill.employee.routing.repositories.AddressRepository;
import com.bill.employee.routing.repositories.AttributeRepository;
import com.bill.employee.routing.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	AddressRepository addressRepository;
	 
	@Autowired
	AttributeRepository attributeRepository;
	//
	public List<Employee> getAllEmployees(){
		
		List<Employee> employees= employeeRepository.findAll();
		//List<Employee> employees=employeeRepository.getClosestOffer(12L);
		if(employees.size() > 0) {
			return employees;
		}else {
			return new ArrayList<Employee>();
		}
		
		
	}
	
	public Employee getEmployeeById(Long id) throws ResourceNotFoundException{
		
		Optional<Employee> employee= employeeRepository.findById(id);
     
		if(employee.isPresent()) {
			return employee.get();
		}else {
            throw new ResourceNotFoundException("No employee record exist for given id");
		}
	}	
	
	@Transactional
	public Employee updateEmployee(Employee employeeEntity) throws ResourceNotFoundException {
		
		Optional<Employee> findedEmployee = employeeRepository.findById(employeeEntity.getEmployeeId());
		
		if(findedEmployee.isPresent()) {
			Employee newEntity =   findedEmployee.get();
			
			
			newEntity.setDateOfBirthday(employeeEntity.getDateOfBirthday() );
			newEntity.setFirstName( employeeEntity.getFirstName());
			newEntity.setLastName( employeeEntity.getLastName());
			newEntity.setHasVehicle(employeeEntity.isHasVehicle());
			
			//Save Address
			Address address = addressRepository.saveAndFlush(employeeEntity.getAddress());
			newEntity.setAddress(address);
			newEntity.setAddressId(address.getAddressId());
			
		
			newEntity.updateAttributeList(employeeEntity.getAttributes());
			newEntity = employeeRepository.save(newEntity);
		 
			return newEntity;
			
		}else {
			throw new ResourceNotFoundException("No Employee record exist for given id");
		}
		
	}
	
	@Transactional
	public Employee createAttribute(Employee employeeEntity) {
		
		Address address = employeeEntity.getAddress();

		address = addressRepository.save(address);
		employeeEntity.setAddress(address);
		employeeEntity.setAddressId(address.getAddressId());
		
		employeeEntity = employeeRepository.save(employeeEntity);
		return employeeEntity;
		
	}	
	
	@Transactional
	// delete employee by id
	public void deleteEmployeeById(Long id)throws ResourceNotFoundException {
		Optional<Employee> employee = employeeRepository.findById(id);
		
		if(employee.isPresent()) {
			
			Employee deletedEmployee  = employee.get();
			deletedEmployee.getAttributes()
			.stream()
			.forEach( attribute->deletedEmployee.removeAttibute(attribute) );
			
			employeeRepository.deleteById(id);
		}
		else {
			throw new ResourceNotFoundException("No attribute record exist for given id");
		}
	}

	//get employees where attribute id is :attributeId
	public List<Employee> getEmployeesByAttibuteId(Long attributeId) {
		
		List<Employee> employees=employeeRepository.getEmployeesByAttibuteId(attributeId);
		if(employees.size() > 0) {
			return employees;
		}else {
			return new ArrayList<Employee>();
		}		
		
	}
	//get employees except emnployee with this employees id:employeeId
	public List<Employee> getFilterEmployeesById(Long employeeId) {
		
		List<Employee> employees=employeeRepository.getFilterEmployeesById(employeeId);
		if(employees.size() > 0) {
			return employees;
		}else {
			return new ArrayList<Employee>();
		}		
		
	}
	
}
