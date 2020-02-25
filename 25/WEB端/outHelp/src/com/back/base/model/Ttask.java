package com.back.base.model;

import java.util.HashMap;

import com.back.base.AbstractEntity;

public class Ttask extends AbstractEntity {
    private String pkId;

    private String colDept;

    private String colRole;

    private String colUid;

    private String colDesc;

    private String colType;
    
    private String colTypeMc;

    private String colUrl;

    private String colState;

    private String colFinaldate;

    private String openType;

    private String colId;

    private String sendName;

    private String createId;

    private String createTime;

    private String updateId;

    private String updateTime;
    
    private String deptCode;
    
    private String deptCodeMc;

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId == null ? null : pkId.trim();
    }

    public String getColDept() {
        return colDept;
    }

    public void setColDept(String colDept) {
        this.colDept = colDept == null ? null : colDept.trim();
    }

    public String getColRole() {
        return colRole;
    }

    public void setColRole(String colRole) {
        this.colRole = colRole == null ? null : colRole.trim();
    }

    public String getColUid() {
        return colUid;
    }

    public void setColUid(String colUid) {
        this.colUid = colUid == null ? null : colUid.trim();
    }

    public String getColDesc() {
        return colDesc;
    }

    public void setColDesc(String colDesc) {
        this.colDesc = colDesc == null ? null : colDesc.trim();
    }

    public String getColType() {
        return colType;
    }

    public void setColType(String colType) {
        this.colType = colType == null ? null : colType.trim();
    }

    public String getColUrl() {
        return colUrl;
    }

    public void setColUrl(String colUrl) {
        this.colUrl = colUrl == null ? null : colUrl.trim();
    }

    public String getColState() {
        return colState;
    }

    public void setColState(String colState) {
        this.colState = colState == null ? null : colState.trim();
    }

    public String getColFinaldate() {
        return colFinaldate;
    }

    public void setColFinaldate(String colFinaldate) {
        this.colFinaldate = colFinaldate == null ? null : colFinaldate.trim();
    }

    public String getOpenType() {
        return openType;
    }

    public void setOpenType(String openType) {
        this.openType = openType == null ? null : openType.trim();
    }

    public String getColId() {
        return colId;
    }

    public void setColId(String colId) {
        this.colId = colId == null ? null : colId.trim();
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName == null ? null : sendName.trim();
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }
    
    
    
	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getColTypeMc() {
		return colTypeMc;
	}

	public void setColTypeMc(String colTypeMc) {
		this.colTypeMc = colTypeMc;
	}

	
	
	public String getDeptCodeMc() {
		return deptCodeMc;
	}

	public void setDeptCodeMc(String deptCodeMc) {
		this.deptCodeMc = deptCodeMc;
	}

	@Override
	public HashMap<String, String> dicMap() {
		HashMap<String, String>  map = new HashMap();
		map.put("colType","YWTYPE");//需要翻译的字段和对应的字典			
		return map;
	}
}