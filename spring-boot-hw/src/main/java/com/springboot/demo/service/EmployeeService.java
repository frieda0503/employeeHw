package com.springboot.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.springboot.demo.model.Department;
import com.springboot.demo.model.Employee;
import com.springboot.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	// for get all data
	public List<Employee> getAll() {
		return employeeRepository.findAll();
	}

	// for get all data by page
	public Page<Employee> getAll(Integer page, Integer size) {
		return employeeRepository.findAll(buildPageRequest(page, size));
	}

	// for add data
	public Employee addEmployee(Employee employee) {
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

	// for query data by condition
	public Page<Employee> getEmployeeData(String employeeName,
			Integer employeeId, Integer age, String departmentName,
			Integer page, Integer size) {
		Specification<Employee> specification = new Specification<Employee>() {
			@Override
			public Predicate toPredicate(Root<Employee> root,
					CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				List<Predicate> list = new ArrayList<Predicate>();
				Join<Employee, Department> joins = root.join("department");

				if (!StringUtils.isEmpty(departmentName)) {
					Predicate namePredicate = criteriaBuilder.equal(
							joins.get("dep_name"), departmentName);
					list.add(namePredicate);
				}
				if (!StringUtils.isEmpty(employeeName)) {
					Predicate namePredicate = criteriaBuilder.equal(
							 root.<String> get("name"), employeeName);
					list.add(namePredicate);
				}
				if (!StringUtils.isEmpty(employeeId)) {
					Predicate idPredicate = criteriaBuilder.equal(
							root.<Integer> get("id"), employeeId);
					list.add(idPredicate);
				}
				if (!StringUtils.isEmpty(age)) {
					Predicate agePredicate = criteriaBuilder.equal(
							root.<Integer> get("age"), age);
					list.add(agePredicate);
				}

				// 姓名、員工編號、年齡、部門名稱(欄位皆為選填)
				Predicate[] predicates = new Predicate[list.size()];
				return criteriaBuilder.and(list.toArray(predicates));
			}
		};
		return employeeRepository.findAll(specification,
				buildPageRequest(page, size));
	}

	// for paging
	private Pageable buildPageRequest(Integer page, Integer size) {
		return new PageRequest(page, size, Direction.ASC, "id");
	}

}
