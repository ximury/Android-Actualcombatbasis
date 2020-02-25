package com.back.phone.service;

import java.util.ArrayList;
import java.util.List;

import com.back.phone.model.TfBusinessList;
import com.back.phone.model.TfBusinessListDeal;
import com.back.phone.model.TfCustomer;
import com.back.phone.model.TfLoss;

public interface TfBusinessListService {
	int deleteByPrimaryKey(String blId);

	int insert(TfBusinessList record);

	int insertSelective(TfBusinessList record);

	TfBusinessList selectByPrimaryKey(String blId);

	int updateByPrimaryKeySelective(TfBusinessList record);

	int updateByPrimaryKey(TfBusinessList record);

	List<TfBusinessList> queryTempList(TfBusinessList record);

	int insertNew(String st, TfBusinessList tfBusinessList, TfBusinessListDeal tfBusinessListDeal,
			ArrayList<TfCustomer> list1, ArrayList<TfLoss> list2);
	
	List<TfBusinessList> queryTempListByCstmId(String record);
}
