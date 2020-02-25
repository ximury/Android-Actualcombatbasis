package mrkj.flowersdemo.application;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import mrkj.flowersdemo.R;
import mrkj.flowersdemo.db.DatasDao;
import mrkj.flowersdemo.service.AlarmService;
import mrkj.flowersdemo.ui.bean.Quotes;
import mrkj.flowersdemo.ui.bean.RecordDate;
import mrkj.flowersdemo.ui.utils.GetDateUtils;
import mrkj.flowersdemo.ui.utils.L;
import mrkj.flowersdemo.ui.utils.SaveKeyValues;

/**
 * Created by Administrator on 2016/7/26.
 */
public class MyDataHelper {
    private DatasDao datasDao;//数据库操作类
    private int[] bimmapID;//花的图片资源ID
    private Bitmap[] flowers;//花的图片
    private String[] flowerName;//花的名称
    private ArrayList<Quotes> quotesList;//名言警句集合
    private static class SingletonHolder {
        private static final MyDataHelper INSTANCE = new MyDataHelper();
    }
    private MyDataHelper (){}
    public static final MyDataHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }
    /**
     * 返回图片资源ID数组
     * @return
     */
    public int[] getBitmapID(){
        if (bimmapID == null){
            bimmapID = new int[]{R.mipmap.mrkj_flower_01, R.mipmap.mrkj_flower_02
                    ,R.mipmap.mrkj_flower_03,R.mipmap.mrkj_flower_04,R.mipmap.mrkj_flower_05
                    ,R.mipmap.mrkj_flower_06,R.mipmap.mrkj_flower_07,R.mipmap.mrkj_flower_08
                    ,R.mipmap.mrkj_flower_09,R.mipmap.mrkj_flower_10};
        }
        return bimmapID;
    }
    /**
     * 花朵bitmap数组
     * @param context
     * @return
     */
    public Bitmap[] getBitmapArray(Context context){
            int[] resID = getBitmapID();
            flowers = new Bitmap[10];
            for (int i = 0 ;i < flowers.length;i++){
                flowers[i] = BitmapFactory.decodeResource(context.getResources(),resID[i]);
            }
        return flowers;
    }
    /**
     * 花的名称
     */
    public String[] getFlowerNames(){
        if (flowerName == null){
            flowerName = new String[]{"勿忘我","三色堇","金盏菊"
                    ,"雏菊","桔梗花","鸡蛋花","石竹","莺萝","荷兰菊","百合"};
        }
        return flowerName;
    }
    /**
     * 向数据表中插入数据
     */
    public void addData(Context context) throws ParseException {
        //创建表
        datasDao  = new DatasDao(context);
        addFlowerData();//添加数据表
        addDateData(context);//添加数据表
        datasDao.close();
    }
    //添加日期-->日期
    private void addDateData(Context context) throws ParseException {
        boolean addUnLoginDayData = false;//添加未登陆天数的假数据
        String lastTime = null;//添加数据前的最后一条数据的日期
        Cursor cursor = datasDao.selectAll("date");//获取游标
        ContentValues values;
        //获取今日的信息
        String nowDate = GetDateUtils.currentDatetime();
        L.e("转换完格式的时间",nowDate);
        Date date = GetDateUtils.now();//获取当前日期
        int year = GetDateUtils.getYear(date);
        int month = GetDateUtils.getMonth(date);
        int day = GetDateUtils.getDay(date);
        int week = GetDateUtils.dayOfWeek();
        L.e("year",year + "");
        L.e("month",month + "");
        L.e("day",day + "");
        //1对应星期日
        L.e("week",week + "");//--->用于判断清空数据-->
        if (cursor.getCount() == 0){//此时应为第一次进入程序时才会调用该方法
            addUnLoginDayData = false;
            //此时该表中没有数据
            //添加六条假数据
            addInitData(6);//默认加入六条假数据用于查询
            //向数据库当中添加数据
            values = new ContentValues();
            values.put("time_str",nowDate);
            values.put("year",year);
            values.put("month",month);
            values.put("day",day);
            values.put("week",week);
            values.put("plant",0);
            values.put("share",0);
            values.put("plant_time",0);
            SaveKeyValues.putIntValues("login_days",1);//默认为一
            //定时服务
            Intent alarm = new Intent(context, AlarmService.class);
            context.startService(alarm);//开启一个定时服务
        }else{//不是第一次进入程序
            cursor.moveToLast();//光标移到最后一行,查询最后条数据
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            lastTime = cursor.getString(cursor.getColumnIndex("time_str"));
            L.e("id",id+"");
            L.e("time",lastTime+"");
            if (TextUtils.equals(nowDate.substring(0,10),lastTime.substring(0,10))){//日期为同一天
                //关闭游标
                cursor.close();
                L.e("同一天登陆","不做任何数据操作");
                //跳出方法
                return;
            }else {
                //向数据库当中添加数据
                values = new ContentValues();
                values.put("time_str",nowDate);
                values.put("year",year);
                values.put("month",month);
                values.put("day",day);
                values.put("week",week);
                values.put("plant",0);
                values.put("share",0);
                values.put("plant_time",0);
                //计算今天和上一天的相隔天数
                //转换格式
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date last = format.parse(lastTime);
                Date now = format.parse(nowDate);
                //作天数比较
                long days = GetDateUtils.getDayDiff(last,now);
                //大于24小时则为一天
                L.e("天数差",days +"");
                //如果days为一时说明用户是在连续一天天在使用该软件
                if (days == 2){
                    //获取之前的值
                    int loginValues = SaveKeyValues.getIntValues("login_days" , 0);
                    loginValues+=1;
                    //存入新的值-->此时是连续登陆
                    SaveKeyValues.putIntValues("login_days",loginValues);//1
                    //如果5天连续登陆-->解锁一次就可以
                    if (loginValues == 5){
                        SaveKeyValues.putBooleanValues("unlock",true);
                    }
                }
                if (days != 1 && days != 2){//不是同一天也不是隔一天
                    addUnLoginDayData = true;//开启添加未登录天数假数据的方法
                    SaveKeyValues.putIntValues("login_days",1);//清空连续登陆的数据
                }
            }
        }
        //插入新的数据之前先判断和之前的数据差几天-->
        //随后插入到上一次使用的数据
        if (addUnLoginDayData){
//            insertUnLoginDaysData("2016-07-24 05:59:59",nowDate);//测试用
            insertUnLoginDaysData(lastTime,nowDate);
        }
        //插入数据
        long result = datasDao.insertValue("date",values);
        L.e("插入数据",result != -1 ? "success":"failure");
        cursor.close();
    }

    /**
     * 插入未登录天数的假数据
     */
    private void insertUnLoginDaysData(String lastTime,String nowDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date last = format.parse(lastTime);
            Date now = format.parse(nowDate);
            long days = GetDateUtils.getDayDiff(last,now);
            L.e("相差天数",days + "");
            addInitData((int)days - 1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //添加花的数据-->花
    private void addFlowerData() {
        Cursor cursor = datasDao.selectAll("flower");//获取游标
        if (cursor.getCount() == 0){
            String[] flowerName = getFlowerNames();
            for (int i = 0;i<flowerName.length;i++){
                ContentValues values = new ContentValues();
                values.put("name",flowerName[i]);
                values.put("count",0);
                long result = datasDao.insertValue("flower",values);
                L.e("插入数据",result != -1 ? "success":"failure");
            }
        }
        cursor.close();
    }

    /**
     * 向数据库中加入初始值(方便查询和显示折线图)
     */
    private void addInitData(int count){
        //从今天往前数六日
        ArrayList<RecordDate> dateSixDayList = getNestDayDate(count);
        int i = 0;
        while (i < count){
            ContentValues values = new ContentValues();
            values.put("time_str",dateSixDayList.get(i).getDate());
            values.put("year",dateSixDayList.get(i).getYear());
            values.put("month",dateSixDayList.get(i).getMonth());
            values.put("day",dateSixDayList.get(i).getDay());
            values.put("week",dateSixDayList.get(i).getWeek());
            values.put("plant",dateSixDayList.get(i).getPlant_counts());
            values.put("share",dateSixDayList.get(i).getShare_counts());
            values.put("plant_time",dateSixDayList.get(i).getTime());
            datasDao.insertValue("date",values);
            i++;
        }
    }

    /**
     * 获取今天之前几天的日期
     * @param k
     */
    private ArrayList<RecordDate> getNestDayDate(int k) {
        ArrayList<RecordDate> dateSixDayList = new ArrayList<>();
        String date ="%s-%s-%s 05:59:59";
        Calendar calendar = Calendar.getInstance();
        for (int i = k; i > 0; i--) {
            RecordDate recordDate = new RecordDate();
            calendar.add(Calendar.DATE, -1);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int week = calendar.get(Calendar.DAY_OF_WEEK);
            recordDate.setDate(String.format(date,year,month,day))
                    .setYear(year)
                    .setMonth(month)
                    .setDay(day)
                    .setWeek(week)
                    .setTime(0)
                    .setPlant_counts(0)
                    .setShare_counts(0);
            dateSixDayList.add(recordDate);
        }
        L.e("正序","///////////////////////");
        for (RecordDate recordDate:dateSixDayList){
            L.e("date",recordDate.getDate());
        }
        Collections.reverse(dateSixDayList);
        L.e("倒序","///////////////////////");
        for (RecordDate recordDate:dateSixDayList){
            L.e("date",recordDate.getDate());
        }
        return dateSixDayList;
    }

    /**
     * 获取名言警句集合(录了20条)
     * @return
     */
    public ArrayList<Quotes> getQuotesData(){
        if (quotesList == null){
            quotesList = new ArrayList<>();
            quotesList.add(new Quotes().setQuotes("我们破灭的希望，流产的才能，失败的事业，受了挫折的雄心，往往积聚起来变为忌妒。").setAuthor("—— 巴尔扎克"));
            quotesList.add(new Quotes().setQuotes("我需要三件东西：爱情友谊和图书。然而这三者之间何其相通！炽热的爱情可以充实图书的内容，图书又是人们最忠实的朋友。").setAuthor("—— 蒙田"));
            quotesList.add(new Quotes().setQuotes("世界上一成不变的东西，只有“任何事物都是在不断变化的”这条真理。").setAuthor("—— 斯里兰卡"));
            quotesList.add(new Quotes().setQuotes("土地是以它的肥沃和收获而被估价的；才能也是土地，不过它生产的不是粮食，而是真理。如果只能滋生瞑想和幻想的话，即使再大的才能也只是砂地或盐池，那上面连小草也长不出来的。").setAuthor("—— 别林斯基"));
            quotesList.add(new Quotes().setQuotes("人生的磨难是很多的，所以我们不可对于每一件轻微的伤害都过于敏感。在生活磨难面前，精神上的坚强和无动于衷是我们抵抗罪恶和人生意外的最好武器。").setAuthor("—— 洛克"));
            quotesList.add(new Quotes().setQuotes("人生并不像火车要通过每个站似的经过每一个生活阶段。人生总是直向前行走，从不留下什么。").setAuthor("—— 刘易斯"));
            quotesList.add(new Quotes().setQuotes("她们把自己恋爱作为终极目标，有了爱人便什么都不要了，对社会作不了贡献，人生价值最少。").setAuthor("—— 向警予"));
            quotesList.add(new Quotes().setQuotes("躯体总是以惹人厌烦告终。除思想以外，没有什么优美和有意思的东西留下来，因为思想就是生命。").setAuthor("—— 萧伯纳"));
            quotesList.add(new Quotes().setQuotes("人类的全部历史都告诫有智慧的人，不要笃信时运，而应坚信思想。").setAuthor("—— 爱献生"));
            quotesList.add(new Quotes().setQuotes("你可以从别人那里得来思想，你的思想方法，即熔铸思想的模子却必须是你自己的。").setAuthor("—— 拉姆"));
            quotesList.add(new Quotes().setQuotes("如果你浪费了自己的年龄，那是挺可悲的。因为你的青春只能持续一点儿时间——很短的一点儿时间。").setAuthor("—— 王尔德"));
            quotesList.add(new Quotes().setQuotes("没有人不爱惜他的生命，但很少人珍视他的时间。").setAuthor("—— 梁实秋"));
            quotesList.add(new Quotes().setQuotes("从不浪费时间的人，没有工夫抱怨时间不够。").setAuthor("—— 杰弗逊"));
            quotesList.add(new Quotes().setQuotes("人类所有的力量，只是耐心加上时间的混合。所谓强者既有意义，又有等待时机。").setAuthor("—— 巴尔扎克"));
            quotesList.add(new Quotes().setQuotes("浪费时间是所有支出中最奢侈最昂贵的。").setAuthor("—— 富兰克林"));
            quotesList.add(new Quotes().setQuotes("我读的书愈多，就愈亲近世界，愈明了生活的意义，愈觉得生活的重要。").setAuthor("—— 高尔基"));
            quotesList.add(new Quotes().setQuotes("人致力于一个目标，一种观念，是人在生活过程中追求完整之需要的一种表现。").setAuthor("—— 罗曼·罗兰"));
            quotesList.add(new Quotes().setQuotes("劳动永远是人类生活的基础，是创造人类文化幸福的基础。").setAuthor("—— 马卡连柯"));
            quotesList.add(new Quotes().setQuotes("不管怎样的事情，都请安静地愉快吧!这是人生。我们要依样地接受人生，勇敢地、大胆地，而且永远地微笑着。").setAuthor("—— 卢森堡"));
            quotesList.add(new Quotes().setQuotes("人生一世，总有些片断当时看着无关紧要，而事实上却牵动了大局。").setAuthor("—— 萨克雷"));
            quotesList.add(new Quotes().setQuotes("如果你比对手更专注，你就能把他抛在身后。专注成就未来！").setAuthor("—— 佚名"));
            quotesList.add(new Quotes().setQuotes("心无旁骛似明镜,无风何处起涟漪。").setAuthor("—— 佚名"));
        }
        return quotesList;
    }
}
