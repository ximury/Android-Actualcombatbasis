package com.back.phone.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.phone.dao.TfCustomerMapper;
import com.back.phone.model.TfCustomer;
import com.back.phone.service.TfCustomerService;

@Service
public class TfCustomerServiceImpl implements TfCustomerService{
	
	@Autowired(required = true)
	private TfCustomerMapper tfCustomerMapper;

	@Override
	public int deleteByPrimaryKey(String customerId) {
		// TODO Auto-generated method stub
		return tfCustomerMapper.deleteByPrimaryKey(customerId);
	}

	@Override
	public int insert(TfCustomer record) {
		// TODO Auto-generated method stub
		if(record.getCustomerId()==null  || record.getCustomerId().equals("")){
			record.setCustomerId(UUID.randomUUID().toString());
		}
		return tfCustomerMapper.insert(record);
	}

	@Override
	public int insertSelective(TfCustomer record) {
		// TODO Auto-generated method stub
		return tfCustomerMapper.insertSelective(record);
	}

	@Override
	public TfCustomer selectByPrimaryKey(String customerId) {
		// TODO Auto-generated method stub
		return tfCustomerMapper.selectByPrimaryKey(customerId);
	}

	@Override
	public int updateByPrimaryKeySelective(TfCustomer record) {
		// TODO Auto-generated method stub
		return tfCustomerMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TfCustomer record) {
		// TODO Auto-generated method stub
		return tfCustomerMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<TfCustomer> queryTempList(TfCustomer record) {
		// TODO Auto-generated method stub
		return tfCustomerMapper.queryTempList(record);
	}

}
