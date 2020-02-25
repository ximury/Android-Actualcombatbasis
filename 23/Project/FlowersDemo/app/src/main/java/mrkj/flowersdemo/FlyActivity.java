package mrkj.flowersdemo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

/**
 * 让大雁飞
 */
public class FlyActivity extends AppCompatActivity {
    private int screenWidth;//获取屏幕宽度
    private ImageView bird;//大雁
    private AnimationDrawable birdAnimation;//帧动画
    private AnimatorSet birdAnimatorset;//属性动画
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fly);
        getWindowWidth();//获取屏幕款
        bird = (ImageView) findViewById(R.id.bird);
        bird.setTranslationX(-screenWidth);//设置大雁摆放位置向左平移一个屏幕的宽
        birdAnimation = (AnimationDrawable) bird.getDrawable();//获取帧动画
        //设置bird的动画
        birdAnimatorset = new AnimatorSet();
        ObjectAnimator birdAnimatorR =
                ObjectAnimator.ofFloat(bird,"translationX",screenWidth);
        birdAnimatorR.setDuration(30*1000);//设置运行时间
        birdAnimatorR.setInterpolator(new LinearInterpolator());
        birdAnimatorR.setRepeatCount(-1);//设置从头开始循环
        birdAnimatorset.play(birdAnimatorR);
        birdAnimation.start();//开启帧动画
        birdAnimatorset.start();//开启属性动画
    }
    /**
     * 获取屏幕的宽度和高度
     */
    private void getWindowWidth(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
    }
}
