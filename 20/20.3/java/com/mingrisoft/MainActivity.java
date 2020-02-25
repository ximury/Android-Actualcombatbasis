package com.mingrisoft;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends Activity {
    private Handler handler; // 定义一个android.os.Handler对象
    private String result = ""; // 定义一个代表显示内容的字符串

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView step = (TextView) findViewById(R.id.text1);   //获取TextView显示单日步数
        final TextView time = (TextView) findViewById(R.id.text2);   //获取TextView显示单日时间
        final TextView heat = (TextView) findViewById(R.id.text3);   //获取TextView显示单日热量
        final TextView km = (TextView) findViewById(R.id.text4);     //获取TextView显示单日公里
        final TextView step1 = (TextView) findViewById(R.id.text5);  //获取TextView显示周步数
        final TextView time1 = (TextView) findViewById(R.id.text6);  //获取TextView显示周时间
        final TextView heat1 = (TextView) findViewById(R.id.text7);  //获取TextView显示周热量
        final TextView km1 = (TextView) findViewById(R.id.text8);    //获取TextView显示周公里

        handler = new Handler() {   //解析返回的JSON串数据并显示
            @Override
            public void handleMessage(Message msg) {
                TextView[][] tv = {{step, time, heat, km}, {step1, time1, heat1, km1}};  //创建TextView二维数组
                try {
                    JSONArray jsonArray = new JSONArray(result);    //将获取的数据保存在JSONArray数组中
                    for (int i = 0; i < jsonArray.length(); i++) {  //通过for循环遍历JSON数据
                        JSONObject jsonObject = jsonArray.getJSONObject(i);  //解析JSON数据
                        tv[i][0].setText(jsonObject.getString("step"));     //获取JSON中的步数值
                        tv[i][1].setText(jsonObject.getString("time"));     //获取JSON中的时间值
                        tv[i][2].setText(jsonObject.getString("heat"));     //获取JSON中的热量值
                        tv[i][3].setText(jsonObject.getString("km"));       //获取JSON中的公里数
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                super.handleMessage(msg);
            }
        };
        new Thread(new Runnable() {  // 创建一个新线程，用于从服务器中获取JSON数据
    public void run() {
        send();     //调用send()方法，用于发送请求并获取JSON数据
        Message m = handler.obtainMessage();    // 获取一个Message
        handler.sendMessage(m);      // 发送消息
    }
}).start();     // 开启线程


    }

    public void send() {  //创建send()方法，实现发送请求并获取JSON数据
        String target = "http://192.168.1.198:8080/example/index.json";    //要发送请求的服务器地址
        URL url;
        try {
            url = new URL(target);  //创建URL对象
            // 创建一个HTTP连接
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setRequestMethod("POST"); // 指定使用POST请求方式
            urlConn.setDoOutput(true); // 从连接中读取数据
            urlConn.setUseCaches(false); // 禁止缓存
            urlConn.setInstanceFollowRedirects(true);    //自动执行HTTP重定向
            InputStreamReader in = new InputStreamReader(
                    urlConn.getInputStream()); // 获得读取的内容
            BufferedReader buffer = new BufferedReader(in); // 获取输入流对象
            String inputLine = null;
            while ((inputLine = buffer.readLine()) != null) {  //通过循环逐行读取输入流中的内容
                result += inputLine;
            }
            in.close();                                         //关闭输入流
            urlConn.disconnect();    //断开连接
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
