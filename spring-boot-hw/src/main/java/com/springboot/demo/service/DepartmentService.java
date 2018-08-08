package com.springboot.demo.service;


import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.springboot.demo.model.Department;
import com.springboot.demo.repository.DepartmentRepository;
import com.springboot.demo.vo.DepartmentVo;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	// for get all data
	public List<DepartmentVo> getAll() {
		List<Department> departmentList = departmentRepository.findAll();
		List<DepartmentVo> departmentVoList = Lists.newArrayList();

		departmentList.forEach(dep -> {
			DepartmentVo departmentVo = new DepartmentVo();
			BeanUtils.copyProperties(dep , departmentVo);
			departmentVoList.add(departmentVo);
		});

		return departmentVoList;

	}

	// for get all data by page
	public Page<Department> getAll(Integer page, Integer size) {
		return departmentRepository.findAll(buildPageRequest(page, size));
	}

	// for add data
	public DepartmentVo addDepartment(DepartmentVo departmentVo) {
		Department department = new Department();
		department.setDep_name(departmentVo.getDep_name());
		departmentRepository.save(department);

		BeanUtils.copyProperties(departmentVo, department);
		return departmentVo;
	}

	// for update data
	public DepartmentVo updateDepartment(DepartmentVo departmentVo) {
		Department department = new Department();

		if (!departmentRepository.exists(departmentVo.getId())) {
			throw new ResourceNotFoundException("Department is not found");
		}

		BeanUtils.copyProperties(departmentVo, department);
		departmentRepository.save(department);
		return departmentVo;

	}

	// for delete data
	public void deleteDepartment(Integer id) {
		if (!departmentRepository.exists(id)) {
			throw new ResourceNotFoundException("Department is not found");
		}
		departmentRepository.delete(id);
	}

	// for paging
	private Pageable buildPageRequest(Integer page, Integer size) {
		return new PageRequest(page, size, Sort.Direction.ASC, "id");
	}
}
