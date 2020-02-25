package com.back.base.dao;

import java.util.List;

import com.back.base.model.TDepartment;
import com.back.base.pageModel.Department;


public interface TDepartmentMapper extends BaseMapper<TDepartment>{
	
	List<Department> query(Department department);

	Department find(String id);
	
}