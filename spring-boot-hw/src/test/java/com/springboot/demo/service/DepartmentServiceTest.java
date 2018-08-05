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
		Department department = spy(tempdepartment);

		when(departmentService.addDepartment(new Department())).thenReturn(department);
	}

	// test update department method
	@Test
	public void testUpdateDepartmentData() throws Exception {
		Department oldDepartment = new Department();
		oldDepartment.setId(1);
		oldDepartment.setDep_name("開發1科");

		Department newDepartment = new Department();
		newDepartment.setId(1);
		newDepartment.setDep_name("開發7科");
		
		when(departmentRepository.existsById(newDepartment.getId())).thenReturn(true);
		when(departmentService.updateDepartment(newDepartment.getId(), newDepartment)).thenReturn(newDepartment);
	}

	// test delete department method
	@Test(expected = ResourceNotFoundException.class)
	public void testDeleteDepartmentData() throws Exception {
		Department department = new Department();
		department.setId(1);
		
		departmentService.deleteDepartment(department.getId());
		
		assertEquals(department, null);
	}

}
