package com.zhangwei.com.fragmentactivity.BaseGlobal.BaseActivity;

import android.os.Bundle;

import com.zhangwei.com.fragmentactivity.BaseGlobal.Global.MyApplication;
import com.zhangwei.framelibs.CustomControl.CustomTitleBarBackControl;
import com.zhangwei.framelibs.Global.AbstractClass.ApplicationTitleBarActivity;


/**
 * Created by Administrator on 2015/1/13.
 * <p/>
 * 基类带title的Activity
 */
public class BaseTitleBarActivity extends ApplicationTitleBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initTitleBar(CustomTitleBarBackControl titleBar) {

    }

    @Override
    public MyApplication fetchApplication() {
        return super.fetchApplication();
    }
}
