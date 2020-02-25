package com.back.phone.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.phone.dao.TfBusinessListDealMapper;
import com.back.phone.model.TfBusinessListDeal;
import com.back.phone.service.TfBusinessListDealService;

@Service
public class TfBusinessListDealServiceImpl implements TfBusinessListDealService{
	
	@Autowired(required = true)
	private TfBusinessListDealMapper tfBusinessListDealMapper;

	@Override
	public int deleteByPrimaryKey(String bldId) {
		// TODO Auto-generated method stub
		return tfBusinessListDealMapper.deleteByPrimaryKey(bldId);
	}

	@Override
	public int insert(TfBusinessListDeal record) {
		if(record.getBldId()==null  || record.getBldId().equals("")){
			record.setBldId(UUID.randomUUID().toString());
		}
		// TODO Auto-generated method stub
		return tfBusinessListDealMapper.insert(record);
	}

	@Override
	public int insertSelective(TfBusinessListDeal record) {
		// TODO Auto-generated method stub
		return tfBusinessListDealMapper.insertSelective(record);
	}

	@Override
	public TfBusinessListDeal selectByPrimaryKey(String bldId) {
		// TODO Auto-generated method stub
		return tfBusinessListDealMapper.selectByPrimaryKey(bldId);
	}

	@Override
	public int updateByPrimaryKeySelective(TfBusinessListDeal record) {
		// TODO Auto-generated method stub
		return tfBusinessListDealMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TfBusinessListDeal record) {
		// TODO Auto-generated method stub
		return tfBusinessListDealMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<TfBusinessListDeal> queryTempList(TfBusinessListDeal record) {
		// TODO Auto-generated method stub
		return tfBusinessListDealMapper.queryTempList(record);
	}

}
