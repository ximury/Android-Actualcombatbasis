<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>查看数据字典信息</title>
<%@ include file="../../../commons/inc.jsp"%>
</head>
<body>
	<div class="main">
		<div class="maintit">
			<h2>
				<img src="${ctx}/backpage/images/tabicon.gif" width="16" height="16" align="absmiddle" /> 查看数据字典信息
			</h2>
			<h3>
				<a href="${ctx}/backpage/base/welcome.jsp">主页面 &gt;&gt;</a>
			</h3>
		</div>
		<!-- 内容开始 -->
		<div class="centent" style="padding-top: 10px;">
			<table cellpadding="0" cellspacing="0" border="0" class="uiTable">
				<tr class="jg">
					<td width="10%" height="18" class="leftbor">字典类别名称：</td>
					<td>${dataDic.dicName}</td>
				</tr>
				<tr>
					<td width="10%" height="18" class="leftbor">字典类别编码：</td>
					<td>${dataDic.typeBusCode}</td>
				</tr>
				<tr class="jg">
					<td width="10%" height="18" class="leftbor">字典名称：</td>
					<td>${dataDic.dicValue}</td>
				</tr>
				<tr>
					<td width="10%" height="18" class="leftbor">业务编码：</td>
					<td>${dataDic.busCode}</td>
				</tr>
				<tr class="jg">
					<td width="10%" height="18" class="leftbor">字典编码：</td>
					<td>${dataDic.dicCode}</td>
				</tr>
				<tr>
					<td width="10%" height="18" class="leftbor">备注 ：</td>
					<td>${dataDic.remark}</td>
				</tr>
				<tr class="jg">
					<td width="10%" height="18" class="leftbor">创建人：</td>
					<td>${dataDic.createId}</td>
				</tr>
				<tr>
					<td width="10%" height="18" class="leftbor">创建时间 ：</td>
					<td>${dataDic.createTime}</td>
				</tr>
				<tr class="jg">
					<td width="10%" height="18" class="leftbor">更新人 ：</td>
					<td>${dataDic.updateId}</td>
				</tr>
				<tr>
					<td width="10%" height="18" class="leftbor">更新时间 ：</td>
					<td>${dataDic.updateTime}</td>
				</tr>
				
			</table>
			<div class="page">
				<input type="button" value="返回" class="but_shop" onclick="history.back(-1)" />
			</div>
		</div>
	</div>
</body>
</html>
