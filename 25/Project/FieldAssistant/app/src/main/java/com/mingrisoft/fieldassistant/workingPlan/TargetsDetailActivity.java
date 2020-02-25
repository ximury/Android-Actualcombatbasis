package com.mingrisoft.fieldassistant.workingPlan;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.mingrisoft.fieldassistant.home.BaseActivity;
import com.mingrisoft.fieldassistant.R;

/**
 * Created by Administrator on 2016/7/5 0005.
 */
public class TargetsDetailActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.targets_detail_layout);
        backBtn = (ImageButton) findViewById(R.id.back_im_btn);
        backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_im_btn:
                finish();
                break;
        }
    }
}
