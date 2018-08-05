package com.springboot.demo.service;

import com.springboot.demo.model.Employee;
import com.springboot.demo.repository.DepartmentRepository;
import com.springboot.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

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

	public void addEmployee(@RequestBody Employee employee) {
		if (!departmentRepository.existsById(employee.getId())) {
			throw new ResourceNotFoundException("Department is not found");
		}
		employeeRepository.save(employee);
	}

	public void updateEmployee(@RequestBody Employee employee) {
		employeeRepository.save(employee);
	}

	public void deleteEmployee(@PathVariable Integer id) {
		employeeRepository.deleteById(id);
	}

	private Pageable buildPageRequest(Integer page, Integer size) {
		return new PageRequest(page, size, Direction.DESC, "id");
	}

}
