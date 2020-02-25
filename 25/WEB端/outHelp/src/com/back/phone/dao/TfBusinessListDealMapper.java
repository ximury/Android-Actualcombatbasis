package com.back.phone.dao;

import java.util.List;

import com.back.phone.model.TfBusinessListDeal;

public interface TfBusinessListDealMapper {
    int deleteByPrimaryKey(String bldId);

    int insert(TfBusinessListDeal record);

    int insertSelective(TfBusinessListDeal record);

    TfBusinessListDeal selectByPrimaryKey(String bldId);

    int updateByPrimaryKeySelective(TfBusinessListDeal record);

    int updateByPrimaryKey(TfBusinessListDeal record);
    
    List<TfBusinessListDeal> queryTempList(TfBusinessListDeal record);
}