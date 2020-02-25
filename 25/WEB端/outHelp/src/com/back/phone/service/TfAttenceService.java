package com.back.phone.service;

import java.util.List;

import com.back.phone.model.TfAttence;

public interface TfAttenceService {
    int deleteByPrimaryKey(String attenceId);

    int insert(TfAttence record);

    int insertSelective(TfAttence record);

    TfAttence selectByPrimaryKey(String attenceId);

    int updateByPrimaryKeySelective(TfAttence record);

    int updateByPrimaryKey(TfAttence record);
    
    List<TfAttence> queryTempList(TfAttence record);
    
    List<TfAttence> queryCheckList(TfAttence record);
}
