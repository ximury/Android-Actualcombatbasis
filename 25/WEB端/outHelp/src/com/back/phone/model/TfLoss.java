package com.back.phone.model;

public class TfLoss {
    private String lossId;

    private String userId;

    private String lossName;

    private String lossMoney;

    private String lossStatus;

    private String lossEnd;

    private String lossSpare1;

    private String lossSpare2;

    private String lossSpare3;

    private String lossSpare4;

    private String lossSpare5;

    public String getLossId() {
        return lossId;
    }

    public void setLossId(String lossId) {
        this.lossId = lossId == null ? null : lossId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getLossName() {
        return lossName;
    }

    public void setLossName(String lossName) {
        this.lossName = lossName == null ? null : lossName.trim();
    }

    public String getLossMoney() {
        return lossMoney;
    }

    public void setLossMoney(String lossMoney) {
        this.lossMoney = lossMoney;
    }

    public String getLossStatus() {
        return lossStatus;
    }

    public void setLossStatus(String lossStatus) {
        this.lossStatus = lossStatus == null ? null : lossStatus.trim();
    }

    public String getLossEnd() {
        return lossEnd;
    }

    public void setLossEnd(String lossEnd) {
        this.lossEnd = lossEnd == null ? null : lossEnd.trim();
    }

    public String getLossSpare1() {
        return lossSpare1;
    }

    public void setLossSpare1(String lossSpare1) {
        this.lossSpare1 = lossSpare1 == null ? null : lossSpare1.trim();
    }

    public String getLossSpare2() {
        return lossSpare2;
    }

    public void setLossSpare2(String lossSpare2) {
        this.lossSpare2 = lossSpare2 == null ? null : lossSpare2.trim();
    }

    public String getLossSpare3() {
        return lossSpare3;
    }

    public void setLossSpare3(String lossSpare3) {
        this.lossSpare3 = lossSpare3 == null ? null : lossSpare3.trim();
    }

    public String getLossSpare4() {
        return lossSpare4;
    }

    public void setLossSpare4(String lossSpare4) {
        this.lossSpare4 = lossSpare4 == null ? null : lossSpare4.trim();
    }

    public String getLossSpare5() {
        return lossSpare5;
    }

    public void setLossSpare5(String lossSpare5) {
        this.lossSpare5 = lossSpare5 == null ? null : lossSpare5.trim();
    }
}