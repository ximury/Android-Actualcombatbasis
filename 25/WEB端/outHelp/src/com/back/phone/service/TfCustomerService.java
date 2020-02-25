package com.back.phone.service;

import java.util.List;

import com.back.phone.model.TfCustomer;

public interface TfCustomerService {
    int deleteByPrimaryKey(String customerId);

    int insert(TfCustomer record);

    int insertSelective(TfCustomer record);

    TfCustomer selectByPrimaryKey(String customerId);

    int updateByPrimaryKeySelective(TfCustomer record);

    int updateByPrimaryKey(TfCustomer record);
    
    List<TfCustomer> queryTempList(TfCustomer record);
}
