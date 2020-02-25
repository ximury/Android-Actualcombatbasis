package com.mingrisoft.fieldassistant.recorder;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mingrisoft.fieldassistant.R;

public class RecorderActivity extends Activity implements OnClickListener {

    private Button start;
    private Button stop;
    private Button myself;
    private ListView listView;
    private Boolean tf = true;
    private MediaPlayer myPlayer;           // 录音文件播放
    private MediaRecorder myRecorder;               // 录音
    private String path;             // 音频文件保存地址
    private String paths = path;
    private File saveFilePath,delFile;  //设置要储存的文件和要删除的文件
    String[] listFile = null;           // 所录音的文件
    private CiDai cidai;                //自定义磁带动画控件

    ShowRecorderAdpter showRecord;     //设置一个内部的adapter 用来显示录音文件的列表
    AlertDialog aler = null;
    private ImageButton backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recorder_layout);

        init();      //  初始化控件
    }

    /**
     * 初始化控件
     * **/
    private void init() {
        // 1、获取屏幕高度、宽度
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        cidai = (CiDai) findViewById(R.id.cidai);
        cidai.stopRotate();
        start = (Button) findViewById(R.id.start_radio);
        stop = (Button) findViewById(R.id.stop_radio);
        myself = (Button) findViewById(R.id.myself);
        listView = (ListView) findViewById(R.id.list);
        backBtn = (ImageButton) findViewById(R.id.back_im_btn);
        backBtn.setOnClickListener(this);
        myPlayer = new MediaPlayer();
        myRecorder = new MediaRecorder();

        myRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);     // 从麦克风源进行录音

        myRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT); // 设置输出格式

        myRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);  // 设置编码格式
        showRecord = new ShowRecorderAdpter();
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            try {
                path = Environment.getExternalStorageDirectory()
                        .getCanonicalPath().toString()
                        + "/XIONGRECORDERS";
                File files = new File(path);
                if (!files.exists()) {
                    files.mkdir();
                }
                listFile = files.list();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        myself.setOnClickListener(this);
        if (listFile != null) {
            listView.setAdapter(showRecord);
        }
    }


    /***
     *
     * 内部的adapter
     * 用来实现录音的功能
     * */
    class ShowRecorderAdpter extends BaseAdapter {

        @Override
        public int getCount() {
            return listFile.length;
        }

        @Override
        public Object getItem(int arg0) {
            return arg0;
        }

        @Override
        public long getItemId(int arg0) {
            return arg0;

        }

        @Override
        public View getView(final int postion, View arg1, ViewGroup arg2) {
            View views = LayoutInflater.from(RecorderActivity.this).inflate(
                    R.layout.list_show_filerecorder, null);
            TextView filename = (TextView) views
                    .findViewById(R.id.show_file_name);
            final Button plays = (Button) views.findViewById(R.id.bt_list_play);
            final Button stop = (Button) views.findViewById(R.id.bt_list_stop);

            filename.setText(listFile[postion]);
            // 播放录音
            plays.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    try {
                        myPlayer.reset();
                        myPlayer.setDataSource(path + "/" + listFile[postion]);

                        if (!myPlayer.isPlaying()) {

                            myPlayer.prepare();
                            myPlayer.start();
                            plays.setVisibility(View.INVISIBLE);
                            stop.setText("停止");
                        } else {
                            myPlayer.pause();
                            plays.setText("播放");
                            stop.setText("删除");
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            // 停止播放
            stop.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    if (myPlayer.isPlaying()) {
                        myPlayer.stop();
                        plays.setVisibility(View.VISIBLE);
                        stop.setText("删除");
                    } else {

                        Builder build = new Builder(RecorderActivity.this);
                        build.setTitle("是否要删除该条录音？");
                        build.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                delFile = new File(path + "/" + listFile[postion]);
                                delFile.delete();
                                // 重新读取 文件
                                File files = new File(path);
                                listFile = files.list();
                                // 刷新ListView
                                showRecord.notifyDataSetChanged();
                                Toast.makeText(RecorderActivity.this, "删除该条录音", Toast.LENGTH_SHORT).show();
                            }
                        });
                        build.setNegativeButton("取消", null);
                        build.show();
                    }
                }
            });
            return views;
        }

    }
    /**
     *点击事件
     * 定义的各个按钮的点击事件
     * */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_radio:
                tf = true;
                setTF(false);
                final EditText filename = new EditText(this);
                Builder alerBuidler = new Builder(this);
                alerBuidler
                        .setTitle("请输入要保存的文件名")
                        .setView(filename)
                        .setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        String text = filename.getText().toString();
                                        try {
                                            paths = path
                                                    + "/"
                                                    + text
                                                    + new SimpleDateFormat(
                                                    "yyyyMMddHHmmss").format(System
                                                    .currentTimeMillis())
                                                    + ".amr";
                                            saveFilePath = new File(paths);
                                            myRecorder.setOutputFile(saveFilePath
                                                    .getAbsolutePath());
                                            saveFilePath.createNewFile();
                                            myRecorder.prepare();
                                            // 开始录音
                                            myRecorder.start();
                                            start.setText("正在录音中。。");
                                            start.setEnabled(false);
                                            aler.dismiss();
                                            cidai.startRotate();
                                            // 重新读取 文件
                                            File files = new File(path);
                                            listFile = files.list();
                                            // 刷新ListView
                                            showRecord.notifyDataSetChanged();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                    }
                                });
                aler = alerBuidler.create();
                aler.setCanceledOnTouchOutside(false);
                aler.show();
                break;
            case R.id.stop_radio:
                if (saveFilePath != null && saveFilePath.exists()) {
                    myRecorder.stop();
                    myRecorder.release();
                    cidai.stopRotate();
                    // 判断是否保存 如果不保存则删除
                    new Builder(this)
                            .setTitle("是否保存该录音")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(RecorderActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            })
                            .setNegativeButton("取消",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog,
                                                            int which) {
                                            saveFilePath.delete();
                                            // 重新读取 文件
                                            File files = new File(path);
                                            listFile = files.list();
                                            // 刷新ListView
                                            showRecord.notifyDataSetChanged();
                                            Toast.makeText(RecorderActivity.this,"已取消",Toast.LENGTH_SHORT).show();
                                            finish();
                                        }
                                    }).show();

                    start.setText("录音");
                    start.setEnabled(true);
                }
                onStart();
                break;

            case R.id.myself:
                if (tf) {
                    myself.setText("我的录音");
                    setTF(true);
                    tf = false;
                } else {
                    myself.setText("返回录音");
                    setTF(false);
                    tf = true;
                }

                break;
            case R.id.back_im_btn:
               finish();
                break;
        }

    }

    //设置图片与列表的显示
    private void setTF(boolean tf) {

        if (tf) {  // 显示录音列表  隐藏磁带
            listView.setVisibility(View.VISIBLE);
            cidai.setVisibility(View.GONE);

        } else {        // 隐藏录音列表  显示磁带
            cidai.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        }
    }

    /*
    * 当该页面finish时  执行该方法
    * */
    @Override
    protected void onDestroy() {
      CleanData();
        super.onDestroy();
    }

    private void CleanData() {
        // 释放资源
        if (myPlayer.isPlaying()) {
            myPlayer.stop();
            myPlayer.release();
        }
        myPlayer.release();
        myRecorder.release();
    }

}

