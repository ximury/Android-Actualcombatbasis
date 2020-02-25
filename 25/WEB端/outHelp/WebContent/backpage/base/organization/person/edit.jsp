<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%String contextPath = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>新增/修改个人信息</title>
<link href="<%=contextPath%>/backpage/styles/main.css" rel="stylesheet" type="text/css" />
<script src="<%=contextPath%>/backpage/scripts/jquery.js" type="text/javascript"></script>
<script src="<%=contextPath%>/backpage/scripts/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
</head>
<body>
	<!-- 内容开始 -->
	<div class="centent" style="padding-top: 10px;">
		<form id="dataform" method="post" action="<%=contextPath%>/back/person.do?cmd=saveOrUpdate">
			<span> 
				<input type="hidden" name="positionid" value="${param.positionid}">
				<input type="hidden" name="id" value="${person.id}">
				<input type="hidden" name="createtime" value="${person.createtime}">
			</span>
			<%@ include file="form_include.jsp"%>
			<div class="page">
				<input type="submit" value="提交" class="but_shop" /> 
				<input type="button" value="取 消" class="but_shop" onclick="history.back(-1)" />
			</div>
		</form>
	</div>
</body>
</html>
