<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<style type="text/css">
	ul{
		padding-top:30px;
		column-width:30%;}
	li{
		list-style:none;
		float:left;
		width:30%;
		color:#FFFFFF;
		font-size:26px;
font-family:微软雅黑;
}
		textarea{
			border:0px;
width:98%;
			
			}
		body{
padding:0px;
			margin:0 auto;
			width:100%;
background:#e3e3e3;
			
		}
			a{
				text-decoration:none;
				color:#4A8FFF;
				}
div{
margin:0px;
}
</style>
<script language="javascript">
function check(){
	var v=form1.content.value;
	if(v==""){
		alert("请输入说说内容！");
		}else{
			alert("输入r的说说内容为："+v);
			form1.submit();
			}
}
</script>
</head>

<body>
    	<div style="height:90px;background:#3d4760;">
        <ul>
        <li>取消</li><li style="text-align:center">写说说</li><li style=" text-align:right"><a href="#" onclick="check()" >发表</a></li>
        </ul>
    
      </div>
  	<form id="form1" name="form1" method="post" action="" onsubmit="return check()">
	  <textarea name="content" id="content"  rows="5" title="写点什么吧"></textarea>
</form>  
<div><img src="bottom2.png" width="100%"  /></div>    

</body>
</html>
