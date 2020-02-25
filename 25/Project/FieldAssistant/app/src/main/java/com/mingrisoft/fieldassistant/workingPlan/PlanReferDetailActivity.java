package com.mingrisoft.fieldassistant.workingPlan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.mingrisoft.fieldassistant.home.BaseActivity;
import com.mingrisoft.fieldassistant.home.BaseApplication;
import com.mingrisoft.fieldassistant.R;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.mingrisoft.fieldassistant.entity.PlanDetailEntity;

/**
 * Created by Administrator on 2016/7/5 0005.
 */
public class PlanReferDetailActivity extends BaseActivity implements View.OnClickListener {

    private TextView title, starEndTime, context, time;
    private RequestParams params;
    private HttpUtils httpUitil;
    private SharedPreferences sharedPreferences;
    private String detailId;
    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan_refer_detail_layout);
        sharedPreferences = getSharedPreferences("share", Context.MODE_PRIVATE);
        title = (TextView) findViewById(R.id.plan_detail_title);
        starEndTime = (TextView) findViewById(R.id.plan_detail_sten_time);
        context = (TextView) findViewById(R.id.plan_detail_context);
        time = (TextView) findViewById(R.id.plan_detail_time);
        params = new RequestParams();
        httpUitil = new HttpUtils();
        backBtn = (ImageButton) findViewById(R.id.back_im_btn);
        backBtn.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        detailId = intent.getStringExtra("detailId");
        getData();
    }

    //从服务器上拉取数据
    private void getData() {
        String ul = BaseApplication.getUrl() + "tfJobPlan_view.do";
        params.addBodyParameter("id", sharedPreferences.getString("userid", ""));
        params.addBodyParameter("pid",detailId);
        httpUtilsConnection(ul, params, HttpMethod.POST);
    }

    private void httpUtilsConnection(String url, RequestParams params, HttpMethod method) {
        httpUitil.send(method, url, params, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                // TODO Auto-generated method stub

                PlanDetailEntity find = new Gson().fromJson(responseInfo.result, PlanDetailEntity.class);
                setData(find);
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                // TODO Auto-generated method stub
            }
        });
    }

    private void setData(PlanDetailEntity entity) {
        title.setText(entity.getTfJobPlan().getJpTitle());
        starEndTime.setText(entity.getTfJobPlan().getJpTime1()+"——"+entity.getTfJobPlan().getJpTime2());
        context.setText(entity.getTfJobPlan().getJpRemark());
        time.setText(entity.getTfJobPlan().getJpData()+" / "+entity.getTfJobPlan().getJpTime());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_im_btn:
                finish();
                break;
        }
    }
}
