package com.springboot.demo.service;

import static org.junit.Assert.assertEquals;
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
import com.springboot.demo.vo.DepartmentVo;

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
		
		DepartmentVo departmentVo = new DepartmentVo();
		departmentVo.setId(1);
		departmentVo.setDep_name("開發1科");

		when(departmentRepository.save(tempdepartment)).thenReturn(tempdepartment);

		departmentService.addDepartment(departmentVo);
		assertEquals("開發1科", departmentVo.getDep_name());
	}

	// test update department method
	@Test
	public void testUpdateDepartmentData() throws Exception {
		DepartmentVo departmentVo = new DepartmentVo();
		Department department = new Department();
		departmentVo.setId(1);
		departmentVo.setDep_name("開發7科");

		when(departmentRepository.exists(departmentVo.getId())).thenReturn(true);
		when(departmentRepository.save(department)).thenReturn(department);

		departmentService.updateDepartment(departmentVo);

		assertEquals("開發7科", departmentVo.getDep_name());
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
