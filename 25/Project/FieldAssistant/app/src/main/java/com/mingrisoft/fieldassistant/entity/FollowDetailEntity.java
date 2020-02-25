package com.mingrisoft.fieldassistant.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/7/5 0005.
 */
public class FollowDetailEntity {


    /**
     * 此实体类是为了解析“跟单”页面的单条详情数据
     *
     * 字段要和后台给的字段是一样的
     *
     * 写出所有的字段
     *
     * 并进行set（）与get（）的方法，以便调用与显示出数据
     * */
    /**
     * idNew : c43344b3-9148-433e-b609-cb700483a7b7
     * tfBusinessList : {"blId":"2a774840-79d4-45c9-ab53-8f77d01fa5e9","userId":"c43344b3-9148-433e-b609-cb700483a7b7","blData":"2016-07-05","blTime":"15:51:00","blName":"U不温度我不","blContent":"英文版祖辈","blStatus":"跟进中","blPlace":"长春市南关区财富领域","blPhoto":null,"blMoney":8085585,"blCustomer":"c8a5a40f-5537-4b42-94d1-27c81b462b4c","blSpare1":null,"blSpare2":null,"blSpare3":null,"blSpare4":null,"blSpare5":null}
     * tfBusinessListDeal : [{"bldId":"6193b6f2-b5e3-4dd4-a7b3-be91fe1497b8","userId":"c43344b3-9148-433e-b609-cb700483a7b7","blId":"2a774840-79d4-45c9-ab53-8f77d01fa5e9","bldData":"2016-07-05","bldTime":"15:51:00","bldContent":"紧紧","bldStatus":"赢单","bldPlace":null,"bldMoney":null,"bldVisitdata":"2016-09-29 07:00:00","bldVisit":"提醒","bldSpare1":null,"bldSpare2":null,"bldSpare3":null,"bldSpare4":null,"bldSpare5":null}]
     * tfCustomer : [{"customerId":"c8a5a40f-5537-4b42-94d1-27c81b462b4c","userId":"c43344b3-9148-433e-b609-cb700483a7b7","customerComplete":"1","customerName":"","customerPhone":null,"customerCompany":null,"customerAddress":null,"customerSex":null,"customerMarry":null,"customerPhoto":null,"customerMobile":null,"customerBirthday":null,"customerBir":null,"customerBirdate":null,"customerLike":null,"customerStatus":null,"customerJob":null,"customerRemark":null,"customerAllmoney":0,"customerSpare1":"1","customerSpare2":null,"customerSpare3":null,"customerSpare4":null,"customerSpare5":null}]
     */

    private String idNew;
    /**
     * blId : 2a774840-79d4-45c9-ab53-8f77d01fa5e9
     * userId : c43344b3-9148-433e-b609-cb700483a7b7
     * blData : 2016-07-05
     * blTime : 15:51:00
     * blName : U不温度我不
     * blContent : 英文版祖辈
     * blStatus : 跟进中
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

    private TfBusinessListBean tfBusinessList;
    /**
     * bldId : 6193b6f2-b5e3-4dd4-a7b3-be91fe1497b8
     * userId : c43344b3-9148-433e-b609-cb700483a7b7
     * blId : 2a774840-79d4-45c9-ab53-8f77d01fa5e9
     * bldData : 2016-07-05
     * bldTime : 15:51:00
     * bldContent : 紧紧
     * bldStatus : 赢单
     * bldPlace : null
     * bldMoney : null
     * bldVisitdata : 2016-09-29 07:00:00
     * bldVisit : 提醒
     * bldSpare1 : null
     * bldSpare2 : null
     * bldSpare3 : null
     * bldSpare4 : null
     * bldSpare5 : null
     */

    private List<TfBusinessListDealBean> tfBusinessListDeal;
    /**
     * customerId : c8a5a40f-5537-4b42-94d1-27c81b462b4c
     * userId : c43344b3-9148-433e-b609-cb700483a7b7
     * customerComplete : 1
     * customerName :
     * customerPhone : null
     * customerCompany : null
     * customerAddress : null
     * customerSex : null
     * customerMarry : null
     * customerPhoto : null
     * customerMobile : null
     * customerBirthday : null
     * customerBir : null
     * customerBirdate : null
     * customerLike : null
     * customerStatus : null
     * customerJob : null
     * customerRemark : null
     * customerAllmoney : 0
     * customerSpare1 : 1
     * customerSpare2 : null
     * customerSpare3 : null
     * customerSpare4 : null
     * customerSpare5 : null
     */

    private List<TfCustomerBean> tfCustomer;

    public String getIdNew() {
        return idNew;
    }

    public void setIdNew(String idNew) {
        this.idNew = idNew;
    }

    public TfBusinessListBean getTfBusinessList() {
        return tfBusinessList;
    }

    public void setTfBusinessList(TfBusinessListBean tfBusinessList) {
        this.tfBusinessList = tfBusinessList;
    }

    public List<TfBusinessListDealBean> getTfBusinessListDeal() {
        return tfBusinessListDeal;
    }

    public void setTfBusinessListDeal(List<TfBusinessListDealBean> tfBusinessListDeal) {
        this.tfBusinessListDeal = tfBusinessListDeal;
    }

    public List<TfCustomerBean> getTfCustomer() {
        return tfCustomer;
    }

    public void setTfCustomer(List<TfCustomerBean> tfCustomer) {
        this.tfCustomer = tfCustomer;
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

    public static class TfBusinessListDealBean {
        private String bldId;
        private String userId;
        private String blId;
        private String bldData;
        private String bldTime;
        private String bldContent;
        private String bldStatus;
        private Object bldPlace;
        private Object bldMoney;
        private String bldVisitdata;
        private String bldVisit;

        public String getBldId() {
            return bldId;
        }

        public void setBldId(String bldId) {
            this.bldId = bldId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getBlId() {
            return blId;
        }

        public void setBlId(String blId) {
            this.blId = blId;
        }

        public String getBldData() {
            return bldData;
        }

        public void setBldData(String bldData) {
            this.bldData = bldData;
        }

        public String getBldTime() {
            return bldTime;
        }

        public void setBldTime(String bldTime) {
            this.bldTime = bldTime;
        }

        public String getBldContent() {
            return bldContent;
        }

        public void setBldContent(String bldContent) {
            this.bldContent = bldContent;
        }

        public String getBldStatus() {
            return bldStatus;
        }

        public void setBldStatus(String bldStatus) {
            this.bldStatus = bldStatus;
        }

        public Object getBldPlace() {
            return bldPlace;
        }

        public void setBldPlace(Object bldPlace) {
            this.bldPlace = bldPlace;
        }

        public Object getBldMoney() {
            return bldMoney;
        }

        public void setBldMoney(Object bldMoney) {
            this.bldMoney = bldMoney;
        }

        public String getBldVisitdata() {
            return bldVisitdata;
        }

        public void setBldVisitdata(String bldVisitdata) {
            this.bldVisitdata = bldVisitdata;
        }

        public String getBldVisit() {
            return bldVisit;
        }

        public void setBldVisit(String bldVisit) {
            this.bldVisit = bldVisit;
        }
    }

    public static class TfCustomerBean {
        private String customerId;
        private String userId;
        private String customerComplete;
        private String customerName;
        private Object customerPhone;
        private Object customerCompany;
        private Object customerAddress;
        private Object customerSex;
        private Object customerMarry;
        private Object customerPhoto;
        private Object customerMobile;
        private Object customerBirthday;
        private Object customerBir;
        private Object customerBirdate;
        private Object customerLike;
        private Object customerStatus;
        private Object customerJob;
        private Object customerRemark;
        private int customerAllmoney;

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getCustomerComplete() {
            return customerComplete;
        }

        public void setCustomerComplete(String customerComplete) {
            this.customerComplete = customerComplete;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public Object getCustomerPhone() {
            return customerPhone;
        }

        public void setCustomerPhone(Object customerPhone) {
            this.customerPhone = customerPhone;
        }

        public Object getCustomerCompany() {
            return customerCompany;
        }

        public void setCustomerCompany(Object customerCompany) {
            this.customerCompany = customerCompany;
        }

        public Object getCustomerAddress() {
            return customerAddress;
        }

        public void setCustomerAddress(Object customerAddress) {
            this.customerAddress = customerAddress;
        }

        public Object getCustomerSex() {
            return customerSex;
        }

        public void setCustomerSex(Object customerSex) {
            this.customerSex = customerSex;
        }

        public Object getCustomerMarry() {
            return customerMarry;
        }

        public void setCustomerMarry(Object customerMarry) {
            this.customerMarry = customerMarry;
        }

        public Object getCustomerPhoto() {
            return customerPhoto;
        }

        public void setCustomerPhoto(Object customerPhoto) {
            this.customerPhoto = customerPhoto;
        }

        public Object getCustomerMobile() {
            return customerMobile;
        }

        public void setCustomerMobile(Object customerMobile) {
            this.customerMobile = customerMobile;
        }

        public Object getCustomerBirthday() {
            return customerBirthday;
        }

        public void setCustomerBirthday(Object customerBirthday) {
            this.customerBirthday = customerBirthday;
        }

        public Object getCustomerBir() {
            return customerBir;
        }

        public void setCustomerBir(Object customerBir) {
            this.customerBir = customerBir;
        }

        public Object getCustomerBirdate() {
            return customerBirdate;
        }

        public void setCustomerBirdate(Object customerBirdate) {
            this.customerBirdate = customerBirdate;
        }

        public Object getCustomerLike() {
            return customerLike;
        }

        public void setCustomerLike(Object customerLike) {
            this.customerLike = customerLike;
        }

        public Object getCustomerStatus() {
            return customerStatus;
        }

        public void setCustomerStatus(Object customerStatus) {
            this.customerStatus = customerStatus;
        }

        public Object getCustomerJob() {
            return customerJob;
        }

        public void setCustomerJob(Object customerJob) {
            this.customerJob = customerJob;
        }

        public Object getCustomerRemark() {
            return customerRemark;
        }

        public void setCustomerRemark(Object customerRemark) {
            this.customerRemark = customerRemark;
        }

        public int getCustomerAllmoney() {
            return customerAllmoney;
        }

        public void setCustomerAllmoney(int customerAllmoney) {
            this.customerAllmoney = customerAllmoney;
        }
    }
}
