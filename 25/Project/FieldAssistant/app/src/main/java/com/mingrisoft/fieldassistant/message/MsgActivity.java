package com.mingrisoft.fieldassistant.message;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
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
import com.mingrisoft.fieldassistant.entity.TaskGetEntity;

import java.util.ArrayList;
import java.util.List;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
/**
 * Created by Administrator on 2016/7/5 0005.
 */
public class MsgActivity extends BaseActivity {


    private RequestParams params;
    private HttpUtils httpUitil;
    private SharedPreferences sharedPreferences;
    private ImageButton backBtn;
    private ListView listView;
    private MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mag_activity_layout);
        sharedPreferences = getSharedPreferences("share", Context.MODE_PRIVATE);
        params = new RequestParams();
        httpUitil = new HttpUtils();
        listView = (ListView) findViewById(R.id.msg_list);
        adapter = new MsgAdapter(this);
        listView.setAdapter(adapter);
        backBtn = (ImageButton) findViewById(R.id.back_im_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    class MsgAdapter extends BaseAdapter {
        Context context;
        List<String> data;

        public MsgAdapter(Context context) {
            this.context = context;
            addData();
        }

        private void addData() {
            data = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                data.add(i,i+"");
            }
        }


//        public void addData(List<TaskGetEntity.TfBusinessReportBean> dt) {
//            data = new ArrayList<>();
//            this.data = dt;
//            notifyDataSetChanged();
//        }

        @Override
        public int getCount() {
//                     if (data != null && data.size() > 0) {
//                return data.size();
//            } else {
//                return 0;
//            }
            return 1;
        }

        @Override
        public Object getItem(int position) {
//            if (data != null && data.size() > 0) {
//                return data.get(position);
//            } else {
//                return 0;
//            }
            return 1;
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
                convertView = LayoutInflater.from(context).inflate(R.layout.msg_itme_layout, null);
                holder.ll = (LinearLayout) convertView.findViewById(R.id.mag_detail_ll);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
                holder.ll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MsgActivity.this, MsgDetailActivity.class);
                    startActivity(intent);
                }
            });

            return convertView;
        }

        class ViewHolder {
            private TextView title, type, text, time;
            private LinearLayout ll;
        }
    }


    //从服务器上拉取数据
    private void getData() {
        String ul = BaseApplication.getUrl() + "tfBusinessReport_list.do";

        params.addBodyParameter("id", sharedPreferences.getString("userid", ""));
        httpUtilsConnection(ul, params, HttpMethod.POST);
    }

    private void httpUtilsConnection(String url, RequestParams params, HttpMethod method) {
        httpUitil.send(method, url, params, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                // TODO Auto-generated method stub
                TaskGetEntity find = new Gson().fromJson(responseInfo.result, TaskGetEntity.class);
//                adapter.addData(find.getTfBusinessReport());
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                // TODO Auto-generated method stub
            }
        });
    }

}
