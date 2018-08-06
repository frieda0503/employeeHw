package com.springboot.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.demo.model.Employee;
import com.springboot.demo.repository.DepartmentRepository;
import com.springboot.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	// for get all data
	public List<Employee> getAll() {
		return employeeRepository.findAll();
	}

	// for get all data by page
	public Page<Employee> getAll(Integer page, Integer size) {
		return employeeRepository.findAll(buildPageRequest(page, size));
	}

	/*
	 * for add data ： 先判斷insert該員工時，所屬部門是否存在
	 */
	public Employee addEmployee(Employee employee) {
		if (!departmentRepository.existsById(employee.getDep_id())) {
			throw new ResourceNotFoundException("Department is not found");
		}

		return employeeRepository.save(employee);
	}

	// for update data
	public Employee updateEmployee(Integer id, Employee employee) {
		if (!employeeRepository.existsById(id)) {
			throw new ResourceNotFoundException("Employee is not found");
		}
		employee.setId(id);
		return employeeRepository.save(employee);
	}

	// for delete data
	public void deleteEmployee(Integer id) {
		if (!employeeRepository.existsById(id)) {
			throw new ResourceNotFoundException("Employee is not found");
		}
		employeeRepository.deleteById(id);

	}

	// for paging
	private Pageable buildPageRequest(Integer page, Integer size) {
		return new PageRequest(page, size, Direction.ASC, "id");
	}

}
