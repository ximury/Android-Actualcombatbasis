package com.mingrisoft.fieldassistant.follow;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
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
 * Created by Administrator on 2016/6/30 0030.
 */
public class FollowEditerActivity extends BaseActivity implements View.OnClickListener, AddTimeDialog.Message {

    private RequestParams params;
    private HttpUtils httpUitil;
    private SharedPreferences sharedPreferences;
    private String blId;
    private Button button;
    private EditText editContext, editMoney;
    private RadioButton radioButtonOne, radioButtonTwo, radioButtonThree;
    private TextView location, editYear, editMonth, editData, editHour;
    private CheckBox checkBox;
    private LinearLayout linearLayout;
    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.follow_editer_layout);
        sharedPreferences = getSharedPreferences("share", Context.MODE_PRIVATE);
        params = new RequestParams();
        httpUitil = new HttpUtils();
        init();
    }

    private void init() {
        button = (Button) findViewById(R.id.send_btn);
        button.setOnClickListener(this);
        editContext = (EditText) findViewById(R.id.send_follow_connect);
        editMoney = (EditText) findViewById(R.id.send_follow_money_all);
        editYear = (TextView) findViewById(R.id.send_follow_back_year);
        editMonth = (TextView) findViewById(R.id.send_follow_back_month);
        editData = (TextView) findViewById(R.id.send_follow_back_day);
        editHour = (TextView) findViewById(R.id.send_follow_back_time);
        radioButtonOne = (RadioButton) findViewById(R.id.send_follow_radio_one);
        radioButtonThree = (RadioButton) findViewById(R.id.send_follow_radio_three);
        radioButtonTwo = (RadioButton) findViewById(R.id.send_follow_radio_two);
        location = (TextView) findViewById(R.id.send_follow_location);
        checkBox = (CheckBox) findViewById(R.id.send_follow_back_remind);
        location.setText(sharedPreferences.getString("alllocation", "未获取到位置信息"));
        backBtn = (ImageButton) findViewById(R.id.back_im_btn);
        backBtn.setOnClickListener(this);
        linearLayout = (LinearLayout) findViewById(R.id.send_task_set_back_time);
        linearLayout.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        blId = intent.getStringExtra("blId");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_btn:
                sendData();
                break;
            case R.id.back_im_btn:
                finish();
                break;
            case R.id.send_task_set_back_time:
                AddTimeDialog dialog = new AddTimeDialog(this);
                dialog.ShowDia();
                dialog.setMessage(this);
                break;
        }
    }

    //从服务器上拉取数据
    private void sendData() {
        String ul = BaseApplication.getUrl() + "tfBusinessList_add.do";

        String context = editContext.getText().toString().trim();
        String money = editMoney.getText().toString().trim();
        String year = editYear.getText().toString().trim();
        String month = editMonth.getText().toString().trim();
        String data = editData.getText().toString().trim();
        String hour = editHour.getText().toString().trim();
        String status = "";
        String remind = "";
        if (radioButtonOne.isChecked()) {
            status = "跟进中";
        }
        if (radioButtonTwo.isChecked()) {
            status = "赢单";
        }
        if (radioButtonThree.isChecked()) {
            status = "订单失败";
        }
        if (checkBox.isChecked()) {
            remind = "提醒";
        }

        params.addBodyParameter("id", sharedPreferences.getString("userid", ""));
        params.addBodyParameter("blId", blId);
        params.addBodyParameter("year", year);
        params.addBodyParameter("month", month);
        params.addBodyParameter("data", data);
        params.addBodyParameter("hour", hour);
        params.addBodyParameter("bldStatus", status);
        params.addBodyParameter("bldPlace", location.getText().toString());
        params.addBodyParameter("bldContent", context);
        params.addBodyParameter("bldMoney", money);
        params.addBodyParameter("bldVisit", remind);


        httpUtilsConnection(ul, params, HttpMethod.POST);
    }

    private void httpUtilsConnection(String url, RequestParams params, HttpMethod method) {
        httpUitil.send(method, url, params, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                // TODO Auto-generated method stub
                if (responseInfo.result.substring(1, 2).equals("t")) {
                    Toast.makeText(FollowEditerActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(FollowEditerActivity.this, "提交失败", Toast.LENGTH_SHORT).show();
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
        editYear.setText(map.get("year"));
        editMonth.setText(map.get("month"));
        editData.setText(map.get("day"));
        editHour.setText(map.get("time"));
    }
}
