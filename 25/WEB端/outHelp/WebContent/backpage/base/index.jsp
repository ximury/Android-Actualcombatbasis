<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%String contextPath = request.getContextPath();%>
<html xmlns="http://www.w3.org/1999/xhtml" style="overflow-x:hidden;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>慧学多元学业诊断系统</title>
</head>
<frameset rows="110,*" cols="*" frameborder="no" border="0" framespacing="0">

  <frame src="<%=contextPath%>/backpage/base/top.jsp" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" />
  
  <frameset id="mainFrameset" cols="226,10,*" frameborder="no" border="0" framespacing="0">
  
    <frame src="<%=contextPath%>/backpage/base/menu.jsp" name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame" />
    
    <frame src="<%=contextPath%>/backpage/base/leftbar.jsp" name="leftbar" scrolling="No" noresize="noresize" id="leftbar" />
    
    <frame src="<%=contextPath%>/back/task_list.do" name="mainFrame" id="mainFrame" />
    
  </frameset>
</frameset>
<noframes><body>
</body></noframes>
</html>