package mrkj.flowersdemo.ui.activity;

import android.graphics.Color;
import android.text.Html;
import android.widget.TextView;

import mrkj.flowersdemo.R;
import mrkj.flowersdemo.ui.base.BaseActivity;
import mrkj.flowersdemo.ui.utils.SaveKeyValues;
import mrkj.flowersdemo.view.MyProgressBar;

/**
 * 说明
 */
public class IllustrateActivity extends BaseActivity {

    private MyProgressBar myProgressBar;//显示连续登陆的天数的进度条
    private TextView showDays;//显示天数
    @Override
    protected int setChildContentView() {
        return R.layout.activity_illustrate;
    }

    @Override
    protected void onCreateChild() {
        setStyle();//设置显示效果
        //初始化控件
        myProgressBar = (MyProgressBar) findViewById(R.id.show_day_progress);
        showDays = (TextView) findViewById(R.id.show_login_days);
        int login_days = SaveKeyValues.getIntValues("login_days",0);//获取登陆天数
        if (login_days > 5){
            myProgressBar.setMax(login_days);
        }
        myProgressBar.setProgress(login_days);
        showDays.setText(Html.fromHtml("已连续登陆<b><font color='#ff0000'>"+ login_days+"</b></font>天"));
    }
    private void setStyle() {
        //设置界面背景
        setActivityBackGround(R.mipmap.mrkj_ill_background);
        //设置标题栏的背景
        setTitleBackgroundColor(Color.TRANSPARENT);
        //设置标题栏
        setTitle("说明",getResources().getColor(R.color.left_text_color),R.mipmap.mrkj_com_back,true);
    }
    /**
     * 全屏（无状态栏）
     * @return
     */
    @Override
    public boolean setActivityFullScreen() {
        return true;
    }

    /**
     * 添加标题
     * @return
     */
    @Override
    public boolean isAddTitle() {
        return true;
    }
}
