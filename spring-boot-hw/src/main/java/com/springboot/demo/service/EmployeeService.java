package com.springboot.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.demo.model.Employee;
import com.springboot.demo.repository.DepartmentRepository;
import com.springboot.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository empRepository;

	@Autowired
	private DepartmentRepository depRepository;

	public List<Employee> getAll() {
		return empRepository.findAll();
	}

	public Employee addEmployee(Employee employee) {
		if (!depRepository.existsById(employee.getDep_id())) {
			throw new ResourceNotFoundException("Department is not found");
		}
		return empRepository.save(employee);
	}

	public Employee updateEmployee(Integer id, Employee employee) {
		if (!empRepository.existsById(id)) {
			throw new ResourceNotFoundException("Employee is not found");
		}
		employee.setId(id);
		return empRepository.save(employee);
	}

	public void deleteEmployee(Integer id) {
		if (!empRepository.existsById(id)) {
			throw new ResourceNotFoundException("Employee is not found");
		}
		empRepository.deleteById(id);
	}

}
