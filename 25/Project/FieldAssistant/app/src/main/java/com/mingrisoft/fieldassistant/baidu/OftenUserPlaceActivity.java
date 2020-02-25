package com.mingrisoft.fieldassistant.baidu;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.mingrisoft.fieldassistant.R;
import com.mingrisoft.fieldassistant.entity.AnalysisCustomerEntity;
import com.mingrisoft.fieldassistant.entity.OftenUserPlaceListEntity;
import com.mingrisoft.fieldassistant.entity.TaskGetEntity;
import com.mingrisoft.fieldassistant.home.BaseActivity;
import com.mingrisoft.fieldassistant.home.BaseApplication;
import com.mingrisoft.fieldassistant.task.TaskDetailActivity;

import java.util.ArrayList;
import java.util.List;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;


/**
 * Created by Administrator on 2016/7/8 0008.
 */
public class OftenUserPlaceActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton backBtn,addBtn;     //添加常用位置与返回按钮
    private ListView listView;              //要显示常用位置的list
    private OftenUserPlaceAdapter adapter;      //定义一个list的适配器
    private RequestParams params;                //拉取网络数据
    private HttpUtils httpUitil;                 //拉取网络数据
    private SharedPreferences sharedPreferences;    //为了从数据库里面获取到用户名


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ofetn_user_place_layout);

        //初始化控件
        sharedPreferences = getSharedPreferences("share", Context.MODE_PRIVATE);
        params = new RequestParams();
        httpUitil = new HttpUtils();
        backBtn = (ImageButton) findViewById(R.id.back_im_btn);
        backBtn.setOnClickListener(this);
        addBtn = (ImageButton) findViewById(R.id.add_im_btn);
        addBtn.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.often_user_list);
        adapter= new OftenUserPlaceAdapter(this);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getData("tfCommon_list",null);  //从网络上获取数据
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_im_btn:
                finish();           //返回上一个页面
                break;
            case R.id.add_im_btn:
                showDia();          //显示添加位置信息的dialog
                break;
        }

    }

    /**
     * 点击添加按钮执行此方法
     * */
    private void showDia() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_layout,null);
        final EditText editText;
        editText = (EditText) view.findViewById(R.id.often_location_et);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        builder.setTitle("输入想要添加的常用位置....");
        builder.setPositiveButton("添加", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getData("tfCommon_add",editText.getText().toString().trim());     // 将输入框内的字段发送给服务器
            }
        });
        builder.setNegativeButton("取消", null);
        builder.show();
    }

    //从服务器上拉取数据
    private void getData(String place,String edit) {
        String ul = BaseApplication.getUrl() + place + ".do";
        params.addBodyParameter("id", sharedPreferences.getString("userid", ""));
        params.addBodyParameter("commonContent", edit);
        httpUtilsConnection(ul, params, HttpMethod.POST,place);
    }
    private void httpUtilsConnection(String url, RequestParams params, HttpMethod method, final String place) {
        httpUitil.send(method, url, params, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                // TODO Auto-generated method stub
                if (place.equals("tfCommon_add")){
                    String result = responseInfo.result.toString().substring(1, 2);
                    if (result.equals("t")) {
                        Toast.makeText(OftenUserPlaceActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                        getData("tfCommon_list",null);      //上传成功后刷新常用位置的列表
                    } else {
                        Toast.makeText(OftenUserPlaceActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                    }
                }else {
                OftenUserPlaceListEntity find = new Gson().fromJson(responseInfo.result, OftenUserPlaceListEntity.class);
                    adapter.addData(find.getTfCommonList());
                }
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                // TODO Auto-generated method stub
            }
        });
    }
    /**
     * 自定义内部一个适配器
     * 用于显示位置信息的list
     *
     * */
    class OftenUserPlaceAdapter extends BaseAdapter {
        Context context;
        List<OftenUserPlaceListEntity.TfCommonListBean> data;

        public OftenUserPlaceAdapter(Context context) {
            this.context = context;
        }


        public void addData(List<OftenUserPlaceListEntity.TfCommonListBean> dt) {
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
                convertView = LayoutInflater.from(context).inflate(R.layout.baidu_itme_layout, null);
                holder.title = (TextView) convertView.findViewById(R.id.baidu_location);
                holder.ll = (LinearLayout) convertView.findViewById(R.id.baidu_location_ll);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.title.setText(data.get(position).getCommonContent());
            holder.ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(OftenUserPlaceActivity.this,BaiduRoutePlanActivity.class);
                    intent.putExtra("location", data.get(position).getCommonContent().toString());
                    startActivity(intent);
                }
            });

            return convertView;
        }

        class ViewHolder {
            private TextView title;
            private LinearLayout ll;
        }
    }

}
