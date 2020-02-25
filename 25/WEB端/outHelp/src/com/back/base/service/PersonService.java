package com.back.base.service;

import java.util.List;

import com.back.base.model.TPerson;


public interface PersonService {

	public void save(TPerson person);

	public List<TPerson> list(TPerson person);

	public TPerson find(String id);

	public void update(TPerson person);
	
	public int deleteByPrimaryKey(String id);

	public TPerson saveOrUpdate(TPerson person);
	
}
