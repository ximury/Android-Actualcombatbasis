package mrkj.flowersdemo.ui.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import mrkj.flowersdemo.R;
import mrkj.flowersdemo.application.MyDataHelper;
import mrkj.flowersdemo.db.MyDataUtils;
import mrkj.flowersdemo.ui.base.BaseActivity;
import mrkj.flowersdemo.ui.bean.Quotes;
import mrkj.flowersdemo.ui.utils.FileIOUtils;
import mrkj.flowersdemo.ui.utils.L;
import mrkj.flowersdemo.ui.utils.SaveKeyValues;
import mrkj.flowersdemo.view.ShowFlower;

/**
 * 种花小屋
 */
public class PlantFlowerBoxActivity extends BaseActivity implements View.OnClickListener{

    private TextView showFlowerCount;//显示花的个数的控件
    private int flowerCount;//花的数量
    private ShowFlower showFlower;//显示花的个数
    private int input_count;//分享的个数
    private int now_plant_counts;//刚刚种的花的个数
    //显示名人名言
    private ArrayList<Quotes> quotesArrayList;
    private TextView show_quotes,show_author;
    private Random random;//用于获取随机数
    private int randomNumber;//获取的随机数
    //初始化想要显示的图片数组
    private String[] indexArray;//索引数组
    private Bitmap[] want_show_bitmap;//想要展示的花的图片
    //设布局
    @Override
    protected int setChildContentView() {
        return R.layout.activity_plant_flower_box;
    }
    //设置功能
    @Override
    protected void onCreateChild() {
        setTitleBar();//设置标题
        initView();//初始化控件
        initData();//初始化数据
        setView();//设置控件
    }

    /**
     * 初始化控件
     */
    private void initView() {
        //显示控件的个数
        showFlowerCount = (TextView) findViewById(R.id.flower_counts);
        //显示花
        showFlower = (ShowFlower) findViewById(R.id.show_flowers);

        //显示名言警句
        show_quotes = (TextView) findViewById(R.id.show_quotes);
        show_author = (TextView) findViewById(R.id.show_author);
    }
    /**
     * 初始化数据
     */
    private void initData(){
        //获取花的数量
        flowerCount = getIntent().getIntExtra("flowersCount",0);
        now_plant_counts = getIntent().getIntExtra("now_plant_counts",0);
        //将这个值存入xml文件中-->种植总数
        SaveKeyValues.putIntValues("all_plant_flowers_counts" ,
                (SaveKeyValues.getIntValues("all_plant_flowers_counts",
                        0) + now_plant_counts));
        //更新数据
        MyDataUtils.upDateData(this,"date","plant",now_plant_counts);
        //获取名言警句的数据集合
        quotesArrayList = MyDataHelper.getInstance().getQuotesData();
        random = new Random();
        randomNumber = random.nextInt(quotesArrayList.size());
        //获取储存的索引值
        String string = SaveKeyValues.getStringValues("showFlowerValues",null);
        setShowBitmaps(string);
    }

    /**
     * 根据索引值设置图片
     * @param string
     */
    private void setShowBitmaps(String string){
        if (string != null){
            indexArray = string.split(",");
            L.e("索引值为", Arrays.toString(indexArray));
        }
        //通过索引值设置显示的图片（支取所以只前10个）
        if (indexArray != null){
            want_show_bitmap = new Bitmap[indexArray.length > 10 ? 10:indexArray.length];
            for (int i = 0;i < want_show_bitmap.length;i++){
                //获取相应的图片
                want_show_bitmap[i] = MyDataHelper.getInstance()
                        .getBitmapArray(this)[Integer.parseInt(indexArray[i])];
            }
        }
    }
    /**
     * 设置控件
     */
    private void setView(){
        //设置显示花的数量
        showFlowerCount.setText(flowerCount + "");
        showFlower.setShowFlowersCounts(flowerCount);
        //设置显示的图片
        if (want_show_bitmap != null){
            showFlower.setShowFlowerArray(want_show_bitmap);
        }
        //根据随机数显示名言警句
        show_quotes.setText(quotesArrayList.get(randomNumber).getQuotes());
        show_author.setText(quotesArrayList.get(randomNumber).getAuthor());
    }
    /**
     * 设置标题的点击事件
     * @param leftView
     * @param title
     * @param rightView
     */
    @Override
    public void setTitleLeftImgWithTitleRightImg(View leftView, View title, View rightView) {
        //对应着标题栏右侧的三个控件
        leftView.setOnClickListener(this);
        title.setOnClickListener(this);
        rightView.setOnClickListener(this);
    }
    /**
     * 点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.right_image1://分享花朵
//                showDialogToSelectShareFlowerCount();//弹窗显示分享-->不能选择花的种类直接分享-->以舍弃
                startActivityForResult(new Intent(this,SelectorShareFlowerActivity.class),1000);
                break;
            case R.id.right_image2://历史记录
                startActivity(new Intent(this,HistoryActivity.class));
                break;
            case R.id.right_image3://规则说明
                startActivity(new Intent(this,IllustrateActivity.class));
                break;
            default:
                break;
        }
    }

    /**
     * 返回
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == RESULT_OK){
            L.e("返回成功",requestCode + "<-->" + resultCode);
            int share_flower_count = data.getIntExtra("share_flower_counts",0);
            String share_flower_index = data.getStringExtra("show_share_flower");
            String share_flower_data = data.getStringExtra("share_flower_data");
            L.e("分享的花的总数",share_flower_count + "");
            L.e("分享的花的索引",share_flower_index + "");
            L.e("分享的花的数据",share_flower_data + "");
            setShowBitmaps(share_flower_index);
            //刷新控件
            showFlower.setBackgroundColor(0xffffffff);//设置背景为白色
            showFlower.setImageResource(R.mipmap.mrkj_flowerpot_drawbackground);//设置图片
            showFlower.setShowFlowerArray(want_show_bitmap);//设置将要显示的花
            showFlower.setShowFlowersCounts(share_flower_count);//设置显示花的个数最大为10
            showFlower.invalidate();//刷新控件显示的样式
            //获取截图跳转界面
            try {
                Bitmap bitmap = getBitmap(showFlower);//获取图像
                String path = FileIOUtils.writeFile(getFilesDir(),"share_image.jpg",bitmap);
                L.e("图片的路径","PATH-->" + path);
                startActivity(new Intent(PlantFlowerBoxActivity.this,ShareActivity.class)
                        .putExtra("image_path",path).putExtra("share_flowers",share_flower_count)
                        .putExtra("update_data" , share_flower_data));
                finish();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取View的显示图片
     */
    private Bitmap getBitmap(View view) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }


    //-------------------------- 设置 --------------------------
    /**
     * 全屏
     * @return
     */
    @Override
    public boolean setActivityFullScreen() {
        return true;
    }

    /**
     * 添加标题栏
     * @return
     */
    @Override
    public boolean isAddTitle() {
        return true;
    }

    /**
     * 设置标题
     */
    private void setTitleBar(){
        //设置界面的背景
        setActivityBackGround(R.mipmap.mrkj_garden);
        //设置标题栏的背景
        setTitleBackgroundColor(Color.TRANSPARENT);
        //设置标题栏
        setTitle(R.mipmap.mrkj_com_back
                ,R.mipmap.mrkj_flowerpot_share
                ,R.mipmap.mrkj_flowerpot_record
                ,R.mipmap.mrkj_aboutus_feedback);
    }


    /**
     * 用弹窗去设置分享花的个数-->舍弃
     */
    private void showDialogToSelectShareFlowerCount(){
        if (flowerCount == 0){//没有花
            Toast.makeText(this,"请先种出自己的花",Toast.LENGTH_SHORT).show();
            return;
        }
        View view = getLayoutInflater().
                inflate(R.layout.select_share_flower_count_layout,null);
        //选择花的数量
        EditText select_count = (EditText) view.findViewById(R.id.select_flower_count);
        //用于提示
        final TextView hint_text = (TextView) view.findViewById(R.id.hint_message);
        hint_text.setVisibility(View.INVISIBLE);//设置默认隐藏
        //设置输入框的文本改变监听
        select_count.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {//文本被改变
                if ("".equals(s.toString())){
                    return;
                }
                int count = Integer.parseInt(s.toString());
                if (count > 0 && count <= flowerCount){//输入值有效
                    hint_text.setVisibility(View.INVISIBLE);
                    input_count = count;
                }else {//输入值无效
                    hint_text.setVisibility(View.VISIBLE);
                    input_count = 0;
                }
            }
        });
        //弹窗
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择分享花朵的个数");
        builder.setView(view);
        builder.setPositiveButton("确认", new DatePickerDialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (input_count == 0){
                    Toast.makeText(PlantFlowerBoxActivity.this,"没有选择分享花的个数",
                            Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        //跳转界面
                        showFlower.setShowFlowersCounts(input_count);//设置显示花的个数最大为10
                        showFlower.invalidate();//刷新控件显示的样式
                        Bitmap bitmap = getBitmap(showFlower);//获取图像
                        String path = FileIOUtils.writeFile(getFilesDir(),"share_image.jpg",bitmap);
                        L.e("图片的路径","PATH-->" + path);
                        startActivity(new Intent(PlantFlowerBoxActivity.this,ShareActivity.class)
                                .putExtra("image_path",path).putExtra("share_flowers",input_count));
                        finish();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        builder.setNegativeButton("取消", new DatePickerDialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create();
        builder.show();
    }
}
