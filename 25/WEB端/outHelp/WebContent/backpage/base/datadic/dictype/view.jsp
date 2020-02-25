<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>查看字典类型信息</title>
<%@ include file="../../../commons/inc.jsp"%>
<%@ include file="../../../commons/taglibs.jsp"%>
</head>
<body>
	<div class="main">
		<div class="maintit">
			<h2>
				<img src="${ctx}/backpage/images/tabicon.gif" width="16" height="16" align="absmiddle" /> 查看字典类型信息
			</h2>
			<h3>
				<a href="${ctx}/backpage/base/welcome.jsp">主页面 &gt;&gt;</a>
			</h3>
		</div>



		<!-- 内容开始 -->
		<div class="centent" style="padding-top: 10px;">
			<table cellpadding="0" cellspacing="0" border="0" class="uiTable">
				<tr class="jg">
					<td width="10%" height="18" class="leftbor">类别编码：</td>
					<td>${dicType.busCode}</td>
				</tr>
				<tr>
					<td width="10%" height="18" class="leftbor">类别名称：</td>
					<td>${dicType.dicName}</td>
				</tr>
				<tr class="jg">
					<td width="10%" height="18" class="leftbor">字典来源：</td>
					<td><c:if test="${dicType.dicOrigin eq '00'}">内部</c:if> <c:if test="${dicType.dicOrigin eq '01'}">外部SQL</c:if></td>
				</tr>
				<tr>
					<td width="10%" height="18" class="leftbor">数据SQL ：</td>
					<td>${dicType.dicSql}</td>
				</tr>
				<tr>
					<td width="10%" height="18" class="leftbor">是否树形显示：</td>
					<td><c:if test="${dicType.isTree eq '0'}">否</c:if> <c:if test="${dicType.isTree eq '1'}">是</c:if></td>
				</tr>
				<tr class="jg">
					<td width="10%" height="18" class="leftbor">备注 ：</td>
					<td>${dicType.remark}</td>
				</tr>
				<tr>
					<td width="10%" height="18" class="leftbor">创建人：</td>
					<td>${dicType.createId}</td>
				</tr>
				<tr class="jg">
					<td width="10%" height="18" class="leftbor">创建时间 ：</td>
					<td>${dicType.createTime}</td>
				</tr>
				<tr>
					<td width="10%" height="18" class="leftbor">更新人 ：</td>
					<td>${dicType.updateId}</td>
				</tr>
				<tr class="jg">
					<td width="10%" height="18" class="leftbor">更新时间 ：</td>
					<td>${dicType.updateTime}</td>
				</tr>
				
			</table>
			<div class="page">
				<input type="button" value="返回" class="but_shop" onclick="history.back(-1)" />
			</div>
		</div>
	</div>
</body>
</html>
