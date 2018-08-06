package com.springboot.demo.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.common.collect.Sets;
import com.springboot.demo.model.Department;
import com.springboot.demo.model.Employee;
import com.springboot.demo.repository.DepartmentRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { DepartmentService.class })
public class DepartmentServiceTest {

	@MockBean
	private DepartmentRepository departmentRepository;

	@Autowired
	private DepartmentService departmentService;

	// test add department method
	@Test
	public void testAddDepartmentData() throws Exception {
		Department tempdepartment = new Department();
		tempdepartment.setId(1);
		tempdepartment.setDep_name("開發1科");

		when(departmentRepository.save(tempdepartment)).thenReturn(
				tempdepartment);

		Department department = departmentService.addDepartment(tempdepartment);
		assertEquals("開發1科", department.getDep_name());
	}

	// test update department method
	@Test
	public void testUpdateDepartmentData() throws Exception {
		Department department = new Department();
		department.setId(1);
		department.setDep_name("開發7科");

		when(departmentRepository.existsById(department.getId())).thenReturn(true);
		when(departmentRepository.save(department)).thenReturn(department);

		departmentService.updateDepartment(department.getId(), department);

		assertEquals("開發7科", department.getDep_name());
	}

	// test delete department method
	@Test(expected = ResourceNotFoundException.class)
	public void testDeleteDepartmentData() throws Exception {
		Department department = new Department();
		department.setId(1);

		departmentService.deleteDepartment(department.getId());

		assertEquals(department, null);
	}

	/*-------------------test get data by condition-------------------*/
	// 測試無參數輸入
	@Test
	public void testGetDepartmentDataByNoParam() throws Exception {
		List<Department> list = Lists.newArrayList();
		Specification<Department> specification = new Specification<Department>() {
			@Override
			public Predicate toPredicate(Root<Department> root,
					CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				// TODO Auto-generated method stub
				return null;
			}
		};

		Pageable page = new PageRequest(0, 10);
		Page<Department> mockResult = new PageImpl<Department>(list,
				new PageRequest(0, 10), list.size());

		when(departmentRepository.findAll(specification, page)).thenReturn(mockResult);
		assertEquals(0, mockResult.getTotalElements());

	}

	// 測試輸入全部參數
	@Test
	public void testGetDepartmentDataByAllParam() throws Exception {
		String employeeName = "Rock";
		String departmentName = "開發1科";

		Department department = new Department();
		department.setDep_name(departmentName);
		Set<Employee> employees = Sets.newHashSet();
		Employee employee = new Employee();
		employee.setId(1);
		employee.setAge(25);
		employee.setName(employeeName);

		employees.add(employee);
		department.setEmployees(employees);

		List<Department> list = new ArrayList<Department>();
		list.add(department);

		Specification<Department> specification = new Specification<Department>() {
			@Override
			public Predicate toPredicate(Root<Department> root,
					CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				// TODO Auto-generated method stub
				return null;
			}
		};

		Pageable page = new PageRequest(0, 10);
		Page<Department> result = new PageImpl<Department>(list, new PageRequest(0, 10), list.size());

		when(departmentRepository.findAll(specification, page)).thenReturn(result);

		departmentService.getDepartmentData(employeeName, 1, 25, departmentName, 0, 10);

		assertEquals(1, result.getNumberOfElements());
		assertEquals(1, result.getTotalElements());

		result.getContent().get(0).getEmployees().forEach(e -> {
			assertEquals(employeeName, e.getName());
			assertEquals(1, e.getId());
			assertEquals(25, e.getAge());
		});

		assertEquals(departmentName, result.getContent().get(0).getDep_name());

	}

	// 測試輸入部分參數
	@Test
	public void testGetDepartmentDataByParam() throws Exception {
		String employeeName = "Rock";
		String departmentName = "開發1科";

		Department department = new Department();
		department.setDep_name(departmentName);
		Set<Employee> employees = Sets.newHashSet();
		Employee employee = new Employee();
		employee.setName(employeeName);

		employees.add(employee);
		department.setEmployees(employees);

		List<Department> list = new ArrayList<Department>();
		list.add(department);

		Specification<Department> specification = new Specification<Department>() {
			@Override
			public Predicate toPredicate(Root<Department> root,
					CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				// TODO Auto-generated method stub
				return null;
			}
		};

		Pageable page = new PageRequest(0, 10);
		Page<Department> result = new PageImpl<Department>(list, new PageRequest(0, 10), list.size());

		when(departmentRepository.findAll(specification, page)).thenReturn(result);

		departmentService.getDepartmentData(employeeName, null, null, departmentName, 0, 10);

		assertEquals(1, result.getNumberOfElements());
		assertEquals(1, result.getTotalElements());

		result.getContent().get(0).getEmployees().forEach(e -> {
			assertEquals(employeeName, e.getName());
		});

		assertEquals(departmentName, result.getContent().get(0).getDep_name());
	}

}
