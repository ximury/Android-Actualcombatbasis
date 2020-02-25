package com.back.phone.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.phone.dao.TfJobPlanMapper;
import com.back.phone.model.TfJobPlan;
import com.back.phone.service.TfJobPlanService;

@Service
public class TfJobPlanServiceImpl implements TfJobPlanService{
	
	@Autowired(required = true)
	private TfJobPlanMapper tfJobPlanMapper;

	@Override
	public int deleteByPrimaryKey(String jpId) {
		// TODO Auto-generated method stub
		return tfJobPlanMapper.deleteByPrimaryKey(jpId);
	}

	@Override
	public int insert(TfJobPlan record) {
		// TODO Auto-generated method stub
		if(record.getJpId()==null  || record.getJpId().equals("")){
			record.setJpId(UUID.randomUUID().toString());
		}
		return tfJobPlanMapper.insert(record);
	}

	@Override
	public int insertSelective(TfJobPlan record) {
		// TODO Auto-generated method stub
		return tfJobPlanMapper.insertSelective(record);
	}

	@Override
	public TfJobPlan selectByPrimaryKey(String jpId) {
		// TODO Auto-generated method stub
		return tfJobPlanMapper.selectByPrimaryKey(jpId);
	}

	@Override
	public int updateByPrimaryKeySelective(TfJobPlan record) {
		// TODO Auto-generated method stub
		return tfJobPlanMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TfJobPlan record) {
		// TODO Auto-generated method stub
		return tfJobPlanMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<TfJobPlan> queryTempList(TfJobPlan record) {
		// TODO Auto-generated method stub
		return tfJobPlanMapper.queryTempList(record);
	}

}
