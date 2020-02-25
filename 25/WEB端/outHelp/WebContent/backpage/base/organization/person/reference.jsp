<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../../../commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户管理</title>
<script src="${ctx}/backpage/scripts/jquery.js" type="text/javascript"></script>
<script src="${ctx}/backpage/scripts/app.js" type="text/javascript"></script>
<link href="${ctx}/backpage/styles/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function reference(id, name) {
		var obj = new Object();
		obj.id = id;
		obj.name = name;
		window.returnValue = obj;
		window.close();
	}
</script>
</head>
<body>
	<div class="main">
		<div class="maintit">
			<h2>
				<img src="${ctx}/backpage/images/tabicon.gif" width="16" height="16" align="absmiddle" /> 参照人员
			</h2>
		</div>
		<div class="centent">
			<div class="search">
				<form id="queryForm" name="queryForm" method="post" action="${ctx}/back/person.do?cmd=list">
					<label> 姓名： <input value="${person.name}" id="name" name="name" maxlength="100" class="" /></label> <label> <input type="submit" value="检 索" class="btn_search" onMouseOver="this.className='btn_search_over'" onMouseOut="this.className='btn_search'" />
					</label>
				</form>
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
						<td><a href="javascript:reference('${obj.id}','${obj.name}')">参照</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>
