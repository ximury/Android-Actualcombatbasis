package com.mingrisoft.fieldassistant.message;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.mingrisoft.fieldassistant.R;
import com.mingrisoft.fieldassistant.home.BaseActivity;

/**
 * Created by Administrator on 2016/7/5 0005.
 */
public class MsgDetailActivity extends BaseActivity {

    private ImageButton backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.masg_detail_layout);
        backBtn = (ImageButton) findViewById(R.id.back_im_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
