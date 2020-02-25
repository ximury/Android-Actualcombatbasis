<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>个人中心</title>
<%@ include file="../../commons/inc.jsp"%>
<%@ include file="../../commons/taglibs.jsp"%>
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
	<div class="main">
		<div class="maintit">
			<h2>
				<img src="${ctx}/backpage/images/tabicon.gif" width="16" height="16" align="absmiddle" /> 修改密码
			</h2>
			<h3>
				<a href="${ctx}/backpage/base/welcome.jsp">主页面 &gt;&gt;</a>
			</h3>
		</div>
		<!-- 内容开始 -->
		<div class="centent" style="padding-top: 10px;">
			<form id="dataform" method="post" action="${ctx}/back/login.do?cmd=password">
				<table cellpadding="0" cellspacing="0" border="0" class="uiTable">
					<tr>
						<td>当前密码：<span class='fieldReq'>*</span></td>
						<td><input type="password" id="password" name="password" class="validate[required,length[0,32]]" /><span style="color:red">${msg}</span></td>
					</tr>
					<tr>
						<td>新密码：<span class='fieldReq'>*</span></td>
						<td><input type="password" id="newpassword" name="newpassword" class="validate[required,length[0,32]]" /></td>
					</tr>
					<tr>
						<td>重复密码：<span class='fieldReq'>*</span></td>
						<td><input type="password" id="rpassword" name="rpassword" class="validate[required,length[0,32]]" /></td>
					</tr>
				</table>
			<div class="page">
				<input type="submit" value="提交" class="but_shop" /> <input type="button" value="取 消" class="but_shop" onclick="history.back(-1)" />
			</div>
			</form>
		</div>
	</div>
</body>
</html>
