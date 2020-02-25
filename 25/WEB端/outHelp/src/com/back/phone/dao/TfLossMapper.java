package com.back.phone.dao;

import java.util.List;

import com.back.phone.model.TfLoss;

public interface TfLossMapper {
    int deleteByPrimaryKey(String lossId);

    int insert(TfLoss record);

    int insertSelective(TfLoss record);

    TfLoss selectByPrimaryKey(String lossId);

    int updateByPrimaryKeySelective(TfLoss record);

    int updateByPrimaryKey(TfLoss record);
    
    List<TfLoss> queryTempList(TfLoss record);
}