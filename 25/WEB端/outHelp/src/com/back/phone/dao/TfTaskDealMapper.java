package com.back.phone.dao;

import com.back.phone.model.TfTaskDeal;

public interface TfTaskDealMapper {
    int deleteByPrimaryKey(String tdId);

    int insert(TfTaskDeal record);

    int insertSelective(TfTaskDeal record);

    TfTaskDeal selectByPrimaryKey(String tdId);

    int updateByPrimaryKeySelective(TfTaskDeal record);

    int updateByPrimaryKey(TfTaskDeal record);
}