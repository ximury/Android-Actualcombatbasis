package com.back.base.service;

import java.util.List;
import java.util.Map;

import com.back.base.model.Ttask;
import com.back.base.pageModel.Company;



public interface TaskService {

	
	public List<Ttask> queryAll(Ttask task);

	public int taskSaveOrUpdate(Ttask task);
	
	public Ttask find(String id);
	
	public int updateTaskClient(String pkId);
	    
	public int deleteTaskAdmin(String pkId);
	
}
