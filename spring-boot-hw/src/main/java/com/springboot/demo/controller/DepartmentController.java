package com.springboot.demo.controller;

import com.springboot.demo.model.Department;
import com.springboot.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
	public Page<Department> getAllBy(@RequestParam(value = "page", defaultValue = "0") Integer page,
									 @RequestParam(value = "size", defaultValue = "5") Integer size) {
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

		Page<Department> result = departmentService.getDepartmentData(employeeName, employeeId, age, departmentName, page, size);
		return result;
	}

	// add data
	@PostMapping("/department")
	public void addDepartment(@RequestBody Department department) {
		departmentService.addDepartment(department);
	}

	// update data
	@PutMapping("/department")
	public void updateDepartment(@RequestBody Department department) {
		departmentService.updateDepartment(department);
	}

	// delete data
	@DeleteMapping("/departments/{id}")
	public void deleteDepartment(@PathVariable Integer id) {
		departmentService.deleteDepartment(id);
	}

}
