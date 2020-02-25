package com.back.base.dao;

import java.util.List;

import com.back.base.model.TResource;

public interface TResourceMapper extends BaseMapper<TResource>{

	TResource queryRoot(String menuid);

	int selectSiblingsCount(String id);
	
	List<TResource> selectByPrincipalid(String id);
	
}