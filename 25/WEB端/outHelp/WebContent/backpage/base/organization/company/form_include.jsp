<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<table cellpadding="0" cellspacing="0" border="0" class="uiTable">
	<tr>
		<td>公司名称：<span class='fieldReq'>*</span></td>
		<td><input type="text" id="name" name="name" value="${company.name}" class="validate[required,length[0,50]]"/></td>
		<td>编号：<span class='fieldReq'>*</span></td>
		<td><input type="text" id="code" name="code" value="${company.code}" class="validate[required,length[0,32]]"/></td>
	</tr>
	<tr>
		<td>公司简称：</td>
		<td><input type="text" name="abbreviation" value="${company.abbreviation}" /></td>
		<td>公司标识：</td>
		<td><input type="text" name="mark" value="${company.mark}" /></td>
	</tr>
	<tr>
		<td>公司类型：</td>
		<td><input type="text" name="type" value="${company.type}" /></td>
		<td>公司级别：</td>
		<td><input type="text" name="grade" value="${company.grade}" /></td>
	</tr>
	<tr>
		<td>所在区域：</td>
		<td><input type="text" name="area" value="${company.area}" /></td>
		<td>联系人：</td>
		<td><input type="text" name="linkman" value="${company.linkman}" /></td>
	</tr>
	<tr>
		<td>联系电话：</td>
		<td><input type="text" name="tel" value="${company.tel}" /></td>
		<td>传真：</td>
		<td><input type="text" name="fax" value="${company.fax}" /></td>

	</tr>
	<tr>
		<td>邮政编码：</td>
		<td><input type="text" name="zipcode" value="${company.zipcode}" /></td>
		<td>地址：</td>
		<td><input type="text" name="address" value="${company.address}" /></td>
	</tr>
	<tr>
		<td>电子邮件：</td>
		<td><input type="text" name="email" value="${company.email}" /></td>
		<td>网站：</td>
		<td><input type="text" name="website" value="${company.website}" /></td>
	</tr>


</table>