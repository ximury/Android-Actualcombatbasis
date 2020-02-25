package com.mingrisoft.writenumber;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SelectActivity extends Activity {   //SelectActivity类头部
    MediaPlayer mediaPlayer;    //定义音乐播放器对象
    @Override
    protected void onCreate(Bundle savedInstanceState) {   // onCreate()方法头部
        super.onCreate(savedInstanceState);
        setContentView(R.layout. activity_select);
        if (MainActivity.isPlay==true){	//如果游戏主界面设置背景音乐为播放音乐状态
            PlayMusic();					//调用播放音乐的方法
        }
    }   //onCreate()方法尾部
    private void PlayMusic() {				//播放背景音乐方法
        //创建音乐播放器对象并加载播放音乐文件
        mediaPlayer = MediaPlayer.create(this, R.raw.number_music);
        mediaPlayer.setLooping(true);		//设置音乐循环播放
        mediaPlayer.start();				//启动播放音乐
    }

    //该方法实现选择数字界面停止时，背景音乐停止
    protected void onStop() {
        super.onStop();
        if (mediaPlayer != null) {			//音乐播放器不为空时
            mediaPlayer.stop();			//停止音乐播放
        }
    }

    // 该方法实现选择数字界面销毁时，背景音乐停止并释放音乐资源
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {			//音乐播放器不为空时
            mediaPlayer.stop();			//停止音乐播放
            mediaPlayer.release();			//释放音乐资源
            mediaPlayer = null;			//设置音乐播放器为空
        }
    }

    //该方法实现从另一界面返回选择数字界面时，根据音乐播放状态播放音乐
    protected void onRestart() {
        super.onRestart();
        if (MainActivity.isPlay == true) {	//如果音乐处于播放状态
            PlayMusic();					//调用播放背景音乐方法，播放音乐
        }
    }
}   //SelectActivity类尾部

