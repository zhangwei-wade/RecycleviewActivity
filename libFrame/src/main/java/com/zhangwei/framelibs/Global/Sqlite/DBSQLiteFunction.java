package com.zhangwei.framelibs.Global.Sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by wade on 2015/12/4.
 */
public class DBSQLiteFunction implements DBSQLiteInterface {
    private static DBSQLiteInterface dbSQLiteFunction;
    private static SQLiteDatabase db;

    /**
     * @param db 一个能操作数据的对象，db的打开和关闭不在这个类里面执行
     */
    public static DBSQLiteInterface getInstance(SQLiteDatabase db) {
        if (dbSQLiteFunction == null) {
            dbSQLiteFunction = new DBSQLiteFunction(db);
        }
        return dbSQLiteFunction;
    }

    public DBSQLiteFunction(SQLiteDatabase db) {
        this.db = db;
    }


    /**
     * 删除数据
     */
    @Override
    public synchronized boolean deleteAll(String table) {
        int delete = 0;
        try {
            delete = db.delete(table, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return delete != 0 ? true : false;
    }


    /**
     * 删除数据
     */
    @Override
    public synchronized boolean deleteById(String table, String key, String[] value) {
        int delete = 0;
        try {
            delete = db.delete(table, key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return delete == 0 ? true : false;
    }

    /**
     * 插入数组
     *
     * @param table  表名
     * @param values 要保存的数据
     * @param update 用来判断当前字段数据是否存在相同如果存在就update否则就添加
     */
    @Override
    public synchronized boolean insertData(String table, List<ContentValues> values, String[] update) {
        try {
            long result = -1;
            db.beginTransaction();
            for (ContentValues contentValue : values) {
                int count = 0;
                String whereClause = "";
                String[] whereArgs = null;
                if (update != null && update.length > 0) {
                    whereArgs = new String[update.length];
                    for (int i = 0; i < update.length; i++) {
                        whereClause += update[i] + " = ? ";
                        if (i < update.length - 1)
                            whereClause += "and ";
                        whereArgs[i] = contentValue.getAsString(update[i]);
                    }
                    Cursor cursor = db.query(table, new String[]{"count(*)"}, whereClause, whereArgs, null, null, null);
                    if (cursor.moveToFirst()) {
                        count = cursor.getInt(0);
                    }
                }
                if (count > 0 && whereArgs != null) {
                    result = db.update(table, contentValue, whereClause, whereArgs);
                } else
                    result = db.insert(table, null, contentValue);
            }
            return (result != -1) ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.setTransactionSuccessful();
            db.endTransaction();
            values.clear();
        }
        return false;
    }


    /**
     * 插入或更新多条数据
     */
    @Override
    public synchronized boolean replaceData(String table, String nullColumnHack, List<ContentValues> values) {
        try {
            long result = -1;
            db.beginTransaction();
            for (ContentValues contentValue : values) {
                result = db.replace(table, null, contentValue);
            }
            return (result != -1) ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.setTransactionSuccessful();
            db.endTransaction();
            values.clear();
        }
        return false;
    }

    /**
     * 修改数据
     */
    @Override
    public synchronized boolean updateData(String table, ContentValues contentValues, String whereCause, String[] whereArgs) {
        long result = -1;
        try {
            result = db.update(table, contentValues, whereCause, whereArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (result != -1) ? true : false;
    }

    /**
     * 修改数据
     */
    @Override
    public synchronized boolean updateData(String table, List<ContentValues> contentValuesList, String whereCause) {
        long result = -1;
        try {
            db.beginTransaction();
            result = -1;
            for (ContentValues contentValues : contentValuesList) {
                result = db.update(table, contentValues, whereCause + "=?",
                        new String[]{contentValues.getAsString(whereCause)});
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.setTransactionSuccessful();
            db.endTransaction();
            contentValuesList.clear();
        }
        return (result != -1) ? true : false;
    }

    /**
     * 获取表的个数
     */
    @Override
    public synchronized int getCountSql(String sql) {
        int count = 0;
        try {
            Cursor cursor = db.rawQuery(sql, null);
            count = cursor.getCount();
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return count;
    }

    /**
     * 查询表字段记录条数
     */
    @Override
    public synchronized int getTableDataCount(String table, String where) {
        int count = 0;
        try {
            Cursor cursor = db.rawQuery("select * from " + table + " where " + where, null);
            count = cursor.getCount();
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
            return count;
        } finally {

        }
        return count;
    }

    /**
     * 查询数据返回json
     */
    @Override
    public synchronized String getTableJsonData(String table, String[] columns, String selection, String[] selectionArgs,
                                                String groupBy, String having, String orderBy, String limit) {
        try {
            Cursor cursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
            String json = toJson(cursor);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 通过sql查询数据
     */
    @Override
    public synchronized String getTableSql(String sql, String[] str) {
        try {
            Cursor cursor = db.rawQuery(sql, str);
            String json = toJson(cursor);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    /**
     * 将Cursor转换成json 数据
     * 返回的是一个数组
     */
    public String toJson(Cursor cursor) {
        String json = "";
        if (cursor.getCount() == 0) {
            return null;
        }
        json += "[";
        if (cursor.moveToFirst()) {
            String[] cursorName = cursor.getColumnNames();
            do {
                String s = "{";
                for (String str : cursorName) {
                    String values = cursor.getString(cursor.getColumnIndex(str));
                    values = values == null ? "" : values;
                    s += "\"" + str + "\":" + "\"" + values + "\"";
                    if (!str.equals(cursorName[cursorName.length - 1])) {
                        s += ",";
                    }
                }
                s += "}";
                if (!cursor.isLast()) {
                    s += ",";
                }
                json += s;
            } while (cursor.moveToNext());
        }
        json += "]";
        cursor.close();
        return json;
    }

}
