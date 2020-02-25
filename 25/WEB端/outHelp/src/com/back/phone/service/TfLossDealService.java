package com.back.phone.service;

import java.util.ArrayList;
import java.util.List;

import com.back.phone.model.TfLoss;
import com.back.phone.model.TfLossDeal;

public interface TfLossDealService {
    int deleteByPrimaryKey(String ldId);

    int insert(TfLossDeal record);

    int insertSelective(TfLossDeal record);

    TfLossDeal selectByPrimaryKey(String ldId);

    int updateByPrimaryKeySelective(TfLossDeal record);

    int updateByPrimaryKey(TfLossDeal record);
    
    List<TfLossDeal> queryTempList(TfLossDeal record);
    
    int insertNew(String id,ArrayList<TfLossDeal> record);
    
    int insertAddNew(TfLoss tfLoss,ArrayList<TfLossDeal> record);
}
