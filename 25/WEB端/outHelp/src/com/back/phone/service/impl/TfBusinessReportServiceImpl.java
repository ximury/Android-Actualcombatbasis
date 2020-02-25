package com.back.phone.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.base.utils.DateTime;
import com.back.phone.dao.TfBusinessReportMapper;
import com.back.phone.dao.TfCustomerMapper;
import com.back.phone.dao.TfLossMapper;
import com.back.phone.model.TfBusinessReport;
import com.back.phone.model.TfCustomer;
import com.back.phone.model.TfLoss;
import com.back.phone.service.TfBusinessReportService;

@Service
public class TfBusinessReportServiceImpl implements TfBusinessReportService {

	@Autowired(required = true)
	private TfBusinessReportMapper tfBusinessReportMapper;

	@Autowired(required = true)
	private TfCustomerMapper tfCustomerMapper;

	@Autowired(required = true)
	private TfLossMapper tfLossMapper;

	@Override
	public int deleteByPrimaryKey(String brId) {
		// TODO Auto-generated method stub
		return tfBusinessReportMapper.deleteByPrimaryKey(brId);
	}

	@Override
	public int insert(TfBusinessReport record) {
		// TODO Auto-generated method stub
		if (record.getBrId() == null || record.getBrId().equals("")) {
			record.setBrId(UUID.randomUUID().toString());
		}
		return tfBusinessReportMapper.insert(record);
	}

	@Override
	public int insertSelective(TfBusinessReport record) {
		// TODO Auto-generated method stub
		return tfBusinessReportMapper.insertSelective(record);
	}

	@Override
	public TfBusinessReport selectByPrimaryKey(String brId) {
		// TODO Auto-generated method stub
		return tfBusinessReportMapper.selectByPrimaryKey(brId);
	}

	@Override
	public int updateByPrimaryKeySelective(TfBusinessReport record) {
		// TODO Auto-generated method stub
		return tfBusinessReportMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TfBusinessReport record) {
		// TODO Auto-generated method stub
		return tfBusinessReportMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<TfBusinessReport> queryTempList(TfBusinessReport record) {
		// TODO Auto-generated method stub
		return tfBusinessReportMapper.queryTempList(record);
	}

	@Override
	public int insertNew(String userId, TfBusinessReport record, ArrayList<Map> list1, ArrayList<Map> list2) {
		// TODO Auto-generated method stub
		String cid = "";
		String lid = "";
		
		String serviceDate = DateTime.getCurDate_yyyy_MM_dd();

		if (list1.size() > 0) {
			for (int i = 0; i < list1.size(); i++) {

				if (list1.get(i).get("customerId") != null && !list1.get(i).get("customerId").equals("")) {

					TfCustomer tfCustomer = tfCustomerMapper
							.selectByPrimaryKey((String) list1.get(i).get("customerId"));


					int hzTime = Integer.valueOf(tfCustomer.getCustomerSpare1()) + 1;

					tfCustomer.setCustomerSpare1(hzTime + "");

					tfCustomerMapper.updateByPrimaryKeySelective(tfCustomer);

				} else {

					String customerId = UUID.randomUUID().toString();

					TfCustomer tfCustomer = new TfCustomer();

					tfCustomer.setCustomerId(customerId);
					tfCustomer.setUserId(userId);
					tfCustomer.setCustomerComplete("1");
					tfCustomer.setCustomerAllmoney("0.00");
					tfCustomer.setCustomerSpare1("1");// 第一次合作

					tfCustomer.setCustomerName((String) list1.get(i).get("customerName"));
					tfCustomer.setCustomerPhone((String) list1.get(i).get("customerPhone"));
					tfCustomer.setCustomerCompany((String) list1.get(i).get("customerCompany"));
					tfCustomer.setCustomerAddress((String) list1.get(i).get("customerAddress"));
					tfCustomer.setCustomerRemark((String) list1.get(i).get("customerRemark"));

					tfCustomerMapper.insert(tfCustomer);

					cid = cid + customerId + ";";
				}
			}
		}

		if (list2.size() > 0) {
			for (int i = 0; i < list2.size(); i++) {
				TfLoss tfLoss = new TfLoss();

				String lossid = UUID.randomUUID().toString();

				tfLoss.setUserId(userId);
				tfLoss.setLossId(lossid);
				tfLoss.setLossStatus("1");//业务
				tfLoss.setLossName((String) list2.get(i).get("name"));
				tfLoss.setLossMoney((String) list2.get(i).get("lossMoney"));
				tfLoss.setLossSpare1((String) list2.get(i).get("lossSpare1"));
				tfLoss.setLossSpare2(serviceDate);

				tfLossMapper.insert(tfLoss);

				lid = lid + lossid + ";";
			}
		}

		if (cid.length() > 0) {
			cid = cid.substring(0, cid.length() - 1);
			record.setBrSpare1(cid);
		}

		if (lid.length() > 0) {
			lid = lid.substring(0, lid.length() - 1);
			record.setBrSpare2(lid);
		}

		this.insert(record);

		return 0;
	}

	@Override
	public List<TfBusinessReport> queryTempListByCstmId(String record) {
		// TODO Auto-generated method stub
		return tfBusinessReportMapper.queryTempListByCstmId(record);
	}

}
