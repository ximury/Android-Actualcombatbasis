package com.mingrisoft.fieldassistant.pay;

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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.mingrisoft.fieldassistant.R;
import com.mingrisoft.fieldassistant.entity.PayAllEntity;
import com.mingrisoft.fieldassistant.home.BaseActivity;
import com.mingrisoft.fieldassistant.home.BaseApplication;
import com.mingrisoft.fieldassistant.tool.YearMonthDialog;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/7 0007.
 */
public class PayActivity extends BaseActivity implements View.OnClickListener,YearMonthDialog.Message {
    private Spinner spinner;
    private ArrayAdapter<String> arrayAdapter;
    String[] msg = new String[]{"全部", "杂费", "业务"};
    private ListView listView;
    private TaskAdapter adapter;
    private TextView addTv;
    private RequestParams params;
    private HttpUtils httpUitil;
    private SharedPreferences sharedPreferences;
    private ImageButton backBtn;
    private TextView yearEt, monthEt;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_home_layout);
        spinner = (Spinner) findViewById(R.id.task_sp);
        listView = (ListView) findViewById(R.id.task_list);
        addTv = (TextView) findViewById(R.id.add_task_tv);
        backBtn = (ImageButton) findViewById(R.id.back_im_btn);
        monthEt = (TextView) findViewById(R.id.send_task_back_month);
        yearEt = (TextView) findViewById(R.id.send_task_back_year);
        backBtn.setOnClickListener(this);
        params = new RequestParams();
        httpUitil = new HttpUtils();
        linearLayout = (LinearLayout) findViewById(R.id.set_time_ll);
        linearLayout.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        sharedPreferences = getSharedPreferences("share", Context.MODE_PRIVATE);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, msg);
        spinner.setAdapter(arrayAdapter);
        adapter = new TaskAdapter(this);
        listView.setAdapter(adapter);
        //TODO
        //将解析出来的数据放到适配器里面
//        adapter.addData("添加数据");
        this.spinner.setOnItemSelectedListener(new OnItemSelectedListenerImpl());
        addTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PayActivity.this, PayAddActivity.class);
                startActivity(intent);
            }
        });
        Date date = new Date();

        int year = date.getYear() + 1900;
        int month = date.getMonth() + 1;

        if (month < 10) {
            monthEt.setText("0" + month);
        } else {
            monthEt.setText(month + "");
        }
        yearEt.setText(year + "");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_im_btn:
                finish();
                break;
            case R.id.set_time_ll:
                YearMonthDialog dialog = new YearMonthDialog(this);
                dialog.ShowDia();
                dialog.setMessage(this);
                break;
        }
    }

    @Override
    public void changer(Map<String, String> map) {
        yearEt.setText(map.get("year"));
        monthEt.setText(map.get("month"));
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
        List<PayAllEntity.TfLossListBean> data;

        public TaskAdapter(Context context) {
            this.context = context;
        }


        public void addData(List<PayAllEntity.TfLossListBean> dt) {
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
            holder.title.setText(data.get(position).getLossName());
            holder.type.setText(data.get(position).getLossStatus());
            holder.text.setText(data.get(position).getLossMoney() + "元");
            holder.time.setText(data.get(position).getLossSpare2());
            holder.rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PayActivity.this, PayDetailActivity.class);
                    intent.putExtra("brId", data.get(position).getLossId());
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
        String ul = BaseApplication.getUrl() + "tfLoss_list.do";

        params.addBodyParameter("id", sharedPreferences.getString("userid", ""));
        if (msg.equals("全部")) {
            params.addBodyParameter("lossStatus", "");
        } else {
            params.addBodyParameter("lossStatus", msg);
        }
//        String monthSt = monthEt.getText().toString();
//        if (monthSt.length()<2){
//            monthSt = "0"+monthEt.getText().toString();
//        }
//        monthEt.setText(monthSt);
        params.addBodyParameter("yearMonth", yearEt.getText().toString() + "-" + monthEt.getText().toString());
        httpUtilsConnection(ul, params, HttpMethod.POST);
    }

    private void httpUtilsConnection(String url, RequestParams params, HttpMethod method) {
        httpUitil.send(method, url, params, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                // TODO Auto-generated method stub
                PayAllEntity find = new Gson().fromJson(responseInfo.result, PayAllEntity.class);
                adapter.addData(find.getTfLossList());
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                // TODO Auto-generated method stub
            }
        });
    }

}
