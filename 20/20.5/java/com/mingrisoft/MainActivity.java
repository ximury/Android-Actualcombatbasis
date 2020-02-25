package com.mingrisoft;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Button btn= (Button) findViewById(R.id.btn_help); //获取布局文件中的游戏玩法按钮
        //实现单击按钮跳转游戏指南页面
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置通过Intent跳转游戏指南页面
                Intent intent = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(intent);
            }
        });

    }
}
