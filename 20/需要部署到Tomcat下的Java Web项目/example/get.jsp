<%@page contentType="text/html; charset=utf-8" language="java" import="sun.misc.BASE64Decoder"%>
<%
String content=request.getParameter("content");//获取输入的微博信息
 if(content!=null){
 	BASE64Decoder decoder=new BASE64Decoder();
	content=new String(decoder.decodeBuffer(content),"utf-8");	//进行base64解码
String date=new java.util.Date().toLocaleString();	//获取系统时间
%>
<%="[马 云]于 "+date+" 发表一条微博，内容如下："%>

<%=content%>
<% }%>

