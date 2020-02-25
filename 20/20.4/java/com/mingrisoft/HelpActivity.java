package com.mingrisoft;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebView;

public class HelpActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        //设置全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //获取布局管理器中添加的WebView组件
        WebView webview = (WebView) findViewById(R.id.webView1);
        webview.setBackgroundResource(R.drawable.bg_help);    //设置WebView背景图片
        webview.setBackgroundColor(0);                     //设置WebView背景色为透明
        //创建一个字符串构建器，将要显示的HTML内容放置在该构建器中
        StringBuilder sb = new StringBuilder();
        sb.append("<br>");
        sb.append("<br>");
        sb.append("<span style=font-size:20px><div>疯狂动物来找茬操作指南：</div></span>");
        sb.append("<ul>");
        sb.append("<span style=font-size:20px><li>一共三关。</li></span>");
        sb.append("<br>");
        sb.append("<span style=font-size:20px><li>找出两张图片的5处不同点。</li></span>");
        sb.append("<br>");
        sb.append("<span style=font-size:20px><li>剩余时间越长，分数越高。</li></span>");
        sb.append("<br>");
        sb.append("<span style=font-size:20px><li>每过完一个关卡将询问是否进入</li></span>");
        sb.append("<span style=font-size:20px>下一关。</span>");
        sb.append("</ul>");
        webview.loadDataWithBaseURL(null, sb.toString(), "text/html", "utf-8", null);    //加载数据

    }
}
