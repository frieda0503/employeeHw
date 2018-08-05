package com.springboot.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.springboot.demo.model.Department;
import com.springboot.demo.repository.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository depRepository;

	public List<Department> getAll() {
		return depRepository.findAll();
	}

	// add data
	@PostMapping("/add/department")
	public void addDepartment(@RequestBody Department department) {
		depRepository.save(department);
	}

	// update data
	@PutMapping("/update/department")
	public void updateDepartment(@RequestBody Department department) {
		depRepository.save(department);
	}

	// delete data
	@DeleteMapping("/departments/{id}")
	public void deleteDepartment(@PathVariable Integer id) {
		depRepository.deleteById(id);
	}
	
	// find data by multiple condition
//		@ResponseStatus(HttpStatus.OK)
//		@GetMapping(value = "/find/employees", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//		public List<Employee> findByCondition() {
//			return empRepository.findAll();
//		}

}
