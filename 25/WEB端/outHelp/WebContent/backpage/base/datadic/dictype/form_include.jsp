<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../../../commons/taglibs.jsp"%>
<table cellpadding="0" cellspacing="0" border="0" class="uiTable">
	<tr>
		<td>类别编码：<span class='fieldReq'>*</span></td>
		<td><input type="text" name="busCode" id="busCode" value="${dicType.busCode}" class="validate[required,length[0,32]]" /></td>
		<td>类别名称：<span class='fieldReq'>*</span></td>
		<td><input type="text" name="dicName" id="dicName" value="${dicType.dicName}" class="validate[required,length[0,32]]" /></td>
	</tr>

	<tr>
		<td>字典来源：</td>
		<td><input type="radio" name="dicOrigin" value="00"  <c:if test="${dicType.dicOrigin eq '00'}">checked</c:if>>内部 <input type="radio" name="dicOrigin" value="01" <c:if test="${dicType.dicOrigin eq '01'}">checked</c:if>>外部SQL</td>
		<td>是否树形显示：</td>
		<td><input type="radio" name="isTree" value="0" <c:if test="${dicType.isTree eq '0'}">checked</c:if>>否 <input type="radio" name="isTree" value="1" <c:if test="${dicType.isTree eq '1'}">checked</c:if>>是</td>
	</tr>
	<tr>
		<td>数据SQL ：</td>
		<td colspan="3"><textarea name="dicSql" cols="130">${dicType.dicSql}</textarea></td>
	</tr>
	<tr>
		<td>备注 ：</td>
		<td colspan="3"><textarea name="remark" cols="130">${dicType.remark}</textarea></td>
	</tr>
</table>