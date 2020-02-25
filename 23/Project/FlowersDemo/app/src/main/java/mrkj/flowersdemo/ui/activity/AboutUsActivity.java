package mrkj.flowersdemo.ui.activity;

import android.graphics.Color;

import mrkj.flowersdemo.R;
import mrkj.flowersdemo.ui.base.BaseActivity;

/**
 * 关于我们
 */
public class AboutUsActivity extends BaseActivity {

    @Override
    protected int setChildContentView() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void onCreateChild() {
        setStyle();
    }
    /**
     * 初始化界面的显示
     */
    private void setStyle() {
        //设置界面背景
        setActivityBackGround(R.mipmap.mrkj_grow_background);
        //设置标题栏的背景
        setTitleBackgroundColor(Color.TRANSPARENT);
        //设置标题栏
        setTitle("关于我们",getResources().getColor(R.color.left_text_color),R.mipmap.mrkj_com_back,true);
    }
    @Override
    public boolean isAddTitle() {
        return true;
    }
    @Override
    public boolean setActivityFullScreen() {
        return true;
    }
}
