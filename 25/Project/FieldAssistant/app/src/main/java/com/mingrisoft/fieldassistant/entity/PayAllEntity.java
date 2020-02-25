package com.mingrisoft.fieldassistant.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/7/7 0007.
 */
public class PayAllEntity  {


    /**
     * 此实体类是为了解析“损耗报表”页面的数据
     *
     * 字段要和后台给的字段是一样的
     *
     * 写出所有的字段
     *
     * 并进行set（）与get（）的方法，以便调用与显示出数据
     * */
    /**
     * lossId : 0024efc2-9d2c-43ee-af51-76b294fd60ec
     * userId : c43344b3-9148-433e-b609-cb700483a7b7
     * lossName : 5555
     * lossMoney : 69858
     * lossStatus : 1
     * lossEnd : null
     * lossSpare1 : null
     * lossSpare2 : 2016-07-05
     * lossSpare3 : null
     * lossSpare4 : null
     * lossSpare5 : null
     */

    private List<TfLossListBean> tfLossList;

    public List<TfLossListBean> getTfLossList() {
        return tfLossList;
    }

    public void setTfLossList(List<TfLossListBean> tfLossList) {
        this.tfLossList = tfLossList;
    }

    public static class TfLossListBean {
        private String lossId;
        private String userId;
        private String lossName;
        private int lossMoney;
        private String lossStatus;
        private Object lossEnd;
        private Object lossSpare1;
        private String lossSpare2;
        private Object lossSpare3;
        private Object lossSpare4;
        private Object lossSpare5;

        public String getLossId() {
            return lossId;
        }

        public void setLossId(String lossId) {
            this.lossId = lossId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getLossName() {
            return lossName;
        }

        public void setLossName(String lossName) {
            this.lossName = lossName;
        }

        public int getLossMoney() {
            return lossMoney;
        }

        public void setLossMoney(int lossMoney) {
            this.lossMoney = lossMoney;
        }

        public String getLossStatus() {
            return lossStatus;
        }

        public void setLossStatus(String lossStatus) {
            this.lossStatus = lossStatus;
        }

        public Object getLossEnd() {
            return lossEnd;
        }

        public void setLossEnd(Object lossEnd) {
            this.lossEnd = lossEnd;
        }

        public Object getLossSpare1() {
            return lossSpare1;
        }

        public void setLossSpare1(Object lossSpare1) {
            this.lossSpare1 = lossSpare1;
        }

        public String getLossSpare2() {
            return lossSpare2;
        }

        public void setLossSpare2(String lossSpare2) {
            this.lossSpare2 = lossSpare2;
        }

        public Object getLossSpare3() {
            return lossSpare3;
        }

        public void setLossSpare3(Object lossSpare3) {
            this.lossSpare3 = lossSpare3;
        }

        public Object getLossSpare4() {
            return lossSpare4;
        }

        public void setLossSpare4(Object lossSpare4) {
            this.lossSpare4 = lossSpare4;
        }

        public Object getLossSpare5() {
            return lossSpare5;
        }

        public void setLossSpare5(Object lossSpare5) {
            this.lossSpare5 = lossSpare5;
        }
    }
}
