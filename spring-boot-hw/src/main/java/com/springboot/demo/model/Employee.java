package com.springboot.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "EMPLOYEE")
public class Employee implements Serializable {
	private static final long serialVersionUID = -4376187124011546736L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", updatable = false, nullable = false)
	private int emp_id;

	@Column(name = "NAME")
	private String name;

	@ManyToOne
	@JoinColumn(name = "DEP_ID", nullable = false)
	private Department department;

	@Column(name = "GENDER")
	private String gender;

	@Column(name = "PHONE")
	private String phone;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "AGE")
	private int age;

	@CreatedDate
	@Column(name = "CREATE_TIME", nullable = false, updatable = false)
	private Date createTime;

	@LastModifiedDate
	@Column(name = "LAST_MODIFY")
	private Date lastModify;

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLastModify() {
		return lastModify;
	}

	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}

	@Override
	public String toString() {
		return "Employee{" + "id=" + emp_id + ", name='" + name + '\''
				+ ", department=" + department + ", gender='" + gender + '\''
				+ ", phone='" + phone + '\'' + ", address='" + address + '\''
				+ ", age=" + age + ", createTime=" + createTime
				+ ", lastModify=" + lastModify + '}';
	}

}