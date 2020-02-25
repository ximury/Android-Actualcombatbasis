package mrkj.flowersdemo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;

import mrkj.flowersdemo.R;
import mrkj.flowersdemo.db.DatasDao;
import mrkj.flowersdemo.db.MyDataUtils;
import mrkj.flowersdemo.ui.adapter.SelectorShareFlowerAdapter;
import mrkj.flowersdemo.ui.base.BaseActivity;
import mrkj.flowersdemo.ui.bean.Flower;
import mrkj.flowersdemo.ui.utils.L;

/**
 * 对数据库操作(主要功能，对数据查询和更改)
 */
public class SelectorShareFlowerActivity extends BaseActivity implements SelectorShareFlowerAdapter.SelectShareFlowerCallBack ,View.OnClickListener{

    private DatasDao datasDao;//数据库操作类
    private ArrayList<Flower> flower_list;//用户种过的花的集合
    private GridView show_flower;//显示数据
    private SelectorShareFlowerAdapter adapter;//适配器
    private TextView hint_text;//提示文字
    private Button comfirm;//确定
    private boolean isComfirm;//手否确定
    /**
     * 设置布局
     * @return
     */
    @Override
    protected int setChildContentView() {
        return R.layout.activity_selector_share_flower;
    }

    /**
     * 初始化当前界面
     */
    @Override
    protected void onCreateChild() {
        //设置标题栏背景颜色
        setTitleBackgroundColor(Color.parseColor("#eeffffff"));
        //设置标题栏
        setTitle("选择想要分享的花",getResources().getColor(R.color.left_text_color),R.mipmap.mrkj_share_back,true);
        //初始化数据
        initData();
        //初始化控件
        show_flower = (GridView) findViewById(R.id.flower_data_list);
        hint_text = (TextView) findViewById(R.id.hint);
        comfirm = (Button) findViewById(R.id.confirm);
        comfirm.setOnClickListener(this);
        //构建适配器
        adapter = new SelectorShareFlowerAdapter(this,flower_list);
        adapter.setCloseKyeBoardListener(this);//设置监听
        //绑定适配器
        show_flower.setAdapter(adapter);
    }
    /**
     * 点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.confirm://确定
                ArrayList<Flower> changeList = adapter.getSelectValues();
                if (changeList == null){
                    hint_text.setText("请选择");
                    Toast.makeText(this,"请选择花朵！",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (isComfirm){
                    //获取选择的数据
                    StringBuffer buffer = new StringBuffer();//用于拼接字符串
                    int count = 0;//总数量
                    for (int i = 0;i < changeList.size(); i++){
                        Flower flower = changeList.get(i);
                        L.e("选择",flower.toString());
                        int select_count = changeList.get(i).getCount();
                        count+= select_count;//花的总数
                        //显示的花的索引值
                        for (int j = 0;j < select_count;j++){
                            buffer.append(flower.getIndex()).append(",");
                        }
                        //设置更改后的数据
                        flower.setCount(
                                flower_list.get(flower.getIndex()).getCount() - flower.getCount());
                    }
                    L.e("分享的总数量","【"+ count +"】");
                    L.e("分享的花",buffer.toString());
                    String select_message = JSON.toJSONString(changeList);
                    L.e("JSON数据",select_message);
                    //返回
                    Intent intent = new Intent();
                    intent.putExtra("share_flower_counts",count);
                    intent.putExtra("show_share_flower",buffer.toString().substring(0,buffer.toString().length() - 1));
                    intent.putExtra("share_flower_data",select_message);
                    setResult(RESULT_OK,intent);
                    finish();
                    return;
                }
                Toast.makeText(this,"请确认后再点击确定！",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
    /**
     * 初始化数据
     */
    private void initData() {
        datasDao = new DatasDao(this);//实例化
        flower_list = MyDataUtils.getFlowerData(datasDao);//获取数据源
        for (Flower flower:flower_list){
            L.e("花", flower.toString());
        }
    }

    /**
     * 隐藏输入键盘
     */
    private void  hideKeyBoard(){
        ((InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        datasDao.close();
    }

    @Override
    public void closeKeyBoard() {
        hideKeyBoard();
    }

    @Override
    public void selectValuesIsRightful(boolean rightful) {
        L.e("所选的值是否有效",rightful + "");
        hint_text.setText(rightful == true ? "选择值有效":"选择值无效");
        isComfirm = rightful;
    }

}
