package com.back.phone.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.phone.dao.TfTaskMapper;
import com.back.phone.model.TfTask;
import com.back.phone.service.TfTaskService;

@Service
public class TfTaskServiceImpl implements TfTaskService{
	
	@Autowired(required = true)
	private TfTaskMapper tfTaskMapper;

	@Override
	public int deleteByPrimaryKey(String taskId) {
		// TODO Auto-generated method stub
		return tfTaskMapper.deleteByPrimaryKey(taskId);
	}

	@Override
	public int insert(TfTask record) {
		// TODO Auto-generated method stub
		return tfTaskMapper.insert(record);
	}

	@Override
	public int insertSelective(TfTask record) {
		// TODO Auto-generated method stub
		return tfTaskMapper.insertSelective(record);
	}

	@Override
	public TfTask selectByPrimaryKey(String taskId) {
		// TODO Auto-generated method stub
		return tfTaskMapper.selectByPrimaryKey(taskId);
	}

	@Override
	public int updateByPrimaryKeySelective(TfTask record) {
		// TODO Auto-generated method stub
		return tfTaskMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TfTask record) {
		// TODO Auto-generated method stub
		return tfTaskMapper.updateByPrimaryKey(record);
	}

}
