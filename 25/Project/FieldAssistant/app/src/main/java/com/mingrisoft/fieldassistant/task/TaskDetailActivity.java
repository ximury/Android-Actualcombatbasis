package com.mingrisoft.fieldassistant.task;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.android.volley.toolbox.NetworkImageView;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.mingrisoft.fieldassistant.cache.AllLruCacheLoad;
import com.mingrisoft.fieldassistant.home.BaseActivity;
import com.mingrisoft.fieldassistant.home.BaseApplication;
import com.mingrisoft.fieldassistant.R;
import com.mingrisoft.fieldassistant.entity.TaskDetailEntity;

/**
 * Created by Administrator on 2016/6/23 0023.
 */
public class TaskDetailActivity extends BaseActivity implements View.OnClickListener {

    private TextView topLeft, topRight, title, text, location;  //用于设置不同的网络加载的文字
    private NetworkImageView imageView;    //用于加载网络的图片
    private RequestParams params;
    private HttpUtils httpUitil;
    private String brId;
    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_detail_layout);
        params = new RequestParams();
        httpUitil = new HttpUtils();
        init();         //初始化控件
    }

    /*
    * 初始化控件
    * */
    private void init() {
        topLeft = (TextView) findViewById(R.id.task_detail_top_left);
        topRight = (TextView) findViewById(R.id.task_detail_top_right);
        title = (TextView) findViewById(R.id.task_detail_title);
        text = (TextView) findViewById(R.id.task_detail_text);
        location = (TextView) findViewById(R.id.task_detail_location);
        imageView = (NetworkImageView) findViewById(R.id.task_detail_net_image);
        backBtn = (ImageButton) findViewById(R.id.back_im_btn);
        backBtn.setOnClickListener(this);
    }


    /*
    * 通过上一个页面传递过来的id
    *
    * 用来加载详情页时  需要把此id发送给服务器
    * */
    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        brId = intent.getStringExtra("brId");
        getData(brId);
    }



    //从服务器上拉取数据
    private void getData(String msg) {
        String ul = BaseApplication.getUrl() + "tfBusinessReport_view.do";

        params.addBodyParameter("iid", msg);           //把上一个页面传递过来的id发送给服务器
        httpUtilsConnection(ul, params, HttpMethod.POST);
    }

    private void httpUtilsConnection(String url, RequestParams params, HttpMethod method) {
        httpUitil.send(method, url, params, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                //创建一个实体类  把服务器返回来的数据解析出来
                TaskDetailEntity find = new Gson().fromJson(responseInfo.result, TaskDetailEntity.class);

                setData(find);   //设置解析出来的数据
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                // TODO Auto-generated method stub
            }
        });
    }


    /**
     * 把设置出来的数据显示出来
     * */
    private void setData(TaskDetailEntity entity) {
        RequestQueue queue;
        queue = Volley.newRequestQueue(this);
        AllLruCacheLoad cache = new AllLruCacheLoad();
        final ImageLoader imageloader = new ImageLoader(queue, cache);
        topLeft.setText(entity.getBrStatus());
        topRight.setText(entity.getBrData());
        title.setText(entity.getBrName());
        text.setText(entity.getBrContent());
        imageView.setDefaultImageResId(R.mipmap.loading);
        imageView.setErrorImageResId(R.mipmap.loadingqqq);
        imageView.setImageUrl(BaseApplication.getImageUrl() + entity.getBrPhoto(), imageloader);
        location.setText(entity.getBrPlace());

    }

    /**
     * 设置监听事件
     * */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_im_btn:
                finish();
                break;
        }
    }
}
