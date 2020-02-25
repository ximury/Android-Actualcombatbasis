package mrkj.flowersdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import mrkj.flowersdemo.view.MyProgressBar;

/**
 * 测试自定义控件MyProgress
 */
public class MyProgressActivity extends AppCompatActivity {

    private int counts = 0;
    private boolean isRunning = true;//子线程的开关
    private MyProgressBar myProgressBar;//声明控件
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 1){
                ++counts;//模拟进度
                if (counts == 100){
                    isRunning = false;//关闭子线程
                    return false;
                }
                myProgressBar.setProgress(counts);
            }
            return false;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_progress);
        //初始化控件
        myProgressBar = (MyProgressBar) findViewById(R.id.test_myprogressbar);
        //设置一个子线程来模拟进度
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRunning){
                    try {
                        Thread.sleep(100);
                        handler.sendEmptyMessage(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
