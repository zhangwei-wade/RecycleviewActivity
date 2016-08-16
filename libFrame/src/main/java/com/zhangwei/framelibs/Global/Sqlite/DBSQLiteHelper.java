package com.zhangwei.framelibs.Global.Sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Lizl
 * @ClassName:DBSQLiteHelper
 * @Description: TODO 搜索页面的缓存数据库
 * @date 2013-12-12
 */
public class DBSQLiteHelper extends SQLiteOpenHelper {
    /**
     * 交易概览
     */
    public final static String sand_Trading = "sand_Trading";
    private String CREATE_READ_TABLE = "CREATE TABLE " + sand_Trading + "(" +
            "sand_TradingID INTEGER primary key autoincrement," +
            "userName  varchar(100) NOT NULL," +
            "shopID    text NOT NULL," +
            "startTime VARCHAR(50),endTime  VARCHAR(50)," +
            "tradingSum double," +
            "tradingPoundage double," +
            "tradingNumber int," +
            "trading1  text," +
            "trading2  text," +
            "trading3 text)";
    /**
     * 照片
     */
    public final static String sand_photo = "sand_photo";
    private String CREATE_PHOTO_TABLE = "CREATE TABLE " + sand_photo + "(" +
            "sand_ID INTEGER primary key autoincrement, " +
            "userName  varchar(100) NOT NULL, " +
            "shopID    text NOT NULL, " +
            "photo_path text NOT NULL, " +
            "photo_type VARCHAR(10) NOT NULL, " +
            "imageID   text ," +
            "uuid   text ," +
            "isupload VARCHAR(10) NOT NULL)";


    /**
     * 提醒
     */
    public final static String sand_remind = "sand_remind";
    private String CREATE_REMIND_TABLE = "CREATE TABLE " + sand_remind + "(" +
            "sand_remindID INTEGER primary key autoincrement, " +
            "userName  varchar(100) NOT NULL," +
            "remindTime VARCHAR(50) NOT NULL," +
            "sand_remark text);";


    /**
     * 登录用户信息表
     */
    public final static String LOGIN_USER_INFO = "LOGIN_USER_INFO";
    private String CREATE_USER_TABLE = "CREATE TABLE " + LOGIN_USER_INFO + "( " +
            "LOGIN_ID    INTEGER           not null," +
            "userId   INTEGER,  " +
            "userName    VARCHAR(20)," +
            "mobileNumber  VARCHAR(15) , " +
            "successful VARCHAR(20),  " +
            "result VARCHAR(10) , " +
            "msgFlg VARCHAR(10),"+
            "constraint P_Key_1 primary key(LOGIN_ID))";
    /**
     * 客户信息表
     */
    public final static String CLIENT_INFO = "CLIENT_INFO";
    private String CREATE_CLIENT_TABLE = "CREATE TABLE " + CLIENT_INFO + "("
            + "SHOPID INTEGER primary key autoincrement,"
            + "SHOPNAME VARCHAR(60),"
            + "ACCESSTIME VARCHAR(15),"
            + "ISCORE VARCHAR(4),"
            + "TIP VARCHAR(10),"
            + "LEVELFLG VARCHAR(10),"
            + "LASTPAGE VARCHAR(4),"
            + "SHOP_ADDRESS VARCHAR(60),"
            + "STORECITIESADDRESS VARCHAR(60),"
            + "DATEEXCEPTION VARCHAR(10),"
            + "CLIENTCOLOR VARCHAR(6),"
            + "TASKTIME VARCHAR(20),"
            + "TASKTYPE VARCHAR(10),"
            + "FLAGMONITOR INTEGER(10)) ";

    public DBSQLiteHelper(Context context, String DBNAME, int VERSION) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("创建start");
        db.execSQL(CREATE_READ_TABLE);
        db.execSQL(CREATE_PHOTO_TABLE);
        db.execSQL(CREATE_REMIND_TABLE);
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_CLIENT_TABLE);
    }


    @Override
    public synchronized SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }

    /**
     * 数据库升级时调用
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        String sql = "alter TABLE Test1 add android varchar(30)";
//        db.execSQL(sql);
    }

}
