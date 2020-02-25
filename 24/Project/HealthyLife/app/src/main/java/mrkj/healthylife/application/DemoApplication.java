package mrkj.healthylife.application;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;

import mrkj.healthylife.R;
import mrkj.healthylife.utils.BringData;
import mrkj.healthylife.utils.GetLocation;
import mrkj.healthylife.utils.SaveKeyValues;

/**
 * Created by Administrator on 2016/5/25.
 */
public class DemoApplication extends Application{
    public static Bitmap[] bitmaps = new Bitmap[5];
    public static String[] shuoming = new String[5];
    @Override
    public void onCreate() {
        super.onCreate();
        //定位
        new GetLocation(getApplicationContext());
        //实例化sharedPreferences
        SaveKeyValues.createSharePreferences(this);
        bitmaps[0] = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.mipmap.mrkj_fushen1);
        bitmaps[1] = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.mipmap.mrkj_fuwocheng1);
        bitmaps[2] = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.mipmap.mrkj_gunlun1);
        bitmaps[3] = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.mipmap.mrkj_wotui1);
        bitmaps[4] = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.mipmap.mrkj_sanwanju1);

        shuoming[0] = "俯身哑铃飞鸟";
        shuoming[1] = "俯卧撑";
        shuoming[2] = "滚轮支点俯卧撑";
        shuoming[3] = "平板卧推";
        shuoming[4] = "仰卧平板杠铃肱三弯举";
        //将食物热量
        int saveDateIndex = SaveKeyValues.getIntValues("date_index",0);
        Log.e("数据库数否被存入", "【" + saveDateIndex + "】");
        if (saveDateIndex == 0){
            try {
                SaveKeyValues.putIntValues("date_index", 1);
                BringData.getDataFromAssets(getApplicationContext());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        DBHelper dbHelper = new DBHelper();
//        Cursor cursor = dbHelper.selectAllDataOfTable("hot");
//        while (cursor.moveToNext()){
//            String name = cursor.getString(cursor.getColumnIndex("name"));
//            String type_name = cursor.getString(cursor.getColumnIndex("type_name"));
//            Log.e("食物","【" + name + "】" + "、"+"【" + type_name + "】");
//        }
//        cursor.close();
    }
}
