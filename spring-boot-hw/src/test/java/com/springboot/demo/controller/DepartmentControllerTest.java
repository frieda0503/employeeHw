package com.springboot.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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
	private final String UPDATE_DEPARTMENT_REQUEST = "{\"dep_name\" : \"開發8科\"}";

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
		mockMvc.perform(
				post("/api/department").content(DEPARTMENT_REQUEST)
						.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(print()).andExpect(status().isOk());

	}

	// test update department api
	@Test
	public void testUpdateDepartmentData() throws Exception {
		mockMvc.perform(
				put("/api/department/1").content(UPDATE_DEPARTMENT_REQUEST)
						.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(print()).andExpect(status().isOk());
	}

	// test delete department api
	@Test
	public void testDeleteDepartmentData() throws Exception {
		mockMvc.perform(
				delete("/api/departments/1").contentType(
						MediaType.APPLICATION_JSON_UTF8)).andExpect(
				status().isOk());
	}

}
