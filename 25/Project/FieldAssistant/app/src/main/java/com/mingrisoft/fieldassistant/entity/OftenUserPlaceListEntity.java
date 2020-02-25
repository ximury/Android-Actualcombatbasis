package com.mingrisoft.fieldassistant.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/7/9 0009.
 */
public class OftenUserPlaceListEntity {
    /**
     * 此实体类是为了解析“导航”页面的常用位置的数据
     *
     * 字段要和后台给的字段是一样的
     *
     * 写出所有的字段
     *
     * 并进行set（）与get（）的方法，以便调用与显示出数据
     * */
    /**
     * commonId : e91c84db-e164-4056-a5e0-a352dbbda3c3
     * userId : c43344b3-9148-433e-b609-cb700483a7b7
     * commonContent : 长春西站
     * commonSpare1 : null
     * commonSpare2 : null
     * commonSpare3 : null
     * commonSpare4 : null
     * commonSpare5 : null
     */

    private List<TfCommonListBean> tfCommonList;

    public List<TfCommonListBean> getTfCommonList() {
        return tfCommonList;
    }

    public void setTfCommonList(List<TfCommonListBean> tfCommonList) {
        this.tfCommonList = tfCommonList;
    }

    public static class TfCommonListBean {
        private String commonId;
        private String userId;
        private String commonContent;
        private Object commonSpare1;
        private Object commonSpare2;
        private Object commonSpare3;
        private Object commonSpare4;
        private Object commonSpare5;

        public String getCommonId() {
            return commonId;
        }

        public void setCommonId(String commonId) {
            this.commonId = commonId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getCommonContent() {
            return commonContent;
        }

        public void setCommonContent(String commonContent) {
            this.commonContent = commonContent;
        }

        public Object getCommonSpare1() {
            return commonSpare1;
        }

        public void setCommonSpare1(Object commonSpare1) {
            this.commonSpare1 = commonSpare1;
        }

        public Object getCommonSpare2() {
            return commonSpare2;
        }

        public void setCommonSpare2(Object commonSpare2) {
            this.commonSpare2 = commonSpare2;
        }

        public Object getCommonSpare3() {
            return commonSpare3;
        }

        public void setCommonSpare3(Object commonSpare3) {
            this.commonSpare3 = commonSpare3;
        }

        public Object getCommonSpare4() {
            return commonSpare4;
        }

        public void setCommonSpare4(Object commonSpare4) {
            this.commonSpare4 = commonSpare4;
        }

        public Object getCommonSpare5() {
            return commonSpare5;
        }

        public void setCommonSpare5(Object commonSpare5) {
            this.commonSpare5 = commonSpare5;
        }
    }
}
