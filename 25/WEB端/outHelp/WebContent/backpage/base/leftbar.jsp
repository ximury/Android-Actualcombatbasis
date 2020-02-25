<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%String contextPath = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>SSI</title>
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/backpage/styles/global.css"/>
<style type="text/css">
<!--
html,body {
	height: 100%;
}
-->
</style>
<script type="text/javascript">
	var open = true;
	function OpenAndClose() {
		var obj = window.top.document.getElementById("mainFrameset");
		if (open) {
			obj.cols = "0,10,*";
			open = false;
			document.getElementById("opimg").style.display = "none";
			document.getElementById("climg").style.display = "block";
		} else {
			obj.cols = "226,10,*";
			open = true;
			document.getElementById("opimg").style.display = "block";
			document.getElementById("climg").style.display = "none";
		}
	}
</script>
</head>
<body class="barbody">
	<div class="barpic" onClick="OpenAndClose();">
		<div class="barpict"></div>
		<img id="opimg" src="<%=contextPath%>/backpage/images/leftbar-close.gif" width="10" height="37" alt="关闭左侧菜单" />
		<img id="climg" src="<%=contextPath%>/backpage/images/leftbar-open.gif" width="10" height="37" style="display: none;" alt="打开左侧菜单" />
	</div>
</body>
</html>
