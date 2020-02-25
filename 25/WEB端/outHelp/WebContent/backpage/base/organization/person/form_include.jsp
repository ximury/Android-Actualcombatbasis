<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<table cellpadding="0" cellspacing="0" border="0" class="uiTable">
	<tr>
		<td>姓名：</td>
		<td><input type="text" name="name" value="${person.name}" /></td>
		<td>英文名：</td>
		<td><input type="text" name="engname" value="${person.engname}" /></td>
	</tr>
	<tr>
		<td>员工分类：</td>
		<td><input type="text" name="type" value="${person.type}" /></td>
		<td>性别：</td>
		<td><input type="text" name="sex" value="${person.sex}" /></td>
	</tr>
	<tr>
		<td>移动电话：</td>
		<td><input type="text" name="phone" value="${person.phone}" /></td>
		<td>固定电话：</td>
		<td><input type="text" name="tel" value="${person.tel}" /></td>		
	</tr>
	<tr>
		<td>电子邮件：</td>
		<td><input type="text" name="email" value="${person.email}" /></td>
		<td>邮编：</td>
		<td><input type="text" name="zipcode" value="${person.zipcode}" /></td>		
	</tr>
	<tr>
		<td>联系地址：</td>
		<td><input type="text" name="address" value="${person.address}" /></td>
	</tr>		
	
</table>