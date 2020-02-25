<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>SSI</title>
<script type="text/javascript" src="<%=contextPath%>/backpage/scripts/jquery.js"></script>
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/backpage/styles/global.css" />
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/backpage/scripts/zTree_v3/css/zTreeStyle/zTreeStyle.css">
<script type="text/javascript" src="<%=contextPath%>/backpage/scripts/zTree_v3/js/jquery.ztree.core-3.5.js"></script>

<style type="text/css">
<!--
html,body {
	height: 100%;
}
-->
</style>

<SCRIPT type="text/javascript">
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
			type:"post",
			url: "<%=contextPath%>/back/menu.do?cmd=tree"
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
			var nodePatch = treeNode.path;
			if(nodePatch.indexOf("?")>0){
				mainFrame.attr("src","<%=contextPath%>" + treeNode.path + "&menuId="+treeNode.id);
			}else{
				mainFrame.attr("src","<%=contextPath%>" + treeNode.path + "?menuId="+treeNode.id);
			}
			
		}
	};

	$(document).ready(function() {
		var t = $("#tree");
		t = $.fn.zTree.init(t, setting);
		mainFrame = $(window.parent.document).find("#mainFrame");
		var zTree = $.fn.zTree.getZTreeObj("tree");
		zTree.expandAll(true);

	});

	function expandAllTrue() {
		var zTree = $.fn.zTree.getZTreeObj("tree");
		zTree.expandAll(true);
	}
</SCRIPT>


<title>.</title>
</head>

<body class="leftbody">
	<div class="left-tyj"></div>
	<div class="left-menu">
		<div style="padding-left: 20px;">
			<ul id="tree" class="ztree" style="overflow: auto;"></ul>
		</div>
	</div>
	<div class="left-dyj"></div>
</body>
</html>

