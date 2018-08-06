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
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.springboot.demo.model.Department;
import com.springboot.demo.model.Employee;
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

	// for query data by condition
	public Page<Department> getDepartmentData(String employeeName,
			Integer employeeId, Integer age, String departmentName,
			Integer page, Integer size) {
		Specification<Department> specification = new Specification<Department>() {
			@Override
			public Predicate toPredicate(Root<Department> root,
					CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				List<Predicate> list = new ArrayList<Predicate>();
				Join<Department, Employee> joins = root.join("employees");

				if (!StringUtils.isEmpty(departmentName)) {
					Predicate namePredicate = criteriaBuilder.equal(
							root.get("dep_name"), departmentName);
					list.add(namePredicate);
				}
				if (!StringUtils.isEmpty(employeeName)) {
					Predicate namePredicate = criteriaBuilder.equal(
							joins.<String> get("name"), employeeName);
					list.add(namePredicate);
				}
				if (!StringUtils.isEmpty(employeeId)) {
					Predicate idPredicate = criteriaBuilder.equal(
							joins.<Integer> get("id"), employeeId);
					list.add(idPredicate);
				}
				if (!StringUtils.isEmpty(age)) {
					Predicate agePredicate = criteriaBuilder.equal(
							joins.<Integer> get("age"), age);
					list.add(agePredicate);
				}

				// 姓名、員工編號、年齡、部門名稱(欄位皆為選填)
				Predicate[] predicates = new Predicate[list.size()];
				return criteriaBuilder.and(list.toArray(predicates));
			}
		};
		return departmentRepository.findAll(specification,
				buildPageRequest(page, size));
	}

	// for paging
	private Pageable buildPageRequest(Integer page, Integer size) {
		return new PageRequest(page, size, Sort.Direction.ASC, "id");
	}
}
