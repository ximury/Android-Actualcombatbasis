package com.back.base.dao;

import java.util.List;

import com.back.base.model.DataDic;


public interface DataDicMapper extends BaseMapper<DataDic>{
	List<DataDic> selectAll(DataDic dataDic);
	
	List<DataDic>  queryByRole(DataDic dataDic);
}