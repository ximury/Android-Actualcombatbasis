<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../../../commons/taglibs.jsp"%>
<%@ include file="../../../commons/inc.jsp"%>
<table cellpadding="0" cellspacing="0" border="0" class="uiTable">
	<tr>
		<td>字典名称：<span class='fieldReq'>*</span></td>
		<td><input type="text" name="dicValue" id="dicValue" value="${dataDic.dicValue}" class="validate[required,length[0,50]]" /></td>
		<td>业务编码：<span class='fieldReq'>*</span></td>
		<td><input value="${dataDic.busCode}" id="busCode" name="busCode" maxlength="32" class="validate[required,length[0,32]]" /></td>
	</tr>
	<tr>
		<td>字典类别名称：<span class='fieldReq'>*</span></td>
		<td colspan="3">
			<select id="typeId" name="typeId" class="validate[required]">
				<option value="">--请选择--</option>
				<c:forEach var="obj" items="${dicTypeList}" varStatus="vs">
					<option value="${obj.pkId}" <c:if test="${obj.checked}">selected</c:if> >${obj.dicName}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td>备注 ：</td>
		<td colspan="3"><textarea rows="3" cols="60" name="remark">${dataDic.remark}</textarea></td>
	</tr>

</table>