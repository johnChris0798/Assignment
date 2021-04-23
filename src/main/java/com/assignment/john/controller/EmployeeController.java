package com.assignment.john.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.john.exception.ResourceNotFoundException;
import com.assignment.john.model.Employee;
import com.assignment.john.repository.EmployeeRepo;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeRepo repo;
	
	@GetMapping
	public List<Employee> getAllEmployees() {
		return (List<Employee>) repo.findAll();
	}
	
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable(value = "id") Long id) {
		
		return repo.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
	}
	
	@PostMapping
	public Employee createEmployee(@RequestBody Employee employee) {
		
		return repo.save(employee);
	}
	
	@PutMapping("/{id}")
	public Employee updateEmployee(@PathVariable(value = "id") Long Id,
			@Validated @RequestBody Employee employeeDetails) {
		
		Employee employee = repo.findById(Id).
				orElseThrow(()-> new ResourceNotFoundException("Employee", "id", Id));
		
		employee.setFirst_name(employeeDetails.getFirst_name());
		employee.setLast_name(employeeDetails.getLast_name());
		employee.setEmployment_id(employeeDetails.getEmployment_id());
		employee.setDesignation(employeeDetails.getDesignation());
		employee.setDepartment(employeeDetails.getDepartment());
		employee.setReporting_manager(employeeDetails.getReporting_manager());
		employee.setStart_date(employeeDetails.getStart_date());
		employee.setEnd_date(employeeDetails.getEnd_date());
		employee.setDOB(employeeDetails.getDOB());
		employee.setGender(employeeDetails.getGender());
		employee.setBlood_group(employeeDetails.getBlood_group());
		employee.setAddress(employeeDetails.getAddress());
		
		Employee updatedEmployee = repo.save(employee);
		
		return updatedEmployee;
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") Long Id){
		
		Employee employee = repo.findById(Id).
				orElseThrow(() -> new ResourceNotFoundException("Employee", "id", Id));
		
		repo.delete(employee);
		
		return ResponseEntity.ok().build();
	}
	
	
	
	
}
