package com.back.base.service;

import java.util.List;

import com.back.base.model.TResource;



public interface ResourceService {


	public TResource find(String id);

	public List<TResource> list(TResource resource);

	public TResource saveOrUpdate(TResource resource);

	public int delete(String id);

	
}
