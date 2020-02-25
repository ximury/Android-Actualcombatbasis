<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();%>
<%String contextPath = request.getContextPath();%>
<%String version = "20140427";%>

<script type="text/javascript">
var dragon = dragon || {};
dragon.contextPath = '<%=contextPath%>';
dragon.basePath = '<%=basePath%>';
dragon.version = '<%=version%>';
</script>

<%-- 引入jQuery --%>
<script type="text/javascript" src="<%=contextPath%>/backpage/scripts/jquery.js"></script>

<%-- 引入my97日期时间控件 --%>
<script type="text/javascript" src="<%=contextPath%>/backpage/scripts/My97DatePicker/WdatePicker.js"></script>

<%-- 引入全局样式 --%>
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/backpage/styles/global.css"/>


<%-- ztree控件--%>
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/backpage/scripts/zTree_v3/css/zTreeStyle/zTreeStyle.css">
<script type="text/javascript" src="<%=contextPath%>/backpage/scripts/zTree_v3/js/jquery.ztree.core-3.5.js"></script>

