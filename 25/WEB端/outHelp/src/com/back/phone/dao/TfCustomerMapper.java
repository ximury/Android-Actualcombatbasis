package com.back.phone.dao;

import java.util.List;
import java.util.Map;

import com.back.phone.model.TfCustomer;

public interface TfCustomerMapper {
    int deleteByPrimaryKey(String customerId);

    int insert(TfCustomer record);

    int insertSelective(TfCustomer record);

    TfCustomer selectByPrimaryKey(String customerId);

    int updateByPrimaryKeySelective(TfCustomer record);

    int updateByPrimaryKey(TfCustomer record);
    
    List<TfCustomer> queryTempList(TfCustomer record);
    
    int queryTempListByUser(Map<String, Object> record);
    
    int queryCusNo(TfCustomer record);
    
    int queryMoneyNo(TfCustomer record);
}