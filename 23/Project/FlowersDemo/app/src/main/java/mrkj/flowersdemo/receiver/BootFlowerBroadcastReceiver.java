package mrkj.flowersdemo.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import mrkj.flowersdemo.R;
import mrkj.flowersdemo.service.AlarmService;

/**
 * 用于开机自启动
 * Created by Administrator on 2016/6/2.
// */
public class BootFlowerBroadcastReceiver extends BroadcastReceiver {
    //重写onReceive方法
    @Override
    public void onReceive(Context context, Intent intent) {
        //后边的XXX.class就是要启动的服务
        Intent alarm = new Intent(context, AlarmService.class);
        context.startService(alarm);
        Log.e("TAG", "开机自动服务自动启动.....");
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder notification = new Notification.Builder(context);
        notification.setContentTitle("Flower");
        notification.setContentText("静待花开，花朵每周的周日将会清零！");
        notification.setSmallIcon(R.mipmap.mrkj_icon);
        notification.setDefaults(Notification.DEFAULT_ALL);
        notification.setAutoCancel(true);
        manager.notify(1, notification.getNotification());
    }
}