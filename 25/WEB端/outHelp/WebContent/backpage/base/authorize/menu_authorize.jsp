<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>菜单授权</title>
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
			url: "${ctx}/back/acl.do?cmd=authorizationMenu&principalid=${param.principalid}&principaltype=${param.principaltype}"
		}
	};

	$(document).ready(function() {
		var t = $("#tree");
		t = $.fn.zTree.init(t, setting);
	});

	function authorize() {
		var resourceIdArray = new Array();
		var zTree = $.fn.zTree.getZTreeObj("tree");
		var nodes = zTree.getCheckedNodes();

		for (var i = 0 ; i < nodes.length; i++) {
			resourceIdArray.push(nodes[i].id);
		}
		$.ajax({
			type : "post",
			url : "${ctx}/back/acl.do?cmd=save",
			data : {
				principalid:'${param.principalid}',
				resourceid:resourceIdArray.join(','),
				principaltype:'${param.principaltype}',
				resourcetype:'menu'
				},
			success : function(msg) {
				msg = $.parseJSON(msg);
				if(msg=='success'){
					alert('授权成功！');
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
			<input type="button" class="but_shop" onclick="authorize();" value="授权" /> <input type="button" class="but_shop" onclick="checkAllTrue();" value="全部选中" /> <input type="button" class="but_shop" onclick="checkAllFalse();" value="全部取消" />
		</p>
	</div>
	<div class="frame-box">
		<ul id="tree" class="ztree" style="overflow: auto;"></ul>
	</div>
</body>
</html>
