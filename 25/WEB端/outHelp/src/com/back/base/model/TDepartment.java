package com.back.base.model;

public class TDepartment {
    private String id;

    private String mark;

    private String type;

    private String grade;

    private String linkman;
    
    private String linkmanexp;
    

    public String getLinkmanexp() {
		return linkmanexp;
	}

	public void setLinkmanexp(String linkmanexp) {
		this.linkmanexp = linkmanexp;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman == null ? null : linkman.trim();
    }
}