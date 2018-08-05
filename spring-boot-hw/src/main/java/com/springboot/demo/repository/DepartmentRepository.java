package com.springboot.demo.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.springboot.demo.model.Department;
import com.springboot.demo.model.Employee;

@RepositoryRestResource
public interface DepartmentRepository extends
		JpaRepository<Department, Integer> ,JpaSpecificationExecutor<Department>{

	// GET /books/:id
	@Override
	Optional<Department> findById(Integer id);

	// GET /books
//	@Override
//	Page<Department> findAll(Pageable pageable);

	// POST /books and PATCH /books/:id
	@SuppressWarnings("unchecked")
	@Override
	Department save(Department s);

	// DELETE /books/:id
	@Override
	void deleteById(Integer id);
}
