package com.back.base.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.base.dao.DicTypeMapper;
import com.back.base.model.DicType;
import com.back.base.service.DicTypeService;

@Service("dicTypeService")
public class DicTypeServiceImpl implements DicTypeService {
	
	
	private static final Logger logger = Logger.getLogger(DicTypeServiceImpl.class);

	@Autowired(required = true)
	private DicTypeMapper dicTypeMapper;
	

	public List<DicType> list(DicType dicType) {
		return dicTypeMapper.query(dicType);
	}

	public DicType saveOrUpdate(DicType dicType) {
		if(dicType.isUpdateFlag()){
			dicTypeMapper.updateByPrimaryKeySelective(dicType);
			
		}else{
			dicTypeMapper.insertSelective(dicType);
		}
		logger.info(DicTypeMapper.class.getName()+"数据更新成功！");
		return dicType;
	}

	public DicType find(String pkId) {
		return dicTypeMapper.selectByPrimaryKey(pkId);
	}

	public int delete(String pkId) {
		int count = dicTypeMapper.deleteByPrimaryKey(pkId);
		logger.info(DicTypeMapper.class.getName()+"数据删除成功！");
		return count;
	}
	
	public List<DicType> selectAll(DicType dicType) {
		return dicTypeMapper.selectAll(dicType);
	}

}
