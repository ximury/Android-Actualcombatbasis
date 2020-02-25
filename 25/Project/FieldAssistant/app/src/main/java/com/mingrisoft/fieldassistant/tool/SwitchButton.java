package com.mingrisoft.fieldassistant.tool;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.mingrisoft.fieldassistant.R;

/**
 * <p/>
 * 开关  自定义button
 */
public class SwitchButton extends FrameLayout {
    private ImageView openImage;
    private ImageView closeImage;

    public SwitchButton(Context context) {
        this(context, null);
    }

    public SwitchButton(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }

    public SwitchButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        //context通过调用obtainStyledAttributes方法来获取一个TypeArray，然后由该TypeArray来对属性进行设置
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SwitchButton);
        Drawable openDrawable = typedArray.getDrawable(R.styleable.SwitchButton_switchOpenImage);
        Drawable closeDrawable = typedArray.getDrawable(R.styleable.SwitchButton_switchCloseImage);
        int switchStatus = typedArray.getInt(R.styleable.SwitchButton_switchStatus, 0);
        //调用结束后务必调用recycle()方法，否则这次的设定会对下次的使用造成影响
        typedArray.recycle();


        LayoutInflater.from(context).inflate(R.layout.switch_button, this);
        openImage = (ImageView) findViewById(R.id.iv_switch_open);
        closeImage = (ImageView) findViewById(R.id.iv_switch_close);

        if (openDrawable != null) {
            openImage.setImageDrawable(openDrawable);
        }
        if (closeDrawable != null) {
            closeImage.setImageDrawable(closeDrawable);
        }
        if (switchStatus == 1) {
            closeSwitch();
        }

    }

    /**
     * 开关是否为打开状态
     */
    public boolean isSwitchOpen() {
        return openImage.getVisibility() == View.VISIBLE;
    }

    /**
     * 打开开关
     */
    public void openSwitch() {
        openImage.setVisibility(View.VISIBLE);
        closeImage.setVisibility(View.INVISIBLE);
    }

    /**
     * 关闭开关
     */
    public void closeSwitch() {
        openImage.setVisibility(View.INVISIBLE);
        closeImage.setVisibility(View.VISIBLE);
    }


}
