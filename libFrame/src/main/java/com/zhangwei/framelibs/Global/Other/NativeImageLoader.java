package com.zhangwei.framelibs.Global.Other;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.util.LruCache;

/**
 * 处理本地图片缓存
 */
public class NativeImageLoader {
    private LruCache<String, Bitmap> mMemoryCache;//用来缓存图片的对象
    private static NativeImageLoader mInstance;

    private NativeImageLoader() {
        final int maxMemory = (int) Runtime.getRuntime().maxMemory() / 1024;
        final int cacheSize = maxMemory / 4;
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {//设置缓存的大小
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // 重写此方法来衡量每张图片的大小，默认返回图片数量。
                int size = bitmap.getRowBytes() * bitmap.getHeight() / 1024;
                if (size > 1 * 1024) {//大于一兆的图片直接recycle掉
                    if (bitmap != null && bitmap.isRecycled())
                        bitmap.recycle();
                }
                return size;
            }
        };
    }

    public static NativeImageLoader getInstance() {
        if (mInstance == null) {
            mInstance = new NativeImageLoader();
        }
        return mInstance;
    }

    /**
     * 添加要缓存的图片
     *
     * @param key    缓存的名称 方便获取
     * @param bitmap 缓存的图片
     */
    private synchronized void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null && bitmap != null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    /**
     * @param key 缓存的名称
     */
    public synchronized Bitmap getBitmapFromMemCache(String key) {
        if (mMemoryCache != null) {
            return mMemoryCache.get(key);
        }
        return null;
    }

    /**
     * 移除缓存的图片
     *
     * @param key 图片名称
     */
    public synchronized void removeBitmap(String key) {
        try {
            if (key != null) {
                if (mMemoryCache != null) {
                    Bitmap bm = mMemoryCache.remove(key);
                    if (bm != null) {
                        bm.recycle();
                        System.gc();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 清空所有缓存
     */
    public void clearCache() {
        if (mMemoryCache != null) {
            if (mMemoryCache.size() > 0) {
                mMemoryCache.evictAll();
            }
            mMemoryCache = null;
        }
    }

    public void addBitmap(String key, Bitmap bitmap) {
        addBitmapToMemoryCache(key, bitmap);
    }

    private Handler handler = new Handler(Looper.getMainLooper());


    public void loadImage(String path, Point mPoint, NativeImageCallBack mCallBack) {
        loadImage(path, mPoint, mCallBack, true);
    }

    /**
     * 获取本地图片
     *
     * @param path      图片路径
     * @param mPoint    控件的高宽
     * @param mCallBack 图片的回调函数
     * @param flag      原图为false  默认为true
     */
    public void loadImage(final String path, final Point mPoint,
                          final NativeImageCallBack mCallBack, final boolean flag) {
        ThreadManager.getInstance().addThread(new Runnable() {
            @Override
            public void run() {
                if (path == null) {
                    return;
                }
                final Bitmap finalBitmap = BitmapUtils.getInstance().decodeThumbBitmap(path,
                        mPoint.x, mPoint.y, flag);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (mCallBack != null)
                            mCallBack.onImageLoader(finalBitmap, path);
                    }
                });
            }
        });
    }


    public interface NativeImageCallBack {
        void onImageLoader(Bitmap bitmap, String path);
    }
}
