package com.springboot.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.springboot.demo.model.Employee;

@RepositoryRestResource
public interface EmployeeRepository extends JpaRepository<Employee, Integer>,
		JpaSpecificationExecutor<Employee> {

	// insert / update
	@SuppressWarnings("unchecked")
	@Override
	Employee save(Employee e);
}
