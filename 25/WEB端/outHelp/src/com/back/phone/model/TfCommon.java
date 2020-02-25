package com.back.phone.model;

public class TfCommon {
    private String commonId;

    private String userId;

    private String commonContent;

    private String commonSpare1;

    private String commonSpare2;

    private String commonSpare3;

    private String commonSpare4;

    private String commonSpare5;

    public String getCommonId() {
        return commonId;
    }

    public void setCommonId(String commonId) {
        this.commonId = commonId == null ? null : commonId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getCommonContent() {
        return commonContent;
    }

    public void setCommonContent(String commonContent) {
        this.commonContent = commonContent == null ? null : commonContent.trim();
    }

    public String getCommonSpare1() {
        return commonSpare1;
    }

    public void setCommonSpare1(String commonSpare1) {
        this.commonSpare1 = commonSpare1 == null ? null : commonSpare1.trim();
    }

    public String getCommonSpare2() {
        return commonSpare2;
    }

    public void setCommonSpare2(String commonSpare2) {
        this.commonSpare2 = commonSpare2 == null ? null : commonSpare2.trim();
    }

    public String getCommonSpare3() {
        return commonSpare3;
    }

    public void setCommonSpare3(String commonSpare3) {
        this.commonSpare3 = commonSpare3 == null ? null : commonSpare3.trim();
    }

    public String getCommonSpare4() {
        return commonSpare4;
    }

    public void setCommonSpare4(String commonSpare4) {
        this.commonSpare4 = commonSpare4 == null ? null : commonSpare4.trim();
    }

    public String getCommonSpare5() {
        return commonSpare5;
    }

    public void setCommonSpare5(String commonSpare5) {
        this.commonSpare5 = commonSpare5 == null ? null : commonSpare5.trim();
    }
}