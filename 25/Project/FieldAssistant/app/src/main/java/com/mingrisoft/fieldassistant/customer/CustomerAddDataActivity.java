package com.mingrisoft.fieldassistant.customer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
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

import java.io.File;

/**
 * Created by Administrator on 2016/6/30 0030.
 */
public class CustomerAddDataActivity extends BaseActivity implements View.OnClickListener {

    private Button saveBtn;
    private EditText nameEt, childAgeEt, birthdayEt, phoneEt, callPhoneEt, likeEt, firmEt, typeEt, jobEt, detailEt;
    private TextView location;
    private CheckBox man, woman, boy, girl, wedding, remind;
    private RequestParams params;
    private HttpUtils httpUitil;
    private SharedPreferences sharedPreferences;
    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_add_data_layout);
        sharedPreferences = getSharedPreferences("share", Context.MODE_PRIVATE);
        params = new RequestParams();
        httpUitil = new HttpUtils();
        init();
    }

    private void init() {
        saveBtn = (Button) findViewById(R.id.send_customer_save);
        saveBtn.setOnClickListener(this);
        nameEt = (EditText) findViewById(R.id.send_customer_name);
        childAgeEt = (EditText) findViewById(R.id.send_customer_child_age);
        birthdayEt = (EditText) findViewById(R.id.send_customer_birthday);
        phoneEt = (EditText) findViewById(R.id.send_customer_phone);
        callPhoneEt = (EditText) findViewById(R.id.send_customer_call_phone);
        likeEt = (EditText) findViewById(R.id.send_customer_like);
        firmEt = (EditText) findViewById(R.id.send_customer_firm);
        typeEt = (EditText) findViewById(R.id.send_customer_type);
        jobEt = (EditText) findViewById(R.id.send_customer_job);
        detailEt = (EditText) findViewById(R.id.send_customer_connect_detail);
        location = (TextView) findViewById(R.id.send_customer_location);
        man = (CheckBox) findViewById(R.id.send_customer_sex_man);
        woman = (CheckBox) findViewById(R.id.send_customer_sex_woman);
        boy = (CheckBox) findViewById(R.id.send_customer_child_boy);
        girl = (CheckBox) findViewById(R.id.send_customer_child_girl);
        wedding = (CheckBox) findViewById(R.id.send_customer_weding);
        remind = (CheckBox) findViewById(R.id.send_customer_birthday_remind);
        backBtn = (ImageButton) findViewById(R.id.back_im_btn);
        backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_customer_save:
                saveData();
                break;
            case R.id.back_im_btn:
                Intent intent = new Intent(CustomerAddDataActivity.this,CustomerAllActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    //从服务器上拉取数据
    private void saveData() {
        String name = nameEt.getText().toString().trim();
        String childAge = childAgeEt.getText().toString().trim();
        String birthday = birthdayEt.getText().toString().trim();
        String phone = phoneEt.getText().toString().trim();
        String callPhone = callPhoneEt.getText().toString().trim();
        String like = likeEt.getText().toString().trim();
        String firm = firmEt.getText().toString().trim();
        String type = typeEt.getText().toString().trim();
        String job = jobEt.getText().toString().trim();
        String detail = detailEt.getText().toString().trim();
        location.setText(sharedPreferences.getString("alllocation", "未获取到位置信息"));
        String sex = "";
        String wed = "";
        String birthRemind = "";
        if (man.isChecked()){
            sex = "男";
        }
        if (woman.isChecked()){
            sex = "女";
        }
        if (wedding.isChecked()){
            wed = "已婚";
        }
        if (remind.isChecked()){
            birthRemind = "是";
        }
        if (boy.isChecked()){

        }
        if (girl.isChecked()){

        }

        String ul = BaseApplication.getUrl() + "tfCustomer_save.do";
        params.addBodyParameter("id", sharedPreferences.getString("userid", ""));
        File file = new File(BaseApplication.getTempFile().getPath());
        File fl = new File("/sdcard/myHead.png");
        params.addBodyParameter("filename", fl);
        params.addBodyParameter("customerName", name);
        params.addBodyParameter("photo", "y");
        params.addBodyParameter("customerBirthday", birthday);
        params.addBodyParameter("customerMobile", phone);
        params.addBodyParameter("customerPhone", callPhone);
        params.addBodyParameter("customerLike", like);
        params.addBodyParameter("customerCompany", firm);
        params.addBodyParameter("customerStatus", type);
        params.addBodyParameter("customerJob", job);
        params.addBodyParameter("customerRemark", detail);
        params.addBodyParameter("customerAddress", location.getText().toString());
        params.addBodyParameter("customerSex", sex);
        params.addBodyParameter("customerMarry", wed);
        params.addBodyParameter("customerBir", birthRemind);
        params.addBodyParameter("customerBirdate", "1");
        params.addBodyParameter("customerSpare3", childAge);
        httpUtilsConnection(ul, params, HttpMethod.POST);
    }

    private void httpUtilsConnection(String url, RequestParams params, HttpMethod method) {
        httpUitil.send(method, url, params, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                // TODO Auto-generated method stub
                if (responseInfo.result.substring(1, 2).equals("t")) {
                    Toast.makeText(CustomerAddDataActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CustomerAddDataActivity.this,CustomerAllActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(CustomerAddDataActivity.this, "保存失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                // TODO Auto-generated method stub
            }
        });
    }
}
