package com.zhangwei.framelibs.Global.InterfaceClass;

/**
 * Created by Administrator on 2015/3/19.
 */
public interface APIHttpInterface<T> {
    void onStart();

    void onSuccess(T object);

    void onFailure(String str);

    void onFinish();

    void onProgress(int current, int fileSize);
}
