<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<table cellpadding="0" cellspacing="0" border="0" class="uiTable">
	<tr>
		<td>资源名称：<span class='fieldReq'>*</span></td>
		<td><input type="text"id="name" name="name" value="${resource.name}" class="validate[required,length[0,50]]"/></td>
		<td>路径：</td>
		<td><input type="text" name="path" value="${resource.path}"/></td>
	</tr>
	<tr>
		<td>类型：<span class='fieldReq'>*</span></td>
		<td>
			<select name="type" id="type" class="validate[required]">
				<option value="">--请选择--</option>
				<option value="0" <c:if test="${resource.type eq '0'}">selected</c:if>>列表按钮</option>
				<option value="1" <c:if test="${resource.type eq '1'}">selected</c:if>>功能按钮</option>
			</select>		
		</td>
		<td>执行方式：<span class='fieldReq'>*</span></td>
		<td>
			<select name="exemode" id="exemode" class="validate[required]">
				<option value="">--请选择--</option>
				<option value="0" <c:if test="${resource.exemode eq '0'}">selected</c:if>>直接执行</option>
				<option value="1" <c:if test="${resource.exemode eq '1'}">selected</c:if>>通过js执行</option>
			</select>		
		</td>
	</tr>	
	<tr>
		<td>排序：</td>
		<td><input type="text" name="ordernum" value="${resource.ordernum}" /></td>
		<td>图标样式：</td>
		<td><input type="text" name="classstyle" value="${resource.classstyle}" /></td>		
	</tr>
	
	<tr>
		<td>备注：</td>
		<td colspan="3"><textarea rows="3" cols="60" name="remark">${resource.remark}</textarea></td>
	</tr>	
</table>