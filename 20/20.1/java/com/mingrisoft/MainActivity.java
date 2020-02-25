package com.mingrisoft;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends Activity {
    private EditText content;                    //定义一个输入文本内容的编辑框对象
    private Handler handler;                    //定义一个android.os.Handler对象
    private String result = "";                 //定义一个代表显示内容的字符串
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        content = (EditText) findViewById(R.id.content);    //获取输入文本内容的EditText组件
        final TextView resultTV = (TextView) findViewById(R.id.result);    //获取显示结果的TextView组件
        Button button = (Button) findViewById(R.id.button);    //获取“发表”按钮组件
        button.setOnClickListener(new View.OnClickListener() {  //单击发送按钮，实现读取服务器微博信息
            @Override
            public void onClick(View v) {
                //判断输入内容是否为空，为空给出提示消息，否则访问服务器
                if ("".equals(content.getText().toString())) {
                    Toast.makeText(MainActivity.this, "请输入要发表的内容！",
                            Toast.LENGTH_SHORT).show();    //显示消息提示
                    return;
                }
                handler = new Handler() {  //将服务器中的数据，显示在UI界面中
                    @Override
                    public void handleMessage(Message msg) {
                        if (result != null) {          //如果服务器返回结果不为空
                            resultTV.setText(result); // 显示获得的结果
                            content.setText("");        //清空文本框
                        }
                        super.handleMessage(msg);
                    }
                };

                new Thread(new Runnable() {  // 创建一个新线程，用于发送并读取微博信息
                    public void run() {
                        send();    //调用send()方法，用于发送文本内容到Web服务器
                        Message m = handler.obtainMessage(); // 获取一个Message
                        handler.sendMessage(m); // 发送消息
                    }
                }).start(); // 开启线程
            }
        });

    }

    public void send() {  //创建send()方法，用于建立一个HTTP连接，并将输入的内容发送到Web服务器上，再读取服务器的处理结果
        String target = "";
        target = "http://192.168.1.198:8080/example/get.jsp?content="
                + base64(content.getText().toString().trim());    //要访问的URL地址
        URL url;
        try {
            url = new URL(target);
            HttpURLConnection urlConn = (HttpURLConnection) url
                    .openConnection();    //创建一个HTTP连接
            InputStreamReader in = new InputStreamReader(
                    urlConn.getInputStream()); // 获得读取的内容
            BufferedReader buffer = new BufferedReader(in); // 获取输入流对象
            String inputLine = null;
            //通过循环逐行读取输入流中的内容
            while ((inputLine = buffer.readLine()) != null) {
                result += inputLine + "\n";
            }
            in.close();    //关闭字符输入流对象
            urlConn.disconnect();    //断开连接
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String base64(String content) {  //对字符串进行Base64编码

        try {
            //对字符串进行Base64编码
            content = Base64.encodeToString(content.getBytes("utf-8"), Base64.DEFAULT);
            content = URLEncoder.encode(content, "utf-8");    //对字符串进行URL编码
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return content;
    }


}
