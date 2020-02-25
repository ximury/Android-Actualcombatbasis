<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>查看菜单</title>
<%@ include file="../../../commons/inc.jsp"%>
<%@ include file="../../../commons/taglibs.jsp"%>
<script type="text/javascript">
	<c:if test="${param.flag eq 'update'}">
	window.parent.updateNode('${articleColumns.name}');
	</c:if>
	<c:if test="${param.flag eq 'add'}">
	var obj = new Object();
	obj.name = '${articleColumns.name}';
	obj.pkId = '${articleColumns.pkId}';
	window.parent.addNode(obj, '${articleColumns.pid}');
	</c:if>

	function addArticleColumns(flag) {
		var url = '';
		if (flag == 'root') {
			url = "${ctx}/back/articleColumns.do?cmd=addInput";
		} else if (flag == 'sub')
			url = "${ctx}/back/articleColumns.do?cmd=addInput&pid=${articleColumns.pkId}";
		var mainFrame = $(window.parent.document).find("#mainFrame");
		mainFrame.attr("src", url);
	}
</script>
</head>
<body>
	<div class="shop" id="shop">
		<p class="left">
			<input type="button" class="but_shop" onclick="addArticleColumns('sub');" value="添加子栏目" />
			<input type="button" class="but_shop" onclick="addArticleColumns('root');" value="添加根栏目" /> 
			<input type="button" class="but_shop" onclick="editInput('${ctx}/back/articleColumns.do?cmd=updateInput&pkId=${articleColumns.pkId}&pid=${articleColumns.pid}');" value="修改" />
			<input type="button" class="but_shop" onclick="del('${ctx}/back/articleColumns.do?cmd=delete&pkId=${articleColumns.pkId}');" value="删除" />
		</p>
	</div>
	<!-- 内容开始 -->
	<div class="centent" style="padding-top: 10px;">
		<table cellpadding="0" cellspacing="0" border="0" class="uiTable">
			<tr class="jg">
				<td width="10%" height="36" class="leftbor">栏目编码：</td>
				<td>${articleColumns.code}</td>
			</tr>
			<tr>
				<td width="10%" height="36" class="leftbor">栏目名称：</td>
				<td>${articleColumns.name}</td>
			</tr>
			<tr class="jg">
				<td width="10%" height="36" class="leftbor">栏目类型：</td>
				<td><c:if test="${articleColumns.types eq '00'}">多条数据</c:if> <c:if test="${articleColumns.types eq '01'}">单条数据</c:if></td>
			</tr>
			<tr>
				<td width="10%" height="36" class="leftbor">排序：</td>
				<td>${articleColumns.orders}</td>
			</tr>
			<tr class="jg">
				<td width="10%" height="36" class="leftbor">创建时间：</td>
				<td>${articleColumns.createTime}</td>
			</tr>
			<tr>
				<td width="10%" height="36" class="leftbor">修改时间：</td>
				<td>${articleColumns.updateTime}</td>
			</tr>
			<tr class="jg">
				<td width="10%" height="36" class="leftbor">备注：</td>
				<td>${articleColumns.remark}</td>
			</tr>
		</table>
		<div class="page">
			<input type="button" value="返回" class="but_shop" onclick="history.back(-1)" />
		</div>
	</div>
</body>
</html>
