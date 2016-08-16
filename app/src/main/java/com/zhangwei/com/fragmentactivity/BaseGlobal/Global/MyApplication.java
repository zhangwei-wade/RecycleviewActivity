package com.zhangwei.com.fragmentactivity.BaseGlobal.Global;

import com.zhangwei.framelibs.Global.AbstractClass.BaseApplication;
import com.zhangwei.framelibs.Global.Other.BaseWebAPI;


/**
 * Created by Administrator on 14-3-24.
 */
public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        fetchDBSQLite().DBCreate();
    }


    @Override
    public BaseWebAPI fetchWebAPI() {
        return WebAPI.getInstance();
    }

}
