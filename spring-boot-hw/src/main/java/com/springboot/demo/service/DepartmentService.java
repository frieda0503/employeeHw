package com.springboot.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.springboot.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.springboot.demo.model.Department;
import com.springboot.demo.repository.DepartmentRepository;

import javax.persistence.criteria.*;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	public List<Department> getAll() {return departmentRepository.findAll();}

	public Page<Department> getAll(Integer page, Integer size) {
		return departmentRepository.findAll(buildPageRequest(page, size));
	}

	// add data
	public void addDepartment(Department department) {
		departmentRepository.save(department);
	}

	// update data
	public void updateDepartment(Department department) {
		departmentRepository.save(department);
	}

	// delete data
	public void deleteDepartment(Integer id) {
		departmentRepository.deleteById(id);
	}

	public Page<Department> getDepartmentData(String employeeName, Integer employeeId, Integer age, String departmentName, Integer page, Integer size) {
		System.out.println("!!!"+employeeName);
		Specification<Department> specification = new Specification<Department>() {
			@Override
			public Predicate toPredicate(Root<Department> root,
										 CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				List<Predicate> list = new ArrayList<Predicate>();
				Join<Department, Employee> joins = root.join("employees");

				if (!StringUtils.isEmpty(departmentName)) {
					Predicate namePredicate = criteriaBuilder.equal(root.get("dep_name"), departmentName);
					list.add(namePredicate);
				}
				if (!StringUtils.isEmpty(employeeName)){
					Predicate namePredicate = criteriaBuilder.equal(joins.<String>get("name"), employeeName);
					list.add(namePredicate);
				}
				if (!StringUtils.isEmpty(employeeId)){
					Predicate idPredicate = criteriaBuilder.equal(joins.<Integer>get("id"), employeeId);
					list.add(idPredicate);
				}
				if (!StringUtils.isEmpty(age)){
					Predicate agePredicate = criteriaBuilder.equal(joins.<Integer>get("age"), age);
					list.add(agePredicate);
				}

				//姓名、員工編號、年齡、部門名稱(欄位皆為選填)
				Predicate[] predicates = new Predicate[list.size()];
				return criteriaBuilder.and(list.toArray(predicates));
			}
		};
		return departmentRepository.findAll(specification, buildPageRequest(page, size));
	}

	private Pageable buildPageRequest(Integer page, Integer size) {
		return new PageRequest(page, size, Sort.Direction.DESC, "id");
	}
}
