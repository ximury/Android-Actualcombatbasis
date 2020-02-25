package com.mingrisoft.fieldassistant.tool;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.mingrisoft.fieldassistant.R;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/11 0011.
 * 同AddTimeDialog这个类是一样
 */
public class YearMonthDialog  {

    public interface Message{
        void changer(Map<String, String> map);
    }
    private Message message;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    private Context context;
    private Spinner spinnerYear, spinnerMonth;
    private ArrayAdapter<String> adapterYear;
    private ArrayAdapter<String> adapterMonth;

    private  String year,month;
    String[] msgYear = new String[]{  "2016", "2017", "2018", "2019", "2020"};
    String[] msgMonth = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
    private int yearSys, monthSys;
    private String yearSs, monthSs;
    private Date date;
    public YearMonthDialog(Context context) {
        this.context = context;
        date = new Date();
        yearSys = date.getYear() + 1900;
        monthSys = date.getMonth() + 1;
        yearSs = yearSys + "";
        if (monthSys < 10) {
            monthSs = "0" + monthSys;
        } else {
            monthSs = monthSys + "";
        }
    }

    public void  ShowDia() {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.time_year_month_layout, null);
        builder.setView(view);
        builder.setTitle("设置时间：");
        spinnerYear = (Spinner) view.findViewById(R.id.send_task_back_year);
        spinnerMonth = (Spinner) view.findViewById(R.id.send_task_back_month);
        adapterYear = new ArrayAdapter<String>(context, android.R.layout.simple_selectable_list_item, msgYear);
        spinnerYear.setAdapter(adapterYear);
        setSpinnerItemSelectedByValue(spinnerYear, yearSs, 1);
        this.spinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String msg = parent.getItemAtPosition(position).toString();
                year = msg;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        adapterMonth = new ArrayAdapter<String>(context, android.R.layout.simple_selectable_list_item, msgMonth);
        spinnerMonth.setAdapter(adapterMonth);
        setSpinnerItemSelectedByValue(spinnerMonth, monthSs, 2);
        this.spinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String msg = parent.getItemAtPosition(position).toString();
                month = msg;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (message != null) {
                    HashMap<String,String> map = new HashMap<String, String>();
                    map.put("year", year);
                    map.put("month", month);
                    message.changer(map);
                }
            }
        });
        builder.show();
    }

    public void setSpinnerItemSelectedByValue(Spinner spinner, String value, int j) {
        SpinnerAdapter apsAdapter = spinner.getAdapter(); //得到SpinnerAdapter对象
        int k = apsAdapter.getCount();
        for (int i = 0; i < k; i++) {
            if (value.equals(apsAdapter.getItem(i).toString())) {
                spinner.setSelection(i, true);// 默认选中项
                switch (j) {
                    case 1:
                        year = apsAdapter.getItem(i).toString();
                        break;
                    case 2:
                        month = apsAdapter.getItem(i).toString();
                        break;
                }
                break;
            }
        }
    }
}
