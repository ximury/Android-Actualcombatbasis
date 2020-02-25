package mrkj.healthylife.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mrkj.healthylife.R;
import mrkj.healthylife.application.DemoApplication;
import mrkj.healthylife.base.BaseActivity;
import mrkj.healthylife.db.DatasDao;
import mrkj.healthylife.fragment.FindFragment;
import mrkj.healthylife.service.ExecuteHealthyPlanService;
import mrkj.healthylife.utils.Constant;

public class MinePlanActivity extends BaseActivity {

    private List<Map<String,Object>> plan_List;//储存计划数据
    private ListView listView;//列表
    private DatasDao datasDao;//数据库操作诶
    private Cursor cursor;//游标
    private MyAdapter myAdapter;//适配器
    @Override
    protected void setActivityTitle() {
        initTitle();//初始化标题栏
        setTitle("我的计划", this);
        setMyBackGround(R.color.watm_background_gray);//设置标题栏的背景颜色
        setTitleTextColor(R.color.theme_blue_two);//设置字体的颜色
        setTitleLeftImage(R.mipmap.mrkj_back_blue);//设施返回键的图片
    }

    /**
     * 初始化界面布局
     */
    @Override
    protected void getLayoutToView() {
        setContentView(R.layout.activity_mine_plan);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initValues() {
        plan_List = new ArrayList<>();
        datasDao = new DatasDao(this);
        cursor = datasDao.selectAll("plans");//获取游标用来查询
        //遍历所有数据
        while (cursor.moveToNext()){
            Map<String,Object> map = new HashMap<>();
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            int type = cursor.getInt(cursor.getColumnIndex("sport_type"));
            int s_year = cursor.getInt(cursor.getColumnIndex("start_year"));
            int s_month = cursor.getInt(cursor.getColumnIndex("start_month"));
            int s_day = cursor.getInt(cursor.getColumnIndex("start_day"));
            int t_year = cursor.getInt(cursor.getColumnIndex("stop_year"));
            int t_month = cursor.getInt(cursor.getColumnIndex("stop_month"));
            int t_day = cursor.getInt(cursor.getColumnIndex("stop_day"));
            String h_time = cursor.getString(cursor.getColumnIndex("hint_str"));
            if (s_year == t_year && s_month == t_month & s_day == t_day){
                map.put("date",s_year+"-"+s_month+"-"+s_day);
            }else {
                map.put("date",s_year+"-"+s_month+"-"+s_day+"~"+t_year+"-"+t_month+"-"+t_day);
            }
            map.put("id",id);
            map.put("type",type);
            map.put("time",h_time);
            //向集合中添加数据
            plan_List.add(map);
        }
        //关闭游标
        cursor.close();
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {
        listView = (ListView) findViewById(R.id.plan_list);
    }

    /**
     * 设置控件的监听
     */
    @Override
    protected void setViewsListener() {

    }

    /**
     * 设置控件的功能
     */
    @Override
    protected void setViewsFunction() {
        if (plan_List.size() > 0){
            myAdapter = new MyAdapter();//构建适配器
            listView.setAdapter(myAdapter);//绑定适配器
        }
        //设置EmptyView当List中的数据为空的时候将显示
        TextView textView = new TextView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        textView.setLayoutParams(params);
        textView.setText("没有数据");
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(50);
        addContentView(textView,params);
        listView.setEmptyView(textView);
    }

    /**
     * 返回界面
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //返回后刷新数据列表
        if (requestCode == 3000 && resultCode ==RESULT_OK){
            Log.e("提示", "设置成功");
            plan_List.clear();//清空数据集合
            List<Map<String,Object>> update = new ArrayList<>();
            cursor = datasDao.selectAll("plans");
            while (cursor.moveToNext()){
                Map<String,Object> map = new HashMap<>();
                int id = cursor.getInt(cursor.getColumnIndex("_id"));
                int type = cursor.getInt(cursor.getColumnIndex("sport_type"));
                int s_year = cursor.getInt(cursor.getColumnIndex("start_year"));
                int s_month = cursor.getInt(cursor.getColumnIndex("start_month"));
                int s_day = cursor.getInt(cursor.getColumnIndex("start_day"));
                int t_year = cursor.getInt(cursor.getColumnIndex("stop_year"));
                int t_month = cursor.getInt(cursor.getColumnIndex("stop_month"));
                int t_day = cursor.getInt(cursor.getColumnIndex("stop_day"));
                String h_time = cursor.getString(cursor.getColumnIndex("hint_str"));
                if (s_year == t_year && s_month == t_month & s_day == t_day){
                    map.put("date",s_year+"-"+s_month+"-"+s_day);
                }else {
                    map.put("date",s_year+"-"+s_month+"-"+s_day+"~"+t_year+"-"+t_month+"-"+t_day);
                }
                map.put("id",id);
                map.put("type",type);
                map.put("time",h_time);
                update.add(map);
            }
            plan_List.addAll(update);
            myAdapter.noti();//通知适配器更新
            cursor.close();//关闭游标
        }
    }

    /**
     * 自定义适配器
     */
    class MyAdapter extends BaseAdapter{


        private void noti(){
            this.notifyDataSetChanged();
        }
        @Override
        public int getCount() {
            return plan_List.size();
        }

        @Override
        public Object getItem(int position) {
            return plan_List.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {
            final ViewHolder holder;
            if (convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.plan_item,null);
                holder = new ViewHolder();
                holder.date = (TextView) convertView.findViewById(R.id.date);
                holder.hint_time = (TextView) convertView.findViewById(R.id.hint_time);
                holder.name = (TextView) convertView.findViewById(R.id.xiangmu);
                holder.count = (TextView) convertView.findViewById(R.id.cishu1);
                holder.change = (TextView) convertView.findViewById(R.id.add_plan);
                holder.delete = (TextView) convertView.findViewById(R.id.delete_plan);
                holder.image = (ImageView) convertView.findViewById(R.id.image_show);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder) convertView.getTag();
            }
            Map<String,Object> map = plan_List.get(position);
            holder.date.setText(map.get("date").toString());//时间段
            holder.hint_time.setText("提示时间："+map.get("time").toString());//时间点
            holder.name.setText(DemoApplication.shuoming[((int) map.get("type"))]);//类型
            holder.count.setText(FindFragment.cishu);
            holder.change.setText("更改计划");
            holder.delete.setText("删除计划");
            holder.image.setImageBitmap(DemoApplication.bitmaps[((int) map.get("type"))]);//类型

            final int id = (int) map.get("id");
            holder.change.setTag(id);
            holder.delete.setTag(id);
            holder.change.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (id == (int)holder.change.getTag()){
                        startActivityForResult(new Intent(MinePlanActivity.this,UpdateActivity.class).putExtra("position",position).putExtra("id",id),3000);
                        Toast.makeText(getApplicationContext(), "更改计划", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            //***************************** DELETE *****************************
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (id == (int)holder.delete.getTag()){
                        AlertDialog.Builder builder = new AlertDialog.Builder(MinePlanActivity.this);
                        builder.setTitle("删除计划");
                        builder.setMessage("确定删除吗？");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                datasDao.deleteValue("plans", "_id=?", new String[]{String.valueOf(id)});
                                startService(new Intent(MinePlanActivity.this, ExecuteHealthyPlanService.class).putExtra("code", Constant.CHANGE_PLAN));
                                plan_List.remove(position);
                                noti();
                                Toast.makeText(getApplicationContext(), "删除计划", Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder.create();
                        builder.show();
                    }
                }
            });
            return convertView;
        }
    }

    class ViewHolder{
        TextView date,name,count,change,delete,hint_time;//日期，名称，次数，更改，删除
        ImageView image;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
