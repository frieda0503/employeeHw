package com.springboot.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
	@GetMapping(value = "/get/employees", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Page<Employee> getAll(
			@RequestParam(required = false) String employeeName,
			@RequestParam(required = false) Integer employeeId,
			@RequestParam(required = false) Integer age,
			@RequestParam(required = false) String departmentName) {
		return employeeService.getEmployeeData(employeeName, employeeId, age,
				departmentName);

		// String employeeName, int employeeId,int age,String departmentName
	}

	// add data
	@PostMapping("/add/employee")
	public void addEmployee(@RequestBody Employee employee) {
		employeeService.addEmployee(employee);
	}

	// update data
	@PutMapping("/update/employee")
	public void updateEmployee(@RequestBody Employee employee) {
		employeeService.updateEmployee(employee);
	}

	// delete data
	@DeleteMapping("/delete/employees/{id}")
	public void deleteEmployee(@PathVariable Integer id) {
		employeeService.deleteEmployee(id);
	}

}
