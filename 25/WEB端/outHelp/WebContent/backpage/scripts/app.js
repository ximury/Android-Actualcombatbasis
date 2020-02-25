function disableSubmit(finalResult, submitButtonId) {
	if (finalResult) {
		document.getElementById(submitButtonId).disabled = true;
		return finalResult;
	} else {
		return finalResult;
	}
}

// 批量删除
function batchDelete(action, checkboxName, form) {

}

// 删除
function del(url) {
	if (confirm("确定删除数据吗？点击确认按钮继续！")) {
		var mainFrame = $(window.parent.document).find("#mainFrame");
		mainFrame.attr("src", url);
	}
}

var  delMess = function (action){
	
	if (confirm("确定删除数据吗？点击确认按钮继续！")) {
		
		document.pagedForm.action = action;
		document.pagedForm.submit();
		
	}
	
}

// 添加
var add = function(action, form) {
	form.action = action;
	form.submit();
};

// 添加页面
var addInput = function(url) {
	var mainFrame = $(window.parent.document).find("#mainFrame");
	mainFrame.attr("src", url);
};

// 修改页面
function editInput(url) {
	var mainFrame = $(window.parent.document).find("#mainFrame");
	mainFrame.attr("src", url);
}

// 查看页面
function view(url) {
	var mainFrame = $(window.parent.document).find("#mainFrame");
	mainFrame.attr("src", url);
}

// 排序
function sortColumns(selectValue, form) {
	form.sortColumns.value = selectValue;
	form.submit();
};

//打开窗口

function openModal(url,w,h) {
	var top = (screen.width-w)/2;
	var left = (screen.height-h)/2;
	window.showModalDialog(
					url,
					null,
					"dialogwidth:"+w+"px;dialogheight:"+h+"px; toolbar=no,top="+top+"px,left="+left+"px, menubar=no, location=no, status=no");
}

function openModalSearch(url,w,h) {
	var top = (screen.width-w)/2;
	var left = (screen.height-h)/2;
	var winPar = window.showModalDialog(
					url,
					'',
					"dialogwidth:"+w+"px;dialogheight:"+h+"px; toolbar=no,top="+top+"px,left="+left+"px, menubar=no, location=no, status=no");
	if(winPar == "refresh"){
		document.getElementById("searchId").click();
	}


}

function openwin(theUrl,w,h)
{
	var lw = (screen.width-w)/2;
	var th = (screen.height-h)/2;
	window.open (theUrl,'','height='+h+',width='+w+',top='+th+',left='+lw+',toolbar=no,menubar=no,scrollbars=yes, resizable=yes,location=no, status=yes')
}

//获取列表中被选中的值
function findSelectionIds(checkboxName) {
	var items = document.getElementsByName(checkboxName);
    var ids = null;
    for(var i=0;i<items.length;i++){
        if(items[i].checked) {
            if(ids == null) {
                ids = new Array(0);
            }
            ids.push(items[i].value);
        }
    }
    return ids;
}

function batchEdit(action, checkboxName, form) {
	if (!hasOneChecked(checkboxName)) {
		alert('请选择要操作的对象!');
		return;
	} else {
		var items = document.getElementsByName(checkboxName);
		var itemId = null;
		var count = 0;
		for (var i = 0; i < items.length; i++) {
			if (items[i].checked == true) {
				itemId = items[i].value;
				count++;
			}
		}
		if (count != 1) {
			alert('请选择一项!');
			return;
		} else {
			window.parent.frames.mainFrame.location.href = action + '?'
					+ itemId;
		}
	}

}

function openDialog(url) {
	var horizontalPadding = 30;
	var verticalPadding = 30;
	document.getElementById("addSit").src = url;
	$("#dialog").dialog("open");

}

function closeDialog() {
	$('#dialog').dialog('close');
	document.execCommand('Refresh');
	// document.designMode = "off";
	// document.execCommand('refresh', false, "");
}
function closeDialogNoRefresh() {
	$('#dialog').dialog('close');
	if ($('#stationDialog') != null) {
		$('#stationDialog').dialog('close');
	}
}

function closeDialogNoRefreshById(dialogId) {
	$('#' + dialogId).dialog('close');
}

function hasOneChecked(name) {
	var items = document.getElementsByName(name);
	if (items.length > 0) {
		for (var i = 0; i < items.length; i++) {
			if (items[i].checked == true) {
				return true;
			}
		}
	} else {
		if (items.checked == true) {
			return true;
		}
	}
	return false;
}

function setAllCheckboxState(name, state) {
	var elms = document.getElementsByName(name);
	for (var i = 0; i < elms.length; i++) {
		elms[i].checked = state;
	}
}

function getReferenceForm(elm) {
	while (elm && elm.tagName != 'BODY') {
		if (elm.tagName == 'FORM')
			return elm;
		elm = elm.parentNode;
	}
	return null;
}

function openDialogById(url, dialogId, iframeId) {
	var horizontalPadding = 30;
	var verticalPadding = 30;
	document.getElementById("" + iframeId).src = url;
	$("#" + dialogId).dialog("open");

}

/**
 * 限制字符显示个数
 */
function limitn(parameters, n) {
	if (parameters) {
		if (parameters.length > n) {
			document.write("<a title=\"" + parameters
					+ "\" style=\"text-decoration: none\">"
					+ parameters.substr(0, n) + "...</a>");
		} else {
			document.write(parameters);
		}
	}
}
/**
 * 级联改变select
 */
function doChange(perObj, childId) {
	var childAr = perAr[$("#" + perObj).val()];
	$("#" + childId).empty();
	$("#" + childId).append("<option value=''>--请选择--</option>");
	if (childAr == null)
		return false;
	for (var i = 0; i < childAr.length; i++) {
		var nextAr = childAr[i];
		$("#" + childId).append(
				"<option value='" + nextAr[1] + "'>" + nextAr[0] + "</option>");
	}
}
/**
 * 
 * @param perId
 *            拍卖会ID
 * @param perVal
 *            拍卖会初始值
 * @param childId
 *            拍场ID
 * @param childVal
 *            拍场初始值
 * @returns {Boolean}
 */
function doInitSel(perId, perVal, childId, childVal) {
	$("#" + perId).val(perVal);
	var childAr = perAr[perVal];
	$("#" + childId).empty();
	$("#" + childId).append("<option value=''>--请选择--</option>");
	if (childAr == null)
		return false;
	for (var i = 0; i < childAr.length; i++) {
		var nextAr = childAr[i];
		var checkVal = "";
		if (nextAr[1] == childVal) {
			checkVal = "selected";
		}
		$("#" + childId).append(
				"<option value='" + nextAr[1] + "' " + checkVal + ">"
						+ nextAr[0] + "</option>");
	}
}

function paramSerialize(id) {
	var paramInputObj = $("#" + id + " input").serialize();
	var paramSelectObj = $("#" + id + " select").serialize();
	var paramAreaObj = $("#" + id + " textarea").serialize();
	var paramInput = (paramInputObj == null || paramInputObj == "") ? ""
			: paramInputObj;
	var paramSelect = (paramSelectObj == null || paramSelectObj == "") ? ""
			: "&" + paramSelectObj;
	var paramArea = (paramAreaObj == null || paramAreaObj == "") ? "" : "&"
			+ paramAreaObj;
	return paramInput + paramSelect + paramArea;
}

function selAll(name , o){
	var  checked = $(o).attr("checked");
	$("input[name='"+name+"']").each(function(){
		$(this).attr("checked",checked);
	});
}