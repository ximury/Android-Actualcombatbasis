package com.mingrisoft.fieldassistant.set;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingrisoft.fieldassistant.R;
import com.mingrisoft.fieldassistant.home.BaseApplication;
import com.mingrisoft.fieldassistant.home.MainActivity;
import com.mingrisoft.fieldassistant.tool.DataCleanManager;
import com.mingrisoft.fieldassistant.tool.SwitchButton;
import com.mingrisoft.fieldassistant.welcome.EnterActivity;

/**
 * Created by dllo on 16/3/2.
 */
public class SetActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_logout;          //定义退出按钮
    private SwitchButton switch_accept_news, switch_sound, switch_shock;  //定义滑动的按钮
    private LinearLayout ll_sound, ll_shock;
    private SharedPreferences sharedPreferences;
    private ImageButton backBtn;
    private TextView clean_cache,cache_data;
    private DataCleanManager dataCleanManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        init();//初始化控件

    }
        /**
         * 初始化控件
         * */
    private void init() {
        sharedPreferences = getSharedPreferences("share", MODE_PRIVATE);
        btn_logout = (Button) findViewById(R.id.btn_logout);
        switch_accept_news = (SwitchButton) findViewById(R.id.switch_accept_news);
        switch_sound = (SwitchButton) findViewById(R.id.switch_sound);
        switch_shock = (SwitchButton) findViewById(R.id.switch_shock);
        ll_sound = (LinearLayout) findViewById(R.id.ll_sound);
        ll_shock = (LinearLayout) findViewById(R.id.ll_shock);
        switch_accept_news.setOnClickListener(this);
        switch_sound.setOnClickListener(this);
        switch_shock.setOnClickListener(this);
        btn_logout.setOnClickListener(this);
        backBtn = (ImageButton) findViewById(R.id.back_im_btn);
        backBtn.setOnClickListener(this);
        clean_cache = (TextView)findViewById(R.id.clean_cache);
        cache_data = (TextView) findViewById(R.id.cache_data);
        clean_cache.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            cache_data.setText(dataCleanManager.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 点击事件
     * */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.switch_accept_news:            //点击新消息提醒设置按钮事件
                if (switch_accept_news.isSwitchOpen()) {
                    switch_accept_news.closeSwitch();
                    ll_sound.setVisibility(View.GONE);
                    ll_shock.setVisibility(View.GONE);
                } else {
                    switch_accept_news.openSwitch();
                    ll_sound.setVisibility(View.VISIBLE);
                    ll_shock.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.switch_sound:                    //点击声音的滑动按钮事件
                if (switch_sound.isSwitchOpen()) {
                    switch_sound.closeSwitch();
                } else {
                    switch_sound.openSwitch();
                }
                break;
            case R.id.switch_shock:              //点击震动的滑动按钮事件
                if (switch_shock.isSwitchOpen()) {
                    switch_shock.closeSwitch();
                } else {
                    switch_shock.openSwitch();
                }
                break;
            case R.id.btn_logout:       //退出登录  将数据库里面的数据清空
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(this, EnterActivity.class);
                startActivity(intent);
                BaseApplication.destroyActivity("mainActivity");
                finish();
                break;

            case R.id.back_im_btn:   //返回上一个页面
                finish();
                break;
            case R.id.clean_cache:  // 清除缓存
                dataCleanManager.clearAllCache(this);
                onStart();
                break;
        }
    }
}
