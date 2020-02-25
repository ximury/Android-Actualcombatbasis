package com.back.base.dao;

import java.util.List;

import com.back.base.model.Troletask;

public interface TroletaskMapper {
	
    int insert(Troletask record);

    int deleteByPrimaryKeys(String[] ids);
    
    
    int deleteByPrimaryKey(String id);
    
    
    List<Troletask> selectAll(Troletask record);
}