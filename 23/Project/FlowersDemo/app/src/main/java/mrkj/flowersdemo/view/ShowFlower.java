package mrkj.flowersdemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import mrkj.flowersdemo.application.MyDataHelper;
import mrkj.flowersdemo.ui.utils.DensityUtil;
import mrkj.flowersdemo.ui.utils.L;

/**
 * Created by Administrator on 2016/7/27.
 */
public class ShowFlower extends ImageView {

    //常量
    private static final int LINE_WIDTH = 4;//根茎的宽度
    private static final int DEFAULT = 10;//默认数量
    private static final int MARGIN_TOP = 20;//距离顶部的距离
    private static final int SPACING_WIDTH = 43;//间距
    //变量
    private Bitmap[] bitmaps;//图片数组
    private int bitmap_width;//图片的宽高
    private int width;//控件的宽度
    private int height;//控件的高度
    private Paint paint;//画线的画笔
    private Bitmap toDrawBitmap;//用于绘图的位图
    private Bitmap toLineBitmap;//用于绘图的位图
    private Canvas toDrawLineCanvas;//划线的画布
    private Canvas toDrawImageCanvas;//划图的画布
    private int imageCanvasWidth;//画布的宽度
    private int imageCanvasHeight;//画布的高度
    private int lines_count;//绘制线的条数
    private List<Point> lines_bottom_points_list;//存放根茎底部的点数集合
    private List<Point> lines_top_points_list;//存放根茎起始点的点数集合
    public ShowFlower(Context context) {
        this(context,null);
    }

    public ShowFlower(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    /**
     * 构造方法 -->用于一些变量初始化操作
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public ShowFlower(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取图片数组
        bitmaps = MyDataHelper.getInstance().getBitmapArray(context);
        bitmap_width = bitmaps[0].getWidth() + 2;
        lines_top_points_list = new ArrayList<>();
        //设置画笔
        setDrawLinePaint();
    }

    /**
     * 设置画笔
     */
    private void setDrawLinePaint() {
        paint = new Paint();
        paint.setAntiAlias(true);//抗锯齿
        paint.setStrokeWidth(LINE_WIDTH);//设置画笔的宽度
        paint.setStyle(Paint.Style.STROKE);
        paint.setDither(true);
        paint.setColor(Color.parseColor("#552E0A"));//设置画笔的颜色
    }
    /**
     * 测量
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);//获取测量宽度
        height = MeasureSpec.getSize(heightMeasureSpec);//获取测量高度
        //设置位图和设置画布
        toLineBitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        imageCanvasWidth = (bitmap_width + 10) * 4;//画布的宽度
        imageCanvasHeight = (bitmap_width + 10) * 3;//画布的高度
        toDrawBitmap = Bitmap.createBitmap(imageCanvasWidth,imageCanvasHeight, Bitmap.Config.ARGB_8888);
        //获取画布
        toDrawLineCanvas = new Canvas(toLineBitmap);
        toDrawImageCanvas = new Canvas(toDrawBitmap);
        //存放根茎的底部坐标点的集合
        lines_bottom_points_list = new ArrayList<>();
        int linesWidth = width/3;//设置花根茎所在的区间为当前控件的的1/3
        int lines_every_width = linesWidth/DEFAULT;//分为八份
        for (int i = 0;i < DEFAULT ; i++){
            Point point = new Point();
            point.set(linesWidth+(i+1)*lines_every_width/2,height);
            //把坐标点添加到集合中
            lines_bottom_points_list.add(point);//根的坐标点集合
        }
    }

    /**
     * 设置显示画花的个数
     * @param count
     */
    public void setShowFlowersCounts(int count){
        this.lines_count = count > 10 ? 10 : count;
        L.e("count" , lines_count + "");
    }
    /**
     * 绘制
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        L.e("canvas" ,  "重绘");
        canvas.drawColor(Color.TRANSPARENT);//设置背景透明
        if (lines_count != 0){//有种出的花才会绘制花朵
            toDrawLineCanvas.drawColor(Color.TRANSPARENT,PorterDuff.Mode.CLEAR);//设置画布的背景
            toDrawImageCanvas.drawColor(Color.TRANSPARENT,PorterDuff.Mode.CLEAR);//绘制花朵
            drawFlowers();//画花
            drawLines();//画根茎
            canvas.drawBitmap(toLineBitmap,0,DensityUtil.dip2px(getContext(),40),null);//绘制线
            canvas.drawBitmap(toDrawBitmap,(width -(bitmap_width + 10) * 4)/2 ,
                    DensityUtil.dip2px(getContext(),40),null);//绘制图片
        }
    }

    /**
     * 画花
     */
    private void drawFlowers() {
        int index = 0;//标记
        int three = (imageCanvasWidth - bitmap_width*3)/2;//三列
        int four = (imageCanvasWidth - bitmap_width*4)/2;//四列
        for (int i = 0;i < 3;i++){//三行四列
            int num = i * 1 == 1 ? 0 : 1;//第二行四列其余三列(0>4,1>3)
            /**
             * 因为我想得到第一行和第三行有三多，第二行有四朵花
             * 所以这样去绘制图片
             */
            for (int j = 0;j <(4 - num); j++){
                if (index == lines_count){//当把将要绘制的数量绘制完成时跳出方法
                    return;
                }
                int child_bitmap_width;
                int child_bitmap_height;
                if (num == 1){//三列
                    child_bitmap_width = three + j*bitmap_width;
                    child_bitmap_height = i*bitmap_width;
                    toDrawImageCanvas.drawBitmap(bitmaps[index],child_bitmap_width,child_bitmap_height,null);
                }else {//四列
                    child_bitmap_width = four + j*bitmap_width;
                    child_bitmap_height = i*bitmap_width;
                    toDrawImageCanvas.drawBitmap(bitmaps[index],child_bitmap_width,child_bitmap_height,null);
                }
                lines_top_points_list.add(new Point(child_bitmap_width+bitmap_width/2,child_bitmap_height +bitmap_width/2 ));
                L.e("INDEX",index + "");
                index++;
            }
        }
    }
    /**
     * 画根茎
     */
    private void drawLines() {
        for(int i = 0;i < lines_count ; i++){//画线
            Path path = new Path();//路径
            path.moveTo(lines_top_points_list.get(i).x,lines_top_points_list.get(i).y);
            float x2 = width/2;
            float y2 = height/2;
            path.quadTo(x2, y2, lines_bottom_points_list.get(i).x, lines_bottom_points_list.get(i).y);
            toDrawLineCanvas.drawPath(path, paint);//绘制路径
        }
    }

    /**
     * 设置想要替换的图片()
     * @param wantShowBitmap
     */
    public void setShowFlowerArray(Bitmap[] wantShowBitmap){
        //数组的长度是定长的
        for (int i = 0; i < wantShowBitmap.length ; i++){
            this.bitmaps[i] = wantShowBitmap[i];//替换对象
        }
    }
}
