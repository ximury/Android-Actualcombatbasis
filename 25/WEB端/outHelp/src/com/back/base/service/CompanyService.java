package com.back.base.service;

import java.util.List;

import com.back.base.pageModel.Company;


public interface CompanyService {

	public Company save(Company company);

	public List<Company> list(Company company);

	public Company find(String id);

	public Company update(Company company);
	
	public Company findRoot();

	public Company saveOrUpdate(Company company);

	public int delete(String id);
	
	
}
