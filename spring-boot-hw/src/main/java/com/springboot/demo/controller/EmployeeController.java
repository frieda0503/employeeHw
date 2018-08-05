package com.springboot.demo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.demo.model.Department;
import com.springboot.demo.model.Employee;
import com.springboot.demo.repository.DepartmentRepository;
import com.springboot.demo.repository.EmployeeRepository;

@RestController
@RequestMapping(value = "/api")
public class EmployeeController {

	@Autowired
	private EmployeeRepository empRepository;

	@Autowired
	private DepartmentRepository depRepository;

	// get all data
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/get/employees", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Employee> getAll() {
		return empRepository.findAll();
	}

	// add data
	@PostMapping("/add/employee")
	public void addEmployee(@RequestBody Employee employee) {
		if (!depRepository.existsById(employee.getDep_id())) {
			throw new ResourceNotFoundException("Department is not found");
		}
		empRepository.save(employee);
	}

	// update data
	@PutMapping("/update/employee")
	public void updateEmployee(@RequestBody Employee employee) {
		empRepository.save(employee);
	}

	// delete data
	@DeleteMapping("/delete/employees/{id}")
	public void deleteEmployee(@PathVariable Integer id) {
		empRepository.deleteById(id);
	}
	
	

}
