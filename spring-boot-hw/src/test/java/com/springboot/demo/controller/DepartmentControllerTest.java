package com.springboot.demo.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.springboot.demo.model.Department;
import com.springboot.demo.service.DepartmentService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = DepartmentController.class, secure = false)
@ContextConfiguration(classes = { DepartmentController.class,
		DepartmentService.class })
public class DepartmentControllerTest {
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private final String DEPARTMENT_REQUEST = "{\"id\" : 1,\"dep_name\" : \"開發6科\"}";

	@Autowired
	private WebApplicationContext webApplicationContext;
	MockMvc mockMvc;

	@MockBean
	private DepartmentService departmentService;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext).build();
	}

	// test add department api
	@Test
	public void testAddDepartmentData() throws Exception {
		Department department = new Department();
		department.setId(1);
		department.setDep_name("開發6科");

		when(departmentService.addDepartment((new Department()))).thenReturn(
				department);

		mockMvc.perform(
				post("/api/add/department").content(DEPARTMENT_REQUEST)
						.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(print()).andExpect(status().isOk())
				// .andExpect(jsonPath("$.id").value("1"))
				.andReturn();

	}

	// test update not exist department
	@Test
	public void testUpdateDepartmentData() throws Exception {
		Department department = new Department();
		department.setId(1);
		department.setDep_name("開發6科");

		when(departmentService.updateDepartment(1, new Department()))
				.thenReturn(department);

		mockMvc.perform(
				put("/update/department/2").content(DEPARTMENT_REQUEST)
						.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(print()).andExpect(status().isNotFound());
	}
	
	// test update not exist department
		@Test
		public void testDeleteDepartmentData() throws Exception {
			
			when(departmentService.deleteDepartment(1))
					.thenReturn(department);

			mockMvc.perform(
					delete("/delete/department/1").content(DEPARTMENT_REQUEST)
							.contentType(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk());
		}

}
