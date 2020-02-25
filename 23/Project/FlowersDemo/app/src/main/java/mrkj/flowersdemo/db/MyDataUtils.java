package mrkj.flowersdemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mrkj.flowersdemo.ui.bean.Flower;
import mrkj.flowersdemo.ui.bean.RecordDate;
import mrkj.flowersdemo.ui.utils.L;

/**
 * 数据操作类
 * Created by Administrator on 2016/7/29.
 */
public class MyDataUtils {

    /**
     * 更改其中一列的数据信息
     * @param context
     * @param table
     * @param column
     * @param value
     */
    public static long upDateData(Context context,String table,String column,int value){
        DatasDao datasDao = new DatasDao(context);
        Cursor cursor = datasDao.selectColumn(table,new String[]{"_id",column});//获取游标
        cursor.moveToLast();//游标移到到最后一行
        int id = cursor.getInt(cursor.getColumnIndex("_id"));//查询出该列的ID
        int select = cursor.getInt(cursor.getColumnIndex(column));//查询出该列的数据
        cursor.close();
        ContentValues values = new ContentValues();
        values.put(column,value + select);//赋值
        long result = datasDao.updateValue(table,values,"_id = ?",new String[]{String.valueOf(id)});
        datasDao.close();
        return result;
    }

    /**
     * 获取分享和种植的数据
     * @param context
     * @return
     */
    public static Map<String,Integer> getTodayWithYesterdayData(Context context){
        DatasDao datasDao = new DatasDao(context);
        Map<String , Integer>  map = new HashMap<>();
        Cursor cursor = datasDao.selectAll("date");
        //今天
        cursor.moveToLast();//游标移至最后一行
        int today_plant = cursor.getInt(cursor.getColumnIndex("plant"));
        int today_share = cursor.getInt(cursor.getColumnIndex("share"));
        map.put("today_plant" ,today_plant);
        map.put("today_share" ,today_share);
        //昨天
        cursor.moveToPrevious();//游标移动到上一行
        int yesterday_plant = cursor.getInt(cursor.getColumnIndex("plant"));
        int yesterday_share = cursor.getInt(cursor.getColumnIndex("share"));
        String date_str = cursor.getString(cursor.getColumnIndex("time_str"));
        L.e("昨日--》日期",date_str);
        map.put("yesterday_plant" ,yesterday_plant);
        map.put("yesterday_share" ,yesterday_share);
        cursor.close();
        datasDao.close();
        return map;
    }

    /**
     * 查询七日的数据信息
     * @param context
     * @return
     */
    public static ArrayList<RecordDate> getSevenDaysPlantTime(Context context){
        ArrayList<RecordDate> list = new ArrayList<>();
        DatasDao datasDao = new DatasDao(context);
        Cursor cursor = datasDao.selectAll("date");
        cursor.moveToLast();
        int i = 0;
        while (i < 7){
            int day = cursor.getInt(cursor.getColumnIndex("day"));
            L.e("day",day + "");
            long time = cursor.getLong(cursor.getColumnIndex("plant_time"));//毫秒
            float hour = (float) (time*0.001)/60/60;
            L.e("hour",hour + "");
            list.add(new RecordDate().setDay(day).setHour(hour));
            cursor.moveToPrevious();
            i++;
        }
        cursor.close();
        datasDao.close();
        Collections.reverse(list);//倒序
        return list;
    }

    /**
     * 更改最后一条数据的种花时间值
     * @param context
     * @param time
     */
    public static long changeLastDataOfPlantTime(Context context,long time){
        DatasDao datasDao = new DatasDao(context);
        Cursor cursor = datasDao.selectColumn("date",new String[]{"_id","plant_time"});
        cursor.moveToLast();
        int id = cursor.getInt(cursor.getColumnIndex("_id"));
        long plant_time = cursor.getLong(cursor.getColumnIndex("plant_time"));
        cursor.close();
        ContentValues values = new ContentValues();
        values.put("plant_time" , (plant_time) + time);
        long result = datasDao.updateValue("date",values,"_id=?",new String[]{String.valueOf(id)});
        return result;
    }

    /**
     * 更改种花的数量
     * @param dao
     * @param index
     * @param addcounts
     * @return
     */
    public static boolean upDataFlowerCount(DatasDao dao ,int index ,int addcounts){
        int id = ++index;//数据的ID值
        int old_count = 0;//数据库中的数据的个数
        int new_count;//新的个数
        Cursor cursor = dao.selectValue2("flower",null,"_id = ?",
                new String[]{String.valueOf(id)}, null,null,null);
        while (cursor.moveToNext()){
            old_count = cursor.getInt(cursor.getColumnIndex("count"));
        }
        new_count = old_count + addcounts;//新的数据个数
        ContentValues values = new ContentValues();
        values.put("count",new_count);
        int result = dao.updateValue("flower",values, "_id=?",new String[]{String.valueOf(id)});
        cursor.close();
        return result > 0? true:false;
    }

    /**
     * 获取flower表中的数据
     * @param dao
     * @return
     */
    public static ArrayList<Flower> getFlowerData(DatasDao dao){
        ArrayList<Flower> flowers = new ArrayList<>();
        Cursor cursor = dao.selectAll("flower");//用于查询flower数据表中的所有数据
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("_id"));//ID值
            String name = cursor.getString(cursor.getColumnIndex("name"));//名称
            int count = cursor.getInt(cursor.getColumnIndex("count"));
            flowers.add(new Flower((id-1),count,id,name));
        }
        cursor.close();//查询结束关闭游标
        return flowers;
    }


    /**
     * 分享成功后屌该方法更新数据
     * @param context
     * @param list
     * @return
     */
    public static boolean shareCallBackUpdateData(Context context,List<Flower> list){
        int resultCode = 0;
        DatasDao datasDao = new DatasDao(context);
        //使用的迭代器
        Iterator<Flower> iterator = list.iterator();
        while (iterator.hasNext()){//如果有元素循环获取对象
            Flower flower = iterator.next();//获取对象
            L.e("遍历",flower.toString());
            ContentValues values = new ContentValues();
            values.put("count",flower.getCount());
            resultCode = datasDao.updateValue(
                    "flower",values,"_id=?",new String[]{String.valueOf(flower.getIndex() + 1)});
        }
        datasDao.close();
        return resultCode > 0?true:false;
    }

    /**
     * 返回数据库中花的数量
     * @param context
     * @return
     */
    public static int getAllFlowerCounts(Context context){
        int counts = 0;
        Cursor cursor = new DatasDao(context).selectAll("flower");
        while (cursor.moveToNext()){
            int singleConut = cursor.getInt(cursor.getColumnIndex("count"));
            counts+=singleConut;
        }
        return counts;
    }
    //*********************************** 测试 ***********************************
    /**
     * 测试数据用
     * @param context
     */
    public static void testData(Context context){
        DatasDao datasDao = new DatasDao(context);
        Cursor cursor1 = datasDao.selectAll("date");
        Cursor cursor2 = datasDao.selectAll("flower");

        while (cursor1.moveToNext()){
            int _id = cursor1.getInt(cursor1.getColumnIndex("_id"));
            String date = cursor1.getString(cursor1.getColumnIndex("time_str"));
            int year = cursor1.getInt(cursor1.getColumnIndex("year"));
            int month = cursor1.getInt(cursor1.getColumnIndex("month"));
            int day = cursor1.getInt(cursor1.getColumnIndex("day"));
            long time = cursor1.getLong(cursor1.getColumnIndex("plant_time"));
            Log.e("日期",_id+","+date+","+year+","+month+","+day);
            Log.e("种植时间",time + "");
        }
        while (cursor2.moveToNext()){
            int _id = cursor2.getInt(cursor2.getColumnIndex("_id"));
            String name = cursor2.getString(cursor2.getColumnIndex("name"));
            int count = cursor2.getInt(cursor2.getColumnIndex("count"));
            Log.e("花",_id+","+name+","+count);
        }
        cursor1.close();
        cursor2.close();
    }

    /**
     * 跟新数据
     * @param datasDao
     */
    public static void upData(DatasDao datasDao){
        //初始化flower表中的数据
        Cursor flower_cursor = datasDao.selectAll("flower");//获取游标
        int data_count = flower_cursor.getCount();//获取数据的条数
        //跟新数据(id从1开始)
        for (int i = 0;i < data_count;i++){
            ContentValues value = new ContentValues();
            value.put("count",0);//设置为0
            datasDao.updateValue("flower",value,"_id=?",new String[]{String.valueOf(i+1)});
        }
        datasDao.close();
    }

    /**
     * 删除指定数据
     * @param context
     */
    public static void delete(Context context ,int id){
        DatasDao datasDao = new DatasDao(context);
        datasDao.deleteValue("date","_id = ?" ,new String[]{String.valueOf(id)});
        datasDao.close();
    }
}
