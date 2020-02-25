<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../../commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户管理</title>
<%@ include file="../../commons/inc.jsp"%>
<script type="text/javascript">
	function relation() {
		var url = "${ctx}/back/login.do?cmd=relationLogin";
		if (!hasOneChecked('items')) {
			alert('请选择要操作的对象!');
			return;
		} else {
			var items = document.getElementsByName('items');
			var itemId = null;
			var count = 0;
			for (var i = 0; i < items.length; i++) {
				if (items[i].checked == true) {
					itemId = items[i].value;
					count++;
				}
			}
			if (count< 1) {
				alert('请选择一项!');
				return;
			} else {
				$.ajax({
					type : "post",
					url : url,
					data : {
						roleIds:'${param.roleId}',
						loginIds:itemId
						},
					success : function(msg) {
						msg = $.parseJSON(msg);
						if(msg == 'success'){
							alert('关联成功！');
							window.returnValue =true;
							window.close();
						}else if(msg =='error'){
							alert('关联失败！');
						}
					}
				});
			}
		}

	}
</script>
</head>
<body>
	<div class="main">
		<div class="maintit">
			<h2>
				<img src="${ctx}/backpage/images/tabicon.gif" width="16" height="16" align="absmiddle" /> 关联用户
			</h2>
		</div>
		<div class="centent">
			<div class="search">
				<form id="queryForm" name="queryForm" method="post" action="${ctx}/back/login.do?cmd=relationLoginList">
					<input type="hidden" name="roleId" value="${param.roleId}"/>
					<label> 用户名： <input value="${login.username}" id="name" name="username" maxlength="100" class="" /></label> <label> <input type="submit" value="检 索" class="btn_search" onMouseOver="this.className='btn_search_over'" onMouseOut="this.className='btn_search'" />
					</label>
				</form>
			</div>
			<div class="shop" id="shop" style="left: 10px; right: 10px;">
				<p class="left">
					<input type="button" class="but_shop" onclick="relation();" value="关联用户" />
				</p>
			</div>
			<table cellpadding="0" cellspacing="0" border="0" class="uiTable">
				<tr>
					<th width="30"><input type="checkbox" onclick="setAllCheckboxState('items',this.checked)" /></th>
					<th width="50">序号</th>
					<th>用户名</th>
					<th>姓名</th>
					<th>性别</th>
					<th>登录IP</th>
					<th>状态</th>
					<th>上次登录时间</th>
				</tr>
				<c:forEach var="obj" items="${list}" varStatus="vs">
					<tr class="${vs.count % 2 == 0 ? 'jg' : ''}">
						<td><input type="checkbox" name="items" value="${obj.id}" /></td>
						<td>${vs.index+1}</td>
						<td>${obj.username}</td>
						<td>${obj.name}</td>
						<td>${obj.sex}</td>
						<td>${obj.loginip}</td>
						<td>${obj.enablestate}</td>
						<td>${obj.logintime}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>
