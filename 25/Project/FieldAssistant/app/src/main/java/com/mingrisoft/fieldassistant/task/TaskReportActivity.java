package com.mingrisoft.fieldassistant.task;

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
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.android.volley.RequestQueue;
import com.google.android.gms.common.api.GoogleApiClient;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.mingrisoft.fieldassistant.home.BaseActivity;
import com.mingrisoft.fieldassistant.home.BaseApplication;
import com.mingrisoft.fieldassistant.R;
import com.mingrisoft.fieldassistant.entity.TaskGetEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/23 0023.
 */
public class TaskReportActivity extends BaseActivity implements View.OnClickListener {

    private Spinner spinner;                                                     //设置下拉的选项框
    private ArrayAdapter<String> arrayAdapter;                               //设置下拉的选项框的适配器
    String[] msg = new String[]{"全部记录", "业务拜访", "订单业绩"};        //设置下拉的选项框里面的内容
    private ListView listView;
    private TaskAdapter adapter;        //设置一个内部的适配器  用来显示解析出来的数据列表
    private TextView addTv;

    private RequestParams params;
    private HttpUtils httpUitil;
    private RequestQueue queue;
    private SharedPreferences sharedPreferences;
    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_report_layout);
        init();       //初始化控件
    }

    /*
    * 初始化空件
    * */
    private void init() {
        spinner = (Spinner) findViewById(R.id.task_sp);
        listView = (ListView) findViewById(R.id.task_list);
        addTv = (TextView) findViewById(R.id.add_task_tv);
        backBtn = (ImageButton) findViewById(R.id.back_im_btn);
        backBtn.setOnClickListener(this);
        params = new RequestParams();
        httpUitil = new HttpUtils();
    }

    @Override
    protected void onStart() {
        super.onStart();
        sharedPreferences = getSharedPreferences("share", Context.MODE_PRIVATE);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, msg);
        spinner.setAdapter(arrayAdapter);
        adapter = new TaskAdapter(this);
        listView.setAdapter(adapter);

        this.spinner.setOnItemSelectedListener(new OnItemSelectedListenerImpl());
        addTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskReportActivity.this, TaskEditeActivity.class);
                startActivity(intent);
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

    class TaskAdapter extends BaseAdapter {
        Context context;
        List<TaskGetEntity.TfBusinessReportBean> data;

        public TaskAdapter(Context context) {
            this.context = context;
        }


        public void addData(List<TaskGetEntity.TfBusinessReportBean> dt) {
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
                convertView = LayoutInflater.from(context).inflate(R.layout.task_itme_layout, null);
                holder.title = (TextView) convertView.findViewById(R.id.task_title);
                holder.type = (TextView) convertView.findViewById(R.id.task_type);
                holder.text = (TextView) convertView.findViewById(R.id.task_text);
                holder.time = (TextView) convertView.findViewById(R.id.task_time);
                holder.rl = (RelativeLayout) convertView.findViewById(R.id.task_item_rl);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.title.setText(data.get(position).getBrName());
            holder.type.setText(data.get(position).getBrStatus());
            holder.text.setText(data.get(position).getBrContent().toString());
            holder.time.setText(data.get(position).getBrData());
            holder.rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(TaskReportActivity.this, TaskDetailActivity.class);
                    intent.putExtra("brId", data.get(position).getBrId());
                    startActivity(intent);
                }
            });

            return convertView;
        }

        class ViewHolder {
            private TextView title, type, text, time;
            private RelativeLayout rl;
        }
    }


    //从服务器上拉取数据
    private void getData(String msg) {
        String ul = BaseApplication.getUrl() + "tfBusinessReport_list.do";

        params.addBodyParameter("id", sharedPreferences.getString("userid", ""));
        if (msg.equals("全部记录")) {
            params.addBodyParameter("brStatus", "");
        } else {
            params.addBodyParameter("brStatus", msg);
        }
        httpUtilsConnection(ul, params, HttpMethod.POST);
    }

    private void httpUtilsConnection(String url, RequestParams params, HttpMethod method) {
        httpUitil.send(method, url, params, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                // TODO Auto-generated method stub
                TaskGetEntity find = new Gson().fromJson(responseInfo.result, TaskGetEntity.class);
                adapter.addData(find.getTfBusinessReport());
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                // TODO Auto-generated method stub
            }
        });
    }

}