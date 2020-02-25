package mrkj.flowersdemo.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import mrkj.flowersdemo.R;
import mrkj.flowersdemo.ui.utils.L;


/**
 * 旋转的花瓣
 * Created by Administrator on 2016/7/18.
 */
public class RotateFlower extends FrameLayout{

    //变量
    private ImageView disc;//种植的背景
    private ImageView petals;//环绕的圈
    private int parent_width;//父布局的宽度
    private int parent_height;//父布局的高度
    private int window_width;//屏幕的宽度
    private int window_height;//屏幕的高度
    private int parent_default_width;//当前控件的默认宽度
    private int parent_default_height;//当前控件的默认高度
    private AnimatorSet animatorSet;//动画集合
    private int childIn_height;//这个控件的大致高度

    /**
     * RotateFlower的构造方法
     * @param context
     */
    public RotateFlower(Context context) {
        this(context,null);
    }

    /**
     * RotateFlower的构造方法
     * @param context
     * @param attrs
     */
    public RotateFlower(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    /**
     * RotateFlower的构造方法
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public RotateFlower(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取屏幕的宽度和高度
        getWindowHeightWithWidth(context);
        //向布局中添加控件
        addViewInRotateFlower(context);
        //声明动画集合
        animatorSet = new AnimatorSet();
        //设置属性动画-->旋转
        ObjectAnimator animatorRotate = ObjectAnimator.ofFloat(petals, "rotation", 0f, 360f);
        animatorRotate.setDuration(60*1000);
        animatorRotate.setRepeatCount(-1);
        animatorRotate.setInterpolator(new LinearInterpolator());
        //设置动画-->宽度变为原来的2倍
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(petals,"scaleX",1,2f);
        animatorX.setRepeatCount(1);
        //设置动画-->高度变为原来的2倍
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(petals,"scaleY",1,2f);
        animatorY.setRepeatCount(1);
        animatorSet.play(animatorY).with(animatorX);
        animatorSet.play(animatorRotate);
    }

    /**
     * 获取手机屏幕的宽度和高度
     * @param context
     */
    private void getWindowHeightWithWidth(Context context) {
        // 获取屏幕高度、宽度
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        window_width = display.getWidth();
        window_height = display.getHeight();
        //屏幕中有状态栏会占据的一部分屏幕的高度
        L.e("屏幕的宽度和高度","屏幕宽度：" + window_width + "\t\t屏幕高度：" + window_height);
        //设置默认的宽度和高度
        parent_default_width = window_width;
        parent_default_height = window_height/2;
    }

    /**
     * 添加控件
     * @param context
     */
    private void addViewInRotateFlower(Context context) {
        //实例化控件-->child1
        disc = new ImageView(context);
        //设置图片的填充模式
        disc.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        //设置填充的图片
        disc.setImageResource(R.mipmap.mrkj_main_disc);
        //添加到布局中
        this.addView(disc);
        //实例化控件-->child2
        petals = new ImageView(context);
        //设置图片的填充模式
        petals.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        //设置填充的图片
        petals.setImageResource(R.mipmap.mrkj_main_petals);
        //添加到布局中
        this.addView(petals);
    }

    /**
     * 测量控件
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        L.e("测量","onMeasure");
        //获取控件的测量宽度
        parent_width = MeasureSpec.getSize(widthMeasureSpec);
        //获取控件的测量高度
        parent_height = MeasureSpec.getSize(heightMeasureSpec);
        L.e("当前控件的宽度","【"+parent_width+"】");
        L.e("当前控件的高度","【"+parent_height+"】");
        //高度设死-->无论设多少都跟布局文件中没有关系-->因为只用一次
        L.e("设定的宽度和高度","设定的宽度:"+"【" + parent_default_width + "】");
        L.e("设定的宽度和高度","设定的高度:"+"【" + parent_default_height + "】");
        setMeasuredDimension(parent_default_width,parent_default_height);
    }


    /**
     * 设置布局
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        L.e("摆放","onLayout");
        //获取子控件的个数
        int childCounts = getChildCount();
        L.e("子控件的个数为","【"+childCounts+"】");
        //子控件的个数肯定是两个,因为我们只想布局中添加了两个子控件
        //获取添加的第一个控件
        View child1In = getChildAt(0);
        //设置第一个子控件的摆放位置
        child1In.layout(0,parent_default_height/3*2,parent_default_width,parent_default_height);
        //获取大致高度
        childIn_height = Math.abs(parent_default_height/3*2 - parent_default_height);
        //获取添加的第二个控件
        View child1Out = getChildAt(1);
        //设置第二额子控件的摆放位置
        child1Out.layout(0,parent_default_height/2,parent_default_width,parent_default_height + parent_default_height/2);
    }

    /**
     *开启动画
     */
    public void setChildOutAnimationStart(){
        animatorSet.start();
    }
    /**
     *运行动画
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setChildOutAnimationResume(){
        animatorSet.resume();
    }
    /**
     * 暂停动画
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setChildOutAnimationPause(){
        animatorSet.pause();
    }
    /**
     * 关闭动画
     */
    public void setChildOutAnimationCancel(){
        animatorSet.cancel();
    }
}
