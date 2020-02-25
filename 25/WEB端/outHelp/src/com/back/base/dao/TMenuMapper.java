package com.back.base.dao;

import java.util.List;

import com.back.base.model.TMenu;

public interface TMenuMapper extends BaseMapper<TMenu>{
	
	List<TMenu> selectRoot();
    
    List<TMenu> selectByPid(String pid);
    
    int deleteAll();

	List<TMenu> selectAll();
}