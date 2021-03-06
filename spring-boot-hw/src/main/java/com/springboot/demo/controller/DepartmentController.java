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
import com.springboot.demo.vo.DepartmentVo;

@RestController
@ResponseBody
@RequestMapping(value = "/api")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	// get all data
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/departments/all")
	@ResponseBody
	public List<DepartmentVo> getAll() {
		return departmentService.getAll();
	}

	// get all data by page
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/departments", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Page<Department> getAllBy(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size) {
		return departmentService.getAll(page, size);
	}

	// add data
	@PostMapping("/department")
	public DepartmentVo addDepartment(@RequestBody DepartmentVo departmentVo) {
		return departmentService.addDepartment(departmentVo);
	}

	// update data by id
	@PutMapping("/department/{id}")
	public DepartmentVo updateDepartment(
			@PathVariable(required = true) Integer id,
			@RequestBody DepartmentVo departmentVo) {
		departmentVo.setId(id);
		return departmentService.updateDepartment(departmentVo);
	}

	// delete data by id
	@DeleteMapping("/departments/{id}")
	public void deleteDepartment(@PathVariable Integer id) {
		departmentService.deleteDepartment(id);
	}

}
