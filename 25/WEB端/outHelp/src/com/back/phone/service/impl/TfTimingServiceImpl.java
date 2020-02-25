package com.back.phone.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.phone.dao.TfTimingMapper;
import com.back.phone.model.TfTiming;
import com.back.phone.service.TfTimingService;

@Service
public class TfTimingServiceImpl implements TfTimingService{
	
	@Autowired(required = true)
	private TfTimingMapper tfTimingMapper;

	@Override
	public int deleteByPrimaryKey(String timingId) {
		// TODO Auto-generated method stub
		return tfTimingMapper.deleteByPrimaryKey(timingId);
	}

	@Override
	public int insert(TfTiming record) {
		// TODO Auto-generated method stub
		return tfTimingMapper.insert(record);
	}

	@Override
	public int insertSelective(TfTiming record) {
		// TODO Auto-generated method stub
		return tfTimingMapper.insertSelective(record);
	}

	@Override
	public TfTiming selectByPrimaryKey(String timingId) {
		// TODO Auto-generated method stub
		return tfTimingMapper.selectByPrimaryKey(timingId);
	}

	@Override
	public int updateByPrimaryKeySelective(TfTiming record) {
		// TODO Auto-generated method stub
		return tfTimingMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TfTiming record) {
		// TODO Auto-generated method stub
		return tfTimingMapper.updateByPrimaryKey(record);
	}

}
