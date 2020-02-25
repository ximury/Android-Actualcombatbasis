package com.mingrisoft.fieldassistant.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.mingrisoft.fieldassistant.home.BaseActivity;
import com.mingrisoft.fieldassistant.home.BaseApplication;
import com.mingrisoft.fieldassistant.R;
import com.mingrisoft.fieldassistant.signed.AddImageActivity;
import com.mingrisoft.fieldassistant.tool.AddTimeDialog;

import java.io.File;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/23 0023.
 */
public class TaskEditeActivity extends BaseActivity implements View.OnClickListener,AddTimeDialog.Message {

    private Spinner spinner;
    private LinearLayout followBackLl;
    private RelativeLayout allMoneyRL, followRL,
            costumerRl,
            costumerNameRl, costumerPhoneRl, costumerFirmRl, costumerLocaRl, costumerNameTwoRl, costumerPhoneTwoRl, costumerFirmTwoRl, costumerLocaTwoRl,
            payRl,
            payNameRl, payMoneyRl, payConnectRl,
            payNameRlTwo, payMoneyRlTwo, payConnectRlTwo,
            payNameRlThree, payMoneyRlThree, payConnectRlThree;
    private EditText themeEt, connectEt, allMoneyEt, followConnectEt,

            costumerNameEt, costumerPhoneEt, costumerFirmEt, costumerLocationEt, costumerConnectEt, costumerNameTwoEt, costumerPhoneTwoEt, costumerFirmTwoEt, costumerLocationTwoEt,
            costumerConnectTwoEt, payNameEt, payMoneyEt, payConnectEt, payNameTwoEt, payMoneyTwoEt, payConnectTwoEt, payNameThreeEt, payMoneyThreeEt, payConnectThreeEt;
    private CheckBox checkBox;
    private ImageView showPhoto, showCamer, followImClose, followImOpen, costumerImClose, costumerImOpen, payImClose, payImOpen;
    private RadioGroup radioGroup;
    private RadioButton radioBtOne, radioBtTwo, radioBtThree;
    private TextView location,yearEt, mouthEt, dayEt, timeEt;
    private Button button;
    String[] msg = new String[]{"业务拜访", "订单业绩"};
    private ArrayAdapter<String> arrayAdapter;
    private SharedPreferences sharedPreferences;

    private RequestParams params;
    private HttpUtils httpUitil;
    private ImageButton backBtn;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("share", Context.MODE_PRIVATE);
        setContentView(R.layout.task_send_layout);
        params = new RequestParams();
        httpUitil = new HttpUtils();
        //控件初始化
        init();
    }

    private void init() {
        spinner = (Spinner) findViewById(R.id.send_task_spinner);
        allMoneyRL = (RelativeLayout) findViewById(R.id.send_task_all_money_rl);
        followRL = (RelativeLayout) findViewById(R.id.send_task_follow_fauther_rl);
        followBackLl = (LinearLayout) findViewById(R.id.send_task_set_back_time);
        followBackLl.setOnClickListener(this);
        costumerRl = (RelativeLayout) findViewById(R.id.send_task_custom_rl);
        costumerNameRl = (RelativeLayout) findViewById(R.id.send_task_custom_name_rl);
        costumerPhoneRl = (RelativeLayout) findViewById(R.id.send_task_custom_phone_rl);
        costumerFirmRl = (RelativeLayout) findViewById(R.id.send_task_custom_firm_rl);
        costumerLocaRl = (RelativeLayout) findViewById(R.id.send_task_custom_location_rl);
        costumerNameTwoRl = (RelativeLayout) findViewById(R.id.send_task_custom_name_two_rl);
        costumerPhoneTwoRl = (RelativeLayout) findViewById(R.id.send_task_custom_phone_two_rl);
        costumerFirmTwoRl = (RelativeLayout) findViewById(R.id.send_task_custom_firm_two_rl);
        costumerLocaTwoRl = (RelativeLayout) findViewById(R.id.send_task_custom_location_two_rl);
        payRl = (RelativeLayout) findViewById(R.id.send_task_pay_rl);
        payNameRl = (RelativeLayout) findViewById(R.id.send_task_pay_name_rl);
        payMoneyRl = (RelativeLayout) findViewById(R.id.send_task_pay_money_rl);
        payConnectRl = (RelativeLayout) findViewById(R.id.send_task_pay_connect_rl);
        payNameRlTwo = (RelativeLayout) findViewById(R.id.send_task_pay_name_two_rl);
        payMoneyRlTwo = (RelativeLayout) findViewById(R.id.send_task_pay_money_two_rl);
        payConnectRlTwo = (RelativeLayout) findViewById(R.id.send_task_pay_connect_two_rl);
        payNameRlThree = (RelativeLayout) findViewById(R.id.send_task_pay_name_three_rl);
        payMoneyRlThree = (RelativeLayout) findViewById(R.id.send_task_pay_money_three_rl);
        payConnectRlThree = (RelativeLayout) findViewById(R.id.send_task_pay_connect_three_rl);
        themeEt = (EditText) findViewById(R.id.send_task_theme);
        connectEt = (EditText) findViewById(R.id.send_task_connect);
        allMoneyEt = (EditText) findViewById(R.id.send_task_money_all);
        followConnectEt = (EditText) findViewById(R.id.send_task_follow_contect);
        yearEt = (TextView) findViewById(R.id.send_task_back_year);
        mouthEt = (TextView) findViewById(R.id.send_task_back_month);
        dayEt = (TextView) findViewById(R.id.send_task_back_day);
        timeEt = (TextView) findViewById(R.id.send_task_back_time);
        costumerNameEt = (EditText) findViewById(R.id.send_task_custom_name);
        costumerPhoneEt = (EditText) findViewById(R.id.send_task_custom_phone);
        costumerFirmEt = (EditText) findViewById(R.id.send_task_custom_firm);
        costumerLocationEt = (EditText) findViewById(R.id.send_task_custom_location);
        costumerConnectEt = (EditText) findViewById(R.id.send_task_custom_connect);
        costumerNameTwoEt = (EditText) findViewById(R.id.send_task_custom_name_two);
        costumerPhoneTwoEt = (EditText) findViewById(R.id.send_task_custom_phone_two);
        costumerFirmTwoEt = (EditText) findViewById(R.id.send_task_custom_firm_two);
        costumerLocationTwoEt = (EditText) findViewById(R.id.send_task_custom_location_two);
        costumerConnectTwoEt = (EditText) findViewById(R.id.send_task_custom_connect_two);
        payNameEt = (EditText) findViewById(R.id.send_task_pay_name);
        payMoneyEt = (EditText) findViewById(R.id.send_task_pay_money);
        payConnectEt = (EditText) findViewById(R.id.send_task_pay_connect);
        payNameTwoEt = (EditText) findViewById(R.id.send_task_pay_name_two);
        payMoneyTwoEt = (EditText) findViewById(R.id.send_task_pay_money_two);
        payConnectTwoEt = (EditText) findViewById(R.id.send_task_pay_connect_two);
        payNameThreeEt = (EditText) findViewById(R.id.send_task_pay_name_three);
        payMoneyThreeEt = (EditText) findViewById(R.id.send_task_pay_money_three);
        payConnectThreeEt = (EditText) findViewById(R.id.send_task_pay_connect_three);
        showPhoto = (ImageView) findViewById(R.id.send_task_image);
        showCamer = (ImageView) findViewById(R.id.send_task_camera);
        followImClose = (ImageView) findViewById(R.id.send_task_follow_no);
        followImOpen = (ImageView) findViewById(R.id.send_task_follow_yes);
        costumerImClose = (ImageView) findViewById(R.id.send_task_custom_no);
        costumerImOpen = (ImageView) findViewById(R.id.send_task_custom_yes);
        payImClose = (ImageView) findViewById(R.id.send_task_pay_no);
        payImOpen = (ImageView) findViewById(R.id.send_task_pay_yes);
        location = (TextView) findViewById(R.id.send_task_location);
        radioGroup = (RadioGroup) findViewById(R.id.send_task_radio_group);
        checkBox = (CheckBox) findViewById(R.id.send_task_back_remind);
        followImClose.setOnClickListener(this);
        followImOpen.setOnClickListener(this);
        costumerImClose.setOnClickListener(this);
        costumerImOpen.setOnClickListener(this);
        payImClose.setOnClickListener(this);
        payImOpen.setOnClickListener(this);
        button = (Button) findViewById(R.id.send_task);
        button.setOnClickListener(this);
        showCamer.setOnClickListener(this);
        radioBtOne = (RadioButton) findViewById(R.id.send_task_radio_one);
        radioBtTwo = (RadioButton) findViewById(R.id.send_task_radio_two);
        radioBtThree = (RadioButton) findViewById(R.id.send_task_radio_three);
        backBtn = (ImageButton) findViewById(R.id.back_im_btn);
        backBtn.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (BaseApplication.getTempFile().getPath() != null) {
            showPhoto.setImageURI(Uri.fromFile(new File(BaseApplication.getTempFile().getPath())));
        }
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, msg);
        spinner.setAdapter(arrayAdapter);
        this.spinner.setOnItemSelectedListener(new OnItemSelectedListenerImpl());
        if (costumerNameEt.getText().toString().trim() == null) {
            costumerNameTwoEt.setEnabled(false);
        }
        costumerNameEt.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                //在这里写一些东西，这里面是每当text改变的时候就会触发
                if (costumerNameEt.getText().toString().trim().length() == 0) {
                    costumerNameTwoEt.setEnabled(false);
                }

            }
        });
        themeEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        location.setText(sharedPreferences.getString("alllocation", "未获取到位置信息"));
        allMoneyEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                    if (allMoneyEt.getText().toString().trim()!= null){
                        radioBtTwo.setChecked(true);
                    }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        showPhoto.setImageURI(null);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        showPhoto.setImageURI(Uri.fromFile(new File(BaseApplication.getTempFile().getPath())));

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.send_task_custom_yes:
                customerVisble();
                costumerImClose.setVisibility(View.VISIBLE);
                costumerImOpen.setVisibility(View.GONE);
                break;
            case R.id.send_task_follow_yes:
                followVisble();
                followImClose.setVisibility(View.VISIBLE);
                followImOpen.setVisibility(View.GONE);
                break;
            case R.id.send_task_pay_yes:
                payVisble();
                payImClose.setVisibility(View.VISIBLE);
                payImOpen.setVisibility(View.GONE);
                break;
            case R.id.send_task_custom_no:
                customerGone();
                costumerImClose.setVisibility(View.GONE);
                costumerImOpen.setVisibility(View.VISIBLE);
                break;
            case R.id.send_task_follow_no:
                followGone();
                followImClose.setVisibility(View.GONE);
                followImOpen.setVisibility(View.VISIBLE);
                break;
            case R.id.send_task_pay_no:
                payGone();
                payImClose.setVisibility(View.GONE);
                payImOpen.setVisibility(View.VISIBLE);
                break;
            case R.id.send_task:
                sendMsg();
                break;
            case R.id.send_task_camera:
                Intent intent = new Intent(this, AddImageActivity.class);
                startActivity(intent);
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

    private void sendMsg() {
        if (type.equals("订单业绩")) {
            String them = themeEt.getText().toString().trim();
            String content = connectEt.getText().toString().trim();
            String money = allMoneyEt.getText().toString().trim();


            String costumerNameOne = costumerNameEt.getText().toString().trim();
            String costumerPhoneOne = costumerPhoneEt.getText().toString().trim();
            String costumerFirmOne = costumerFirmEt.getText().toString().trim();
            String costumerLocationOne = costumerLocationEt.getText().toString().trim();
            String costumerConnectOne = costumerConnectEt.getText().toString().trim();

            String costumerNameTwo = costumerNameTwoEt.getText().toString().trim();
            String costumerPhoneTwo = costumerPhoneTwoEt.getText().toString().trim();
            String costumerFirmTwo = costumerFirmTwoEt.getText().toString().trim();
            String costumerLocationTwo = costumerLocationTwoEt.getText().toString().trim();
            String costumerConnectTwo = costumerConnectTwoEt.getText().toString().trim();


            String payNameOne = payNameEt.getText().toString();
            String payMoneyOne = payMoneyEt.getText().toString();
            String payConnectOne = payConnectEt.getText().toString();
            String payNameTwo = payNameTwoEt.getText().toString();
            String payMoneyTwo = payMoneyTwoEt.getText().toString();
            String payConnectTwo = payConnectTwoEt.getText().toString();
            String payNameThree = payNameThreeEt.getText().toString();
            String payMoneyThree = payMoneyThreeEt.getText().toString();
            String payConnectThree = payConnectThreeEt.getText().toString();


            String followContext = followConnectEt.getText().toString();
            String year = yearEt.getText().toString();
            String month = mouthEt.getText().toString();
            String today = dayEt.getText().toString();
            String hour = timeEt.getText().toString();
            String remind = "";
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


            if (((payNameOne.length() > 0 && payMoneyOne.length() > 0) || (payNameOne.length() == 0 && payMoneyOne.length() == 0))
                    && ((payNameTwo.length() > 0 && payMoneyTwo.length() > 0) || (payNameTwo.length() == 0 && payMoneyTwo.length() == 0))
                    && ((payNameThree.length() > 0 && payMoneyThree.length() > 0) || (payNameThree.length() == 0 && payMoneyThree.length() == 0))) {

                if (followContext.trim().length() > 0  | radioBtOne.isChecked() | radioBtTwo.isChecked() | radioBtThree.isChecked()) {
                    if (checkBox.isChecked()) {
                        remind = "提醒";
                        if (year.trim().length() > 0 && month.trim().length() > 0 && today.trim().length() > 0 && hour.trim().length() > 0) {
                            String url = BaseApplication.getUrl() + "tfBusinessList_on.do";
                            sendTaskTwoMsg(type, them, content, money, location.getText().toString(), costumerNameOne, costumerPhoneOne, costumerFirmOne,
                                    costumerLocationOne, costumerConnectOne, costumerNameTwo, costumerPhoneTwo, costumerFirmTwo, costumerLocationTwo,
                                    costumerConnectTwo, payNameOne, payMoneyOne, payConnectOne, payNameTwo, payMoneyTwo, payConnectTwo, payNameThree,
                                    payMoneyThree, payConnectThree,
                                    followContext, year, month, today, hour, remind, followType
                                    , url);
                        } else {
                            Toast.makeText(this, "请填写正确时间", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        String url = BaseApplication.getUrl() + "tfBusinessList_on.do";
                        sendTaskTwoMsg(type, them, content, money, location.getText().toString(), costumerNameOne, costumerPhoneOne, costumerFirmOne,
                                costumerLocationOne, costumerConnectOne, costumerNameTwo, costumerPhoneTwo, costumerFirmTwo, costumerLocationTwo,
                                costumerConnectTwo, payNameOne, payMoneyOne, payConnectOne, payNameTwo, payMoneyTwo, payConnectTwo, payNameThree,
                                payMoneyThree, payConnectThree,
                                followContext, year, month, today, hour, remind, followType
                                , url);
                    }

                } else {
                    String url = BaseApplication.getUrl() + "tfBusinessReport_add.do";
                    sendTaskMsg(type, them, content, money, location.getText().toString(), costumerNameOne, costumerPhoneOne, costumerFirmOne,
                            costumerLocationOne, costumerConnectOne, costumerNameTwo, costumerPhoneTwo, costumerFirmTwo, costumerLocationTwo,
                            costumerConnectTwo, payNameOne, payMoneyOne, payConnectOne, payNameTwo, payMoneyTwo, payConnectTwo, payNameThree,
                            payMoneyThree, payConnectThree, url
                    );
                }
            } else {
                Toast.makeText(this, "耗费支出输入不合理", Toast.LENGTH_SHORT).show();
            }


        }
        if (type.equals("业务拜访")) {
            String them = themeEt.getText().toString().trim();
            String content = connectEt.getText().toString().trim();
            location.setText(sharedPreferences.getString("alllocation", "未获取到位置信息"));
            upFileUseXUtills(type, them, location.getText().toString(), content);
            BaseApplication.getTempFile().delete();
        }

    }

    private void sendTaskTwoMsg(String type, String them, String content,
                                String money, String location, String costumerNameOne,
                                String costumerPhoneOne, String costumerFirmOne,
                                String costumerLocationOne, String costumerConnectOne,
                                String costumerNameTwo, String costumerPhoneTwo, String costumerFirmTwo,
                                String costumerLocationTwo, String costumerConnectTwo, String payNameOne,
                                String payMoneyOne, String payConnectOne, String payNameTwo, String payMoneyTwo,
                                String payConnectTwo, String payNameThree, String payMoneyThree, String payConnectThree,
                                String followContext, String year, String month, String today, String hour, String remind, String followType,
                                String url) {

        File file = new File(BaseApplication.getTempFile().getPath());
        File fl = new File("/sdcard/myHead.png");
        params.addBodyParameter("filename", fl);

        params.addBodyParameter("id", sharedPreferences.getString("userid", ""));
        params.addBodyParameter("brName", them);
        params.addBodyParameter("brContent", content);
        params.addBodyParameter("brPlace", location);
        params.addBodyParameter("brStatus", type);
        params.addBodyParameter("brMoney", money);
        params.addBodyParameter("customerName2", costumerNameOne);
        params.addBodyParameter("customerPhone2", costumerPhoneOne);
        params.addBodyParameter("customerCompany2", costumerFirmOne);
        params.addBodyParameter("customerAddress2", costumerLocationOne);
        params.addBodyParameter("customerRemark2", costumerConnectOne);
        params.addBodyParameter("customerName2", costumerNameTwo);
        params.addBodyParameter("customerPhone2", costumerPhoneTwo);
        params.addBodyParameter("customerCompany2", costumerFirmTwo);
        params.addBodyParameter("customerAddress2", costumerLocationTwo);
        params.addBodyParameter("customerRemark2", costumerConnectTwo);
        params.addBodyParameter("lossName1", payNameOne);
        params.addBodyParameter("lossMoney1", payMoneyOne);
        params.addBodyParameter("lossSpare1", payConnectOne);
        params.addBodyParameter("lossName2", payNameTwo);
        params.addBodyParameter("lossMoney2", payMoneyTwo);
        params.addBodyParameter("lossSpare2", payConnectTwo);
        params.addBodyParameter("lossName3", payNameThree);
        params.addBodyParameter("lossMoney3", payMoneyThree);
        params.addBodyParameter("lossSpare3", payConnectThree);

        params.addBodyParameter("bldContent", followContext);
        params.addBodyParameter("year", year);
        params.addBodyParameter("month", month);
        params.addBodyParameter("data", today);
        params.addBodyParameter("hour", hour);
        params.addBodyParameter("bldVisit", remind);
        params.addBodyParameter("bldStatus", followType);

        uploadMethod(params, url);
        BaseApplication.getTempFile().delete();

    }

    private void sendTaskMsg(String type, String them, String content,
                             String money, String location, String costumerNameOne,
                             String costumerPhoneOne, String costumerFirmOne,
                             String costumerLocationOne, String costumerConnectOne,
                             String costumerNameTwo, String costumerPhoneTwo, String costumerFirmTwo,
                             String costumerLocationTwo, String costumerConnectTwo, String payNameOne,
                             String payMoneyOne, String payConnectOne, String payNameTwo, String payMoneyTwo,
                             String payConnectTwo, String payNameThree, String payMoneyThree, String payConnectThree
            , String url) {


        File file = new File(BaseApplication.getTempFile().getPath());
        params.addBodyParameter("filename", file);
        params.addBodyParameter("id", sharedPreferences.getString("userid", ""));
        params.addBodyParameter("brName", them);
        params.addBodyParameter("brContent", content);
        params.addBodyParameter("brPlace", location);
        params.addBodyParameter("brStatus", type);
        params.addBodyParameter("brMoney", money);
        params.addBodyParameter("customerName2", costumerNameOne);
        params.addBodyParameter("customerPhone2", costumerPhoneOne);
        params.addBodyParameter("customerCompany2", costumerFirmOne);
        params.addBodyParameter("customerAddress2", costumerLocationOne);
        params.addBodyParameter("customerRemark2", costumerConnectOne);
        params.addBodyParameter("customerName2", costumerNameTwo);
        params.addBodyParameter("customerPhone2", costumerPhoneTwo);
        params.addBodyParameter("customerCompany2", costumerFirmTwo);
        params.addBodyParameter("customerAddress2", costumerLocationTwo);
        params.addBodyParameter("customerRemark2", costumerConnectTwo);
        params.addBodyParameter("lossName1", payNameOne);
        params.addBodyParameter("lossMoney1", payMoneyOne);
        params.addBodyParameter("lossSpare1", payConnectOne);
        params.addBodyParameter("lossName2", payNameTwo);
        params.addBodyParameter("lossMoney2", payMoneyTwo);
        params.addBodyParameter("lossSpare2", payConnectTwo);
        params.addBodyParameter("lossName3", payNameThree);
        params.addBodyParameter("lossMoney3", payMoneyThree);
        params.addBodyParameter("lossSpare3", payConnectThree);
        uploadMethod(params, url);
        BaseApplication.getTempFile().delete();
    }

    @Override
    public void changer(Map<String, String> map) {
        yearEt.setText(map.get("year"));
        mouthEt.setText(map.get("month"));
        dayEt.setText(map.get("day"));
        timeEt.setText(map.get("time"));
    }

    //下拉框选择事件
    private class OnItemSelectedListenerImpl implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int position, long id) {
            String msg = parent.getItemAtPosition(position).toString();
            if (msg.equals("业务拜访")) {
                //如果是 业务拜访  则隐藏一些组件
                goneThey();
                type = "业务拜访";
            } else {
                //若不是则显示一些组件
                visibleThey();
                type = "订单业绩";
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            // TODO Auto-generated method stub
        }
    }

    private void visibleThey() {
        allMoneyRL.setVisibility(View.VISIBLE);
        followRL.setVisibility(View.VISIBLE);
        costumerRl.setVisibility(View.VISIBLE);
        payRl.setVisibility(View.VISIBLE);

    }

    private void goneThey() {
        allMoneyRL.setVisibility(View.GONE);
        followRL.setVisibility(View.GONE);
        costumerRl.setVisibility(View.GONE);
        payRl.setVisibility(View.GONE);
        payGone();
        customerGone();
        followGone();

    }

    private void followGone() {
        followConnectEt.setVisibility(View.GONE);
        radioGroup.setVisibility(View.GONE);
        followBackLl.setVisibility(View.GONE);
        checkBox.setVisibility(View.GONE);
    }

    private void followVisble() {
        followConnectEt.setVisibility(View.VISIBLE);
        radioGroup.setVisibility(View.VISIBLE);
        followBackLl.setVisibility(View.VISIBLE);
        checkBox.setVisibility(View.VISIBLE);
    }

    private void payGone() {
        payNameRl.setVisibility(View.GONE);
        payMoneyRl.setVisibility(View.GONE);
        payConnectRl.setVisibility(View.GONE);
        payNameRlTwo.setVisibility(View.GONE);
        payMoneyRlTwo.setVisibility(View.GONE);
        payConnectRlTwo.setVisibility(View.GONE);
        payNameRlThree.setVisibility(View.GONE);
        payMoneyRlThree.setVisibility(View.GONE);
        payConnectRlThree.setVisibility(View.GONE);
    }

    private void payVisble() {
        payNameRl.setVisibility(View.VISIBLE);
        payMoneyRl.setVisibility(View.VISIBLE);
        payConnectRl.setVisibility(View.VISIBLE);
        payNameRlTwo.setVisibility(View.VISIBLE);
        payMoneyRlTwo.setVisibility(View.VISIBLE);
        payConnectRlTwo.setVisibility(View.VISIBLE);
        payNameRlThree.setVisibility(View.VISIBLE);
        payMoneyRlThree.setVisibility(View.VISIBLE);
        payConnectRlThree.setVisibility(View.VISIBLE);
    }

    private void customerVisble() {
        costumerNameRl.setVisibility(View.VISIBLE);
        costumerPhoneRl.setVisibility(View.VISIBLE);
        costumerFirmRl.setVisibility(View.VISIBLE);
        costumerLocaRl.setVisibility(View.VISIBLE);
        costumerNameTwoRl.setVisibility(View.VISIBLE);
        costumerPhoneTwoRl.setVisibility(View.VISIBLE);
        costumerFirmTwoRl.setVisibility(View.VISIBLE);
        costumerLocaTwoRl.setVisibility(View.VISIBLE);
        costumerConnectEt.setVisibility(View.VISIBLE);
        costumerConnectTwoEt.setVisibility(View.VISIBLE);
    }

    private void customerGone() {
        costumerNameRl.setVisibility(View.GONE);
        costumerPhoneRl.setVisibility(View.GONE);
        costumerFirmRl.setVisibility(View.GONE);
        costumerLocaRl.setVisibility(View.GONE);
        costumerNameTwoRl.setVisibility(View.GONE);
        costumerPhoneTwoRl.setVisibility(View.GONE);
        costumerFirmTwoRl.setVisibility(View.GONE);
        costumerLocaTwoRl.setVisibility(View.GONE);
        costumerConnectEt.setVisibility(View.GONE);
        costumerConnectTwoEt.setVisibility(View.GONE);
    }

    //往服务器上传数据
    private void upFileUseXUtills(String tp, String them, String location, String connect) {
        String url = BaseApplication.getUrl() + "tfBusinessReport_add.do";
        File file = new File(BaseApplication.getTempFile().getPath());
                File fl = new File("/sdcard/myHead.png");
        params.addBodyParameter("filename", fl);
        params.addBodyParameter("id", sharedPreferences.getString("userid", ""));
        params.addBodyParameter("brName", them);
        params.addBodyParameter("brContent", connect);
        params.addBodyParameter("brPlace", location);
        params.addBodyParameter("brStatus", tp);
        uploadMethod(params, url);

    }


    public void uploadMethod(final RequestParams params, final String uploadHost) {
        httpUitil.send(HttpMethod.POST, uploadHost, params, new RequestCallBack<String>() {
            private boolean progressShow = true;
            final ProgressDialog pd = new ProgressDialog(TaskEditeActivity.this);

            @Override
            public void onStart() {
                //上传开始
            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                //上传中

                pd.setCanceledOnTouchOutside(false);
                pd.setOnCancelListener(new DialogInterface.OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface dialog) {
                        progressShow = false;
                    }
                });
                pd.setMessage("正在提交....");
                pd.show();
            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                //上传成功，这里面的返回值，就是服务器返回的数据
                String result = responseInfo.result.toString().substring(1, 2);
                if (result.equals("t")) {
                    pd.dismiss();
                    Toast.makeText(TaskEditeActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    pd.dismiss();
                    Toast.makeText(TaskEditeActivity.this, "提交失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                //上传失败
                pd.dismiss();
                Toast.makeText(TaskEditeActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseApplication.getTempFile().delete();
    }
}
