package com.mingrisoft.fieldassistant.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.Poi;
import com.mingrisoft.fieldassistant.baidu.LocationService;

/**
 * Created by Administrator on 2016/6/20 0020.
 */
public class BaseActivity extends AppCompatActivity {

    private LocationService locationService;            //百度地图的定位服务
    private SharedPreferences sharedPreferences;           //创建一个用于存放定位的位置信息
    private Poi poi;
    private static boolean isExit = false;

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onStart() {
        super.onStart();
        sharedPreferences = getSharedPreferences("share", Context.MODE_PRIVATE);    //初始化数据库
    }

    public void getLocation() {
        // -----------location config ------------
        locationService = ((BaseApplication) getApplication()).locationService;
        //获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        locationService.registerListener(mListener);
        //注册监听
        int type = getIntent().getIntExtra("from", 0);
        if (type == 0) {
            locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        } else if (type == 1) {
            locationService.setLocationOption(locationService.getOption());
        }
        locationService.start();// 定位SDK
    }

    /*****
     * 定位结果回调，重写onReceiveLocation方法，可以直接拷贝如下代码到自己工程中修改
     */
    private BDLocationListener mListener = new BDLocationListener() {

        @Override
        public void onReceiveLocation(final BDLocation location) {
            // TODO Auto-generated method stub
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                StringBuffer sb = new StringBuffer(256);
                sb.append("time : ");
                /**
                 * 时间也可以使用systemClock.elapsedRealtime()方法 获取的是自从开机以来，每次回调的时间；
                 * location.getTime() 是指服务端出本次结果的时间，如果位置不发生变化，则时间不变
                 */
                sb.append("\nlatitude : ");
                sb.append(location.getLatitude());
                sb.append("\nlontitude : ");
                sb.append(location.getLongitude());
                sb.append("\ncity : ");
                sb.append(location.getAddress());
                sb.append("\ncity : ");
                sb.append(location.getCountry());
                sb.append("\ncity : ");
                sb.append(location.getCity());
                sb.append("\ncity : ");
                sb.append(location.getCity());
                Log.e("-*/-*/-*/-*/", "sb:" + sb);
                if (location.getPoiList() != null && !location.getPoiList().isEmpty()) {
                    for (int i = 0; i < location.getPoiList().size(); i++) {
                         poi = (Poi) location.getPoiList().get(0);
                        sb.append(poi.getName() + ";");
                    }
                }
                if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果

                    sb.append("gps定位成功");
                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                    // 运营商信息
                    sb.append("\noperationers : ");
                    sb.append(location.getOperators());
                    sb.append("\ndescribe : ");
                    sb.append("网络定位成功");
                } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                    Toast.makeText(BaseActivity.this, "离线定位成功，离线定位结果也是有效的", Toast.LENGTH_SHORT).show();
                } else if (location.getLocType() == BDLocation.TypeServerError) {
                    Toast.makeText(BaseActivity.this, "服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因", Toast.LENGTH_SHORT).show();
                } else if (location.getLocType() == BDLocation.TypeNetWorkException) {

                    Toast.makeText(BaseActivity.this, "网络不同导致定位失败，请检查网络是否通畅", Toast.LENGTH_SHORT).show();
                } else if (location.getLocType() == BDLocation.TypeCriteriaException) {

                    Toast.makeText(BaseActivity.this, "无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机", Toast.LENGTH_SHORT).show();
                }

                /**
                 * 把从百度地图定位获取到的数据存放到数据库里面
                 * **/
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("city", location.getCity());
                editor.putString("location", poi.getName());
                editor.putString("alllocation",location.getCity()+location.getDistrict()+poi.getName());
                editor.putString("latitude", location.getLatitude()+"");
                editor.putString("longitude", location.getLongitude()+"");
                editor.commit();
                locationService.unregisterListener(mListener);        //为百度地图的服务设置监听
                locationService.stop();
            }
        }

    };
}
