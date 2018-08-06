package com.springboot.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.demo.model.Employee;
import com.springboot.demo.service.DepartmentService;
import com.springboot.demo.service.EmployeeService;

@RestController
@RequestMapping(value = "/api")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private DepartmentService departmentService;

	// get all data
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/employees/all")
	@ResponseBody
	public List<Employee> getAll() {
		return employeeService.getAll();
	}

	// get all data by page
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/employees")
	@ResponseBody
	public Page<Employee> getAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size) {
		return employeeService.getAll(page, size);
	}

	// add data
	@PostMapping("/employee")
	public void addEmployee(@RequestBody Employee employee) {
		employeeService.addEmployee(employee);
	}

	// update data by id
	@PutMapping("/employee/{id}")
	public Employee updateEmployee(@PathVariable(required = true) Integer id,
			@RequestBody Employee employee) {
		return employeeService.updateEmployee(id, employee);
	}

	// delete data by id
	@DeleteMapping("/employees/{id}")
	public void deleteEmployee(@PathVariable Integer id) {
		employeeService.deleteEmployee(id);
	}
	
}