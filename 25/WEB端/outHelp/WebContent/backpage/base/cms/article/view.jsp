<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>查看菜单</title>
<%@ include file="../../../commons/inc.jsp"%>
<%@ include file="../../../commons/taglibs.jsp"%>
<script type="text/javascript">
	function addArticle() {
		url = "${ctx}/back/article.do?cmd=addInput&columnsId=${article.columnsId}";
		var mainFrame = $(window.parent.parent.document).find("#mainFrame");
		mainFrame.attr("src", url);
	}

	function editArticle(pkId) {
		url = "${ctx}/back/article.do?cmd=updateInput&columnsId=${article.columnsId}&pkId=" + pkId;
		var mainFrame = $(window.parent.parent.document).find("#mainFrame");
		mainFrame.attr("src", url);
	}

	function publishArticle(pkId, status, msg1, msg2) {
		if (confirm(msg1)) {
			jQuery.ajax({
				type : "POST",
				url : "${ctx}/back/article.do?cmd=update",
				data : {
					"pkId" : pkId,
					"status" : status
				},
				success : function(data) {
					alert(msg2);
					window.location.reload();
				},
				error : function(XMLResponse) {
					alert('error:' + arguments[1]);
				}
			});
		}
	}
</script>
</head>
<body>
	<div class="shop" id="shop">
		<p class="left">
			<input type="button" class="but_shop" onclick="addArticle();" value="添加文章" />
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
		</table>
	</div>
	<div class="shop" id="shop" style="left: 10px; right: 10px;">
		<p class="right">文章列表</p>
		<div class="clear"></div>
	</div>
	<table cellpadding="0" cellspacing="0" border="0" class="uiTable">
		<tr>
			<th width="50">序号</th>
			<th>标题</th>
			<th>作者</th>
			<th>创建时间</th>
			<th>更新时间</th>
			<th>状态</th>
			<th>操作</th>
		</tr>
		<c:forEach var="obj" items="${list}" varStatus="vs">
			<tr class="${vs.count % 2 == 0 ? 'jg' : ''}">
				<td>${vs.index+1}</td>
				<td>${obj.title}</td>
				<td>${obj.author}</td>
				<td>${obj.createTime}</td>
				<td>${obj.updateTime}</td>
				<td>${obj.statusMc}</td>
				<td><c:if test="${obj.status eq '00'}">
						<a class="ext-icon-user_add" style="padding-left: 18px;" href="javascript:publishArticle('${obj.pkId}','01','请确认要发布吗？','发布成功！');">发布</a>
						<a class="ext-icon-user_edit" style="padding-left: 18px;" href="javascript:editArticle('${obj.pkId}');">编辑</a>
						<a class="ext-icon-user_delete" style="padding-left: 18px;" href="javascript:del('${ctx}/back/article.do?cmd=delete&columnsId=${article.columnsId}&pkId=${obj.pkId}');">删除</a>
					</c:if> <c:if test="${obj.status eq '01'}">
						<a class="ext-icon-user_delete" style="padding-left: 18px;" href="javascript:publishArticle('${obj.pkId}','00','请确认要取消发布吗？','取消发布成功！');">取消发布</a>
					</c:if></td>
			</tr>
		</c:forEach>
	</table>
	${page.pagedView}
</body>
</html>
