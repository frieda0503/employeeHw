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

import com.springboot.demo.service.DepartmentService;
//github.com/frieda0503/employeeHw.git
import com.springboot.demo.model.Department;

@RestController
@RequestMapping(value = "/api")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	// get all data
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/departments/all")
	@ResponseBody
	public List<Department> getAll() {
		return departmentService.getAll();
	}

	// get all data
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/departments", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Page<Department> getAllBy(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size) {
		return departmentService.getAll(page, size);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/condition")
	public Page<Department> getByCondition(
			@RequestParam(required = false) String employeeName,
			@RequestParam(required = false) Integer employeeId,
			@RequestParam(required = false) Integer age,
			@RequestParam(required = false) String departmentName,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "5") Integer size) {

		Page<Department> result = departmentService.getDepartmentData(
				employeeName, employeeId, age, departmentName, page, size);
		return result;
	}

	// add data
	@PostMapping("/department")
	public void addDepartment(@RequestBody Department department) {
		departmentService.addDepartment(department);
	}

	// update data
	@PutMapping("/department/{id}")
	public Department updateDepartment(
			@PathVariable(required = true) Integer id,
			@RequestBody Department department) {
		return departmentService.updateDepartment(id, department);
	}

	// delete data
	@DeleteMapping("/departments/{id}")
	public void deleteDepartment(@PathVariable Integer id) {
		departmentService.deleteDepartment(id);
	}

}
