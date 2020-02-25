<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>菜单管理</title>
<%@ include file="../../../commons/inc.jsp"%>
<script type="text/javascript">
	 var setting = {
		check: {
			enable: true,
			chkStyle: "radio",
			radioType: "level"
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
			url: "${ctx}/back/party.do?cmd=tree"
		}
	};

	$(document).ready(function() {
		var t = $("#tree");
		t = $.fn.zTree.init(t, setting);
	});

	function confirm() {
		var zTree = $.fn.zTree.getZTreeObj("tree");
		var nodes = zTree.getCheckedNodes(true);
		if (nodes.length == 0) {
			alert("请先选择一个节点");
		}
		window.returnValue = nodes[0];
		window.close();
	}

</script>
</head>
<body>
	<!-- 内容开始 -->
	<div class="shop" id="shop">
		<p class="left">
			<input type="button" class="but_shop" onclick="confirm();" value="确定" /> 
		</p>
	</div>
	<div class="frame-box">
		<ul id="tree" class="ztree" style="overflow: auto;"></ul>
	</div>
</body>
</html>
