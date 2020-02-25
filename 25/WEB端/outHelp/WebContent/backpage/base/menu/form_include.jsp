<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<table cellpadding="0" cellspacing="0" border="0" class="uiTable">
	<tr>
		<td>菜单名称：<span class='fieldReq'>*</span></td>
		<td><input type="text"id="name" name="name" value="${menu.name}" class="validate[required,length[0,50]]"/></td>
		<td>路径：</td>
		<td><input type="text" name="path" value="${menu.path}"/></td>
	</tr>
	<tr>
		<td>图标样式：</td>
		<td><input type="text" name="icon" value="${menu.icon}" /></td>
		<td>排序：</td>
		<td><input type="text" name="ordernum" value="${menu.ordernum}" /></td>
	</tr>
</table>