package com.mingrisoft;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends Activity {
    private EditText edit_Username;// 定义一个输入用户名的编辑框组件
    private EditText edit_Password;// 定义一个输入密码的编辑框组件
    private Handler handler; // 定义一个android.os.Handler对象
    private String result = ""; // 定义一个代表显示内容的字符串

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit_Username = (EditText) findViewById(R.id.username);     //获取用于输入用户名的编辑框组件
        edit_Password = (EditText) findViewById(R.id.password);     //获取用于输入密码的编辑框组件
        ImageButton btn_Login = (ImageButton) findViewById(R.id.login);    //获取用于登录的按钮控件
        btn_Login.setOnClickListener(new View.OnClickListener() {  //实现单击登录按钮，发送信息与服务器交互
            @Override
            public void onClick(View v) {
                //当用户名、密码为空时给出相应提示
                if ("".equals(edit_Username.getText().toString())
                        || "".equals(edit_Password.getText().toString())) {
                    Toast.makeText(MainActivity.this, "请填写用户名或密码！",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                handler = new Handler() {  //如果服务器返回值为“ok”证明用户名密码正确并跳转登录后界面否则给出相应的提示信息
                    @Override
                    public void handleMessage(Message msg) {
                        if ("ok".equals(result)) { //如果服务器返回值为“ok”，证明用户名、密码输入正确
                            //跳转登录后界面
                            Intent in = new Intent(MainActivity.this, MessageActivity.class);
                            startActivity(in);
                        }else {
                            //用户名、密码错误的提示信息
                            Toast.makeText(MainActivity.this, "请填写正确的用户名和密码！", Toast.LENGTH_SHORT).show();
                        }
                        super.handleMessage(msg);
                    }
                };


                new Thread(new Runnable() {  // 创建一个新线程，用于从网络上获取文件
                    public void run() {
                        send();     //调用send()方法，用于发送用户名、密码到Web服务器
                        Message m = handler.obtainMessage();    // 获取一个Message
                        handler.sendMessage(m);      // 发送消息
                    }
                }).start();     // 开启线程
            }
        });


    }

    public void send() {
        String target = "http://192.168.1.198:8080/example/post.jsp";    //要提交的服务器地址
        URL url;
        try {
            url = new URL(target);  //创建URL对象
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();  // 创建一个HTTP连接
            urlConn.setRequestMethod("POST"); // 指定使用POST请求方式
            urlConn.setDoInput(true); // 向连接中写入数据
            urlConn.setDoOutput(true); // 从连接中读取数据
            urlConn.setUseCaches(false); // 禁止缓存
            urlConn.setInstanceFollowRedirects(true);    //自动执行HTTP重定向
            urlConn.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded"); // 设置内容类型
            DataOutputStream out = new DataOutputStream(
                    urlConn.getOutputStream()); // 获取输出流
            String param = "username="
                    + URLEncoder.encode(edit_Username.getText().toString(), "utf-8")
                    + "&password="
                    + URLEncoder.encode(edit_Password.getText().toString(), "utf-8");    //连接要提交的数据
            out.writeBytes(param);//将要传递的数据写入数据输出流
            out.flush();    //输出缓存
            out.close();    //关闭数据输出流

            if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK) {  //判断是否响应成功
                InputStreamReader in = new InputStreamReader(
                        urlConn.getInputStream()); // 获得读取的内容
                BufferedReader buffer = new BufferedReader(in); // 获取输入流对象
                String inputLine = null;

                while ((inputLine = buffer.readLine()) != null) {  //通过循环逐行读取输入流中的内容
                    result += inputLine;
                }
                in.close();    //关闭字符输入流
            }
            urlConn.disconnect();    //断开连接
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
