<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../../../commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>查看人员信息</title>
<script src="${ctx}/backpage/scripts/jquery.js" type="text/javascript"></script>
<link href="${ctx}/backpage/styles/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<!-- 内容开始 -->
	<div class="centent" style="padding-top: 10px;">
		<table cellpadding="0" cellspacing="0" border="0" class="uiTable">
			<tr class="jg">
				<td width="10%" height="26" class="leftbor">姓名：</td>
				<td>${person.name}</td>
			</tr>
			<tr>
				<td width="10%" height="26" class="leftbor">英文名：</td>
				<td>${person.engname}</td>
			</tr>
			<tr class="jg">
				<td width="10%" height="26" class="leftbor">员工分类：</td>
				<td>${person.type}</td>
			</tr>
			<tr>
				<td width="10%" height="26" class="leftbor">性别：</td>
				<td>${person.sex}</td>
			</tr>
			<tr class="jg">
				<td width="10%" height="26" class="leftbor">移动电话：</td>
				<td>${person.phone}</td>
			</tr>
			<tr>
				<td width="10%" height="26" class="leftbor">固定电话：</td>
				<td>${person.tel}</td>
			</tr>
			<tr class="jg">
				<td width="10%" height="26" class="leftbor">电子邮件：</td>
				<td>${person.email}</td>
			</tr>
			<tr>
				<td width="10%" height="26" class="leftbor">邮编：</td>
				<td>${person.zipcode}</td>
			</tr>
			<tr class="jg">
				<td width="10%" height="26" class="leftbor">创建时间：</td>
				<td>${person.createtime}</td>
			</tr>
			<tr class="jg">
				<td width="10%" height="26" class="leftbor">修改时间：</td>
				<td>${person.updatetime}</td>
			</tr>
			<tr class="jg">
				<td width="10%" height="26" class="leftbor">联系地址：</td>
				<td>${person.address}</td>
			</tr>
		</table>
		<div class="page">
			<input type="button" value="返回" class="but_shop" onclick="history.back(-1)" />
		</div>
	</div>
</body>
</html>
