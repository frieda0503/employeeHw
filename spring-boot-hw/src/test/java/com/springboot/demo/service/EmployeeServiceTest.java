package com.springboot.demo.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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

import com.springboot.demo.model.Department;
import com.springboot.demo.model.Employee;
import com.springboot.demo.repository.DepartmentRepository;
import com.springboot.demo.repository.EmployeeRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { EmployeeService.class })
public class EmployeeServiceTest {

	@MockBean
	private EmployeeRepository employeeRepository;

	@MockBean
	private DepartmentRepository departmentRepository;

	@Autowired
	private EmployeeService employeeService;

	// test add employee method
	@Test
	public void testAddEmployeeData() throws Exception {
		Department department = new Department();
		department.setId(1);

		Employee tempEmployee = new Employee();
		tempEmployee.setDepartment(department);
		tempEmployee.setAge(40);
		tempEmployee.setGender("Female");
		tempEmployee.setId(1);

		when(employeeRepository.save(tempEmployee)).thenReturn(tempEmployee);

		Employee employee = employeeService.addEmployee(tempEmployee);
		assertEquals("Female", employee.getGender());

	}

	// test update employee method
	@Test
	public void testUpdateEmployeeData() throws Exception {
		Employee tempEmployee = new Employee();
		tempEmployee.setId(1);
		tempEmployee.setPhone("023456");

		when(employeeRepository.existsById(1)).thenReturn(true);
		when(employeeRepository.save(tempEmployee)).thenReturn(tempEmployee);

		Employee employee = employeeService.updateEmployee(
				tempEmployee.getId(), tempEmployee);
		assertEquals("023456", employee.getPhone());

	}

	// test delete employee method
	@Test(expected = ResourceNotFoundException.class)
	public void testDeleteEmployeeData() throws Exception {
		Employee employee = new Employee();
		employee.setId(1);

		employeeService.deleteEmployee(employee.getId());

		assertEquals(employee, null);
	}

	/*-------------------test get data by condition-------------------*/
	// 測試無參數輸入
	@Test
	public void testGetEmployeeDataByNoParam() throws Exception {
		List<Employee> list = Lists.newArrayList();
		Specification<Employee> specification = new Specification<Employee>() {
			@Override
			public Predicate toPredicate(Root<Employee> root,
					CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				// TODO Auto-generated method stub
				return null;
			}
		};

		Pageable page = new PageRequest(0, 10);
		Page<Employee> mockResult = new PageImpl<Employee>(list,
				new PageRequest(0, 10), list.size());

		when(employeeRepository.findAll(specification, page)).thenReturn(
				mockResult);
		assertEquals(0, mockResult.getTotalElements());

	}

	// 測試輸入全部參數
	@Test
	public void testGetEmployeeDataByAllParam() throws Exception {
		String employeeName = "Rock";
		String departmentName = "開發1科";

		Department department = new Department();
		department.setId(1);
		department.setDep_name(departmentName);

		Employee employee = new Employee();
		employee.setId(1);
		employee.setAge(25);
		employee.setName(employeeName);
		employee.setDepartment(department);

		List<Employee> list = new ArrayList<Employee>();
		list.add(employee);

		Specification<Employee> specification = new Specification<Employee>() {
			@Override
			public Predicate toPredicate(Root<Employee> root,
					CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				// TODO Auto-generated method stub
				return null;
			}
		};

		Pageable page = new PageRequest(0, 10);
		Page<Employee> result = new PageImpl<Employee>(list, new PageRequest(0,
				10), list.size());

		when(employeeRepository.findAll(specification, page)).thenReturn(result);

		employeeService.getEmployeeData(employeeName, 1, 25, departmentName, 0, 10);

		assertEquals(1, result.getNumberOfElements());
		assertEquals(1, result.getTotalElements());

		assertEquals(employeeName, result.getContent().get(0).getName());
		assertEquals(1, result.getContent().get(0).getId());
		assertEquals(25, result.getContent().get(0).getAge());
		assertEquals(departmentName, result.getContent().get(0).getDepartment().getDep_name());

	}

	// 測試輸入部分參數
	@Test
	public void testGetEmployeeDataByParam() throws Exception {
		String employeeName = "Rock";
		String departmentName = "開發1科";

		Department department = new Department();
		department.setDep_name(departmentName);

		Employee employee = new Employee();
		employee.setName(employeeName);
		employee.setDepartment(department);

		List<Employee> list = new ArrayList<Employee>();
		list.add(employee);

		Specification<Employee> specification = new Specification<Employee>() {
			@Override
			public Predicate toPredicate(Root<Employee> root,
					CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				// TODO Auto-generated method stub
				return null;
			}
		};

		Pageable page = new PageRequest(0, 10);
		Page<Employee> result = new PageImpl<Employee>(list, new PageRequest(0,
				10), list.size());

		when(employeeRepository.findAll(specification, page)).thenReturn(result);

		employeeService.getEmployeeData(employeeName, null, null,departmentName, 0, 10);

		assertEquals(1, result.getNumberOfElements());
		assertEquals(1, result.getTotalElements());

		assertEquals(employeeName, result.getContent().get(0).getName());
		assertEquals(departmentName, result.getContent().get(0).getDepartment().getDep_name());
	}

}
