package mrkj.flowersdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.EditText;

public class MyEditText extends EditText {

    private int lineColor;//横线颜色
    private float lineHeight;//横线宽度

    public MyEditText(Context context) {
        this(context,null);


    }
    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        //设置默认颜色和横线宽度
        lineColor = Color.parseColor("#288C8A");
        lineHeight = 1f;//默认高度(线的粗度)
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //创建画笔
        Paint mPaint = new Paint();
        mPaint.setStrokeWidth(lineHeight);//设置画笔的粗度
        mPaint.setStyle(Paint.Style.FILL);//设置画笔的绘制样式
        mPaint.setColor(lineColor);//设置画笔而定颜色

        //获取参数
        int padL = this.getPaddingLeft();//获取左侧内边距
        int padR = this.getPaddingRight();//获取右侧内边距
        int padT = this.getPaddingTop();//获取上册内边距
        int lines = this.getMaxLines() >
                this.getLineCount()? this.getMaxLines():this.getLineCount();//获取行数
        float size = this.getTextSize();//获取文字大小
        float baseTop = padT + size / 6;//从上向下第一条线的位置
        float gap = this.getLineHeight();//获取行宽
        //根据设置的最大行数来绘制文本的下划线
        for(int i = 1;i <= lines;i++)
        {
            /**
             * drawLine()通过该方法绘制一条直线
             * 相关参数
             * l 左侧起始坐标X
             * t 上部起始坐标Y
             * r 右侧结束坐标X
             * b 底部结束坐标Y
             * l,t,r,b,画笔
             */
            canvas.drawLine(padL
                    , baseTop + gap * i
                    , this.getWidth() - padR
                    , baseTop + gap * i
                    , mPaint);
        }
    }
}