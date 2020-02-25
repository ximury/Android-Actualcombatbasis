package mrkj.healthylife.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Map;

import mrkj.healthylife.R;
import mrkj.healthylife.application.DemoApplication;
import mrkj.healthylife.db.DatasDao;
import mrkj.healthylife.utils.Constant;
import mrkj.healthylife.utils.DateUtils;

/**
 * 添加计划的界面
 */
public class SettingHealthyHealthyActivity extends AppCompatActivity implements TimePicker.OnTimeChangedListener, View.OnClickListener {

    private TextView back;
    private TextView title;//运动类型
    private int type;
    private String title_name;
    private TimePicker timePicker;//设置时间
    private int alarmhour;//提示时
    private int alarmminute;//提示分
    private DatePickerDialog datePickerDialog;
    private Button start, stop;
    private int index;//用于区分获取开始还是结束
    private int start_year,start_month,start_day,stop_year,stop_month,stop_day;
    private Button finish_setting;
    //存入数据
    private DatasDao datasDao;
    private boolean isToSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_healthy_healthy);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);// 设置布局填充activity界面

        datasDao = new DatasDao(this);
        Intent intent = getIntent();

        //返回
        back = (TextView) findViewById(R.id.to_back);
        back.setOnClickListener(this);
        //类型
        title = (TextView) findViewById(R.id.plan_type);
        type = intent.getIntExtra("type", 0);
        title_name = DemoApplication.shuoming[type];
        title.setText(title_name);
        //时间
        timePicker = (TimePicker) findViewById(R.id.timePicker1);
        timePicker.setOnTimeChangedListener(this);
        //日期
        Map<String, Object> timeMap = DateUtils.getDate();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                
                switch (index){
                    case 0://start
                        start_year = year;
                        start_month = monthOfYear + 1;
                        start_day = dayOfMonth;
                        start.setText("起始："+start_year + "-" + start_month + "-" + start_day);
                        break;
                    case 1://stop
                        stop_year = year;
                        stop_month = monthOfYear + 1;
                        stop_day = dayOfMonth;
                        stop.setText("结束："+stop_year + "-" + stop_month + "-" + stop_day);
                        break;
                    default:
                        break;

                }
            }
        }, (Integer) timeMap.get("year"), (Integer) timeMap.get("month") - 1, (Integer) timeMap.get("day"));
        start = (Button) findViewById(R.id.plan_start);
        stop = (Button) findViewById(R.id.plan_stop);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        //完成
        finish_setting = (Button) findViewById(R.id.set_clock);
        finish_setting.setOnClickListener(this);
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.to_back://返回
                finish();
                break;
            case R.id.plan_start://设置开始日期
                index = 0;
                datePickerDialog.setTitle("设置开始时间");
                datePickerDialog.show();
                break;
            case R.id.plan_stop://设置结束日期
                index = 1;
                datePickerDialog.setTitle("设置结束时间");
                datePickerDialog.show();
                break;
            case R.id.set_clock://设置完成
                Log.e("分割","====================");
//                Log.e("分割","");
                int isAdd_24_hours = 0;
                long nowTime = DateUtils.getMillisecondValues((int) DateUtils.getDate().get("hour"), (int) DateUtils.getDate().get("minute"));
                //先对要存入的数值做一下处理
                long wantSaveTime = DateUtils.getMillisecondValues(alarmhour,alarmminute);
                int selectID = 0;
//                Log.e("增加前", wantSaveTime + "");
                if (wantSaveTime <= nowTime ){
                    wantSaveTime += Constant.DAY_FOR_24_HOURS;
                    isAdd_24_hours = 1;
//                    Log.e("执行了？","加上了24个小时");
                }
                //没设置结束时间
                if ( start_year != 0 && stop_year == 0){
                    stop_year = start_year;
                    stop_month = start_month;
                    stop_day = start_day;
                }
                //没设置开始时间
                if( start_year == 0 && stop_year != 0){
                    start_year = (int) DateUtils.getDate().get("year");
                    start_month = (int) DateUtils.getDate().get("month");
                    start_day = (int) DateUtils.getDate().get("day");
                }
                //没实质计划时间
                if(start_year == 0 && stop_year == 0){
                    start_year = (int) DateUtils.getDate().get("year");
                    start_month = (int) DateUtils.getDate().get("month");
                    start_day = (int) DateUtils.getDate().get("day");
                    stop_year = start_year;
                    stop_month = start_month;
                    stop_day = start_day;
                }
                //设置的时间不合法
                if (DateUtils.getMillisecondValues(start_year,start_month,start_day) > DateUtils.getMillisecondValues(stop_year,stop_month,stop_day)){
                    Toast.makeText(this,"开始时间不得大于结束时间！",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (DateUtils.getMillisecondValues((Integer) DateUtils.getDate().get("year"),(int) DateUtils.getDate().get("month"),(int) DateUtils.getDate().get("day")) > DateUtils.getMillisecondValues(stop_year,stop_month,stop_day)){
                    Toast.makeText(this,"结束时间不得小于当前时间！",Toast.LENGTH_SHORT).show();
                    return;
                }
                final ContentValues values = new ContentValues();
                //1.存入运动类型
                values.put("sport_type" , type);//1
                //2.存入运动类型名称
                values.put("sport_name" , title_name);//2
                //3.存入开始年月日
                values.put("start_year" , start_year);//3
                values.put("start_month" , start_month);//4
                values.put("start_day" , start_day);//5
                //4.存入结束年月日
                values.put("stop_year" , stop_year);//6
                values.put("stop_month" , stop_month);//7
                values.put("stop_day" , stop_day);//8
                //5.存入设置的时间
                values.put("set_time", nowTime);//9
                //6.存入提醒时间
                if (alarmminute == 0 && alarmhour == 0){
                    alarmhour = (int) DateUtils.getDate().get("hour");
                    alarmminute = (int) DateUtils.getDate().get("minute");
                }
                values.put("hint_time" , wantSaveTime);//10
//                Log.e("想要设置的时间", wantSaveTime + "");
                values.put("hint_str" , alarmhour + "点" + alarmminute + "分");
                values.put("hint_hour",alarmhour);
                values.put("hint_minute",alarmminute);
                //7.存入顺序
                values.put("number_values" , 0);
                values.put("add_24_hour",isAdd_24_hours);
                //插入前先做个判断 如果想要设置的提醒时间已存在则提示用户是否覆盖之前的设置时间
                Cursor cursor = datasDao.selectColumn("plans", new String[]{"_id", "hint_time"});
                //如果游标的值大于0，则说明数据库plans表中一有数据存入到当中
                if (cursor.getCount() != 0){

                    //查询数据 是否有这个值已被存入在数据表中
                    while (cursor.moveToNext()){

                        int id = cursor.getInt(cursor.getColumnIndex("_id"));
                        long alarmTime = cursor.getLong(cursor.getColumnIndex("hint_time"));
//                        Log.e("查询出的ID", id + "");
//                        Log.e("查询的时间", alarmTime + "");
//                        Log.e("比较", wantSaveTime + "<--->" + alarmTime);
                        if (wantSaveTime == alarmTime){//两个时间相等说明改时间点已被占用
//                            Log.e("是否进入判断", (wantSaveTime - alarmTime) + "yes");
                            selectID = id;
                            break;
                        }else{
//                            Log.e("是否进入判断", (wantSaveTime - alarmTime) + "no");
                        }
                    }

//                    Log.e("执行完循环体","yes");
                    cursor.close();
//                    Log.e("查询出的相同数据ID", selectID + "");
                    if (selectID != 0){
                        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                        dialog.setTitle("提示");
                        dialog.setMessage("改时间点已被占用，是否要覆盖改时间点的运动计划！");
                        final int finalSelectID = selectID;
                        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                datasDao.deleteValue("plans","_id=?",new String[]{String.valueOf(finalSelectID)});
                                isToSave = true;
                                insertData(values);
                            }
                        });
                        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                isToSave = false;
                                Toast.makeText(SettingHealthyHealthyActivity.this, "取消设置计划！", Toast.LENGTH_SHORT).show();
                            }
                        });
                        dialog.create();
                        dialog.show();
                    }else{
                        isToSave = true;
                        insertData(values);
                    }
                }else {
                    isToSave = true;
                    insertData(values);
                }

                break;
            default:
                break;
        }
    }

    private void insertData(ContentValues values){
        if (isToSave){
            //向数据库中插入数据
            long result = datasDao.insertValue("plans",values);
            if (result > 0){
                Toast.makeText(this,"设置计划成功！",Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
                finish();
            }else {
                Toast.makeText(this,"设置计划失败！请重新设置计划！",Toast.LENGTH_SHORT).show();
            }
        }
    }
    /**
     * 时间改变回调
     *
     * @param view
     * @param hourOfDay
     * @param minute
     */
    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
//        Log.e("hourOfDay",hourOfDay + "时");
//        Log.e("minute", minute + " 分");
        alarmhour = hourOfDay;
        alarmminute = minute;
    }
}
