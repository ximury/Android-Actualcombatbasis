package com.mingrisoft.fieldassistant.welcome;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.mingrisoft.fieldassistant.home.BaseApplication;
import com.mingrisoft.fieldassistant.R;
import com.mingrisoft.fieldassistant.entity.EnterEntity;
import com.mingrisoft.fieldassistant.singleton.QueueSingleton;


public class EnterActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText editPerson, editCode; // 用于输入用户名和密码
    private ImageView imageView, imageViewClose; //用于判断密码是否可见
    private Button btn;
    String name = null;
    String num = null;
    private boolean autoLogin = false;
    public static String currentUsername;   //用来读取存放到数据库的用户名
    private String currentPassword;         //用来读取存放到数据库的密码
    private SharedPreferences sharedPreferences;
    private EnterEntity enterEntity;        //定义一个实体类 用于来解析后台返回的数据
    private String url = BaseApplication.getUrl() + "login_on.do?";         //此页面的数据接口
    private boolean progressShow = true;
    private ProgressDialog pd;               //用于登录时弹出的dialog等待
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        init();//初始化控件

    }

    private void init() {
        btn = (Button) findViewById(R.id.enter_btn);
        btn.setOnClickListener(this);
        editCode = (EditText) findViewById(R.id.enter_code);
        editPerson = (EditText) findViewById(R.id.enter_name);
        imageView = (ImageView) findViewById(R.id.eye_code);
        imageViewClose = (ImageView) findViewById(R.id.eye_code_close);
        imageView.setVisibility(View.VISIBLE);
        imageViewClose.setVisibility(View.INVISIBLE);
        imageView.setOnClickListener(this);
        imageViewClose.setOnClickListener(this);
        pd =  new ProgressDialog(EnterActivity.this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        /**
         * 登录时先从数据库里面读取用户的用户名和密码
         *
         * 如果不为空则直接登录
         * */
        sharedPreferences = getSharedPreferences("share", MODE_PRIVATE);
        String name = sharedPreferences.getString("username", "");
        String code = sharedPreferences.getString("password", "");
        if (name.length() > 1 && code.length() > 1) {
            editPerson.setText(name);
            editCode.setText(code);
            login(); //执行登录的方法
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.enter_btn:             //登录按钮的监听事件
               showLog();
                login();                    //执行登录的方法
                break;
            case R.id.eye_code:              //使密码可见
                num = String.valueOf(editCode.getText());
                imageView.setVisibility(View.INVISIBLE);
                imageViewClose.setVisibility(View.VISIBLE);
                editCode.setInputType(131073);
                break;
            case R.id.eye_code_close:       //使密码不可见
                imageView.setVisibility(View.VISIBLE);
                imageViewClose.setVisibility(View.INVISIBLE);
                editCode.setInputType(129);
                break;
        }
    }


    /**
     * 该方法是弹出登录时的等待框
     * **/
    private void showLog() {
        pd.setCanceledOnTouchOutside(false);
        pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                progressShow = false;
            }
        });
        pd.setMessage("正在登录....");
        pd.show();   //显示等待框
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (autoLogin) {
            return;
        }
    }

    /**
     * 登录的执行方法
     */
    public void login() {
        showLog();
        currentUsername = editPerson.getText().toString().trim();       //把输入框的用户名字段提取出来
        currentPassword = editCode.getText().toString().trim();         //把输入框的密码字段提取出来

        if (TextUtils.isEmpty(currentUsername)) {                       //判断用户名是否为空
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            pd.dismiss();
            return;
        }
        if (TextUtils.isEmpty(currentPassword)) {                       //判断密码是否为空
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            pd.dismiss();
            return;
        }
        addData(currentUsername, currentPassword);                      //如果均不为空 则执行向服务器请求数据
    }


    /**
     * 向服务器请求数据的方法
     * **/
    private void addData(String name, String code) {

        //把用户名和密码传递给服务器
        StringRequest request = new StringRequest(url + "username=" + name + "&password=" + code, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                    //创建实体类  用来解析登录时服务器返回来的的数据
                enterEntity = gson.fromJson(response, EnterEntity.class);
                //把解析的数据显示出来
                setGsonData(enterEntity);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EnterActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                pd.dismiss();       //等待框消失
            }
        });
        QueueSingleton.getInstance().getQueue().add(request);
    }

    private void setGsonData(EnterEntity enterEntity) {
        if (enterEntity.getId()== null) {
            Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_SHORT).show();        //当返回的数据的id为空时 是用户名或密码错误
            pd.dismiss();
        }else {

            /**
             * 当返回来的数据和输入的用户名一样时则表示登录成功
             *
             *把用户名和密码添加到数据库里面  以便下一次自动登录
             * */
            if (enterEntity.getUsername().equals(currentUsername)){
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("userid",enterEntity.getId());
                editor.putString("username", enterEntity.getUsername());
                editor.putString("password", enterEntity.getPassword());
                editor.putString("name", enterEntity.getName());
                editor.putString("did", enterEntity.getDepartmentId());
                editor.commit();
                Intent intent = new Intent(EnterActivity.this,SplashActivity.class);
                startActivity(intent);      //跳转到主页面
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                pd.dismiss();                //等待框消失
                finish();

            }
        }

    }
}
