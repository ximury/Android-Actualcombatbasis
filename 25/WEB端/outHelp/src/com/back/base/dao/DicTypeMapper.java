package com.back.base.dao;

import java.util.List;

import com.back.base.model.DicType;

public interface DicTypeMapper extends BaseMapper<DicType>{
	 List<DicType> selectAll(DicType dicType);
}