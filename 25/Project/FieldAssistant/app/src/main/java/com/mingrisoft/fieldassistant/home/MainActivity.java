package com.mingrisoft.fieldassistant.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mingrisoft.fieldassistant.R;
import com.mingrisoft.fieldassistant.analysis.WorkAnalysisActivity;
import com.mingrisoft.fieldassistant.baidu.BaiduRoutePlanActivity;
import com.mingrisoft.fieldassistant.customer.CustomerAllActivity;
import com.mingrisoft.fieldassistant.follow.FollowActivity;
import com.mingrisoft.fieldassistant.message.MsgActivity;
import com.mingrisoft.fieldassistant.pay.PayActivity;
import com.mingrisoft.fieldassistant.recorder.RecorderActivity;
import com.mingrisoft.fieldassistant.set.SetActivity;
import com.mingrisoft.fieldassistant.signed.SignedActivity;
import com.mingrisoft.fieldassistant.task.TaskReportActivity;
import com.mingrisoft.fieldassistant.workingPlan.WorkingPlanActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //定义一个gridView的表格布局
    private GridView gridView;


    //设置每个item的图片的数组
    private int[] images = new int[]{
            R.mipmap.one, R.mipmap.two, R.mipmap.three,
            R.mipmap.four, R.mipmap.five, R.mipmap.six,
            R.mipmap.seven, R.mipmap.eight, R.mipmap.nine
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        /**
         * 初始化控件
         *
         * */
        gridView = (GridView) findViewById(R.id.home_gv);
        PictureAdapter adapter = new PictureAdapter(null, images, this);      //自定义gridview的适配器
        gridView.setAdapter(adapter);       //为gridview设置适配器

        /***
         * //gridview设置点击监听事件
         *
         * 点击不同item的时候 跳转到不同的activity里面
         *
         * */
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startOther(SignedActivity.class);   //执行跳转的方法
                        break;
                    case 1:
                        startOther(TaskReportActivity.class);   //执行跳转的方法
                        break;
                    case 2:
                        startOther(FollowActivity.class);   //执行跳转的方法
                        break;
                    case 3:
                        startOther(CustomerAllActivity.class);   //执行跳转的方法
                        break;
                    case 4:
                        startOther(WorkingPlanActivity.class);   //执行跳转的方法
                        break;
                    case 5:
                        startOther(WorkAnalysisActivity.class);   //执行跳转的方法
                        break;
                    case 6:
                        startOther(PayActivity.class);   //执行跳转的方法
                        break;
                    case 7:
                      startOther(MsgActivity.class);   //执行跳转的方法
                        break;
                    case 8:
                       startOther(SetActivity.class);   //执行跳转的方法
                        break;
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //将此activity加到列队里面  以便finish掉
        BaseApplication.addDestroyActiivty(this,"mainActivity");
    }

        /**
         * 该方法是用来此页面跳转到其他的页面的
         * */
    private void startOther(Class cls) {
        Intent intent = new Intent(MainActivity.this, cls);
        startActivity(intent);
    }

    //点击进入导航页面
    public void navigation(View v) {
        startActivity(new Intent().setClass(this, BaiduRoutePlanActivity.class));
    }

    //点击进入录音功能界面
    public void record(View v) {
        startActivity(new Intent().setClass(this, RecorderActivity.class));

    }

    //自定义表格布局的适配器
    class PictureAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        private List<Picture> pictures;     //向适配器里面添加数据

        public PictureAdapter(String[] titles, int[] images, Context context) {
            super();
            pictures = new ArrayList<Picture>();
            inflater = LayoutInflater.from(context);
            for (int i = 0; i < images.length; i++) {  //执行循环的方法向里面添加数据
                Picture picture = new Picture(null, images[i]);
                pictures.add(picture);
            }

        }

        @Override
        public int getCount() {
            if (null != pictures) {
                return pictures.size();
            } else {
                return 0;
            }
        }

        @Override
        public Object getItem(int position) {
            return pictures.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.home_item_layout, null);
                viewHolder = new ViewHolder();
                viewHolder.title = (TextView) convertView.findViewById(R.id.home_gv_tv);
                viewHolder.image = (ImageView) convertView.findViewById(R.id.home_gv_iv);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.title.setText(pictures.get(position).getTitle());
            viewHolder.image.setImageResource(pictures.get(position).getImageId());
            return convertView;
        }
    }
}

class ViewHolder {
    public TextView title;
    public ImageView image;

}


/**
 * 设置一个picture的实体类
 *
 * 用来向适配器里里面添加数据用的
 *
 * 设置一个string类型的标题
 * 和int的图片
 *
 * */
class Picture {
    private String title;
    private int imageId;

    public Picture() {
        super();
    }

    public Picture(String title, int imageId) {
        super();
        this.title = title;
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
