package com.zhangwei.framelibs.Global.InterfaceClass;

import android.view.MotionEvent;

/**
 * Created by Administrator on 2014/8/25.
 */
public interface GridViewOnTouch {
    void onDown(MotionEvent event);

    void onMove(MotionEvent event);

    void onUp(MotionEvent event);

}
