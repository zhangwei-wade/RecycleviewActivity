package com.zhangwei.framelibs.Global.AbstractClass;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zhangwei.framelibs.CustomControl.CustomTitleBarBackControl;
import com.zhangwei.framelibs.Global.InitView;
import com.zhangwei.framelibs.R;

/**
 * Created by Administrator on 2014/11/7.
 * <p/>
 * 带有TitleBar 的 Fragment
 * <p/>
 * setContentViewFun必须用这个方法添加view
 */
public abstract class ApplicationTitleBarFragment extends ApplicationFragment implements CustomTitleBarBackControl.OnTitleBarBackListenClick {
    protected CustomTitleBarBackControl titleBar;
    private LinearLayout group;
    private String title;
    private String url;
    private String context;
    private ImageView imageView_share;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setContentView(R.layout.title_bar_activity);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        titleBar = $.findViewById(R.id.titleBar);
        imageView_share = $.findViewById(R.id.imageView_share);
        titleBar.setOnTitleBarBackListenClick(this);
        titleBar.setVisibility(View.VISIBLE);
        group = $.findViewById(R.id.group);
        title = getArguments().getString(BaseGlobal.title);
        url = getArguments().getString(BaseGlobal.url);
        context = getArguments().getString(BaseGlobal.context);
        titleBar.setTitleText(title);
    }


    public void setContentViewFun(int layoutResID) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.
                LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        InitView activity = new InitView(getActivity(), layoutResID, null);
        group.addView(activity.getView(), layoutParams);
    }

    public void setContentViewFun(View view) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.
                LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        group.addView(view, layoutParams);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onLeftClick() {
        getActivity().onBackPressed();
    }

}
