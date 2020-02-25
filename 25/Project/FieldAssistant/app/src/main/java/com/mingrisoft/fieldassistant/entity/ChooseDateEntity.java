package com.mingrisoft.fieldassistant.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/6/27 0027.
 */
public class ChooseDateEntity {
    /**
     * 此实体类是为了解析“打卡”页面的里面选择不同的日期显示出不同日期的数据
     *
     * 字段要和后台给的字段是一样的
     *
     * 写出所有的字段
     *
     * 并进行set（）与get（）的方法，以便调用与显示出数据
     * */

    /**
     * amId : a
     * userId : c43344b3-9148-433e-b609-cb700483a7b7
     * amData : 2016-06-01
     * amStart : 1
     * amStartt : null
     * amStartr : 0
     * amSplace : null
     * amSphoto : null
     * amEnd : 1
     * amEndt : null
     * amEndr : 1
     * amEplace : null
     * amEphoto : null
     * amSpare1 : null
     * amSpare2 : null
     * amSpare3 : null
     * amSpare4 : null
     * amSpare5 : null
     */

    private List<TfAttenceMainBean> tfAttenceMain;

    public List<TfAttenceMainBean> getTfAttenceMain() {
        return tfAttenceMain;
    }

    public void setTfAttenceMain(List<TfAttenceMainBean> tfAttenceMain) {
        this.tfAttenceMain = tfAttenceMain;
    }

    public static class TfAttenceMainBean {
        private String amId;
        private String userId;
        private String amData;
        private String amStart;
        private Object amStartt;
        private String amStartr;
        private Object amSplace;
        private Object amSphoto;
        private String amEnd;
        private Object amEndt;
        private String amEndr;
        private Object amEplace;
        private Object amEphoto;

        public String getAmId() {
            return amId;
        }

        public void setAmId(String amId) {
            this.amId = amId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getAmData() {
            return amData;
        }

        public void setAmData(String amData) {
            this.amData = amData;
        }

        public String getAmStart() {
            return amStart;
        }

        public void setAmStart(String amStart) {
            this.amStart = amStart;
        }

        public Object getAmStartt() {
            return amStartt;
        }

        public void setAmStartt(Object amStartt) {
            this.amStartt = amStartt;
        }

        public String getAmStartr() {
            return amStartr;
        }

        public void setAmStartr(String amStartr) {
            this.amStartr = amStartr;
        }

        public Object getAmSplace() {
            return amSplace;
        }

        public void setAmSplace(Object amSplace) {
            this.amSplace = amSplace;
        }

        public Object getAmSphoto() {
            return amSphoto;
        }

        public void setAmSphoto(Object amSphoto) {
            this.amSphoto = amSphoto;
        }

        public String getAmEnd() {
            return amEnd;
        }

        public void setAmEnd(String amEnd) {
            this.amEnd = amEnd;
        }

        public Object getAmEndt() {
            return amEndt;
        }

        public void setAmEndt(Object amEndt) {
            this.amEndt = amEndt;
        }

        public String getAmEndr() {
            return amEndr;
        }

        public void setAmEndr(String amEndr) {
            this.amEndr = amEndr;
        }

        public Object getAmEplace() {
            return amEplace;
        }

        public void setAmEplace(Object amEplace) {
            this.amEplace = amEplace;
        }

        public Object getAmEphoto() {
            return amEphoto;
        }

        public void setAmEphoto(Object amEphoto) {
            this.amEphoto = amEphoto;
        }
    }
}
