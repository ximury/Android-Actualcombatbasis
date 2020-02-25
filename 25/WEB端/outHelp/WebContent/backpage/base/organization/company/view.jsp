<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>查看公司</title>
<%@ include file="../../../commons/inc.jsp"%>
<%@ include file="../../../commons/taglibs.jsp"%>
<script type="text/javascript">
	<c:if test="${param.flag eq 'update'}">
	window.parent.updateNode('${company.name}');
	</c:if>
	<c:if test="${param.flag eq 'add'}">
	var obj = new Object();
	obj.name = '${company.name}';
	obj.discriminate = 'company';
	obj.id = '${company.id}';
	window.parent.addNode(obj, '${company.pid}');
	</c:if>
</script>
</head>
<body>
	<c:if test="${param.setting eq 'true'}">
		<div class="main">
			<div class="maintit">
				<h2>
					<img src="${ctx}/backpage/images/tabicon.gif" width="16" height="16" align="absmiddle" /> 设置公司信息
				</h2>
				<h3>
					<a href="${ctx}/backpage/base/welcome.jsp">主页面 &gt;&gt;</a>
				</h3>
			</div>
	</c:if>
	<c:if test="${param.setting ne 'true'}">
		<div class="shop" id="shop">
			<p class="left">
				<input type="button" class="but_shop" onclick="addInput('${ctx}/back/company.do?cmd=addInput&pid=${company.id}');" value="添加子公司" /> <input type="button" class="but_shop" onclick="addInput('${ctx}/back/department.do?cmd=addInput&pid=${company.id}');" value="添加部门" /> <input type="button" class="but_shop" onclick="editInput('${ctx}/back/company.do?cmd=updateInput&id=${company.id}&pid=${company.pid}');" value="修改" /> <input type="button" class="but_shop" onclick="del('${ctx}/back/company.do?cmd=delete&id=${company.id}');" value="删除" />
			</p>
		</div>
	</c:if>
	<!-- 内容开始 -->
	<div class="centent" style="padding-top: 10px;">
		<table cellpadding="0" cellspacing="0" border="0" class="uiTable">
			<tr class="jg">
				<td width="10%" height="36" class="leftbor">公司名称：</td>
				<td>${company.name}</td>
			</tr>
			<tr>
				<td width="10%" height="36" class="leftbor">编号：</td>
				<td>${company.code}</td>
			</tr>
			<tr class="jg">
				<td width="10%" height="36" class="leftbor">公司简称：</td>
				<td>${company.abbreviation}</td>
			</tr>
			<tr>
				<td width="10%" height="36" class="leftbor">公司标识：</td>
				<td>${company.mark}</td>
			</tr>
			<tr class="jg">
				<td width="10%" height="36" class="leftbor">公司类型：</td>
				<td>${company.type}</td>
			</tr>
			<tr>
				<td width="10%" height="36" class="leftbor">公司级别：</td>
				<td>${company.grade}</td>
			</tr>
			<tr class="jg">
				<td width="10%" height="36" class="leftbor">所在区域：</td>
				<td>${company.area}</td>
			</tr>
			<tr>
				<td width="10%" height="36" class="leftbor">联系人：</td>
				<td>${company.linkman}</td>
			</tr>
			<tr>
				<td width="10%" height="36" class="leftbor">联系电话：</td>
				<td>${company.tel}</td>
			</tr>
			<tr>
				<td width="10%" height="36" class="leftbor">传真：</td>
				<td>${company.fax}</td>
			</tr>
			<tr>
				<td width="10%" height="36" class="leftbor">邮编：</td>
				<td>${company.zipcode}</td>
			</tr>
			<tr>
				<td width="10%" height="36" class="leftbor">地址：</td>
				<td>${company.address}</td>
			</tr>
			<tr>
				<td width="10%" height="36" class="leftbor">电子邮件：</td>
				<td>${company.email}</td>
			</tr>
			<tr>
				<td width="10%" height="36" class="leftbor">网址：</td>
				<td>${company.website}</td>
			</tr>
			<tr>
				<td width="10%" height="36" class="leftbor">创建时间：</td>
				<td>${company.createtime}</td>
			</tr>
			<tr>
				<td width="10%" height="36" class="leftbor">修改时间：</td>
				<td>${company.updatetime}</td>
			</tr>
			<tr>
				<td width="10%" height="36" class="leftbor">描述：</td>
				<td>${company.description}</td>
			</tr>
		</table>
		<div class="page">
			<input type="button" value="返回" class="but_shop" onclick="history.back(-1)" />
		</div>
	</div>
	<c:if test="${param.setting eq 'true'}">
		</div>
	</c:if>
</body>
</html>
