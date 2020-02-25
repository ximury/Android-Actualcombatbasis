package com.back.base.dao;

import java.util.List;

import com.back.base.model.TCompany;
import com.back.base.pageModel.Company;


public interface TCompanyMapper extends BaseMapper<TCompany>{

	List<Company> query(Company company);

	Company find(String id);
	
	Company findRoot();

}