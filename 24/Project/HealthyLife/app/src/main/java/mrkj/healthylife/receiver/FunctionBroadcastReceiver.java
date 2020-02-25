package mrkj.healthylife.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import mrkj.healthylife.R;
import mrkj.healthylife.activity.PlayActivity;
import mrkj.healthylife.application.DemoApplication;
import mrkj.healthylife.service.ExecuteHealthyPlanService;
import mrkj.healthylife.service.RecordedSaveService;
import mrkj.healthylife.service.StepCounterService;
import mrkj.healthylife.utils.Constant;
import mrkj.healthylife.utils.SaveKeyValues;

/**
 * 用于接收定时服务发送的广播
 * 接收后提醒用户
 * Created by Administrator on 2016/6/3.
 */
public class FunctionBroadcastReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (RecordedSaveService.cancelSaveService.equals(action)){

            //关闭记录计步服务
            Intent cancel= new Intent(context,RecordedSaveService.class);
            context.stopService(cancel);
        }else if (StepCounterService.alarmSaveService.equals(action)){

            //开启记录计步服务
            Intent start= new Intent(context,RecordedSaveService.class);
            context.startService(start);
        }else if (ExecuteHealthyPlanService.planSaveService.equals(action)){
            int taskID;
            int taskNum;
            int taskType;
            //执行运动计划
            int mode = intent.getIntExtra("mode", 1);
            switch (mode){
                case 1://执行的是单个的定时任务
                    taskType = intent.getIntExtra("hint_type",0);
                    Log.e("通知","通知用户进行运动");
                    Log.e("通知","提示类型" + taskType);
                    Log.e("通知","提示计划" + DemoApplication.shuoming[taskType]);
                    sendNotification(context , taskType ,DemoApplication.shuoming[taskType]);
                    context.startService(new Intent(context, ExecuteHealthyPlanService.class).putExtra("code", Constant.ONE_PLAN));
                    break;
                case 2://执行的是多个的定时任务
                    Log.e("多个定时任务","此时数据的条数大于1");
                    taskType = intent.getIntExtra("hint_type",0);
                    taskID = intent.getIntExtra("id",0);
                    taskNum = intent.getIntExtra("number",0);
                    //获取当前数据
                    Log.e("通知","通知用户进行运动");
                    Log.e("通知","提示类型" + taskType);
                    Log.e("通知","提示计划" + DemoApplication.shuoming[taskType]);
                    Log.e("数据","数据_ID" + taskID);
                    Log.e("数据","数据序号" + taskNum);
                    sendNotification(context , taskType ,DemoApplication.shuoming[taskType]);
                    //此时任务数量大于1
                    context.startService(new Intent(context, ExecuteHealthyPlanService.class).putExtra("code", Constant.NEXT_PLAN).putExtra("started_num",taskNum).putExtra("started_id",taskID));
                    break;
                case 3://关闭服务
                    Log.e("通知","将要执行关闭服务");
                    int finish_plans = SaveKeyValues.getIntValues("finish_plan", 0);
                    SaveKeyValues.putIntValues("finish_plan", ++finish_plans);
                    context.stopService(new Intent(context, ExecuteHealthyPlanService.class));
                    break;
                default:
                    break;
            }

        }
    }

    /**
     * 发送通知
     * @param context
     * @param type
     * @param messages
     */
    private void sendNotification(Context context ,int type ,String messages){
        SaveKeyValues.putIntValues("do_hint",1);
        SaveKeyValues.putLongValues("show_hint",System.currentTimeMillis());
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(context , PlayActivity.class);
        intent.putExtra("play_type", type);
        intent.putExtra("what",1);
        intent.putExtra("do_hint",1);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle("KeepFit");
        builder.setContentText(messages);
        builder.setSmallIcon(R.mipmap.mrkj_do_sport);
        builder.setDefaults(Notification.DEFAULT_ALL);
        builder.setAutoCancel(true);
        builder.setContentIntent(pendingIntent);
        manager.notify(0, builder.getNotification());
    }
}
