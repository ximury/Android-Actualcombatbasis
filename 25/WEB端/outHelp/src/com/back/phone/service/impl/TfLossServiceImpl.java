package com.back.phone.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.phone.dao.TfLossMapper;
import com.back.phone.model.TfLoss;
import com.back.phone.service.TfLossService;

@Service
public class TfLossServiceImpl implements TfLossService{
	
	@Autowired(required = true)
	private TfLossMapper tfLossMapper;

	@Override
	public int deleteByPrimaryKey(String lossId) {
		// TODO Auto-generated method stub
		return tfLossMapper.deleteByPrimaryKey(lossId);
	}

	@Override
	public int insert(TfLoss record) {
		// TODO Auto-generated method stub
		if(record.getLossId()==null  || record.getLossId().equals("")){
			record.setLossId(UUID.randomUUID().toString());
		}
		return tfLossMapper.insert(record);
	}

	@Override
	public int insertSelective(TfLoss record) {
		// TODO Auto-generated method stub
		return tfLossMapper.insertSelective(record);
	}

	@Override
	public TfLoss selectByPrimaryKey(String lossId) {
		// TODO Auto-generated method stub
		return tfLossMapper.selectByPrimaryKey(lossId);
	}

	@Override
	public int updateByPrimaryKeySelective(TfLoss record) {
		// TODO Auto-generated method stub
		return tfLossMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TfLoss record) {
		// TODO Auto-generated method stub
		return tfLossMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<TfLoss> queryTempList(TfLoss record) {
		// TODO Auto-generated method stub
		return tfLossMapper.queryTempList(record);
	}

}
