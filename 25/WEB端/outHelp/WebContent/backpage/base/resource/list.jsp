<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../../commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>资源管理</title>
<%@ include file="../../commons/inc.jsp"%>
<script type="text/javascript">
	
</script>
</head>
<body>

	<form id="pagedForm" name="pagedForm" method="post" action="${ctx}/back/resource.do?cmd=list">
		<div class="centent">
			<div class="search">

				<input type="hidden" value="${resource.menuid}" name="menuid" /> <label> 名称： <input value="${resource.name}" id="name" name="name" maxlength="100" class="" /></label> <label> <input type="submit" value="检 索" class="btn_search" onMouseOver="this.className='btn_search_over'" onMouseOut="this.className='btn_search'" />
				</label>

			</div>
			<div class="shop" id="shop" style="left: 10px; right: 10px;">
				<p class="left">
					<input type="button" class="but_shop" onclick="addInput('${ctx}/back/resource.do?cmd=addInput&menuid=${param.menuid}');" value="添加" />
				</p>
			</div>
			<table cellpadding="0" cellspacing="0" border="0" class="uiTable">
				<tr>
					<th width="50">序号</th>
					<th>名称</th>
					<th>路径</th>
					<th>排序</th>
					<th>创建时间</th>
					<th>更新时间</th>
					<th>操作</th>
				</tr>
				<c:forEach var="obj" items="${list}" varStatus="vs">
					<tr class="${vs.count % 2 == 0 ? 'jg' : ''}">
						<td>${vs.index+1}</td>
						<td>${obj.name}</td>
						<td>${obj.path}</td>
						<td>${obj.ordernum}</td>
						<td>${obj.createtime}</td>
						<td>${obj.updatetime}</td>
						<td><a class="ext-icon-user_edit" style="padding-left: 18px;" href="javascript:editInput('${ctx}/back/resource.do?cmd=updateInput&id=${obj.id}&menuid=${obj.menuid}');">修改<a /> <a class="ext-icon-user_delete" style="padding-left: 18px;" href="javascript:del('${ctx}/back/resource.do?cmd=delete&id=${obj.id}');">删除<a /></td>
					</tr>
				</c:forEach>
			</table>
			${page.pagedView}
		</div>
	</form>
</body>
</html>
