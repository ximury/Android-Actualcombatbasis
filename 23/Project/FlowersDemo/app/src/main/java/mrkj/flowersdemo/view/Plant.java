package mrkj.flowersdemo.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import mrkj.flowersdemo.R;
import mrkj.flowersdemo.application.MyDataHelper;
import mrkj.flowersdemo.ui.utils.DensityUtil;
import mrkj.flowersdemo.ui.utils.L;


/**
 * 目的：实现种子落在地上，然后开始长花
 * 种子，叶子(左右)，底部，根茎，花朵-->共6种控件
 * 种子（位移动画）
 * 叶子（渐变动画+位移动画）
 * 底部(无效果)
 * 根茎（渐变动画）
 * 花朵(渐变动画)
 * Created by Administrator on 2016/7/22.
 */
public class Plant extends FrameLayout{

    //常量
    private final int DEFAULT_HEIGHT = DensityUtil.dip2px(getContext(),200);//设置默认的高
    private final int DEFAULT_WIDTH = DensityUtil.dip2px(getContext(),100);//设置默认的宽
    private final int WIDTH = 1;//宽
    private final int HEIGHT = 2;//高
    private Map<String,Integer> childViewValues = null;//存储一些宽高度值
    //变量
    private int parentWidth = 0;//控件的宽
    private int parentHeight = 0;//控件的高
    private int flowers_count;//中出的花的个数
    //控件
    private ImageView seedImg = null;//种子
    private ImageView leftLeafImg = null;//叶子
    private ImageView rightLeafImg = null;//叶子
    private ImageView budImg = null;//花朵
    private ImageView branchImg = null;//根茎
    private ImageView gapImg = null;//地缝
    //动画
    private AnimatorSet animatorSetGroup;//第一步
    private boolean isCirculation;//是否循环
    private ArrayList<Integer> flower_list;//花朵的集合
    private int plant_flower_index;//种的花的标记
    private boolean getIndex = true;//获取标记
    /**
     * 构造方法
     * @ram context
     */
    public Plant(Context context) {
        this(context,null);
    }

    public Plant(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        //添加控件
        addPlantChildView(context);
        //默认
        onlyShowGapImg();
    }

    /**
     * 添加控件
     */
    private void addPlantChildView(Context context) {
        //种子
        seedImg = new ImageView(context);
        seedImg.setImageResource(R.mipmap.mrkj_grow_seed);
        //左侧叶子
        leftLeafImg = new ImageView(context);
        leftLeafImg.setImageResource(R.mipmap.mrkj_plantflower_leaf_01);
        //右侧叶子
        rightLeafImg = new ImageView(context);
        rightLeafImg.setImageResource(R.mipmap.mrkj_plantflower_leaf_02);
        //花朵
        budImg = new ImageView(context);
        budImg.setImageResource(R.mipmap.mrkj_grow_bud_1);
        //根茎
        branchImg = new ImageView(context);
        branchImg.setScaleType(ImageView.ScaleType.FIT_XY);
        branchImg.setImageResource(R.mipmap.mrkj_plantflower_branch);
        //地缝
        gapImg = new ImageView(context);
        gapImg.setImageResource(R.mipmap.mrkj_flowerplant_gap);
        //添加控件
        addView(gapImg);//地缝0
        addView(branchImg);//根茎1
        addView(leftLeafImg);//左侧叶子2
        addView(rightLeafImg);//右侧叶子3
        addView(budImg);//花朵4
        addView(seedImg);//种子5
    }
    /**
     * 初始化后的显示效果
     * 默认只显示地缝
     */
    private void onlyShowGapImg(){
        branchImg.setVisibility(INVISIBLE);
        leftLeafImg.setVisibility(INVISIBLE);
        rightLeafImg.setVisibility(INVISIBLE);
        budImg.setVisibility(INVISIBLE);
        seedImg.setVisibility(INVISIBLE);
        budImg.setImageResource(R.mipmap.mrkj_grow_bud_1);
        leftLeafImg.setImageResource(R.mipmap.mrkj_plantflower_leaf_01);
        rightLeafImg.setImageResource(R.mipmap.mrkj_plantflower_leaf_02);
    }
    /**
     * 显示其余控件-->显示茎、叶
     */
    private void otherView(){
        branchImg.setVisibility(VISIBLE);//茎
        leftLeafImg.setVisibility(VISIBLE);//左叶
        rightLeafImg.setVisibility(VISIBLE);//右叶
    }


    /**
     * 设置动画
     * 动画的第一阶段
     */
    private void startShowPlantFlower(){
        final Bitmap[] bitmaps = MyDataHelper.getInstance().getBitmapArray(getContext());
        animatorSetGroup = new AnimatorSet();//动画集合
        // **********************************************************
        // 成长阶段 **************************************************
        // **********************************************************
        //1.种子-->平移动画
        ObjectAnimator seedTranslation = ObjectAnimator.ofFloat(seedImg,"translationY",
                0,childViewValues.get("seedMoveLength"));
        seedTranslation.setDuration(5*1000);
        seedTranslation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                otherView();//用来显示控件
                seedImg.setVisibility(INVISIBLE);
            }
        });
        seedTranslation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                seedImg.setVisibility(VISIBLE);//显示种子
                seedImg.setPivotY(0);
                seedImg.invalidate();
            }
        });
        //2.根茎-->缩放动画
        ObjectAnimator branchAnimator = ObjectAnimator.ofFloat(branchImg,"scaleY",0f,1.0f);
        branchAnimator.setDuration(10*1000);
        branchImg.setPivotY(childViewValues.get("BranchHeight"));
        branchImg.invalidate();
        //3.叶子-->缩放+位移动画
        //3.1（左）
        //缩放
        PropertyValuesHolder leftLeafScaleX = PropertyValuesHolder.ofFloat("scaleX",0f,0.5f);
        PropertyValuesHolder leftLeafScaleY = PropertyValuesHolder.ofFloat("scaleY",0f,0.5f);
        //平移
        leftLeafImg.setPivotX(childViewValues.get("LeftLeafWidth"));
        leftLeafImg.setPivotY(childViewValues.get("LeftLeafHeight"));
        PropertyValuesHolder leftLeafTranslation = PropertyValuesHolder.ofFloat("translationY",
                0,- childViewValues.get("BranchHeightHalf")*2/3);
        ObjectAnimator leftAnimator = ObjectAnimator.ofPropertyValuesHolder(leftLeafImg,
                leftLeafScaleX,leftLeafScaleY,leftLeafTranslation);
        leftAnimator.setDuration(8*1000);

        //3.2（右）
        PropertyValuesHolder rightLeafScaleX = PropertyValuesHolder.ofFloat("scaleX",0f,0.5f);
        PropertyValuesHolder rightLeafScaleY = PropertyValuesHolder.ofFloat("scaleY",0f,0.5f);
        //平移
        rightLeafImg.setPivotX(0);
        rightLeafImg.setPivotY(childViewValues.get("LeftLeafHeight"));
        PropertyValuesHolder rightLeafTranslation = PropertyValuesHolder.ofFloat("translationY",
                0,- childViewValues.get("BranchHeightHalf")*2/3);
        ObjectAnimator rightAnimator = ObjectAnimator.ofPropertyValuesHolder(rightLeafImg,
                rightLeafScaleX,rightLeafScaleY,rightLeafTranslation);
        rightAnimator.setDuration(8*1000);
        //4.花朵的显示
        PropertyValuesHolder budAnimatorScaleX = PropertyValuesHolder.ofFloat("scaleX",0.1f,1.0f);
        PropertyValuesHolder budAnimatorScaleY = PropertyValuesHolder.ofFloat("scaleY",0.1f,1.0f);
        ObjectAnimator budAnimator = ObjectAnimator.ofPropertyValuesHolder(budImg,
                budAnimatorScaleX,budAnimatorScaleY);
        budAnimator.setDuration(5*1000);
        budAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                budImg.setVisibility(VISIBLE);
            }
        });
        //5.叶子继续放大
        PropertyValuesHolder leftLeafScaleXMore = PropertyValuesHolder.ofFloat("scaleX",0.5f,1.0f);
        PropertyValuesHolder leftLeafScaleYMore = PropertyValuesHolder.ofFloat("scaleY",0.5f,1.0f);
        ObjectAnimator leftLeafAnimatorMore = ObjectAnimator.ofPropertyValuesHolder(leftLeafImg,
                leftLeafScaleXMore,leftLeafScaleYMore);
        leftLeafAnimatorMore.setDuration(5*1000);
        leftLeafAnimatorMore.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                leftLeafImg.setImageResource(R.mipmap.mrkj_grow_leaf_2);
            }
        });
        PropertyValuesHolder rightLeafScaleXMore = PropertyValuesHolder.ofFloat("scaleX",0.5f,1.0f);
        PropertyValuesHolder rightLeafScaleYMore = PropertyValuesHolder.ofFloat("scaleY",0.5f,1.0f);
        ObjectAnimator rightLeafAnimatorMore = ObjectAnimator.ofPropertyValuesHolder(rightLeafImg,
                rightLeafScaleXMore,rightLeafScaleYMore);
        rightLeafAnimatorMore.setDuration(5*1000);
        rightLeafAnimatorMore.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                rightLeafImg.setImageResource(R.mipmap.mrkj_grow_leaf_1);
            }
        });
        //开始长花
        PropertyValuesHolder budGroupToFlowerX = PropertyValuesHolder.ofFloat(
                "scaleX",0.5f,1.0f);
        PropertyValuesHolder budGroupToFlowerY = PropertyValuesHolder.ofFloat(
                "scaleY",0.5f,1.0f);
        ObjectAnimator budGroupAnimator = ObjectAnimator.ofPropertyValuesHolder(budImg,
                budGroupToFlowerX,budGroupToFlowerY );
        budGroupAnimator.setDuration(5*1000);
        budGroupAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                budImg.setImageResource(R.mipmap.mrkj_grow_bud_2);
            }
        });
        //最后开花
        PropertyValuesHolder budGroupToFlowerXMore = PropertyValuesHolder.ofFloat(
                "scaleX",0.5f,1.0f);
        PropertyValuesHolder budGroupToFlowerYMore = PropertyValuesHolder.ofFloat(
                "scaleY",0.5f,1.0f);
        ObjectAnimator openFlowerAnimator = ObjectAnimator.ofPropertyValuesHolder(budImg,
                budGroupToFlowerXMore,budGroupToFlowerYMore );
        openFlowerAnimator.setDuration(5*1000);
        openFlowerAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (getIndex){
                    if (flower_list != null){
                        L.e("length",flower_list.size()+"");
                        int length = flower_list.size();//获取数据的长度
                        plant_flower_index = flower_list.get(getIndex(length));//获取索引值
                        budImg.setImageBitmap(bitmaps[plant_flower_index]);//根据索引值设置图片
                    }else {
                        plant_flower_index = 0;//默认索引值
                        budImg.setImageResource(R.mipmap.mrkj_flower_01);//设置默认图片
                    }
                    getIndex = false;
                }
            }
        });
        //***************************** 分割线 *****************************
        //播放动画集合
        animatorSetGroup.play(branchAnimator).with(leftAnimator).with(rightAnimator).after(seedTranslation);
        animatorSetGroup.play(rightLeafAnimatorMore).with(leftLeafAnimatorMore).after(leftAnimator);
        animatorSetGroup.play(budAnimator).after(branchAnimator);
        animatorSetGroup.play(budGroupAnimator).after(budAnimator);
        animatorSetGroup.play(openFlowerAnimator).after(budGroupAnimator);
        animatorSetGroup.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (isCirculation){
                    onlyShowGapImg();
                    getIndex = true;
                    animatorSetGroup.start();
                    flowers_count++;
                    bloomFlowers();
                }
            }
        });
    }

    /**
     * 获取随机数
     * @param length
     * @return
     */
    private int getIndex(int length){
        Random random = new Random();//用于获取随机数
        plant_flower_index = random.nextInt(length);
        return plant_flower_index;
    }
    /**
     * 回调函数
     */
    private onPlantFlowerCountsListener onPlantFlowerCountsListener;
    public interface onPlantFlowerCountsListener{
        void thePlantFlowerCounts(int counts);
        void theFlowerIndex(int index ,int count);
    }
    public void setonPlantFlowerCountsListener(onPlantFlowerCountsListener listener){
        this.onPlantFlowerCountsListener = listener;
    }
    /**
     * 回调
     */
    private void bloomFlowers(){
        if (onPlantFlowerCountsListener != null){
            onPlantFlowerCountsListener.thePlantFlowerCounts(flowers_count);//返回计数
            onPlantFlowerCountsListener.theFlowerIndex(plant_flower_index,1);//返回图片索引
        }
    }

    //*************************** 公开的方法 ***************************

    /**
     * 设置是否循环
     * @param isCirculation
     */
    public void setCirculation(boolean isCirculation){
        this.isCirculation = isCirculation;
    }

    /**
     * 设置花朵的集合
     */
    public void setFlowersList(ArrayList<Integer> list){
        this.flower_list = list;
    }
    /**
     * 以上是功能性代码
     * 以下是获取相关的属性
     */
    //*************************** 测量和摆放子控件 ***************************
    /**
     * 测量-->设置大小
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取测量模式
        int width_mode = MeasureSpec.getMode(widthMeasureSpec);
        int height_mode = MeasureSpec.getMode(heightMeasureSpec);
        //获取测量值
        int width_size = MeasureSpec.getSize(widthMeasureSpec);
        int height_size = MeasureSpec.getSize(heightMeasureSpec);
        //根据测量结果设置最终的宽度和高度
        parentWidth = opinionWidthOrHeight(width_mode,width_size,WIDTH);//宽度的测量结果
        parentHeight =opinionWidthOrHeight(height_mode,height_size,HEIGHT);//高度的测量结果
        //设置子控件
        setChildLayoutParams();
        setMeasuredDimension(parentWidth,parentHeight);
    }

    /**
     * 返回当前的测量值（宽）
     * @return
     */
    public int plantWidth(){
        return parentWidth;
    }

    /**
     * 返回当前的测量值（高）
     * @return
     */
    public int plantHeight(){
        return parentHeight;
    }
    /**
     * 设置子控件的大小
     */
    private void setChildLayoutParams() {
        //获取子控件的个数
        int childCounts = getChildCount();
        //设置子控件的大小
        for (int i = 0;i < childCounts; i++){
            View childView = getChildAt(i);
            FrameLayout.LayoutParams params;
            if (i == 1){
                params= new LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            }else {
                params= new LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
            childView.setLayoutParams(params);
        }
    }

    /**
     * 根据模式判断宽度或者高度
     * @param mMode
     * @param mSize
     * @param what
     * @return
     */
    private int opinionWidthOrHeight(int mMode, int mSize , int what) {
        int result = 0;
        if (mMode == MeasureSpec.EXACTLY) {
            result = mSize;
        } else {
            // 设置默认宽度
            int size = what == WIDTH ? DEFAULT_WIDTH : DEFAULT_HEIGHT;
            if (mMode == MeasureSpec.AT_MOST) {
                result = Math.min(mSize, size);
            }
        }
        return result;
    }

    /**
     * 布局-->摆放位置
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //储存子控件的宽度和高度信息
        childViewValues = new HashMap<>();
        //设置子控件的摆放位置
        setGapImgPlace();//地缝
        setBranchPlace();//根茎
        setLeftLeafPlace();//左侧叶子
        setRightLeafPlace();//右侧叶子
        setBudOrSeedPlace(4);//花朵
        setBudOrSeedPlace(5);//种子
        startShowPlantFlower();
    }
    /**
     * 获取子控件的宽高属性
     * @param child
     * @return
     */
    private List<Integer> getChildValues(View child){
        List<Integer> list = new ArrayList<>();
        list.add(child.getWidth());//宽0
        list.add(child.getHeight());//高1
        return list;
    }
    /**
     * 地缝
     */
    private void setGapImgPlace() {
        View child = getChildAt(0);
        List<Integer> childValues = getChildValues(child);
        int l = parentWidth/2 - childValues.get(0)/2;
        int r = parentWidth/2 + childValues.get(0)/2;
        int t = parentHeight - childValues.get(1);
        int b = parentHeight;
        child.layout(l,t,r,b);
        //存放地缝的高度
        childViewValues.put("GapHeight",childValues.get(1));//高度
    }

    /**
     * 根茎
     */
    private void setBranchPlace() {
        View child = getChildAt(1);
        List<Integer> childValues = getChildValues(child);
        int l = parentWidth/2 - childValues.get(0)/2;
        int r = parentWidth/2 + childValues.get(0)/2;
        int t = parentHeight/3;//纯属为了好看才设置的这个值
        int b = parentHeight - childViewValues.get("GapHeight")/2;
        child.layout(l,t,r,b);
        //存放根茎的高度
        childViewValues.put("BranchTopY",t);//距离顶部的高度
        childViewValues.put("BranchWidth",childValues.get(0));//根茎的宽度
        childViewValues.put("BranchHeight",Math.abs(t - b));//根茎的高度
        childViewValues.put("BranchHeightHalf",Math.abs(t - b)/2);//高度的一半
    }

    /**
     * 叶子(左)
     */
    private void setLeftLeafPlace() {
        View child = getChildAt(2);
        List<Integer> childValues = getChildValues(child);
        int l = parentWidth/2 - childValues.get(0)- childViewValues.get("BranchWidth");
        int r = parentWidth/2 - childViewValues.get("BranchWidth");
        int t = parentHeight - (childValues.get(1) + childViewValues.get("GapHeight")/2);
        int b = parentHeight - childViewValues.get("GapHeight")/2;
        child.layout(l,t,r,b);
        childViewValues.put("LeftLeafWidth" ,childValues.get(0));//子控件的宽度
        childViewValues.put("LeftLeafHeight" ,childValues.get(1));//子控件的高度
    }

    /**
     * 叶子(右)
     */
    private void setRightLeafPlace() {
        View child = getChildAt(3);
        List<Integer> childValues = getChildValues(child);
        int l = parentWidth/2 + childViewValues.get("BranchWidth");
        int r = parentWidth/2 + childValues.get(0) + childViewValues.get("BranchWidth");
        int t = parentHeight - (childValues.get(1) + childViewValues.get("GapHeight")/2);
        int b = parentHeight - childViewValues.get("GapHeight")/2;
        child.layout(l,t,r,b);
    }

    /**
     * 花朵或种子
     */
    private void setBudOrSeedPlace(int index) {
        View child = getChildAt(index);
        List<Integer> childValues = getChildValues(child);
        int l = parentWidth/2 - childValues.get(0)/2;
        int r = parentWidth/2 + childValues.get(0)/2;
        int t = childViewValues.get("BranchTopY") - childValues.get(1)/2;
        int b = childViewValues.get("BranchTopY") + childValues.get(1)/2;
        child.layout(l,t,r,b);
        switch (index){
            case 4://花朵
                break;
            case 5://种子
                childViewValues.put("seedMoveLength",
                        Math.abs(parentHeight - t - childViewValues.get("GapHeight")));
                break;
            default:
                break;
        }
    }
    //************************* 对外的公有方法 *************************
    /**
     * 设置动画的播放，停止，运行，取消
     */
    /**
     * 播放
     */
    public void plantAnimatorStart(){
        animatorSetGroup.start();
    }

    /**
     * 运行
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void plantAnimatorResume(){
        animatorSetGroup.resume();
    }

    /**
     * 暂停
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void plantAnimatorPause(){
        animatorSetGroup.pause();
    }

    /**
     * 取消
     */
    public void plantAnimatorCancel(){
        animatorSetGroup.cancel();
        onlyShowGapImg();
    }
}
