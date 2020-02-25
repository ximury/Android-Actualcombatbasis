<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<table cellpadding="0" cellspacing="0" border="0" class="uiTable">
	<tr>
		<td>用户名：<span class='fieldReq'>*</span></td>
		<td><input type="text" id="username" name="username" value="${requestScope.login.username}" class="validate[required,length[0,50]]" /></td>
		<!--<td>密码：<span class='fieldReq'>*</span></td>
		<td><input type="password" id="password" name="password" value="${requestScope.login.password}" class="validate[required,length[0,32]]" /></td>-->

		<!-- <td>关联人员：</td>
		<td>
			<input type="text" id="personname" disabled="disabled"/><a href="javascript:referencePerson();">参照</a>
			<input type="hidden" id="personid" name="personid"/>
		</td> -->
		<td>部门：<span class='fieldReq'>*</span></td>
		<td><input type="text" id="departmentName" disabled="disabled" value="${requestScope.login.departmentName}" class="validate[required,length[0,50]]" /><a href="javascript:departmentReference();"><span class="ext-icon-user_go" style="padding-left: 18px;">参照</span></a> <input type="hidden" id="departmentId" name="departmentId" value="${requestScope.login.departmentId}" /></td>
	</tr>
	<tr>
		<td>姓名：<span class='fieldReq'>*</span></td>
		<td><input type="text" id="name" name="name" value="${requestScope.login.name}" class="validate[required,length[0,50]]"/></td>
		<td>英文名：</td>
		<td><input type="text" name="engname" value="${requestScope.login.engname}" /></td>
	</tr>
	<tr>
		<td>员工分类：</td>
		<td><input type="text" name="type" value="${requestScope.login.type}" /></td>
		<td>性别：</td>
		<td><select name="sex">
				<option value="">--请选择--</option>
				<option value="1" <c:if test="${requestScope.login.sex eq '1'}">selected</c:if>>男</option>
				<option value="0" <c:if test="${requestScope.login.sex eq '0'}">selected</c:if>>女</option>
		</select></td>
	</tr>
	<tr>
		<td>移动电话：</td>
		<td><input type="text" name="phone" value="${requestScope.login.phone}" /></td>
		<td>固定电话：</td>
		<td><input type="text" name="tel" value="${requestScope.login.tel}" /></td>
	</tr>
	<tr>
		<td>电子邮件：</td>
		<td><input type="text" name="email" value="${requestScope.login.email}" /></td>
		<td>邮编：</td>
		<td><input type="text" name="zipcode" value="${requestScope.login.zipcode}" /></td>
	</tr>
	<tr>
		<td>联系地址：</td>
		<td colspan="3"><input type="text" name="address" value="${requestScope.login.address}" /></td>

	</tr>
</table>