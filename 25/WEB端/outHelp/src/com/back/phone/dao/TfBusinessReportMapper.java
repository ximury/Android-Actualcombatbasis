package com.back.phone.dao;

import java.util.List;
import java.util.Map;

import com.back.phone.model.TfBusinessReport;

public interface TfBusinessReportMapper {
    int deleteByPrimaryKey(String brId);

    int insert(TfBusinessReport record);

    int insertSelective(TfBusinessReport record);

    TfBusinessReport selectByPrimaryKey(String brId);

    int updateByPrimaryKeySelective(TfBusinessReport record);

    int updateByPrimaryKey(TfBusinessReport record);
    
    List<TfBusinessReport> queryTempList(TfBusinessReport record);
    
    List<TfBusinessReport> queryTempListByCstmId(String record);
    
    int queryTempListByUser(Map<String, Object> record);
}