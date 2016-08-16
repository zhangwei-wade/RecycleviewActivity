package com.zhangwei.framelibs.Global.WebAPI;

import android.os.Handler;
import android.os.Message;

/**
 * Created by Administrator on 2014/5/16.
 * <p/>
 * 网络访问后的回调方法
 * <p/>
 * 这个类主要在AbstractWebAPI调用
 */
public abstract class BaseAPIResponse {
    public final int start = 0, success = 1, failure = 2,
            progress = 3, finish = 4;

    private Handler handlerAPI = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            BaseAPIResponse.this.handleMessage(msg);
        }
    };


    /**
     * 开始访问服务器
     */
    public void fetchStart() {
        Message.obtain(handlerAPI, start).sendToTarget();
    }

    /**
     * 读取服务器数据时多线程中获取数据成功时调用的
     */
    public void fetchSuccess(String response_str) {
        Message.obtain(handlerAPI, success, response_str).sendToTarget();
    }

    /**
     * 读取服务器数据时多线程中获取数据失败时调用的
     */
    public void fetchFailure(String response_str) {
        Message.obtain(handlerAPI, failure, response_str).sendToTarget();
    }

    /**
     * 读取服务器数据时多线程中下载文件返回的
     */
    public void fetchProgressLoading(int current, int fileSize) {
        Message.obtain(handlerAPI, progress, current, fileSize).sendToTarget();
    }

    /**
     * 读取服务器数据结束
     */
    public void fetchFinish() {
        Message.obtain(handlerAPI, finish).sendToTarget();
    }

    protected abstract void handleMessage(Message msg);
}
