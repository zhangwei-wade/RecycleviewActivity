package com.zhangwei.framelibs.Global.AbstractClass;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Administrator on 2014/7/16.
 * <p/>
 * 封装实体和对象之间的转换
 */
public abstract class AbstractGson<T> {
    private Gson gson;
    private Type entityClass;

    public AbstractGson() {//将json转换成对象
        gson = new Gson();
    }

    private void Init() {
        Type genType = getClass().getGenericSuperclass();//获取当前类的类型
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        entityClass = params[0];//得到T这个对象的type
    }

    public T fromJson(String json) {
        Init();
        try {
            if (json != null)
                return (T) gson.fromJson(json, entityClass);
            else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public String toJson(Object object) {
        try {
            if (object != null) {
                return gson.toJson(object);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
