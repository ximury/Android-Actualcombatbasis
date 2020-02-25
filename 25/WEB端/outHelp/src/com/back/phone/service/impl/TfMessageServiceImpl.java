package com.back.phone.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.phone.dao.TfMessageMapper;
import com.back.phone.model.TfMessage;
import com.back.phone.service.TfMessageService;

@Service
public class TfMessageServiceImpl implements TfMessageService{
	
	@Autowired(required = true)
	private TfMessageMapper tfMessageMapper;

	@Override
	public int deleteByPrimaryKey(String messageId) {
		// TODO Auto-generated method stub
		return tfMessageMapper.deleteByPrimaryKey(messageId);
	}

	@Override
	public int insert(TfMessage record) {
		// TODO Auto-generated method stub
		return tfMessageMapper.insert(record);
	}

	@Override
	public int insertSelective(TfMessage record) {
		// TODO Auto-generated method stub
		return tfMessageMapper.insertSelective(record);
	}

	@Override
	public TfMessage selectByPrimaryKey(String messageId) {
		// TODO Auto-generated method stub
		return tfMessageMapper.selectByPrimaryKey(messageId);
	}

	@Override
	public int updateByPrimaryKeySelective(TfMessage record) {
		// TODO Auto-generated method stub
		return tfMessageMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TfMessage record) {
		// TODO Auto-generated method stub
		return tfMessageMapper.updateByPrimaryKey(record);
	}

}
