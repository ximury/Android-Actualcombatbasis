package mrkj.flowersdemo.ui.bean;

/**
 * Created by Administrator on 2016/7/30.
 */
public class RecordDate {
    private String date;//日期
    private int year;//年
    private int month;//月
    private int day;//日
    private int week;//星期的第几天周日~周六（1~7）
    private long time;//种花的时间
    private int plant_counts;//种植的画的数量
    private int share_counts;//分享的花的个数
    private float hour;//小时


    public float getHour() {
        return hour;
    }

    public RecordDate setHour(float hour) {
        this.hour = hour;
        return this;
    }

    public RecordDate setPlant_counts(int plant_counts) {
        this.plant_counts = plant_counts;
        return this;
    }

    public RecordDate setShare_counts(int share_counts) {
        this.share_counts = share_counts;
        return this;
    }

    public int getPlant_counts() {
        return plant_counts;
    }

    public int getShare_counts() {
        return share_counts;
    }

    public String getDate() {
        return date;
    }

    public RecordDate setDate(String date) {
        this.date = date;
        return this;
    }

    public int getYear() {
        return year;
    }

    public RecordDate setYear(int year) {
        this.year = year;
        return this;
    }

    public int getMonth() {
        return month;
    }

    public RecordDate setMonth(int month) {
        this.month = month;
        return this;
    }

    public int getDay() {
        return day;
    }

    public RecordDate setDay(int day) {
        this.day = day;
        return this;
    }

    public int getWeek() {
        return week;
    }

    public RecordDate setWeek(int week) {
        this.week = week;
        return this;
    }

    public long getTime() {
        return time;
    }

    public RecordDate setTime(long time) {
        this.time = time;
        return this;
    }
}
