package mrkj.flowersdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ProgressBar;

import mrkj.flowersdemo.R;


/**
 * 自定义进度条
 * Created by Administrator on 2016/8/1.
 */
public class MyProgressBar extends ProgressBar{
    /**
     * 默认的属性
     */
    private static final int DEFAULT_VALUES = 10;//默认的值
    private static final int DEFAULT_INDICATOR_HEIGHT = DEFAULT_VALUES * 2;//三角形的底边宽
    private static final int DEFAULT_INDICATOR_COLOR = 0xffFF0000;//指示器默认的颜色(红)
    private static final int DEFAULT_BACK_LINE_HEIGHT = DEFAULT_VALUES;//进度条默认高度（无进度）
    private static final int DEFAULT_BACK_LINE_COLOR = 0xffFF8080;//进度条默认颜色(粉)
    private static final int DEFAULT_FORE_LINE_HEIGHT = DEFAULT_VALUES;//进度条默认高度（有进度）
    private static final int DEFAULT_FORE_LINE_COLOR = 0xff95CAFF;//进度条默认颜色(蓝)
    /**
     * 变量
     */
    private int indicator_height = dp2px(DEFAULT_INDICATOR_HEIGHT);
    private int indicator_color = DEFAULT_INDICATOR_COLOR;
    private int back_height = dp2px(DEFAULT_BACK_LINE_HEIGHT);
    private int back_color = DEFAULT_BACK_LINE_COLOR;
    private int fore_height = dp2px(DEFAULT_FORE_LINE_HEIGHT);
    private int fore_color = DEFAULT_FORE_LINE_COLOR;
    private int progress_width;//绘制线的宽度
    private int triangle_width = indicator_height;//底边宽的一半
    /**
     * 绘制
     */
    private Paint indicator_paint;//绘制指示器的画笔
    private Paint back_paint;//绘制进度条的底部
    private Paint fore_paint;//绘制进度条的进度
    private int line_endX;//进度条的终点坐标
    /**
     * 构造方法
     * @param context
     */
    public MyProgressBar(Context context) {
        this(context,null);
    }

    public MyProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取自定义属性
        obtainAttributes(attrs);
        //初始化画笔
        initPaint();
    }

    /**
     * 绘制画笔
     */
    private void initPaint() {
        //绘制指示器的画笔
        indicator_paint = new Paint();
        indicator_paint.setAntiAlias(true);
        indicator_paint.setStyle(Paint.Style.FILL);//填充
        indicator_paint.setColor(indicator_color);//设置颜色
        //绘制进度条的底部
        back_paint = new Paint();
        back_paint.setStyle(Paint.Style.FILL);//填充
        back_paint.setColor(back_color);//设置颜色
        back_paint.setStrokeWidth(back_height);
        //绘制进度条的进度
        fore_paint = new Paint();
        fore_paint.setStyle(Paint.Style.FILL);//填充
        fore_paint.setColor(fore_color);//设置颜色
        fore_paint.setStrokeWidth(fore_height);
    }

    /**
     * 获取自定义属性
     */
    private void obtainAttributes(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(
                attrs, R.styleable.MyProgressBar);
        //指示器
        indicator_height = (int) typedArray.getDimension(
                R.styleable.MyProgressBar_indicator_height,indicator_height);
        indicator_color = typedArray.getColor(
                R.styleable.MyProgressBar_indicator_color,DEFAULT_INDICATOR_COLOR);
        //底部进度条
        back_height = (int) typedArray.getDimension(
                R.styleable.MyProgressBar_back_line_height,back_height);
        back_color = typedArray.getColor(
                R.styleable.MyProgressBar_back_line_color,DEFAULT_BACK_LINE_COLOR);
        //顶部进度
        fore_height = (int) typedArray.getDimension(
                R.styleable.MyProgressBar_fore_line_height,fore_height);
        fore_color = typedArray.getColor(
                R.styleable.MyProgressBar_fore_line_color,DEFAULT_FORE_LINE_COLOR);
        //回收资源
        typedArray.recycle();
    }

    /**
     * 测量
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);
        setMeasuredDimension(width, height);
        progress_width = getMeasuredWidth()
                - getPaddingRight() - getPaddingLeft();//进度条的宽度
        line_endX = progress_width - triangle_width;//进度条线的终点
    }
    private int measureHeight(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);//获取测量高度模式
        int specSize = MeasureSpec.getSize(measureSpec);//获取测量高度的值
        if (specMode == MeasureSpec.EXACTLY) {//精确的测量模式
            result = specSize;
        } else {
            result = (getPaddingTop() + getPaddingBottom()
                    + Math.max(back_height, fore_height)) + indicator_height;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }
    /**
     * 绘制
     * @param canvas
     */
    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(getPaddingLeft(), getHeight()/2);
        canvas.drawLine(triangle_width,0,line_endX,0,back_paint);//底部
        float progress_with = getProgress()*1.0f/getMax();
        float progressPosX = (int) ((line_endX
                - triangle_width) * progress_with);
        canvas.drawLine(triangle_width,0,progressPosX
                + triangle_width,0,fore_paint);//顶部
        //绘制三角形
        setTriangle(canvas , progressPosX);
        canvas.restore();
    }

    /**
     * 绘制三角形
     * @param canvas
     */
    private void setTriangle(Canvas canvas,float progressPosX) {
        // 绘制等边三角形
        float w = triangle_width/2;
        float h = (float) (triangle_width * Math.sin(45));
        Path path = new Path();
        path.moveTo(progressPosX + triangle_width, - back_height/2);//起点
        path.lineTo(w + progressPosX+ triangle_width,-h- back_height/2);
        path.lineTo(-w  + progressPosX+ triangle_width,-h- back_height/2);
        path.close();
        canvas.drawPath(path, indicator_paint);
    }
    /**
     * dp转px
     * @param dpValues
     * @return
     */
    private int dp2px(int dpValues) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dpValues,
                getResources().getDisplayMetrics());
    }
}
