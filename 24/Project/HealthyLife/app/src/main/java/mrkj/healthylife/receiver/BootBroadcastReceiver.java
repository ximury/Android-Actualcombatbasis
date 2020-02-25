package mrkj.healthylife.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import mrkj.healthylife.R;
import mrkj.healthylife.service.ExecuteHealthyPlanService;
import mrkj.healthylife.service.StepCounterService;

/**
 * 用于开机自启动
 * Created by Administrator on 2016/6/2.
// */
public class BootBroadcastReceiver extends BroadcastReceiver {
    //重写onReceive方法
    @Override
    public void onReceive(Context context, Intent intent) {
        //后边的XXX.class就是要启动的服务
        Intent service = new Intent(context, StepCounterService.class);
        context.startService(service);
        Intent alarm = new Intent(context, ExecuteHealthyPlanService.class);
        context.startService(alarm);
        Toast.makeText(context, "启动了", Toast.LENGTH_SHORT).show();
        Log.e("TAG", "开机自动服务自动启动.....");
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder notification = new Notification.Builder(context);
        notification.setContentTitle("KeepFit");
        notification.setContentText("计步开始了！");
        notification.setSmallIcon(R.mipmap.mrkj_do_sport);
        notification.setDefaults(Notification.DEFAULT_ALL);
        notification.setAutoCancel(true);
        manager.notify(1, notification.getNotification());
    }
}