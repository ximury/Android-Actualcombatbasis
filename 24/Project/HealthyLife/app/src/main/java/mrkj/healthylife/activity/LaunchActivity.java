package mrkj.healthylife.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.WindowManager;

import mrkj.healthylife.R;
import mrkj.healthylife.utils.Constant;
import mrkj.healthylife.utils.SaveKeyValues;

/**
 * 启动页
 */
public class LaunchActivity extends AppCompatActivity {
    private boolean isFirst;//是否为第一次启动
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 1) {
                if (isFirst){
                    startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                }else {
                    startActivity(new Intent(LaunchActivity.this, FunctionActivity.class));
                }
                finish();
            }
            return false;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //不显示状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        //设置默认加载运动首页
        SaveKeyValues.putIntValues("launch_which_fragment", Constant.TURN_MAIN);
        //判断是否是第一次启动
        int count = SaveKeyValues.getIntValues("count" , 0);
        isFirst = (count == 0)? true : false;
        handler.sendEmptyMessageDelayed(1, 3000);
    }
    /**
     * 屏蔽返回键
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            return false;
        }
        return false;
    }
}
