<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>个人中心</title>
<%@ include file="../../commons/inc.jsp"%>
<%@ include file="../../commons/taglibs.jsp"%>
</head>
<body>
	<div class="main">
		<div class="maintit">
			<h2>
				<img src="${ctx}/backpage/images/tabicon.gif" width="16" height="16" align="absmiddle" /> 个人中心
			</h2>
			<h3>
				<a href="${ctx}/backpage/base/welcome.jsp">主页面 &gt;&gt;</a>
			</h3>
		</div>
		<!-- 内容开始 -->
		<div class="centent" style="padding-top: 10px;">
			<table cellpadding="0" cellspacing="0" border="0" class="uiTable">
				<tr class="jg">
					<td width="10%" height="36" class="leftbor">姓名：</td>
					<td>${login.name}</td>
				</tr>
				<tr>
					<td width="10%" height="36" class="leftbor">英文名：</td>
					<td>${login.engname}</td>
				</tr>
				<tr class="jg">
					<td width="10%" height="36" class="leftbor">员工分类：</td>
					<td>${login.type}</td>
				</tr>
				<tr>
					<td width="10%" height="36" class="leftbor">性别：</td>
					<td>${login.sex}</td>
				</tr>
				<tr class="jg">
					<td width="10%" height="36" class="leftbor">移动电话：</td>
					<td>${login.phone}</td>
				</tr>
				<tr>
					<td width="10%" height="36" class="leftbor">固定电话：</td>
					<td>${login.tel}</td>
				</tr>
				<tr class="jg">
					<td width="10%" height="36" class="leftbor">电子邮件：</td>
					<td>${login.email}</td>
				</tr>
				<tr>
					<td width="10%" height="36" class="leftbor">联系地址：</td>
					<td>${login.address}</td>
				</tr>
				<tr class="jg">
					<td width="10%" height="36" class="leftbor">邮编：</td>
					<td>${login.zipcode}</td>
				</tr>
				<tr>
					<td width="10%" height="36" class="leftbor">创建时间：</td>
					<td>${login.createtime}</td>
				</tr>
				<tr class="jg">
					<td width="10%" height="36" class="leftbor">更新时间：</td>
					<td>${login.updatetime}</td>
				</tr>
				<tr>
					<td width="10%" height="36" class="leftbor">用户名：</td>
					<td>${login.username}</td>
				</tr>
				<tr class="jg">
					<td width="10%" height="36" class="leftbor">登录IP：</td>
					<td>${login.loginip}</td>
				</tr>

			</table>
			<div class="page">
				<input type="button" value="返回" class="but_shop" onclick="history.back(-1)" />
			</div>
		</div>
	</div>
</body>
</html>
