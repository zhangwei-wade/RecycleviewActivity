package com.zhangwei.framelibs.CustomControl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.zhangwei.framelibs.Global.WebAPI.APIResponse;
import com.zhangwei.framelibs.R;

/**
 * Created by Administrator on 2014/8/15.
 *
 *
 * 传入下载进度的APIRespons让控件显示当前的下载进度
 *
 */
public class TextViewProgress extends TextView implements APIResponse.APIResponseDownLoadInterface {
    public TextViewProgress(Context context) {
        super(context, null);
    }

    public TextViewProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setAPIRespons(APIResponse apiResponse) {
        if (apiResponse != null)
            apiResponse.setApiResponseDownLoadInterface(this);
    }

    @Override
    public void Progress(int current, int fileLength) {
        if (current != fileLength) {
            setText(current + "%");
        } else setText(getResources().getString(R.string.downLoadInstall));
    }
}
