package com.back.base.service.impl;


import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.base.dao.TtaskMapper;
import com.back.base.model.Ttask;
import com.back.base.service.TaskService;


@Service
public class TaskServiceImpl implements TaskService {

	private static final Logger logger = Logger.getLogger(TaskServiceImpl.class);

	@Autowired(required = true)
	private TtaskMapper taskMapper;

	public List<Ttask> queryAll(Ttask task) {
		// TODO Auto-generated method stub
		
		return taskMapper.query(task);
	}

	public int taskSaveOrUpdate(Ttask task) {
		int i;
		if(task.getPkId() == null || task.getPkId().equals("")){
			task.setPkId(UUID.randomUUID().toString());
			
			i = taskMapper.insert(task);
		}else{
			i = taskMapper.updateByPrimaryKeySelective(task);
			
		}
		
		// TODO Auto-generated method stub
		return i;
	}

	public Ttask find(String id) {
		// TODO Auto-generated method stub
		return taskMapper.selectByPrimaryKey(id);
	}

	public int updateTaskClient(String pkId) {
		// TODO Auto-generated method stub
		return taskMapper.updateTaskClient(pkId);
	}

	public int deleteTaskAdmin(String pkId) {
		// TODO Auto-generated method stub
		return taskMapper.deleteTaskAdmin(pkId);
	}
	

	
		

}
