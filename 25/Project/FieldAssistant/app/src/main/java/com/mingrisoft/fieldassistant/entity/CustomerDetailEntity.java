package com.mingrisoft.fieldassistant.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/7/6 0006.
 */
public class CustomerDetailEntity {


    /**
     * 此实体类是为了解析“客户”页面点进去的单条详情页的数据
     *
     * 字段要和后台给的字段是一样的
     *
     * 写出所有的字段
     *
     * 并进行set（）与get（）的方法，以便调用与显示出数据
     * */
    /**
     * customerId : 329b4c4f-f8d4-427c-980b-f9eb76ac3418
     * userId : c43344b3-9148-433e-b609-cb700483a7b7
     * customerComplete : 0
     * customerName : 777
     * customerPhone : 888
     * customerCompany : 999
     * customerAddress :
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
     * customerRemark :
     * customerAllmoney : null
     * customerSpare1 : null
     * customerSpare2 : null
     * customerSpare3 : null
     * customerSpare4 : null
     * customerSpare5 : null
     */

    private TfCustomerBean tfCustomer;
    /**
     * brId : f11cc745-d76f-4c43-85a6-5b310915783f
     * userId : c43344b3-9148-433e-b609-cb700483a7b7
     * brData : 2016-06-30
     * brDataEnd : 2016-06-30
     * brTime : 17:01:39
     * brTimeEnd : 17:01:39
     * brName :
     * brContent :
     * brStatus : 订单业绩
     * brPlace : 长春市南关区财富领域
     * brPhoto : null
     * brMoney :
     * brSpare1 : 329b4c4f-f8d4-427c-980b-f9eb76ac3418
     * brSpare2 : null
     * brSpare3 : null
     * brSpare4 : null
     * brSpare5 : null
     */

    private List<TfBusinessReportListBean> tfBusinessReportList;

    public TfCustomerBean getTfCustomer() {
        return tfCustomer;
    }

    public void setTfCustomer(TfCustomerBean tfCustomer) {
        this.tfCustomer = tfCustomer;
    }

    public List<TfBusinessReportListBean> getTfBusinessReportList() {
        return tfBusinessReportList;
    }

    public void setTfBusinessReportList(List<TfBusinessReportListBean> tfBusinessReportList) {
        this.tfBusinessReportList = tfBusinessReportList;
    }

    public static class TfCustomerBean {
        private String customerId;
        private String userId;
        private String customerComplete;
        private String customerName;
        private String customerPhone;
        private String customerCompany;
        private String customerAddress;
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
        private String customerRemark;
        private Object customerAllmoney;
        private Object customerSpare1;
        private Object customerSpare2;
        private Object customerSpare3;

        public Object getCustomerSpare3() {
            return customerSpare3;
        }

        public void setCustomerSpare3(Object customerSpare3) {
            this.customerSpare3 = customerSpare3;
        }

        public Object getCustomerSpare2() {
            return customerSpare2;
        }

        public void setCustomerSpare2(Object customerSpare2) {
            this.customerSpare2 = customerSpare2;
        }

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

        public String getCustomerPhone() {
            return customerPhone;
        }

        public void setCustomerPhone(String customerPhone) {
            this.customerPhone = customerPhone;
        }

        public String getCustomerCompany() {
            return customerCompany;
        }

        public void setCustomerCompany(String customerCompany) {
            this.customerCompany = customerCompany;
        }

        public String getCustomerAddress() {
            return customerAddress;
        }

        public void setCustomerAddress(String customerAddress) {
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

        public String getCustomerRemark() {
            return customerRemark;
        }

        public void setCustomerRemark(String customerRemark) {
            this.customerRemark = customerRemark;
        }

        public Object getCustomerAllmoney() {
            return customerAllmoney;
        }

        public void setCustomerAllmoney(Object customerAllmoney) {
            this.customerAllmoney = customerAllmoney;
        }

        public Object getCustomerSpare1() {
            return customerSpare1;
        }

        public void setCustomerSpare1(Object customerSpare1) {
            this.customerSpare1 = customerSpare1;
        }
    }

    public static class TfBusinessReportListBean {
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
        private Object brPhoto;
        private String brMoney;
        private String brSpare1;
        private Object brSpare2;
        private Object brSpare3;
        private Object brSpare4;
        private Object brSpare5;

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

        public Object getBrPhoto() {
            return brPhoto;
        }

        public void setBrPhoto(Object brPhoto) {
            this.brPhoto = brPhoto;
        }

        public String getBrMoney() {
            return brMoney;
        }

        public void setBrMoney(String brMoney) {
            this.brMoney = brMoney;
        }

        public String getBrSpare1() {
            return brSpare1;
        }

        public void setBrSpare1(String brSpare1) {
            this.brSpare1 = brSpare1;
        }

        public Object getBrSpare2() {
            return brSpare2;
        }

        public void setBrSpare2(Object brSpare2) {
            this.brSpare2 = brSpare2;
        }

        public Object getBrSpare3() {
            return brSpare3;
        }

        public void setBrSpare3(Object brSpare3) {
            this.brSpare3 = brSpare3;
        }

        public Object getBrSpare4() {
            return brSpare4;
        }

        public void setBrSpare4(Object brSpare4) {
            this.brSpare4 = brSpare4;
        }

        public Object getBrSpare5() {
            return brSpare5;
        }

        public void setBrSpare5(Object brSpare5) {
            this.brSpare5 = brSpare5;
        }
    }
}
