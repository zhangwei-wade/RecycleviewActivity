package com.zhangwei.com.fragmentactivity.BaseGlobal.BaseFragment;

import android.os.Bundle;

import com.zhangwei.com.fragmentactivity.BaseGlobal.Global.MyApplication;
import com.zhangwei.framelibs.Global.AbstractClass.ApplicationTitleBarFragment;

/**
 * Created by Administrator on 2015/1/13.
 * <p/>
 * 基类带title的Fragment
 */
public class BaseTitleBarFragment extends ApplicationTitleBarFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public MyApplication fetchApplication() {
        return super.fetchApplication();
    }
}
