package com.mingrisoft.fieldassistant.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/6/23 0023.
 */
public class SignedEntity {


    /**
     * 此实体类是为了解析“考勤”页面的打卡的数据
     *
     * 字段要和后台给的字段是一样的
     *
     * 写出所有的字段
     *
     * 并进行set（）与get（）的方法，以便调用与显示出数据
     * */
    /**
     * amId : b6ea7db4-7025-4971-a9f1-743afbfe7d83
     * userId : c43344b3-9148-433e-b609-cb700483a7b7
     * amData : 2016-07-01
     * amStart : 1
     * amStartt : 11:19:44
     * amStartr : 1
     * amSplace : 长春市南关区财富领域
     * amSphoto : 1467343184787.jpg
     * amEnd : 1
     * amEndt : 11:20:48
     * amEndr : 1
     * amEplace : 长春市南关区财富领域
     * amEphoto : 1467343248332.jpg
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
        private String amStartt;
        private String amStartr;
        private String amSplace;
        private String amSphoto;
        private String amEnd;
        private String amEndt;
        private String amEndr;
        private String amEplace;
        private String amEphoto;
        private Object amSpare1;
        private Object amSpare2;
        private Object amSpare3;
        private Object amSpare4;
        private Object amSpare5;

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

        public String getAmStartt() {
            return amStartt;
        }

        public void setAmStartt(String amStartt) {
            this.amStartt = amStartt;
        }

        public String getAmStartr() {
            return amStartr;
        }

        public void setAmStartr(String amStartr) {
            this.amStartr = amStartr;
        }

        public String getAmSplace() {
            return amSplace;
        }

        public void setAmSplace(String amSplace) {
            this.amSplace = amSplace;
        }

        public String getAmSphoto() {
            return amSphoto;
        }

        public void setAmSphoto(String amSphoto) {
            this.amSphoto = amSphoto;
        }

        public String getAmEnd() {
            return amEnd;
        }

        public void setAmEnd(String amEnd) {
            this.amEnd = amEnd;
        }

        public String getAmEndt() {
            return amEndt;
        }

        public void setAmEndt(String amEndt) {
            this.amEndt = amEndt;
        }

        public String getAmEndr() {
            return amEndr;
        }

        public void setAmEndr(String amEndr) {
            this.amEndr = amEndr;
        }

        public String getAmEplace() {
            return amEplace;
        }

        public void setAmEplace(String amEplace) {
            this.amEplace = amEplace;
        }

        public String getAmEphoto() {
            return amEphoto;
        }

        public void setAmEphoto(String amEphoto) {
            this.amEphoto = amEphoto;
        }

        public Object getAmSpare1() {
            return amSpare1;
        }

        public void setAmSpare1(Object amSpare1) {
            this.amSpare1 = amSpare1;
        }

        public Object getAmSpare2() {
            return amSpare2;
        }

        public void setAmSpare2(Object amSpare2) {
            this.amSpare2 = amSpare2;
        }

        public Object getAmSpare3() {
            return amSpare3;
        }

        public void setAmSpare3(Object amSpare3) {
            this.amSpare3 = amSpare3;
        }

        public Object getAmSpare4() {
            return amSpare4;
        }

        public void setAmSpare4(Object amSpare4) {
            this.amSpare4 = amSpare4;
        }

        public Object getAmSpare5() {
            return amSpare5;
        }

        public void setAmSpare5(Object amSpare5) {
            this.amSpare5 = amSpare5;
        }
    }
}
