package com.zhangwei.framelibs.Global.AbstractClass;

import android.content.ContentValues;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2014/5/16.
 * <p/>
 * 实体的基类
 */
public abstract class AbstractBaseEntity implements Serializable {
    protected String tableName;

    public ContentValues fetchContentValues() {
        return toContentValues(this);
    }

    /**
     * 将对象转换成List<ContentValues>
     */
    public static <T extends AbstractBaseEntity> List<ContentValues> fetchContentValuesAll(List<T> objects) {
        List<ContentValues> list = new ArrayList<ContentValues>();
        for (T entity : objects) {
            list.add(toContentValues(entity));
        }
        return list;
    }


    /**
     * 将对象转换成ContentValues
     */
    private static <T> ContentValues toContentValues(T entity) {
        ContentValues contentValues = new ContentValues();
        Field[] fields = entity.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            String name = "get" + firstCharToUpper(fields[i].getName());
            Method getMethod;//获得get方法
            try {
                getMethod = entity.getClass().getMethod(name);
                Object object = getMethod.invoke(entity);//执行get方法返回一个Object
                if (object instanceof String) {
                    if (!BaseGlobal.isEmpty(object.toString()))
                        contentValues.put(fields[i].getName(), object.toString());
                }
                if (object instanceof Integer) {
                    if (!BaseGlobal.isEmpty(object.toString()))
                        contentValues.put(fields[i].getName(), Integer.parseInt(object.toString()));
                }
                if (object instanceof Boolean) {
                    if (!BaseGlobal.isEmpty(object.toString()))
                        contentValues.put(fields[i].getName(), Boolean.parseBoolean(object.toString()));
                }
                if (object instanceof Date) {
                    if (!BaseGlobal.isEmpty(object.toString())) {
                        Date date = (Date) object;
                        contentValues.put(fields[i].getName(),
                                date.getTime() + "");
                    }
                }
                if (object instanceof Long) {
                    if (!BaseGlobal.isEmpty(object.toString()))
                        contentValues.put(fields[i].getName(), Long.parseLong(object.toString()));
                }
                if (object instanceof List) {
                    if (object != null
                            && ((List<? extends AbstractBaseEntity>) object).size() > 0)
                        fetchContentValuesAll((List<? extends AbstractBaseEntity>) object);
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return contentValues;
    }

    /**
     * 将单词的首字母变成大写
     */
    private static String firstCharToUpper(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
