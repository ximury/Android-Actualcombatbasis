package com.back.base.dao;

import java.util.List;

import com.back.base.model.TLogin;
import com.back.base.pageModel.Login;


public interface TLoginMapper extends BaseMapper<TLogin>{

	List<Login> query(Login login);

	int deleteByPrimaryKeys(String[] ids);

	Login find(Login login);
	
	Login selectById(String id);

	List<Login> selectByRoleId(String roleId);
	
	
}