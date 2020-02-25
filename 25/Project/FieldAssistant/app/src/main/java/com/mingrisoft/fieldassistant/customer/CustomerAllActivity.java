package com.mingrisoft.fieldassistant.customer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import com.mingrisoft.fieldassistant.entity.CustomerAllEntity;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/30 0030.
 */
public class CustomerAllActivity extends BaseActivity implements View.OnClickListener {

    private Spinner spinner;
    private ArrayAdapter arrayAdapter;
    private CustomerAllAdapter allAdapter;
    private ListView listView;
    private Button addBtn;
    String[] msg = new String[]{"全部", "多次合作", "初次合作", "未合作"};
    private RequestParams params;
    private HttpUtils httpUitil;
    private SharedPreferences sharedPreferences;
    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_home_layout);
        sharedPreferences = getSharedPreferences("share", Context.MODE_PRIVATE);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, msg);
        spinner = (Spinner) findViewById(R.id.customer_spinner);
        spinner.setAdapter(arrayAdapter);
        listView = (ListView) findViewById(R.id.custome_home_lv);
        allAdapter = new CustomerAllAdapter(this);
        listView.setAdapter(allAdapter);
        addBtn = (Button) findViewById(R.id.add_customer_btn);
        addBtn.setOnClickListener(this);
        params = new RequestParams();
        httpUitil = new HttpUtils();
        backBtn = (ImageButton) findViewById(R.id.back_im_btn);
        backBtn.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.spinner.setOnItemSelectedListener(new OnItemSelectedListenerImpl());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_customer_btn:
                Intent intent = new Intent(this, CustomerAddDataActivity.class);
                startActivity(intent);
                finish();
                break;
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

    //从服务器上拉取数据
    private void getData(String msg) {
        String ul = BaseApplication.getUrl() + "tfCustomer_list.do";
        params.addBodyParameter("id", sharedPreferences.getString("userid", ""));
        params.addBodyParameter("type", msg);
        httpUtilsConnection(ul, params, HttpMethod.POST);
    }

    private void httpUtilsConnection(String url, RequestParams params, HttpMethod method) {
        httpUitil.send(method, url, params, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                // TODO Auto-generated method stub
                CustomerAllEntity find = new Gson().fromJson(responseInfo.result, CustomerAllEntity.class);
                allAdapter.addData(find.getTfCustomerList());
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                // TODO Auto-generated method stub
            }
        });
    }

    class CustomerAllAdapter extends BaseAdapter {
        Context context;
        List<CustomerAllEntity.TfCustomerListBean> data;

        public CustomerAllAdapter(Context context) {
            this.context = context;

        }


        public void addData(List<CustomerAllEntity.TfCustomerListBean> dt) {
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
                convertView = LayoutInflater.from(context).inflate(R.layout.customer_all_itme_layout, null);
                holder.name = (TextView) convertView.findViewById(R.id.customer_all_name);
                holder.type = (TextView) convertView.findViewById(R.id.customer_all_type);
                holder.text = (TextView) convertView.findViewById(R.id.customer_all_text);
                holder.time = (TextView) convertView.findViewById(R.id.customer_all_time);
                holder.ll = (LinearLayout) convertView.findViewById(R.id.customer_all_rl);
                holder.phone = (ImageButton) convertView.findViewById(R.id.customer_all_phone);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            final CustomerAllEntity.TfCustomerListBean bean = data.get(position);
            holder.name.setText(bean.getCustomerName());
            holder.type.setText(bean.getCustomerStatus() + "");
            holder.text.setText(bean.getCustomerRemark());
            holder.time.setText(bean.getCustomerSpare4()+"");
            holder.ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CustomerAllActivity.this, CustomerDataActivity.class);
                    intent.putExtra("customerId", bean.getCustomerId());
                    startActivity(intent);
                }
            });
            holder.phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (bean.getCustomerMobile()!= null){
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + bean.getCustomerMobile()));
                        startActivity(intent);
                    }else {
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + bean.getCustomerPhone()));
                        startActivity(intent);
                    }


                }
            });

            return convertView;
        }

        class ViewHolder {
            private ImageButton phone;
            private TextView name, type, text, time;
            private LinearLayout ll;
        }
    }
}