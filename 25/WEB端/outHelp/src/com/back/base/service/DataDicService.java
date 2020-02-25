package com.back.base.service;

import java.util.List;

import com.back.base.model.CodePoolVo;
import com.back.base.model.DataDic;


public interface DataDicService {

	public List<DataDic> list(DataDic dataDic);

	public DataDic saveOrUpdate(DataDic dataDic);

	public DataDic find(String id);

	public int delete(String id);
	
	public List<DataDic> selectAll(DataDic dataDic);
	
	/**
	 * 查询预警信息
	 * @param key 编号类型key
	 * @param table 表名
	 * @param column 字段名
	 * @param prefixKeySetup 关键值
	 * @param condititon 自定义条件
	 * @return 当前最大编号
	 */
	public String findMaxCode(String key,String table,String column,String prefixKeySetup,String condititon);
	    
		/**
		 * 查询号码池
		 * @param key 关键值
		 * @return 编号列表
		 */
	    public List findCodePool(String key);
	    
	    /**
	     * 插入编号池
	     * 
	     * @param vo 用于添加的VO对象
	     * @return 若添加成功，返回新生成的Oid
	     */
	    public CodePoolVo insertCodePool(CodePoolVo vo);
	    
	    /**
	     * 删除编号池数据
	     * 
	     * @param id 用于删除的记录的id
	     * @return 成功删除的记录数
	     */
	    public int deleteCodePool(String code);
	    
	    /**
	     * 更新编号占用后释放时间
	     * 
	     * @param code 编号
	     * @param release 释放时间
	     * @return 成功更新的记录数
	     */
	    public int updateRelease(String code,String release);
	
}
