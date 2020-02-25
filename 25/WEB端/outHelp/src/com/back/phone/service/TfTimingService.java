package com.back.phone.service;

import com.back.phone.model.TfTiming;

public interface TfTimingService {
    int deleteByPrimaryKey(String timingId);

    int insert(TfTiming record);

    int insertSelective(TfTiming record);

    TfTiming selectByPrimaryKey(String timingId);

    int updateByPrimaryKeySelective(TfTiming record);

    int updateByPrimaryKey(TfTiming record);
}
