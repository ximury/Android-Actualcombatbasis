package mrkj.flowersdemo.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import mrkj.flowersdemo.R;
import mrkj.flowersdemo.db.DatasDao;
import mrkj.flowersdemo.db.MyDataUtils;
import mrkj.flowersdemo.ui.base.BaseActivity;
import mrkj.flowersdemo.ui.bean.Flower;
import mrkj.flowersdemo.ui.utils.GetDateUtils;
import mrkj.flowersdemo.ui.utils.L;
import mrkj.flowersdemo.ui.utils.SaveKeyValues;
import mrkj.flowersdemo.view.Plant;
import mrkj.flowersdemo.view.PlantFlowers;

/**
 * 种花
 *
 * 先是 -->静止手机,5秒后开始播种
 * 随后显示计时器隐藏文本
 */
public class PlantActivity extends BaseActivity implements View.OnClickListener,
        Chronometer.OnChronometerTickListener,Plant.onPlantFlowerCountsListener{

    private DatasDao datasDao;//用于数据库的操作
    private ArrayList<Flower> flowerValues;//存花的数据集合
    private StringBuffer stringBuffer;//保存图片的索引
    //背景颜色变换
    private View parentView;//父布局
    private ObjectAnimator parentAnimator;//父布局动画
    //屏幕
    private int screenWidth;
    private int screenHeight;
    //标题栏
    private TextView mTitleTxt;//标题
    private ImageView mLeftImage,mRightImage;//标题的左侧和右侧按钮
    private boolean changeImage = true;
    //用于显示实时时间
    private Thread showTimeThread;
    private boolean toRun;//执行线程

    //云彩和太阳
    private ImageView sun,cloud;//sun和cloud
    private AnimationDrawable sunAnimation,cloudAnimation;//帧动画
    private AnimatorSet cloudAnimatorset;//属性动画

    //提示和计时
    private int counts;
    private int write_down = 0;//用于开启计时器
    private Chronometer mChronometer;//计时器
    private TextView hint_message;//提示信息
    private String hint_str = "静止手机,%s秒后开始播种";
    private Thread showCountTime;//显示计时器
    //种花
    private PlantFlowers plantFlowers;//动画
    private Plant plant;//动画
    private TextView showFlowerCounts;//显示花的个数
    private int last_flower_counts;//之前的花的数量
    private boolean openBack;//开启返回功能
    private boolean isStart;//动画开始了
    private long startTime;//计时器开始的时间
    private long stopTime;//计时器结束的时间
    //播放音乐
    private MediaPlayer player;//播放音频
    private boolean isPlayMusic;//播放音频的开关
    private boolean isPauseMusic;//是否暂停
    /**
     * 加载布局
     * @return
     */
    @Override
    protected int setChildContentView() {
        return R.layout.activity_plant;
    }

    /**
     * 初始化界面
     */
    @Override
    protected void onCreateChild() {
        //用于保存图片的索引
        stringBuffer = new StringBuffer();
        //初始化数据库操作类
        datasDao = new DatasDao(this);
        //初始化颜色渐变的控件
        parentView = getLayoutView();
        //设置背景颜色渐变的动画
        setParentViewAnimation();
        //获取数据库中的值
        //获取传值
        last_flower_counts = getIntent().getIntExtra("flowersCount" , 0);
        getWindowWidthAndHeight();
        //设置标题栏的背景
        setTitleBackgroundColor(Color.TRANSPARENT);
        //设置标题
        setTitle(GetDateUtils.currentDatetime(),
                Color.parseColor("#288C8A"),R.mipmap.mrkj_flowers_soundon,
                R.mipmap.mrkj_plant_flower_btn);
        //初始化控件
        sun = (ImageView) findViewById(R.id.sun);
        sunAnimation = (AnimationDrawable) sun.getDrawable();
        //设置sun的动画
        cloud = (ImageView) findViewById(R.id.cloud);
        cloudAnimation = (AnimationDrawable) cloud.getDrawable();
        //设置cloud的动画
        cloudAnimatorset = new AnimatorSet();
        ObjectAnimator cloudAnimatorR =
                ObjectAnimator.ofFloat(cloud,"translationX",screenWidth);
        cloudAnimatorR.setDuration(30*1000);
        cloudAnimatorR.setInterpolator(new LinearInterpolator());
        cloudAnimatorR.setRepeatCount(-1);
        ObjectAnimator cloudAnimatorL =
                ObjectAnimator.ofFloat(cloud,"translationX",-screenWidth);
        cloudAnimatorL.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                cloud.setVisibility(View.VISIBLE);
            }
        });
        cloudAnimatorset.play(cloudAnimatorR).after(cloudAnimatorL);
        cloudAnimatorset.start();
        //******************************* 提示和计时 *******************************
        mChronometer = (Chronometer) findViewById(R.id.chronometer);
        mChronometer.setOnChronometerTickListener(this);//监听计时器的时间
        hint_message = (TextView) findViewById(R.id.hint_news);
        //******************************* 种花 *******************************
        plantFlowers = (PlantFlowers) findViewById(R.id.plant);
        plantFlowers.setChildOutAnimationStart();
        plant = plantFlowers.getPlant();
        plant.setFlowersList(getPlantFlowerSelect());
        plant.setonPlantFlowerCountsListener(this);
        //显示花的个数
        showFlowerCounts = (TextView) findViewById(R.id.flower_counts);
        //显示种过花的数量
        showFlowerCounts.setText(0+"");
    }


    /**
     * 设置背景颜色渐变动画
     */
    private void setParentViewAnimation() {
        parentAnimator= (ObjectAnimator) AnimatorInflater.
                loadAnimator(this, R.animator.background);
        parentAnimator.setEvaluator(new ArgbEvaluator());
        parentAnimator.setTarget(parentView);
        parentAnimator.start();
    }

    /**
     * 获取想种的花的集合
     */
    private ArrayList<Integer> getPlantFlowerSelect(){
        ArrayList<Integer> flower_index = getIntent().getIntegerArrayListExtra("flowers_index");
        flowerValues = new ArrayList<>();
        //集合为空或者集合元素为空则返回空
        if (flower_index == null || flower_index.size() == 0){
            //默认添加第一个
            flowerValues.add(new Flower().setIndex(0).setCount(0));
            return null;
        }
        //每次的数据都是新的
        for (Integer index:flower_index){
            L.e("index" , index + "");
            flowerValues.add(new Flower().setIndex(index).setCount(0));
        }
        return flower_index;
    }
    /**
     * 计时器的回调函数
     * @param chronometer
     */
    @Override
    public void onChronometerTick(Chronometer chronometer) {
        L.e("计时器",chronometer.getText().toString());
        int str_length = chronometer.getText().toString().length();
        if (str_length > 8 ){
            mChronometer.setFormat("%s");
        }else {
            mChronometer.setFormat("00:%s");
        }
    }

    //*********************************** 开启子线程 ******************************
    /**
     * 获得子线程的消息
     */
    private final int SHOW_TIME = 0;//时间
    private final int SHOW_COUNT_TIME = 1;//计时
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case SHOW_TIME://显示时间
                    mTitleTxt.setText(GetDateUtils.currentDatetime());
                    break;
                case SHOW_COUNT_TIME://计时
                    int time = msg.arg1;
                    if (time == -1){//当前界面取消焦点
                        handler.removeMessages(SHOW_COUNT_TIME);
                        return false;//不做任何操作
                    }
                    if (time == 0){//倒计时结束
                        //显示计时器
                        mChronometer.setVisibility(View.VISIBLE);
                        mChronometer.setBase(SystemClock.elapsedRealtime());
                        mChronometer.start();//开启计时
                        startTime = mChronometer.getDrawingTime();
                        //隐藏提示信息
                        hint_message.setVisibility(View.GONE);
                        plant.setCirculation(true);
                        plant.plantAnimatorStart();
                        isStart = true;
                        return false;
                    }
                    //提示信息
                    hint_message.setText(String.format(hint_str,time));
                    break;
                default:
                    break;
            }
            return false;
        }
    });
    /**
     * 设置功能
     */
    private synchronized void setFunctionShowTime() {
        showTimeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (toRun){
                    try {
                        Thread.sleep(200);
                        handler.sendEmptyMessage(SHOW_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        toRun = true;
        showTimeThread.start();
    }
    /**
     * 设置一个倒计时功能
     */
    public synchronized void setToCountDown(){
        showCountTime = new Thread(new Runnable() {
            @Override
            public void run() {
                while (counts >= 0){
                    try {
                        Thread.sleep(1000);
                        Message msg = Message.obtain();
                        msg.what = SHOW_COUNT_TIME;
                        msg.arg1 = counts;
                        handler.sendMessage(msg);
                        counts--;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        showCountTime.start();
    }
    /**
     * 获取标题栏中的控件
     * @param leftView
     * @param title
     * @param rightView
     */
    @Override
    public void setTitleLeftImgWithTitleRightImg(
            View leftView, View title, View rightView) {
        super.setTitleLeftImgWithTitleRightImg(leftView, title, rightView);
        mLeftImage = (ImageView) leftView;
        mTitleTxt = (TextView) title;
        mRightImage = (ImageView) rightView;
        //设置点击监听
        mRightImage.setOnClickListener(this);
        mLeftImage.setOnClickListener(this);
        mTitleTxt.setTextSize(14);
    }

    /**
     * 点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.left_image1://左侧-->播放音乐片段
                if (!changeImage){//关
                    mLeftImage.setImageResource(R.mipmap.mrkj_flowers_soundon);
                    changeImage = true;
                    stopMusic();//停止播放
                    Toast.makeText(PlantActivity.this, "停止", Toast.LENGTH_SHORT).show();
                }else{//开
                    mLeftImage.setImageResource(R.mipmap.mrkj_flowers_soundoff);
                    changeImage = false;
                    isPlayMusic = true;
                    playMusic();//播放音乐
                    Toast.makeText(PlantActivity.this, "开始", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.right_image1://右侧-->跳转种花小屋
                if (saveDataToDB()){
                    int flowerCount = saveDataToLocation();
                    //构建意图
                    Intent intent = new Intent(PlantActivity.this,
                            PlantFlowerBoxActivity.class);
                    intent.putExtra("flowersCount" ,
                            last_flower_counts + flowerCount);
                    intent.putExtra("now_plant_counts",flowerCount);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(PlantActivity.this,"发生未知错误！",
                            Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }

    /**
     * 向数据库中存储数据
     * @return
     */
    private boolean saveDataToDB(){
        boolean turn = false;
        for (Flower flower:flowerValues){
            //存储数据
            turn = MyDataUtils.upDataFlowerCount(datasDao,flower.getIndex(),flower.getCount());
        }
        return turn;
    }

    /**
     * 保存数据到本地
     */
    private int saveDataToLocation(){
        String showFlowerValues = stringBuffer.toString();
        if (!"".equals(showFlowerValues)){
            showFlowerValues = showFlowerValues.substring(0,showFlowerValues.length() - 1);
            L.e("种植的花的索引拼接字符串",showFlowerValues);
            SaveKeyValues.putStringValues("showFlowerValues",showFlowerValues);//存值
        }
        stopTime = mChronometer.getDrawingTime();
        L.e("时间",(stopTime - startTime) + "");
        MyDataUtils.changeLastDataOfPlantTime(PlantActivity.this,(stopTime - startTime));
        int flowerCount = Integer.parseInt(showFlowerCounts.getText().toString());
        //数据保存
        SaveKeyValues.putIntValues("flowerCount" , last_flower_counts +flowerCount);
        return flowerCount;
    }
    /**
     * 获取屏幕的宽度和高度
     */
    private void getWindowWidthAndHeight(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
    }

    /**
     * plant中的回调函数
     * @param counts
     */
    @Override
    public void thePlantFlowerCounts(int counts) {
        Log.e("用于计数",counts + "");
        showFlowerCounts.setText((counts) + "");
    }

    @Override
    public void theFlowerIndex(int index ,int count) {
        Log.e("刚刚种过的花的索引",index + "");
        L.e("用于计数",count + "");
        //根据索引值存入新的数据
        for(Flower flower:flowerValues){
            if (flower.getIndex() == index){
                int old_count = flower.getCount();
                flower.setCount(old_count+count);
                break;
            }
        }
        //拼接索引值
        stringBuffer.append(index).append(",");
    }
    //************************ 设置 ************************

    /**
     * 全屏
     * @return
     */
    @Override
    public boolean setActivityFullScreen() {
        return true;
    }

    /**
     * 添加标题栏
     * @return
     */
    @Override
    public boolean isAddTitle() {
        return true;
    }


    //************************ 生命周期的管理 ************************
    @Override
    protected void onRestart() {
        super.onRestart();
        if (isStart){
            openBack = true;//退出该界面的开关
            mChronometer.stop();//停止计时器
            plant.setVisibility(View.INVISIBLE);//隐藏长花的控件
            Toast.makeText(this,"花儿枯萎了，点击返回键返回主界面！",Toast.LENGTH_SHORT).show();
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onResume() {
        super.onResume();
        //当程序运行时开启子线程显示实时时间
        if (showTimeThread == null && toRun == false){
            toRun = true;
            setFunctionShowTime();//开启子线程
        }
        if (showCountTime == null){
            counts = write_down == 0? 5 : write_down;
            setToCountDown();
        }
        //开启动画
        sunAnimation.start();
        cloudAnimation.start();
        cloudAnimatorset.resume();
        plantFlowers.setChildOutAnimationResume();
        parentAnimator.resume();
        playMusic();
    }

    /**
     * 播放音频
     */
    private void playMusic() {
        if (isPauseMusic){
            player.start();
            isPauseMusic = false;
            return;
        }
        if (!isPlayMusic){
            return;
        }
        if (isPlayMusic){
            player = MediaPlayer.create(this,R.raw.music);
        }
        player.start();
    }

    /**
     * 停止播放
     */
    private void stopMusic() {
        if (isPlayMusic){
            player.stop();
            player.release();
            isPlayMusic = false;
        }
    }

    /**
     * 停止播放
     */
    private void pauseMusic(){
        if (isPlayMusic){
            isPauseMusic = true;
            player.pause();
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onPause() {
        super.onPause();
//        datasDao.close();//关闭
        toRun = false;
        showTimeThread = null;
        //结束线程的执行方法
        write_down = counts;
        counts = -1;
        showCountTime = null;
        //关闭动画
        sunAnimation.stop();
        cloudAnimation.stop();
        cloudAnimatorset.pause();
        plantFlowers.setChildOutAnimationPause();
        plant.setCirculation(false);
        plant.plantAnimatorCancel();
        parentAnimator.pause();
        pauseMusic();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cloudAnimatorset.cancel();//取消云彩的属性动画
        mChronometer.stop();//关闭计时器
        plantFlowers.setChildOutAnimationCancel();
        plantFlowers.setKeepScreenOn(false);
        parentAnimator.cancel();
        datasDao.close();
        if (isPlayMusic){
            player.release();
        }
    }


    /**
     * 屏蔽返回键
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (openBack){
            return super.onKeyDown(keyCode,event);
        }
        if(keyCode == KeyEvent.KEYCODE_BACK){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("温馨提示");
            builder.setMessage("有了坚定的意志，就等于给双脚添了一对翅膀。");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (saveDataToDB()){
                        saveDataToLocation();
                        finish();
                    }else {
                        Toast.makeText(PlantActivity.this,"发生未知错误！",
                                Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.create();
            builder.show();
            return false;
        }
        return false;
    }
}
