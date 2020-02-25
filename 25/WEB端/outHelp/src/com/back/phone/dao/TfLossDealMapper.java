package com.back.phone.dao;

import java.util.List;

import com.back.phone.model.TfLossDeal;

public interface TfLossDealMapper {
    int deleteByPrimaryKey(String ldId);

    int insert(TfLossDeal record);

    int insertSelective(TfLossDeal record);

    TfLossDeal selectByPrimaryKey(String ldId);

    int updateByPrimaryKeySelective(TfLossDeal record);

    int updateByPrimaryKey(TfLossDeal record);
    
    List<TfLossDeal> queryTempList(TfLossDeal record);
}