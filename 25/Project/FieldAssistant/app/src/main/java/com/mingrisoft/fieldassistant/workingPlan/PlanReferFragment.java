package com.mingrisoft.fieldassistant.workingPlan;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.mingrisoft.fieldassistant.home.BaseApplication;
import com.mingrisoft.fieldassistant.R;
import com.mingrisoft.fieldassistant.entity.PlanListEntity;

import java.util.ArrayList;
import java.util.List;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * Created by Administrator on 2016/7/5 0005.
 */
public class PlanReferFragment  extends Fragment{

    private ListView listView;
    private PlanReferAdapter adapter;
    private RequestParams params;
    private HttpUtils httpUitil;
    private SharedPreferences sharedPreferences;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.list_view_layout,null);
        adapter = new PlanReferAdapter(getActivity());
        listView = (ListView) view.findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sharedPreferences = getActivity().getSharedPreferences("share", Context.MODE_PRIVATE);
        params = new RequestParams();
        httpUitil = new HttpUtils();

    }

    @Override
    public void onStart() {
        super.onStart();
        getData();
    }

    //从服务器上拉取数据
    private void getData() {
        String ul = BaseApplication.getUrl() + "tfJobPlan_list.do";
        params.addBodyParameter("id", sharedPreferences.getString("userid", ""));
        httpUtilsConnection(ul, params, HttpMethod.POST);
    }

    private void httpUtilsConnection(String url, RequestParams params, HttpMethod method) {
        httpUitil.send(method, url, params, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                // TODO Auto-generated method stub
                PlanListEntity find = new Gson().fromJson(responseInfo.result, PlanListEntity.class);
                adapter.addData(find.getTfJobPlanList());
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                // TODO Auto-generated method stub
            }
        });
    }

    class PlanReferAdapter extends BaseAdapter {
        Context context;
        List<PlanListEntity.TfJobPlanListBean> data;
        public PlanReferAdapter(Context context) {
            this.context = context;

        }
        public void addData(List<PlanListEntity.TfJobPlanListBean> dt) {
            data = new ArrayList<>();
            this.data = dt;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            if (data!= null&& data.size()>0){
                return data.size();
            }else {
                return 0;
            }
        }

        @Override
        public Object getItem(int position) {
            if (data!= null && data.size()>0){
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
                convertView = LayoutInflater.from(context).inflate(R.layout.plan_refer_item_layout,null);
                holder.name = (TextView) convertView.findViewById(R.id.working_name);
                holder.type = (TextView) convertView.findViewById(R.id.working_type);
                holder.text = (TextView) convertView.findViewById(R.id.working_connect);
                holder.time = (TextView) convertView.findViewById(R.id.working_time);
                holder.rl = (RelativeLayout) convertView.findViewById(R.id.working_item_rl);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder) convertView.getTag();
            }
            final PlanListEntity.TfJobPlanListBean bean = data.get(position);
            holder.name.setText(bean.getJpTitle());
            holder.type.setText("");
            holder.text.setText(bean.getJpRemark()+"");
            holder.time.setText(bean.getJpData()+" / "+bean.getJpTime());
            holder.rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),PlanReferDetailActivity.class);
                    intent.putExtra("detailId",bean.getJpId());
                    startActivity(intent);
                }
            });

            return convertView;
        }
        class ViewHolder {
            private TextView name,type,text,time;
            private RelativeLayout rl;
        }
    }
}
