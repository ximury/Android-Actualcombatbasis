package com.back.phone.service;

import java.util.List;

import com.back.phone.model.TfJobPlan;

public interface TfJobPlanService {
    int deleteByPrimaryKey(String jpId);

    int insert(TfJobPlan record);

    int insertSelective(TfJobPlan record);

    TfJobPlan selectByPrimaryKey(String jpId);

    int updateByPrimaryKeySelective(TfJobPlan record);

    int updateByPrimaryKey(TfJobPlan record);
    
    List<TfJobPlan> queryTempList(TfJobPlan record);
}
