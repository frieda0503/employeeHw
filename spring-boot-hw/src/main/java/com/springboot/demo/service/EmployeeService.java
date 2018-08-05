package com.springboot.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.springboot.demo.model.Employee;
import com.springboot.demo.repository.DepartmentRepository;
import com.springboot.demo.repository.EmployeeRepository;
//github.com/frieda0503/employeeHw.git
import org.springframework.beans.factory.annotation.Autowired;
//github.com/frieda0503/employeeHw.git
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
//github.com/frieda0503/employeeHw.git


@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	public List<Employee> getAll() {
		return employeeRepository.findAll();
	}

	public Page<Employee> getAll(Integer page, Integer size) {
		return employeeRepository.findAll(buildPageRequest(page, size));
	}


	public Employee addEmployee(@RequestBody Employee employee) {
		if (!departmentRepository.existsById(employee.getDep_id())) {
			throw new ResourceNotFoundException("Department is not found");
		}

		return employeeRepository.save(employee);
	}


	public Employee updateEmployee(Integer id, Employee employee) {
		if (!employeeRepository.existsById(id)) {
			throw new ResourceNotFoundException("Employee is not found");
		}
		employee.setId(id);
		return employeeRepository.save(employee);
	}


	public void deleteEmployee(Integer id) {
		if (!employeeRepository.existsById(id)) {
			throw new ResourceNotFoundException("Employee is not found");
		}
		employeeRepository.deleteById(id);

	}

	private Pageable buildPageRequest(Integer page, Integer size) {
		return new PageRequest(page, size, Direction.DESC, "id");
	}


}
