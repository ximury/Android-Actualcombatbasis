<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<table cellpadding="0" cellspacing="0" border="0" class="uiTable">
	<tr>
		<td>岗位名称：</td>
		<td><input type="text" name="name" value="${position.name}" /></td>
		<td>岗位标识：</td>
		<td><input type="text" name="mark" value="${position.mark}" /></td>
	</tr>
	<tr>
		<td>岗位类型：</td>
		<td><input type="text" name="type" value="${position.type}" /></td>
		<td>岗位级别：</td>
		<td><input type="text" name="grade" value="${position.grade}" /></td>
	</tr>
	<tr>
		<td>是否领导：</td>
		<td><input type="text" name="isleader" value="${position.isleader}" /></td>
		<td>领导级别：</td>
		<td><input type="text" name="leaderlevel" value="${position.leaderlevel}" /></td>		
	</tr>
	
	<tr>
		<td>描述：</td>
		<td colspan="3"><textarea rows="3" cols="60" name="description">${position.description}</textarea></td>
	</tr>
</table>