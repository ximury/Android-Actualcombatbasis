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
import com.mingrisoft.fieldassistant.home.BaseActivity;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.mingrisoft.fieldassistant.home.BaseApplication;
import com.mingrisoft.fieldassistant.tool.YearMonthDialog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * Created by Administrator on 2016/7/7 0007.
 */
public class OrderAnalysisActivity extends BaseActivity implements View.OnClickListener,YearMonthDialog.Message {
    private ImageButton backBtn;
    private PieChartView chart;            //绘制饼状图的工具类
    private PieChartData data;              //为饼状图添加数据
    private RequestParams params;
    private HttpUtils httpUitil;
    private SharedPreferences sharedPreferences;
    private TextView yearEt, monthEt;
    private Button searchBtn;
    private TextView tvOne,tvTwo,tvThree,tvFour,tvFive;     //显示网络加载的的数据
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_analysis_layout);

        //初始化控件
        init();
    }
    /**
     *  初始化控件的方法
     * */
    private void init() {
        sharedPreferences = getSharedPreferences("share", Context.MODE_PRIVATE);
        backBtn = (ImageButton) findViewById(R.id.back_im_btn);
        backBtn.setOnClickListener(this);
        chart= (PieChartView) findViewById(R.id.order_pie_show);
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
        tvFive = (TextView) findViewById(R.id.tv_five);
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
        getData(); //获取数据
    }

    /**
     * 点击事件
     * */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_im_btn: //返回按钮
                finish();
                break;
            case R.id.search_customer_btn:  //获取数据  检索
                getData();
                break;
            case R.id.set_time_ll:      //显示时间的选择框
                YearMonthDialog dialog = new YearMonthDialog(this);
                dialog.ShowDia();
                dialog.setMessage(this);
                break;
        }
    }


    /*从服务器上拉取数据*/
    private void getData() {
        String ul = BaseApplication.getUrl() + "count_No2.do";
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
                AnalysisCustomerEntity find = new Gson().fromJson(responseInfo.result, AnalysisCustomerEntity.class);
                tvOne.setText(find.getNum1() + "单");
                tvTwo.setText(find.getNum2() + "单");
                tvThree.setText(find.getNum3() + "单");
                tvFour.setText(find.getNum4() + "单");
                tvFive.setText(find.getNum5() + "%");
                generateData(find);//把数据传递给索要显示的饼状图
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                // TODO Auto-generated method stub
            }
        });
    }

    /**
     * 绘制显示饼状图的方法
     * */
    private void generateData(AnalysisCustomerEntity find) {
        int numValues = 4;
        int one = Integer.parseInt(find.getNum1());
        int two = Integer.parseInt(find.getNum2());
        int three = Integer.parseInt(find.getNum3());
        int four = Integer.parseInt(find.getNum4());
        int [] num ={one,two,three,four};
        List<SliceValue> values = new ArrayList<SliceValue>();
        String [] name = {"拜访单","赢单","失败单","进行单"};
        for (int i = 0; i < numValues; ++i) {
            SliceValue sliceValue = new SliceValue(num[i], ChartUtils.pickColor());
            sliceValue.setLabel(name[i]);
            values.add(sliceValue);
        }
        data = new PieChartData(values);
        data.setHasLabels(true);
        chart.setPieChartData(data);

    }

    /**
     * 时间选择框的接口回调方法
     * 用于刷新现实的时间
     * */
    @Override
    public void changer(Map<String, String> map) {
        yearEt.setText(map.get("year"));
        monthEt.setText(map.get("month"));
    }
}
