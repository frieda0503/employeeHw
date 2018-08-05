package com.springboot.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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

	public void addEmployee(@RequestBody Employee employee) {
		if (!depRepository.existsById(employee.getDep_id())) {
			throw new ResourceNotFoundException("Department is not found");
		}
		empRepository.save(employee);
	}

	public void updateEmployee(@RequestBody Employee employee) {
		empRepository.save(employee);
	}

	public void deleteEmployee(@PathVariable Integer id) {
		empRepository.deleteById(id);
	}
	
	public Page<Employee> getEmployeeData(String employeeName, int employeeId,int age,String departmentName){
		Specification<Employee> specification = new Specification<Employee>(){
			@Override
			public Predicate toPredicate(Root<Employee> root,
					CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<Predicate>();
				
				Join<Employee, Department> joinTable = root.join("DEP_ID", JoinType.INNER);
				
				if (!StringUtils.isEmpty(employeeName)){
                    Predicate namePredicate = criteriaBuilder.equal(root.get("employeeName"), employeeName);
                    list.add(namePredicate);
                }
	

				
				//姓名、員工編號、年齡、部門名稱(欄位皆為選填)
				Predicate[] predicates = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(predicates));
				
//				return null;
			}
			
		};
		return empRepository.findAll(specification, buildPageRequest());
	};
	
	private Pageable buildPageRequest(){
		return new PageRequest(1,10,new Sort(Direction.DESC,"createTime"));
	}
	

}
