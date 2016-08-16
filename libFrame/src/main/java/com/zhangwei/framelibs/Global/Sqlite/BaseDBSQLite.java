package com.zhangwei.framelibs.Global.Sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


/**
 * 实现DB数据的增、删、改、查
 */

public class BaseDBSQLite extends DBSQLiteHelper {
    private static final String DBNAME = "sand-db";
    public static final int VERSION = 1;
    private SQLiteDatabase db;
    private static BaseDBSQLite baseDBSQLite;

    public BaseDBSQLite(Context context) {
        super(context, DBNAME, VERSION);
    }

    public static BaseDBSQLite getInstance(Context context) {
        if (baseDBSQLite == null) {
            baseDBSQLite = new BaseDBSQLite(context);
        }
        return baseDBSQLite;
    }

    public boolean deleteAll(String table) {
        getWritable();
        boolean flag = DBSQLiteFunction.getInstance(db).deleteAll(table);
        DBClose();
        return flag;
    }

    public boolean deleteById(String table, String key, String value) {
        return deleteById(table, key, new String[]{value});
    }

    public boolean deleteById(String table, String key, String[] value) {
        getWritable();
        boolean flag = DBSQLiteFunction.getInstance(db).deleteById(table, key, value);
        DBClose();
        return flag;
    }


    public boolean insertData(String table, ContentValues value) {
        List<ContentValues> list = new ArrayList<>();
        list.add(value);
        return insertData(table, list);
    }


    public boolean insertData(String table, ContentValues value, String[] update) {
        List<ContentValues> list = new ArrayList<>();
        list.add(value);
        return insertData(table, list, update);
    }


    public boolean insertData(String table, List<ContentValues> values) {
        return insertData(table, values, null);
    }


    public boolean insertData(String table, List<ContentValues> values, String[] update) {
        getWritable();
        boolean flag = DBSQLiteFunction.getInstance(db).insertData(table, values, update);
        DBClose();
        return flag;
    }


    public boolean replaceData(String table, String nullColumnHack, ContentValues initialValues) {
        List<ContentValues> list = new ArrayList<>();
        if (initialValues != null)
            list.add(initialValues);
        return replaceData(table, nullColumnHack, list);
    }


    public boolean replaceData(String table, String nullColumnHack, List<ContentValues> values) {
        getWritable();
        boolean flag = DBSQLiteFunction.getInstance(db).replaceData(table, nullColumnHack, values);
        DBClose();
        return flag;
    }


    public boolean updateData(String table, List<ContentValues> contentValuesList, String whereCause) {
        getWritable();
        boolean flag = DBSQLiteFunction.getInstance(db).updateData(table, contentValuesList, whereCause);
        DBClose();
        return flag;
    }


    public boolean updateData(String table, ContentValues contentValues,
                              String whereCause, String[] whereArgs) {
        getWritable();
        boolean flag = DBSQLiteFunction.getInstance(db).updateData(table, contentValues, whereCause
                , whereArgs);
        DBClose();
        return flag;
    }


    public int getCountSql(String sql) {
        getWritable();
        int count = DBSQLiteFunction.getInstance(db).getCountSql(sql);
        DBClose();
        return count;
    }


    public int getTableDataCount(String table, String where) {
        getWritable();
        int count = DBSQLiteFunction.getInstance(db).getTableDataCount(table, where);
        DBClose();
        return count;
    }


    public String getTableJsonData(String table) {
        return getTableJsonData(table, null);
    }


    public String getTableJsonData(String table, String[] columns) {
        return getTableJsonData(table, columns, null, null);
    }


    public String getTableJsonData(String table, String[] columns, String selection, String[] selectionArgs) {
        return getTableJsonData(table, columns, selection, selectionArgs, null, null, null, null);
    }


    public String getTableJsonData(String table, String[] columns, String selection,
                                   String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
        getReadable();
        String json = DBSQLiteFunction.getInstance(db).getTableJsonData(table, columns, selection, selectionArgs, groupBy,
                having, orderBy, limit);
        DBClose();
        return json;
    }

    public String getTableSql(String sql, String[] str) {
        getReadable();
        String json = DBSQLiteFunction.getInstance(db).getTableSql(sql, str);
        DBClose();
        return json;
    }

    private void getReadable() {
        if (db == null || !db.isReadOnly())
            db = getReadableDatabase();
    }

    private void getWritable() {
        if (db == null || !db.isOpen())
            db = getWritableDatabase();
    }

    private synchronized void DBClose() {
        if (db != null && db.isOpen()) {
            db.close();
            db = null;
        }
    }

    /**
     * 初始化数据库
     */
    public void DBCreate() {
        getWritableDatabase();
        close();
    }

}
