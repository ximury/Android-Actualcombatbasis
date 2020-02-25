<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../../../commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>数据字典管理</title>
<%@ include file="../../../commons/inc.jsp"%>
</head>
<body>
	<form id="pagedForm" name="pagedForm" method="post" action="${ctx}/back/dataDic.do?cmd=list">
		<div class="main">
			<div class="maintit">
				<h2>
					<img src="${ctx}/backpage/images/tabicon.gif" width="16" height="16" align="absmiddle" /> 数据字典管理
				</h2>
				<h3>
					<a href="${ctx}/backpage/base/welcome.jsp">主页面 &gt;&gt;</a>
				</h3>
			</div>
			<div class="centent">
				<div class="search">
					<label> 字典类别名称： <input value="${dataDic.dicName}" id="dicName" name="dicName" maxlength="100" class="" /></label> <label> 业务编码： <input value="${dataDic.busCode}" id="busCode" name="busCode" maxlength="100" class="" /></label> <label> <input type="submit" value="检 索" class="btn_search" onMouseOver="this.className='btn_search_over'" onMouseOut="this.className='btn_search'" /></label>
				</div>
				<div class="shop" id="shop" style="left: 10px; right: 10px;">
					<p class="left">
						<input type="button" class="but_shop" onclick="addInput('${ctx}/back/dataDic.do?cmd=addInput');" value="添加" />
					</p>
				</div>
				<table cellpadding="0" cellspacing="0" border="0" class="uiTable">
					<tr>
						<th width="50">序号</th>
						<th>字典类别名称</th>
						<th>字典类别编码</th>
						<th>字典名称</th>
						<th>业务编码</th>
						<th>操作</th>
					</tr>
					<c:forEach var="obj" items="${list}" varStatus="vs">
						<tr class="${vs.count % 2 == 0 ? 'jg' : ''}">
							<td>${vs.index+1}</td>
							<td>${obj.dicName}</a></td>
							<td>${obj.typeBusCode}</td>
							<td>${obj.dicValue}</td>
							<td>${obj.busCode}</td>
							<td><a class="ext-icon-user_edit" style="padding-left: 18px;" href="javascript:editInput('${ctx}/back/dataDic.do?cmd=updateInput&id=${obj.id}');">修改<a /> <a class="ext-icon-user_delete" style="padding-left: 18px;" href="javascript:del('${ctx}/back/dataDic.do?cmd=delete&id=${obj.id}');">删除<a /> <a class="ext-icon-user" style="padding-left: 18px;" href="javascript:view('${ctx}/back/dataDic.do?cmd=find&id=${obj.id}');">查看<a /></td>
						</tr>
					</c:forEach>
				</table>
				${page.pagedView}
			</div>
		</div>
	</form>
</body>
</html>
