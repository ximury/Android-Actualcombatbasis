package com.mingrisoft.fieldassistant.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/7/9 0009.
 */
public class RakingEntity {

    /**
     * 此实体类是为了解析“业务分析”页面的琅琊榜的数据
     *
     * 字段要和后台给的字段是一样的
     *
     * 写出所有的字段
     *
     * 并进行set（）与get（）的方法，以便调用与显示出数据
     * */

    private String num;
    private String name ;
    private String id;
    private String money;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    //
//    private List<String> list;
//
//    public List<String> getList() {
//        return list;
//    }
//
//    public void setList(List<String> list) {
//        this.list = list;
//    }
}
