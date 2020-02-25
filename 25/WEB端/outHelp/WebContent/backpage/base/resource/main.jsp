<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>资源管理</title>
<%@ include file="../../commons/inc.jsp"%>
<script type="text/javascript">
	var zTree;
	var mainFrame;

	var setting = {
		view : {
			dblClickExpand : false,
			showLine : true,
			selectedMulti : false
		},
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "pid",
				rootPId : ""
			}
		},
		async: {
			enable: true,
			url: "${ctx}/back/menu.do?cmd=tree"
		},		
		callback : {
			onClick: zTreeOnClick
		}
	};

	function zTreeOnClick(event, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("tree");
		if (treeNode.isParent) {
			zTree.expandNode(treeNode);
			return false;
		}else{
			mainFrame.attr("src","${ctx}/back/resource.do?cmd=list&menuid="+treeNode.id);			
		}
	};

	$(document).ready(function() {
		var t = $("#tree");
		mainFrame = $("#mainFrame");
		t = $.fn.zTree.init(t, setting);
	});
</script>
</head>
<body>
	<!-- 内容开始 -->
	<div class="main">
		<div class="maintit">
			<h2>
				<img src="${ctx}/backpage/images/tabicon.gif" width="16" height="16" align="absmiddle" /> 资源管理
			</h2>
			<h3>
				<a href="${ctx}/backpage/base/welcome.jsp">主页面 &gt;&gt;</a>
			</h3>
		</div>
		<div class="frame-box">
			<div class="frame-menu">
				<ul id="tree" class="ztree" style="padding-top:10px;overflow: auto;"></ul>
			</div>
			<iframe class="frame-cen" id="mainFrame" width="86%" height="90%" frameborder="no" border="0" framespacing="0"></iframe>
		</div>
	</div>
</body>
</html>
