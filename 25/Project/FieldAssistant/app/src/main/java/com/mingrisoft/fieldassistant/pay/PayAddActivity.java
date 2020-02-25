package com.mingrisoft.fieldassistant.pay;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.mingrisoft.fieldassistant.R;
import com.mingrisoft.fieldassistant.entity.TaskGetEntity;
import com.mingrisoft.fieldassistant.home.BaseActivity;
import com.mingrisoft.fieldassistant.home.BaseApplication;

/**
 * Created by Administrator on 2016/7/7 0007.
 */
public class PayAddActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton backBtn;
    private Button sendBtn;
    private RadioGroup radioGroup;
    private RadioButton radioBtOne, radioBtTwo, radioBtThree;
    private SharedPreferences sharedPreferences;
    private EditText payNameEt, payMoneyEt, payConnectEt,
            payNameTwoEt, payMoneyTwoEt, payConnectTwoEt,
            payNameThreeEt, payMoneyThreeEt, payConnectThreeEt,
            payThemeEt;
    private RequestParams params;
    private HttpUtils httpUitil;
    private Spinner spinner;
    private ArrayAdapter<String> arrayAdapter;
    private RelativeLayout themeRl;
    String[] msg = new String[]{"业务", "杂费"};
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_add_layout);
        sharedPreferences = getSharedPreferences("share", Context.MODE_PRIVATE);
        params = new RequestParams();
        httpUitil = new HttpUtils();
        init();
    }

    private void init() {
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
        payThemeEt = (EditText) findViewById(R.id.send_task_pay_theme);
        themeRl = (RelativeLayout) findViewById(R.id.pay_theme_rl);

        spinner = (Spinner) findViewById(R.id.pay_send_spinner);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, msg);
        spinner.setAdapter(arrayAdapter);
        this.spinner.setOnItemSelectedListener(new OnItemSelectedListenerImpl());
    }
    //下拉框选择事件
    private class OnItemSelectedListenerImpl implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int position, long id) {
            String msg = parent.getItemAtPosition(position).toString();
            if (msg.equals("杂费")) {
                //如果是 杂费  则隐藏一些组件
                themeRl.setVisibility(View.GONE);
                type = "杂费";
            } else {
                themeRl.setVisibility(View.VISIBLE);
                type = "业务";
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            // TODO Auto-generated method stub
        }
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
        String payTheme = payThemeEt.getText().toString();

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
        String ul = BaseApplication.getUrl() + "tfLoss_new.do";
        params.addBodyParameter("id", sharedPreferences.getString("userid", ""));
        params.addBodyParameter("lossName", payTheme);
        params.addBodyParameter("lossStatus", type);
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
                    Toast.makeText(PayAddActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(PayAddActivity.this, "提交失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                // TODO Auto-generated method stub
                Toast.makeText(PayAddActivity.this, "提交失败" + msg, Toast.LENGTH_SHORT).show();
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
