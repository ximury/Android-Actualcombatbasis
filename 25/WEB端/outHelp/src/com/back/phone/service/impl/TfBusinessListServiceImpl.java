package com.back.phone.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.base.utils.DateTime;
import com.back.phone.dao.TfBusinessListDealMapper;
import com.back.phone.dao.TfBusinessListMapper;
import com.back.phone.dao.TfCustomerMapper;
import com.back.phone.dao.TfLossMapper;
import com.back.phone.model.TfBusinessList;
import com.back.phone.model.TfBusinessListDeal;
import com.back.phone.model.TfCustomer;
import com.back.phone.model.TfLoss;
import com.back.phone.service.TfBusinessListService;

@Service
public class TfBusinessListServiceImpl implements TfBusinessListService {

	@Autowired(required = true)
	private TfBusinessListMapper tfBusinessListMapper;

	@Autowired(required = true)
	private TfCustomerMapper tfCustomerMapper;

	@Autowired(required = true)
	private TfLossMapper tfLossMapper;

	@Autowired(required = true)
	private TfBusinessListDealMapper tfBusinessListDealMapper;

	@Override
	public int deleteByPrimaryKey(String blId) {
		// TODO Auto-generated method stub
		return tfBusinessListMapper.deleteByPrimaryKey(blId);
	}

	@Override
	public int insert(TfBusinessList record) {
		if (record.getBlId() == null || record.getBlId().equals("")) {
			record.setBlId(UUID.randomUUID().toString());
		}
		// TODO Auto-generated method stub
		return tfBusinessListMapper.insert(record);
	}

	@Override
	public int insertSelective(TfBusinessList record) {
		// TODO Auto-generated method stub
		return tfBusinessListMapper.insertSelective(record);
	}

	@Override
	public TfBusinessList selectByPrimaryKey(String blId) {
		// TODO Auto-generated method stub
		return tfBusinessListMapper.selectByPrimaryKey(blId);
	}

	@Override
	public int updateByPrimaryKeySelective(TfBusinessList record) {
		// TODO Auto-generated method stub
		return tfBusinessListMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TfBusinessList record) {
		// TODO Auto-generated method stub
		return tfBusinessListMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<TfBusinessList> queryTempList(TfBusinessList record) {
		// TODO Auto-generated method stub
		if (record.getBlStatus()!=null && record.getBlStatus().equals("跟进中")) {
			return tfBusinessListMapper.queryTempList(record);
		} else {
			return tfBusinessListMapper.queryTempListO(record);
		}
	}

	@Override
	public int insertNew(String st, TfBusinessList tfBusinessList, TfBusinessListDeal tfBusinessListDeal,
			ArrayList<TfCustomer> list1, ArrayList<TfLoss> list2) {
		String serviceDate = DateTime.getCurDate_yyyy_MM_dd();
		// TODO Auto-generated method stub
		/********* begin 客户 *************/
		String uuCid = "";
		if (list1.size() > 0) {
			for (int i = 0; i < list1.size(); i++) {
				TfCustomer tfCustomer = list1.get(i);
				if (tfCustomer.getCustomerId() != null && tfCustomer.getCustomerId().length() > 0) {

					int hzTime = Integer.valueOf(tfCustomer.getCustomerSpare1()) + 1;

					tfCustomer.setCustomerSpare1(hzTime + "");

					tfCustomerMapper.updateByPrimaryKeySelective(tfCustomer);
				} else {
					String customerId = UUID.randomUUID().toString();

					tfCustomer.setCustomerId(customerId);
					tfCustomer.setCustomerComplete("1");
					tfCustomer.setCustomerAllmoney("0.00");
					tfCustomer.setCustomerSpare1("1");// 第一次合作

					tfCustomerMapper.insert(tfCustomer);

					uuCid = uuCid + customerId + ";";
				}

			}

		}

		/********* begin 损耗 ************/
		String uuLid = "";
		if (list2.size() > 0) {
			for (int i = 0; i < list2.size(); i++) {
				TfLoss tfLoss = list2.get(i);
				String lossid = UUID.randomUUID().toString();

				tfLoss.setLossId(lossid);
				tfLoss.setLossStatus("1");// 业务
				tfLoss.setLossSpare2(serviceDate);

				tfLossMapper.insert(tfLoss);

				uuLid = uuLid + lossid + ";";
			}
		}

		if (uuCid.length() > 0) {
			uuCid = uuCid.substring(0, uuCid.length() - 1);
			tfBusinessList.setBlCustomer(uuCid);
		}
		if (uuLid.length() > 0) {
			uuLid = uuLid.substring(0, uuLid.length() - 1);
			tfBusinessList.setBlSpare1(uuLid);
		}

		this.insert(tfBusinessList);

		tfBusinessListDeal.setBldId(UUID.randomUUID().toString());
		tfBusinessListDealMapper.insert(tfBusinessListDeal);

		return 0;
	}

	@Override
	public List<TfBusinessList> queryTempListByCstmId(String record) {
		// TODO Auto-generated method stub
		return tfBusinessListMapper.queryTempListByCstmId(record);
	}

}
