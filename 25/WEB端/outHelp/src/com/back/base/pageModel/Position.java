package com.back.base.pageModel;

public class Position {
    private String id;
    
    private String pid;
    
    private String name;

    private String mark;

    private String type;

    private String grade;

    private String isleader;

    private String leaderlevel; 
    
    private String description;
    
    private String createtime;
    
    private String updatetime;
    
    private boolean updateFlag;


    public boolean isUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(boolean updateFlag) {
		this.updateFlag = updateFlag;
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

	public String getIsleader() {
		return isleader;
	}

	public void setIsleader(String isleader) {
		this.isleader = isleader;
	}

	public String getLeaderlevel() {
		return leaderlevel;
	}

	public void setLeaderlevel(String leaderlevel) {
		this.leaderlevel = leaderlevel;
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
}