package com.mingrisoft.fieldassistant.entity;

/**
 * Created by Administrator on 2016/6/24 0024.
 */
public class EnterEntity {


    /**
     * 此实体类是为了解析“登录时”页面返回来的数据
     *
     * 字段要和后台给的字段是一样的
     *
     * 写出所有的字段
     *
     * 并进行set（）与get（）的方法，以便调用与显示出数据
     * */
    /**
     * menuId : null
     * operateColumn : null
     * id : c43344b3-9148-433e-b609-cb700483a7b7
     * personid : null
     * username : u1
     * password : 000
     * loginip : null
     * enablestate : 1
     * enabletime : null
     * logintime : null
     * createtime : 2016-06-24 10:53:46
     * updatetime : null
     * name : 张三
     * sex : 1
     * engname :
     * type :
     * phone :
     * tel :
     * email :
     * address :
     * zipcode :
     * loginFlag : false
     * departmentId : 3a0d04c6-05df-4dc0-b485-a5b9b578e056
     * departmentName : 销售一部
     * departmentCode : 001
     * roleId : null
     * roleName : null
     * updateFlag : false
     */

    private String id;
    private String username;
    private String password;
    private Object enabletime;
    private String name;
    private String departmentName;
    private String departmentId;


    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Object getEnabletime() {
        return enabletime;
    }

    public void setEnabletime(Object enabletime) {
        this.enabletime = enabletime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
