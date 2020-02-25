package com.mingrisoft.fieldassistant.home;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.mingrisoft.fieldassistant.baidu.LocationService;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by wangzeqi on 2016/5/27.
 */
public class BaseApplication extends Application {

    public LocationService locationService;       //百度地图定位的服务
    private static String url = "http://192.168.0.166:8080/outHelp/phone/";           //用于加载数据的拼接的网址
    private static String imageUrl = "http://192.168.0.166:8080/outHelp/upload/";       //用于加载图片的拼接的网址
    private static Context context;
    private static Map<String, Activity> destroyMap = new HashMap<>();
    private static final String PHOTO_FILE_NAME = "myPhoto.jpg";                        // 图片名称
    private  static  File tempFile = new File(Environment.getExternalStorageDirectory(), PHOTO_FILE_NAME);
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        locationService = new LocationService(getApplicationContext());  //初始化百度地图的服务
    }
    public static Context getContext() {
        return context;
    }

    public static String getImageUrl() {
        return imageUrl;
    }

    public static void setImageUrl(String imageUrl) {
        BaseApplication.imageUrl = imageUrl;
    }

    //获取数据的接口
    public static String getUrl() {
        return url;
    }

    //获取要上传照片的路径
    public static File getTempFile() {
        return tempFile;
    }



    /**
     * 添加到销毁的列队
     * <p/>
     * 要销毁的activity
     */
    public static void addDestroyActiivty(Activity activity, String activityName) {
        destroyMap.put(activityName, activity);
    }


    /**
     * 销毁指定的activity
     */
    public static void destroyActivity(String activityName) {
        Set<String> keySet = destroyMap.keySet();
        for (String key : keySet) {
            destroyMap.get(key).finish();
        }
    }
}
