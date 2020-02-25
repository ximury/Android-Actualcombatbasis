package com.back.phone.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.base.dao.TLoginMapper;
import com.back.base.dao.TPersonMapper;
import com.back.base.model.TLogin;
import com.back.base.model.TPerson;
import com.back.base.pageModel.Login;
import com.back.phone.dao.TfBusinessListMapper;
import com.back.phone.dao.TfBusinessReportMapper;
import com.back.phone.dao.TfCustomerMapper;
import com.back.phone.model.TfBusinessList;
import com.back.phone.model.TfBusinessReport;
import com.back.phone.model.TfCustomer;
import com.back.phone.modelNew.CountNew;
import com.back.phone.modelNew.CountNo1New;
import com.back.phone.modelNew.CountNo2New;
import com.back.phone.modelNew.CountNo3New;
import com.back.phone.service.CountService;

@Service
public class CountServiceImpl implements CountService {

	@Autowired(required = true)
	private TfBusinessReportMapper tfBusinessReportMapper;

	@Autowired(required = true)
	private TfBusinessListMapper tfBusinessListMapper;

	@Autowired(required = true)
	private TfCustomerMapper tfCustomerMapper;

	@Autowired(required = true)
	private TPersonMapper tPersonMapper;
	
	@Autowired(required = true)
	private TLoginMapper loginMapper;

	@Override
	public CountNew selectNum(String id, String Did, String yearMonth) {
		// TODO Auto-generated method stub
		CountNew cn = new CountNew();
		String[] Cid = null;
		Cid = id.split(";");
		Map<String, Object> mapPer = new HashMap<String, Object>();
		mapPer.put("mapList", Cid);
		mapPer.put("begin", yearMonth);
		int iPer = tfBusinessListMapper.queryCountByUser(mapPer);
		cn.setBusPer(iPer + "");// 个人业绩
		int tfBusinessReports = tfBusinessReportMapper.queryTempListByUser(mapPer);
		cn.setViPer(tfBusinessReports + "");// 个人拜访
		int tfCustomers = tfCustomerMapper.queryTempListByUser(mapPer);
		cn.setCusPer(tfCustomers + "");// 个人客户

		TfCustomer tfCustomer = new TfCustomer();
		tfCustomer.setUserId(id);
		tfCustomer.setCustomerComplete("0");
		tfCustomer.setCustomerSpare4(yearMonth);
		List<TfCustomer> tfCustomerss = tfCustomerMapper.queryTempList(tfCustomer);
		cn.setLeft(tfCustomerss.size() + "");// LIFT

		TfBusinessList tfBusinessList = new TfBusinessList();
		tfBusinessList.setUserId(id);
		tfBusinessList.setBlStatus("赢单");
		tfBusinessList.setBlData(yearMonth);
		List<TfBusinessList> tfBusinessListWin = tfBusinessListMapper.queryTempList(tfBusinessList);
		double winNum = (double) tfBusinessListWin.size();

		TfBusinessList tfBusinessList1 = new TfBusinessList();
		tfBusinessList1.setUserId(id);
		tfBusinessList1.setBlData(yearMonth);
		List<TfBusinessList> tfBusinessListLoss = tfBusinessListMapper.queryTempList(tfBusinessList);
		double losNum = (double) tfBusinessListLoss.size();

		DecimalFormat df = new DecimalFormat("######0.00");// 计算用保留两位
		if (losNum != 0) {
			double d = winNum / losNum * 100;
			cn.setMid(df.format(d));// MID
		} else {
			cn.setMid("0");
		}

		TPerson person = new TPerson();
		person.setDepartmentid(Did);
		List<TPerson> tPersons = tPersonMapper.query(person);
		String[] CidAll = null;
		String tpersonId = "";
		for (TPerson t : tPersons) {
			tpersonId = tpersonId + t.getId() + ";";
		}
		if (tpersonId.length() > 0) {
			tpersonId = tpersonId.substring(0, tpersonId.length() - 1);
		}
		CidAll = tpersonId.split(";");
		Map<String, Object> mapAll = new HashMap<String, Object>();
		mapAll.put("mapList", CidAll);
		mapAll.put("begin", yearMonth);
		int iAll = tfBusinessListMapper.queryCountByUser(mapAll);
		cn.setBusAll(iAll + "");// 部门业绩
		int tfBusinessReportsAll = tfBusinessReportMapper.queryTempListByUser(mapAll);
		cn.setViAll(tfBusinessReportsAll + "");// 部门拜访
		int tfCustomersAll = tfCustomerMapper.queryTempListByUser(mapAll);
		cn.setCusAll(tfCustomersAll + "");// 部门客户

		/***** 排名 *****/
		Map<String, Integer> mp = new HashMap<String, Integer>();
		for (int i = 0; i < CidAll.length; i++) {
			String[] sc = CidAll[i].split(";");
			Map<String, Object> mapSc = new HashMap<String, Object>();
			mapSc.put("mapList", sc);
			mapSc.put("begin", yearMonth);
			int scMoney = tfBusinessListMapper.queryCountMoneyByUser(mapSc);
			mp.put(sc[0], scMoney);
		}

		List<Map.Entry<String, Integer>> infoIds = new ArrayList<Map.Entry<String, Integer>>(mp.entrySet());

		// 排序规则
		Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o2.getValue() - o1.getValue());
				// return (o1.getKey()).toString().compareTo(o2.getKey());
			}
		});

		int num = 0;
		for (int i = 0; i < infoIds.size(); i++) {
			num = i;
			Entry<String, Integer> mp11 = infoIds.get(i);
			if (mp11.getKey().equals(id)) {
				break;
			}
		}

		if (num == 0) {
			num = 1;
		}

		cn.setRight(num + "");// RIGHT

		return cn;
	}

	@Override
	public CountNo1New selectNumNo(String id, String Did, String yearMonth) {
		// TODO Auto-generated method stub
		CountNo1New countNo1New = new CountNo1New();

		TfCustomer tfCustomer = new TfCustomer();

		tfCustomer.setUserId(id);
		tfCustomer.setCustomerSpare4(yearMonth);

		tfCustomer.setCustomerSpare1("no1");
		int num1 = tfCustomerMapper.queryCusNo(tfCustomer);
		countNo1New.setNum1(String.valueOf(num1));

		tfCustomer.setCustomerSpare1("no2");
		int num2 = tfCustomerMapper.queryCusNo(tfCustomer);
		countNo1New.setNum2(String.valueOf(num2));

		tfCustomer.setCustomerSpare1("no3");
		int num3 = tfCustomerMapper.queryCusNo(tfCustomer);
		countNo1New.setNum3(String.valueOf(num3));

		tfCustomer.setCustomerSpare1("no4");
		int num4 = tfCustomerMapper.queryMoneyNo(tfCustomer);
		countNo1New.setNum4(String.valueOf(num4));

		tfCustomer.setCustomerSpare1("no5");
		int num5 = tfCustomerMapper.queryMoneyNo(tfCustomer);
		countNo1New.setNum5(String.valueOf(num5));

		return countNo1New;
	}

	@Override
	public CountNo2New selectNumNo2(String id, String Did, String yearMonth) {
		// TODO Auto-generated method stub
		CountNo2New countNo2New = new CountNo2New();

		TfBusinessList tfBusinessList = new TfBusinessList();

		tfBusinessList.setUserId(id);
		tfBusinessList.setBlData(yearMonth);

		List<TfBusinessList> tfBusinessList1 = tfBusinessListMapper.queryTempList(tfBusinessList);
		int num1 = tfBusinessList1.size();

		tfBusinessList.setBlStatus("赢单");
		List<TfBusinessList> tfBusinessList2 = tfBusinessListMapper.queryTempList(tfBusinessList);
		int num2 = tfBusinessList2.size();

		tfBusinessList.setBlStatus("订单失败");
		List<TfBusinessList> tfBusinessList3 = tfBusinessListMapper.queryTempList(tfBusinessList);
		int num3 = tfBusinessList3.size();

		tfBusinessList.setBlStatus("跟进中");
		List<TfBusinessList> tfBusinessList4 = tfBusinessListMapper.queryTempList(tfBusinessList);
		int num4 = tfBusinessList4.size();

		countNo2New.setNum1(num1 + "");
		countNo2New.setNum2(num2 + "");
		countNo2New.setNum3(num3 + "");
		countNo2New.setNum4(num4 + "");

		DecimalFormat df = new DecimalFormat("######0.00");// 计算用保留两位
		if (num1 != 0) {
			double d = (double) num2 / (double) num1 * 100;
			countNo2New.setNum5(df.format(d));
		} else {
			countNo2New.setNum5("0");
		}

		return countNo2New;
	}

	@Override
	public CountNo3New selectNumNo3(String id, String Did, String yearMonth) {
		// TODO Auto-generated method stub
		CountNo3New countNo3New = new CountNo3New();

		TPerson person = new TPerson();
		person.setDepartmentid(Did);
		List<TPerson> tPersons = tPersonMapper.query(person);
		String[] CidAll = null;
		String tpersonId = "";
		for (TPerson t : tPersons) {
			tpersonId = tpersonId + t.getId() + ";";
		}
		if (tpersonId.length() > 0) {
			tpersonId = tpersonId.substring(0, tpersonId.length() - 1);
		}
		CidAll = tpersonId.split(";");

		Map<String, Double> map = new HashMap<String, Double>();

		for (int i = 0; i < CidAll.length; i++) {
			Map<String, Object> mapSc = new HashMap<String, Object>();
			mapSc.put("mapList", CidAll[i]);
			mapSc.put("begin", yearMonth);
			List<TfBusinessList> tbls = tfBusinessListMapper.queryLYB(mapSc);
			
			double in = 0;
			if(tbls.size()>0){
				for(TfBusinessList t:tbls){
					in = in + Double.valueOf(t.getBlMoney()).doubleValue();
				}
			}
			map.put(CidAll[i], in);
		}

		List<Map.Entry<String, Double>> infoIds = new ArrayList<Map.Entry<String, Double>>(map.entrySet());

		// 排序规则
		Collections.sort(infoIds, new Comparator<Map.Entry<String, Double>>() {
			public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
				return (int) (o2.getValue() - o1.getValue());
				// return (o1.getKey()).toString().compareTo(o2.getKey());
			}
		});
		
		List result = new ArrayList();
		
		if(infoIds.size()>0){
			for (int i = 0; i < infoIds.size(); i++) {
				String ssid = infoIds.get(i).getKey();
				TLogin lo = loginMapper.selectByPrimaryKey(ssid);
				result.add((i+1)+","+lo.getUsername()+","+lo.getId()+","+infoIds.get(i).getValue());
			}
		}

		countNo3New.setList(result);

		return countNo3New;
	}

}
