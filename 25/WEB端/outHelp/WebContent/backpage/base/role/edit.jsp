<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>新增/修改角色信息</title>
<%@ include file="../../commons/inc.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("#dataform").validationEngine({
			validationEventTriggers : "keyup blur", //触发的事件  validationEventTriggers:"keyup blur",
			inlineValidation : true,//是否即时验证，false为提交表单时验证,默认true
			//returnIsValid: true,
			success : false,//为true时即使有不符合的也提交表单,false表示只有全部通过验证了才能提交表单,默认false
			promptPosition : "topRight"//,//提示所在的位置，topLeft, topRight, bottomLeft,  centerRight, bottomRight
		});
	});
</script>
</head>
<body>
	<!-- 内容开始 -->
	<div class="centent" style="padding-top: 10px;">
		<form id="dataform" method="post" action="${ctx}/back/role.do?cmd=saveOrUpdate">
			<span> <input type="hidden" name="id" value="${role.id}">
			</span>
			<table cellpadding="0" cellspacing="0" border="0" class="uiTable">
				<tr>
					<td>角色名称：<span class='fieldReq'>*</span></td>
					<td colspan="3"><input type="text" id="name" name="name" value="${role.name}" class="validate[required,length[0,50]]" /></td>
				</tr>
				<tr>
					<td>描述：</td>
					<td colspan="3"><textarea rows="3" cols="60" name="description">${role.description}</textarea></td>
				</tr>
			</table>
			<div class="page">
				<input type="submit" value="提交" class="but_shop" /> <input type="button" value="取 消" class="but_shop" onclick="history.back(-1)" />
			</div>
		</form>
	</div>
</body>
</html>
