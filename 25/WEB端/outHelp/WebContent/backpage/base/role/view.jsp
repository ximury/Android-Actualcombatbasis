<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>查看角色</title>
<%@ include file="../../commons/taglibs.jsp"%>
<%@ include file="../../commons/inc.jsp"%>
<script type="text/javascript">

	<c:if test="${param.flag eq 'update'}">
		window.parent.updateNode('${role.name}');
	</c:if>
	<c:if test="${param.flag eq 'add'}">
		var obj = new Object();
		obj.name='${role.name}';
		obj.id='${role.id}';
		window.parent.addNode(obj);
	</c:if>	

	function relationLogin() {
		var url = "${ctx}/back/login.do?cmd=relationLoginList&roleId=${role.id}";
		var retValue = window.showModalDialog(url, "关联用户",
				"dialogWidth=800px;dialogHeight=400px");
		if (retValue == true) {
			window.location.reload();
		}
	}

	function menuAuthorize(id) {
		var url = "${ctx}/back/role.do?cmd=menuAuthorizeInput&principalid="
				+ id + "&principaltype=role";
		window
				.showModalDialog(
						url,
						null,
						"dialogwidth:300px;dialogheight:1000px; toolbar=no,top=200,left=200, menubar=no, location=no, status=no");
	}
	
	
	function resourceAuthorize(id) {
		var url = "${ctx}/back/role.do?cmd=resourceAuthorizeInput&principalid="
				+ id + "&principaltype=role";
		window
				.showModalDialog(
						url,
						null,
						"dialogwidth:300px;dialogheight:1000px; toolbar=no,top=200,left=200, menubar=no, location=no, status=no");
	}	
	
	function taskInput(id){
		
		var url = "${ctx}/back/role.do?cmd=taskAuthorizeInput&principalid="
			+ id + "&principaltype=role";
	window
			.showModalDialog(
					url,
					null,
					"dialogwidth:300px;dialogheight:500px; toolbar=no,top=200,left=200, menubar=no, location=no, status=no");

		
	}
	

	function deleteRelation(id) {
		if (confirm("确定删除关联吗？")) {
			var url = "${ctx}/back/login.do?cmd=deleteRelation&ids=" + id;
			var mainFrame = $(window.parent.document).find("#mainFrame");
			mainFrame.attr("src", url);
		}
	}
</script>
</head>
<body>
<form id="pagedForm" name="pagedForm" method="post" action="${ctx}/back/role.do?cmd=find">
	<div class="shop" id="shop">
		<p class="left">
			<input type="button" class="but_shop" onclick="addInput('${ctx}/back/role.do?cmd=addInput');" value="新增角色" /> <input type="button" class="but_shop" onclick="editInput('${ctx}/back/role.do?cmd=updateInput&id=${role.id}');" value="修改" /> <input type="button" class="but_shop" onclick="del('${ctx}/back/role.do?cmd=delete&id=${role.id}');" value="删除" /> <input type="button" class="but_shop" onclick="relationLogin();" value="关联用户" /> <input type="button" class="but_shop" onclick="menuAuthorize('${role.id}')" value="菜单授权" /> <input type="button" class="but_shop" onclick="resourceAuthorize('${role.id}')" value="资源授权" />
		</p>
	</div>
	<!-- 内容开始 -->
	<div class="centent" style="padding-top: 10px;">
		<table cellpadding="0" cellspacing="0" border="0" class="uiTable">
			<tr class="jg">
				<td width="10%" height="36" class="leftbor">角色名称：</td>
				<td>${role.name}</td>
			</tr>
			<tr>
				<td width="10%" height="36" class="leftbor">创建时间：</td>
				<td>${role.createtime}</td>
			</tr>
			<tr class="jg">
				<td width="10%" height="36" class="leftbor">修改时间：</td>
				<td>${role.updatetime}</td>
			</tr>
			<tr>
				<td width="10%" height="36" class="leftbor">描述：</td>
				<td>${role.description}</td>
			</tr>			
		</table>
	</div>
	<div class="shop" id="shop" style="left: 10px; right: 10px;">
		<p class="right">用户列表</p>
		<div class="clear"></div>
	</div>
	<table cellpadding="0" cellspacing="0" border="0" class="uiTable">
		<tr>
			<th width="30"><input type="checkbox" onclick="setAllCheckboxState('items',this.checked)"></th>
			<th width="50">序号</th>
			<th>用户名</th>
			<th>姓名</th>
			<th>性别</th>
			<th>所在部门</th>
			<th>登录IP</th>
			<th>状态</th>
			<th>上次登录时间</th>
			<th>操作</th>
		</tr>
		<c:forEach var="obj" items="${list}" varStatus="vs">
			<tr class="${vs.count % 2 == 0 ? 'jg' : ''}">
				<td><input type="checkbox" name="items" value="id=${obj.id}"></td>
				<td>${vs.index+1}</td>
				<td>${obj.username}</a></td>
				<td>${obj.name}</td>
				<td><c:if test="${obj.sex eq '1'}">男</c:if> <c:if test="${obj.sex eq '0'}">女</c:if></td>
				<td>${obj.departmentName}</td>
				<td>${obj.loginip}</td>
				<td><c:if test="${obj.enablestate eq '1'}">启用</c:if> <c:if test="${obj.enablestate eq '0'}">禁用</c:if></td>
				<td>${obj.logintime}</td>
				<td><a class="ext-icon-user_delete" style="padding-left: 18px;" href="javascript:deleteRelation('${obj.id}');">删除关联<a /></td>
			</tr>
		</c:forEach>
	</table>
	${page.pagedView}
	</form>
</body>
</html>
