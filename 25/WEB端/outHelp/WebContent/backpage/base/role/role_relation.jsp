<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>菜单管理</title>
<%@ include file="../../commons/inc.jsp"%>
<script type="text/javascript">
	 var setting = {
		check: {
			enable: true
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
			url: "${ctx}/back/role.do?cmd=relationLoginList&loginId=${param.loginId}"
		}
	};

	$(document).ready(function() {
		var t = $("#tree");
		t = $.fn.zTree.init(t, setting);
	});

	function relation() {
		var roleIdArray = new Array();
		var zTree = $.fn.zTree.getZTreeObj("tree");
		var nodes = zTree.getCheckedNodes();

		for (var i = 0 ; i < nodes.length; i++) {
			roleIdArray.push(nodes[i].id);
		}
		$.ajax({
			type : "post",
			url : "${ctx}/back/login.do?cmd=relationLogin",
			data : {
				loginIds:'${param.loginId}',
				roleIds:roleIdArray.join(','),
				},
			success : function(msg) {
				msg = $.parseJSON(msg);
				if(msg=='success'){
					alert('关联成功！');
					window.close();
				}
			}
		});		
	}

	function checkAllTrue() {
		var zTree = $.fn.zTree.getZTreeObj("tree");
		zTree.checkAllNodes(true);
	}

	function checkAllFalse() {
		var zTree = $.fn.zTree.getZTreeObj("tree");
		zTree.checkAllNodes(false);
	}
</script>
</head>
<body>
	<!-- 内容开始 -->
	<div class="shop" id="shop">
		<p class="left">
			<input type="button" class="but_shop" onclick="relation();" value="关联" /> <input type="button" class="but_shop" onclick="checkAllTrue();" value="全部选中" /> <input type="button" class="but_shop" onclick="checkAllFalse();" value="全部取消" />
		</p>
	</div>
	<div class="frame-box">
		<ul id="tree" class="ztree" style="overflow: auto;"></ul>
	</div>
</body>
</html>
