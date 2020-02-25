package com.back.base.dao;

import java.util.List;

import com.back.base.model.TLoginRoleKey;

public interface TLoginRoleMapper extends BaseMapper<TLoginRoleKey>{
    int deleteByPrimaryKey(TLoginRoleKey key);

	int deleteByLoginIds(String[] ids);
	
	List<String> queryRoleIdByLoginId(String loginId);
	
}