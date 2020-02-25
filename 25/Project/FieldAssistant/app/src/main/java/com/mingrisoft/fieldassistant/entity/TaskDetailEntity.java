package com.mingrisoft.fieldassistant.entity;

/**
 * Created by Administrator on 2016/6/23 0023.
 */
public class TaskDetailEntity {


    /**
     * 此实体类是为了解析“任务上报”页面的详情的数据
     *
     * 字段要和后台给的字段是一样的
     *
     * 写出所有的字段
     *
     * 并进行set（）与get（）的方法，以便调用与显示出数据
     * */
    /**
     * brId : 9e197e10-f99d-4077-97c9-0e966a2cd87c
     * userId : c43344b3-9148-433e-b609-cb700483a7b7
     * brData : 2016-06-30
     * brDataEnd : 2016-06-30
     * brTime : 14:44:26
     * brTimeEnd : 14:44:26
     * brName : 腥风血雨
     * brContent : ooxx
     * brStatus : 业务拜访
     * brPlace : 长春市南关区财富领域
     * brPhoto :
     * brMoney : null
     * brSpare1 : null
     * brSpare2 : null
     * brSpare3 : null
     * brSpare4 : null
     * brSpare5 : null
     */

    private String brId;
    private String userId;
    private String brData;
    private String brDataEnd;
    private String brTime;
    private String brTimeEnd;
    private String brName;
    private String brContent;
    private String brStatus;
    private String brPlace;
    private String brPhoto;
    private Object brMoney;

    public String getBrId() {
        return brId;
    }

    public void setBrId(String brId) {
        this.brId = brId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBrData() {
        return brData;
    }

    public void setBrData(String brData) {
        this.brData = brData;
    }

    public String getBrDataEnd() {
        return brDataEnd;
    }

    public void setBrDataEnd(String brDataEnd) {
        this.brDataEnd = brDataEnd;
    }

    public String getBrTime() {
        return brTime;
    }

    public void setBrTime(String brTime) {
        this.brTime = brTime;
    }

    public String getBrTimeEnd() {
        return brTimeEnd;
    }

    public void setBrTimeEnd(String brTimeEnd) {
        this.brTimeEnd = brTimeEnd;
    }

    public String getBrName() {
        return brName;
    }

    public void setBrName(String brName) {
        this.brName = brName;
    }

    public String getBrContent() {
        return brContent;
    }

    public void setBrContent(String brContent) {
        this.brContent = brContent;
    }

    public String getBrStatus() {
        return brStatus;
    }

    public void setBrStatus(String brStatus) {
        this.brStatus = brStatus;
    }

    public String getBrPlace() {
        return brPlace;
    }

    public void setBrPlace(String brPlace) {
        this.brPlace = brPlace;
    }

    public String getBrPhoto() {
        return brPhoto;
    }

    public void setBrPhoto(String brPhoto) {
        this.brPhoto = brPhoto;
    }

    public Object getBrMoney() {
        return brMoney;
    }

    public void setBrMoney(Object brMoney) {
        this.brMoney = brMoney;
    }
}

