package com.zhangwei.framelibs.Global.Other;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2014/11/18.
 * 加载网络数据多线程管理
 * 管理任务的下载
 */
public class ThreadManager {
    private ExecutorService mImageThreadPool;
    private static ThreadManager threadManager = null;
    private int cpuNum;

    private ThreadManager() {
        cpuNum = Runtime.getRuntime().availableProcessors();
        mImageThreadPool = Executors.newFixedThreadPool(cpuNum * 2);//实例化线程池 1代表一次性启动两个线程
    }

    public static ThreadManager getInstance() {
        if (threadManager == null)
            threadManager = new ThreadManager();
        return threadManager;
    }

    public void addThread(Runnable runnable) {
        mImageThreadPool.execute(runnable);//将线程添加到线程池中、
    }
}
