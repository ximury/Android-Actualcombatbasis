package mrkj.flowersdemo.ui.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import mrkj.flowersdemo.R;

/**
 * 自定义带有标题栏的基类界面的基类
 */
public abstract class BaseActivity extends FragmentActivity implements SetBaseActivity ,SetTitleShowStyle{
    private View parentView = null;//主布局
    private DrawerLayout mDrawer = null;//抽屉
    private LinearLayout mLinear = null;//添加子布局的父布局
    //设置标题栏
    private ImageView rightImg1 = null,rightImg2 = null,rightImg3 = null;//右侧图片
    private ImageView leftImg1 = null,leftImg2 = null,leftImg3 = null;//左侧图片
    private ImageView titleImg = null;//标题图片
    private TextView rightTxt1 = null,rightTxt2 = null,rightTxt3 = null;//右侧文本
    private TextView leftTxt1 = null,leftTxt2 = null,leftTxt3 = null;//左侧文本
    private TextView titleTxt = null;//标题文本
    private RelativeLayout titleParentLayout = null;//标题栏的父布局
    //初始化界面布局
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //通过该值判断是否隐藏状态栏
        boolean IS_FULLSCREEN = setActivityFullScreen();
        if (IS_FULLSCREEN == true){
            //不显示状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        //带标题栏的父布局
        parentView = getLayoutInflater().inflate(R.layout.activity_base,null);
        setContentView(parentView);
        //是否添加标题-->true添加
        if (isAddTitle() == true){
            //初始化标题栏
            initTitleBarView();
        }
        //向父布局中添加想要增加的布局
        initAboutDrawerLayoutValues();
        //初始化界面
        onCreateChild();
    }

    /**
     * 获取父布局
     * @return
     */
    protected View getLayoutView(){
        return parentView;
    }
    /**
     * 设置背景
     * @param redID
     */
    protected void setActivityBackGround(int redID){
        parentView.setBackgroundResource(redID);
    }

    /**
     * 设置背景颜色
     * @param color
     */
    protected void setActivityBackGroundColor(int color){
        parentView.setBackgroundColor(color);
    }
    /**
     * 初始化抽屉相关的属性
     */
    public void initAboutDrawerLayoutValues(){
        mDrawer = (DrawerLayout) findViewById(R.id.drawer);//抽屉
        if (openDrawerLayout() == true){
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED); //打开手势滑动
            //实例化左侧抽屉
            LinearLayout left_show = (LinearLayout) findViewById(R.id.left_layout);//显示抽屉
            //获取到这个控件
            initViewInTheDrawerLayout(mDrawer,left_show);
        }else {
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED); //关闭手势滑动
        }
        //实例化控件
        mLinear = (LinearLayout) findViewById(R.id.layout_home);//界面的布局
        addChildActivityLayout(mLinear);//组合合成新的界面
    }

    /**
     * 初始化抽屉中的控件
     */
    @Override
    public void initViewInTheDrawerLayout(DrawerLayout drawer,LinearLayout leftView) {
        //可以重写这个方法去设置抽屉中从左向右滑出的布局
    }

    /**
     * 是否开启抽屉
     * @return
     */
    @Override
    public boolean openDrawerLayout() {
        return false;
    }

    /**
     * 向父布局中添加子布局
     * @param parent
     */
    private  void addChildActivityLayout(LinearLayout parent){
        //返回一个资源ID
        int childLayoutID = setChildContentView();
        View childView = getLayoutInflater().inflate(childLayoutID,null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        childView.setLayoutParams(params);
        //向父布局中添加子布局
        parent.addView(childView);
    }

    /**
     * 设置界面是否全屏
     * @return
     */
    @Override
    public boolean setActivityFullScreen() {
        return false;
    }

    /**
     *获取布局文件的布局
     * 初始化界面
     */
    protected abstract int setChildContentView();
    /**
     * 初始化界面
     */
    protected abstract void onCreateChild();

    //****************************** 设置标题栏 ******************************

    /**
     * 是否添加标题
     * @return
     */
    @Override
    public boolean isAddTitle() {
        return false;
    }

    /**
     * 初始化标题栏相关的控件
     */
    private void initTitleBarView(){
        //标题栏的父布局
        titleParentLayout = (RelativeLayout) findViewById(R.id.back_home);
        //左侧
        leftImg1 = (ImageView) findViewById(R.id.left_image1);
        leftImg2 = (ImageView) findViewById(R.id.left_image2);
        leftImg3 = (ImageView) findViewById(R.id.left_image3);
        leftTxt1 = (TextView) findViewById(R.id.left_name1);
        leftTxt2 = (TextView) findViewById(R.id.left_name2);
        leftTxt3 = (TextView) findViewById(R.id.left_name3);
        //中间
        titleImg = (ImageView) findViewById(R.id.title_image);
        titleTxt = (TextView) findViewById(R.id.title_name);
        //右侧
        rightImg1 = (ImageView) findViewById(R.id.right_image1);
        rightImg2 = (ImageView) findViewById(R.id.right_image2);
        rightImg3 = (ImageView) findViewById(R.id.right_image3);
        rightTxt1 = (TextView) findViewById(R.id.right_name1);
        rightTxt2 = (TextView) findViewById(R.id.right_name2);
        rightTxt3 = (TextView) findViewById(R.id.right_name3);
        //显示标题栏
        titleParentLayout.setVisibility(View.VISIBLE);
    }

    /**
     * 设置标题栏的背景颜色
     * @param color
     */
    protected void setTitleBackgroundColor(int color){
        titleParentLayout.setBackgroundColor(color);
    }
    /**
     * 设置标题栏的背景
     * @param resID
     */
    protected void setTitleBackground(int resID){
        titleParentLayout.setBackgroundResource(resID);
    }

    //****************************** 设置只带有左右图片的标题栏 ******************************
    /**
     * 设置只带有左右图片的标题栏
     * @param leftImgResID
     * @param rightImgResID
     */
    protected void setTitle(int leftImgResID,int rightImgResID){
        //显示控件-->在布局中默认都给隐藏了
        leftImg1.setVisibility(View.VISIBLE);
        rightImg1.setVisibility(View.VISIBLE);
        //设置图片
        leftImg1.setImageResource(leftImgResID);
        rightImg1.setImageResource(rightImgResID);
        setTitleLeftImgAndRightImg(leftImg1,rightImg1);
    }
    /**
     * 设置带有左右图片按钮的标题栏的接口的回调方法
     * @param leftView
     * @param rightView
     */
    @Override
    public void setTitleLeftImgAndRightImg(View leftView, View rightView) {

    }
    //****************************** 设置带有标题和返回键的标题栏 ******************************
    /**
     * 设置带有可设置左侧按钮的标题(如果不使用返回，可以去获取左侧的控件再设置)
     * @param titleName
     * @param textColor
     * @param resID
     * @param isFinish-->设置true返回,设置false不返回
     */
    protected void setTitle(String titleName,int textColor,int resID,boolean isFinish){
        //显示控件
        titleTxt.setVisibility(View.VISIBLE);
        leftImg1.setVisibility(View.VISIBLE);
        //设置显示的样式
        titleTxt.setText(titleName);//设置文本
        titleTxt.setTextColor(textColor);//设置字体的颜色
        leftImg1.setImageResource(resID);//设置图片
        if (isFinish){
            leftImg1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    return;
                }
            });
        }
        setTitleLeftImg(leftImg1);
    }

    /**
     * 获取左侧的ImageView
     * @param leftView
     */
    @Override
    public void setTitleLeftImg(View leftView) {

    }
    //****************************** 左:图片，中:标题,右:图片 ******************************

    /**
     * 左:图片，中:标题,右:图片
     * @param text
     * @param textColor
     * @param leftImageResID
     * @param rightImageResID
     */
    protected void setTitle(String text,int textColor,int leftImageResID,int rightImageResID){
        titleTxt.setVisibility(View.VISIBLE);
        leftImg1.setVisibility(View.VISIBLE);
        rightImg1.setVisibility(View.VISIBLE);
        //标题
        titleTxt.setText(text);
        titleTxt.setTextColor(textColor);
        //左侧
        leftImg1.setImageResource(leftImageResID);
        //右侧
        rightImg1.setImageResource(rightImageResID);
        setTitleLeftImgWithTitleRightImg(leftImg1,titleTxt,rightImg1);
    }

    /**
     * 获取标题栏中的控件
     * @param leftView
     * @param title
     * @param rightView
     */
    @Override
    public void setTitleLeftImgWithTitleRightImg(View leftView, View title, View rightView) {

    }
    //****************************** 左:图片，右1:图片,右2:图片 ******************************
    protected void setTitle(int leftImageResID,int rightImageResID1,int rightImageResID2,int rightImageResID3){
        //显示控件
        leftImg1.setVisibility(View.VISIBLE);
        rightImg1.setVisibility(View.VISIBLE);
        rightImg2.setVisibility(View.VISIBLE);
        rightImg3.setVisibility(View.VISIBLE);
        leftImg1.setImageResource(leftImageResID);
        rightImg1.setImageResource(rightImageResID1);
        rightImg2.setImageResource(rightImageResID2);
        rightImg3.setImageResource(rightImageResID3);
        //返回
        leftImg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setTitleLeftImgWithTitleRightImg(rightImg1,rightImg2,rightImg3);
    }

}
