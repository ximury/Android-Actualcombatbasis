package com.mingrisoft.fieldassistant.workingPlan;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mingrisoft.fieldassistant.R;
import com.mingrisoft.fieldassistant.follow.FollowDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/5 0005.
 */
public class TargetsFragment extends Fragment {

    private ListView listView;
    private PlanReferAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.list_view_layout,null);
        adapter = new PlanReferAdapter(getActivity());
        listView = (ListView) view.findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        return view;
    }

    class PlanReferAdapter extends BaseAdapter {
        Context context;
        List<String> data;
        public PlanReferAdapter(Context context) {
            this.context = context;
            adD();
        }

        private void adD() {
            data = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                data.add("1");
            }
        }


        public void addData(List<String> dt) {
            data = new ArrayList<>();
            this.data = dt;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
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
            holder.name.setText("领导下达的任务");
            holder.type.setText("团队任务");
            holder.text.setText("你们碎叶别拦我，别问我为什么，我只能说任性");
            holder.time.setText("2016-12-23");
            holder.rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(getActivity(),TargetsDetailActivity.class);
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
