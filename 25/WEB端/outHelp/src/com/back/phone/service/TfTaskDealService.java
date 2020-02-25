package com.back.phone.service;

import com.back.phone.model.TfTaskDeal;

public interface TfTaskDealService {
    int deleteByPrimaryKey(String tdId);

    int insert(TfTaskDeal record);

    int insertSelective(TfTaskDeal record);

    TfTaskDeal selectByPrimaryKey(String tdId);

    int updateByPrimaryKeySelective(TfTaskDeal record);

    int updateByPrimaryKey(TfTaskDeal record);
}
