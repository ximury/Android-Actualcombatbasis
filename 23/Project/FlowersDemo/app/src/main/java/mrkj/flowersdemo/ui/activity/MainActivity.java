package mrkj.flowersdemo.ui.activity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import mrkj.flowersdemo.R;
import mrkj.flowersdemo.db.MyDataUtils;
import mrkj.flowersdemo.ui.base.BaseActivity;
import mrkj.flowersdemo.ui.utils.L;
import mrkj.flowersdemo.ui.utils.SaveKeyValues;
import mrkj.flowersdemo.view.MainLeftToolView;
import mrkj.flowersdemo.view.RotateFlower;

/**
 * 首页
 */
public class MainActivity extends BaseActivity implements
        View.OnClickListener,MainLeftToolView.onItemCheckedChangedListener
        ,View.OnLongClickListener{
    private long exitTime;//第一次单机退出键的时间
    //当前界面中的控件
    private ImageView flower;//白色的花朵
    private ObjectAnimator flowerAnimator;//缩放动画
    private ImageView flower_e,flower_z,plant;//具有帧动画的ImageView
//    private AnimationDrawable flower_e_drawable,flower_z_drawable,plant_drawable;//动画
    private RotateFlower rotateFlower;//旋转的花瓣
    //侧标题栏
    private DrawerLayout mDrawer;//抽屉布局
    private LinearLayout mLeftView;//抽屉的左侧布局
    /**
     * 添加布局
     * @return
     */
    @Override
    protected int setChildContentView() {
        return R.layout.activity_main;
    }
    /**
     * 初始化
     */
    @Override
    protected void onCreateChild() {
        //设置背景
        setActivityBackGround(R.mipmap.mrkj_grow_background);
        //设置标题栏
        setTitle(R.mipmap.mrkj_activity_main_topbarleft,R.mipmap.mrkj_main_flower);
        setTitleBackgroundColor(Color.TRANSPARENT);
        //初始化控件
        rotateFlower = (RotateFlower) findViewById(R.id.rotate);
        rotateFlower.setChildOutAnimationStart();//开始动画
        //三个带有帧动画的ImageView
        flower_e = (ImageView) findViewById(R.id.flower_e);
        flower_z = (ImageView) findViewById(R.id.flower_z);
        plant = (ImageView) findViewById(R.id.plant);
        //设置点击事件
        plant.setOnClickListener(this);//普通点击
        plant.setOnLongClickListener(this);//长按事件
        //设置动画
//        flower_e_drawable = (AnimationDrawable) flower_e.getDrawable();
//        flower_z_drawable = (AnimationDrawable) flower_z.getDrawable();
//        plant_drawable = (AnimationDrawable) plant.getDrawable();
        //白色的花-->循环缩放
        flower = (ImageView) findViewById(R.id.flower);
        //设置属性动画
        //X轴的缩放
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX",1.0f,0.5f);
        //Y轴的缩放
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY",1.0f,0.5f);
        //设置动画
        flowerAnimator = ObjectAnimator.ofPropertyValuesHolder(flower,scaleX,scaleY);
        //时间
        flowerAnimator.setDuration(2000);
        //循环播放
        flowerAnimator.setRepeatCount(-1);
        //设置播放模式
        flowerAnimator.setRepeatMode(ValueAnimator.REVERSE);
        flowerAnimator.start();//开始动画
    }

    //=========================== 帧动画这里取消了
    /**
     * 停止播放动画
     */
//    private void stopFrameAnimation(){
//        flower_e_drawable.stop();
//        flower_z_drawable.stop();
//        plant_drawable.stop();
//    }
    /**
     * 开始播放动画
     */
//    private void startFrameAnimation(){
//        flower_e_drawable.start();
//        flower_z_drawable.start();
//        plant_drawable.start();
//    }
    /**
     * 设置标题栏中的控件
     * @param leftView
     * @param rightView
     */
    @Override
    public void setTitleLeftImgAndRightImg(View leftView, View rightView) {
        super.setTitleLeftImgAndRightImg(leftView, rightView);
        //设置点击监听
        leftView.setOnClickListener(this);
        rightView.setOnClickListener(this);
    }

    /**
     * 设置抽屉的显示
     * @param drawer
     * @param leftView -- >默认的宽度为160dp
     */
    @Override
    public void initViewInTheDrawerLayout(DrawerLayout drawer, LinearLayout leftView) {
        mDrawer = drawer;
        mLeftView = leftView;
        //设置除了显示区以外的部分为透明
        drawer.setScrimColor(Color.TRANSPARENT);
//        leftView.setBackgroundColor(this.getResources().getColor(R.color.drawer_white));
        //API23之后getColor的方法已经过时
        leftView.setBackgroundColor(ContextCompat.getColor(this,R.color.drawer_white));
        //设置侧标题栏的样式
        MainLeftToolView mainLeftToolView = new MainLeftToolView(this);
        //设置填满父布局
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mainLeftToolView.setLayoutParams(params);
        //添加布局
        leftView.addView(mainLeftToolView);
        //设置单选事件
        mainLeftToolView.setOnItemCheckedChanged(this);
    }

    /**
     * 单选事件
     */
    @Override
    public void onItemChecked(View view,int position) {
        //************** 左标题栏 **************
        switch (position){
            case MainLeftToolView.NEWS://名言
                mDrawer.closeDrawer(mLeftView);
                startActivity(new Intent(this,ShowQuotesActivity.class));
                break;
            case MainLeftToolView.ABOUTS://我们
                mDrawer.closeDrawer(mLeftView);
                startActivity(new Intent(this,AboutUsActivity.class));
                break;
            default:
                break;
        }
    }

    /**
     * 设置点击事件
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //************** 标题栏 **************
            case R.id.left_image1://标题栏左侧按钮的点击事件
                //显示左侧侧标题栏
                if (mDrawer.isDrawerOpen(mLeftView) == false){
                    //开启抽屉
                    mDrawer.openDrawer(mLeftView);
                }
                break;
            case R.id.right_image1://标题栏右侧按钮的点击事件
                //获取中的花的数量
                int flowerCount = SaveKeyValues.getIntValues("flowerCount",0);
                Intent intent = new Intent(MainActivity.this,PlantFlowerBoxActivity.class);
                intent.putExtra("flowersCount" , flowerCount);
                //跳转界面-->跳转到种花小屋
                startActivity(intent);
                break;
            //************** 种花 **************
            case R.id.plant://跳转至种花界面
                //跳转到种花的界面
                startActivity(new Intent(MainActivity.this,PlantActivity.class)
                        .putExtra("flowersCount",SaveKeyValues.getIntValues("flowerCount",0)));
                break;
            default:
                break;
        }
    }

    /**
     * 长按事件
     * @param view
     * @return
     */
    @Override
    public boolean onLongClick(View view) {
        if (view.getId() == R.id.plant){
            //跳转到选花的界面
            startActivity(new Intent(MainActivity.this,SelectedFlowerActivity.class));
        }
        return true;
    }

    //************************** 设置 **************************
    /**
     * 设置全屏
     * @return
     */
    @Override
    public boolean setActivityFullScreen() {
        return true;
    }
    /**
     * 允许当前界面有抽屉效果
     * @return
     */
    @Override
    public boolean openDrawerLayout() {
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

    /**
     * 管理生命周期
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onResume() {
        super.onResume();
//        startFrameAnimation();//开始播放帧动画
        flowerAnimator.resume();
        rotateFlower.setChildOutAnimationResume();
        int flowerCounts = MyDataUtils.getAllFlowerCounts(this);
        L.i("数据库中花的个数为","【"+ flowerCounts +"】");
        SaveKeyValues.putIntValues("flowerCount",flowerCounts);//储存数据
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onPause() {
        super.onPause();
        rotateFlower.setChildOutAnimationPause();//停止动画
        flowerAnimator.pause();//停止动画
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rotateFlower.setChildOutAnimationCancel();//取消动画
//        stopFrameAnimation();//停止播放帧动画
        flowerAnimator.cancel();//取消动画
    }

    /**
     * 按两次退出按钮退出程序
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK &&
                event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
