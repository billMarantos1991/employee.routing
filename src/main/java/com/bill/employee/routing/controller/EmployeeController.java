package com.bill.employee.routing.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bill.employee.routing.exceptions.ResourceNotFoundException;

import com.bill.employee.routing.model.Employee;

import com.bill.employee.routing.service.EmployeeService;


@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService service;
   
		// get all employees
		@GetMapping
		public ResponseEntity<List<Employee>> getAllEmployees() {
	        List<Employee> list = service.getAllEmployees();

	        
	        return new ResponseEntity<List<Employee>>(list, new HttpHeaders(), HttpStatus.OK);
		}
		// get employees by attribute id
		@GetMapping("/connected_attribute/{id}")
		public ResponseEntity<List<Employee>> getAllEmployeesByAttributeId( @PathVariable("id") Long attributeId) {
	        List<Employee> list = service.getEmployeesByAttibuteId(attributeId);
	        return new ResponseEntity<List<Employee>>(list, new HttpHeaders(), HttpStatus.OK);
		}	
		@GetMapping("/not_contnain_employee/{id}")
		public ResponseEntity<List<Employee>> getFilterEmployeesById( @PathVariable("id") Long emplyeeId) {
	        List<Employee> list = service.getFilterEmployeesById(emplyeeId);
	        return new ResponseEntity<List<Employee>>(list, new HttpHeaders(), HttpStatus.OK);
		}			
		
		// get employee by id
		@GetMapping("/{id}")
		public ResponseEntity<Employee> geEmployeeById(@PathVariable("id") Long id) throws ResourceNotFoundException {
	        Employee employee = service.getEmployeeById(id);

	        return new ResponseEntity<Employee>(employee, new HttpHeaders(), HttpStatus.OK);
		}
		
		// create new employee 
		@PostMapping("/create_new")
		public ResponseEntity<Employee> createEmployee(@RequestBody Employee  employee) throws ResourceNotFoundException {
	       
			Employee  newEmployee = 	service.createAttribute(employee);
	        return new ResponseEntity<Employee>(newEmployee, new HttpHeaders(), HttpStatus.OK);
		}
	    @PutMapping("/update")
	    public ResponseEntity<Employee> updateEmployee(  @RequestBody Employee updatedEmployee)
	                                                    throws ResourceNotFoundException {
	    	Employee updated = service.updateEmployee(updatedEmployee);
	    	return new ResponseEntity<Employee>(updated, new HttpHeaders(), HttpStatus.OK);
	    }   
	    

	    @DeleteMapping("/{id}")
	    public HttpStatus deleteEmployeeById(@PathVariable("id") Long id) 
	                                                    throws ResourceNotFoundException {
	        service.deleteEmployeeById(id);
	        return HttpStatus.FORBIDDEN;
	    }		
		
}
