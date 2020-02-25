package com.back.phone.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.phone.dao.TfAttenceMainMapper;
import com.back.phone.model.TfAttenceMain;
import com.back.phone.service.TfAttenceMainService;

@Service
public class TfAttenceMainServiceImpl implements TfAttenceMainService{
	
	@Autowired (required = true)
	private TfAttenceMainMapper tfAttenceMainMapper;

	@Override
	public int deleteByPrimaryKey(String amId) {
		// TODO Auto-generated method stub
		return tfAttenceMainMapper.deleteByPrimaryKey(amId);
	}

	@Override
	public int insert(TfAttenceMain record) {
		if(record.getAmId()==null  || record.getAmId().equals("")){
			record.setAmId(UUID.randomUUID().toString());
		}
		// TODO Auto-generated method stub
		return tfAttenceMainMapper.insert(record);
	}

	@Override
	public int insertSelective(TfAttenceMain record) {
		// TODO Auto-generated method stub
		return tfAttenceMainMapper.insertSelective(record);
	}

	@Override
	public TfAttenceMain selectByPrimaryKey(String amId) {
		// TODO Auto-generated method stub
		return tfAttenceMainMapper.selectByPrimaryKey(amId);
	}

	@Override
	public int updateByPrimaryKeySelective(TfAttenceMain record) {
		// TODO Auto-generated method stub
		return tfAttenceMainMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TfAttenceMain record) {
		// TODO Auto-generated method stub
		return tfAttenceMainMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<TfAttenceMain> queryTempList(TfAttenceMain record) {
		// TODO Auto-generated method stub
		return tfAttenceMainMapper.queryTempList(record);
	}

	@Override
	public TfAttenceMain selectByIdDate(TfAttenceMain record) {
		// TODO Auto-generated method stub
		return tfAttenceMainMapper.selectByIdDate(record);
	}

}
