<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%String password=request.getParameter("password");//获取输入的密码
String username=request.getParameter("username");//获取输入的用户名
if(password!=null && username!=null){
username=new String(username.getBytes("iso-8859-1"),"utf-8");	//对用户名进行转码
password=new String(password.getBytes("iso-8859-1"),"utf-8");	//对密码进行转码
if("mr".equals(username)&&"mrsoft".equals(password)){
%>
<%="ok"%>
<%}%>
<%}%>






