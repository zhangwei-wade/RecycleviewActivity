package com.zhangwei.framelibs.Global.Other;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.zhangwei.framelibs.Global.AbstractClass.BaseGlobal;
import com.zhangwei.framelibs.Global.InterfaceClass.OnRefreshViewListener;

/**
 * Created by wade on 2015/10/15.
 * <p/>
 * 刷新控件广播
 */
public class RefreshViewReceiver {
    private OnRefreshViewListener onRefreshViewListener;


    private BroadcastReceiver connectionReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(BaseGlobal.VIEW_REFRESH_RECEIVED_ACTION)) {
                if (onRefreshViewListener != null) {
                    onRefreshViewListener.onRefreshView(intent);
                }
            }
        }
    };


    /**
     * 注册更新view广播
     */
    public void onRegisterReceiver(Context context) {
        if (connectionReceiver != null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(BaseGlobal.VIEW_REFRESH_RECEIVED_ACTION);
            context.registerReceiver(connectionReceiver, intentFilter);
        }
    }

    /**
     * 取消更新view广播
     */
    public void UnRegisterReceiver(Context context) {
        try {
            if (connectionReceiver != null)
                context.unregisterReceiver(connectionReceiver);
        } catch (Exception e) {
        }
    }


    public void setOnRefreshViewListener(OnRefreshViewListener onRefreshViewListener) {
        this.onRefreshViewListener = onRefreshViewListener;
    }
}
