package mrkj.healthylife.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import mrkj.healthylife.db.DatasDao;
import mrkj.healthylife.entity.HealthyPlan;
import mrkj.healthylife.receiver.FunctionBroadcastReceiver;
import mrkj.healthylife.utils.Constant;
import mrkj.healthylife.utils.DateUtils;
import mrkj.healthylife.utils.SaveKeyValues;

/**
 * 执行运动计划的服务
 */
public class ExecuteHealthyPlanService extends Service {
    public static final String planSaveService = "mrkj.healthylife.PLAN";
    //用于操作数据库
    private DatasDao datasDao;
    private Intent toBroadReciver;
    private AlarmManager manager;
    private PendingIntent senser;
    //数据
    private List<HealthyPlan> plansList;//计划的集合
    //第一个数据的提示时间|id|序号|提示类型
    private long first_hint_time;
    private int first_hint_id;
    private int first_hint_num;
    private int first_hint_type;


    private int finish_plans;//完成计划
    public ExecuteHealthyPlanService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        datasDao = new DatasDao(this);
        manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        finish_plans = SaveKeyValues.getIntValues("finish_plan" , 0);
//        toBroadReciver = new Intent(this, FunctionBroadcastReceiver.class);
//        senser = PendingIntent.getBroadcast(this, 0, toBroadReciver, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null){
            int code = intent.getIntExtra("code", 0);
            switch (code){
                case Constant.START_PLAN://开启任务
                    //先设置首个定时任务-->适用于数据库中只有唯一的一条数据
                    Log.e("开启服务后","设置第一个定时服务!");
                    setTaskAtOnlyOneDataInDataList();
                    break;
                case Constant.CHANGE_PLAN://更新执行顺序
                    //当数据大于1后执行这个方法对数据进行排列，然后从新设置定时服务
                    Log.e("更新数据并排列据","设置执行的顺序");
                    Cursor cursor = datasDao.selectAll("plans");
                    int count = cursor.getCount();
                    cursor.close();
                    if (count == 0){
                        sendBroadcast(new Intent(this, FunctionBroadcastReceiver.class).setAction("mrkj.healthylife.PLAN").putExtra("mode", 3));
                    }else {
//                        compareAllData();
                        if (count > 1){
                            getHealthyDataAndSortDataToSetAlarm();//排序任务再设置
                        }else {
                            setTaskAtOnlyOneDataInDataList();//设置单个任务
                        }
                    }
                    cursor.close();
                    break;
                case Constant.NEXT_PLAN://执行下一个任务
                    Log.e("此时完成了设置多个定时服务的第一个任务","设置下一个定时的操作");
                    int started_id = intent.getIntExtra("started_id",0);//执行过的ID
                    int started_num = intent.getIntExtra("started_num",0);//执行过的序号
                    //设置下一个定时任务-->判断是否可以开启下一个定时任务
                    setToExecuteNextAlarmTask(started_id , started_num);
                    break;
                case Constant.ONE_PLAN://执行一个任务
                    setCirculationTaskAtOnlyOneDataInDataList();
                    break;
                case Constant.STOP_PLAN://关闭服务
                    break;
                default:
                    break;
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }
    //================================ 设置下一个定时任务 ================================
    /**
     * 设置下一个定时任务
     * @param oldID
     * @param oldNum
     */
    private void setToExecuteNextAlarmTask(int oldID,int oldNum) {
        Log.e("刚刚执行完的定时任务_ID","【"+ oldID +"】");
        Log.e("刚刚执行完的定时任务序号","【"+ oldNum +"】");
        //1、首先先判断执行过的任务的数据还是否有效-->判断任务的结束日期
        long nowDate = DateUtils.getNowDateMillisecondValues();//当前日期
        compareVerificationData(nowDate, oldID);//对刚刚执行玩的数据进行校验
        //2、判断当前是否是最后一个任务计划
        Cursor cursor = datasDao.selectAll("plans");//全查询
        if (cursor.getCount() == 1){//如果此时只剩一个任务时
            //则设置单个任务计划
            setTaskAtOnlyOneDataInDataList();
            return;
        }
        if (oldNum == cursor.getCount()){//此时执行完最后一个任务
            //重新排序并设置任务
            getHealthyDataAndSortDataToSetAlarm();
            return;
        }
        //3、根据序号查找相应的数据进行设置定时计划
        int nextNum = oldNum + 1;//下一个任务的序号
        long hintTime;
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("_id"));//id
            int hour = cursor.getInt(cursor.getColumnIndex("hint_hour"));//提醒时间-->时
            int minute = cursor.getInt(cursor.getColumnIndex("hint_minute"));//提醒时间-->分
            int number = cursor.getInt(cursor.getColumnIndex("number_values"));//顺序
            int hint_type = cursor.getInt(cursor.getColumnIndex("sport_type"));//类型
            if (number == nextNum){//查询出结果
                hintTime = DateUtils.getMillisecondValues(hour,minute);
                setAlarmService(2,id,number,hint_type,hintTime);//设置定时任务
                break;
            }
        }
    }

    /**
     * 校验数据
     * @param nowDate
     */
    private void compareVerificationData(long nowDate ,int oldID){
        Cursor cursor = datasDao.selectValue2("plans",null,"_id=?",new String[]{String.valueOf(oldID)},null,null,null);
        while (cursor.moveToNext()){
            int stop_year = cursor.getInt(cursor.getColumnIndex("stop_year"));//结束日期--->年
            int stop_month = cursor.getInt(cursor.getColumnIndex("stop_month"));//结束日期--->月
            int stop_day = cursor.getInt(cursor.getColumnIndex("stop_day"));//结束日期--->日
            long stopDate = DateUtils.getMillisecondValues(stop_year, stop_month, stop_day);
            if (stop_year == (int)(DateUtils.getDate().get("year"))
                    && stop_month == (int)(DateUtils.getDate().get("month"))
                    && stop_day == (int)(DateUtils.getDate().get("day"))){//此时意味着当前数据将不再保存在数据中-->删除该条数据
                finish_plans = SaveKeyValues.getIntValues("finish_plan" , 0);
                SaveKeyValues.putIntValues("finish_plan",++finish_plans);
                datasDao.deleteValue("plans", "_id=?", new String[]{String.valueOf(oldID)});
            }
        }
        cursor.close();
    }
    /**
     * 校验全部数据
     */
    private void compareAllData(){
//        long nowDate = DateUtils.getNowDateMillisecondValues();//当前日期
//        Map<String,Object>  times = DateUtils.getDate();
        Cursor cursor = datasDao.selectAll("plans");
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            int stop_year = cursor.getInt(cursor.getColumnIndex("stop_year"));//结束日期--->年
            int stop_month = cursor.getInt(cursor.getColumnIndex("stop_month"));//结束日期--->月
            int stop_day = cursor.getInt(cursor.getColumnIndex("stop_day"));//结束日期--->日
            Log.e("结束日期",stop_year +"-"+stop_month+"-"+stop_day );
//            long stopDate = DateUtils.getMillisecondValues(stop_year, stop_month, stop_day);
//            Log.e("当前时间",nowDate+"" );
//            Log.e("结束时间",stopDate+"" );
            Log.e("==","**************************" );

            if (stop_year == (int)(DateUtils.getDate().get("year"))
                    && stop_month == (int)(DateUtils.getDate().get("month"))
                    && stop_day == (int)(DateUtils.getDate().get("day"))){//此时意味着当前数据将不再保存在数据中-->删除该条数据
                Log.e("==","执行了" );
                finish_plans = SaveKeyValues.getIntValues("finish_plan" , 0);
                SaveKeyValues.putIntValues("finish_plan",++finish_plans);
                datasDao.deleteValue("plans", "_id=?", new String[]{String.valueOf(id)});
                break;
            }
        }
        cursor.close();
    }
    //================================ 排序并设置第一个执行任务 ================================
    /**
     * 对数据进行排列-->此时数据至少有两个
     */
    private void getHealthyDataAndSortDataToSetAlarm() {
        Log.e("设置定时","对任务进行排序");
        plansList = new ArrayList<>();
        //首先查询数据
        Cursor cursor = datasDao.selectColumn("plans",new String[]{"_id","hint_hour","hint_minute"});
        int counts = cursor.getCount();//数据的个数
        Log.e("任务个数",counts + "个");
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            int hour = cursor.getInt(cursor.getColumnIndex("hint_hour"));
            int minute = cursor.getInt(cursor.getColumnIndex("hint_minute"));
            HealthyPlan plan = new HealthyPlan();
            plan.setId_Num(id);
            plan.setPlan_Time(DateUtils.getMillisecondValues(hour, minute));
            plansList.add(plan);
        }
        cursor.close();//关闭游标
        Log.e("集合中元素的数量",plansList.size() + "个");
//        Log.e("集合中元素排列前","====================");
//        for (HealthyPlan plan : plansList){
//            Log.e("元素","_id = " + plan.getId_Num() + "\t\t" + "提示时间毫秒值 = " + plan.getPlan_Time());
//        }
        if (plansList.size() == counts){
            //对集合进行升序排列
            Collections.sort(plansList, new Comparator<HealthyPlan>() {
                @Override
                public int compare(HealthyPlan lhs, HealthyPlan rhs) {
                    Long timeL = lhs.getPlan_Time();
                    Long timeR = rhs.getPlan_Time();
                    return timeL.compareTo(timeR);
                }
            });
            Log.e("集合中元素排列后", "====================");
//            for (HealthyPlan plan : plansList){
//                Log.e("元素","_id = " + plan.getId_Num() + "\t\t" + "提示时间毫秒值 = " + plan.getPlan_Time());
//            }
            Log.e("集合中元素排列后", "对数据表中的数据设置顺序值");

            //按照list的排列顺数数据表中的数据进行设置
            for (int i = 0; i < counts; i++ ){
                setNumberValuerToDataValues(plansList.get(i).getId_Num() ,(i + 1));
            }
            //验证
            Log.e("设置顺序值后", "验证");
            functionalVerification();
            //排列后设置定时服务-->根据排序值设置定时服务-->从第一个开始
            long result = accordanceNumberSetAlarmTask(1);
            Log.e("定时返回值","【"+result+"】");
        }
    }

    /**
     * 根据排列顺序进行设置定时任务
     * @param index
     */
    private long accordanceNumberSetAlarmTask(int index) {
        long nowTime = DateUtils.getNowMillisecondValues();//当前时间
        long nowDate = DateUtils.getNowDateMillisecondValues();//当前日期
        return getvirtualValueIndex(index ,nowTime ,nowDate);
    }
    private long getvirtualValueIndex(int index , long time , long date){
        long hintTime;//提示时间
        long stopDate;//提示日期
        Cursor cursor = datasDao.selectAll("plans");
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("_id"));//id
            int hour = cursor.getInt(cursor.getColumnIndex("hint_hour"));//提醒时间-->时
            int minute = cursor.getInt(cursor.getColumnIndex("hint_minute"));//提醒时间-->分
            int number = cursor.getInt(cursor.getColumnIndex("number_values"));//顺序
            int hint_type = cursor.getInt(cursor.getColumnIndex("sport_type"));//类型
            int stop_year = cursor.getInt(cursor.getColumnIndex("stop_year"));//结束日期--->年
            int stop_month = cursor.getInt(cursor.getColumnIndex("stop_month"));//结束日期--->月
            int stop_day = cursor.getInt(cursor.getColumnIndex("stop_day"));//结束日期--->日
            if (index == number){
                //判断当前的数据是否有效
                hintTime = DateUtils.getMillisecondValues(hour,minute);
                stopDate = DateUtils.getMillisecondValues(stop_year,stop_month,stop_day);
                //1、根据日期判断计划是否有效如果有效则判断时间是否有效
                //如有效：则判断时间是否有效-->时间有效则设置定时服务|时间超过当前时间则执行下一个循环
                //如无效：则删除此条数据执行下一个循环
                if(date  <= stopDate) {//如果当前日期大于设置的停止日期则判断时间是否有效
                    if (time >= hintTime) {//如果当前时间大于提示时间则执行下一个循环
                        //寻找比当前index大一的数据
                        if (index == 1) {//记录下index为为一的相关数据
                            first_hint_time = hintTime;
                            first_hint_id = id;
                            first_hint_num = number;
                            first_hint_type = hint_type;
                            Log.e("序号为1的_ID","【"+first_hint_id+"】");
                            Log.e("序号为1的序号","【"+first_hint_num+"】");
                            Log.e("序号为1的时间","【"+first_hint_time+"】");
                            Log.e("序号为1的类型","【"+first_hint_type+"】");
                        }
                        if (index == cursor.getCount()) {//如果到最后的数据的提示时间都小于当前时间，则设置index为1的数据进行设置定时提醒
                            setAlarmService(2, first_hint_id, first_hint_num, first_hint_type, first_hint_time + Constant.DAY_FOR_24_HOURS);
                            compareAllData();
                            return 2000;
                        }
                        cursor.close();//关闭游标
                        index += 1;//查询下一条数据
                        return accordanceNumberSetAlarmTask(index);
                    } else {//设置时间大于当前时间则设置定时任务
                        setAlarmService(2, id, number, hint_type, hintTime);
                    }
                }
            }
        }
        return 1000;
    }

    /**
     * 更新数据
     * @param index
     */
    private void setNumberValuerToDataValues(int id,int index){
        ContentValues values = new ContentValues();
        values.put("number_values" , index);
        datasDao.updateValue("plans", values, "_id=?", new String[]{String.valueOf(id)});
    }
    //================================ 设置单个任务 ================================
    /**
     * 在数据表中只有一条数据时以这个方法设置定时服务
     */
    private void setTaskAtOnlyOneDataInDataList() {
        Cursor cursor = datasDao.selectAll("plans");
        while (cursor.moveToNext()){
            //查询出相关的数值
            int id = cursor.getInt(cursor.getColumnIndex("_id"));//id
            long hintTime = cursor.getLong(cursor.getColumnIndex("hint_time"));//提示时间
            int number = cursor.getInt(cursor.getColumnIndex("number_values"));//排列顺序
            int hint_type = cursor.getInt(cursor.getColumnIndex("sport_type"));//提示类型
            setAlarmService(1, id, number, hint_type,hintTime);
            break;
        }
        cursor.close();
    }
    /**
     * 循环一个任务
     */
    private void setCirculationTaskAtOnlyOneDataInDataList() {
        long nowTime = DateUtils.getNowMillisecondValues();//当前时间
        long nowDate = DateUtils.getNowDateMillisecondValues();//当前日期
        long hintTime;//提示时间
        long stopDate;//提示日期
        Log.e("设置定时","循环数据表中唯一的任务");
        Cursor cursor = datasDao.selectAll("plans");
        int counts = cursor.getCount();
        while (cursor.moveToNext()){
            //查询出相关的数值
            int id = cursor.getInt(cursor.getColumnIndex("_id"));//id
            int number = cursor.getInt(cursor.getColumnIndex("number_values"));//排列顺序
            int hint_type = cursor.getInt(cursor.getColumnIndex("sport_type"));//提示类型
            int hour = cursor.getInt(cursor.getColumnIndex("hint_hour"));//提示时间--->时
            int minute = cursor.getInt(cursor.getColumnIndex("hint_minute"));//提示时间--->分
            int stop_year = cursor.getInt(cursor.getColumnIndex("stop_year"));//结束日期--->年
            int stop_month = cursor.getInt(cursor.getColumnIndex("stop_month"));//结束日期--->月
            int stop_day = cursor.getInt(cursor.getColumnIndex("stop_day"));//结束日期--->日
            //设置提示时间-->此时重新获取要提示的long型的毫秒值用于设置定时的开始时间
            hintTime = DateUtils.getMillisecondValues(hour, minute);
            stopDate = DateUtils.getMillisecondValues(stop_year,stop_month,stop_day);
            if (counts == 1){
                if (hintTime < nowTime){
                    hintTime += Constant.DAY_FOR_24_HOURS;
                }
                if (stopDate < nowDate){//设置循环的下一次定时
                    Log.e("通知","设置服务");
                    //设置定时
                    setAlarmService(1, id, number, hint_type, hintTime);
                }else{//通知广播关闭服务
                    Log.e("通知","关闭服务");
                    //清空表中数据
                    datasDao.clear("plans");
                    sendBroadcast(new Intent(this, FunctionBroadcastReceiver.class).setAction("mrkj.healthylife.PLAN").putExtra("mode", 3));
                }
            }else{
                Log.e("数据以增加","将执行排列后再设置定时！");
            }
            break;
        }
        cursor.close();
    }
    //================================ 功能 ================================
    /**
     * 功能眼增
     */
    private void functionalVerification(){
        Cursor cursor = datasDao.selectColumn("plans", new String[]{"_id", "hint_hour", "hint_minute", "number_values"});
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            int hour = cursor.getInt(cursor.getColumnIndex("hint_hour"));
            int minute = cursor.getInt(cursor.getColumnIndex("hint_minute"));
            int nunber = cursor.getInt(cursor.getColumnIndex("number_values"));
            Log.e("元素","_id = " + id + "\t\t" + "时间 = " + hour + "点" + minute + "分" +"\t\t" + "序号 = " + nunber);

        }
        cursor.close();
    }

    /**
     * 设置定时任务
     * @id号 id
     * @排列序号 number
     * @提示类型 hint_type
     * @提示时间 hintTime
     */
    private void setAlarmService(int mode ,int id ,int number ,int hint_type , long hintTime){
        toBroadReciver = new Intent(this, FunctionBroadcastReceiver.class);
        toBroadReciver.setAction(planSaveService);
        toBroadReciver.putExtra("mode",mode);//int
        toBroadReciver.putExtra("id", id);//int
        toBroadReciver.putExtra("number", number);//int
        toBroadReciver.putExtra("hint_type",hint_type);//int
        senser = PendingIntent.getBroadcast(this, 0, toBroadReciver, PendingIntent.FLAG_UPDATE_CURRENT);
        manager.set(AlarmManager.RTC_WAKEUP, hintTime, senser);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("运动计划全部执行完","服务结束了");
    }
}
