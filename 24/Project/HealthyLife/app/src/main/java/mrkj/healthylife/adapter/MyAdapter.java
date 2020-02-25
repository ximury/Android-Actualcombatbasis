package mrkj.healthylife.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import mrkj.healthylife.R;
import mrkj.healthylife.activity.SettingHealthyHealthyActivity;
import mrkj.healthylife.fragment.FindFragment;

/**
 * Created by Administrator on 2016/6/6.
 */
public class MyAdapter extends BaseAdapter {

    private LayoutInflater inflater;//布局填充器
    private List<Map<String,Object>> list;//数据源
    private FindFragment findFragment;//fragment
    private Context context;//上下文

    /**
     * 构造方法
     * @param context
     * @param list
     * @param findFragment
     */
    public MyAdapter(Context context,List<Map<String,Object>> list,FindFragment findFragment){
        this.list = list;
        inflater = LayoutInflater.from(context);
        this.findFragment = findFragment;
        this.context = context;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item,null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.image_show);
            holder.textView1 = (TextView) convertView.findViewById(R.id.xiangmu);
            holder.textView2 = (TextView) convertView.findViewById(R.id.cishu1);
            holder.textView3 = (TextView) convertView.findViewById(R.id.add_plan);
            final int index = position;
            holder.textView3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    findFragment.startActivityForResult(new Intent(context, SettingHealthyHealthyActivity.class).putExtra("type", index), 2000);
                }
            });
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Map<String,Object> map = list.get(position);
        holder.imageView.setImageBitmap((Bitmap) map.get("tu"));
        holder.textView1.setText((String) map.get("xm"));
        holder.textView2.setText((String) map.get("cs"));
        holder.textView3.setText((String) map.get("tj"));
        return convertView;
    }

    class ViewHolder{
        ImageView imageView;
        TextView textView1,textView2,textView3;
    }
}
