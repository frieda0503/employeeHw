package com.springboot.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.springboot.demo.model.Department;
import com.springboot.demo.model.Employee;
import com.springboot.demo.repository.DepartmentRepository;
import com.springboot.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository empRepository;

	@Autowired
	private DepartmentRepository depRepository;
//	
//	@Autowired
//	private DepartmentEmpRepository departmentEmpRepository;

	public List<Employee> getAll() {
		return empRepository.findAll();
	}

	public void addEmployee(Employee employee) {
		if (!depRepository.existsById(employee.getDep_id())) {
			throw new ResourceNotFoundException("Department is not found");
		}
		empRepository.save(employee);
	}

	public void updateEmployee(Employee employee) {
		empRepository.save(employee);
	}

	public void deleteEmployee(Integer id) {
		empRepository.deleteById(id);
	}
	
}
