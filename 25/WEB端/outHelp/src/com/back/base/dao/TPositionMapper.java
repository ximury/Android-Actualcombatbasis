package com.back.base.dao;

import java.util.List;

import com.back.base.model.TPosition;
import com.back.base.pageModel.Position;


public interface TPositionMapper {
    int deleteByPrimaryKey(String id);

    int insert(TPosition record);

    int insertSelective(TPosition record);

    TPosition selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TPosition record);

    int updateByPrimaryKey(TPosition record);

	List<TPosition> selectByCondtion(TPosition position);

	void deleteByPrimaryKeys(String[] split);

	Position find(String id);

	void deleteAll();
}