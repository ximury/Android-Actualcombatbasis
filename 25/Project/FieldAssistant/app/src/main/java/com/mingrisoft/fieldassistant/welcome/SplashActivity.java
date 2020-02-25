package com.mingrisoft.fieldassistant.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.mingrisoft.fieldassistant.home.MainActivity;
import com.mingrisoft.fieldassistant.R;


/***
 *
 * 此页面为闪屏页
 *  再次页停顿2秒
 *
 *  在停顿的1.5秒内从网络向下拉取数据
 * */
public class SplashActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGHT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {//延迟发送runnable对象，封装成message
            @Override
            public void run() {
                //从闪屏页跳转到主页
                //在闪屏页停留3秒
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }

        }, SPLASH_DISPLAY_LENGHT);
    }
}
