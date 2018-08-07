package com.springboot.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.demo.model.Department;
import com.springboot.demo.repository.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	// for get all data
	public List<Department> getAll() {
		return departmentRepository.findAll();
	}

	// for get all data by page
	public Page<Department> getAll(Integer page, Integer size) {
		return departmentRepository.findAll(buildPageRequest(page, size));
	}

	// for add data
	public Department addDepartment(Department department) {
		return departmentRepository.save(department);
	}

	// for update data
	public Department updateDepartment(Integer id, Department department) {
		if (!departmentRepository.existsById(id)) {
			throw new ResourceNotFoundException("Department is not found");
		}
		department.setId(id);
		return departmentRepository.save(department);

	}

	// for delete data
	public void deleteDepartment(Integer id) {
		if (!departmentRepository.existsById(id)) {
			throw new ResourceNotFoundException("Department is not found");
		}
		departmentRepository.deleteById(id);
	}

	// for paging
	private Pageable buildPageRequest(Integer page, Integer size) {
		return new PageRequest(page, size, Sort.Direction.ASC, "id");
	}
}
