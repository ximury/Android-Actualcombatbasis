<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%String contextPath = request.getContextPath();%>
<%@page import="java.util.*"%>
<%@page import="java.text.*"%>
<%@page import="com.back.base.pageModel.Login"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/backpage/styles/header.css" />
<title>SSI</title>
</head>
<%
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 E");
	String currDate = sdf.format(new Date());
	
	Login login = (Login)request.getSession().getAttribute("login");
	
	if(login==null){
		
		login = new Login();
	}
%>

<body class="topbody"> 
<div class="logo"></div> 
<div class="info">
	<div class="time">日期：<%=currDate%></div> 
	<div class="welcome">欢迎您，<%=login.getName()%>
		<span
			class="red1">
		</span>
	</div>
</div>
<div class="navbar">
	<a href="<%=contextPath%>/back/login.do?cmd=passwordInput&id=<%=login.getId()%>" target="mainFrame"><span>修改密码</span></a>
	&nbsp;&nbsp;&nbsp;&nbsp;
	<!-- <a href="<%=contextPath%>/back/login.do?cmd=find&id=<%=login.getId()%>" target="mainFrame"><span>个人中心</span></a>   -->
	<div class="function"> 
		<a href="<%=contextPath%>/index.do" target="_parent">
		<img src="<%=contextPath%>/backpage/images/icon_exit.gif" width="13" height="15" align="absmiddle" /> 退出登录</a>
	</div> 
</div>
</body>
</html>

