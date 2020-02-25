package com.back.phone.dao;

import com.back.phone.model.TfMessage;

public interface TfMessageMapper {
    int deleteByPrimaryKey(String messageId);

    int insert(TfMessage record);

    int insertSelective(TfMessage record);

    TfMessage selectByPrimaryKey(String messageId);

    int updateByPrimaryKeySelective(TfMessage record);

    int updateByPrimaryKey(TfMessage record);
}