package com.back.base.service;

import java.util.List;

import com.back.base.pageModel.Department;


public interface DepartmentService {
	
	public Department save(Department department);

	public List<Department> list(Department department);

	public int delete(String id);

	public Department find(String id);

	public Department update(Department department);
	
	public Department saveOrUpdate(Department department);
	

}
