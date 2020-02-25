package com.back.base.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.base.dao.TDepartmentMapper;
import com.back.base.dao.TPartyMapper;
import com.back.base.model.TDepartment;
import com.back.base.model.TParty;
import com.back.base.pageModel.Department;
import com.back.base.service.DepartmentService;


@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

	private static final Logger logger = Logger.getLogger(DepartmentServiceImpl.class);

	private TDepartmentMapper departmentMapper;
	
	private TPartyMapper partyMapper;
	
	

	public TDepartmentMapper getDepartmentMapper() {
		return departmentMapper;
	}

	@Autowired
	public void setDepartmentMapper(TDepartmentMapper departmentMapper) {
		this.departmentMapper = departmentMapper;
	}
	
	public TPartyMapper getPartyMapper() {
		return partyMapper;
	}

	@Autowired
	public void setPartyMapper(TPartyMapper partyMapper) {
		this.partyMapper = partyMapper;
	}
	

	
	public Department save(Department department) {
		TParty tparty = new TParty();
		TDepartment tdepartment = new TDepartment();
		BeanUtils.copyProperties(department, tparty);
		BeanUtils.copyProperties(department, tdepartment);
		tparty.setDiscriminate("department");
		tparty.setOrdernum(Integer.parseInt(department.getGrade()));
		partyMapper.insertSelective(tparty);
		departmentMapper.insertSelective(tdepartment);
		logger.info(TDepartment.class.getName() + "新增成功！");
		return department;
	}

	
	public List<Department> list(Department department) {
		return departmentMapper.query(department);
	}

	
	public int delete(String id) {
		int  count = departmentMapper.deleteByPrimaryKey(id);
		count += partyMapper.deleteByPrimaryKey(id);
		logger.info(TDepartment.class.getName() + "删除成功！");
		return count;
	}

	
	public Department find(String id) {
		return departmentMapper.find(id);
	}

	
	public Department update(Department department) {
		TParty tparty = new TParty();
		TDepartment tdepartment = new TDepartment();
		BeanUtils.copyProperties(department, tparty);
		BeanUtils.copyProperties(department, tdepartment);
		departmentMapper.updateByPrimaryKeySelective(tdepartment);
		tparty.setOrdernum(Integer.parseInt(department.getGrade()));
		partyMapper.updateByPrimaryKeySelective(tparty);
		logger.info(TDepartment.class.getName() + "更新成功！");
		return department;		
	}
	
	public Department saveOrUpdate(Department department) {
		if(department.isUpdateFlag()){
			update(department);
		}else{
			save(department);
		}
		return department;
	}	

}
