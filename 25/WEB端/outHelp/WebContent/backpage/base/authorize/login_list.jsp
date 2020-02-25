<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../../commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户授权</title>
<%@ include file="../../commons/inc.jsp"%>
<script type="text/javascript">
	function menuAuthorize(id) {
		var url = "${ctx}/backpage/base/authorize/menu_authorize.jsp?principalid="+id+"&principaltype=login";
		window.showModalDialog(url,null,"dialogwidth:300px;dialogheight:1000px; toolbar=no,top=200,left=200, menubar=no, location=no, status=no");
	}		
</script>
</head>
<body>
	<div class="main">
		<div class="maintit">
			<h2>
				<img src="${ctx}/backpage/images/tabicon.gif" width="16" height="16" align="absmiddle" /> 用户授权
			</h2>
			<h3>
				<a href="${ctx}/backpage/base/welcome.jsp">主页面 &gt;&gt;</a>
			</h3>
		</div>
		<div class="centent">
			<div class="search">
				<form id="queryForm" name="queryForm" method="post" action="${ctx}/back/login.do?cmd=list">
					<label> 用户名： <input value="${login.username}" id="name" name="username" maxlength="100" class="" /></label> <label> <input type="submit" value="检 索" class="btn_search" onMouseOver="this.className='btn_search_over'" onMouseOut="this.className='btn_search'" />
					</label>
				</form>
			</div>
			<table cellpadding="0" cellspacing="0" border="0" class="uiTable">
				<tr>
					<th width="50">序号</th>
					<th>用户名</th>
					<th>姓名</th>
					<th>性别</th>
					<th>登录IP</th>
					<th>状态</th>
					<th>上次登录时间</th>
					<th>操作</th>
				</tr>
				<c:forEach var="obj" items="${list}" varStatus="vs">
					<tr class="${vs.count % 2 == 0 ? 'jg' : ''}">
						<td>${vs.index+1}</td>
						<td>${obj.username}</a></td>
						<td>${obj.name}</td>
						<td>${obj.sex}</td>
						<td>${obj.loginip}</td>
						<td>${obj.enablestate}</td>
						<td>${obj.logintime}</td>
						<td><a class="ext-icon-user_female" style="padding-left:18px;" href="javascript:menuAuthorize('${obj.id}');">菜单授权<a/></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>
