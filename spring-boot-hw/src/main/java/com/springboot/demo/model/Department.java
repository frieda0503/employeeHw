package com.springboot.demo.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.common.collect.Sets;

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
	//
	// public void addValue(Employee value) {
	// values.add(value);
	// }
	//
	// public void removeValue(Employee value) {
	// values.remove(value);
	// }

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + dep_name + "]";
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

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

	

}
