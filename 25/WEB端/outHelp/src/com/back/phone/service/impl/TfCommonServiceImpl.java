package com.back.phone.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.phone.dao.TfCommonMapper;
import com.back.phone.model.TfCommon;
import com.back.phone.service.TfCommonService;

@Service
public class TfCommonServiceImpl implements TfCommonService{
	
	@Autowired(required = true)
	private TfCommonMapper tfCommonMapper;

	@Override
	public int deleteByPrimaryKey(String commonId) {
		// TODO Auto-generated method stub
		return tfCommonMapper.deleteByPrimaryKey(commonId);
	}

	@Override
	public int insert(TfCommon record) {
		// TODO Auto-generated method stub
		if (record.getCommonId() == null || record.getCommonId().equals("")) {
			record.setCommonId(UUID.randomUUID().toString());
		}
		return tfCommonMapper.insert(record);
	}

	@Override
	public int insertSelective(TfCommon record) {
		// TODO Auto-generated method stub
		return tfCommonMapper.insertSelective(record);
	}

	@Override
	public TfCommon selectByPrimaryKey(String commonId) {
		// TODO Auto-generated method stub
		return tfCommonMapper.selectByPrimaryKey(commonId);
	}

	@Override
	public int updateByPrimaryKeySelective(TfCommon record) {
		// TODO Auto-generated method stub
		return tfCommonMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKey(TfCommon record) {
		// TODO Auto-generated method stub
		return tfCommonMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<TfCommon> queryTempList(TfCommon record) {
		// TODO Auto-generated method stub
		return tfCommonMapper.queryTempList(record);
	}

}
