package mrkj.healthylife.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;

import mrkj.healthylife.R;
import mrkj.healthylife.activity.PlayActivity;
import mrkj.healthylife.base.BaseFragment;
import mrkj.healthylife.entity.PMInfo;
import mrkj.healthylife.entity.TodayInfo;
import mrkj.healthylife.service.StepCounterService;
import mrkj.healthylife.utils.Constant;
import mrkj.healthylife.utils.HttpUtils;
import mrkj.healthylife.utils.SaveKeyValues;
import mrkj.healthylife.utils.StepDetector;
import mrkj.library.wheelview.circlebar.CircleBar;

/**
 * 运动
 * 功能介绍显示所在城市的空气质量和温度
 * 展示走步的进度
 * 展示一走的里程和消耗的热量
 * 跳转界面的功能按钮
 * Created by Administrator on 2016/5/27.
 */
public class SportFragment extends BaseFragment{//此处直接继承Fragment即可
    private static final int WEATHER_MESSAGE = 1;//显示天气信息
    private static final int STEP_PROGRESS = 2;//显示步数信息
    private View view;//界面的布局
    private TextView city_name,city_temperature,city_air_quality;//展示天气相关控件
    //显示精度的圆形进度条
    private CircleBar circleBar;//进度条
    private TextView show_mileage,show_heat,want_steps;//显示里程和热量
    private ImageButton warm_btn;//跳转按钮
    //下载天气预报的相关信息
    private TodayInfo todayInfo;//今日的天气
    private PMInfo pmInfo;//今日空气质量
    private String weather_url;//天气接口
    private String query_city_name;//城市名称
    //展示进度、里程、热量的相关参数
    private int custom_steps;//用户的步数
    private int custom_step_length;//用户的步长
    private int custom_weight;//用户的体重
    private Thread get_step_thread; // 定义线程对象
    private Intent step_service;//计步服务
    private boolean isStop;//是否运行子线程
    private Double distance_values;// 路程：米
    private int steps_values;//步数
    private Double heat_values;//热量
    private int duration;//动画时间
    private Context context;
    //传值
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case WEATHER_MESSAGE://天气信息网络请求结束后会跳到这里
                    String jsonStr = (String) msg.obj;
                    //获取Json数据
//                    Log.e("downLoad", "success:" + jsonStr);
                    if (jsonStr != null){
                        //获取下载的Json数据并进行相应的设置
                        setDownLoadMessageToView(jsonStr);
                    }
                    break;
                case STEP_PROGRESS://步数跟新后会调至这里
                    //获取计步的步数
                    steps_values = StepDetector.CURRENT_SETP;
                    //吧步数的进度显示在进度条上
                    circleBar.update(steps_values,duration);
                    duration = 0;
                    //存储当前的步数
                    SaveKeyValues.putIntValues("sport_steps", steps_values);
//                    Log.e("执行了", ":" + steps_values);
                    //计算里程
                    distance_values = steps_values *
                            custom_step_length * 0.01 *0.001;//km
//                    Log.e("里程", ":" + distance_values+"km");
                    show_mileage.setText(formatDouble(distance_values) + context.getString(R.string.km));
                    //存值
                    SaveKeyValues.putStringValues("sport_distance",
                            formatDouble(distance_values));
                    //消耗热量:跑步热量（kcal）＝体重（kg）×距离（公里）×1.036
                    heat_values = custom_weight * distance_values * 1.036;
                    //展示信息
                    show_heat.setText(formatDouble(heat_values) + context.getString(R.string.cal));
                    //存值
                    SaveKeyValues.putStringValues("sport_heat",
                            formatDouble(heat_values));
                    break;
            }
            return false;
        }
    });

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    /**
     * 创建视图
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sport, null);
        initView();//初始化控件
        initValues();//初始化数据
        setNature();//设置功能
        //提示
        if (StepDetector.CURRENT_SETP > custom_steps){
            Toast.makeText(getContext(),"您已达到目标步数,请适量运动！"
                    ,Toast.LENGTH_LONG).show();
        }
        //提示弹窗
        if (SaveKeyValues.getIntValues("do_hint",0) == 1
                && (System.currentTimeMillis() > (SaveKeyValues.
                getLongValues("show_hint",0)+Constant.DAY_FOR_24_HOURS))){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
            alertDialog.setTitle("提示");
            alertDialog.setMessage("你有计划没有完成!");
            alertDialog.setPositiveButton("点击确定不再提示！",
                    new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SaveKeyValues.putIntValues("do_hint" , 0);
                }
            });
            alertDialog.create();//创建弹窗
            alertDialog.show();//显示弹窗
        }
        return view;
    }

    /**
     * 初始化相关的属性
     */
    private void initValues(){
        //1、获取所在城市并获取该城市的天气信息
        query_city_name = SaveKeyValues.getStringValues("city", "北京");
        try {
            //使用URLEncoder这个方法
            // 请在gradle中依赖
            // compile 'org.apache.httpcomponents:httpcore:4.4.4'
            weather_url = String.format(Constant.GET_DATA,
                    URLEncoder.encode(query_city_name, "utf-8"));
            downLoadDataFromNet();//下载网络数据
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //2、获取计算里程和热量的相关参数-->默认步数：1000、步长：70cm、体重：50kg
        isStop = false;
        duration = 800;
        //获取默认值用于计算公里数和消耗的热量
        custom_steps = SaveKeyValues.getIntValues("step_plan",6000);//用户的步数
        custom_step_length = SaveKeyValues.getIntValues("length",70);//用户的步长
        custom_weight = SaveKeyValues.getIntValues("weight", 50);//用户的体重
//        Log.e("步数", custom_steps + "步");
//        Log.e("步长", custom_step_length + "厘米");
//        Log.e("体重", custom_weight + "公斤");
        //开启计步服务
        int history_values = SaveKeyValues.getIntValues("sport_steps", 0);
//        Log.e("获取存储的值", "" + history_values);
        int service_values = StepDetector.CURRENT_SETP;
//        Log.e("关闭程序后的值",service_values+"");
        boolean isLaunch = getArguments().getBoolean("is_launch",false);
        if (isLaunch){
            StepDetector.CURRENT_SETP = history_values + service_values;
        }
        //开启计步服务
        step_service = new Intent(getContext(),StepCounterService.class);
        getContext().startService(step_service);
    }
    /**
     * 下载数据
     */
    private void downLoadDataFromNet() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //下载天气预报
                String str = HttpUtils.getJsonStr(weather_url);
                Message message = Message.obtain();
                message.obj = str;
                message.what = WEATHER_MESSAGE;
                //handler传值
                handler.sendMessage(message);
            }
        }).start();
    }

    /**
     * 把下载的数值解析后赋值给相关的控件
     * @param resultStr
     */
    private void setDownLoadMessageToView(String resultStr){
        todayInfo = HttpUtils.parseNowJson(resultStr);// 获取当日的天气信息
        pmInfo = HttpUtils.parsePMInfoJson(resultStr);// 获取PM2.5的数据
        if (isAdded()){
            city_name.setText(context.getString(R.string.city)+query_city_name);
            city_temperature.setText(context.getString(R.string.temperature_hint) + todayInfo.getTemperature() + getString(R.string.temperature_unit));
            city_air_quality.setText(context.getString(R.string.quality) + pmInfo.getQuality());
        }
    }
    /**
     * 初始化控件
     */
    private void initView() {
        circleBar = (CircleBar) view.findViewById(R.id.show_progress);
        city_name = (TextView) view.findViewById(R.id.city_name);
        city_temperature = (TextView) view.findViewById(R.id.temperature);
        city_air_quality = (TextView) view.findViewById(R.id.air_quality);
        warm_btn = (ImageButton) view.findViewById(R.id.warm_up);
        show_mileage = (TextView) view.findViewById(R.id.mileage_txt);
        show_heat = (TextView) view.findViewById(R.id.heat_txt);
        want_steps = (TextView) view.findViewById(R.id.want_steps);
    }
    /**
     * 设置相关属性
     */
    private void setNature() {
        //设置初始的进度
        circleBar.setcolor(R.color.theme_blue_two);//设置进度条的颜色
        circleBar.setMaxstepnumber(custom_steps);//设置进度条的最大值
        getServiceValue();
        //跳转界面的按钮
        warm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "跳转热身界面！", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext(), PlayActivity.class)
                        .putExtra("play_type", 0).putExtra("what",0));
//                Random random = new Random();
//                for (int i = 0; i < 5; i++){
//                    int a = random.nextInt(5);
//                    Log.e("随机数","【"+a+"】");
//                }
//                startActivity(new Intent(getContext(), WarmUpActivity.class)
//                         .putExtra("random",new Random().nextInt(5)));
            }
        });
        want_steps.setText("今日目标："+custom_steps+"步");

    }
    /**
     * 获取计步服务的信息
     */
    private void getServiceValue() {
//        Log.e("开启子线程", "进入方法体！");
        if (get_step_thread == null) {

            get_step_thread = new Thread() {// 子线程用于监听当前步数的变化

                @Override
                public void run() {
                    super.run();
                    while (!isStop) {
                        try {
//                            Log.e("进入子线程","进入方法体！");
                            Thread.sleep(1000);//每个一秒发送一条信息给UI线程
                            if (StepCounterService.FLAG) {
                                handler.sendEmptyMessage(STEP_PROGRESS);// 通知主线程
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            };
            get_step_thread.start();
        }

    }
    /**
     * 计算并格式化doubles数值，保留两位有效数字
     *
     * @param doubles
     * @return 返回当前路程
     */
    private String formatDouble(Double doubles) {
        DecimalFormat format = new DecimalFormat("####.##");
        String distanceStr = format.format(doubles);
        return distanceStr.equals("0") ? "0.00" : distanceStr;
    }
    /**
     * 在当前Fragment结束之前，销毁一些不需要的变量
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(get_step_thread);
        isStop = true;
        get_step_thread = null;
        steps_values = 0;
        duration = 800;
    }
}
