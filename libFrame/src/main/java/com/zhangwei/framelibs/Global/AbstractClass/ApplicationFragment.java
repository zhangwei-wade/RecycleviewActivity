package com.zhangwei.framelibs.Global.AbstractClass;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhangwei.framelibs.Global.InitView;
import com.zhangwei.framelibs.Global.InterfaceClass.OnRefreshViewListener;

/**
 * Created by admin on 13-11-23.
 * <p/>
 * 基类的Fragment
 * <p/>
 * 主要是调用了ApplicationActivity的方法
 * 让子Fragment方便的调用
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public abstract class ApplicationFragment extends Fragment implements OnRefreshViewListener {
    public InitView $;
    private int layoutId = 0;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public <T extends BaseApplication> T fetchApplication() {
        if (getActivity() == null) {
            return null;
        }
        return ((ApplicationActivity) getActivity()).fetchApplication();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (layoutId != 0)
            $ = new InitView(getActivity(), getContentView(), null);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return $.getView();
    }

    public int getContentView() {
        return layoutId;
    }

    /**
     * 必须在onAttach中调用
     */
    public void setContentView(int layoutId) {
        this.layoutId = layoutId;
    }

    public Context getContext() {
        return getActivity();
    }

    /**
     * 获取屏幕的宽度
     */
    public int getScreenWidth() {
        return ((ApplicationActivity) getActivity()).getScreenWidth();
    }

    /**
     * 获取屏幕的高度
     */
    public int getScreenHeight() {
        return ((ApplicationActivity) getActivity()).getScreenHeight();
    }

    /**
     * 获取状态栏的高度
     */
    public int statusBarHeight() {
        return ((ApplicationActivity) getActivity()).getStatusBarHeight();
    }

    /**
     * 获取资源文件的string字符
     */
    public String getResourcesString(int id) {
        return getResources().getString(id);
    }

    /**
     * 移除当前的fragment
     */
    public void RemoveThisFragment() {
        getFragmentManager().beginTransaction().remove(this).commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 广播刷新View
     */
    @Override
    public void onRefreshView(Intent intent) {
    }


}
