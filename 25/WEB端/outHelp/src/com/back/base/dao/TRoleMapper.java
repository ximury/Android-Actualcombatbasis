package com.back.base.dao;

import java.util.List;

import com.back.base.model.TRole;


public interface TRoleMapper extends BaseMapper<TRole>{

	List<TRole> query(TRole role);

	List<TRole> selectByLoginId(String loginId);
}