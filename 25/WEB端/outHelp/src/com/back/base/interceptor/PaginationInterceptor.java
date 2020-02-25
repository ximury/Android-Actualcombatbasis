package com.back.base.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.builder.xml.dynamic.ForEachSqlNode;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import com.back.base.page.Dialect;
import com.back.base.page.MySql5Dialect;
import com.back.base.page.Page;
import com.back.base.page.PageContext;
import com.back.base.page.ReflectHelper;

//只拦截select部分
@Intercepts({@Signature(type=Executor.class,method="query",args={ MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class })})
public class PaginationInterceptor implements Interceptor{
	
	private final static Log log = LogFactory.getLog(PaginationInterceptor.class);   
	
	Dialect dialect = new MySql5Dialect();
	 
	public Object intercept(Invocation invocation) throws Throwable {
		//com.flydreamer.orm.mapper.UsersMapper.list2
		MappedStatement mappedStatement=(MappedStatement)invocation.getArgs()[0];	
		if(mappedStatement.getId().indexOf("query")<=0){
			
			 return invocation.proceed();
		}
		Object parameter = invocation.getArgs()[1]; 
		BoundSql boundSql = mappedStatement.getBoundSql(parameter); 
		String originalSql = boundSql.getSql().trim();   
		RowBounds rowBounds = (RowBounds)invocation.getArgs()[2];
		
		Object parameterObject = boundSql.getParameterObject();
		if(boundSql==null || boundSql.getSql()==null || "".equals(boundSql.getSql()))
			return null;
		//分页参数--上下文传参
		Page page = null;
		PageContext context=PageContext.getContext();
		
		//map传参每次都将currentPage重置,先判读map再判断context
		if(parameterObject!=null)
			page = (Page)ReflectHelper.isPage(parameterObject,"page");
		
		//分页参数--context参数里的Page传参
		if(page==null && context.isPagination()==true)
		{
			page = context;
		}
		
		//后面用到了context的东东
		if(page!=null && page.isPagination()==true) 			
		{
		  int totpage=page.getTotalRows();	
		  //得到总记录数
		 // if (totpage==0)
			//{
				StringBuffer countSql  = new StringBuffer(originalSql.length()+100 );
				
				
				if(originalSql.indexOf("order by")==-1){
					countSql.append("select count(1) ").append(originalSql.substring(originalSql.indexOf("from"))).append("");
					
				}else{
					countSql.append("select count(1) ").append(originalSql.substring(originalSql.indexOf("from"),originalSql.indexOf("order by"))).append("");
					
				}
				
				 Connection connection=mappedStatement.getConfiguration().getEnvironment().getDataSource().getConnection()  ;          
                 PreparedStatement countStmt = connection.prepareStatement(countSql.toString());  
                 BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(),countSql.toString(),boundSql.getParameterMappings(),parameterObject);  
                 setParameters(countStmt,mappedStatement,countBS,parameterObject);  
                 ResultSet rs = countStmt.executeQuery();  
                 if (rs.next()) {  
                	 totpage = rs.getInt(1);  
                 }  
                 rs.close();  
                 countStmt.close();  
                 connection.close();
			//}
		    //分页计算
	        page.init(totpage,page.getPageSize(),page.getCurrentPage());
		  
			if(rowBounds == null || rowBounds == RowBounds.DEFAULT){
				
				int a = page.getCurrentPage()-1;
				
				if(a<0){
					a = 0;
				}
				
//				if(page.getPageSize()>50){
//					
//					page.setPageSize(50);
//				}
				int begin = page.getPageSize()*a;
				int end = page.getPageSize();
				rowBounds= new RowBounds(begin,end);
				
			}	

			//分页查询 本地化对象 修改数据库注意修改实现
		    String pagesql=dialect.getLimitString(originalSql, rowBounds.getOffset(), rowBounds.getLimit());
		    invocation.getArgs()[2] = new RowBounds(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);   
		    BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(), pagesql,boundSql.getParameterMappings(),boundSql.getParameterObject());   
		    MappedStatement newMs = copyFromMappedStatement(mappedStatement,new BoundSqlSqlSource(newBoundSql));  
		    invocation.getArgs()[0]= newMs;  
		}
			
			
		   return invocation.proceed();
		
	}
	public static class BoundSqlSqlSource implements SqlSource {  
        BoundSql boundSql;  
  
        public BoundSqlSqlSource(BoundSql boundSql) {  
            this.boundSql = boundSql;  
        }  
  
        public BoundSql getBoundSql(Object parameterObject) {  
            return boundSql;  
        }  
    }  
	public Object plugin(Object arg0) {
		 return Plugin.wrap(arg0, this);
	}
	public void setProperties(Properties arg0) {
	        
	}
    
    /** 
     * 对SQL参数(?)设值,参考org.apache.ibatis.executor.parameter.DefaultParameterHandler 
     * @param ps 
     * @param mappedStatement 
     * @param boundSql 
     * @param parameterObject 
     * @throws SQLException 
     */  
    private void setParameters(PreparedStatement ps,MappedStatement mappedStatement,BoundSql boundSql,Object parameterObject) throws SQLException {  
        ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());  
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();  
        if (parameterMappings != null) {  
            Configuration configuration = mappedStatement.getConfiguration();  
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();  
            MetaObject metaObject = parameterObject == null ? null: configuration.newMetaObject(parameterObject);  
            for (int i = 0; i < parameterMappings.size(); i++) {  
                ParameterMapping parameterMapping = parameterMappings.get(i);  
                if (parameterMapping.getMode() != ParameterMode.OUT) {  
                    Object value;  
                    String propertyName = parameterMapping.getProperty();  
                    PropertyTokenizer prop = new PropertyTokenizer(propertyName);  
                    if (parameterObject == null) {  
                        value = null;  
                    } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {  
                        value = parameterObject;  
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {  
                        value = boundSql.getAdditionalParameter(propertyName);  
                    } else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX)&& boundSql.hasAdditionalParameter(prop.getName())) {  
                        value = boundSql.getAdditionalParameter(prop.getName());  
                        if (value != null) {  
                            value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));  
                        }  
                    } else {  
                        value = metaObject == null ? null : metaObject.getValue(propertyName);  
                    }  
                    TypeHandler typeHandler = parameterMapping.getTypeHandler();  
                    if (typeHandler == null) {  
                        throw new ExecutorException("There was no TypeHandler found for parameter "+ propertyName + " of statement "+ mappedStatement.getId());  
                    }  
                    typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());  
                }  
            }  
        }  
    }  
    
    private MappedStatement copyFromMappedStatement(MappedStatement ms,   
    		 SqlSource newSqlSource) {   
    		Builder builder = new MappedStatement.Builder(ms.getConfiguration(),   
    		ms.getId(), newSqlSource, ms.getSqlCommandType());   
    		builder.resource(ms.getResource());   
    		builder.fetchSize(ms.getFetchSize());   
    		builder.statementType(ms.getStatementType());   
    		builder.keyGenerator(ms.getKeyGenerator());   
    		//builder.keyProperty(ms.getKeyProperties()[0]);   
    		builder.timeout(ms.getTimeout());   
    		 builder.parameterMap(ms.getParameterMap());   
    		builder.resultMaps(ms.getResultMaps());   
    		builder.cache(ms.getCache());   
    		MappedStatement newMs = builder.build();   
    		return newMs;   
    		}   
    	 

}