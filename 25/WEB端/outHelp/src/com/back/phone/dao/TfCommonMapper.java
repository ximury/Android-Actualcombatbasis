package com.back.phone.dao;

import java.util.List;

import com.back.phone.model.TfCommon;

public interface TfCommonMapper {
    int deleteByPrimaryKey(String commonId);

    int insert(TfCommon record);

    int insertSelective(TfCommon record);

    TfCommon selectByPrimaryKey(String commonId);

    int updateByPrimaryKeySelective(TfCommon record);

    int updateByPrimaryKey(TfCommon record);
    
    List<TfCommon> queryTempList(TfCommon record);
}