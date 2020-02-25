<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@ page import="org.apache.commons.logging.LogFactory"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String contextPath = request.getContextPath();%>
<html>
<head>
<title>Error Page</title>
</head>

<body>

	<div id="content">
		<img alt="system internal error" src="<%=contextPath%>/backpage/images/error.gif" style="padding-top:30px;"/>
		<h3>
			对不起,不能处理您的请求<br />
		</h3>
		<b>错误信息:</b> <br>

		<div id="detail_system_error_msg">
			<pre>${msg}</pre>
		</div><br>
		
		<!-- <button onclick="history.back();">返回</button>  -->
		<A HREF="${href}"> 返回 </A>

	</div>
</body>
</html>