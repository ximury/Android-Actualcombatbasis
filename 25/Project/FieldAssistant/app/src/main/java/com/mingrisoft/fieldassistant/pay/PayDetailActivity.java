package com.mingrisoft.fieldassistant.pay;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.mingrisoft.fieldassistant.R;
import com.mingrisoft.fieldassistant.entity.PayAllEntity;
import com.mingrisoft.fieldassistant.entity.PayDetailEntity;
import com.mingrisoft.fieldassistant.entity.TaskGetEntity;
import com.mingrisoft.fieldassistant.home.BaseActivity;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.mingrisoft.fieldassistant.home.BaseApplication;
import com.mingrisoft.fieldassistant.task.TaskDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/7 0007.
 */
public class PayDetailActivity extends BaseActivity implements View.OnClickListener {

    private String lossid;
    private RequestParams params;
    private HttpUtils httpUitil;
    private ListView listView;
    private PayDetailAdapter adapter;
    private View headView;
    private Button addBtn;
    private ImageButton backBtn;
    private TextView theme, name, money, type, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_detail_layout);
        params = new RequestParams();
        httpUitil = new HttpUtils();
        listView = (ListView) findViewById(R.id.pay_detail_list);
        addHeadView();
        listView.addHeaderView(headView);
        adapter = new PayDetailAdapter(this);
        listView.setAdapter(adapter);
        addBtn = (Button) findViewById(R.id.add_pay_btn);
        addBtn.setOnClickListener(this);
        backBtn = (ImageButton) findViewById(R.id.back_im_btn);
        backBtn.setOnClickListener(this);
    }

    private void addHeadView() {
        headView = LayoutInflater.from(this).inflate(R.layout.pay_detail_head_layout, null);
        theme = (TextView) headView.findViewById(R.id.pay_detail_head_theme);
        money = (TextView) headView.findViewById(R.id.pay_detail_head_money);
        type = (TextView) headView.findViewById(R.id.pay_detail_head_type);
        time = (TextView) headView.findViewById(R.id.pay_detail_head_time);
    }

    private void setHeadView(PayDetailEntity entity) {
        PayDetailEntity.TfLossBean bean = entity.getTfLoss();
        theme.setText(bean.getLossName());
        money.setText(bean.getLossMoney() + "元");
        type.setText(bean.getLossStatus());
        time.setText(bean.getLossSpare2());

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        lossid = intent.getStringExtra("brId");
        getData();
    }


    //从服务器上拉取数据
    private void getData() {
        String ul = BaseApplication.getUrl() + "tfLoss_view.do";

        params.addBodyParameter("lossid", lossid);
        httpUtilsConnection(ul, params, HttpMethod.POST);
    }

    private void httpUtilsConnection(String url, RequestParams params, HttpMethod method) {
        httpUitil.send(method, url, params, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                // TODO Auto-generated method stub

                PayDetailEntity find = new Gson().fromJson(responseInfo.result, PayDetailEntity.class);
                setHeadView(find);
                adapter.addData(find.getTfLossDeal());
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
            case R.id.add_pay_btn:
                Intent intent = new Intent(PayDetailActivity.this, PayDetailAddActivity.class);
                intent.putExtra("losid",lossid);
                startActivity(intent);
                break;
        }
    }

    class PayDetailAdapter extends BaseAdapter {
        Context context;
        List<PayDetailEntity.TfLossDealBean> data;

        public PayDetailAdapter(Context context) {
            this.context = context;
        }


        public void addData(List<PayDetailEntity.TfLossDealBean> dt) {
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
                convertView = LayoutInflater.from(context).inflate(R.layout.pay_detail_itme_layout, null);
                holder.name = (TextView) convertView.findViewById(R.id.pay_detail_head_name);
                holder.money = (TextView) convertView.findViewById(R.id.pay_detail_head_money);
                holder.time = (TextView) convertView.findViewById(R.id.pay_detail_head_time);
                holder.text = (TextView) convertView.findViewById(R.id.pay_detail_head_text);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.name.setText(data.get(position).getLdSpare1());
            holder.money.setText(data.get(position).getLdMoney()+"元");
            holder.time.setText(data.get(position).getLdSpare2());
            holder.text.setText(data.get(position).getLdRemark());

            return convertView;
        }

        class ViewHolder {
            private TextView name, money, time, text;

        }
    }

}
