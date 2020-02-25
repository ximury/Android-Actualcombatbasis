package com.mingrisoft.fieldassistant.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/7/6 0006.
 */
public class PlanListEntity {


    /**
     * 此实体类是为了解析“工作计划”页面的所有list集合的数据
     *
     * 字段要和后台给的字段是一样的
     *
     * 写出所有的字段
     *
     * 并进行set（）与get（）的方法，以便调用与显示出数据
     * */
    /**
     * jpId : cc388e6d-01dd-4914-a437-ca9efd575c4d
     * userId : c43344b3-9148-433e-b609-cb700483a7b7
     * jpData : 2016-07-06
     * jpTime : 15:58:23
     * jpTime1 : 2016-03-26
     * jpTime2 : 2019-02-20
     * jpTitle : 赤壁床头
     * jpRemark : null
     * jpSpare1 : null
     * jpSpare2 : null
     * jpSpare3 : null
     * jpSpare4 : null
     * jpSpare5 : null
     */

    private List<TfJobPlanListBean> tfJobPlanList;

    public List<TfJobPlanListBean> getTfJobPlanList() {
        return tfJobPlanList;
    }

    public void setTfJobPlanList(List<TfJobPlanListBean> tfJobPlanList) {
        this.tfJobPlanList = tfJobPlanList;
    }

    public static class TfJobPlanListBean {
        private String jpId;
        private String userId;
        private String jpData;
        private String jpTime;
        private String jpTime1;
        private String jpTime2;
        private String jpTitle;
        private Object jpRemark;
        private Object jpSpare1;
        private Object jpSpare2;
        private Object jpSpare3;
        private Object jpSpare4;
        private Object jpSpare5;

        public String getJpId() {
            return jpId;
        }

        public void setJpId(String jpId) {
            this.jpId = jpId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getJpData() {
            return jpData;
        }

        public void setJpData(String jpData) {
            this.jpData = jpData;
        }

        public String getJpTime() {
            return jpTime;
        }

        public void setJpTime(String jpTime) {
            this.jpTime = jpTime;
        }

        public String getJpTime1() {
            return jpTime1;
        }

        public void setJpTime1(String jpTime1) {
            this.jpTime1 = jpTime1;
        }

        public String getJpTime2() {
            return jpTime2;
        }

        public void setJpTime2(String jpTime2) {
            this.jpTime2 = jpTime2;
        }

        public String getJpTitle() {
            return jpTitle;
        }

        public void setJpTitle(String jpTitle) {
            this.jpTitle = jpTitle;
        }

        public Object getJpRemark() {
            return jpRemark;
        }

        public void setJpRemark(Object jpRemark) {
            this.jpRemark = jpRemark;
        }

        public Object getJpSpare1() {
            return jpSpare1;
        }

        public void setJpSpare1(Object jpSpare1) {
            this.jpSpare1 = jpSpare1;
        }

        public Object getJpSpare2() {
            return jpSpare2;
        }

        public void setJpSpare2(Object jpSpare2) {
            this.jpSpare2 = jpSpare2;
        }

        public Object getJpSpare3() {
            return jpSpare3;
        }

        public void setJpSpare3(Object jpSpare3) {
            this.jpSpare3 = jpSpare3;
        }

        public Object getJpSpare4() {
            return jpSpare4;
        }

        public void setJpSpare4(Object jpSpare4) {
            this.jpSpare4 = jpSpare4;
        }

        public Object getJpSpare5() {
            return jpSpare5;
        }

        public void setJpSpare5(Object jpSpare5) {
            this.jpSpare5 = jpSpare5;
        }
    }
}
