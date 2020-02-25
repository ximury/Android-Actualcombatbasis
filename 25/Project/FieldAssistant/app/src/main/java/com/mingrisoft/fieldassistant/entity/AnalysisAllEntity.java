package com.mingrisoft.fieldassistant.entity;

/**
 * Created by Administrator on 2016/7/8 0008.
 */
public class AnalysisAllEntity  {

    /**
     * 此实体类是为了解析“业务分析”页面的数据
     *
     * 字段要和后台给的字段是一样的
     *
     * 写出所有的字段
     *
     * 并进行set（）与get（）的方法，以便调用与显示出数据
     * */

    /**
     * busPer : 2
     * busAll : 2
     * viPer : 7
     * viAll : 7
     * cusPer : 14
     * cusAll : 14
     * left : 5
     * mid : 100.00
     * right : 1
     */

    private String busPer;
    private String busAll;
    private String viPer;
    private String viAll;
    private String cusPer;
    private String cusAll;
    private String left;
    private String mid;
    private String right;

    public String getBusPer() {
        return busPer;
    }

    public void setBusPer(String busPer) {
        this.busPer = busPer;
    }

    public String getBusAll() {
        return busAll;
    }

    public void setBusAll(String busAll) {
        this.busAll = busAll;
    }

    public String getViPer() {
        return viPer;
    }

    public void setViPer(String viPer) {
        this.viPer = viPer;
    }

    public String getViAll() {
        return viAll;
    }

    public void setViAll(String viAll) {
        this.viAll = viAll;
    }

    public String getCusPer() {
        return cusPer;
    }

    public void setCusPer(String cusPer) {
        this.cusPer = cusPer;
    }

    public String getCusAll() {
        return cusAll;
    }

    public void setCusAll(String cusAll) {
        this.cusAll = cusAll;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }
}
