package com.mingrisoft.fieldassistant.analysis;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.mingrisoft.fieldassistant.R;
import com.mingrisoft.fieldassistant.entity.AnalysisCustomerEntity;
import com.mingrisoft.fieldassistant.entity.RakingEntity;
import com.mingrisoft.fieldassistant.home.BaseActivity;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.mingrisoft.fieldassistant.home.BaseApplication;
import com.mingrisoft.fieldassistant.tool.YearMonthDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/7 0007.
 */
public class RankingAnalysisActivity extends BaseActivity implements View.OnClickListener,YearMonthDialog.Message {

    private ImageButton backBtn;
    private RequestParams params;
    private HttpUtils httpUitil;
    private SharedPreferences sharedPreferences;
    private TextView yearEt, monthEt;
    private Button searchBtn;
    private TextView tvOne, tvTwo, tvThree, tvFour;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.raking_analysis_layout);
        init();//初始化话控件
    }

    /**
     * 初始化控件
     * */
    private void init() {
        sharedPreferences = getSharedPreferences("share", Context.MODE_PRIVATE);
        backBtn = (ImageButton) findViewById(R.id.back_im_btn);
        backBtn.setOnClickListener(this);
        monthEt = (TextView) findViewById(R.id.send_task_back_month);
        yearEt = (TextView) findViewById(R.id.send_task_back_year);
        params = new RequestParams();
        httpUitil = new HttpUtils();
        searchBtn = (Button) findViewById(R.id.search_customer_btn);
        searchBtn.setOnClickListener(this);
        tvOne = (TextView) findViewById(R.id.tv_one);
        tvTwo = (TextView) findViewById(R.id.tv_two);
        tvThree = (TextView) findViewById(R.id.tv_three);
        tvFour = (TextView) findViewById(R.id.tv_four);
        linearLayout = (LinearLayout) findViewById(R.id.set_time_ll);
        linearLayout.setOnClickListener(this);
    }

    /**
     * 在onStart（）的方法里面获取到系统的年月
     * 并获取数据
     *  **/
    @Override
    protected void onStart() {
        super.onStart();
        Date date = new Date();
        int year = date.getYear() + 1900;
        int month = date.getMonth() + 1;

        if (month < 10) {
            monthEt.setText("0" + month);
        } else {
            monthEt.setText(month + "");
        }
        yearEt.setText(year + "");
        getData(); // 获取网络的数据
    }


    /**
     * 点击事件
     * */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_im_btn: // 返回按钮
                finish();
                break;
            case R.id.search_customer_btn:// 检索数据
                getData();
                break;
            case R.id.set_time_ll:      //显示时间选择框
                YearMonthDialog dialog = new YearMonthDialog(this);
                dialog.ShowDia();
                dialog.setMessage(this);
                break;
        }
    }

    //从服务器上拉取数据
    private void getData() {
        String ul = BaseApplication.getUrl() + "count_No3.do";

        params.addBodyParameter("id", sharedPreferences.getString("userid", ""));
        params.addBodyParameter("did", sharedPreferences.getString("did", ""));
        params.addBodyParameter("yearMonth", yearEt.getText().toString() + "-" + monthEt.getText().toString());
        httpUtilsConnection(ul, params, HttpMethod.POST);
    }

    private void httpUtilsConnection(String url, RequestParams params, HttpMethod method) {
        httpUitil.send(method, url, params, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                // TODO Auto-generated method stub

                //解析并显示网络数据
                //由于服务器传递过来的数据类型不同
                //所以解析的方法不同
                List<RakingEntity> listData;
                listData = new ArrayList<RakingEntity>();
                RakingEntity rakingEntity = null;
                try {
                    JSONObject array = new JSONObject(responseInfo.result);
                    JSONArray list2 = array.getJSONArray("list");
                    for (int i = 0; i < list2.length(); i++) {
                        String str = (String) list2.get(i);
                        String[] strArray = str.split(",");
                        rakingEntity = new RakingEntity();
                        rakingEntity.setNum(strArray[0]);
                        rakingEntity.setName(strArray[1]);
                        rakingEntity.setId(strArray[2]);
                        rakingEntity.setMoney(strArray[3]);
                        listData.add(i, rakingEntity);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (listData.size() > 2) {
                    tvTwo.setText("第一名：" + listData.get(0).getName() + "完成订单额" + listData.get(0).getMoney()+"元");
                    tvThree.setText("第二名：" + listData.get(1).getName() + "完成订单额" + listData.get(1).getMoney()+"元");
                    tvFour.setText("第三名：" + listData.get(2).getName() + "完成订单额" + listData.get(2).getMoney()+"元");
                } else if (listData.size() > 1) {
                    tvTwo.setText("第一名：" + listData.get(0).getName() + "完成订单额" + listData.get(0).getMoney()+"元");
                    tvThree.setText("第二名：" + listData.get(1).getName() + "完成订单额" + listData.get(1).getMoney()+"元");
                } else if (listData.size() > 0) {
                    tvTwo.setText("第一名：" + listData.get(0).getName() + "完成订单额" + listData.get(0).getMoney()+"元");
                } else {

                }
                for (int j = 0; j < listData.size(); j++) {
                    if (sharedPreferences.getString("username", "").equals(listData.get(j).getName())) {
                        int s = listData.get(j).getMoney().length();
                        if (s>4){
                            tvOne.setText(listData.get(j).getMoney().substring(0,s-4) + "万元");
                        }else {
                            tvOne.setText(listData.get(j).getMoney() + "元");
                        }
                        break;
                    }
                }
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                // TODO Auto-generated method stub
            }
        });
    }

    @Override
    public void changer(Map<String, String> map) {
        yearEt.setText(map.get("year"));
        monthEt.setText(map.get("month"));
    }
}
