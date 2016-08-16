package com.zhangwei.framelibs.Global.Other;


import android.graphics.Point;
import android.os.Process;

import com.zhangwei.framelibs.Global.AbstractClass.BaseGlobal;
import com.zhangwei.framelibs.Global.WebAPI.APIResponse;
import com.zhangwei.framelibs.Global.WebAPI.AbstractWebAPI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

/**
 * Created by Administrator on 2014/4/2.
 * <p/>
 * 调用父类访问服务器的方法
 * 将其加入线程池中
 */
public class BaseWebAPI extends AbstractWebAPI {

    public String companyInfo = "/api/base/androidUpdate.html";
    public String common = "/action/common.ashx";


    public void onPostEntity(String html) {
        onPostEntity(html, null, null);
    }

    public void onPostEntity(String html, APIResponse apiResponse) {
        onPostEntity(html, null, apiResponse);
    }

    public void onPostEntity(String html, String params) {
        onPostEntity(html, params, null);
    }


    public void onPostEntity(final String html, final String params, final APIResponse apiResponse) {
        if (BaseGlobal.getDownFileProgress(html) != null)//如果当前下载线程存在 那么久不在启动相同的下载线程
            return;
        if (apiResponse != null)
            apiResponse.fetchStart();
        ThreadManager.getInstance().addThread(new Runnable() {//将多线程添加到线程池中
            @Override
            public void run() {
                setThreadProcess();
                BaseGlobal.addDownFileProgress(html, apiResponse);
                postUrl(html, params, apiResponse);
            }
        });
    }

    public void onGetEntity(String html) {
        onGetEntity(html, null, null);
    }

    public void onGetEntity(String html, String param) {
        onGetEntity(html, param, null);
    }

    public void onGetEntity(String html, APIResponse apiResponse) {//map指的是url的参数
        onGetEntity(html, null, apiResponse);
    }


    public void onGetEntity(final String html, final String param, final APIResponse apiResponse) {//map指的是url的参数
        if (apiResponse != null)
            apiResponse.fetchStart();
        ThreadManager.getInstance().addThread(new Runnable() {
            @Override
            public void run() {
                setThreadProcess();
                getUrl(html, param, apiResponse);
            }
        });
    }

    public void onLoadingFile(final File file, final String url, final APIResponse apiResponse) {
        if (apiResponse != null)
            apiResponse.fetchStart();
        if (BaseGlobal.getDownFileProgress(url) != null)//如果当前下载线程存在 那么久不在启动相同的下载线程
            return;
        ThreadManager.getInstance().addThread(new Runnable() {
            @Override
            public void run() {
                setThreadProcess();
                BaseGlobal.addDownFileProgress(url, apiResponse);
                fetchFile(file, url, apiResponse);
            }
        });
    }

    /**
     * 上传原文件
     */
    public void uploadPostFile(String url, Map<String, String> map,
                               Map<String, String> fileMap, APIResponse apiResponse) {
        uploadPostFile(url, map, fileMap, apiResponse, null);
    }

    /**
     * 多文件上传
     */
    public void uploadPostFile(final String url, final Map<String, String> map, final Map<String,
            String> fileMap, final APIResponse apiResponse, final Point mPoint) {
        if (apiResponse != null)
            apiResponse.fetchStart();
        BaseGlobal.addDownFileProgress(fetchFullUrl(url, map), apiResponse);
        ThreadManager.getInstance().addThread(new Runnable() {
            @Override
            public void run() {
                setThreadProcess();
                try {
                    postFile(url, map, fileMap, apiResponse, mPoint);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 设置线程的优先级
     */
    private void setThreadProcess() {
        Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
    }

}
