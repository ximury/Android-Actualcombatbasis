package mrkj.healthylife.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import mrkj.healthylife.R;
import mrkj.healthylife.base.BaseActivity;
import mrkj.healthylife.fragment.FindFragment;
import mrkj.healthylife.fragment.HeartFragment;
import mrkj.healthylife.fragment.MineFragment;
import mrkj.healthylife.fragment.SportFragment;
import mrkj.healthylife.utils.Constant;
import mrkj.healthylife.utils.SaveKeyValues;

/**
 * 功能界面
 */
public class FunctionActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener{

    //变量
    private long exitTime;//第一次单机退出键的时间
    private int load_values;//判断加载fragment的变量
    //控件
    private RadioGroup radioGroup;//切换按钮的容器
    private RadioButton sport_btn,find_btn,heart_btn,mine_btn;//切换按钮
    //碎片
    private SportFragment sportFragment;//运动
    private FindFragment findFragment;//发现
    private HeartFragment heartFragment;//心率
    private MineFragment mineFragment;//我的
    /**
     * 设置标题
     */
    @Override
    protected void setActivityTitle() {

    }

    /**
     * 初始化界面
     */
    @Override
    protected void getLayoutToView() {
        setContentView(R.layout.activity_function);
    }

    /**
     * 初始化相关变量
     */
    @Override
    protected void initValues() {

        //如果这个值等于1就加载运动界面，等于2就加载发现界面
        load_values = SaveKeyValues.getIntValues("launch_which_fragment",0);
        Log.e("加载判断值", load_values + "");
        //实例化相关碎片
        sportFragment = new SportFragment();
        findFragment = new FindFragment();
        heartFragment = new HeartFragment();
        mineFragment = new MineFragment();
        //初始化界面
        if (load_values == Constant.TURN_MAIN){
            Bundle bundle = new Bundle();
            bundle.putBoolean("is_launch",true);
            sportFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().add(R.id.frag_home,sportFragment,Constant.SPORT_TAG).commit();
        }else {
            getSupportFragmentManager().beginTransaction().add(R.id.frag_home,findFragment,Constant.FIND_TAG).commit();
        }
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {
        radioGroup = (RadioGroup) findViewById(R.id.ui_btn_group);
        sport_btn = (RadioButton) findViewById(R.id.sport_btn);
        find_btn = (RadioButton) findViewById(R.id.find_btn);
        heart_btn = (RadioButton) findViewById(R.id.heart_btn);
        mine_btn = (RadioButton) findViewById(R.id.mine_btn);
    }

    /**
     * 设置监听
     */
    @Override
    protected void setViewsListener() {
        radioGroup.setOnCheckedChangeListener(this);
    }

    /**
     * 设置功能
     */
    @Override
    protected void setViewsFunction() {
        if (load_values == Constant.TURN_MAIN){
            sport_btn.setChecked(true);
        }else {
            find_btn.setChecked(true);
        }
    }

    /**
     * 切换界面
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (checkedId){
            case R.id.sport_btn://运动
                if (!sportFragment.isAdded()){
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("is_launch", false);
                    sportFragment.setArguments(bundle);
                    transaction.replace(R.id.frag_home,sportFragment,Constant.SPORT_TAG);
                }
                break;
            case R.id.find_btn://发现
                if (!findFragment.isAdded()){
                    transaction.replace(R.id.frag_home, findFragment,Constant.FIND_TAG);
                }
                break;
            case R.id.heart_btn://心率
                if (!heartFragment.isAdded()){
                    transaction.replace(R.id.frag_home,heartFragment,Constant.HEART_TAG);
                }
                break;
            case R.id.mine_btn://我的
                if (!mineFragment.isAdded()){
                    transaction.replace(R.id.frag_home,mineFragment,Constant.MINE_TAG);
                }
                break;
            default:
                break;
        }
        transaction.commit();
    }

    /**
     * 按两次退出按钮退出程序
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            // System.currentTimeMillis()无论何时调用，肯定大于2000
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
