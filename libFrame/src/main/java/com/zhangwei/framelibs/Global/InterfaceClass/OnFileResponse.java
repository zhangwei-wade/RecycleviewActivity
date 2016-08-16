package com.zhangwei.framelibs.Global.InterfaceClass;

import android.graphics.Bitmap;

/**
 * Created by wade on 2016/8/1.
 * <p/>
 * 文件下载回调事件
 */
public interface OnFileResponse {
    void onLoading(Bitmap bitmap, String imageUrl, String path);

    void onProgress(String url, int current, int fileSize);
}

