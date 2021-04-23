package com.assignment.john.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.john.model.Employee;

@Repository
public interface EmployeeRepo extends CrudRepository<Employee, Long>{
	
	
}
