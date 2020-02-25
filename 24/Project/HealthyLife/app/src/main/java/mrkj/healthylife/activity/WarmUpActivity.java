package mrkj.healthylife.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import mrkj.healthylife.R;
import mrkj.healthylife.application.DemoApplication;
import mrkj.healthylife.base.BaseActivity;
import mrkj.healthylife.fragment.FindFragment;

public class WarmUpActivity extends BaseActivity {

    private int what;
    private ImageView imageView;
    private TextView type,count,time;
    private Button start;
    @Override
    protected void setActivityTitle() {
        initTitle();
        setTitle("推荐热身", this);
        setMyBackGround(R.color.watm_background_gray);
        setTitleTextColor(R.color.theme_blue_two);
        setTitleLeftImage(R.mipmap.mrkj_back_blue);
    }

    @Override
    protected void getLayoutToView() {
        setContentView(R.layout.activity_warm_up);
    }

    @Override
    protected void initValues() {
        what = getIntent().getIntExtra("random",0);
        Log.e("随机数", "【" + what + "】");
    }

    @Override
    protected void initViews() {
        imageView = (ImageView) findViewById(R.id.image_show);
        type = (TextView) findViewById(R.id.xiangmu);
        count = (TextView) findViewById(R.id.cishu1);
        time = (TextView) findViewById(R.id.add_plan);
        start = (Button) findViewById(R.id.start_warm);
    }

    @Override
    protected void setViewsListener() {
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WarmUpActivity.this,PlayActivity.class).putExtra("play_type",what));
                finish();
            }
        });

    }

    @Override
    protected void setViewsFunction() {
        time.setText("12秒");
        count.setText(FindFragment.cishu);
        type.setText(DemoApplication.shuoming[what]);
        imageView.setImageBitmap(DemoApplication.bitmaps[what]);
    }
}
