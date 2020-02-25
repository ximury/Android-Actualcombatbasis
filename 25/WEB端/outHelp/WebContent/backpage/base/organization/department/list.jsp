<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>部门管理</title>
<link href="<%=contextPath%>/backpage/styles/main.css" rel="stylesheet" type="text/css" />
<script src="<%=contextPath%>/backpage/scripts/jquery.js" type="text/javascript"/>
</head>
<body>
	<div class="search">
		<form id="queryForm" name="queryForm" method="post" action="<%=contextPath%>/back/company.do?cmd=simpleQuery">
			<label> 名称： <input type="text" name="name" value="${department.name}" /></label> 
			<label> 标识： <input type="text" name="code" value="${department.mark}" /></label> 
			<label> <input type="submit" value="检 索" class="btn_search" onMouseOver="this.className='btn_search_over'" onMouseOut="this.className='btn_search'" /></label>
		</form>
	</div>
	<div class="shop" id="shop">
		<p class="left">
			<input type="button" class="but_shop" onclick="addCompany('${ctx}/demo/Person!add.do',document.listForm);" value="添加子公司" />
			<input type="button" class="but_shop" onclick="add('${ctx}/demo/Person!add.do',document.listForm);" value="添加部门" /> 
			<input type="button" class="but_shop" onclick="edit('${ctx}/demo/Person!edit.do','items',document.listForm);" value="修改" /> 
			<input type="button" class="but_shop" onclick="batchDelete('${ctx}/demo/Person!remove.do','items',document.listForm);" value="删除" />
		</p>
		<p class="right">
			<select name="sortColumns" id="sortColumns" class="font12" onchange="sortColumns(this.value,document._pageForm_)">
				<option value="ts">--默认排序--</option>
			</select>
		</p>
	</div>
	<table cellpadding="0" cellspacing="0" border="0" class="uiTable">
		<tr>
			<th width="30"><input type="checkbox" onclick="setAllCheckboxState('items',this.checked)"></th>
			<th width="50">序号</th>
			<th>名称</th>
			<th>标识</th>
			<th>电话</th>
			<th>创建时间</th>
			<th>修改时间</th>
		</tr>
	</table>
</body>
</html>
