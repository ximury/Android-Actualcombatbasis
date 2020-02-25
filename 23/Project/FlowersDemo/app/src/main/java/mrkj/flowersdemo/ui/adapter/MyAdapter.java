package mrkj.flowersdemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import mrkj.flowersdemo.R;
import mrkj.flowersdemo.application.MyDataHelper;
import mrkj.flowersdemo.ui.bean.Quotes;

/**
 * Created by Administrator on 2016/8/1.
 */
public class MyAdapter extends BaseAdapter{

    private ArrayList<Quotes> list;//数据源
    private LayoutInflater inflater;//布局填充器

    public MyAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        this.list = MyDataHelper.getInstance().getQuotesData();
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
        if(convertView == null){
            holder  = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_layout,null);
            holder.quotes = (TextView) convertView.findViewById(R.id.quotes);
            holder.author = (TextView) convertView.findViewById(R.id.author);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.quotes.setText(list.get(position).getQuotes());
        holder.author.setText(list.get(position).getAuthor());
        return convertView;
    }

    class ViewHolder{
        TextView quotes,author;
    }
}
