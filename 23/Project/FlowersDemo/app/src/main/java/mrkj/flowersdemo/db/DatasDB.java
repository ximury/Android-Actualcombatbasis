package mrkj.flowersdemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Author LYJ
 * Created on 2016/5/21.
 * Time 11:42
 */
public class DatasDB extends SQLiteOpenHelper {

    private static String DB_NAME = "flower_db";// 数据库名称
    private static int DB_VERSION = 1;// 数据库版本号
    private static DatasDB datasDB;
    /**
     * 构造方法
     * @param context
     */
    private DatasDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    public static synchronized DatasDB getDataDB(Context context){
        if (datasDB == null) {
            datasDB = new DatasDB(context);
        }
        return datasDB;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        //主键自增,花名，花的个数
        String flowers = "create table flower "+
                "(_id integer primary key autoincrement ," +
                "name varchar(30),count integer)";
        //时间 时间转换后的时间 年、月、日,当日的种花的个数，当日的分享个数
        String time = "create table date (_id integer primary key"+
                " autoincrement ,time_str varchar(30) ,year integer,"
                +"month integer,day integer,week integer"+
                ",plant integer,share integer"+",plant_time integer)";
        //执行SQL语句
        db.execSQL(flowers);
        db.execSQL(time);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
