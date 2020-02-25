package com.mingrisoft.fieldassistant.workingPlan;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.mingrisoft.fieldassistant.home.BaseActivity;
import com.mingrisoft.fieldassistant.R;

/**
 * Created by Administrator on 2016/7/5 0005.
 */
public class WorkingPlanActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    private FragmentTransaction transaction;
    private RadioGroup radioGroup;
    private PlanReferFragment planReferFragment;
    private TargetsFragment targetsFragment;
    private Button addBtn;
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.working_plan_layout);
        radioGroup = (RadioGroup) findViewById(R.id.working_radio_group);
        radioGroup.setOnCheckedChangeListener(this);
        addBtn = (Button) findViewById(R.id.add_new_plan);
        addBtn.setOnClickListener(this);
        backBtn = (ImageView) findViewById(R.id.back_btn);
        backBtn.setOnClickListener(this);
        planReferFragment = new PlanReferFragment();
        setFragment(planReferFragment);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //使radioGroup每次都是单选
        radioGroup.setClickable(false);

        switch (checkedId) {

            //点击相应的按钮 加载相应的布局
            case R.id.working_plan_refer_btn:
                if (planReferFragment == null) {
                    planReferFragment = new PlanReferFragment();
                }
                setFragment(planReferFragment);
                break;

            case R.id.working_targets_btn:
                if (targetsFragment == null) {
                    targetsFragment = new TargetsFragment();
                }
                setFragment(targetsFragment);
                break;

        }
    }

    public void setFragment(Fragment fragment) {
        //显示相应的Fragment
        transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.add_new_plan:
                Intent intent = new Intent(this, PlanReferSendActivity.class);
                startActivity(intent);
                break;
            case R.id.back_btn:
                finish();
                break;

        }
    }
}
