package mrkj.flowersdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import mrkj.flowersdemo.R;

/**
 * 主界面左侧的标题栏
 * Created by Administrator on 2016/7/20.
 */
public class MainLeftToolView extends LinearLayout implements View.OnClickListener{

    public static final int NEWS = 0;//原创
    public static final int NOTIFICATION = 1;//活动
    public static final int ABOUTS = 2;//我们
    private LayoutInflater inflater;//布局填充器

    /**
     * 点击回调
     */
    private onItemCheckedChangedListener  changedListener;

    public interface onItemCheckedChangedListener{
        void onItemChecked(View view,int position);
    }
    public void setOnItemCheckedChanged(onItemCheckedChangedListener listener){
        this.changedListener = listener;
    }

    /**
     * 构造方法
     * @param context
     */
    public MainLeftToolView(Context context) {
        this(context,null);
    }

    public MainLeftToolView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取布局填充器
        inflater = LayoutInflater.from(context);
        //获取单选按钮的父容器
        View view = inflater.inflate(R.layout.left_bar_layout,this);
        TextView news = (TextView) view.findViewById(R.id.news);
        TextView notification = (TextView) view.findViewById(R.id.notification);
        notification.setVisibility(GONE);
        TextView about = (TextView) view.findViewById(R.id.about);
        //设置点击事件
        news.setOnClickListener(this);
        notification.setOnClickListener(this);
        about.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (changedListener == null){
            return;
        }
        switch (view.getId()){
            case R.id.news://原创
                changedListener.onItemChecked(view,NEWS);
                break;
            case R.id.notification://活动
                changedListener.onItemChecked(view,NOTIFICATION);
                break;
            case R.id.about://关于
                changedListener.onItemChecked(view,ABOUTS);
                break;
            default:
                break;
        }
    }
}
