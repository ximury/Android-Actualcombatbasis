package com.back.phone.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.back.phone.model.TfBusinessReport;

public interface TfBusinessReportService {
    int deleteByPrimaryKey(String brId);

    int insert(TfBusinessReport record);

    int insertSelective(TfBusinessReport record);

    TfBusinessReport selectByPrimaryKey(String brId);

    int updateByPrimaryKeySelective(TfBusinessReport record);

    int updateByPrimaryKey(TfBusinessReport record);
    
    List<TfBusinessReport> queryTempList(TfBusinessReport record);
    
    int insertNew(String userid,TfBusinessReport record,ArrayList<Map> list1,ArrayList<Map> list2);
    
    List<TfBusinessReport> queryTempListByCstmId(String record);
}
