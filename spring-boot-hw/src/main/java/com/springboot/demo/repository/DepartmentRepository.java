package com.springboot.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.springboot.demo.model.Department;

@RepositoryRestResource
public interface DepartmentRepository extends JpaRepository<Department, Integer>,
		JpaSpecificationExecutor<Department> {

	// insert / update
	@SuppressWarnings("unchecked")
	@Override
	Department save(Department s);
}
