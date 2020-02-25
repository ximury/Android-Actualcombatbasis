<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<title>Success Page</title>
<%@ include file="../commons/inc.jsp"%>
<%@ include file="../commons/taglibs.jsp"%>
<script type="text/javascript">
	<c:if test="${flag eq 'remove'}">
	window.parent.removeNode();
	</c:if>
</script>
</head>

<body>

	<div id="content">
		<img alt="system internal error" src="${ctx}/backpage/images/success.gif" style="padding-top:30px;"/>
		<h3>
			${msg}<br />
		</h3>
	</div>
</body>
</html>