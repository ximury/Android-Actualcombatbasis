package com.mingrisoft.fieldassistant.follow;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import com.mingrisoft.fieldassistant.cache.AllLruCacheLoad;
import com.mingrisoft.fieldassistant.home.BaseActivity;
import com.mingrisoft.fieldassistant.home.BaseApplication;
import com.mingrisoft.fieldassistant.R;
import com.mingrisoft.fieldassistant.entity.FollowDetailEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/30 0030.
 */
public class FollowDetailActivity extends BaseActivity implements View.OnClickListener {

    private Button followBtn;
    private ImageButton backBtn;
    private RequestParams params;
    private HttpUtils httpUitil;
    private SharedPreferences sharedPreferences;
    private String upId;
    private ListView listView , listFoot;
    private View headView;
    private FollowDetailAdapter adapter;
    private TextView textType,textTheme,textContext;
    private NetworkImageView imageView;
    private FollowFootAdapter followFootAdapter;
    private View footView;
    private String blId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.follow_detail_layout);
        sharedPreferences = getSharedPreferences("share", Context.MODE_PRIVATE);
        init();
    }

    private void init() {
        params = new RequestParams();
        httpUitil = new HttpUtils();
        followBtn = (Button) findViewById(R.id.follow_detail_btn);
        followBtn.setOnClickListener(this);
        backBtn = (ImageButton) findViewById(R.id.back_im_btn);
        backBtn.setOnClickListener(this);
        adapter= new FollowDetailAdapter(this);
        listView = (ListView) findViewById(R.id.follow_detail_list);
        listView.setAdapter(adapter);
        addHeadView();
        addFootView();
        listView.addHeaderView(headView);
        listView.addFooterView(footView);
    }

    private void addFootView() {
        footView = LayoutInflater.from(this).inflate(R.layout.list_view_layout,null);
        listFoot = (ListView) footView.findViewById(R.id.list_view);
        followFootAdapter = new FollowFootAdapter(this);

        listFoot.setAdapter(followFootAdapter);
    }

    private void addHeadView() {
        headView = LayoutInflater.from(this).inflate(R.layout.follow_detail_item_head_layout,null);
        textType = (TextView) headView.findViewById(R.id.follow_detail_type);
        textTheme = (TextView) headView.findViewById(R.id.follow_detail_theme);
        textContext = (TextView) headView.findViewById(R.id.follow_detail_context);
        imageView = (NetworkImageView) headView.findViewById(R.id.follow_detail_image);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        upId = intent.getStringExtra("iid");
        getData(upId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.follow_detail_btn:
                Intent intent  = new Intent(this,FollowEditerActivity.class);
                intent.putExtra("blId",blId);
                startActivity(intent);
                break;
            case R.id.back_im_btn:
                finish();
                break;
        }
    }
    //从服务器上拉取数据
    private void getData(String msg) {
        String ul = BaseApplication.getUrl() + "tfBusinessList_view.do";

        params.addBodyParameter("id", sharedPreferences.getString("userid", ""));
        params.addBodyParameter("id1",msg);

        httpUtilsConnection(ul, params, HttpMethod.POST);
    }

    private void httpUtilsConnection(String url, RequestParams params, HttpMethod method) {
        httpUitil.send(method, url, params, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                // TODO Auto-generated method stub
                FollowDetailEntity find = new Gson().fromJson(responseInfo.result, FollowDetailEntity.class);
                blId = find.getTfBusinessList().getBlId();
                setHeadData(find);
                followFootAdapter.addData(find.getTfCustomer());
                adapter.addData(find);
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                // TODO Auto-generated method stub
            }
        });
    }

    private void setHeadData(FollowDetailEntity entity) {
        RequestQueue queue;
        queue = Volley.newRequestQueue(this);
        AllLruCacheLoad cache = new AllLruCacheLoad();
        final ImageLoader imageloader = new ImageLoader(queue, cache);
        FollowDetailEntity.TfBusinessListBean bean = entity.getTfBusinessList();
        textType.setText(bean.getBlStatus());
        textTheme.setText(bean.getBlName());
        textContext.setText(bean.getBlContent());
        imageView.setDefaultImageResId(R.mipmap.loading);
        imageView.setErrorImageResId(R.mipmap.loadingqqq);
        imageView.setImageUrl(BaseApplication.getImageUrl()+bean.getBlPhoto(),imageloader);
    }

    class FollowFootAdapter extends BaseAdapter {
        Context context;
        List<FollowDetailEntity.TfCustomerBean> data;
        public FollowFootAdapter(Context context) {
            this.context = context;
        }

        public void addData(List<FollowDetailEntity.TfCustomerBean> dt) {
            data = new ArrayList<>();
            this.data = dt;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            if (data != null &&data.size()>0){
                return data.size();
            }else {
                return 0;
            }

        }

        @Override
        public Object getItem(int position) {
            if (data != null &&data.size()>0){
                return data.get(position);
            }else {
                return 0;
            }
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null){
                holder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.follow_detail_foot_layout,null);
                holder.name = (TextView) convertView.findViewById(R.id.follow_detail_foot_name);
                holder.phone = (TextView) convertView.findViewById(R.id.follow_detail_foot_phone);
                holder.firm = (TextView) convertView.findViewById(R.id.follow_detail_foot_firm);
                holder.detail = (TextView) convertView.findViewById(R.id.follow_detail_foot_detail);

                convertView.setTag(holder);
            }else {
                holder = (ViewHolder) convertView.getTag();
            }
            final FollowDetailEntity.TfCustomerBean bean = data.get(position);
            holder.name.setText(bean.getCustomerName());
            holder.phone.setText(bean.getCustomerPhone()+"");
            holder.firm.setText(bean.getCustomerCompany()+"");
            holder.detail.setText(bean.getCustomerRemark()+"");
            return convertView;
        }
        class ViewHolder {
            private TextView name,phone,firm,detail;
        }
    }
}
