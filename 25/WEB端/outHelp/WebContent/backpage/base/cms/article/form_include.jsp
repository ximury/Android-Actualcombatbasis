<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../../../commons/taglibs.jsp"%>

<script type="text/javascript">
	$(function() {

		var editor = UE.getEditor('container');

		editor.addListener("ready", function() {
			// editor准备好之后才可以使用
			editor.setContent('${article.content}');
		});		

	});


</script>

<table cellpadding="0" cellspacing="0" border="0" class="uiTable">
	<tr>
		<td>标题：<span class='fieldReq'>*</span></td>
		<td><input type="text" id="name" name="title" style="width:400px;" value="${article.title}" class="validate[required,length[0,50]]" /></td>
	</tr>
	<tr>
		<td>作者：<span class='fieldReq'>*</span></td>
		<td><input type="text" id="author" name="author" style="width:400px;" value="${article.author}" class="validate[required,length[0,50]]" /></td>
	</tr>	
	
	<tr>
		<td>关键字：</td>
		<td><input type="text" name="keywords" style="width:400px;" value="${article.keywords}" /><span class='fieldReq'>两个关键字之间用逗号隔开</span></td>
	</tr>	
	<tr>
	   <td>缩略图：<span class='fieldReq'>*</span></td>
		<td>
				<input type="hidden" id="smallUrl" name="smallUrl" value="${article.smallUrl}"/>
				<c:choose>
					<c:when test="${article.smallUrl==null || article.smallUrl eq ''}">
						<img  src="${ctx}/backpage/images/noPic.png" id="img_b"  width="100" height="150" autosize='yes' pos='0' />
					</c:when>
					<c:otherwise>
						<img  src="${webUrl}${article.smallUrl}" id="img_b"  width="100" height="150" autosize='yes' pos='0' />
					</c:otherwise>
				</c:choose>
				<a class="ext-icon-user_edit" style="padding-left:18px;" href="#"  onclick="addImage('${ctx}/back/item_addImgIndex.do','img_b')">选择图<a />&nbsp;&nbsp; 
				<a class="ext-icon-user_edit" style="padding-left:18px;" href="#"  onclick="delImage()">删除图<a />&nbsp;&nbsp; 
		</td>		
	</tr>
	<tr>
		<td>备注：</td>
		<td><textarea rows="3" cols="60" name="remark">${article.remark}</textarea></td>
	</tr>
	<tr>
		<td colspan="2"><script id="container" name="content" type="text/plain" style="width: 100%; height: 400px;">
		</script></td>
	</tr>

</table>