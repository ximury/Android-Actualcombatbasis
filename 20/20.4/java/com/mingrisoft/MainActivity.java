package com.mingrisoft;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        WebView webView = (WebView) findViewById(R.id.webView1);       //获取布局管理器中添加的WebView组件
        webView.loadUrl("http://study.mingrisoft.com/index.html");    //指定要加载的网页
        webView.getSettings().setUseWideViewPort(true);    //设置此属性，可任意比例缩放
        webView.getSettings().setLoadWithOverviewMode(true); //设置加载内容自适应屏幕

    }
}
