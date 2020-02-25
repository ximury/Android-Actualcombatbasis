package mrkj.healthylife.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import mrkj.healthylife.R;
import mrkj.healthylife.fragment.SportFragment;

/**
 * 测试界面
 */
public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        //添加SportFragment
        Bundle bundle = new Bundle();
        bundle.putBoolean("is_launch", false);
        SportFragment sportFragment = new SportFragment();
        sportFragment.setArguments(bundle);
        getSupportFragmentManager().
                beginTransaction().
                add(R.id.frag,sportFragment).
                commit();
    }
}
