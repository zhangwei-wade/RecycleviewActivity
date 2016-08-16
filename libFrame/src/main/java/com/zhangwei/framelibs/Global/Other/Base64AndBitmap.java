package com.zhangwei.framelibs.Global.Other;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.util.Base64;

import com.zhangwei.framelibs.Global.AbstractClass.BaseGlobal;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2014/4/4.
 * <p/>
 * 将图片转换成base64
 */
public class Base64AndBitmap {

    /**
     * 获取缓存中的图片,将图片转换成bas64 上传服务器
     */
    public static String getPathToBitmap(String path, int state, Point point) {
        try {
            Bitmap bitmapFactory = BitmapUtils.getInstance().decodeThumbBitmap(path, point.x, point.y, false);
            String base64String = getBitmapStrBase64(bitmapFactory, state);
            NativeImageLoader.getInstance().removeBitmap(BaseGlobal.fetchUrlDownName(path) + point.x + point.y);
            return base64String;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    /**
     * 获取缓存中的图片
     */
    public static byte[] getPathToByte(String path, int state, Point point) {
        try {
            Bitmap bitmapFactory = BitmapUtils.getInstance().decodeThumbBitmap(path, point.x, point.y);
            return getBitmapByte(bitmapFactory, state);
        } catch (Exception e) {
            return null;
        }
    }


    private static String getBitmapStrBase64(Bitmap bitmap, int state) {
        byte[] bytes = getBitmapByte(bitmap, state);
        if (bytes == null) {
            return "";
        }
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    private static byte[] getBitmapByte(Bitmap bitmap, int state) {
        byte[] bytes = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            switch (state) {
                case 0:
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 95, baos);
                    break;
                case 1:
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
                    break;
                case 2:
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
                    break;
            }
            bytes = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bitmap != null && bitmap.isRecycled())
                bitmap.recycle();
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }


    /**
     * 把Base64转换成Bitmap
     */
    public Bitmap getBitmap(String iconBase64) {
        byte[] bitmapArray;
        bitmapArray = Base64.decode(iconBase64, 0);
        return BitmapFactory
                .decodeByteArray(bitmapArray, 0, bitmapArray.length);
    }
}
