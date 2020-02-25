package com.back.base.model;

public class TPosition {
    private String id;

    private String mark;

    private String type;

    private String grade;

    private String isleader;

    private String leaderlevel;

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

    public String getIsleader() {
        return isleader;
    }

    public void setIsleader(String isleader) {
        this.isleader = isleader == null ? null : isleader.trim();
    }

    public String getLeaderlevel() {
        return leaderlevel;
    }

    public void setLeaderlevel(String leaderlevel) {
        this.leaderlevel = leaderlevel == null ? null : leaderlevel.trim();
    }
}