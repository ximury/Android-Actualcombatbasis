package com.back.phone.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.phone.dao.TfLossDealMapper;
import com.back.phone.dao.TfLossMapper;
import com.back.phone.model.TfLoss;
import com.back.phone.model.TfLossDeal;
import com.back.phone.service.TfLossDealService;

@Service
public class TfLossDealServiceImpl implements TfLossDealService {

	@Autowired(required = true)
	private TfLossDealMapper tfLossDealMapper;

	@Autowired(required = true)
	private TfLossMapper tfLossMapper;

	@Override
	public int deleteByPrimaryKey(String ldId) {
		// TODO Auto-generated method stub
		return tfLossDealMapper.deleteByPrimaryKey(ldId);
	}

	@Override
	public int insert(TfLossDeal record) {
		// TODO Auto-generated method stub
		if (record.getLdId() == null || record.getLdId().equals("")) {
			record.setLdId(UUID.randomUUID().toString());
		}
		return tfLossDealMapper.insert(record);
	}

	@Override
	public int insertSelective(TfLossDeal record) {
		// TODO Auto-generated method stub
		return tfLossDealMapper.insertSelective(record);
	}

	@Override
	public TfLossDeal selectByPrimaryKey(String ldId) {
		// TODO Auto-generated method stub
		return tfLossDealMapper.selectByPrimaryKey(ldId);
	}

	@Override
	public int updateByPrimaryKeySelective(TfLossDeal record) {
		// TODO Auto-generated method stub
		return tfLossDealMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TfLossDeal record) {
		// TODO Auto-generated method stub
		return tfLossDealMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<TfLossDeal> queryTempList(TfLossDeal record) {
		// TODO Auto-generated method stub
		return tfLossDealMapper.queryTempList(record);
	}

	@Override
	public int insertNew(String id, ArrayList<TfLossDeal> record) {
		// TODO Auto-generated method stub
		DecimalFormat df = new DecimalFormat("######0.00");// 保存显示用保留二位
		
		double money = 0;

		if (record != null && record.size() > 0) {

			for (int i = 0; i < record.size(); i++) {
				TfLossDeal tfLossDeal = record.get(i);
				this.insert(tfLossDeal);
				money = money + Double.valueOf(tfLossDeal.getLdMoney()).doubleValue();
			}
		}

		if (money > 0) {
			TfLoss tfLoss = tfLossMapper.selectByPrimaryKey(id);
			tfLoss.setLossMoney(df.format(money + Double.valueOf(tfLoss.getLossMoney()).doubleValue()));
			tfLossMapper.updateByPrimaryKeySelective(tfLoss);
		}

		return 0;
	}

	@Override
	public int insertAddNew(TfLoss tfLoss, ArrayList<TfLossDeal> record) {
		// TODO Auto-generated method stub
		DecimalFormat df = new DecimalFormat("######0.00");// 保存显示用保留二位
		
		double money = 0;

		if (record != null && record.size() > 0) {

			for (int i = 0; i < record.size(); i++) {
				TfLossDeal tfLossDeal = record.get(i);
				this.insert(tfLossDeal);
				money = money + Double.valueOf(tfLossDeal.getLdMoney()).doubleValue();
			}
		}

		tfLoss.setLossMoney(df.format(money));
		tfLossMapper.insert(tfLoss);

		return 0;
	}

}
