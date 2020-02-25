package com.back.phone.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.phone.dao.TfTaskDealMapper;
import com.back.phone.model.TfTaskDeal;
import com.back.phone.service.TfTaskDealService;

@Service
public class TfTaskDealServiceImpl implements TfTaskDealService{
	
	@Autowired (required = true)
	private TfTaskDealMapper tfTaskDealMapper;

	@Override
	public int deleteByPrimaryKey(String tdId) {
		// TODO Auto-generated method stub
		return tfTaskDealMapper.deleteByPrimaryKey(tdId);
	}

	@Override
	public int insert(TfTaskDeal record) {
		// TODO Auto-generated method stub
		return tfTaskDealMapper.insert(record);
	}

	@Override
	public int insertSelective(TfTaskDeal record) {
		// TODO Auto-generated method stub
		return tfTaskDealMapper.insertSelective(record);
	}

	@Override
	public TfTaskDeal selectByPrimaryKey(String tdId) {
		// TODO Auto-generated method stub
		return tfTaskDealMapper.selectByPrimaryKey(tdId);
	}

	@Override
	public int updateByPrimaryKeySelective(TfTaskDeal record) {
		// TODO Auto-generated method stub
		return tfTaskDealMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TfTaskDeal record) {
		// TODO Auto-generated method stub
		return tfTaskDealMapper.updateByPrimaryKey(record);
	}

}
