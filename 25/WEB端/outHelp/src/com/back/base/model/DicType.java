package com.back.base.model;

public class DicType {
    private String pkId;

    private String dicName;

    private String busCode;

    private String dicCode;

    private String dicOrigin;

    private String dicSql;

    private String isTree;

    private String remark;

    private String status;

    private String createId;

    private String createTime;

    private String updateId;
    
    private String updateTime;
    private boolean updateFlag;
    
    private boolean checked;
    
    public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(boolean updateFlag) {
		this.updateFlag = updateFlag;
	}

	public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId == null ? null : pkId.trim();
    }

    public String getDicName() {
        return dicName;
    }

    public void setDicName(String dicName) {
        this.dicName = dicName == null ? null : dicName.trim();
    }

    public String getBusCode() {
        return busCode;
    }

    public void setBusCode(String busCode) {
        this.busCode = busCode == null ? null : busCode.trim();
    }

    public String getDicCode() {
        return dicCode;
    }

    public void setDicCode(String dicCode) {
        this.dicCode = dicCode == null ? null : dicCode.trim();
    }

    public String getDicOrigin() {
        return dicOrigin;
    }

    public void setDicOrigin(String dicOrigin) {
        this.dicOrigin = dicOrigin == null ? null : dicOrigin.trim();
    }

    public String getDicSql() {
        return dicSql;
    }

    public void setDicSql(String dicSql) {
        this.dicSql = dicSql == null ? null : dicSql.trim();
    }

    public String getIsTree() {
        return isTree;
    }

    public void setIsTree(String isTree) {
        this.isTree = isTree == null ? null : isTree.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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
}