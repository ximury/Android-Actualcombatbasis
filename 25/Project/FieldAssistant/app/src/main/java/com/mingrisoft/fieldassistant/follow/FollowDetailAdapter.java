package com.mingrisoft.fieldassistant.follow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mingrisoft.fieldassistant.R;
import com.mingrisoft.fieldassistant.entity.FollowDetailEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/6 0006.
 */
public class FollowDetailAdapter extends BaseAdapter {

    private Context context;
    private List<FollowDetailEntity.TfBusinessListDealBean> dealBeanList;

    public FollowDetailAdapter(Context context) {
        this.context = context;
        dealBeanList = new ArrayList<>();
    }

    public void addData(FollowDetailEntity find) {
        this.dealBeanList = find.getTfBusinessListDeal();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (dealBeanList != null && dealBeanList.size() > 0) {
            return dealBeanList.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        if (dealBeanList != null && dealBeanList.size() > 0) {
            return dealBeanList.get(position);
        } else {
            return 0;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.follow_detail_item_one_layout,null);

            holder.context = (TextView) convertView.findViewById(R.id.follow_detail_list_context);
            holder.status = (TextView) convertView.findViewById(R.id.follow_detail_list_status);
            holder.time = (TextView) convertView.findViewById(R.id.follow_detail_list_time);
            holder.location = (TextView) convertView.findViewById(R.id.follow_detail_list_location);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.context.setText(dealBeanList.get(position).getBldContent());
        holder.status.setText(dealBeanList.get(position).getBldStatus());
        holder.time.setText(dealBeanList.get(position).getBldVisitdata());
        holder.location.setText(dealBeanList.get(position).getBldPlace()+"");

        return convertView;
    }

    class ViewHolder {
        private TextView time,status,context,location;
    }
}
