<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../../commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户管理</title>
<%@ include file="../../commons/inc.jsp"%>
<script type="text/javascript">
	function roleRelation(id) {
		var url = "${ctx}/back/login.do?cmd=roleRelationInput&loginId=" + id;
		window
				.showModalDialog(
						url,
						null,
						"dialogwidth:300px;dialogheight:1000px; toolbar=no,top=200,left=200, menubar=no, location=no, status=no");
	}
	function statusChange(id) {
		if (confirm("确定改变用户状态吗？")) {
			$.ajax({
				type : "post",
				url : "${ctx}/back/login.do?cmd=statusChange",
				data : {
					id : id
				},
				success : function(msg) {
					msg = $.parseJSON(msg);
					if (msg == 'success') {
						alert('操作成功！');
						window.location.reload();
					}
				}
			});
		}
	}
	function pwdChange(id){
		if (confirm("您确定要重置密码吗？点击确认继续，点击取消返回")) {
			$.ajax({
				type : "post",
				url : "${ctx}/back/login.do?cmd=pwdChange",
				data : {
					id : id
				},
				success : function(msg) {
					msg = $.parseJSON(msg);
					if (msg == 'success') {
						alert('操作成功！');
					}
				}
			});
		}
		
	}
</script>
</head>
<body>
	<form id="pagedForm" name="pagedForm" method="post" action="${ctx}/back/login.do?cmd=list">
		<div class="main">
			<div class="maintit">
				<h2>
					<img src="${ctx}/backpage/images/tabicon.gif" width="16" height="16" align="absmiddle" /> 用户管理
				</h2>
				<h3>
					<a href="${ctx}/backpage/base/welcome.jsp">主页面 &gt;&gt;</a>
				</h3>
			</div>
			<div class="centent">
				<div class="search">

					<label> 用户名： <input value="${requestScope.login.username}" id="name" name="username" maxlength="50" /></label>
					<label> 姓名： <input value="${requestScope.login.name}" name="name" maxlength="50" /></label>
					<label> 性别：<select name="sex">
							<option value="">--请选择--</option>
							<option value="1" <c:if test="${requestScope.login.sex eq '1'}">selected</c:if>>男</option>
							<option value="0" <c:if test="${requestScope.login.sex eq '0'}">selected</c:if>>女</option>
						</select>
					</label>
					<label> 状态：<select name="enablestate">
							<option value="">--请选择--</option>
							<option value="1" <c:if test="${requestScope.login.enablestate eq '1'}">selected</c:if>>启用</option>
							<option value="0" <c:if test="${requestScope.login.enablestate eq '0'}">selected</c:if>>禁用</option>
						</select>
					</label>					
					<label> <input type="submit" value="检 索" class="btn_search" onMouseOver="this.className='btn_search_over'" onMouseOut="this.className='btn_search'" />
					</label>

				</div>
				<div class="shop" id="shop" style="left: 10px; right: 10px;">
					<p class="left">
						<input type="button" class="but_shop" onclick="addInput('${ctx}/back/login.do?cmd=addInput');" value="添加" />
					</p>
				</div>
				<table cellpadding="0" cellspacing="0" border="0" class="uiTable">
					<tr>
						<th width="50">序号</th>
						<th>用户名</th>
						<th>姓名</th>
						<th>性别</th>
						<th>所在部门</th>
						<th>登录IP</th>
						<th>状态</th>
						<th>上次登录时间</th>
						<th width="300px">操作</th>
					</tr>
					<c:forEach var="obj" items="${list}" varStatus="vs">
						<tr class="${vs.count % 2 == 0 ? 'jg' : ''}">
							<td>${vs.index+1}</td>
							<td>${obj.username}</a></td>
							<td>${obj.name}</td>
							<td><c:if test="${obj.sex eq '1'}">男</c:if> <c:if test="${obj.sex eq '0'}">女</c:if></td>
							<td>${obj.departmentName}</td>
							<td>${obj.loginip}</td>
							<td><c:if test="${obj.enablestate eq '1'}">启用</c:if> <c:if test="${obj.enablestate eq '0'}">禁用</c:if></td>
							<td>${obj.logintime}</td>
							<td>
								<a class="ext-icon-user_edit" style="padding-left: 18px;" href="javascript:editInput('${ctx}/back/login.do?cmd=updateInput&id=${obj.id}');">修改</a> 
								<a class="ext-icon-user_delete" style="padding-left: 18px;" href="javascript:del('${ctx}/back/login.do?cmd=delete&id=${obj.id}');">删除</a> 
								<a class="ext-icon-user_add" style="padding-left: 18px;" href="javascript:roleRelation('${obj.id}')">添加角色</a>
								<a class="ext-icon-user_add" style="padding-left: 18px;" href="javascript:statusChange('${obj.id}')">
									<c:if test="${obj.enablestate eq '1'}">禁用</c:if>
									<c:if test="${obj.enablestate eq '0'}">启用</c:if>
								</a>
								<a class="ext-icon-user_edit" style="padding-left: 18px;" href="javascript:pwdChange('${obj.id}');">重置密码</a> 
							</td>
						</tr>
					</c:forEach>
				</table>
				${page.pagedView}
			</div>
		</div>
	</form>
</body>
</html>
