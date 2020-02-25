package mrkj.healthylife.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.File;
import java.util.Map;

import mrkj.healthylife.R;
import mrkj.healthylife.base.BaseActivity;
import mrkj.healthylife.utils.DateUtils;
import mrkj.healthylife.utils.GetPictureFromLocation;
import mrkj.healthylife.utils.SaveKeyValues;
import mrkj.library.wheelview.circleimageview.CircleImageView;

/**
 * 更改用户信息
 */
public class CompileDetailsActivity extends BaseActivity implements View.OnClickListener{
    private static final int PHOTO_REQUEST_CAMERA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_GALLERY2 = 4;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";// 图片名称
    //1、更换头像
    private CircleImageView head_image;//显示头像
    private TextView change_image;//更换头像
    private String path;//头像的路径
    private File tempFile;//图片路径
    //2、修改昵称
    private String nick_str;//用户昵称
    private EditText change_nick;//修改昵称
    //3、修改性别
    private RadioGroup change_gender;//更改性别
    private String sex_str;//性别
    //4、修改生日
    private TextView change_birthDay;//更改
    private String date;
    //生日日期
    private int birth_year;
    private int birth_month;
    private int birth_day;
    //当日日期
    private int now_year;
    private int now_month;
    private int now_day;
    //5、修改身高
    private EditText change_height;
    private int height;
    //6、修改体重
    private EditText change_weight;
    private int weight;
    //7、修改步长
    private EditText change_length;
    private int length;
    //用户年龄
    //确定修改
    private Button change_OK_With_Save;//确定保存
    @Override
    protected void setActivityTitle() {
        initTitle();
        setTitle("更改个人信息", this);
        setMyBackGround(R.color.watm_background_gray);
        setTitleTextColor(R.color.theme_blue_two);
        setTitleLeftImage(R.mipmap.mrkj_back_blue);
        setResult(RESULT_OK);
    }

    @Override
    protected void getLayoutToView() {
        setContentView(R.layout.activity_compile_details);
    }

    @Override
    protected void initValues() {
        path = SaveKeyValues.getStringValues("path","path");
        nick_str = SaveKeyValues.getStringValues("nick","未填写");
        sex_str = SaveKeyValues.getStringValues("gender","男");
        //获取今日日期
        getTodayDate();
        birth_year = SaveKeyValues.getIntValues("birth_year",now_year);
        birth_month = SaveKeyValues.getIntValues("birth_month",now_month);
        birth_day = SaveKeyValues.getIntValues("birth_day",now_day);
        date = birth_year+"-"+birth_month+"-"+birth_day;

        height = SaveKeyValues.getIntValues("height",0);
        weight = SaveKeyValues.getIntValues("weight",0);
        length = SaveKeyValues.getIntValues("length",0);
    }

    /**
     * 获取当日日期
     */
    private void getTodayDate() {
        Map<String,Object> map = DateUtils.getDate();
        now_year = (int) map.get("year");
        now_month = (int) map.get("month");
        now_day = (int) map.get("day");
    }

    @Override
    protected void initViews() {
        //1、更换头像
        head_image = (CircleImageView) findViewById(R.id.head_pic);
        change_image = (TextView) findViewById(R.id.change_image);
        //2、更换名称
        change_nick = (EditText) findViewById(R.id.change_nick);
        //3、更改性别
        change_gender = (RadioGroup) findViewById(R.id.change_gender);
        //4、更改生日
        change_birthDay = (TextView) findViewById(R.id.change_date);
        //确定并退出界面
        change_OK_With_Save = (Button) findViewById(R.id.change_ok);

        //修改参数
        change_height = (EditText) findViewById(R.id.change_height);
        change_weight = (EditText) findViewById(R.id.change_weight);
        change_length = (EditText) findViewById(R.id.change_length);
    }

    @Override
    protected void setViewsListener() {
        change_image.setOnClickListener(this);
        change_OK_With_Save.setOnClickListener(this);
        change_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                hideKeyBoard();
                switch (checkedId) {
                    case R.id.change_girl:
                        sex_str = "女";
                        break;
                    case R.id.change_boy:
                        sex_str = "男";
                        break;
                    default:
                        break;
                }
            }
        });
        change_birthDay.setOnClickListener(this);
    }

    @Override
    protected void setViewsFunction() {
        //1、设置更换头像
        if (!"path".equals(path)) {
            Log.e("图片路径", path);
            head_image.setImageBitmap(BitmapFactory.decodeFile(path));
        }
        change_nick.setHint(nick_str);
        change_nick.setHintTextColor(getResources().getColor(R.color.btn_gray));
        change_height.setHint(String.valueOf(height));
        change_height.setHintTextColor(getResources().getColor(R.color.btn_gray));
        change_length.setHint(String.valueOf(length));
        change_length.setHintTextColor(getResources().getColor(R.color.btn_gray));
        change_weight.setHint(String.valueOf(weight));
        change_weight.setHintTextColor(getResources().getColor(R.color.btn_gray));
    }

    /**
     * 点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        hideKeyBoard();
        switch (v.getId()){
            case R.id.change_image://更换头像
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("选择图片");
                builder.setMessage("可以通过相册和照相来修改默认图片！");
                builder.setPositiveButton("图库", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tempFile = new File(Environment.getExternalStorageDirectory(), PHOTO_FILE_NAME);
                        gallery();
                    }
                });
                builder.setNegativeButton("拍照", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tempFile = new File(Environment.getExternalStorageDirectory(), PHOTO_FILE_NAME);
                        camera();
                    }
                });
                builder.create();//创建
                builder.show();//显示
                break;
            case R.id.change_date://更爱日期-->更改年龄
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        birth_day = year;
                        birth_month = monthOfYear + 1;
                        birth_day = dayOfMonth;
                        date = birth_year+"-"+birth_month+"-"+birth_day;
                        change_birthDay.setText(date);
                    }
                },birth_year,birth_month - 1,birth_day);
                datePickerDialog.setTitle("请设置生日日期");
                datePickerDialog.show();
                break;
            case R.id.change_ok://保存退出
                if (tempFile != null){
                    SaveKeyValues.putStringValues("path",tempFile.getPath());//保存图片的路径
                }
                if(!"".equals(change_nick.getText().toString())) {
                    SaveKeyValues.putStringValues("nick", change_nick.getText().toString());//保存昵称
                }
                SaveKeyValues.putStringValues("gender", sex_str);//保存性别
                SaveKeyValues.putStringValues("birthday", birth_year + "年" + birth_month + "月" + birth_day + "日");//保存生日日期
                SaveKeyValues.putIntValues("birth_year", birth_year);
                SaveKeyValues.putIntValues("birth_month", birth_month);
                SaveKeyValues.putIntValues("birth_day", birth_day);
                SaveKeyValues.putIntValues("age", now_year - birth_year);//保存年龄年龄
                if (!"".equals(change_height.getText().toString())){
                    SaveKeyValues.putIntValues("height", Integer.parseInt(change_height.getText().toString().trim()));//保存身高
                }
                if (!"".equals(change_length.getText().toString())){
                    SaveKeyValues.putIntValues("length", Integer.parseInt(change_length.getText().toString().trim()));//保存步长
                }
                if (!"".equals(change_weight.getText().toString())){
                    SaveKeyValues.putIntValues("weight", Integer.parseInt(change_weight.getText().toString().trim()));//保存体重
                }
                finish();
                break;
            default:
                break;
        }
    }
    /**
     * 从相册获取
     */
    public void gallery() {
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);//ACTION_OPEN_DOCUMENT
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/jpeg");
        if(android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.KITKAT){
            startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
        }else{
            startActivityForResult(intent, PHOTO_REQUEST_GALLERY2);
        }
    }

    /**
     * 从相机获取
     */
    public void camera() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        // 判断存储卡是否可以用，可用进行存储
        if (hasSdcard()) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT,
                    Uri.fromFile(new File(Environment.getExternalStorageDirectory(), PHOTO_FILE_NAME)));
        }
        startActivityForResult(intent, PHOTO_REQUEST_CAMERA);
    }

    /**
     * 判断SD卡是否可用
     *
     * @return
     */
    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 通过返回的值来设置图片
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PHOTO_REQUEST_GALLERY2) {
            if (data != null) {
                // 得到图片的全路径
                String path = GetPictureFromLocation.selectImage(getApplicationContext(),data);
                crop(Uri.parse("file://"+path));
            }

        }  else if (requestCode == PHOTO_REQUEST_GALLERY) {
            if (data != null) {
                // 得到图片的全路径
                String path = GetPictureFromLocation.getPath(getApplicationContext(), data.getData());
                crop(Uri.parse("file://"+path));
            }

        }else if (requestCode == PHOTO_REQUEST_CAMERA) {
            crop(Uri.fromFile(tempFile));
        }else if (requestCode == PHOTO_REQUEST_CUT) {
            try {
                Bitmap bitmap = BitmapFactory.decodeFile(tempFile.getPath());
                Log.e("uri", Uri.fromFile(tempFile).toString());
                head_image.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 隐藏输入键盘
     */
    private void  hideKeyBoard(){
        ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(CompileDetailsActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 剪切图片
     *
     * @function:
     * @author:Jerry
     * @date:2013-12-30
     * @param uri
     */
    private void crop(Uri uri) {
        Log.e("URI", uri.getPath());
        Log.e("URI", uri.toString());
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		// 裁剪框的比例，1：1
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// 裁剪后输出图片的尺寸大小
		intent.putExtra("outputX", 150);
		intent.putExtra("outputY", 150);
        intent.putExtra("scale", true);//黑边
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
		// 图片格式
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);// true:不返回uri，false：返回uri
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

}
