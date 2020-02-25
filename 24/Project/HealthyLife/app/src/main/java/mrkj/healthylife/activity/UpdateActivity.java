package mrkj.healthylife.activity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Map;

import mrkj.healthylife.R;
import mrkj.healthylife.db.DatasDao;
import mrkj.healthylife.service.ExecuteHealthyPlanService;
import mrkj.healthylife.utils.Constant;
import mrkj.healthylife.utils.DateUtils;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener,TimePicker.OnTimeChangedListener{


    private Button change_stop_date,dimind_change_date;
    private TextView back_to_front;
    private TimePicker change_time;
    private int hour,minute;
    private int change_year,change_month,change_day;
    private int index;
    private int id;
    private DatasDao datasDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Intent intent = getIntent();
        index = intent.getIntExtra("position",0);
        id = intent.getIntExtra("id",1);
        Map<String,Object> time = DateUtils.getDate();
        change_year = (int) time.get("year");
        change_month = (int) time.get("month");
        change_day = (int) time.get("day");
        hour = (int) time.get("hour");
        minute = (int) time.get("minute");
        datasDao = new DatasDao(this);
        //初始化控件
        change_stop_date = (Button) findViewById(R.id.plan_stop);
        dimind_change_date = (Button) findViewById(R.id.set_clock);
        back_to_front = (TextView) findViewById(R.id.to_back);
        change_time = (TimePicker) findViewById(R.id.timePicker1);
        //设置监听事件
        change_stop_date.setOnClickListener(this);
        dimind_change_date.setOnClickListener(this);
        back_to_front.setOnClickListener(this);
        change_time.setOnTimeChangedListener(this);

        dimind_change_date.setText("确定更改第【"+(++index)+"】条计划吗？");
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.plan_stop://设置日期
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        change_year = year;
                        change_month = monthOfYear + 1;
                        change_day = dayOfMonth;
                        if(DateUtils.getNowDateMillisecondValues() > DateUtils.getMillisecondValues(year,monthOfYear,dayOfMonth)){
                            change_stop_date.setText("结束时间不得小于当前时间！");
                            change_stop_date.setTextColor(Color.parseColor("#ff0000"));
                        }else{
                            change_stop_date.setText(change_year+"-"+change_month+"-"+change_day);
                            change_stop_date.setTextColor(Color.parseColor("#ffffff"));
                        }
                    }
                }, (int)DateUtils.getDate().get("year"),(int)DateUtils.getDate().get("month")-1,(int)DateUtils.getDate().get("day"));
                datePickerDialog.show();
                break;
            case R.id.set_clock://确顶返回，并更新数据
                ContentValues values = new ContentValues();
                values.put("stop_year",change_year );
                values.put("stop_month",change_month );
                values.put("stop_day",change_day );
                values.put("hint_time",DateUtils.getMillisecondValues(hour,minute) );
                values.put("hint_hour",hour);
                values.put("hint_minute",minute);
                values.put("hint_str", hour + "点" + minute + "分");
                Cursor cursor =  datasDao.selectAll("plans");
                boolean up = true;
                while (cursor.moveToNext()){
                    int get_hour = cursor.getInt(cursor.getColumnIndex("hint_hour"));
                    int get_minute = cursor.getInt(cursor.getColumnIndex("hint_minute"));
                    if (DateUtils.getMillisecondValues(get_hour,get_minute) == DateUtils.getMillisecondValues(hour,minute)){
                        Toast.makeText(UpdateActivity.this,"该时间点有任务，请选择请他的时间点！",Toast.LENGTH_SHORT).show();
                        up = false;
                        break;
                    }
                }
                if (up){
                    int result = datasDao.updateValue("plans",values,"_id=?",new String[]{String.valueOf(id)});
                    if (result > 0){
                        startService(new Intent(UpdateActivity.this, ExecuteHealthyPlanService.class).putExtra("code", Constant.CHANGE_PLAN));
                        setResult(RESULT_OK);
                        finish();
                    }
                }
                break;
            case R.id.to_back://返回:
                finish();
                break;
            default:
                break;
        }
    }

    /**
     * 时间控件
     * @param view
     * @param hourOfDay
     * @param minute
     */
    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        this.hour = hourOfDay;
        this.minute = minute;
//        Log.e("时",this.hour + "");
//        Log.e("分",this.minute + "");
    }
}
