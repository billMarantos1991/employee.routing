package com.bill.employee.routing.repositories;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bill.employee.routing.model.Employee;


@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	 @Query("select a.employees from Attribute a where a.attributeId=:attributeId")
	 List<Employee> getEmployeesByAttibuteId(Long attributeId);	
	 
	 @Query("select e from Employee e where e.employeeId!=:employeeId")
	 List<Employee> getFilterEmployeesById(Long employeeId);	
}
