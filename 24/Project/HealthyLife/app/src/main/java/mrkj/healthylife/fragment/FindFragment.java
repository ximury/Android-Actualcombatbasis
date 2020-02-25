package mrkj.healthylife.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mrkj.healthylife.R;
import mrkj.healthylife.activity.PlayActivity;
import mrkj.healthylife.adapter.MyAdapter;
import mrkj.healthylife.application.DemoApplication;
import mrkj.healthylife.base.BaseFragment;
import mrkj.healthylife.db.DatasDao;
import mrkj.healthylife.service.ExecuteHealthyPlanService;
import mrkj.healthylife.utils.Constant;

/**
 * Created by Administrator on 2016/5/27.
 */
public class FindFragment extends BaseFragment{
    private View view;//界面的布局
    private Context context;
    public static Bitmap[] bitmaps = DemoApplication.bitmaps;
    public static String[] shuoming = DemoApplication.shuoming;
    public static final String cishu = "1组6次";
    private ListView listView;
    private List<Map<String,Object>> list;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_find,null);
        listView = (ListView) view.findViewById(R.id.find_list);
        if (isAdded()){
            list = new ArrayList<>();
            for (int i =0 ; i< 5 ; i++){
                Map<String,Object> map =new HashMap<>();
                map.put("tu",bitmaps[i]);
                map.put("xm",shuoming[i]);
                map.put("cs",cishu);
                map.put("tj","添加新计划");
                list.add(map);
            }
            MyAdapter adapter = new MyAdapter(context,list,this);
            listView.setAdapter(adapter);
            DatasDao datasDao = new DatasDao(getContext());
            Cursor cursor = datasDao.selectAll("plans");
            Log.e("当前计划的任务个数",cursor.getCount() + "个");
        }


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context,"开始运动",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext(), PlayActivity.class).putExtra("play_type", position).putExtra("what",1));
            }
        });
        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 2000){
            DatasDao datasDao = new DatasDao(getContext());
            Cursor cursor = datasDao.selectAll("plans");
            if (cursor.getCount() == 1){
                //开启执行计划的服务
                Log.e("开启服务","服务初始化");
                getContext().startService(new Intent(getContext(), ExecuteHealthyPlanService.class).putExtra("code", Constant.START_PLAN));
            }else{
                Log.e("开启服务","服务已开启");
                Log.e("当前任务个数", cursor.getCount() + "个任务");
                getContext().startService(new Intent(getContext(), ExecuteHealthyPlanService.class).putExtra("code", Constant.CHANGE_PLAN));
            }
            cursor.close();
        }
    }
}
