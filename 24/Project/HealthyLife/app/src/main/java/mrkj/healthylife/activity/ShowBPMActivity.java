package mrkj.healthylife.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import mrkj.healthylife.R;
import mrkj.healthylife.base.BaseActivity;
import mrkj.healthylife.utils.SaveKeyValues;
import mrkj.library.wheelview.circlebar.ColorArcProgressBar;

public class ShowBPMActivity extends BaseActivity {

    private TextView one,two,three;
    private ImageView share;//分享
    private ColorArcProgressBar bpm_show;//显示心率
    private int bpm_values;//心率值
    @Override
    protected void setActivityTitle() {
        initTitle();
        setMyBackGround(R.color.watm_background_gray);
        share = setTitle("心率测试结果", this, R.mipmap.mrkj_share_blue);
        setTitleLeftImage(R.mipmap.mrkj_back_blue);
        setTitleTextColor(R.color.theme_blue_two);
        share.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void getLayoutToView() {
        setContentView(R.layout.activity_show_bpm);
    }

    @Override
    protected void initValues() {
        bpm_values = Integer.parseInt(SaveKeyValues.getStringValues("bpm","80"));
    }

    @Override
    protected void initViews() {
        bpm_show = (ColorArcProgressBar) findViewById(R.id.heat_bpm_values);
        one = (TextView) findViewById(R.id.one);
        two = (TextView) findViewById(R.id.two);
        three = (TextView) findViewById(R.id.three);
    }

    @Override
    protected void setViewsListener() {

    }

    @Override
    protected void setViewsFunction() {
        bpm_show.setMaxValues(220);
        bpm_show.setCurrentValues(bpm_values);

        if (bpm_values <= 60){
            one.setTextColor(Color.RED);
        }else if(bpm_values > 60 && bpm_values <= 100){
            two.setTextColor(Color.RED);
        }else {
            three.setTextColor(Color.RED);
        }
    }
}
