package com.back.base.pageModel;

import java.util.HashMap;

import com.back.base.AbstractEntity;

public class Department extends AbstractEntity{
    private String id;
    
    private String pid;
    
    private String name;

    private String mark;

    private String type;

    private String grade;

    private String linkman;
    
    private String linkmanexp;
    
	private String description;
    
    private String createtime;
    
    private String updatetime;    
    
    private boolean isUpdateFlag;
    
    public String getLinkmanexp() {
		return linkmanexp;
	}

	public void setLinkmanexp(String linkmanexp) {
		this.linkmanexp = linkmanexp;
	}

	public boolean isUpdateFlag() {
		return isUpdateFlag;
	}

	public void setUpdateFlag(boolean isUpdateFlag) {
		this.isUpdateFlag = isUpdateFlag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	@Override
	public HashMap<String, String> dicMap() {
		// TODO Auto-generated method stub
		return null;
	}

}