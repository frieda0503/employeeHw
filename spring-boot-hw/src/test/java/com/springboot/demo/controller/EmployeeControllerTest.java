package com.springboot.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import com.springboot.demo.service.EmployeeService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeController.class, secure = false)
@ContextConfiguration(classes = { EmployeeController.class,
		EmployeeService.class })
public class EmployeeControllerTest {
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private final String EMPLOYEE_REQUEST = "{\"id\" : 1,\"name\" : \"Tom\",\"gender\" : \"Male\"}";
	private final String UPDATE_EMPLOYEE_REQUEST = "{\"name\" : \"Sunny\"}";

	@Autowired
	private WebApplicationContext webApplicationContext;
	MockMvc mockMvc;

	@MockBean
	private EmployeeService employeeService;
	
	@MockBean
	private DepartmentService departmentService;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext).build();
	}

	// test add employee api
	@Test
	public void testAddEmployeeData() throws Exception {
		mockMvc.perform(
				post("/api/employee").content(EMPLOYEE_REQUEST)
						.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(print()).andExpect(status().isOk());

	}

	// test update employee api
	@Test
	public void testUpdateEmployeeData() throws Exception {
		mockMvc.perform(
				put("/api/employee/1").content(UPDATE_EMPLOYEE_REQUEST)
						.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(print()).andExpect(status().isOk());
	}

	// test delete employee api
	@Test
	public void testDeleteEmployeeData() throws Exception {
		mockMvc.perform(
				delete("/api/employees/1").contentType(
						MediaType.APPLICATION_JSON_UTF8)).andExpect(
				status().isOk());
	}
	
	/*-------------------test get data by condition-------------------*/
	@Test
	public void testGetEmployeeDataByNoParam() throws Exception {
		mockMvc.perform(
				get("/api/employee/condition").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testGetEmployeeDataByAllParam() throws Exception {
		String employeeName = "Rock";
		String employeeId = "1";
		String age = "25";
		String departmentName = "開發1科";

		mockMvc.perform(
				get("/api/employee/condition")  
				        .param("employeeName", employeeName)
						.param("employeeId", employeeId)
						.param("age", age)
						.param("departmentName", departmentName)
						.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void testGetEmployeeDataByParam() throws Exception {
		String employeeName = "Rock";
		String departmentName = "開發1科";

		mockMvc.perform(
				get("/api/employee/condition")  
				        .param("employeeName", employeeName)
						.param("departmentName", departmentName)
						.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(print()).andExpect(status().isOk());
	}


}
