package mrkj.flowersdemo.application;

import android.app.Application;

import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.text.ParseException;

import mrkj.flowersdemo.ui.utils.L;
import mrkj.flowersdemo.ui.utils.SaveKeyValues;

/**
 * Created by Administrator on 2016/7/20.
 */
public class MyApplication extends Application{
    // 微信AppID:wxab6a2c57df1240f6
    private static final String APP_ID = "wxab6a2c57df1240f6";
    public static IWXAPI api;
    @Override
    public void onCreate() {
        super.onCreate();
        try {
            /*
		     * 注册应用到微信
		     */
            api = WXAPIFactory.createWXAPI(this, APP_ID, true);
            api.registerApp(APP_ID);
            //开启打印Log-->true , 关闭打印Log-->false
            L.isDebug = false;
            //实例化key值存储工具类
            SaveKeyValues.createSharePreferences(this);
            //帮助类
            MyDataHelper helper = MyDataHelper.getInstance();
            helper.addData(this);//添加数据或者更改数据
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
