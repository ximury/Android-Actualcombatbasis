package com.back.base.utils;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.back.base.model.DataDic;

public class DicUtil {
	
	private static WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();  
	
	private static SqlSessionFactory sessionFactory =(SqlSessionFactory)wac.getBean("sqlSessionFactory"); 
	

	
	public static  String converter(String type,String code){
		//dataDicService.list(new DataDic());
		SqlSession session = sessionFactory.openSession();
		List<DataDic> dataDics = session.selectList(DataDic.class.getName()+".selectByPrimaryKey","1");
		System.out.println(dataDics.size());
		return "";
	}
	
	
	public static void init(){}
	
	

}
