package com.back.base.dao;

import java.util.List;



public interface BaseMapper<T> {
	
	
	/**
	 * 根据id删除操作
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(String id);

    /**
     * 插入对象
     * @param t
     * @return
     */
    int insert(T t);

    /**
     * 动态插入对象
     * @param t
     * @return
     */
    int insertSelective(T t);

    /**
     * 根据id查询对象
     * @param id
     * @return
     */
    T selectByPrimaryKey(String id);
    
    /**
     * 查询对象列表
     * @param t
     * @return
     */
    List<T> select(T t);
    
    /**
     * 查询对象列表，带分页
     * @param t
     * @return
     */
    List<T> query(T t);

    /**
     * 根据id动态更新对象
     * @param t
     * @return
     */
    int updateByPrimaryKeySelective(T t);

    /**
     * 根据id更新对象
     * @param t
     * @return
     */
    int updateByPrimaryKey(T t);
    

}