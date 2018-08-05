package com.springboot.demo.model;

import com.google.common.collect.Sets;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "DEPARTMENT")
public class Department implements Serializable {
	private static final long serialVersionUID = -4376187124011546736L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", updatable = false, nullable = false)
	private int id;

	@Column(name = "DEP_NAME")
	private String dep_name;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "DEP_ID", updatable = false)
	private Set<Employee> employees = Sets.newLinkedHashSet();

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

	@Override
	public String toString() {
		return "Department{" +
				"id=" + id +
				", dep_name='" + dep_name + '\'' +
				", employees=" + employees +
				'}';
	}
}