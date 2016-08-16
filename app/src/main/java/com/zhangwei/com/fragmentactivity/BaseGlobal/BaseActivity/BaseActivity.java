package com.zhangwei.com.fragmentactivity.BaseGlobal.BaseActivity;

import android.os.Bundle;

import com.zhangwei.com.fragmentactivity.BaseGlobal.Global.MyApplication;
import com.zhangwei.framelibs.Global.AbstractClass.ApplicationActivity;


/**
 * Created by Administrator on 14-3-21.
 * <p/>
 * 父类Activity
 */
public class BaseActivity extends ApplicationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public MyApplication fetchApplication() {
        return super.fetchApplication();
    }

}
