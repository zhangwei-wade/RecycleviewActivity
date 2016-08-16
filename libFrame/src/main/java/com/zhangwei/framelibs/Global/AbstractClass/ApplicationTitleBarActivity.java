package com.zhangwei.framelibs.Global.AbstractClass;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zhangwei.framelibs.CustomControl.CustomTitleBarBackControl;
import com.zhangwei.framelibs.Global.InitView;
import com.zhangwei.framelibs.R;

/**
 * Created by Administrator on 2014/11/7.
 * <p/>
 * 带有TitleBar 的Activity
 * <p/>
 * setContentViewFun必须用这个方法添加view
 */
public abstract class ApplicationTitleBarActivity extends ApplicationActivity
        implements CustomTitleBarBackControl.OnTitleBarBackListenClick {
    private CustomTitleBarBackControl titleBar;
    private LinearLayout group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_bar_activity);
        titleBar = $.findViewById(R.id.titleBar);
        titleBar.setOnTitleBarBackListenClick(this);
        group = $.findViewById(R.id.group);
        initTitleBar(titleBar);
    }

    public void setContentViewFun(int layoutResID) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.
                LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        InitView $ = new InitView(this, layoutResID, null);
        group.addView($.getView(), layoutParams);
    }

    public void setContentViewFun(View view) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.
                LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        group.addView(view, layoutParams);
    }

    protected abstract void initTitleBar(CustomTitleBarBackControl titleBar);

    /**
     * 控件的返回按钮
     */
    @Override
    public void onLeftClick() {
        onBackPressed();
        // finish();
    }

    @Override
    public void finish() {
        super.finish();
    }
}
