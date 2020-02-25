package mrkj.flowersdemo.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.alibaba.fastjson.JSON;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXImageObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.openapi.IWXAPI;

import java.io.IOException;
import java.util.List;

import mrkj.flowersdemo.R;
import mrkj.flowersdemo.application.MyApplication;
import mrkj.flowersdemo.db.MyDataUtils;
import mrkj.flowersdemo.ui.base.BaseActivity;
import mrkj.flowersdemo.ui.bean.Flower;
import mrkj.flowersdemo.ui.utils.FileIOUtils;
import mrkj.flowersdemo.ui.utils.SaveKeyValues;

/**
 *分享
 */
public class ShareActivity extends BaseActivity implements View.OnClickListener{
    private IWXAPI api;//分享
    private RadioButton pyqShare,wxShare;
    private int share_counts;//分享的个数
    private String share_data;//分享的数据
    private WXShareCallBack shareCallBack;//分享的回调信息
    @Override
    protected int setChildContentView() {
        return R.layout.activity_share;
    }

    @Override
    protected void onCreateChild() {
        setStyle();//初始化界面
        api = MyApplication.api;
        //初始化控件
        ImageView test = (ImageView) findViewById(R.id.image_test);
        pyqShare = (RadioButton) findViewById(R.id.share_pyq);
        wxShare = (RadioButton) findViewById(R.id.share_wx);
        //设置点击时间的监听
        pyqShare.setOnClickListener(this);
        wxShare.setOnClickListener(this);
        //获取传递的值
        String path = getIntent().getStringExtra("image_path");//展示图片
        share_counts = getIntent().getIntExtra("share_flowers" ,0);//分享花的个数
        share_data = getIntent().getStringExtra("update_data");//用于更改数据库中的数据
        try {
            //显示图片
            test.setImageBitmap(FileIOUtils.readFile(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 分享
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.share_pyq://分享到朋友圈
                toShare(SendMessageToWX.Req.WXSceneTimeline);
                break;
            case R.id.share_wx://分享到微信
                toShare(SendMessageToWX.Req.WXSceneSession);
                break;
            default:
                break;
        }
        //注册广播
        if (shareCallBack == null){
            shareCallBack = new WXShareCallBack();
            IntentFilter filter = new IntentFilter();
            filter.addAction("mrkj.flowersdemo.ui.activity.share");
            registerReceiver(shareCallBack,filter);
        }
    }

    /**
     * 初始化界面的显示
     */
    private void setStyle() {
        //设置界面背景
        setActivityBackGround(R.mipmap.mrkj_grow_background);
        //设置标题栏的背景
        setTitleBackgroundColor(Color.TRANSPARENT);
        //设置标题栏
        setTitle("分享", ContextCompat.getColor(this,R.color.left_text_color),R.mipmap.mrkj_com_back,true);
    }

    /**
     * 分享方法
     *
     * @param i
     */
    private void toShare(int i) {
        FrameLayout relativeLayout = (FrameLayout) findViewById(R.id.share_picture);
        Bitmap bmp = getBitmap(relativeLayout);
        WXImageObject imgObj = new WXImageObject(bmp);
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = imgObj;
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("img");
        req.message = msg;
        req.scene = i;
        api.sendReq(req);
    }
    /**
     * 截图
     */
    public static Bitmap getBitmap(View view) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }
    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
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

    /**
     * 接收微信分享的回调函数
     */
    public class WXShareCallBack extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() != "mrkj.flowersdemo.ui.activity.share"){
                Log.i("接收到广播","随后将对对数据进行更改");
                finish();
                return;
            }
            //如果分享成功则更新数据
            if (intent != null && intent.getIntExtra("result",0) == BaseResp.ErrCode.ERR_OK){
                 //存入新的值
                 SaveKeyValues.putIntValues("flowerCount",
                         SaveKeyValues.getIntValues("flowerCount",0)
                                 - share_counts);
                 //存入当日的分享的花朵数量
                 MyDataUtils.upDateData(ShareActivity.this,"date","share",share_counts);
                 //将这个值存入xml文件中-->分享总数
                 SaveKeyValues.putIntValues("all_share_flowers_counts" ,
                         (SaveKeyValues.getIntValues("all_share_flowers_counts",
                                 0) + share_counts));
                //更爱数据库中的数据
                List<Flower> list = JSON.parseArray(share_data,Flower.class);
                MyDataUtils.shareCallBackUpdateData(context,list);
            }
            unregisterReceiver(shareCallBack);//解除注册广播
            finish();
        }
    }
}
