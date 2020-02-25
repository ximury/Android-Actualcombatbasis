package com.mingrisoft.fieldassistant.customer;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.GroundOverlayOptions;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.mingrisoft.fieldassistant.R;
import com.mingrisoft.fieldassistant.home.BaseActivity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/14 0014.
 */
public class CustomerLocationActivity extends BaseActivity {


    MapView mMapView = null;    // 地图View
    BaiduMap mBaidumap = null;
    // 定位相关
    LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    boolean isFirstLoc = true; // 是否首次定位
    private Marker mMarkerA;
    private Marker mMarkerB;
    private Marker mMarkerC;
    private Marker mMarkerD;
    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.customer_location_layout);
        mMapView = (MapView) findViewById(R.id.map);
        mBaidumap = mMapView.getMap();
        backBtn = (ImageButton) findViewById(R.id.back_im_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // 开启定位图层
        mBaidumap.setMyLocationEnabled(true);
        // 定位初始化
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        mLocClient.setLocOption(option);
        mLocClient.start();
        initOverlay();
        getLocation();
    }
    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                            // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaidumap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mBaidumap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }
    }

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        // 退出时销毁定位
        mLocClient.stop();
        // 关闭定位图层
        mBaidumap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        super.onDestroy();
    }
    public void initOverlay() {
        // add marker overlay
        LatLng llA = new LatLng(43.836854, 125.333053);
        LatLng llB = new LatLng(43.837854, 125.332053);
        LatLng llC = new LatLng(43.838854, 125.334053);
        LatLng llD = new LatLng(43.833854, 125.331053);
        BitmapDescriptor bitmapDescriptorA = BitmapDescriptorFactory.fromResource(R.drawable.icon_marka);
        BitmapDescriptor bitmapDescriptorB = BitmapDescriptorFactory.fromResource(R.drawable.icon_markb);
        BitmapDescriptor bitmapDescriptorC = BitmapDescriptorFactory.fromResource(R.drawable.icon_markc);
        BitmapDescriptor bitmapDescriptorD = BitmapDescriptorFactory.fromResource(R.drawable.icon_markd);
        MarkerOptions ooA = new MarkerOptions().position(llA).icon(bitmapDescriptorA);
        mMarkerA = (Marker) (mBaidumap.addOverlay(ooA));
        MarkerOptions ooB = new MarkerOptions().position(llB).icon(bitmapDescriptorB);
        mMarkerB = (Marker) (mBaidumap.addOverlay(ooB));
        MarkerOptions ooC = new MarkerOptions().position(llC).icon(bitmapDescriptorC);
        mMarkerC = (Marker) (mBaidumap.addOverlay(ooC));
        MarkerOptions ooD = new MarkerOptions().position(llD).icon(bitmapDescriptorD);
        mMarkerD = (Marker) (mBaidumap.addOverlay(ooD));
//        ArrayList<BitmapDescriptor> giflist = new ArrayList<BitmapDescriptor>();
//        giflist.add(bitmapDescriptorA);
//        giflist.add(bitmapDescriptorB);
//        giflist.add(bitmapDescriptorC);
//        giflist.add(bitmapDescriptorD);
//        MarkerOptions ooE = new MarkerOptions().position(llD).icons(giflist)
//                .zIndex(0).period(10);
//        mMarkerC = (Marker) (mBaidumap.addOverlay(ooE));
        mBaidumap.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {
            public void onMarkerDrag(Marker marker) {
            }

            public void onMarkerDragEnd(Marker marker) {
                Toast.makeText(
                        CustomerLocationActivity.this,
                        "拖拽结束，新位置：" + marker.getPosition().latitude + ", "
                                + marker.getPosition().longitude,
                        Toast.LENGTH_LONG).show();
            }

            public void onMarkerDragStart(Marker marker) {
            }
        });
    }
}
