package com.mingrisoft;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {

    private ImageButton play, pause, stop;     // 定义播放、暂停和停止按钮
    private MediaPlayer mediaPlayer;        // 定义MediaPlayer对象
    private SurfaceHolder surfaceHolder;    // 定义SurfaceHolder对象
    private boolean noPlay = true;          //定义播放状态


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        play = (ImageButton) findViewById(R.id.play);                  // 获取播放按钮对象
        pause = (ImageButton) findViewById(R.id.pause);                // 获取暂停按钮对象
        stop = (ImageButton) findViewById(R.id.stop);                  // 获取停止按钮对象
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.surfaceView);  //获取SurfaceView组件
        surfaceHolder = surfaceView.getHolder();                     //获取SurfaceHolder
        pause.setEnabled(false);                                      //设置暂停按钮不可用
        stop.setEnabled(false);                                       //设置停止按钮不可用
        mediaPlayer = new MediaPlayer();                               //创建MediaPlayer对象
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);    //设置多媒体的类型


        play.setOnClickListener(new View.OnClickListener() {          //实现播放与继续播放功能
            @Override
            public void onClick(View v) {
                if (noPlay) {                   //如果没有播放视频
                    play();                     //调用play()方法实现播放功能
                    noPlay = false;            //设置播放状态为正在播放
                } else {
                    mediaPlayer.start();       //继续播放视频
                }
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {  //实现暂停功能
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) { //如果视频处于播放状态
                    mediaPlayer.pause();       //暂停视频的播放
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {  //实现停止功能
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {   //如果视频处于播放状态
                    mediaPlayer.stop();          // 停止播放
                    noPlay = true;               //设置播放状态为没有播放
                    pause.setEnabled(false);     // 设置“暂停”按钮不可用
                    stop.setEnabled(false);      //设置“停止”按钮不可用
                }
            }
        });
        // 为MediaPlayer对象添加完成事件监听器
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(MainActivity.this, "视频播放完毕！", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void play() {  //创建play()方法，在该方法中实现视频的播放功能
        mediaPlayer.reset();                        //重置MediaPlayer
        mediaPlayer.setDisplay(surfaceHolder);    //把视频画面输出到SurfaceView
        try {
            // 模拟器的SD卡上的视频文件
            mediaPlayer.setDataSource(Environment.getExternalStorageDirectory() + "/video.mp4");
            mediaPlayer.prepare();    // 预加载
        } catch (Exception e) {       //输出异常信息
            e.printStackTrace();
        }
        mediaPlayer.start(); // 开始播放
        pause.setEnabled(true);    // 设置“暂停”按钮可用
        stop.setEnabled(true);     //设置“停止”按钮可用
    }

    @Override
    protected void onDestroy() {  //当前Activity销毁时，停止正在播放的视频，并释放MediaPlayer所占用的资源
        super.onDestroy();
        if (mediaPlayer != null) {          //如果MediaPlayer不为空
            if (mediaPlayer.isPlaying()) {  //如果处于播放状态
                mediaPlayer.stop();         // 停止播放视频
            }
            // Activity销毁时停止播放，释放资源。不做这个操作，即使退出还是能听到视频播放的声音
            mediaPlayer.release();
        }
    }

}
