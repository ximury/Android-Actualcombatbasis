package com.back.phone.model;

public class TfBusinessReport {
    private String brId;

    private String userId;

    private String brData;
    private String brDataEnd;

    public String getBrDataEnd() {
		return brDataEnd;
	}

	public void setBrDataEnd(String brDataEnd) {
		this.brDataEnd = brDataEnd;
	}

	private String brTime;
    
    private String brTimeEnd;

    public String getBrTimeEnd() {
		return brTimeEnd;
	}

	public void setBrTimeEnd(String brTimeEnd) {
		this.brTimeEnd = brTimeEnd;
	}

	private String brName;

    private String brContent;

    private String brStatus;

    private String brPlace;

    private String brPhoto;

    private String brMoney;

    private String brSpare1;

    private String brSpare2;

    private String brSpare3;

    private String brSpare4;

    private String brSpare5;

    public String getBrId() {
        return brId;
    }

    public void setBrId(String brId) {
        this.brId = brId == null ? null : brId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getBrData() {
        return brData;
    }

    public void setBrData(String brData) {
        this.brData = brData == null ? null : brData.trim();
    }

    public String getBrTime() {
        return brTime;
    }

    public void setBrTime(String brTime) {
        this.brTime = brTime == null ? null : brTime.trim();
    }

    public String getBrName() {
        return brName;
    }

    public void setBrName(String brName) {
        this.brName = brName == null ? null : brName.trim();
    }

    public String getBrContent() {
        return brContent;
    }

    public void setBrContent(String brContent) {
        this.brContent = brContent == null ? null : brContent.trim();
    }

    public String getBrStatus() {
        return brStatus;
    }

    public void setBrStatus(String brStatus) {
        this.brStatus = brStatus == null ? null : brStatus.trim();
    }

    public String getBrPlace() {
        return brPlace;
    }

    public void setBrPlace(String brPlace) {
        this.brPlace = brPlace == null ? null : brPlace.trim();
    }

    public String getBrPhoto() {
        return brPhoto;
    }

    public void setBrPhoto(String brPhoto) {
        this.brPhoto = brPhoto == null ? null : brPhoto.trim();
    }

    public String getBrMoney() {
        return brMoney;
    }

    public void setBrMoney(String brMoney) {
        this.brMoney = brMoney == null ? null : brMoney.trim();
    }

    public String getBrSpare1() {
        return brSpare1;
    }

    public void setBrSpare1(String brSpare1) {
        this.brSpare1 = brSpare1 == null ? null : brSpare1.trim();
    }

    public String getBrSpare2() {
        return brSpare2;
    }

    public void setBrSpare2(String brSpare2) {
        this.brSpare2 = brSpare2 == null ? null : brSpare2.trim();
    }

    public String getBrSpare3() {
        return brSpare3;
    }

    public void setBrSpare3(String brSpare3) {
        this.brSpare3 = brSpare3 == null ? null : brSpare3.trim();
    }

    public String getBrSpare4() {
        return brSpare4;
    }

    public void setBrSpare4(String brSpare4) {
        this.brSpare4 = brSpare4 == null ? null : brSpare4.trim();
    }

    public String getBrSpare5() {
        return brSpare5;
    }

    public void setBrSpare5(String brSpare5) {
        this.brSpare5 = brSpare5 == null ? null : brSpare5.trim();
    }
}