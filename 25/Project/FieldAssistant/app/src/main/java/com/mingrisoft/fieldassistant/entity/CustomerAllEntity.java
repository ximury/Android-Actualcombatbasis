package com.mingrisoft.fieldassistant.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/7/6 0006.
 */
public class CustomerAllEntity  {


    /**
     * 此实体类是为了解析“客户”页面的数据
     *
     * 字段要和后台给的字段是一样的
     *
     * 写出所有的字段
     *
     * 并进行set（）与get（）的方法，以便调用与显示出数据
     * */

    /**
     * tfCustomerList : [{"customerId":"06efc018-64d7-4824-882c-fd3eb834fe60","userId":"c43344b3-9148-433e-b609-cb700483a7b7","customerComplete":"0","customerName":"000","customerPhone":"111","customerCompany":"222","customerAddress":"","customerSex":null,"customerMarry":null,"customerPhoto":null,"customerMobile":null,"customerBirthday":null,"customerBir":null,"customerBirdate":null,"customerLike":null,"customerStatus":null,"customerJob":null,"customerRemark":"","customerAllmoney":null,"customerSpare1":null,"customerSpare2":null,"customerSpare3":null,"customerSpare4":null,"customerSpare5":null},{"customerId":"329b4c4f-f8d4-427c-980b-f9eb76ac3418","userId":"c43344b3-9148-433e-b609-cb700483a7b7","customerComplete":"0","customerName":"777","customerPhone":"888","customerCompany":"999","customerAddress":"","customerSex":null,"customerMarry":null,"customerPhoto":null,"customerMobile":null,"customerBirthday":null,"customerBir":null,"customerBirdate":null,"customerLike":null,"customerStatus":null,"customerJob":null,"customerRemark":"","customerAllmoney":null,"customerSpare1":null,"customerSpare2":null,"customerSpare3":null,"customerSpare4":null,"customerSpare5":null}]
     * tfCustomer : null
     * tfBusinessReportList : null
     * tfBusinessListList : null
     */

    private Object tfCustomer;
    /**
     * customerId : 06efc018-64d7-4824-882c-fd3eb834fe60
     * userId : c43344b3-9148-433e-b609-cb700483a7b7
     * customerComplete : 0
     * customerName : 000
     * customerPhone : 111
     * customerCompany : 222
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

    private List<TfCustomerListBean> tfCustomerList;

    public Object getTfCustomer() {
        return tfCustomer;
    }

    public void setTfCustomer(Object tfCustomer) {
        this.tfCustomer = tfCustomer;
    }

    public List<TfCustomerListBean> getTfCustomerList() {
        return tfCustomerList;
    }

    public void setTfCustomerList(List<TfCustomerListBean> tfCustomerList) {
        this.tfCustomerList = tfCustomerList;
    }

    public static class TfCustomerListBean {
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
        private Object customerSpare4;

        public Object getCustomerSpare4() {
            return customerSpare4;
        }

        public void setCustomerSpare4(Object customerSpare4) {
            this.customerSpare4 = customerSpare4;
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
    }
}
