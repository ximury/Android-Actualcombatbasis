package com.back.phone.service;

import java.util.List;

import com.back.phone.model.TfLoss;

public interface TfLossService {
    int deleteByPrimaryKey(String lossId);

    int insert(TfLoss record);

    int insertSelective(TfLoss record);

    TfLoss selectByPrimaryKey(String lossId);

    int updateByPrimaryKeySelective(TfLoss record);

    int updateByPrimaryKey(TfLoss record);
    
    List<TfLoss> queryTempList(TfLoss record);
}
