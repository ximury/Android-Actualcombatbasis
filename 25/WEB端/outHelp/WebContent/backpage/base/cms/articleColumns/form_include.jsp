<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../../../commons/taglibs.jsp"%>

<table cellpadding="0" cellspacing="0" border="0" class="uiTable">
	<tr>
		<td>栏目名称：<span class='fieldReq'>*</span></td>
		<td><input type="text"id="name" name="name" value="${articleColumns.name}" class="validate[required,length[0,50]]"/></td>
		<td>栏目类型：<span class='fieldReq'>*</span></td>
		<td>
			<select name="types" id="types" class="validate[required]">
				<option value="">--请选择--</option>
				<option value="00" <c:if test="${articleColumns.types eq '00'}">selected</c:if>>多条数据</option>
				<option value="01" <c:if test="${articleColumns.types eq '01'}">selected</c:if>>单条数据</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>栏目编码：</td>
		<td><input type="text" name="code" value="${articleColumns.code}" /></td>	
		<td>排序：</td>
		<td><input type="text" name="orders" value="${articleColumns.orders}" /></td>
	</tr>
	<tr>
		<td>备注：</td>
		<td colspan="3"><textarea rows="3" cols="60" name="remark">${articleColumns.remark}</textarea></td>
	</tr>	
	
</table>