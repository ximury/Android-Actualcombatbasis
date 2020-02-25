package mrkj.flowersdemo.ui.activity;

import android.graphics.Color;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;
import mrkj.flowersdemo.R;
import mrkj.flowersdemo.db.MyDataUtils;
import mrkj.flowersdemo.ui.base.BaseActivity;
import mrkj.flowersdemo.ui.bean.RecordDate;
import mrkj.flowersdemo.ui.utils.SaveKeyValues;

/**
 * 历史
 */
public class HistoryActivity extends BaseActivity {


    //种植plant
    private TextView plant_today,plant_yesterday,plant_all;
    //分享share
    private TextView share_today,share_yesterday,share_all;
    private Map<String,Integer> mapData;//数据(分享与种植的数据)
    private ArrayList<RecordDate> timeList;//时间数据
    private LineChartView chartView;//折线统计图
    private LineChartData data;//数据集
    @Override
    protected int setChildContentView() {
        return R.layout.activity_history;
    }

    @Override
    protected void onCreateChild() {
        setStyle();//设置显示效果
        //初始化数据
        mapData = MyDataUtils.getTodayWithYesterdayData(this);
        timeList = MyDataUtils.getSevenDaysPlantTime(this);
        initViews();//初始化控件
        setTextViews();//设置控件
        setLineChart();//设置折线图的显示
    }

    /**
     * 设置折线图
     */
    private void setLineChart() {
        //设置X轴显示的日期
        List<AxisValue> axisValuesX = new ArrayList<>();
        for (int i = 0; i < timeList.size(); i++) {
            AxisValue axisValue = new AxisValue(i);
            axisValue.setLabel(timeList.get(i).getDay() + "");
            axisValuesX.add(axisValue);
        }
        Axis axisx = new Axis();//X轴
        Axis axisy = new Axis();//Y轴
        //设置X轴的显示样式
        axisx.setTextColor(Color.BLACK)
                .setName("日期")
                .setValues(axisValuesX);
        //设置Y轴的显示样式
        axisy.setTextColor(Color.BLACK)
                .setName("时间").setMaxLabelChars(5)
                .setHasLines(true);
        //设置数据源用于显示折线图
        List<PointValue> values = new ArrayList<>();
        for (int i = 0; i < timeList.size(); i++) {
            values.add(new PointValue(i, timeList.get(i).getHour()));
        }
        //这线的集合-->此处只有一条折线
        List<Line> lines = new ArrayList<>();
        //设置折线的样式
        Line line = new Line(values)
                .setColor(Color.parseColor("#4592F3"))
                .setCubic(false)
                .setHasLines(true)
                .setHasPoints(true);
        lines.add(line);
        data = new LineChartData();
        data.setLines(lines);
        data.setAxisYLeft(axisy);
        data.setAxisXBottom(axisx);
        //设置折线统计图
        chartView.setLineChartData(data);
    }

    /**
     * 设置控件
     */
    private void setTextViews() {
        plant_all.setText(SaveKeyValues.getIntValues("all_plant_flowers_counts",0) + "");
        plant_today.setText(mapData.get("today_plant") + "");
        plant_yesterday.setText(mapData.get("yesterday_plant")+ "");
        share_all.setText(SaveKeyValues.getIntValues("all_share_flowers_counts",0) + "");
        share_today.setText(mapData.get("today_share")+ "");
        share_yesterday.setText(mapData.get("yesterday_share")+ "");
    }

    /**
     * 初始化控件
     */
    private void initViews() {
        plant_today = (TextView) findViewById(R.id.plant_today);
        plant_yesterday = (TextView) findViewById(R.id.plant_yesterday);
        plant_all = (TextView) findViewById(R.id.plant_all);
        share_today = (TextView) findViewById(R.id.share_today);
        share_yesterday = (TextView) findViewById(R.id.share_yesterday);
        share_all = (TextView) findViewById(R.id.share_all);
        //显示折线图的控件
        chartView = (LineChartView) findViewById(R.id.line_chart);
    }

    private void setStyle() {
        //设置界面背景
        setActivityBackGround(R.mipmap.mrkj_grow_background);
        //设置标题栏的背景
        setTitleBackgroundColor(Color.TRANSPARENT);
        //设置标题栏
        setTitle("历史记录",getResources().getColor(R.color.left_text_color),R.mipmap.mrkj_com_back,true);
    }
    /**
     * 全屏（无状态栏）
     * @return
     */
    @Override
    public boolean setActivityFullScreen() {
        return true;
    }

    /**
     * 添加标题
     * @return
     */
    @Override
    public boolean isAddTitle() {
        return true;
    }

}
