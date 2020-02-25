package com.back.phone.modelNew;

import java.util.List;

import com.back.phone.model.TfBusinessList;
import com.back.phone.model.TfBusinessListDeal;
import com.back.phone.model.TfCustomer;

public class TfBusinessListViewNew {
	
	private String idNew;
	
	private TfBusinessList tfBusinessList;
	
	private List<TfBusinessListDeal> tfBusinessListDeal;
	
	private List<TfCustomer> tfCustomer;

	public String getIdNew() {
		return idNew;
	}

	public void setIdNew(String idNew) {
		this.idNew = idNew;
	}

	public TfBusinessList getTfBusinessList() {
		return tfBusinessList;
	}

	public void setTfBusinessList(TfBusinessList tfBusinessList) {
		this.tfBusinessList = tfBusinessList;
	}

	public List<TfBusinessListDeal> getTfBusinessListDeal() {
		return tfBusinessListDeal;
	}

	public void setTfBusinessListDeal(List<TfBusinessListDeal> tfBusinessListDeal) {
		this.tfBusinessListDeal = tfBusinessListDeal;
	}

	public List<TfCustomer> getTfCustomer() {
		return tfCustomer;
	}

	public void setTfCustomer(List<TfCustomer> tfCustomer) {
		this.tfCustomer = tfCustomer;
	}

	
	
	
}
