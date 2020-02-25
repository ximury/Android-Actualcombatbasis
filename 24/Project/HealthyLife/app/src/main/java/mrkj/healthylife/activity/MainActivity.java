package mrkj.healthylife.activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mrkj.healthylife.R;
import mrkj.healthylife.base.BaseActivity;
import mrkj.healthylife.utils.SaveKeyValues;
import mrkj.library.wheelview.pickerView.PickerView;
import mrkj.library.wheelview.scalerulerview.ScaleRulerView;
import mrkj.library.wheelview.utils.DateViewHelper;

/**
 * 完善信息界面
 * -->主界面是FunctionActivity
 */
public class MainActivity extends BaseActivity implements View.OnClickListener,PickerView.onSelectListener,RadioGroup.OnCheckedChangeListener,DateViewHelper.OnResultMessageListener,View.OnFocusChangeListener{

    //TAG
    private static final String TAG = MainActivity.class.getSimpleName();
    //功能
    private DateViewHelper dateViewHelper;//日历操作
    private LayoutInflater inflater;//布局填充器
    private boolean closeDataPicker;//判断显示或隐藏日历
    private List<String> height_list;//身高的集合
    private boolean closeHeightPicker;//判断显示或隐藏数字选择器
    private boolean nextShow;//判断按钮是否显示
    //控件
    private LinearLayout personal_information_page_one;//完善资料1/2布局
    private RadioGroup group;//性别选择
    private EditText input_nick;//属性昵称
    private TextView input_birthday,input_height;//生日、身高
    private Button next_action;//下一步
    private LinearLayout choose_date;//日期选择
    private LinearLayout choose_height;//选择身高
    private PickerView height_picker;//横下滑动选择身高
    private ImageView back;//返回上一步
    private LinearLayout personal_information_page_two;//完善资料2/2布局
    private ScaleRulerView input_weight;//选择体重
    private TextView show_weight;//显示选择的体重
    private ScaleRulerView input_length;//选择体重
    private TextView show_length;//显示选择的体重
    private Button go_walk;//先去逛逛
    private Button go_make;//制定计划
    //信息
    private String gender_str;//性别
    private String nick_str;//昵称
    private String birthday_str;//生日
    private String height_str;//身高
    private int custom_age;//年龄
    private String weight_str;//体重文字
    private int weight;//体重数值
    private String length_str;//步长文字
    private int length;//步长数值

    private int year;
    private int month;
    private  int day;
    /**
     * 初始化窗口
     */
    @Override
    protected void getLayoutToView() {
        setContentView(R.layout.activity_main);
    }
    /**
     * 初始化标题
     */
    @Override
    protected  void setActivityTitle(){
        initTitle();
        setTitle(getString(R.string.personal_information_one));
        setTitleTextColor(getResources().getColor(R.color.black));


    }
    /**
     * 设置初始化的值和变量
     */
    @Override
    protected void initValues() {
        gender_str = getResources().getString(R.string.boy);
        initHeightData();
        nextShow = true;
    }

    /**
     * 初始化身高的集合
     */
    private void initHeightData(){
        //设置130cm至210cm
        height_list = new ArrayList<>();
        for (int i = 130;i <= 210;i++){
            height_list.add(i+"");
        }
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {

        //======================================= 完善资料1/2 =======================================

        personal_information_page_one = (LinearLayout) findViewById(R.id.personal_information_page_one);
        group = (RadioGroup) findViewById(R.id.gender);
        input_nick = (EditText) findViewById(R.id.input_nick);
        input_birthday = (TextView) findViewById(R.id.input_birthday);
        input_height = (TextView) findViewById(R.id.input_height);
        next_action = (Button) findViewById(R.id.next);
        //设置日期选择器
        inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        choose_date=(LinearLayout) findViewById(R.id.choose_date);
        dateViewHelper = new DateViewHelper(this);
        //设置身高选择器
        choose_height = (LinearLayout) findViewById(R.id.choose_height);
        height_picker = (PickerView) findViewById(R.id.height_picker);


        //======================================= 完善资料2/2 =======================================

        personal_information_page_two = (LinearLayout) findViewById(R.id.personal_information_page_two);
        input_weight = (ScaleRulerView) findViewById(R.id.input_weight);
        show_weight = (TextView) findViewById(R.id.show_weight);
        input_length = (ScaleRulerView) findViewById(R.id.input_length);
        show_length = (TextView) findViewById(R.id.show_length);
        go_walk = (Button) findViewById(R.id.walk);
        go_make = (Button) findViewById(R.id.make);
    }

    /**
     * 获取体重信息
     */
    private ScaleRulerView.OnValueChangeListener input_weight_listener = new ScaleRulerView.OnValueChangeListener() {
        @Override
        public void onValueChange(float value) {
            show_weight.setText((int)value+getString(R.string.kg));
            weight = (int) value;
            weight_str = (int)value+getString(R.string.kg);
        }
    };
    /**
     * 获取步长信息
     */
    private ScaleRulerView.OnValueChangeListener input_length_listener = new ScaleRulerView.OnValueChangeListener() {
        @Override
        public void onValueChange(float value) {
            show_length.setText((int)value+getString(R.string.cm));
            length = (int) value;
            length_str = (int)value+getString(R.string.cm);
        }
    };
    /**
     * 用于隐藏设置器和键盘
     */
    private View.OnTouchListener messageListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP){
                hideOthers();
            }
            return true;
        }
    };
    /**
     * 初始化控件的监听
     */
    @Override
    protected void setViewsListener() {

        //======================================= 完善资料1/2 =======================================

        group.setOnCheckedChangeListener(this);
        input_birthday.setOnClickListener(this);
        input_height.setOnClickListener(this);
        next_action.setOnClickListener(this);
        dateViewHelper.setOnResultMessageListener(this);
        input_nick.setOnFocusChangeListener(this);
        input_birthday.setOnFocusChangeListener(this);
        input_height.setOnFocusChangeListener(this);
        height_picker.setOnSelectListener(this);
        personal_information_page_one.setOnTouchListener(messageListener);

        //======================================= 完善资料2/2 =======================================

        input_weight.setValueChangeListener(input_weight_listener);
        input_length.setValueChangeListener(input_length_listener);
        go_walk.setOnClickListener(this);
        go_make.setOnClickListener(this);
    }
    /**
     * 设置相关管功能
     */
    @Override
    protected void setViewsFunction() {

        //======================================= 完善资料1/2 =======================================

        personal_information_page_one.setVisibility(View.VISIBLE);
        input_nick.setClickable(true);
        input_birthday.setClickable(true);
        input_height.setClickable(true);
        input_nick.setFocusableInTouchMode(true);
        input_birthday.setFocusableInTouchMode(true);
        input_height.setFocusableInTouchMode(true);
        //设置日期选择器
        choose_date.addView(dateViewHelper.getDataPick(inflater));
        //设置身高选择器
        height_picker.setData(height_list);

        //======================================= 完善资料2/2 =======================================

        personal_information_page_two.setVisibility(View.GONE);
        //默认50千克，最小30千克，最大130千克-->单位千克
        input_weight.initViewParam(50, 130, 30);
        //默认70厘米，最小40厘米，最大100厘米-->单位厘米
        input_length.initViewParam(70, 100, 40);
    }

    /**
     * 单选
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        hideOthers();
        //选择性别
        switch (checkedId){
            case R.id.boy://男
                gender_str = getResources().getString(R.string.boy);
                break;
            case R.id.girl://女
                gender_str = getResources().getString(R.string.girl);
                break;
            default:
                break;
        }
    }

    /**
     * 隐藏
     */
    private void hideOthers(){
        if (closeDataPicker == true){
            openPickerOrClose(false);
        }
        if (closeHeightPicker == true){
            openHeightPickerOrClose(false);
        }
        if (!nextShow){
            showNextBtn();
        }
        hideKeyBoard();
    }
    /**
     * 点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        //======================================= 完善资料1/2 =======================================
        switch (v.getId()){
            case R.id.input_birthday://获取生日信息
                openPickerOrClose(!closeDataPicker);
                if (!nextShow){
                    showNextBtn();
                }
                break;
            case R.id.input_height://获取身高信息
                openHeightPickerOrClose(!closeHeightPicker);
                if (!nextShow){
                    showNextBtn();
                }
                break;
            case R.id.next://获取更多信息
                nick_str = input_nick.getText().toString();
                birthday_str = input_birthday.getText().toString();
                height_str = input_height.getText().toString();
                Log.e("获取的相关信息","性别："+gender_str+"\t\t"+"昵称："+nick_str+"\t\t"+"生日："+birthday_str+"\t\t身高："+height_str+"\t\t年龄："+custom_age+"岁");
                if (!"".equals(nick_str) && !getString(R.string.please_write_birthday).equals(birthday_str) && !getString(R.string.please_write_height).equals(height_str)){
                    //保存资料
                    saveMessageOne();
                    //将“完善资料2/2”的布局显示出来并隐藏“完善资料1/2”的布局
                    //1、显示布局2
                    showAnimation(personal_information_page_one, R.anim.alpha_out);
                    personal_information_page_two.setVisibility(View.VISIBLE);
                    showAnimation(personal_information_page_two, R.anim.push_left_in).setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            //2、隐藏布局1
                            personal_information_page_one.setVisibility(View.GONE);
                            back = setTitleLeft(getString(R.string.personal_information_two));
                            //设置显示的图片
                            back.setImageResource(R.mipmap.mrkj_mrkjback_black);
                            //设置返回上一步的点击事件
                            back.setOnClickListener(MainActivity.this);
                            Toast.makeText(MainActivity.this, "请设置身高与体重信息！", Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                }else {
                    Toast.makeText(this,"请将相关信息填写完整！",Toast.LENGTH_SHORT).show();
                }
                break;
            //======================================= 完善资料2/2 =======================================
            case R.id.left_btn://返回上一步
                personal_information_page_two.setVisibility(View.GONE);
                showAnimation(personal_information_page_two, R.anim.push_left_out);
                personal_information_page_one.setVisibility(View.VISIBLE);
                showAnimation(personal_information_page_one, R.anim.wave_scale).setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        setTitle(getString(R.string.personal_information_one));
                    }
                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
                Toast.makeText(this,"返回上一步！",Toast.LENGTH_SHORT).show();
                break;
            case R.id.walk://先去逛逛
                saveMessageTwo();
                Toast.makeText(this,"先去逛逛！",Toast.LENGTH_SHORT).show();
                Intent functionIntent = new Intent(this,FunctionActivity.class);
                startActivity(functionIntent);
                finish();
                break;
            case R.id.make://制定计划
                saveMessageTwo();
                Toast.makeText(this,"制定计划！",Toast.LENGTH_SHORT).show();
                Intent PlanningIntent = new Intent(this,PlanningActivity.class);
                startActivity(PlanningIntent);
                finish();
                break;
            default:
                break;
        }
    }

    /**
     * 存入第一部分资料
     */
    private void saveMessageOne(){
        SaveKeyValues.putStringValues("gender", gender_str);//性别
        SaveKeyValues.putStringValues("nick",nick_str);//昵称
        SaveKeyValues.putStringValues("birthday",birthday_str);//生日日期
        SaveKeyValues.putIntValues("birth_year",year);
        SaveKeyValues.putIntValues("birth_month",month);
        SaveKeyValues.putIntValues("birth_day",day);
        SaveKeyValues.putStringValues("height_str",height_str);//身高带单位的文字
        SaveKeyValues.putIntValues("height", Integer.parseInt(height_str.substring(0,height_str.length()-2)));//身高数值
        SaveKeyValues.putIntValues("age",custom_age);//年龄
    }
    /**
     * 存入第二部分资料
     */
    private void saveMessageTwo(){
        SaveKeyValues.putIntValues("weight",weight);//体重值
        SaveKeyValues.putStringValues("weight_str", weight_str);//体重信息
        SaveKeyValues.putIntValues("length", length);//步长值
        SaveKeyValues.putStringValues("length_str", length_str);//步长信息
        SaveKeyValues.putIntValues("count",1);//用于判断不是第一次启动
    }
    /**
     * 获取日历信息
     * @param map
     */
    @Override
    public void getMessage(Map<String, Object> map) {
        year = (int) map.get("year");
        month = (int) map.get("month");
        day = (int) map.get("day");
        custom_age = Integer.parseInt(map.get("age").toString());
        input_birthday.setText(year + getString(R.string.year) + month + getString(R.string.month) + day + getString(R.string.day));
    }

    /**
     * 焦点监听
     * @param v
     * @param hasFocus
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()){
            case R.id.input_nick:
                if (!nextShow){
                    showNextBtn();
                }
                break;
            case R.id.input_birthday:
                openPickerOrClose(hasFocus);
                break;
            case R.id.input_height:
                openHeightPickerOrClose(hasFocus);
                break;
            default:
                break;
        }
    }

    /**
     * 显示或隐藏日期选择器
     * @param flag
     */
    private void openPickerOrClose(boolean flag){
        if (flag){
            Log.e(TAG, "获取生日-->获取焦点了");
            hideKeyBoard();
            choose_date.setVisibility(View.VISIBLE);
            hideNextBtn();
            showAnimation(choose_date, R.anim.push_up_in);
            closeDataPicker = true;
        }else {
            if (closeDataPicker == true){
                showAnimation(choose_date, R.anim.push_up_out);
                choose_date.setVisibility(View.GONE);
                showNextBtn();
                closeDataPicker = false;
            }
        }
    }
    /**
     * 显示或隐藏身高选择器
     * @param flag
     */
    private void openHeightPickerOrClose(boolean flag){
        if (flag){
            Log.e(TAG, "获取身高-->获取焦点了");
            hideKeyBoard();
            choose_height.setVisibility(View.VISIBLE);
            hideNextBtn();
            showAnimation(choose_height, R.anim.push_left_in);
            closeHeightPicker = true;
        }else {
            if (closeHeightPicker == true){
                showAnimation(choose_height,R.anim.push_left_out);
                choose_height.setVisibility(View.GONE);
                showNextBtn();
                closeHeightPicker = false;
            }
        }
    }
    /**
     * 显示下一步按钮
     */
    private void showNextBtn(){
        if (closeDataPicker == false && closeHeightPicker== false){
            next_action.setVisibility(View.VISIBLE);
            showAnimation(next_action, R.anim.fade_in);
            nextShow = true;
        }
    }

    /**
     * 隐藏下一步按钮
     */
    private void hideNextBtn(){
        showAnimation(next_action, R.anim.fade_out);
        next_action.setVisibility(View.INVISIBLE);
        nextShow = false;
    }

    /**
     * 设置动画
     * @param view
     * @param animID
     */
    private Animation showAnimation(View view,int animID){
        Animation animation = AnimationUtils.loadAnimation(this,animID);
        view.setAnimation(animation);
        animation.start();
        return animation;
    }

    /**
     * 获取身高信息
     * @param text
     */
    @Override
    public void onSelect(String text) {
        input_height.setText(text + getString(R.string.cm));
    }

    /**
     * 隐藏输入键盘
     */
    private void  hideKeyBoard(){
       ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(MainActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 屏蔽返回键
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            return false;
        }
        return false;
    }
}
