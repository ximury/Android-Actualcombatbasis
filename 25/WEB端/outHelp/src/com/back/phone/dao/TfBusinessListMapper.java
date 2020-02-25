package com.back.phone.dao;

import java.util.List;
import java.util.Map;


import com.back.phone.model.TfBusinessList;

public interface TfBusinessListMapper {
    int deleteByPrimaryKey(String blId);

    int insert(TfBusinessList record);

    int insertSelective(TfBusinessList record);

    TfBusinessList selectByPrimaryKey(String blId);

    int updateByPrimaryKeySelective(TfBusinessList record);

    int updateByPrimaryKey(TfBusinessList record);
    
    List<TfBusinessList> queryTempList(TfBusinessList record);
    
    List<TfBusinessList> queryTempListO(TfBusinessList record);
    
    List<TfBusinessList> queryTempListByCstmId(String record);
    
    List<TfBusinessList> queryTempListByUser(Map<String, Object> record);
    
    int queryCountByUser(Map<String, Object> record);
    
    int queryCountMoneyByUser(Map<String, Object> record);
    
    List<TfBusinessList> queryLYB(Map<String, Object> record);
    
    
}