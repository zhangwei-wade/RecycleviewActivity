package com.zhangwei.framelibs.Global;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 14-3-21.
 */
public class InitView {
    private View view = null;
    private FragmentActivity activity;

    public InitView(FragmentActivity activity) {
        this.activity = activity;
    }

    public InitView(Context context, int layout) {
        Init(context, layout, null);
    }

    public InitView(Context context, int layout, ViewGroup viewGroup) {
        Init(context, layout, viewGroup);
    }

    private void Init(Context context, int layout, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(layout, viewGroup);
    }


    /**
     * 控件的强制转换
     */
    public <T extends View> T findViewById(int id) {
        if (view == null) {
            return (T) activity.getWindow().getDecorView().findViewById(id);
        } else
            return (T) view.findViewById(id);
    }

    public View getView() {
        if (view != null)
            return view;
        else
            return activity.getWindow().getDecorView();
    }

    public Context getContext() {
        if (view != null)
            return view.getContext();
        else
            return activity.getApplicationContext();
    }

}
