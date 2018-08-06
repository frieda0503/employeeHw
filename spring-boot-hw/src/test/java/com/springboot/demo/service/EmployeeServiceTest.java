package com.springboot.demo.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
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
		Employee tempEmployee = new Employee();
		tempEmployee.setDep_id(1);
		tempEmployee.setAge(40);
		tempEmployee.setGender("Female");
		tempEmployee.setId(1);
		
		when(departmentRepository.existsById(tempEmployee.getDep_id())).thenReturn(true);
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
		
		Employee employee = employeeService.updateEmployee(tempEmployee.getId(), tempEmployee);
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

}
