package mrkj.flowersdemo.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import mrkj.flowersdemo.R;
import mrkj.flowersdemo.application.MyDataHelper;
import mrkj.flowersdemo.ui.base.BaseActivity;
import mrkj.flowersdemo.ui.utils.L;
import mrkj.flowersdemo.ui.utils.SaveKeyValues;

/**
 * 选择花的样式
 * 一个不选的话就是默认从当前显示的花中随机选出花
 * 选的话就从选的花中进行随机选取
 */
public class SelectedFlowerActivity extends BaseActivity implements
        CompoundButton.OnCheckedChangeListener,View.OnClickListener{

    //复选框
    private CheckBox flower1,flower2,
            flower3,flower4,flower5,
            flower6,flower7,flower8,flower9;
    //存放选中的想要种的花
    private SparseArray selectedFlowers;
    //显示选中花的个数
    private TextView showSelectedCounts;
    //确定按钮
    private Button select_end_btn;
    //加载布局
    @Override
    protected int setChildContentView() {
        return R.layout.activity_selected_flower;
    }

    @Override
    protected void onCreateChild() {
        //设置背景
        setActivityBackGround(R.mipmap.mrkj_grow_background);
        //设置标题栏背景
        setTitleBackground(Color.TRANSPARENT);
        //设置标题栏
        setTitle("秘密花园", Color.parseColor("#288C8A"),R.mipmap.mrkj_sharesetting_cross,true);
        //初始化控件
        initView();
        //初始化
        selectedFlowers = new SparseArray();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        boolean unlock = SaveKeyValues.getBooleanValues("unlock",false);
        showSelectedCounts = (TextView) findViewById(R.id.select_flower_counts);
        select_end_btn = (Button) findViewById(R.id.select_end);
        select_end_btn.setOnClickListener(this);
        //选花
        flower1 = (CheckBox) findViewById(R.id.flower1);
        flower2 = (CheckBox) findViewById(R.id.flower2);
        flower3 = (CheckBox) findViewById(R.id.flower3);
        flower4 = (CheckBox) findViewById(R.id.flower4);
        flower5 = (CheckBox) findViewById(R.id.flower5);
        flower6 = (CheckBox) findViewById(R.id.flower6);
        flower7 = (CheckBox) findViewById(R.id.flower7);
        flower8 = (CheckBox) findViewById(R.id.flower8);
        flower9 = (CheckBox) findViewById(R.id.flower9);
        //复选框改变监听事件
        flower1.setOnCheckedChangeListener(this);
        flower2.setOnCheckedChangeListener(this);
        flower3.setOnCheckedChangeListener(this);
        flower4.setOnCheckedChangeListener(this);
        flower5.setOnCheckedChangeListener(this);
        flower6.setOnCheckedChangeListener(this);
        flower7.setOnCheckedChangeListener(this);
        flower8.setOnCheckedChangeListener(this);
        flower9.setOnCheckedChangeListener(this);
        //解锁显示获取隐藏(复选框)
        if (unlock){
            flower8.setVisibility(View.VISIBLE);
            flower9.setVisibility(View.GONE);
        }else {
            flower8.setVisibility(View.GONE);
            flower9.setVisibility(View.VISIBLE);
        }
        //flower的控件的集合
        ArrayList<CheckBox> flower_list = new ArrayList<>();
        flower_list.add(flower1);
        flower_list.add(flower2);
        flower_list.add(flower3);
        flower_list.add(flower4);
        flower_list.add(flower5);
        flower_list.add(flower6);
        flower_list.add(flower7);
        flower_list.add(flower8);
        flower_list.add(flower9);
        //设置花的名字
        String[] flower_name = MyDataHelper.getInstance().getFlowerNames();
        //获取花朵名称集合的前八的数据
        for (int i = 0;i < 8;i++){
            if (i == 7){//此时是名称集合的最后一条数据
                flower_list.get(i).setText(flower_name[i]);
                flower_list.get(i + 1).setText(flower_name[i]);
            }else {
                flower_list.get(i).setText(flower_name[i]);
            }
        }
    }

    /**
     * 复选框的监听事件
     * @param buttonView
     * @param isChecked
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int index = -1;//标记值
        switch (buttonView.getId()){
            case R.id.flower1:
                L.e("选中了1",isChecked + "");
                index = 0;
                break;
            case R.id.flower2:
                L.e("选中了2",isChecked + "");
                index = 1;
                break;
            case R.id.flower3:
                L.e("选中了3",isChecked + "");
                index = 2;
                break;
            case R.id.flower4:
                L.e("选中了4",isChecked + "");
                index = 3;
                break;
            case R.id.flower5:
                L.e("选中了5",isChecked + "");
                index = 4;
                break;
            case R.id.flower6:
                L.e("选中了6",isChecked + "");
                index = 5;
                break;
            case R.id.flower7:
                L.e("选中了7",isChecked + "");
                index = 6;
                break;
            case R.id.flower8:
                L.e("选中了8",isChecked + "");
                index = 7;
                break;
            case R.id.flower9:
                isChecked = false;
                Toast.makeText(this,"未解锁,详情请看说明",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        //通过判断isChecked来判断是增加还是移除
        if (isChecked){
            selectedFlowers.put(index, index);
        }else {
            selectedFlowers.remove(index);
        }
        showSelectedCounts.setText(selectedFlowers.size()+"");
    }

    /**
     * 点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.select_end){//选好了
            //获取中的花的数量
            int flowerCount = SaveKeyValues.getIntValues("flowerCount",0);
            L.e("VALUES","+++++++++++++++++++++++++++");
            //选中花的集合
            ArrayList<Integer> flowers = new ArrayList<>();
            //获取选中的花
            if (selectedFlowers.size() != 0){
                for (int i = 0;i < selectedFlowers.size();i++){
                    int values = (int) selectedFlowers.valueAt(i);
                    flowers.add(values);
                    L.e("VALUES",values+"");
                }
            }
            //跳转到种花的界面
            Intent intent = new Intent(SelectedFlowerActivity.this,PlantActivity.class);
            intent.putIntegerArrayListExtra("flowers_index",flowers);
            intent.putExtra("flowersCount" , flowerCount);
            startActivity(intent);
            finish();
        }
    }
    //************************ 设置 ************************

    /**
     * 全屏
     * @return
     */
    @Override
    public boolean setActivityFullScreen() {
        return true;
    }

    /**
     * 添加标题栏
     * @return
     */
    @Override
    public boolean isAddTitle() {
        return true;
    }

}
