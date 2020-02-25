package mrkj.flowersdemo.view;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import mrkj.flowersdemo.R;

/**
 * Created by Administrator on 2016/7/22.
 */
public class PlantFlowers extends FrameLayout{

    private LayoutInflater inflater;//布局填充器
    private int parentWidth ;//宽
    private int parentHeight ;//高
    /**
     * 种花
     */
    private Plant plant;//种花的效果动画
    private View hills;//山丘
    private int plant_width;//种花宽
    private int plant_height;//种花高
    private int hill_width;//山丘宽
    private int hill_height;//山丘高
    /**
     * 风车
     */
    private ImageView windmill;//风车
    private ObjectAnimator animatorRotate;//属性动画

    /**
     * 初始化
     * @param context
     */
    public PlantFlowers(Context context) {
        this(context,null);
    }

    public PlantFlowers(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PlantFlowers(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
        //获取布局填充器
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.plant_flowers_layout,this);
        //初始化控件
        windmill = (ImageView) view.findViewById(R.id.windmill);
        setWindmillAnimator();
        plant = (Plant) view.findViewById(R.id.plant_animator);
        hills = view.findViewById(R.id.hills);
        //获取控件宽和高
        ViewTreeObserver viewTreeObserverHill = hills.getViewTreeObserver();
        viewTreeObserverHill.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                hills.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                hill_width = hills.getWidth();
                hill_height = hills.getHeight();
            }
        });
    }

    /**
     * 设置风车的动画
     */
    private void setWindmillAnimator() {
        //设置属性动画-->旋转
        animatorRotate = ObjectAnimator.ofFloat(windmill, "rotation", 0f, 360f);
        animatorRotate.setDuration(20*1000);
        animatorRotate.setRepeatCount(-1);
        animatorRotate.setInterpolator(new LinearInterpolator());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        parentHeight = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        plant_width = plant.plantWidth();
        plant_height = plant.plantHeight();
        int l = parentWidth/2 - plant_width/2;
        int r = parentWidth/2 + plant_width/2;
        int t = parentHeight - hill_height/2 - plant_height;
        int b = parentHeight - hill_height/2;
        plant.layout(l,t,r,b);
    }
    public Plant getPlant(){
        return plant;
    }
    /**
     *开启动画
     */
    public void setChildOutAnimationStart(){
        animatorRotate.start();
    }
    /**
     *运行动画
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setChildOutAnimationResume(){
        animatorRotate.resume();
    }
    /**
     * 暂停动画
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setChildOutAnimationPause(){
        animatorRotate.pause();
    }
    /**
     * 关闭动画
     */
    public void setChildOutAnimationCancel(){
        animatorRotate.cancel();
    }


}
