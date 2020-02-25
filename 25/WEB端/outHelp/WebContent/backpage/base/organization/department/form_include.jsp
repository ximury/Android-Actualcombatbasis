<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<table cellpadding="0" cellspacing="0" border="0" class="uiTable">
	<tr>
		<td>部门名称：<span class='fieldReq'>*</span></td>
		<td><input type="text" id="name" name="name" value="${department.name}" class="validate[required,length[0,50]]"/></td>
		<td>部门标识：<span class='fieldReq'>*</span></td>
		<td><input type="text" id="mark" name="mark" value="${department.mark}" class="validate[required,length[0,50]]"/></td>
	</tr>
	<tr>
		<td>部门类型：</td>
		<td><input type="text" name="type" value="${department.type}" /></td>
		<td>部门级别：</td>
		<td><input type="text" name="grade" value="${department.grade}" /></td>
	</tr>
	<tr>
		<td>联系人：</td>
		<td colspan="3"><input type="text" name="linkman" value="${department.linkman}" /></td>
	</tr>
	<tr>
		<td>联系人经历：</td>
		<td colspan="3"><textarea rows="3" cols="60" name="linkmanexp">${department.linkmanexp}</textarea></td>			
	</tr>	
	<tr>
		<td>描述：</td>
		<td colspan="3"><textarea rows="3" cols="60" name="description">${department.description}</textarea></td>
	</tr>
</table>