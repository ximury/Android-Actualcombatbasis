<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../../../commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>查看岗位</title>
<script src="${ctx}/backpage/scripts/jquery.js" type="text/javascript"></script>
<link href="${ctx}/backpage/styles/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function addPosition() {
		var url = "${ctx}/backpage/base/organization/position/edit.jsp?pid=${position.id}";
		var mainFrame = $(window.parent.document).find("#mainFrame");
		mainFrame.attr("src", url);
	}

	function addPerson() {
		var url = "${ctx}/backpage/base/organization/person/edit.jsp?positionid=${position.id}";
		var mainFrame = $(window.parent.document).find("#mainFrame");
		mainFrame.attr("src", url);
	}

	function editPosition() {
		var url = "${ctx}/back/position.do?cmd=updateInput&id=${position.id}&pid=${position.pid}";
		var mainFrame = $(window.parent.document).find("#mainFrame");
		mainFrame.attr("src", url);
	}

	function deletePosition() {
		var url = "${ctx}/back/position.do?cmd=delete&id=${position.id}";
		var mainFrame = $(window.parent.document).find("#mainFrame");
		mainFrame.attr("src", url);
	}

	function editPerson(id) {
		var url = "${ctx}/back/person.do?cmd=updateInput&id="+id+"&positionid=${position.id}";
		var mainFrame = $(window.parent.document).find("#mainFrame");
		mainFrame.attr("src", url);
	}	

	function deletePerson(id) {
		var url = "${ctx}/back/person.do?cmd=delete&id="+id;
		var mainFrame = $(window.parent.document).find("#mainFrame");
		mainFrame.attr("src", url);
	}

	function detailPerson(id) {
		var url = "${ctx}/back/person.do?cmd=find&id="+id;
		var mainFrame = $(window.parent.document).find("#mainFrame");
		mainFrame.attr("src", url);
	}		
</script>
</head>
<body>

	<div class="shop" id="shop">
		<p class="left">
			<input type="button" class="but_shop" onclick="addPosition();" value="添加子岗位" />
			<input type="button" class="but_shop" onclick="addPerson();" value="添加人员" /> 
			<input type="button" class="but_shop" onclick="editPosition();" value="修改" />
			<input type="button" class="but_shop" onclick="deletePosition();" value="删除" />
		</p>
	</div>
	<!-- 内容开始 -->
	<div class="centent" style="padding-top: 10px;">
		<table cellpadding="0" cellspacing="0" border="0" class="uiTable">
			<tr class="jg">
				<td width="10%" height="26" class="leftbor">岗位名称：</td>
				<td>${position.name}</td>
			</tr>
			<tr>
				<td width="10%" height="26" class="leftbor">岗位标识：</td>
				<td>${position.mark}</td>
			</tr>
			<tr class="jg">
				<td width="10%" height="26" class="leftbor">岗位类型：</td>
				<td>${position.type}</td>
			</tr>
			<tr>
				<td width="10%" height="26" class="leftbor">岗位级别：</td>
				<td>${position.grade}</td>
			</tr>
			<tr class="jg">
				<td width="10%" height="26" class="leftbor">是否领导：</td>
				<td>${position.isleader}</td>
			</tr>
			<tr>
				<td width="10%" height="26" class="leftbor">领导级别：</td>
				<td>${position.leaderlevel}</td>
			</tr>
			<tr class="jg">
				<td width="10%" height="26" class="leftbor">创建时间：</td>
				<td>${position.createtime}</td>
			</tr>
			<tr>
				<td width="10%" height="26" class="leftbor">修改时间：</td>
				<td>${position.updatetime}</td>
			</tr>
			<tr class="jg">
				<td width="10%" height="26" class="leftbor">描述：</td>
				<td>${position.description}</td>
			</tr>
		</table>
	</div>

	<div class="shop" id="shop" style="left: 10px; right: 10px;">
		<p class="right">人员列表</p>
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
			<th>操作</th>
		</tr>
		<c:forEach var="obj" items="${list}" varStatus="vs">
			<tr class="${vs.count % 2 == 0 ? 'jg' : ''}">
				<td>${vs.index}</td>
				<td>${obj.name}</td>
				<td>${obj.sex}</td>
				<td>${obj.tel}</td>
				<td>${obj.address}</td>
				<td>${obj.createtime}</td>
				<td>${obj.updatetime}</td>
				<td>
					<a href="javascript:detailPerson('${obj.id}');">查看</a>
					<a href="javascript:editPerson('${obj.id}');">修改</a>
					<a href="javascript:deletePerson('${obj.id}');">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
