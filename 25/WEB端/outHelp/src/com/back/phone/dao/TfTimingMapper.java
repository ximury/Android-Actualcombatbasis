package com.back.phone.dao;

import com.back.phone.model.TfTiming;

public interface TfTimingMapper {
    int deleteByPrimaryKey(String timingId);

    int insert(TfTiming record);

    int insertSelective(TfTiming record);

    TfTiming selectByPrimaryKey(String timingId);

    int updateByPrimaryKeySelective(TfTiming record);

    int updateByPrimaryKey(TfTiming record);
}