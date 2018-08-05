package com.springboot.demo.controller;

import com.springboot.demo.model.Employee;
import com.springboot.demo.service.DepartmentService;
import com.springboot.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

	// get all data
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/employees")
	@ResponseBody
	public Page<Employee> getAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "5") Integer size) {
		return employeeService.getAll(page, size);
	}

	// add data
	@PostMapping("/employee")
	public void addEmployee(@RequestBody Employee employee) {
		employeeService.addEmployee(employee);
	}

	// update data
	@PutMapping("/employee")
	public void updateEmployee(@RequestBody Employee employee) {
		employeeService.updateEmployee(employee);
	}

	// delete data
	@DeleteMapping("/employees/{id}")
	public void deleteEmployee(@PathVariable Integer id) {
		employeeService.deleteEmployee(id);
	}

}
