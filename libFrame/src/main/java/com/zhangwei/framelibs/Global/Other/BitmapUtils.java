package com.zhangwei.framelibs.Global.Other;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;

import com.zhangwei.framelibs.Global.AbstractClass.BaseGlobal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2014/11/19.
 *
 * 加载本地图片
 *
 * <p/>
 */
public class BitmapUtils {
    private static BitmapUtils bitmapUtils;

    public static BitmapUtils getInstance() {
        if (bitmapUtils == null)
            bitmapUtils = new BitmapUtils();
        return bitmapUtils;
    }


    /**
     * 对图片的旋转和截取
     *
     * @param status 该图片是否要进行截取
     */
    public Bitmap rotateBitmap(int angle, Bitmap bitmap, int width, int height, boolean status) {// 图片旋转角度
        // 旋转图片 动作
        float scaleW = ((float) width) / bitmap.getWidth();
        float scaleH = ((float) height) / bitmap.getHeight();
        BaseGlobal.playELog(scaleW + "scale" + scaleH);
//        matrix.setRotate(angle, (float) bitmap.getWidth() / 2,
//                (float) bitmap.getHeight() / 2);
        Matrix matrix = new Matrix();
        matrix.setScale(scaleW, scaleW);
//        Bitmap b = Bitmap.createBitmap(bitmap, 0, 0, bmpWidth, bmpHeight,
//                matrix, true);
        int scanX = 0;
        int scanY = 0;
        int scanWidth = bitmap.getWidth();
        int scanHeight = bitmap.getHeight();
        if (status) {
            scanX = (bitmap.getWidth() - width) > 0 && width > 0 ? (bitmap
                    .getWidth() - width) / 2 : 0;
            scanY = (bitmap.getHeight() - height) > 0 && height > 0 ? (bitmap
                    .getHeight() - height) / 2 : 0;
            scanWidth = (width > bitmap.getWidth() || width == 0) ? bitmap.getWidth()
                    : width;
            scanHeight = (height > bitmap.getHeight() || height == 0) ? bitmap
                    .getHeight() : height;
        }
//        // 创建新的图片
        Bitmap b = Bitmap.createBitmap(bitmap, scanX, scanY, scanWidth, scanHeight, matrix, true);
        return b;
    }

    /**
     * 读取图片的浏览角度
     */
    public int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);// 获取图片当前的浏览角度
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
                default:
                    degree = 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 通过角度获取浏览的值
     */
    public int NumToExifInterface(int num) {
        int browseModeStatus;
        switch (num) {
            case 270:
                browseModeStatus = ExifInterface.ORIENTATION_ROTATE_270;
                break;
            case 90:
                browseModeStatus = ExifInterface.ORIENTATION_ROTATE_90;
                break;
            case 180:
                browseModeStatus = ExifInterface.ORIENTATION_ROTATE_180;
                break;
            default:
                browseModeStatus = ExifInterface.ORIENTATION_UNDEFINED;
                break;
        }
        return browseModeStatus;
    }

    public Bitmap decodeThumbBitmap(String path, int viewWidth, int viewHeight) {
        return decodeThumbBitmap(path, viewWidth, viewHeight, true);
    }

    /**
     * 通过控件的高宽读取缓存中的图片，如果图片不存在就保存并且加入缓存中
     * <p/>
     * 放入缓存时加入控件的高宽目的是因为有查看大图片这个功能
     *
     * @param status 大图片为false  默认为true
     */
    public Bitmap decodeThumbBitmap(String path, int viewWidth, int viewHeight, boolean status) {
        InputStream fis = null;
        Bitmap backBitmap = null;
        try {
            File file = new File(path);
            if (NativeImageLoader.getInstance().getBitmapFromMemCache(file.getName()) != null) {
                return NativeImageLoader.getInstance().getBitmapFromMemCache(file.getName());
            }
            BitmapFactory.Options options = createOptions(path, viewWidth,
                    viewHeight, status);
            fis = new FileInputStream(file);
            backBitmap = BitmapFactory.decodeStream(fis, null, options);
            if (backBitmap == null) {
                return null;
            }
            NativeImageLoader.getInstance().addBitmap(file.getName(), backBitmap);//将图片存入缓存中
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return backBitmap;
    }

    /**
     * 计算缩放比例
     */
    private BitmapFactory.Options createOptions(String path, int viewWidth,
                                                int viewHeight, boolean status) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        if (viewWidth != 0 && viewHeight != 0) {
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, options);
            options.inSampleSize = computeScale(options, viewWidth, viewHeight, status);
            options.inJustDecodeBounds = false;
        }
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        return options;
    }

    /**
     * 根据原图的高宽和控件的高宽算出合适比例
     */
    public int computeScale(BitmapFactory.Options options, int viewWidth,
                            int viewHeight, boolean status) {// 获取图片适合屏幕大小的比例
        int inSampleSize = 1;
        int bitmapWidth = options.outWidth;
        int bitmapHeight = options.outHeight;
        if (viewWidth == 0 && viewHeight == 0) {
            return inSampleSize;
        }
        if (bitmapWidth >= bitmapHeight && bitmapWidth >= viewWidth) {// 如果宽度大的话根据宽度固定大小缩放
            inSampleSize = (bitmapWidth / viewWidth);
            if (bitmapHeight / inSampleSize < viewHeight) {
                inSampleSize = (bitmapHeight / viewHeight);
            }
        } else if (bitmapWidth <= bitmapHeight && bitmapHeight >= viewHeight) {// 如果高度高的话根据宽度固定大小缩放
            inSampleSize = (bitmapHeight / viewHeight);
            if (bitmapWidth / inSampleSize < viewWidth) {//如果缩放宽度比例小于传入的图片
                inSampleSize = (bitmapWidth / viewWidth);//把缩放比例设置成传入图片比例
            }
        }
        return inSampleSize;
    }

    public Bitmap decodeThumbBitmap(Resources resources, int id) {
        return decodeThumbBitmap(resources, id, 0, 0);
    }

    /**
     * 通过流来处理控件的高宽读取图片
     *
     * @param viewWidth  控件的宽度
     * @param viewHeight 控件的高度
     */
    public Bitmap decodeThumbBitmap(Resources resources, int id, int viewWidth,
                                    int viewHeight) {
        Bitmap backBitmap = NativeImageLoader.getInstance().getBitmapFromMemCache(id + "");
        if (backBitmap != null) {
            return backBitmap;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        if (viewHeight != 0 && viewWidth != 0) {
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(resources, id, options);
            options.inJustDecodeBounds = false;
            options.inSampleSize = computeScale(options, viewWidth, viewHeight, true);
        }
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        backBitmap = BitmapFactory.decodeResource(resources, id, options);

        NativeImageLoader.getInstance().addBitmap(id + "", backBitmap);//将图片存入缓存中
        return backBitmap;
    }

    /**
     * 通过流来处理控件的高宽读取图片
     *
     * @param rotate 图片的浏览角度
     */
    public Bitmap decodeThumbBitmap(byte[] data, int viewWidth, int viewHeight,
                                    int rotate) throws IOException {
        Bitmap backBitmap, newBitmap;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data, 0, data.length, options);
        options.inJustDecodeBounds = false;
        options.inSampleSize = computeScale(options, viewWidth, viewHeight, true);
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        backBitmap = BitmapFactory.decodeByteArray(data, 0, data.length,
                options);
        newBitmap = rotateBitmap(rotate, backBitmap, 0, 0, true);
        if (backBitmap != null && backBitmap.isRecycled())
            backBitmap.recycle();
        return newBitmap;
    }

    /**
     * 设置图片的浏览角度
     */
    public void setJPGOrientation(String path, int rotate) {
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            exifInterface.setAttribute(ExifInterface.TAG_ORIENTATION, rotate
                    + "");
            exifInterface.saveAttributes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
