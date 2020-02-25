package com.mingrisoft.fieldassistant.entity;

/**
 * Created by Administrator on 2016/7/6 0006.
 */
public class PlanDetailEntity  {


    /**
     * 此实体类是为了解析“工作计划”页面的提交过任务的数据
     *
     * 字段要和后台给的字段是一样的
     *
     * 写出所有的字段
     *
     * 并进行set（）与get（）的方法，以便调用与显示出数据
     * */
    /**
     * jpId : 3a81fec4-a0bc-4478-8433-4b469c7e2906
     * userId : c43344b3-9148-433e-b609-cb700483a7b7
     * jpData : 2016-07-06
     * jpTime : 16:51:45
     * jpTime1 : 1855-10-10
     * jpTime2 : 2056-12-25
     * jpTitle : 跟你说合格
     * jpRemark : 苏DVDv对吧办法发不发吃吧发吧有呀呀呀的DVD不那你不不不v吃方法
     * jpSpare1 : null
     * jpSpare2 : null
     * jpSpare3 : null
     * jpSpare4 : null
     * jpSpare5 : null
     */

    private TfJobPlanBean tfJobPlan;

    public TfJobPlanBean getTfJobPlan() {
        return tfJobPlan;
    }

    public void setTfJobPlan(TfJobPlanBean tfJobPlan) {
        this.tfJobPlan = tfJobPlan;
    }

    public static class TfJobPlanBean {
        private String jpId;
        private String userId;
        private String jpData;
        private String jpTime;
        private String jpTime1;
        private String jpTime2;
        private String jpTitle;
        private String jpRemark;
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

        public String getJpRemark() {
            return jpRemark;
        }

        public void setJpRemark(String jpRemark) {
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
