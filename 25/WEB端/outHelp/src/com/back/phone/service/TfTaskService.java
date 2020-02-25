package com.back.phone.service;

import com.back.phone.model.TfTask;

public interface TfTaskService {
    int deleteByPrimaryKey(String taskId);

    int insert(TfTask record);

    int insertSelective(TfTask record);

    TfTask selectByPrimaryKey(String taskId);

    int updateByPrimaryKeySelective(TfTask record);

    int updateByPrimaryKey(TfTask record);
}
