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

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository depRepository;

	public List<Department> getAll() {
		return depRepository.findAll();
	}

	public Department addDepartment(Department department) {
		return depRepository.save(department);
	}

	public Department updateDepartment(Integer id, Department department) {
		if (!depRepository.existsById(id)) {
			throw new ResourceNotFoundException("Department is not found");
		}
		department.setId(id);
		return depRepository.save(department);
	}

	public void deleteDepartment(Integer id) {
		if (!depRepository.existsById(id)) {
			throw new ResourceNotFoundException("Department is not found");
		}
		depRepository.deleteById(id);
	}

	public Page<Department> getEmployeeData(String employeeName,
			Integer employeeId, Integer age, String departmentName) {

		Specification<Department> specification = new Specification<Department>() {
			@Override
			public Predicate toPredicate(Root<Department> root,
					CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<Predicate>();

				if (!StringUtils.isEmpty(departmentName)) {
					list.add(criteriaBuilder.equal(root.get("dep_name"),
							departmentName));
				}

				if (employeeId != null) {
					Set<Employee> employees = (Set<Employee>) root
							.get("employees");

					System.out.println(employees);

					list.add(criteriaBuilder.equal(
							root.get("employees").get("id"), employeeId));
				}

				// 姓名、員工編號、年齡、部門名稱(欄位皆為選填)
				Predicate[] predicates = new Predicate[list.size()];
				return criteriaBuilder.and(list.toArray(predicates));

				// return null;
			}

		};
		return depRepository.findAll(specification, buildPageRequest());
	};

	private Pageable buildPageRequest() {
		return new PageRequest(0, 10, new Sort(Direction.ASC, "id"));
	}

}
