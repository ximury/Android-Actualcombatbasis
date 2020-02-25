package com.mingrisoft.fieldassistant.customer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
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
import com.mingrisoft.fieldassistant.entity.CustomerDetailEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/30 0030.
 */
public class CustomerDataActivity extends BaseActivity implements View.OnClickListener {

    private ListView listView;
    private View headView;
    private CustomerDataAdapter adapter;
    private TextView name, sex, age, childBirthday, childSex,
            birthday, phone, callPhone, like, type, firm, job,
            level, count, remark, location;
    private String cId;
    private RequestParams params;
    private HttpUtils httpUitil;
    private SharedPreferences sharedPreferences;
    private ImageButton backBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_customer_layout);
        sharedPreferences = getSharedPreferences("share", Context.MODE_PRIVATE);
        params = new RequestParams();
        httpUitil = new HttpUtils();
        listView = (ListView) findViewById(R.id.customer_list);
        adapter = new CustomerDataAdapter(this);
        addHead();
        listView.addHeaderView(headView);
        listView.setAdapter(adapter);
    }

    private void addHead() {
        headView = LayoutInflater.from(this).inflate(R.layout.customer_head_layout, null);
        name = (TextView) headView.findViewById(R.id.customer_head_name);
        sex = (TextView) headView.findViewById(R.id.customer_head_sex);
        age = (TextView) headView.findViewById(R.id.customer_head_age);
        childBirthday = (TextView) headView.findViewById(R.id.customer_head_child_age);
        childSex = (TextView) findViewById(R.id.customer_head_child_sex);
        birthday = (TextView) headView.findViewById(R.id.customer_birthday);
        phone = (TextView) headView.findViewById(R.id.customer_head_phone);
        callPhone = (TextView) headView.findViewById(R.id.customer_head_call);
        like = (TextView) headView.findViewById(R.id.customer_head_like);
        type = (TextView) headView.findViewById(R.id.customer_head_type);
        firm = (TextView) headView.findViewById(R.id.customer_head_firm);
        job = (TextView) headView.findViewById(R.id.customer_head_firm_job);
        level = (TextView) headView.findViewById(R.id.customer_head_level);
        count = (TextView) headView.findViewById(R.id.customer_head_count);
        remark = (TextView) headView.findViewById(R.id.customer_head_remarks);
        location = (TextView) headView.findViewById(R.id.customer_head_location);
        backBtn = (ImageButton) findViewById(R.id.back_im_btn);
        backBtn.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        cId = intent.getStringExtra("customerId");
        getData();
    }

    private void setHead(CustomerDetailEntity customerDetailEntity) {

        CustomerDetailEntity.TfCustomerBean bean = customerDetailEntity.getTfCustomer();
        name.setText(bean.getCustomerName());
        sex.setText(bean.getCustomerSex() + "");
//        age.setText("");
        childBirthday.setText(bean.getCustomerSpare3()+"");
//        childSex.setText("");
//        birthday.setText("");
        phone.setText(bean.getCustomerMobile() + "");
        callPhone.setText(bean.getCustomerPhone() + "");
        like.setText(bean.getCustomerLike() + "");
        type.setText(bean.getCustomerStatus() + "");
        firm.setText(bean.getCustomerCompany());
        job.setText(bean.getCustomerJob() + "");
        level.setText(bean.getCustomerSpare2() + "");
        count.setText(bean.getCustomerSpare1() + "");
        remark.setText(bean.getCustomerRemark() + "");
        location.setText(bean.getCustomerAddress() + "");
    }

    //从服务器上拉取数据
    private void getData() {
        String ul = BaseApplication.getUrl() + "tfCustomer_view.do";
        params.addBodyParameter("id", sharedPreferences.getString("userid", ""));
        params.addBodyParameter("cid", cId);
        httpUtilsConnection(ul, params, HttpMethod.POST);
    }

    private void httpUtilsConnection(String url, RequestParams params, HttpMethod method) {
        httpUitil.send(method, url, params, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                // TODO Auto-generated method stub
                CustomerDetailEntity find = new Gson().fromJson(responseInfo.result, CustomerDetailEntity.class);
                setHead(find);
                adapter.addData(find.getTfBusinessReportList());
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                // TODO Auto-generated method stub
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_im_btn:
                finish();
                break;
        }
    }

    class CustomerDataAdapter extends BaseAdapter {
        Context context;
        List<CustomerDetailEntity.TfBusinessReportListBean> data;

        public CustomerDataAdapter(Context context) {
            this.context = context;
        }

        public void addData(List<CustomerDetailEntity.TfBusinessReportListBean> dt) {
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
                convertView = LayoutInflater.from(context).inflate(R.layout.customer_item_layout, parent, false);
                holder.stage = (TextView) convertView.findViewById(R.id.costomer_detail_list_stage);
                holder.type = (TextView) convertView.findViewById(R.id.costomer_detail_list_type);
                holder.count = (TextView) convertView.findViewById(R.id.costomer_detail_list_count);
                holder.first = (TextView) convertView.findViewById(R.id.costomer_detail_list_first);
                holder.location = (TextView) convertView.findViewById(R.id.costomer_detail_list_location);
                holder.time = (TextView) convertView.findViewById(R.id.costomer_detail_list_time);
                holder.win = (TextView) convertView.findViewById(R.id.costomer_detail_list_win);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.stage.setText("签单完成");
            holder.type.setText(data.get(position).getBrStatus());
            holder.count.setText("");
            holder.first.setText(data.get(position).getBrContent());
            holder.win.setText("");
            holder.location.setText(data.get(position).getBrPlace());
            holder.time.setText(data.get(position).getBrData() + " / "+data.get(position).getBrTime());

            return convertView;
        }

        class ViewHolder {
            private TextView type, stage, count, first, win, location, time;

        }
    }
}
