package com.mingrisoft.fieldassistant.workingPlan;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.mingrisoft.fieldassistant.home.BaseActivity;
import com.mingrisoft.fieldassistant.home.BaseApplication;
import com.mingrisoft.fieldassistant.R;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.mingrisoft.fieldassistant.tool.AddTimeDialog;

import java.util.Map;


/**
 * Created by Administrator on 2016/7/5 0005.
 */
public class PlanReferSendActivity extends BaseActivity implements View.OnClickListener, AddTimeDialog.Message {

    private EditText themeEt, contextEt;
    private Button sendBtn;
    private TextView yearStarEt, monthStarEt, dataStarEt, yearEndEt, monthEndEt, dataEndEt,hourStart,hourEnd;
    private RequestParams params;
    private HttpUtils httpUitil;
    private SharedPreferences sharedPreferences;
    private ImageButton backBtn;
    private LinearLayout startTime, endTime;
    AddTimeDialog addTimeDialog = new AddTimeDialog(this);
    private String type = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan_send_layout);
        sharedPreferences = getSharedPreferences("share", Context.MODE_PRIVATE);
        params = new RequestParams();
        httpUitil = new HttpUtils();
        init();
    }

    private void init() {
        themeEt = (EditText) findViewById(R.id.send_plan_theme);
        yearStarEt = (TextView) findViewById(R.id.send_plan_back_year);
        monthStarEt = (TextView) findViewById(R.id.send_plan_back_month);
        dataStarEt = (TextView) findViewById(R.id.send_plan_back_day);
        yearEndEt = (TextView) findViewById(R.id.send_plan_end_year);
        monthEndEt = (TextView) findViewById(R.id.send_plan_end_month);
        dataEndEt = (TextView) findViewById(R.id.send_plan_end_data);
        contextEt = (EditText) findViewById(R.id.send_plan_context);
        sendBtn = (Button) findViewById(R.id.send_plan_btn);
        sendBtn.setOnClickListener(this);
        backBtn = (ImageButton) findViewById(R.id.back_im_btn);
        backBtn.setOnClickListener(this);
        startTime = (LinearLayout) findViewById(R.id.send_task_set_back_time_start);
        endTime = (LinearLayout) findViewById(R.id.send_task_set_back_time_end);
        startTime.setOnClickListener(this);
        endTime.setOnClickListener(this);
        hourStart = (TextView) findViewById(R.id.send_follow_back_time_start);
        hourEnd = (TextView) findViewById(R.id.send_follow_back_time_end);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_plan_btn:
                sendData();
                break;
            case R.id.back_im_btn:
                finish();
                break;
            case R.id.send_task_set_back_time_start:
                type = "start";
                addTimeDialog.ShowDia();
                addTimeDialog.setMessage(this);
                break;
            case R.id.send_task_set_back_time_end:
                type = "end";
                addTimeDialog.ShowDia();
                addTimeDialog.setMessage(this);
                break;
        }
    }

    //从服务器上拉取数据
    private void sendData() {
        String ul = BaseApplication.getUrl() + "tfJobPlan_add.do";

        String theme = themeEt.getText().toString().trim();
        String yearStar = yearStarEt.getText().toString().trim();
        String monthStar = monthStarEt.getText().toString().trim();
        String dataStar = dataStarEt.getText().toString().trim();
        String yearEnd = yearEndEt.getText().toString().trim();
        String monthEnd = monthEndEt.getText().toString().trim();
        String dataEnd = dataEndEt.getText().toString().trim();
        String context = contextEt.getText().toString().trim();


        params.addBodyParameter("id", sharedPreferences.getString("userid", ""));
        params.addBodyParameter("jpTitle", theme);
        params.addBodyParameter("jpTime1", yearStar + "-" + monthStar + "-" + dataStar);
        params.addBodyParameter("jpTime2", yearEnd + "-" + monthEnd + "-" + dataEnd);
        params.addBodyParameter("jpRemark", context);

        httpUtilsConnection(ul, params, HttpMethod.POST);
    }

    private void httpUtilsConnection(String url, RequestParams params, HttpMethod method) {
        httpUitil.send(method, url, params, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                // TODO Auto-generated method stub
                if (responseInfo.result.substring(1, 2).equals("t")) {
                    Toast.makeText(PlanReferSendActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(PlanReferSendActivity.this, "提交失败", Toast.LENGTH_SHORT).show();
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

        if (type.equals("start")) {
            yearStarEt.setText(map.get("year"));
            monthStarEt.setText(map.get("month"));
            dataStarEt.setText(map.get("day"));
            hourStart.setText(map.get("time"));
        } else {
            yearEndEt.setText(map.get("year"));
            monthEndEt.setText(map.get("month"));
            dataEndEt.setText(map.get("day"));
            hourEnd.setText(map.get("time"));
        }
    }
}
