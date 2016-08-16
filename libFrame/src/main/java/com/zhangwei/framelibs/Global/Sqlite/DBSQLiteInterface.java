package com.zhangwei.framelibs.Global.Sqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by wade on 2015/12/4.
 * <p/>
 * 访问DB数据的增删改成的方法
 */
public interface DBSQLiteInterface {
    /**
     * 删除数据
     *
     * @param table 表名
     */
    boolean deleteAll(String table);

    /**
     * 删除数据
     *
     * @param table 表名
     * @param key   表的属性名
     * @param value key对应的value值
     */
    boolean deleteById(String table, String key, String[] value);

    /**
     * 添加数据
     * @param table 表名
     *
     */
    boolean insertData(String table, List<ContentValues> values, String[] update);

    boolean replaceData(String table, String nullColumnHack, List<ContentValues> values);

    boolean updateData(String table, ContentValues contentValues, String whereCause, String[] whereArgs);

    boolean updateData(String table, List<ContentValues> contentValuesList, String whereCause);

    int getCountSql(String sql);

    int getTableDataCount(String table, String where);

    String getTableJsonData(String table, String[] columns, String selection, String[] selectionArgs,
                            String groupBy, String having, String orderBy, String limit);

    String getTableSql(String sql, String[] str);
}
