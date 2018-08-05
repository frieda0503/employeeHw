package com.springboot.demo.controller;

import java.util.List;

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

import com.springboot.demo.model.Department;
import com.springboot.demo.service.DepartmentService;

@RestController
@RequestMapping(value = "/api")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	// get all data
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/get/departments", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Department> getAll() {
		return departmentService.getAll();
	}

	// add data
	@PostMapping("/add/department")
	public void addDepartment(@RequestBody Department department) {
		departmentService.addDepartment(department);
	}

	// update data
	@PutMapping("/update/department")
	public void updateDepartment(@RequestBody Department department) {
		departmentService.updateDepartment(department);
	}

	// delete data
	@DeleteMapping("/departments/{id}")
	public void deleteDepartment(@PathVariable Integer id) {
		departmentService.deleteDepartment(id);
	}
	
	// find data by multiple condition
//		@ResponseStatus(HttpStatus.OK)
//		@GetMapping(value = "/find/employees", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//		public List<Employee> findByCondition() {
//			return empRepository.findAll();
//		}
	// get all data
		@ResponseStatus(HttpStatus.OK)
		@GetMapping(value = "/get/department/employees", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public @ResponseBody Page<Department> getAll(
				@RequestParam(required = false) String employeeName,
				@RequestParam(required = false) Integer employeeId,
				@RequestParam(required = false) Integer age,
				@RequestParam(required = false) String departmentName) {
			return departmentService.getEmployeeData(employeeName, employeeId, age,
					departmentName);

			// String employeeName, int employeeId,int age,String departmentName
		}

}
