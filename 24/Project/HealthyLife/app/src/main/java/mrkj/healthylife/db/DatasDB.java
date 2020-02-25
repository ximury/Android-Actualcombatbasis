package mrkj.healthylife.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/5/21.
 */
public class DatasDB extends SQLiteOpenHelper {
    private static String DB_NAME = "healthy_db";// 数据库名称
    private static int DB_VERSION = 1;// 数据库版本号
    public DatasDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //主键自增,日期,年,月,日,步数,热量,里程
        String stepRecorded = "create table step (_id integer primary key autoincrement ," +
                " date varchar(20),year integer,month integer,day integer,steps integer,hot varchar(20),length varchar(20))";
        //主键自增，类型，名称，开始年，月，日，结束年，月，日，设置时间long值,提示时间long值，提示时，提示分提示时间文字，排序 ,增加
        String sportTable = "create table plans (_id integer primary key autoincrement ,"+
                "sport_type integer,sport_name varchar(20),start_year integer,start_month integer,start_day integer,"
                +"stop_year integer,stop_month integer,stop_day integer,set_time integer,hint_time integer,hint_hour integer, hint_minute integer,hint_str varchar(20),add_24_hour integer,"
                +"number_values"+")";
        //执行SQL语句
        db.execSQL(stepRecorded);
        db.execSQL(sportTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
