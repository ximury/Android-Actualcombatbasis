package com.mingrisoft;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            WebView webView= (WebView) findViewById(R.id.webView1);  //获取布局文件中的WebView组件
        webView.getSettings().setJavaScriptEnabled(true); 		//设置JavaScript可用
        webView.setWebChromeClient(new WebChromeClient());      //设置处理JavaScript中的对话框
        webView.loadUrl("http://192.168.1.198:8080/example/javascript.jsp");	   //指定要加载的网页

    }
}
