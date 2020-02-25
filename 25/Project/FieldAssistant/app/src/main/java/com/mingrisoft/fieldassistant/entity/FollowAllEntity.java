package com.mingrisoft.fieldassistant.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/7/5 0005.
 */
public class FollowAllEntity {

    /**
     * 此实体类是为了解析“跟单”页面的数据
     *
     * 字段要和后台给的字段是一样的
     *
     * 写出所有的字段
     *
     * 并进行set（）与get（）的方法，以便调用与显示出数据
     * */
    /**
     * id : c43344b3-9148-433e-b609-cb700483a7b7
     * tfBusinessList : [{"blId":"2a774840-79d4-45c9-ab53-8f77d01fa5e9","userId":"c43344b3-9148-433e-b609-cb700483a7b7","blData":"2016-07-05","blTime":"15:51:00","blName":"U不温度我不","blContent":"英文版祖辈","blStatus":"订单业绩","blPlace":"长春市南关区财富领域","blPhoto":null,"blMoney":8085585,"blCustomer":"c8a5a40f-5537-4b42-94d1-27c81b462b4c","blSpare1":null,"blSpare2":null,"blSpare3":null,"blSpare4":null,"blSpare5":null},{"blId":"fc4998ed-b4ce-4063-96c5-0cc9d957c091","userId":"c43344b3-9148-433e-b609-cb700483a7b7","blData":"2016-07-05","blTime":"15:36:14","blName":"fcyrxxe","blContent":"付车费重复粗大u付发二姐夫u度 复u付许多许多许多学信网","blStatus":"订单业绩","blPlace":"长春市南关区财富领域","blPhoto":null,"blMoney":6000000,"blCustomer":"e9e6daf1-c982-42f7-9e05-f61015c36e5c","blSpare1":"0024efc2-9d2c-43ee-af51-76b294fd60ec","blSpare2":null,"blSpare3":null,"blSpare4":null,"blSpare5":null}]
     */

    private String id;
    /**
     * blId : 2a774840-79d4-45c9-ab53-8f77d01fa5e9
     * userId : c43344b3-9148-433e-b609-cb700483a7b7
     * blData : 2016-07-05
     * blTime : 15:51:00
     * blName : U不温度我不
     * blContent : 英文版祖辈
     * blStatus : 订单业绩
     * blPlace : 长春市南关区财富领域
     * blPhoto : null
     * blMoney : 8085585
     * blCustomer : c8a5a40f-5537-4b42-94d1-27c81b462b4c
     * blSpare1 : null
     * blSpare2 : null
     * blSpare3 : null
     * blSpare4 : null
     * blSpare5 : null
     */

    private List<TfBusinessListBean> tfBusinessList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<TfBusinessListBean> getTfBusinessList() {
        return tfBusinessList;
    }

    public void setTfBusinessList(List<TfBusinessListBean> tfBusinessList) {
        this.tfBusinessList = tfBusinessList;
    }

    public static class TfBusinessListBean {
        private String blId;
        private String userId;
        private String blData;
        private String blTime;
        private String blName;
        private String blContent;
        private String blStatus;
        private String blPlace;
        private Object blPhoto;
        private int blMoney;
        private String blCustomer;

        public String getBlId() {
            return blId;
        }

        public void setBlId(String blId) {
            this.blId = blId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getBlData() {
            return blData;
        }

        public void setBlData(String blData) {
            this.blData = blData;
        }

        public String getBlTime() {
            return blTime;
        }

        public void setBlTime(String blTime) {
            this.blTime = blTime;
        }

        public String getBlName() {
            return blName;
        }

        public void setBlName(String blName) {
            this.blName = blName;
        }

        public String getBlContent() {
            return blContent;
        }

        public void setBlContent(String blContent) {
            this.blContent = blContent;
        }

        public String getBlStatus() {
            return blStatus;
        }

        public void setBlStatus(String blStatus) {
            this.blStatus = blStatus;
        }

        public String getBlPlace() {
            return blPlace;
        }

        public void setBlPlace(String blPlace) {
            this.blPlace = blPlace;
        }

        public Object getBlPhoto() {
            return blPhoto;
        }

        public void setBlPhoto(Object blPhoto) {
            this.blPhoto = blPhoto;
        }

        public int getBlMoney() {
            return blMoney;
        }

        public void setBlMoney(int blMoney) {
            this.blMoney = blMoney;
        }

        public String getBlCustomer() {
            return blCustomer;
        }

        public void setBlCustomer(String blCustomer) {
            this.blCustomer = blCustomer;
        }
    }
}
