package com.mingrisoft.fieldassistant.signed;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.mingrisoft.fieldassistant.home.BaseActivity;
import com.mingrisoft.fieldassistant.home.BaseApplication;
import com.mingrisoft.fieldassistant.R;
import com.mingrisoft.fieldassistant.cache.AllLruCacheLoad;
import com.mingrisoft.fieldassistant.entity.ChooseDateEntity;
import com.mingrisoft.fieldassistant.entity.SignedEntity;
import com.mingrisoft.fieldassistant.singleton.QueueSingleton;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class SignedActivity extends BaseActivity implements View.OnClickListener {

    private String date = null;// 设置默认选中的日期  格式为 “2014-04-05” 标准DATE格式
    private TextView popupwindow_calendar_month, tishi;
    private KCalendar calendar;
    private List<String> list = new ArrayList<String>(); //设置标记列表

    private NetworkImageView upImage, downImage, upImageArd, downImageArd;
    private Button upButton, downButton;
    private TextView upText, dawnText, upTextArd, downTextArd, dataArd, otherUpText, otherDownText;
    private RelativeLayout rlDown, rltUp, rlDownArd, rlUpArd;
    private SharedPreferences sharedPreferences;

    private String url = BaseApplication.getUrl();
    private SignedEntity signedEntity;
    private ImageButton backBtn;
    String today;
    String month;
    String year;
    String day;

    private GoogleApiClient client;

    private RequestParams params;
    private HttpUtils httpUitil;
    private RequestQueue queue;

    AllLruCacheLoad cache;
    ImageLoader imageloader;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signed_main_layout);
        params = new RequestParams();
        httpUitil = new HttpUtils();
        init();

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        chooseToday(today);
        queue = Volley.newRequestQueue(this);
        cache = new AllLruCacheLoad();
        imageloader = new ImageLoader(queue, cache);
    }

    private void addData(String yearMonth) {
        StringRequest request = new StringRequest(url + "tfAttence_list.do?"
                + "id=" + sharedPreferences.getString("userid", "") + "&yearMonth=" + yearMonth, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                signedEntity = gson.fromJson(response, SignedEntity.class);
                //把解析的数据显示出来
                setGsonData(signedEntity);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SignedActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
            }
        });
        QueueSingleton.getInstance().getQueue().add(request);
    }

    private void setGsonData(SignedEntity signedEntity) {
        AllLruCacheLoad cache = new AllLruCacheLoad();
        final ImageLoader imageloader = new ImageLoader(QueueSingleton.getInstance().getQueue(), cache);
        int k = 0;
        getTody();
        for (int i = 0; i < signedEntity.getTfAttenceMain().size(); i++) {
            SignedEntity.TfAttenceMainBean bean = signedEntity.getTfAttenceMain().get(i);
            if (bean.getAmStart() == null
                    || bean.getAmStartr().equals("1")
                    || bean.getAmEnd() == null
                    || bean.getAmEndr().equals("1")) {
                list.add(k, bean.getAmData());
            }
//            if (bean.getAmData().equals(today)) {
//                if (bean.getAmStart() != null && bean.getAmStart().equals("1")) {
//                    upButtonArdCl();
//
//                }
//                if (bean.getAmEnd() != null && bean.getAmEnd().equals("1")) {
//                    downButtonArdCl();
//                }
//            }
        }
        calendar.addMarks(list, 0);
    }

    private void getTody() {
        Date date = new Date();
        int years = date.getYear() + 1900;
        year = years + "";
        int months = date.getMonth() + 1;
        int days = date.getDate();
        if (months < 10) {
            month = "0" + months;
        } else {
            month = months + "";
        }
        if (days < 10) {
            day = "0" + days;
        } else {
            day = days + "";
        }
        today = year + "-" + month + "-" + day;

    }

    private void downButtonArdCl() {
        downButton.setClickable(false);
        downButton.setText("已打卡");

    }

    private void upButtonArdCl() {
        upButton.setClickable(false);
        upButton.setText("已打卡");
        rlDown.setVisibility(View.VISIBLE);
    }

    private void init() {
        sharedPreferences = getSharedPreferences("share", Context.MODE_PRIVATE);

        popupwindow_calendar_month = (TextView) findViewById(R.id.popupwindow_calendar_month);
        calendar = (KCalendar) findViewById(R.id.popupwindow_calendar);
        upImage = (NetworkImageView) findViewById(R.id.up_image);
        upText = (TextView) findViewById(R.id.up_location);
        upButton = (Button) findViewById(R.id.up_btn);
        downImage = (NetworkImageView) findViewById(R.id.down_image);
        dawnText = (TextView) findViewById(R.id.down_location);
        downButton = (Button) findViewById(R.id.down_btn);
        rlDown = (RelativeLayout) findViewById(R.id.down_rl);
        rltUp = (RelativeLayout) findViewById(R.id.up_rl);
        rlDownArd = (RelativeLayout) findViewById(R.id.down_rl_ard);
        rlUpArd = (RelativeLayout) findViewById(R.id.up_rl_ard);
        upImageArd = (NetworkImageView) findViewById(R.id.up_image_ard);
        downImageArd = (NetworkImageView) findViewById(R.id.down_image_ard);
        upTextArd = (TextView) findViewById(R.id.up_location_ard);
        downTextArd = (TextView) findViewById(R.id.down_location_ard);
        dataArd = (TextView) findViewById(R.id.data_ard);
        tishi = (TextView) findViewById(R.id.tishi);
        upImage.setOnClickListener(this);
        downImage.setOnClickListener(this);
        upButton.setOnClickListener(this);
        downButton.setOnClickListener(this);
        backBtn = (ImageButton) findViewById(R.id.signed_back);
        backBtn.setOnClickListener(this);
        otherUpText = (TextView) findViewById(R.id.other_up_ard);
        otherDownText = (TextView) findViewById(R.id.other_down_ard);

    }

    @Override
    protected void onStart() {
        super.onStart();
        client.connect();
        getLocation();
        popupwindow_calendar_month.setText(calendar.getCalendarYear() + "年"
                + calendar.getCalendarMonth() + "月");
        String calenderMonth;
        if (calendar.getCalendarMonth() < 10) {
            calenderMonth = "0" + calendar.getCalendarMonth();
        } else {
            calenderMonth = calendar.getCalendarMonth() + "";
        }
        addData(calendar.getCalendarYear() + "-" + calenderMonth + "");

        if (null != date) {
            int years = Integer.parseInt(date.substring(0,
                    date.indexOf("-")));
            int month = Integer.parseInt(date.substring(
                    date.indexOf("-") + 1, date.lastIndexOf("-")));
            popupwindow_calendar_month.setText(years + "年" + month + "月");
            calendar.showCalendar(years, month);
        }

        //把所有的异常日期都标记出来
        calendar.addMarks(list, 0);
        //监听所选中的日期
        calendar.setOnCalendarClickListener(new KCalendar.OnCalendarClickListener() {

            public void onCalendarClick(int row, int col, String dateFormat) {
                int month = Integer.parseInt(dateFormat.substring(
                        dateFormat.indexOf("-") + 1,
                        dateFormat.lastIndexOf("-")));
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("clickdaty", dateFormat);
                editor.commit();
                //点击后，获取到点击那天的签到信息
                getDateMsg(dateFormat);
                if (calendar.getCalendarMonth() - month == 1//跨年跳转
                        || calendar.getCalendarMonth() - month == -11) {
                    calendar.lastMonth();

                } else if (month - calendar.getCalendarMonth() == 1 //跨年跳转
                        || month - calendar.getCalendarMonth() == -11) {
                    calendar.nextMonth();

                } else {
                    calendar.removeAllBgColor();
                    date = dateFormat;//最后返回给全局 date
                }
            }
        });

        //监听当前月份
        calendar.setOnCalendarDateChangedListener(new KCalendar.OnCalendarDateChangedListener() {
            public void onCalendarDateChanged(int year, int month) {
                popupwindow_calendar_month.setText(year + "年" + month + "月");
                String calenderMonth;
                if (calendar.getCalendarMonth() < 10) {
                    calenderMonth = "0" + month;
                } else {
                    calenderMonth = month + "";
                }
                addData(year + "-" + calenderMonth + "");
            }
        });
        Log.e("uri", BaseApplication.getTempFile().toString());
        if (BaseApplication.getTempFile() != null){
            upImage.setImageURI(Uri.parse(BaseApplication.getTempFile().toString()));
            downImage.setImageURI(Uri.parse(BaseApplication.getTempFile().toString()));
        }
        getTody();
    }

    private void getDateMsg(String dateFormat) {
        //如果点击的日期是今天则显示打卡与提示
        //否则隐藏打卡的显示，显示之前的打卡信息
        if (dateFormat.equals(today)) {
            //TODO
            //判断是否要显示签退的的布局
            tishi.setVisibility(View.VISIBLE);
            rlDown.setVisibility(View.GONE);
            rltUp.setVisibility(View.VISIBLE);
            dataArd.setVisibility(View.GONE);
            rlDownArd.setVisibility(View.GONE);
            rlUpArd.setVisibility(View.GONE);
            chooseToday(dateFormat);
        } else {
            tishi.setVisibility(View.GONE);
            rltUp.setVisibility(View.GONE);
            rlDown.setVisibility(View.GONE);
            rlDownArd.setVisibility(View.VISIBLE);
            rlUpArd.setVisibility(View.VISIBLE);
            dataArd.setVisibility(View.VISIBLE);
            dataArd.setText(dateFormat);
            chooseDate(dateFormat);
        }
    }

    private void chooseToday(String date) {
        String path = url + "tfAttence_view.do?" + "id=" + sharedPreferences.getString("userid", "")
                + "&date=" + date;
        StringRequest request = new StringRequest(path, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                ChooseDateEntity chooseDateEntity = gson.fromJson(response, ChooseDateEntity.class);
                //把解析的数据显示出来
                if (chooseDateEntity.getTfAttenceMain() != null && chooseDateEntity.getTfAttenceMain().size() > 0) {
                    setTodayData(chooseDateEntity);
                } else {
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SignedActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
            }
        });
        QueueSingleton.getInstance().getQueue().add(request);
    }

    private void setTodayData(ChooseDateEntity chooseDateEntity) {

        ChooseDateEntity.TfAttenceMainBean mainBean = chooseDateEntity.getTfAttenceMain().get(0);
        if (mainBean.getAmStart() != null && mainBean.getAmStart().equals("1")) {
            upButtonArdCl();
        }
        if (mainBean.getAmEnd() != null && mainBean.getAmEnd().equals("1")) {
            downButtonArdCl();
        }
        if (mainBean.getAmSplace() != null) {
            upText.setText(mainBean.getAmSplace().toString());
        } else {
            upText.setText("");
        }
        if (mainBean.getAmEplace() != null) {
            dawnText.setText(mainBean.getAmEplace().toString());
        } else {
            dawnText.setText("");
        }
        if (mainBean.getAmSphoto() != null) {
            upImage.setErrorImageResId(R.mipmap.loadingqqq);
            upImage.setDefaultImageResId(R.mipmap.loading);
            upImage.setImageUrl(BaseApplication.getImageUrl() + mainBean.getAmSphoto(), imageloader);
            upImage.setClickable(false);
        } else {

            upImage.setImageUrl(null, null);
        }
        if (mainBean.getAmEphoto() != null) {
            downImage.setErrorImageResId(R.mipmap.loadingqqq);
            downImage.setDefaultImageResId(R.mipmap.loading);
            downImage.setImageUrl(BaseApplication.getImageUrl() + mainBean.getAmEphoto(), imageloader);
            downImage.setClickable(false);
        } else {
            downImage.setImageUrl(null, null);

        }
    }

    private void chooseDate(String date) {
        String path = url + "tfAttence_view.do?" + "id=" + sharedPreferences.getString("userid", "")
                + "&date=" + date;
        StringRequest request = new StringRequest(path, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                ChooseDateEntity chooseDateEntity = gson.fromJson(response, ChooseDateEntity.class);
                //把解析的数据显示出来
                if (chooseDateEntity.getTfAttenceMain() != null && chooseDateEntity.getTfAttenceMain().size() > 0) {
                    setOtherData(chooseDateEntity);
                } else {
                    otherUpText.setText("");
                    otherDownText.setText("");
                    upTextArd.setText("");
                    downTextArd.setText("");
                    upImageArd.setImageUrl(null, null);
                    downImageArd.setImageUrl(null, null);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SignedActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
            }
        });
        QueueSingleton.getInstance().getQueue().add(request);
    }

    private void setOtherData(ChooseDateEntity chooseDateEntity) {
        ChooseDateEntity.TfAttenceMainBean mainBean = chooseDateEntity.getTfAttenceMain().get(0);

        if (mainBean.getAmSplace() != null) {
            upTextArd.setText(mainBean.getAmSplace().toString());
        } else {
            upTextArd.setText("");
        }
        if (mainBean.getAmEplace() != null) {
            downTextArd.setText(mainBean.getAmEplace().toString());
        } else {
            downTextArd.setText("");
        }
        if (mainBean.getAmSphoto() != null) {
            upImageArd.setDefaultImageResId(R.mipmap.loading);
            upImageArd.setErrorImageResId(R.mipmap.loadingqqq);
            upImageArd.setImageUrl(BaseApplication.getImageUrl() + mainBean.getAmSphoto(), imageloader);
        } else {
            upImageArd.setImageUrl(null, null);
        }
        if (mainBean.getAmEphoto() != null) {
            downImageArd.setDefaultImageResId(R.mipmap.loading);
            downImageArd.setErrorImageResId(R.mipmap.loadingqqq);
            downImageArd.setImageUrl(BaseApplication.getImageUrl() + mainBean.getAmEphoto(), imageloader);
        } else {
            downImageArd.setImageUrl(null, null);
        }

        if (chooseDateEntity.getTfAttenceMain().get(0).getAmStartr().equals("0")) {
            otherUpText.setText("正常");
            otherUpText.setTextColor(Color.BLUE);
        } else {
            otherUpText.setText("迟到");
            otherUpText.setTextColor(Color.RED);
        }
        if (chooseDateEntity.getTfAttenceMain().get(0).getAmEndr().equals("0")) {
            otherDownText.setText("正常");
            otherDownText.setTextColor(Color.BLUE);
        } else {
            otherDownText.setText("早退");
            otherDownText.setTextColor(Color.RED);
        }
    }

    //按钮的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.up_image:
                //跳转到添加图片的activity
                addImage();
                break;
            case R.id.down_image:
                //跳转到添加图片的activity
                addImage();
                break;
            case R.id.up_btn:
                String loc = sharedPreferences.getString("alllocation", "未获取到位置信息");
                upText.setText(loc);
                upFileUseXUtills("0", loc);
                break;
            case R.id.down_btn:
                String locc = sharedPreferences.getString("alllocation", "未获取到位置信息");
                dawnText.setText(locc);
                upFileUseXUtills("1", locc);
                break;
            case R.id.signed_back:
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("clickdaty");
                editor.commit();
                finish();
                break;
        }
    }

    private void showTF(String num) {
        if (num.equals("0")) {
            upButtonArdCl();
        }
    }

    private void addImage() {
        Intent intent = new Intent(this, AddImageActivity.class);
        startActivity(intent);
    }


    //往服务器上传图片
    private void upFileUseXUtills(final String num, final String location) {
        String ul = url + "tfAttence_on.do";
        File file = new File(BaseApplication.getTempFile().getPath());
        File fl = new File("/sdcard/myHead.png");
        params.addBodyParameter("filename", fl);
        params.addBodyParameter("id", sharedPreferences.getString("userid", ""));
        params.addBodyParameter("date", sharedPreferences.getString("clickdaty", today));
        params.addBodyParameter("attenceStatus", num);
        params.addBodyParameter("attencePlace", location);
        uploadMethod(num, params, ul);
    }

    public void uploadMethod(final String num, final RequestParams params, final String uploadHost) {
        httpUitil.send(HttpMethod.POST, uploadHost, params, new RequestCallBack<String>() {
            private boolean progressShow = true;
            final ProgressDialog pd = new ProgressDialog(SignedActivity.this);
            @Override
            public void onStart() {
                //上传开始
            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                //上传中

                pd.setCanceledOnTouchOutside(false);
                pd.setOnCancelListener(new DialogInterface.OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface dialog) {
                        progressShow = false;
                    }
                });
                pd.setMessage("正在打卡....");
                pd.show();

            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                //上传成功，这里面的返回值，就是服务器返回的数据
                String result = responseInfo.result.toString().substring(1, 2);
                pd.dismiss();
                if (result.equals("t")) {
                    Toast.makeText(SignedActivity.this, "打卡成功", Toast.LENGTH_SHORT).show();
                    showTF(num);
                    BaseApplication.getTempFile().delete();
                } else {
                    Toast.makeText(SignedActivity.this, "打卡失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                //上传失败
                Toast.makeText(SignedActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseApplication.getTempFile().delete();
    }
}
