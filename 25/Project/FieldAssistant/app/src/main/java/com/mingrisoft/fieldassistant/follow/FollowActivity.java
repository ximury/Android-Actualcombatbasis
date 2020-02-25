package com.mingrisoft.fieldassistant.follow;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
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
import com.mingrisoft.fieldassistant.entity.FollowAllEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/29 0029.
 */
public class FollowActivity extends BaseActivity implements View.OnClickListener {

    private Spinner spinner;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private FollowAdapter adapter;
    String[] msg = new String[]{"跟进中", "订单完成"};
    private RequestParams params;
    private HttpUtils httpUitil;
    private SharedPreferences sharedPreferences;
    private ImageButton backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.documentary_layout);
        sharedPreferences = getSharedPreferences("share", Context.MODE_PRIVATE);
        spinner = (Spinner) findViewById(R.id.follow_spinner);
        listView = (ListView) findViewById(R.id.follow_list);
        backbtn = (ImageButton) findViewById(R.id.back_im_btn);
        backbtn.setOnClickListener(this);
        adapter = new FollowAdapter(this);
        listView.setAdapter(adapter);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, msg);
        spinner.setAdapter(arrayAdapter);
        this.spinner.setOnItemSelectedListener(new OnItemSelectedListenerImpl());
        params = new RequestParams();
        httpUitil = new HttpUtils();
    }

    //从服务器上拉取数据
    private void getData(String msg) {
        String ul = BaseApplication.getUrl() + "tfBusinessList_list.do";
        params.addBodyParameter("id", sharedPreferences.getString("userid", ""));
        params.addBodyParameter("blStatus", msg);
        httpUtilsConnection(ul, params, HttpMethod.POST);
    }

    private void httpUtilsConnection(String url, RequestParams params, HttpMethod method) {
        httpUitil.send(method, url, params, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                // TODO Auto-generated method stub
                FollowAllEntity find = new Gson().fromJson(responseInfo.result, FollowAllEntity.class);
                adapter.addData(find.getTfBusinessList());
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                // TODO Auto-generated method stub
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_im_btn:
                finish();
                break;
        }
    }

    //下拉框选择事件
    private class OnItemSelectedListenerImpl implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int position, long id) {
            String msg = parent.getItemAtPosition(position).toString();
            getData(msg);

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            // TODO Auto-generated method stub
        }
    }

    class FollowAdapter extends BaseAdapter {
        Context context;
        List<FollowAllEntity.TfBusinessListBean> data;

        public FollowAdapter(Context context) {
            this.context = context;
        }

        public void addData(List<FollowAllEntity.TfBusinessListBean> dt) {
            data = new ArrayList<>();
            this.data = dt;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            if (data != null && data.size() > 0) {
                return data.size();
            } else {
                return 0;
            }

        }

        @Override
        public Object getItem(int position) {
            if (data != null && data.size() > 0) {
                return data.get(position);
            } else {
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
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.follow_item_layout, null);
                holder.name = (TextView) convertView.findViewById(R.id.follow_item_name);
                holder.type = (TextView) convertView.findViewById(R.id.follow_item_result);
                holder.text = (TextView) convertView.findViewById(R.id.follow_item_connect);
                holder.time = (TextView) convertView.findViewById(R.id.follow_item_time);
                holder.rl = (RelativeLayout) convertView.findViewById(R.id.follow_item);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            final FollowAllEntity.TfBusinessListBean bean = data.get(position);
            holder.name.setText(bean.getBlName());
            holder.type.setText(bean.getBlStatus());
            holder.text.setText(bean.getBlContent());
            holder.time.setText(bean.getBlTime());
            holder.rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(FollowActivity.this, FollowDetailActivity.class);
                    intent.putExtra("iid", bean.getBlId() + "");
                    startActivity(intent);

                }
            });

            return convertView;
        }

        class ViewHolder {
            private TextView name, type, text, time;
            private RelativeLayout rl;
        }
    }
}
