package mrkj.flowersdemo.ui.bean;

/**
 * 花的实体类
 * Created by Administrator on 2016/7/20.
 */
public class Flower {
    private int index;//索引值
    private int count;//计数
    private int id;//数据库中的唯一标识
    private String name;//花的名称
    private boolean isValid;//数值有效
    /**
     * 无参的构造方法
     */
    public Flower() {
    }

    /**
     * 有参的构造方法
     * @param count
     * @param index
     */
    public Flower(int count, int index) {
        this.count = count;
        this.index = index;
    }

    /**
     * 有参的构造方法
     * @param count
     * @param id
     * @param name
     */
    public Flower(int count, int id, String name) {
        this.count = count;
        this.id = id;
        this.name = name;
    }

    /**
     * 有参的构造方法
     * @param index
     * @param count
     * @param id
     * @param name
     */
    public Flower(int index, int count, int id, String name) {
        this.index = index;
        this.count = count;
        this.id = id;
        this.name = name;
    }

    public Flower setId(int id) {
        this.id = id;
        return this;
    }

    public Flower setName(String name) {
        this.name = name;
        return this;
    }

    public Flower setIndex(int index) {
        this.index = index;
        return this;
    }

    public Flower setCount(int count) {
        this.count = count;
        return this;
    }

    public int getIndex() {
        return index;
    }

    public int getCount() {
        return count;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public  boolean isValid() {
        return isValid;
    }

    public Flower setValid(boolean valid) {
        isValid = valid;
        return this;
    }

    @Override
    public String toString() {
        return "INDEX = " + "【"+ index +"】\t" +"ID = " + "【"+ id +"】\t" +"名称 = " + "【"+ name +"】\t"+"数量 = " + "【"+ count +"】\t";
    }

}
