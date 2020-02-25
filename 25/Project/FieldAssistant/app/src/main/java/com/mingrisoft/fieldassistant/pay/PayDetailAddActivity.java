package com.mingrisoft.fieldassistant.pay;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.mingrisoft.fieldassistant.R;
import com.mingrisoft.fieldassistant.home.BaseActivity;
import com.mingrisoft.fieldassistant.home.BaseApplication;

/**
 * Created by Administrator on 2016/7/8 0008.
 */
public class PayDetailAddActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout typeRl,themeRl;
    private ImageButton backBtn;
    private Button sendBtn;
    private RadioGroup radioGroup;
    private RadioButton radioBtOne, radioBtTwo, radioBtThree;
    private SharedPreferences sharedPreferences;
    private EditText payNameEt, payMoneyEt, payConnectEt,
            payNameTwoEt, payMoneyTwoEt, payConnectTwoEt,
            payNameThreeEt, payMoneyThreeEt, payConnectThreeEt;
    private RequestParams params;
    private HttpUtils httpUitil;
    private String losId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_add_layout);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        losId = intent.getStringExtra("losid");
    }

    private void init() {
        typeRl = (RelativeLayout) findViewById(R.id.pay_type_rl);
        themeRl = (RelativeLayout) findViewById(R.id.pay_theme_rl);
        typeRl.setVisibility(View.GONE);
        themeRl.setVisibility(View.GONE);
        sharedPreferences = getSharedPreferences("share", Context.MODE_PRIVATE);
        params = new RequestParams();
        httpUitil = new HttpUtils();
        radioGroup = (RadioGroup) findViewById(R.id.send_task_radio_group);
        radioBtOne = (RadioButton) findViewById(R.id.send_task_radio_one);
        radioBtTwo = (RadioButton) findViewById(R.id.send_task_radio_two);
        radioBtThree = (RadioButton) findViewById(R.id.send_task_radio_three);
        backBtn = (ImageButton) findViewById(R.id.back_im_btn);
        sendBtn = (Button) findViewById(R.id.send_task);
        sendBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
        payNameEt = (EditText) findViewById(R.id.send_task_pay_name);
        payMoneyEt = (EditText) findViewById(R.id.send_task_pay_money);
        payConnectEt = (EditText) findViewById(R.id.send_task_pay_connect);
        payNameTwoEt = (EditText) findViewById(R.id.send_task_pay_name_two);
        payMoneyTwoEt = (EditText) findViewById(R.id.send_task_pay_money_two);
        payConnectTwoEt = (EditText) findViewById(R.id.send_task_pay_connect_two);
        payNameThreeEt = (EditText) findViewById(R.id.send_task_pay_name_three);
        payMoneyThreeEt = (EditText) findViewById(R.id.send_task_pay_money_three);
        payConnectThreeEt = (EditText) findViewById(R.id.send_task_pay_connect_three);
    }
    //从服务器上拉取数据
    private void sendData() {
        String payNameOne = payNameEt.getText().toString();
        String payMoneyOne = payMoneyEt.getText().toString();
        String payConnectOne = payConnectEt.getText().toString();
        String payNameTwo = payNameTwoEt.getText().toString();
        String payMoneyTwo = payMoneyTwoEt.getText().toString();
        String payConnectTwo = payConnectTwoEt.getText().toString();
        String payNameThree = payNameThreeEt.getText().toString();
        String payMoneyThree = payMoneyThreeEt.getText().toString();
        String payConnectThree = payConnectThreeEt.getText().toString();
        String followType = "";
        if (radioBtOne.isChecked()) {
            followType = "跟进中";
        }
        if (radioBtTwo.isChecked()) {
            followType = "赢单";
        }
        if (radioBtThree.isChecked()) {
            followType = "订单失败";
        }
        String ul = BaseApplication.getUrl() + "tfLoss_add.do";
        params.addBodyParameter("lid", losId);
        params.addBodyParameter("ldSpare11", payNameOne);
        params.addBodyParameter("ldSpare12", payNameTwo);
        params.addBodyParameter("ldSpare13", payNameThree);
        params.addBodyParameter("ldMoney1", payMoneyOne);
        params.addBodyParameter("ldMoney2", payMoneyTwo);
        params.addBodyParameter("ldMoney3", payMoneyThree);
        params.addBodyParameter("ldRemark1", payConnectOne);
        params.addBodyParameter("ldRemark2", payConnectTwo);
        params.addBodyParameter("ldRemark3", payConnectThree);
        httpUtilsConnection(ul, params, HttpMethod.POST);
    }

    private void httpUtilsConnection(String url, RequestParams params, HttpMethod method) {
        httpUitil.send(method, url, params, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                // TODO Auto-generated method stub
                String result = responseInfo.result.toString().substring(1, 2);
                if (result.equals("t")) {
                    Toast.makeText(PayDetailAddActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(PayDetailAddActivity.this, "提交失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                // TODO Auto-generated method stub
                Toast.makeText(PayDetailAddActivity.this, "提交失败" + msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_task:
                sendData();
                break;
            case R.id.back_im_btn:
                finish();
                break;
        }
    }
}
