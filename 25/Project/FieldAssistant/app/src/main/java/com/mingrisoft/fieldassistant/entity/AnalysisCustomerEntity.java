package com.mingrisoft.fieldassistant.entity;

/**
 * Created by Administrator on 2016/7/8 0008.
 */
public class AnalysisCustomerEntity {
    /**
     * 此实体类是为了解析“业务分析里面的客户分析”页面的数据
     *
     * 字段要和后台给的字段是一样的
     *
     * 写出所有的字段
     *
     * 并进行set（）与get（）的方法，以便调用与显示出数据
     * */
    /**
     * num1 : 5
     * num2 : 5
     * num3 : 4
     * num4 : 2
     * num5 : 12
     */

    private String num1;
    private String num2;
    private String num3;
    private String num4;
    private String num5;

    public String getNum1() {
        return num1;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public String getNum2() {
        return num2;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }

    public String getNum3() {
        return num3;
    }

    public void setNum3(String num3) {
        this.num3 = num3;
    }

    public String getNum4() {
        return num4;
    }

    public void setNum4(String num4) {
        this.num4 = num4;
    }

    public String getNum5() {
        return num5;
    }

    public void setNum5(String num5) {
        this.num5 = num5;
    }
}
