package com.back.base.dao;

import java.util.List;
import java.util.Map;

import com.back.base.model.Ttask;

public interface TtaskMapper {
    int deleteByPrimaryKey(String pkId);

    int insert(Ttask record);

    int insertSelective(Ttask record);

    Ttask selectByPrimaryKey(String pkId);

    int updateByPrimaryKeySelective(Ttask record);

    int updateByPrimaryKey(Ttask record);
    
    int updateTaskClient(String pkId);
    
    int deleteTaskAdmin(String pkId);
    
    
    List<Ttask> query(Ttask task);
}