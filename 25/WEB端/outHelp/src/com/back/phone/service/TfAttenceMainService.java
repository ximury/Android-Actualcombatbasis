package com.back.phone.service;

import java.util.List;

import com.back.phone.model.TfAttenceMain;

public interface TfAttenceMainService {
    int deleteByPrimaryKey(String amId);

    int insert(TfAttenceMain record);

    int insertSelective(TfAttenceMain record);

    TfAttenceMain selectByPrimaryKey(String amId);

    int updateByPrimaryKeySelective(TfAttenceMain record);

    int updateByPrimaryKey(TfAttenceMain record);
    
    List<TfAttenceMain> queryTempList(TfAttenceMain record);
    
    TfAttenceMain selectByIdDate(TfAttenceMain record);
}
