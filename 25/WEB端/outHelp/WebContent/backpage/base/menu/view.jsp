<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>查看菜单</title>
<%@ include file="../../commons/inc.jsp"%>
<%@ include file="../../commons/taglibs.jsp"%>
<script type="text/javascript">

	<c:if test="${param.flag eq 'update'}">
		window.parent.updateNode('${menu.name}');
	</c:if>
	<c:if test="${param.flag eq 'add'}">
		var obj = new Object();
		obj.name='${menu.name}';
		obj.id='${menu.id}';
		window.parent.addNode(obj,'${menu.pid}');
	</c:if>	

	function addMenu(flag) {
		var url = '';
		if(flag == 'root'){
			url = "${ctx}/back/menu.do?cmd=addInput";
		}else if(flag == 'sub')
			url = "${ctx}/back/menu.do?cmd=addInput&pid=${menu.id}";
		var mainFrame = $(window.parent.document).find("#mainFrame");
		mainFrame.attr("src",url);
	}
		
</script>
</head>
<body>	
	<div class="shop" id="shop">
		<p class="left">
			<input type="button" class="but_shop" onclick="addMenu('sub');" value="添加子菜单" /> 
			<input type="button" class="but_shop" onclick="addMenu('root');" value="添加根菜单" /> 
			<input type="button" class="but_shop" onclick="editInput('${ctx}/back/menu.do?cmd=updateInput&id=${menu.id}&pid=${menu.pid}');" value="修改" /> 
			<input type="button" class="but_shop" onclick="del('${ctx}/back/menu.do?cmd=delete&id=${menu.id}');" value="删除" />
		</p>
	</div>
	<!-- 内容开始 -->
	<div class="centent" style="padding-top: 10px;">
		<table cellpadding="0" cellspacing="0" border="0" class="uiTable">
			<tr class="jg">
				<td width="10%" height="36" class="leftbor">菜单名称：</td>
				<td>${menu.name}</td>
			</tr>
			<tr>
				<td width="10%" height="36" class="leftbor">路径：</td>
				<td>${menu.path}</td>
			</tr>
			<tr class="jg">
				<td width="10%" height="36" class="leftbor">图标样式：</td>
				<td>${menu.icon}</td>
			</tr>
			<tr>
				<td width="10%" height="36" class="leftbor">排序：</td>
				<td>${menu.ordernum}</td>
			</tr>
			<tr class="jg">
				<td width="10%" height="36" class="leftbor">创建时间：</td>
				<td>${menu.createtime}</td>
			</tr>
			<tr>
				<td width="10%" height="36" class="leftbor">修改时间：</td>
				<td>${menu.updatetime}</td>
			</tr>
		</table>
		<div class="page">
			<input type="button" value="返回" class="but_shop" onclick="history.back(-1)" />
		</div>
	</div>
</body>
</html>
