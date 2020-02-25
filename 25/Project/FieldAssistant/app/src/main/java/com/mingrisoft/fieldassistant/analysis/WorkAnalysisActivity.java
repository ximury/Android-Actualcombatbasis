package com.mingrisoft.fieldassistant.analysis;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.mingrisoft.fieldassistant.R;
import com.mingrisoft.fieldassistant.entity.AnalysisAllEntity;
import com.mingrisoft.fieldassistant.entity.PayAllEntity;
import com.mingrisoft.fieldassistant.home.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.mingrisoft.fieldassistant.home.BaseApplication;

/**
 * Created by Administrator on 2016/7/7 0007.
 */
public class WorkAnalysisActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton backBtn;                             //定义返回按钮
    private TextView textCustomer,textOrder,textRanking;        //加载客户分析，订单分析图，琅琊榜的数据
    private ImageView imCustomer,imOrder,imRanking;           //加载客户分析，订单分析图，琅琊榜的图片
    private LineChartView chart;                              //显示出来线形图
    private LineChartData data;                            //用于线形图的数据加载
    private RequestParams params;                           //加载网络数据
    private HttpUtils httpUitil;                            //加载网络数据
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work_analysis_layout);
        init();  // 初始化控件
    }

    /**
     * 初始化控件
     * */
    private void init() {
        sharedPreferences = getSharedPreferences("share", Context.MODE_PRIVATE);
        chart = (LineChartView) findViewById(R.id.all_analysis_line);
        backBtn = (ImageButton) findViewById(R.id.back_im_btn);
        backBtn.setOnClickListener(this);
        textCustomer = (TextView) findViewById(R.id.analysis_customer);
        textOrder = (TextView) findViewById(R.id.analysis_order);
        textRanking = (TextView) findViewById(R.id.analysis_ranking);
        imCustomer = (ImageView) findViewById(R.id.img_one);
        imOrder = (ImageView) findViewById(R.id.img_two);
        imRanking = (ImageView) findViewById(R.id.img_three);
        imCustomer.setOnClickListener(this);
        imOrder.setOnClickListener(this);
        imRanking.setOnClickListener(this);
        backBtn.setOnClickListener(this);
        params = new RequestParams();
        httpUitil = new HttpUtils();
        getData();  // 获取网络数据

    }


    /**
     * 点击事件
     * */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_im_btn:  // 返回上一个页面
                finish();
                break;
            case R.id.img_one:      //跳转到客户分析页面
                starOtherActivity(CustomerAnalysisActivity.class);
                break;
            case R.id.img_two:          //跳转到订单分析页面
                starOtherActivity(OrderAnalysisActivity.class);
                break;
            case R.id.img_three:            //  跳转到琅琊榜页面
                starOtherActivity(RankingAnalysisActivity.class);
                break;
        }
    }

    /**
     * 执行该方法  完成页面间的通信
     * */
    private void starOtherActivity(Class  cls) {
        Intent intent = new Intent(WorkAnalysisActivity.this, cls);
        startActivity(intent);
    }


    //从服务器上拉取数据
    private void getData() {
        String ul = BaseApplication.getUrl() + "count_on.do";
        params.addBodyParameter("id", sharedPreferences.getString("userid", ""));
        params.addBodyParameter("did", sharedPreferences.getString("did", ""));
        httpUtilsConnection(ul, params, HttpMethod.POST);
    }

    private void httpUtilsConnection(String url, RequestParams params, HttpMethod method) {
        httpUitil.send(method, url, params, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                // TODO Auto-generated method stub

                //解析并显示数据
                AnalysisAllEntity find = new Gson().fromJson(responseInfo.result, AnalysisAllEntity.class);
                textCustomer.setText("有效"+find.getLeft()+"人");
                textOrder.setText("成单"+find.getMid()+"%");
                textRanking.setText("业绩第"+find.getRight()+"名");
                getDateTest(find);  //将服务器获取到的数据传给绘制线形图的方法里
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                // TODO Auto-generated method stub
            }
        });
    }


    /**
     *   此方法时绘制线形图的犯法
     * */
    private void getDateTest(AnalysisAllEntity entity) {

        int one = Integer.parseInt(entity.getBusAll());
        int two = Integer.parseInt(entity.getViAll());
        int three = Integer.parseInt(entity.getCusAll());
        int [] points = {one,two,three};
        String [] name={"业绩","拜访量","客户量"};
        List<AxisValue> axisValues = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            AxisValue axisValue = new AxisValue(i);
            axisValue.setLabel(name[i]);
            axisValues.add(axisValue);
        }
        Axis axisx = new Axis();
        Axis axisy = new Axis();
        axisx.setTextColor(Color.BLACK)
                .setName("")
                .setValues(axisValues);
        axisy.setTextColor(Color.BLACK)
                .setName("数量")
                .setHasLines(true)
                .setMaxLabelChars(5);
        List<PointValue> values = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            values.add(new PointValue(i, points[i]));
        }
        List<Line> lines = new ArrayList<>();       //绘制的第一条线
        Line line = new Line(values)
                .setColor(Color.parseColor("#4592F3"))
                .setCubic(false)
                .setHasPoints(false);
        line.setHasLines(true);
        line.setHasLabels(true);
        line.setHasPoints(true);
        List<PointValue> values2 = new ArrayList<>();
        int oneP = Integer.parseInt(entity.getBusPer());
        int twoP = Integer.parseInt(entity.getViPer());
        int threeP = Integer.parseInt(entity.getCusPer());
        int [] pointsP = {oneP,twoP,threeP};
        for (int i = 0; i < 3; i++) {
            values2.add(new PointValue(i, pointsP[i]));
        }
        Line line2 = new Line(values2)              //绘制的第二条线
                .setColor(Color.parseColor("#ff0000"))
                .setCubic(false)
                .setHasPoints(false);
        line2.setHasLines(true);
        line2.setHasLabels(true);
        line2.setHasPoints(true);
        lines.add(line2);
        lines.add(line);
        data = new LineChartData();
        data.setLines(lines);
        data.setAxisYLeft(axisy);
        data.setAxisXBottom(axisx);
        chart.setLineChartData(data);
    }
}
