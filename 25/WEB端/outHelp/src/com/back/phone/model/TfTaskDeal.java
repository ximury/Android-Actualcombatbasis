package com.back.phone.model;

public class TfTaskDeal {
    private String tdId;

    private String taskId;

    private String userId;

    private String tdSpare1;

    private String tdSpare2;

    private String tdSpare3;

    private String tdSpare4;

    private String tdSpare5;

    public String getTdId() {
        return tdId;
    }

    public void setTdId(String tdId) {
        this.tdId = tdId == null ? null : tdId.trim();
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getTdSpare1() {
        return tdSpare1;
    }

    public void setTdSpare1(String tdSpare1) {
        this.tdSpare1 = tdSpare1 == null ? null : tdSpare1.trim();
    }

    public String getTdSpare2() {
        return tdSpare2;
    }

    public void setTdSpare2(String tdSpare2) {
        this.tdSpare2 = tdSpare2 == null ? null : tdSpare2.trim();
    }

    public String getTdSpare3() {
        return tdSpare3;
    }

    public void setTdSpare3(String tdSpare3) {
        this.tdSpare3 = tdSpare3 == null ? null : tdSpare3.trim();
    }

    public String getTdSpare4() {
        return tdSpare4;
    }

    public void setTdSpare4(String tdSpare4) {
        this.tdSpare4 = tdSpare4 == null ? null : tdSpare4.trim();
    }

    public String getTdSpare5() {
        return tdSpare5;
    }

    public void setTdSpare5(String tdSpare5) {
        this.tdSpare5 = tdSpare5 == null ? null : tdSpare5.trim();
    }
}