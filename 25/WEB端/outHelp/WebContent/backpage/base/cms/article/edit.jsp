<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>新增/修改 文章</title>
<%@ include file="../../../commons/inc.jsp"%>
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

	function  addImage(url,id){
		makeNewWindow(url+"?id="+id);
	}

	var newWindow;//定义一个窗口，有利于窗口间的通讯
	function makeNewWindow(url) {
		if (!newWindow || newWindow.closed) {
			var width = 380;
		    var height = 500;
		    var left = parseInt((screen.availWidth/2) - (width/2));//屏幕居中
		    var top = parseInt((screen.availHeight/2) - (height/2));
		    var windowFeatures = "width=" + width + ",height=" + height + ",status,resizable,left=" + left + ",top=" + top + ",screenX=" + left + ",screenY=" + top +",scrollbars=yes";
		    newWindow = window.open(url, "subWind", windowFeatures);
		 } else {
		   // window is already open, so bring it to the front
		    newWindow.focus();
		}
	}	


	function viweAddImage(url){
		$("#img_b").attr("src",url);
		$("#smallUrl").val(url);
	}	

	function delImage(){
		if(confirm("您确定要删除改图片么? 点击确认继续，点击取消退出")){
			$("#img_b").attr("src",'${ctx}/backpage/images/noPic.png');
			$("#smallUrl").val('');
		 }
	}	


	
</script>
</head>
<body>

	<div class="main">

		<div class="maintit">
			<h2>
				<img src="${ctx}/backpage/images/tabicon.gif" width="16" height="16" align="absmiddle" /> 新增/修改文章
			</h2>
			<h3>
				<a href="${ctx}/backpage/base/welcome.jsp">主页面 &gt;&gt;</a>
			</h3>
		</div>

		<!-- 内容开始 -->
		<div class="centent" style="padding-top: 10px;">
			<form id="dataform" method="post" action="${ctx}/back/article.do?cmd=saveOrUpdate">
				<span> <input type="hidden" name="pkId" value="${article.pkId}"> <input type="hidden" name="columnsId" value="${param.columnsId}">
				</span>
				<%@ include file="form_include.jsp"%>
				<div class="page">
					<input type="submit" value="提交" class="but_shop" /> <input type="button" value="取 消" class="but_shop" onclick="history.back(-1)" />
				</div>
			</form>
		</div>
	</div>
</body>
</html>
