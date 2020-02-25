package com.back.phone.model;

public class TfLossDeal {
    private String ldId;

    private String lossId;

    private String ldMoney;

    private String ldRemark;

    private String ldSpare1;

    private String ldSpare2;

    private String ldSpare3;

    private String ldSpare4;

    private String ldSpare5;

    public String getLdId() {
        return ldId;
    }

    public void setLdId(String ldId) {
        this.ldId = ldId == null ? null : ldId.trim();
    }

    public String getLossId() {
        return lossId;
    }

    public void setLossId(String lossId) {
        this.lossId = lossId == null ? null : lossId.trim();
    }

    public String getLdMoney() {
        return ldMoney;
    }

    public void setLdMoney(String ldMoney) {
        this.ldMoney = ldMoney;
    }

    public String getLdRemark() {
        return ldRemark;
    }

    public void setLdRemark(String ldRemark) {
        this.ldRemark = ldRemark == null ? null : ldRemark.trim();
    }

    public String getLdSpare1() {
        return ldSpare1;
    }

    public void setLdSpare1(String ldSpare1) {
        this.ldSpare1 = ldSpare1 == null ? null : ldSpare1.trim();
    }

    public String getLdSpare2() {
        return ldSpare2;
    }

    public void setLdSpare2(String ldSpare2) {
        this.ldSpare2 = ldSpare2 == null ? null : ldSpare2.trim();
    }

    public String getLdSpare3() {
        return ldSpare3;
    }

    public void setLdSpare3(String ldSpare3) {
        this.ldSpare3 = ldSpare3 == null ? null : ldSpare3.trim();
    }

    public String getLdSpare4() {
        return ldSpare4;
    }

    public void setLdSpare4(String ldSpare4) {
        this.ldSpare4 = ldSpare4 == null ? null : ldSpare4.trim();
    }

    public String getLdSpare5() {
        return ldSpare5;
    }

    public void setLdSpare5(String ldSpare5) {
        this.ldSpare5 = ldSpare5 == null ? null : ldSpare5.trim();
    }
}