package com.mingrisoft.fieldassistant.recorder;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;

import com.mingrisoft.fieldassistant.R;

public class CiDai extends RelativeLayout {
	private ImageView left_circle, right_circle;
	private RotateAnimation rotateAnimation;// 动画
	// 设置默认是100子控件是正方形此处只设置宽（也代表这高）
	private static final int default_width = 100;
	// 父控件的宽默认是子控件宽的两倍
	private static final int parent_default_width = 2 * default_width;
	// 获取的正方形边长
	private int width;

	public CiDai(Context context) {
		this(context, null);
	}

	public CiDai(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CiDai(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		init(context);
	}

	/**
	 * 测量
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		// 获取绘制模式
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		// 获取测量值
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		// Log.d("宽度", widthSize + "");
		// Log.d("高度", heightSize + "");
		// 判断宽
		int measuredWidth = opinionWidth(widthMode, widthSize);
		// 判断高 -->此处没啥用
		int measuredHeight = opinionHeight(heightMode, heightSize);
		// Log.d("设置宽度", measuredWidth + "");
		// Log.d("预设置高度", measuredHeight + "");
		measuredHeight = measuredWidth / 2;
		// Log.d("设置的高度", measuredHeight + "");
		width = measuredHeight;
		// 设置控件的宽和高
		setMeasuredDimension(measuredWidth, measuredHeight);
	}

	/**
	 * 设置布局
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		// 获取子控件的个数
		int childCount = getChildCount();
		for (int i = 0; i < childCount; i++) {
			View childView = getChildAt(i);
			if ((i + 1) % 2 == 0) {
				// Log.i("i_if", i + "");
				childView.layout(width, 0, 2 * width, width);
			} else {
				// Log.i("i_else", i + "");
				childView.layout(0, 0, width, width);
			}
		}
	}

	/**
	 * 判断宽
	 *
	 */

	private int opinionWidth(int wMode, int wSize) {
		int result = 0;
		if (wMode == MeasureSpec.EXACTLY) {
			result = wSize;
		} else {
			// 设置默认宽度
			int size = parent_default_width;
			if (wMode == MeasureSpec.AT_MOST) {
				result = Math.min(wSize, size);
			}
		}
		return result;
	}

	/**
	 * 判断高
	 *
	 * @param hMode
	 * @param hSize
	 * @return
	 */

	private int opinionHeight(int hMode, int hSize) {
		int result = 0;
		if (hMode == MeasureSpec.EXACTLY) {
			result = hSize;
		} else {
			// 设置默认高度度
			int size = default_width;
			if (hMode == MeasureSpec.AT_MOST) {
				result = Math.min(hSize, size);
			}
		}
		return result;
	}

	/**
	 * 初始化控件
	 */
	private void init(Context context) {
		// 初始化控件
		ImageView left_back = new ImageView(context);
		ImageView right_back = new ImageView(context);
		left_circle = new ImageView(context);
		right_circle = new ImageView(context);
		// 设置控件的初始值
		LayoutParams paramsBL = new LayoutParams(default_width, default_width);
		paramsBL.addRule(ALIGN_PARENT_LEFT);
		LayoutParams paramsBR = new LayoutParams(default_width, default_width);
		paramsBR.addRule(ALIGN_PARENT_RIGHT);
		LayoutParams paramsCL = new LayoutParams(default_width, default_width);
		paramsCL.addRule(ALIGN_PARENT_LEFT);
		LayoutParams paramsCR = new LayoutParams(default_width, default_width);
		paramsCR.addRule(ALIGN_PARENT_RIGHT);
		// 向控件添加图片
		left_back.setImageResource(R.drawable.left);
		right_back.setImageResource(R.drawable.right);
		left_circle.setImageResource(R.drawable.circle);
		right_circle.setImageResource(R.drawable.circle);
		// 设置图片充满子控件 图片清晰度很高很高
		left_back.setScaleType(ScaleType.FIT_XY);
		right_back.setScaleType(ScaleType.FIT_XY);
		left_circle.setScaleType(ScaleType.FIT_XY);
		right_circle.setScaleType(ScaleType.FIT_XY);
		// 想布局中添加控件
		addView(left_back, paramsBL);
		addView(right_back, paramsBR);
		addView(left_circle, paramsCL);
		addView(right_circle, paramsCR);
		initAnimotain();
		startRotate();
	}

	/**
	 * 设置动画
	 */
	private void initAnimotain() {
		rotateAnimation = new RotateAnimation(0, 359, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		rotateAnimation.setInterpolator(new LinearInterpolator());		// 匀速
		rotateAnimation.setRepeatCount(-1);
		rotateAnimation.setDuration(2000);								// 每转一周是两秒
	}

	/**
	 * 开始动画
	 */
	public void startRotate() {
		this.setFocusableInTouchMode(true);
		this.setFocusable(true);
		left_circle.setAnimation(rotateAnimation);
		right_circle.setAnimation(rotateAnimation);
	}

	/**
	 * 结束动画
	 */
	public void stopRotate() {
		this.setFocusableInTouchMode(false);
		this.setFocusable(false);
		left_circle.clearAnimation();
		right_circle.clearAnimation();
	}
}
