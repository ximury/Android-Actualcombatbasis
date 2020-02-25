package com.back.phone.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.phone.dao.TfAttenceMapper;
import com.back.phone.model.TfAttence;
import com.back.phone.service.TfAttenceService;

@Service
public class TfAttenceServiceImpl implements TfAttenceService {
	
	@Autowired(required = true)
	private TfAttenceMapper tfAttenceMapper;

	@Override
	public int deleteByPrimaryKey(String attenceId) {
		// TODO Auto-generated method stub
		return tfAttenceMapper.deleteByPrimaryKey(attenceId);
	}

	@Override
	public int insert(TfAttence record) {
		if(record.getAttenceId()==null  || record.getAttenceId().equals("")){
			record.setAttenceId(UUID.randomUUID().toString());
		}
		// TODO Auto-generated method stub
		return tfAttenceMapper.insert(record);
	}

	@Override
	public int insertSelective(TfAttence record) {
		// TODO Auto-generated method stub
		return tfAttenceMapper.insertSelective(record);
	}

	@Override
	public TfAttence selectByPrimaryKey(String attenceId) {
		// TODO Auto-generated method stub
		return tfAttenceMapper.selectByPrimaryKey(attenceId);
	}

	@Override
	public int updateByPrimaryKeySelective(TfAttence record) {
		// TODO Auto-generated method stub
		return tfAttenceMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TfAttence record) {
		// TODO Auto-generated method stub
		return tfAttenceMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<TfAttence> queryTempList(TfAttence record) {
		// TODO Auto-generated method stub
		return tfAttenceMapper.queryTempList(record);
	}

	@Override
	public List<TfAttence> queryCheckList(TfAttence record) {
		// TODO Auto-generated method stub
		return tfAttenceMapper.queryCheckList(record);
	}

}
