package com.back.base.interceptor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.FastResultSetHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import com.back.base.MapParam;
import com.back.base.utils.ReflectUtil;

@Intercepts(@Signature(method="handleResultSets", type=ResultSetHandler.class, args={Statement.class}))  
public class MapInterceptor implements Interceptor {

	public Object intercept(Invocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		//通过invocation获取代理的目标对象  
	       Object target = invocation.getTarget();  
	        //暂时ResultSetHandler只有FastResultSetHandler这一种实现  
	        if (target instanceof FastResultSetHandler) {  
	            FastResultSetHandler resultSetHandler = (FastResultSetHandler) target;  
	            //利用反射获取到FastResultSetHandler的ParameterHandler属性，从而获取到ParameterObject；  
	            ParameterHandler parameterHandler = ReflectUtil.getFieldValue(resultSetHandler, "parameterHandler");  
	            Object parameterObj = parameterHandler.getParameterObject();  
	            //判断ParameterObj是否是我们定义的MapParam，如果是则进行自己的处理逻辑  
	            if (parameterObj instanceof MapParam) {//拦截到了  
	                MapParam mapParam = (MapParam) parameterObj;  
	                //获取到当前的Statement  
	                Statement stmt = (Statement) invocation.getArgs()[0];  
	               
	                //通过Statement获取到当前的结果集，对其进行处理，并返回对应的处理结果  
	                return handleResultSet(stmt.getResultSet(), mapParam);  
	            }  
	        }  
	        //如果没有进行拦截处理，则执行默认逻辑  
	        return invocation.proceed();  
 

	}
	 /** 
	    * 处理结果集 
	    * @param resultSet 
	    * @param mapParam 
	    * @return 
	    */  
	   private Object handleResultSet(ResultSet resultSet, MapParam mapParam) {  
	       // TODO Auto-generated method stub  
	       if (resultSet != null) {  
	           //拿到Key对应的字段  
	           String keyField = (String) mapParam.get(MapParam.KEY_FIELD);  
	           //拿到Value对应的字段  
	           String valueField = (String) mapParam.get(MapParam.VALUE_FIELD);  
	           //定义用于存放Key-Value的Map  
	           Map<Object, Object> map = new HashMap<Object, Object>();  
	           //handleResultSets的结果一定是一个List，当我们的对应的Mapper接口定义的是返回一个单一的元素，并且handleResultSets返回的列表  
	           //的size为1时，Mybatis会取返回的第一个元素作为对应Mapper接口方法的返回值。  
	           List<Object> resultList = new ArrayList<Object>();  
	           try {  
	               //把每一行对应的Key和Value存放到Map中  
	               while (resultSet.next()) {  
	                   Object key = resultSet.getObject(keyField);  
	                   Object value = resultSet.getObject(valueField);  
	                   map.put(key, value);  
	               }  
	           } catch (SQLException e) {  
	               e.printStackTrace();  
	           } finally {  
	               closeResultSet(resultSet);  
	           }  
	           //把封装好的Map存放到List中并进行返回  
	           resultList.add(map);  
	           return resultList;  
	       }  
	       return null;  
	   }  
	   /** 
	     * 关闭ResultSet 
	     * @param resultSet 需要关闭的ResultSet 
	     */  
	    private void closeResultSet(ResultSet resultSet) {  
	        try {  
	            if (resultSet != null) {  
	                resultSet.close();  
	            }  
	        } catch (SQLException e) {  
	              
	        }  
	    }  
	  


	public Object plugin(Object obj) {
		// TODO Auto-generated method stub
		return Plugin.wrap(obj, this);  
	}

	public void setProperties(Properties arg0) {
		// TODO Auto-generated method stub
		
	}  

}
