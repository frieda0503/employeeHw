package com.springboot.demo.model;

import java.util.Set;


public class DepartmentEmp {
	private int id;

	private String dep_name;

	private Set<Employee> employees;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDep_name() {
		return dep_name;
	}

	public void setDep_name(String dep_name) {
		this.dep_name = dep_name;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

}
