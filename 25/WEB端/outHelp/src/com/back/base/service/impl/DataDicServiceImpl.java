package com.back.base.service.impl;

import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.base.dao.CodePoolMapper;
import com.back.base.dao.DataDicMapper;
import com.back.base.model.CodePoolVo;
import com.back.base.model.DataDic;
import com.back.base.service.DataDicService;

@Service("dataDicService")
public class DataDicServiceImpl implements DataDicService {
	
	
	private static final Logger logger = Logger.getLogger(DataDicServiceImpl.class);

	@Autowired(required = true)
	private DataDicMapper dataDicMapper;
	
	@Autowired(required = true)
	private CodePoolMapper codePoolMapper;

	public List<DataDic> list(DataDic dataDic) {
		return dataDicMapper.query(dataDic);
	}

	public DataDic saveOrUpdate(DataDic dataDic) {
		if(dataDic.isUpdateFlag()){
			dataDicMapper.updateByPrimaryKeySelective(dataDic);
			
		}else{
			dataDicMapper.insert(dataDic);
		}
		logger.info(DataDic.class.getName()+"数据更新成功！");
		return dataDic;
	}

	public DataDic find(String id) {
		return dataDicMapper.selectByPrimaryKey(id);
	}

	public int delete(String id) {
		int count = dataDicMapper.deleteByPrimaryKey(id);
		logger.info(DataDicMapper.class.getName()+"数据删除成功！");
		return count;
	}
	
	public List<DataDic> selectAll(DataDic dataDic) {
		return dataDicMapper.selectAll(dataDic);
	}
	
	

	/**
	 * 查询预警信息
	 * @param key 编号类型key
	 * @param table 表名
	 * @param column 字段名
	 * @param prefixKeySetup 关键值
	 * @param condititon 自定义条件
	 * @return 当前最大编号
	 */
    public String findMaxCode(String key,String table,String column,String prefixKeySetup,String condititon){
    	//String sql="SELECT MAX(code) FROM (SELECT MAX("+column+") code FROM "+table+" WHERE "+column+" LIKE '"+prefixKeySetup+"%' "+condititon+" UNION ALL SELECT   MAX (code) code FROM bs_codepool WHERE key='"+key+"' AND code LIKE '"+prefixKeySetup+"%')";
    	CodePoolVo codePoolVo = new CodePoolVo();
    	codePoolVo.setColumn(column);
    	codePoolVo.setTable(table);
    	codePoolVo.setPrefixKeySetup(prefixKeySetup);
    	codePoolVo.setCondititon(condititon);
    	codePoolVo.setKey(key);
    	List<String> list = codePoolMapper.findMaxCode(codePoolVo);
    	if(list != null && list.size() > 0){
    		return list.get(0);
    	}
		return null;
    }
    
	/**
	 * 查询号码池
	 * @param key 关键值
	 * @return 编号列表
	 */
    public List findCodePool(String key){
        List list = codePoolMapper.findCodePool(key);
		return list;
    }
    
    /**
     * 插入编号池
     * 
     * @param vo 用于添加的VO对象
     */
    public CodePoolVo insertCodePool(CodePoolVo vo) {
    	String id = UUID.randomUUID().toString();
        vo.setId(id);
    	codePoolMapper.insertCodePool(vo);
		logger.info(CodePoolVo.class.getName()+"数据更新成功！");
		return vo;
    }
    
    /**
     * 删除编号池数据
     * 
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int deleteCodePool(String code_value) {
    	int count = codePoolMapper.deleteCodePool(code_value);
    	logger.info(CodePoolVo.class.getName()+"数据删除成功！");
		return count;
    }
    
    /**
     * 更新编号占用后释放时间
     * 
     * @param code 编号
     * @param release 释放时间
     * @return 成功更新的记录数
     */
    public int updateRelease(String codeValue,String releaseValue) {
    	CodePoolVo codePoolVo = new CodePoolVo();
    	codePoolVo.setReleaseValue(releaseValue);
    	codePoolVo.setCodeValue(codeValue);
    	int count = codePoolMapper.updateRelease(codePoolVo);
    	logger.info(CodePoolVo.class.getName()+"数据更新成功！");
    	return count;
    }
}
