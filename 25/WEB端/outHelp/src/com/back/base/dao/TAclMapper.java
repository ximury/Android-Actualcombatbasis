package com.back.base.dao;

import java.util.List;

import com.back.base.model.TAcl;

public interface TAclMapper extends BaseMapper<TAcl>{

	List<String> selectResourceIdByPrincipalIds(List<String> principalIds);

	/**
	 * 根据PrincipalId和ResourceType删除授权
	 * @param tacl
	 * @return
	 */
	int deleteByPrincipalIdAndResourceType(TAcl tacl);

}