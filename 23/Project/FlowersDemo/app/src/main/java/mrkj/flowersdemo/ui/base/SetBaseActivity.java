package mrkj.flowersdemo.ui.base;

import android.support.v4.widget.DrawerLayout;
import android.widget.LinearLayout;

/**
 * 用于设置界面
 * Created by Administrator on 2016/7/19.
 */
public interface SetBaseActivity {
    //是否开启抽屉
    boolean openDrawerLayout();
    //设置是否全屏
    boolean setActivityFullScreen();
    //获取抽屉的从左向右滑出的布局
    void initViewInTheDrawerLayout(DrawerLayout drawer, LinearLayout leftView);
}
