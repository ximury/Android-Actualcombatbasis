package com.back.base.dbCon;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.back.base.cache.DefData;

public class DbHelper {
	private static Connection ct=null;
	private static PreparedStatement ps=null;
	private static ResultSet rs=null;
	private static CallableStatement cs=null;
	
	private static String url="";
	private static String username="";
	private static String driver="";
	private static String password="";
	
	
	static{
		try {
//		    url=DefData.getUrl();
//		    username=DefData.getUsername();
//		    driver=DefData.getDriver();
//		    password=DefData.getPassword();
		    System.out.println(url);
			Class.forName(driver);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){	
		try {
			ct=DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ct;
	}
	
	public static Connection getCt() {
		return ct;
	}
	
	public static PreparedStatement getPs() {
		return ps;
	}
	
	public static CallableStatement getCs() {
		return cs;
	}

	//1.先写一个update/ delete/insert
	//sql格式： update 表名 set 字段名=？where 字段=？
	//parameters 应该是{"abc","23",.....}
	public static void executeUpdate(String sql,String[] parameters){
		try {
		 ct=getConnection();
		 ps=ct.prepareStatement(sql);
		 if(parameters!=null){
			for(int i=0;i<parameters.length;i++){
			   ps.setObject(i+1, parameters[i]);
			}
	    }
		 ps.executeUpdate();		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
			
		}finally{
			close(rs,ps,ct);			
		}		
	 }	
	public static void executeUpdateAll(List updateSql){
		
		try {
		 ct=getConnection();
		 int index=0;
		 for(int i=0;i<updateSql.size();i++){
			Object [] obj = (Object[]) updateSql.get(i);
			String sql = (String) obj[0];
			String[] parameters = (String[]) obj[1];
			ps=ct.prepareStatement(sql);
			if(parameters!=null){
				for(int k=0;k<parameters.length;k++){
					ps.setObject(k+1, parameters[k]);
				}
			}
			index++;
			ps.executeUpdate();
			if(index==100){
				 ct.commit();	
				 index=0;
			}
			
		 }
		 ct.commit();			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
			
		}finally{
			close(rs,ps,ct);			
		}		
	 }	
	//2.如果有多个dml语句则要考虑事务
	public static void executeUpdate(String[] sql,String[][] parameters){
		try{
		 ct=getConnection();
		 ct.setAutoCommit(false);
		 for(int i=0;i<=sql.length;i++){
			ps=ct.prepareStatement(sql[i]);
		    if(parameters[i]!=null){
				for(int j=0;j<parameters[i].length;j++){
					ps.setString(j+1,parameters[i][j] );
			      }
		    ps.executeUpdate();
		   }
		 }
		ct.commit();	
		} catch (SQLException e) {
		 // TODO Auto-generated catch block
			e.printStackTrace();
			try {
				ct.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
			throw new RuntimeException(e.getMessage());			
			}finally{
			close(rs,ps,ct);
		}	
	}
	
	//3.查询select语句
	public static ResultSet executeQuery(String sql,String[] parameters){
		ct=getConnection();
		try {
			ps=ct.prepareStatement(sql);
			if(parameters!=null){
			   for(int i=0;i<parameters.length;i++){
				  ps.setObject(i+1, parameters[i]);   //用setString(...)也行单setObject的范围更广一些
			   }				
		     }
		 rs=ps.executeQuery();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}	
		return rs;	
	}
	
	
   //4.对executeQuery进行升级  改进的方法就可以显示那里用资源那里关闭资源
	
	public static ArrayList executeQuery2(String sql,String[] parameters){
		//解释一下下面的原因,把变量放在方法里面定义更加保险，以后看见不要惊讶
		PreparedStatement psmt=null;  
		Connection conn=null;
		ResultSet  rs=null;                
		try {
		   conn=getConnection();
		   psmt=conn.prepareStatement(sql);	   
		   if(parameters!=null){
		     for(int i=0;i<parameters.length;i++){
		    	psmt.setObject(i+1, parameters[i]);   //用setString(...)也行单setObject的范围更广一些
			   }				
		    }
		rs=psmt.executeQuery();
		ArrayList al=new ArrayList();
		ResultSetMetaData rsmd=rs.getMetaData();
		int column=rsmd.getColumnCount();    //这里可以得到一条记录有多少列		
		while(rs.next()){
		  Object[] ob=new Object[column]; //对象数组，表示一行数据或一条记录
		  for(int i=1;i<=column;i++){
			 ob[i-1]=rs.getObject(i);				
		  }
			al.add(ob);
		 }
		  return al;  
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		 }finally{
			close(rs, psmt, conn);
	   }
		 
    }
	
	
	
	//5.分页问题?
	
	
	//6.调用存储过程
	public static void callpro1(String sql, String[] parameters){
		try {
			ct=getConnection();
			cs=ct.prepareCall(sql);
			if(parameters!=null){
				for(int i=0;i<parameters.length;i++){
				  cs.setObject(i+1, parameters[i]);	
				}
			}
			cs.execute();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}finally{
			close(rs, cs, ct);
		}
		
	}
	
	//7.调用有返回值的存储过程
	//sql call 过程(?,?,?)
	public static CallableStatement callpro2(String sql,String[] inparameters,Integer[] outparameters){
		try {
			ct=getConnection();
			cs=ct.prepareCall(sql);
			if(inparameters!=null){
				for(int i=0;i<inparameters.length;i++){
					cs.setObject(i+1, inparameters[i]);
				}				
			}
			//给out参数赋值
			if(outparameters!=null){
				for(int i=0;i<outparameters.length;i++){
					cs.registerOutParameter(inparameters.length+1+i,outparameters[i]);
				}		
			}
	
			cs.execute();
		} catch (Exception e) {
			// TODO: handle exception
		   e.printStackTrace();
		   throw new RuntimeException(e.getMessage());
		}finally{
			//无须关闭
		}	
		return cs;		
	}
	
	
	//关闭资源函数	
	public static void close(ResultSet rs,Statement ps,Connection ct){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rs=null;
		}
		if(ps!=null){
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ps=null;
		}
		if(ct!=null){
			try {
				ct.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ct=null;
		}
	}
}
