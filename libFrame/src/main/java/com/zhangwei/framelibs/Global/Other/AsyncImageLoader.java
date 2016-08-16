package com.zhangwei.framelibs.Global.Other;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Handler;
import android.widget.ImageView;

import com.zhangwei.framelibs.Global.AbstractClass.BaseApplication;
import com.zhangwei.framelibs.Global.AbstractClass.BaseGlobal;
import com.zhangwei.framelibs.Global.InterfaceClass.OnFileResponse;
import com.zhangwei.framelibs.Global.WebAPI.APIResponse;

import java.io.File;

/**
 * 图片的下载首先判断内存中是否存在图片，
 * 如果没有就去本地查找，本地不存在再去
 * 服务器下载图片。（下载的图片会存在本地）shu
 * <p/>
 * *
 */
public class AsyncImageLoader {
    private static AsyncImageLoader asyncImageLoader;

    public static AsyncImageLoader getInstance() {
        if (asyncImageLoader == null)
            asyncImageLoader = new AsyncImageLoader();
        return asyncImageLoader;
    }


    /**
     * 通过url获取图片
     */
    public void fetchBitmap(Context context, Point mPoint, ImageView imageView, String imageUrl) {
        File file = BaseGlobal.fetchImagePathFile(context,
                imageUrl);
        fetchImage(context, file, mPoint, imageUrl, imageView);
    }

    /**
     * 如果本地图片不存在就去服务器下載
     */
    private void fetchImage(Context context, File file, Point mPoint,
                            String imageUrl, final ImageView imageView) {
        downloadBitmap(context, file, mPoint, imageUrl,
                new OnFileResponse() {
                    @Override
                    public void onLoading(Bitmap bitmap, String imageUrl, String path) {
                        if (imageView != null && bitmap != null) {
                            BaseGlobal.invalidate(imageView, bitmap);
                        }
                    }

                    @Override
                    public void onProgress(String url, int current, int fileSize) {

                    }
                }
        );
    }


    //通过url获取图片
    public void fetchBitmap(Context context, Point mPoint, String imageUrl, OnFileResponse onFileResponse) {
        fetchBitmap(context, mPoint, imageUrl, onFileResponse, true);
    }


    //通过url获取图片
    public void fetchBitmap(Context context, Point mPoint, String imageUrl, OnFileResponse onFileResponse, boolean flag) {
        File file = BaseGlobal.fetchImagePathFile(context,
                imageUrl);
        downloadBitmap(context, file, mPoint, imageUrl, onFileResponse, flag);
    }


    /**
     * 载入一张图片  先从内存中取 其次在本地缓存文件中取 最后在网络取
     * <p/>
     * 如果本地或者内存中不存在这张图片，就去服务器获取
     *
     * @return
     */
    public void downloadBitmap(final Context context, final File file,
                               final Point mPoint, final String imageUrl, final OnFileResponse onFileResponse) {
        downloadBitmap(context, file, mPoint, imageUrl, onFileResponse, true);
    }

    /**
     * 载入一张图片  先从内存中取 其次在本地缓存文件中取 最后在网络取
     * <p/>
     * 如果本地或者内存中不存在这张图片，就去服务器获取
     *
     * @return
     */
    public void downloadBitmap(final Context context, final File file,
                               final Point mPoint, final String imageUrl, final OnFileResponse onFileResponse, boolean flag) {
        BaseGlobal.playLog(mPoint.toString());
        if (file.exists() && file.length() != 0) {
            NativeImageLoader.getInstance().loadImage(file.getPath(), mPoint,
                    new NativeImageLoader.NativeImageCallBack() {
                        @Override
                        public void onImageLoader(Bitmap bitmap, String path) {
                            if (bitmap != null)
                                onFileResponse.onLoading(bitmap, imageUrl, path);
                            else {
                                file.delete();
                                downloadBitmap(context, file, mPoint, imageUrl, onFileResponse, true);
                            }
                        }
                    }, flag);
            return;
        }
        downBitmap(context, file, mPoint, imageUrl, onFileResponse, flag);
    }


    /**
     * 请求服务器下载图片
     */
    private void downBitmap(final Context context, final File file,
                            final Point mPoint, final String imageUrl, final OnFileResponse onFileResponse, boolean flag) {
        BaseApplication baseApplication = (BaseApplication) context.getApplicationContext();
        baseApplication.fetchWebAPI().onLoadingFile(file, imageUrl, new APIResponse<String>(context) {
            @Override
            public void onSuccess(String str) {
                if (BaseGlobal.Success.equals(str)) {
                    Bitmap bitmap = BitmapUtils.getInstance().decodeThumbBitmap(file.getPath(), mPoint.x, mPoint.y);
                    onFileResponse.onLoading(bitmap, imageUrl, file.getPath());
                }
            }

            @Override
            public void onFailure(String str) {//如果网络异常就每隔五秒再去访问服务器下载数据
                if (str.equals(BaseGlobal.ConnectionTimeOut) || str.equals(BaseGlobal.InternetConnectionFailure)) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            downloadBitmap(context, file, mPoint, imageUrl, onFileResponse);
                        }
                    }, 10 * 1000);
                }
            }

            @Override
            public void onProgress(int current, int fileSize) {
                super.onProgress(current, fileSize);
                onFileResponse.onProgress(imageUrl, current, fileSize);
            }
        });
    }
}
