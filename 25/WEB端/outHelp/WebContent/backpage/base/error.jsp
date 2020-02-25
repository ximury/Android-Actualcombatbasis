<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%String contextPath = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>消息提示</title>
<%@ include file="../commons/taglibs.jsp"%>
<script src="${ctx}/backpage/scripts/formValidator/js/jquery.min.js" type="text/javascript"></script>
<link href="${ctx}/backpage/styles/style.css" rel="stylesheet" type="text/css" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Nature Sign In Form,Login Forms,Sign up Forms,Registration Forms,News latter Forms,Elements"./>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
</script>
<script type="text/javascript">
	var i = 20; 
	var intervalid; 
	intervalid = setInterval("fun()", 1000); 
	function fun() { 
		if (i == 0) { 
			//window.location.href = "../index.html"; 
			clearInterval(intervalid); 
			
			window.returnValue='refresh';window.close();
		} 
		$("#mes").html(i) ; 
		i--; 
	} 
</script>	


</head>
<body style="background:url(${ctx}/backpage/images/85.jpg) repeat scroll 0 0 / cover ">
		<div class="app-nature" style="width:700px; margin-top:15%;">
			<div class="nature" style="background:none; padding-left:10px; border:2px solid #FC6;">
				<h1 style="line-height:128px;font-size:30px;">
					<img src="${ctx}/backpage/images/User2.png" style=" float:left;"></img>	对不起操作失败，请与管理员联系！
					<br/>
					【失败原因：${error}】<br/>
					<span id="mes">20</span>秒后自动关闭
					
				</h1>
			
			</div>
		</div>
</body>
</html>
