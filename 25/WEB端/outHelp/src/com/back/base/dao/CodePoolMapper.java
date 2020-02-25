package com.back.base.dao;

import java.util.List;

import com.back.base.model.CodePoolVo;
import com.back.base.model.DataDic;

public interface CodePoolMapper extends BaseMapper<DataDic>{

	int deleteCodePool(String code_value);

	int updateRelease(CodePoolVo codePoolVo);

	void insertCodePool(CodePoolVo vo);

	List findCodePool(String key);

	List<String> findMaxCode(CodePoolVo codePoolVo);
	
}