<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>查看部门</title>
<%@ include file="../../../commons/inc.jsp"%>
<%@ include file="../../../commons/taglibs.jsp"%>
<script type="text/javascript">
	<c:if test="${param.flag eq 'update'}">
		window.parent.updateNode('${department.name}');
	</c:if>
	<c:if test="${param.flag eq 'add'}">
		var obj = new Object();
		obj.name='${department.name}';
		obj.discriminate='department';
		obj.id='${department.id}';
		window.parent.addNode(obj,'${department.pid}');
	</c:if>	
</script>
</head>
<body>
<form id="pagedForm" name="pagedForm" method="post" action="${ctx}/back/department.do?cmd=find">
	<div class="shop" id="shop">
		<p class="left">
			<input type="button" class="but_shop" onclick="addInput('${ctx}/back/department.do?cmd=addInput&pid=${department.id}');" value="添加子部门" /> <!-- <input type="button" class="but_shop" onclick="addPosition();" value="添加岗位" />  --> 
			<input type="button" class="but_shop" onclick="editInput('${ctx}/back/department.do?cmd=updateInput&id=${department.id}&pid=${department.pid}')" value="修改" />
			<input type="button" class="but_shop" onclick="del('${ctx}/back/department.do?cmd=delete&id=${department.id}')" value="删除" />
		</p>
	</div>
	<!-- 内容开始 -->
	<div class="centent" style="padding-top: 10px;">
		<table cellpadding="0" cellspacing="0" border="0" class="uiTable">
			<tr class="jg">
				<td width="10%" height="36" class="leftbor">部门名称：</td>
				<td>${department.name}</td>
			</tr>
			<tr>
				<td width="10%" height="36" class="leftbor">部门标识：</td>
				<td>${department.mark}</td>
			</tr>
			<tr class="jg">
				<td width="10%" height="36" class="leftbor">部门类型：</td>
				<td>${department.type}</td>
			</tr>
			<tr>
				<td width="10%" height="36" class="leftbor">部门级别：</td>
				<td>${department.grade}</td>
			</tr>
			<tr class="jg">
				<td width="10%" height="36" class="leftbor">联系人：</td>
				<td>${department.linkman}</td>
			</tr>
			<tr>
				<td width="10%" height="36" class="leftbor">联系人经历：</td>
				<td>${department.linkmanexp}</td>
			</tr>			
			<tr class="jg">
				<td width="10%" height="36" class="leftbor">创建时间：</td>
				<td>${department.createtime}</td>
			</tr>
			<tr>
				<td width="10%" height="36" class="leftbor">修改时间：</td>
				<td>${department.updatetime}</td>
			</tr>
			<tr class="jg">
				<td width="10%" height="36" class="leftbor">描述：</td>
				<td>${department.description}</td>
			</tr>
		</table>
		
	<div class="shop" id="shop" style="left: 10px; right: 10px;">
		<p class="left">人员列表</p>
		<div class="clear"></div>
	</div>

	<table cellpadding="0" cellspacing="0" border="0" class="uiTable">
		<tr>
			<th width="50">序号</th>
			<th>姓名</th>
			<th>性别</th>
			<th>电话</th>
			<th>地址</th>
			<th>创建时间</th>
			<th>修改时间</th>
		</tr>
		<c:forEach var="obj" items="${list}" varStatus="vs">
			<tr class="${vs.count % 2 == 0 ? 'jg' : ''}">
				<td>${vs.index+1}</td>
				<td>${obj.name}</td>
				<td><c:if test="${obj.sex eq '1'}">男</c:if> <c:if test="${obj.sex eq '0'}">女</c:if></td>
				<td>${obj.tel}</td>
				<td>${obj.address}</td>
				<td>${obj.createtime}</td>
				<td>${obj.updatetime}</td>
			</tr>
		</c:forEach>
	</table>
	${page.pagedView}		
		<div class="page">
			<input type="button" value="返回" class="but_shop" onclick="history.back(-1)" />
		</div>
	</div>
	</form>
</body>
</html>
