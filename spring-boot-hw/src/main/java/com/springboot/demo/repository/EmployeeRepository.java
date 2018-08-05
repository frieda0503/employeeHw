package com.springboot.demo.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.springboot.demo.model.Employee;

@RepositoryRestResource
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
	
	// GET /employees/:id
    @Override
    Optional<Employee> findById(Integer id);

    // GET /employees
    @Override
    Page<Employee> findAll(Pageable pageable);

    // POST /employees
    @SuppressWarnings("unchecked")
	@Override
	Employee save(Employee e);

    // DELETE /employees/:id
    @Override
    void deleteById(Integer id);

}
