package mrkj.flowersdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

import mrkj.flowersdemo.ui.bean.MyCircle;

/**
 * Created by Administrator on 2016/7/31.
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback,Runnable{

    private Paint paint;//画笔
    private int counts = 5;//画圆的次数数
    private float screenWidth,screenHeight;//屏幕宽高
    private float[] raidusArray;
    private Thread thread;
    private boolean isRunning;//判断图片是否在进行移动
    private static Random random = new Random();//获取随机数
    private ArrayList<MyCircle> circle_xxl = new ArrayList<>();//存圆的集合
    private ArrayList<MyCircle> circle_xl = new ArrayList<>();
    private ArrayList<MyCircle> circle_l = new ArrayList<>();
    private ArrayList<MyCircle> circle_m = new ArrayList<>();
    private ArrayList<MyCircle> circle_s = new ArrayList<>();
    private SurfaceHolder holder;
    private Canvas canvas = null;
    private String theWhite = "#45F8E58D";
    /**
     * 构造方法
     * @param context
     */
    public MySurfaceView(Context context) {
        this(context,null);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        raidusArray =new float[]{
                dip2px(context,2*2),
                dip2px(context,4*2),
                dip2px(context,6*2),
                dip2px(context,8*2),
                dip2px(context,10*2)};//圆的半径
        holder = getHolder();
        holder.addCallback(this);
        //设置底层绘制的surfaceview透明
        setZOrderOnTop(true);
        holder.setFormat(PixelFormat.TRANSPARENT);
        init(context);
        addRandomCircle();
    }

    /**
     * 初始化园
     */
    private void addRandomCircle() {
        for (int i = 0;i < counts;i++){
            circle_xxl.add(new MyCircle()
                    .setCenterX(random.nextFloat()*screenWidth)
                    .setCenterY(random.nextFloat()*screenHeight)
                    .setRadius(raidusArray[4])
                    .setSpeed(10));
            circle_xl.add(new MyCircle()
                    .setCenterX(random.nextFloat()*screenWidth)
                    .setCenterY(random.nextFloat()*screenHeight)
                    .setRadius(raidusArray[3])
                    .setSpeed(8));
            circle_l.add(new MyCircle()
                    .setCenterX(random.nextFloat()*screenWidth)
                    .setCenterY(random.nextFloat()*screenHeight)
                    .setRadius(raidusArray[2])
                    .setSpeed(6));
            circle_m.add(new MyCircle()
                    .setCenterX(random.nextFloat()*screenWidth)
                    .setCenterY(random.nextFloat()*screenHeight)
                    .setRadius(raidusArray[1])
                    .setSpeed(4));
            circle_s.add(new MyCircle()
                    .setCenterX(random.nextFloat()*screenWidth)
                    .setCenterY(random.nextFloat()*screenHeight)
                    .setRadius(raidusArray[0])
                    .setSpeed(2));
        }
    }

    /**
     * 初始化
     * @param context
     */
    private void init(Context context) {
        screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        screenHeight = context.getResources().getDisplayMetrics().heightPixels;
//        Log.e("屏幕的宽和高","w-->" +screenWidth + "h-->" +screenHeight);
        paint = new Paint();
        paint.setColor(Color.parseColor(theWhite));
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
    }


    /**
     * run(线程)
     */
    @Override
    public void run() {
        while (isRunning){

            try {
                canvas = holder.lockCanvas();//锁定画布
                canvas.drawColor(Color.RED, PorterDuff.Mode.CLEAR);
                if (canvas != null){
                    //画圆
                    drawMyCircle(canvas);
                    //改变位置
                    for (int i = 0; i < counts;i++){
                        CircleDrop(circle_xxl.get(i),10);
                        CircleDrop(circle_xl.get(i),8);
                        CircleDrop(circle_l.get(i),6);
                        CircleDrop(circle_m.get(i),4);
                        CircleDrop(circle_s.get(i),2);
                    }
                    if (canvas != null){
                        holder.unlockCanvasAndPost(canvas);
                    }
                }
                Thread.sleep(200);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 降落
     * @param myCircle
     */
    private void CircleDrop(MyCircle myCircle,int speed) {
        if (myCircle.getCenterY() > screenHeight){
            myCircle.setCenterY(0);
        }
        myCircle.setCenterY(myCircle.getCenterY() + speed);
    }

    /**
     * 画圆
     * @param canvas
     */
    private void drawMyCircle(Canvas canvas) {
        for (int i = 0 ; i < counts;i++){
            canvas.drawCircle(circle_s.get(i).getCenterX(),
                    circle_s.get(i).getCenterY(),circle_s.get(i).getRadius(),paint);
            canvas.drawCircle(circle_m.get(i).getCenterX(),
                    circle_m.get(i).getCenterY(),circle_m.get(i).getRadius(),paint);
            canvas.drawCircle(circle_l.get(i).getCenterX(),
                    circle_l.get(i).getCenterY(),circle_l.get(i).getRadius(),paint);
            canvas.drawCircle(circle_xl.get(i).getCenterX(),
                    circle_xl.get(i).getCenterY(),circle_xl.get(i).getRadius(),paint);
            canvas.drawCircle(circle_xxl.get(i).getCenterX(),
                    circle_xxl.get(i).getCenterY(),circle_xxl.get(i).getRadius(),paint);
        }
    }

    /**
     * callback(回调)
     * @param holder
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }
    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        isRunning = (visibility == VISIBLE);
    }
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
