<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>外勤助手</title>
<%@ include file="../commons/taglibs.jsp"%>
<%@ include file="../commons/inc.jsp"%>
<script src="${ctx}/backpage/scripts/formValidator/js/jquery.min.js"
	type="text/javascript"></script>
<link href="${ctx}/backpage/styles/style.css" rel="stylesheet"
	type="text/css" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Nature Sign In Form,Login Forms,Sign up Forms,Registration Forms,News latter Forms,Elements" ./>
<script type="application/x-javascript">
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
</script>
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#loginFrm").validationEngine({
			validationEventTriggers : "keyup blur", //触发的事件  validationEventTriggers:"keyup blur",
			inlineValidation : true,//是否即时验证，false为提交表单时验证,默认true
			//returnIsValid: true,
			success : false,//为true时即使有不符合的也提交表单,false表示只有全部通过验证了才能提交表单,默认false
			promptPosition : "topRight"//,//提示所在的位置，topLeft, topRight, bottomLeft,  centerRight, bottomRight
		});
	});
	//点击切换验证码
	function changeVerifyCode() {
		$("#yzmImg").attr("src",
				"Kaptcha.jpg?" + Math.floor(Math.random() * 100));
	}
</script>

<script type="text/javascript">
	if (top.location != self.location) {
		top.location = self.location;
	}
</script>
</head>
<body onload="allinfo()">


	<span id="show" style="display: none">
	<h2 style="font-family: Georgia; font-weight: bold;" align="center">
			<IMG src="${ctx}/backpage/images/warn.jpg">
			<input type="test" value="" name="showValue" id="showValue" style="background-color: transparent;border: medium none;width: 380px;color: white;" />
	</h2>
	</span>
	
	
	<h1 style="font-family: Georgia; font-weight: bold;">外勤助手</h1>

	
	<div class="app-nature">
		
		<form id="loginFrm" method="post"
			action="<%=contextPath%>/login.do?cmd=login">
			<input type="text" name="username" id="username" autocomplete="off"
				class="text" value="用户名" onFocus="this.value = '';"
				onBlur="if (this.value == '') {this.value = '用户名';}"> <input
				type="password" name="password" id="password" value="Password"
				onFocus="this.value = '';"
				onBlur="if (this.value == '') {this.value = 'Password';}">
			<div class="yanz">
				<input type="text" id="ma" name="verifyCode" autocomplete="off"
					value="验证码" style="float: left;" onFocus="this.value = '';"
					onBlur="if (this.value == '') {this.value = '验证码';}"> <img
					src="Kaptcha.jpg" id="yzmImg" class="matu"
					onclick="changeVerifyCode()" title="刷新">
				<div class="clear"></div>
			</div>
			<div class="submit">
				<input type="submit" value="登 录">
			</div>
			<div class="clear"></div>
			${msg}
		</form>
	</div>
	<div class="copy-right">
		<p></p>
	</div>
</body>
</html>

<script>

	

	function allinfo() {

		var ua = navigator.userAgent;
		//var wa =  window.ActiveXObject;
	
		ua = ua.toLowerCase();
		var match = /(webkit)[ \/]([\w.]+)/.exec(ua)
				|| /(opera)(?:.*version)?[ \/]([\w.]+)/.exec(ua)
				|| /(msie) ([\w.]+)/.exec(ua) 
				|| !/compatible/.test(ua)
				&& /(mozilla)(?:.*? rv:([\w.]+))?/.exec(ua) 
				|| [];

		//如果需要获取浏览器版本号：match[2] 

		switch (match[1]) {
		case "msie": //ie 
			if (parseInt(match[2]) === 6) { //ie6 
				adocument.getElementById("show").style.display = "block"; 
				document.getElementById("showValue").value = "您的浏览器版本为IE6.0, 请升级您的浏览器版本至9.0及以上！";
			} else if (parseInt(match[2]) === 7) { //ie7 
				adocument.getElementById("show").style.display = "block"; 
				document.getElementById("showValue").value = "您的浏览器版本为IE7.0, 请升级您的浏览器版本至9.0及以上！";

			} else if (parseInt(match[2]) === 8) { //ie8 
				document.getElementById("show").style.display = "block"; 
				document.getElementById("showValue").value = "您的浏览器版本为IE8.0, 请升级您的浏览器版本至9.0及以上！";
			}

			break;
		//case "webkit": //safari or chrome 
			//document.getElementById("show").style.display = "block"; 
			//document.getElementById("showValue").value = "建议使用IE浏览器，并且浏览器版本为9.0及以上1！"+match;
			//break;
		//case "opera": //opera 
			//document.getElementById("show").style.display = "block"; 
			//document.getElementById("showValue").value = "建议使用IE浏览器，并且浏览器版本为9.0及以上2！";
			//break;
		//case "mozilla": //Firefox 
			//document.getElementById("show").style.display = "block"; 
			//document.getElementById("showValue").value = "建议使用IE浏览器，并且浏览器版本为9.0及以上3！"+match;
			//break;
		default:
			break;
		}
	}
</script>
