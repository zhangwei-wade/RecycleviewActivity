package com.zhangwei.framelibs.Global.AbstractClass;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.NinePatchDrawable;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.zhangwei.framelibs.Global.Other.BitmapUtils;
import com.zhangwei.framelibs.Global.WebAPI.APIResponse;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2014/4/3.
 * <p/>
 * 将一些全局的方法和属性封装起来
 */
public abstract class BaseGlobal {
    public final static String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";//截取短信的received
    public final static String VIEW_REFRESH_RECEIVED_ACTION = "sand.com.sandPatrolServer.Refresh";//更新view的广播
    public final static String title = "TITLE";
    public final static String url = "URL";
    public final static String context = "context";
    public final static String status = "STATUS";
    public final static int REQUEST_CROP_ICON = 2;
    public final static String InternetConnectionFailure = "1", OtherEEROR = "-1",
            ConnectionTimeOut = "2", Success = "0", NoService = "500";
    public static boolean logStatus = true;
    public static String MT = ".sand";
    public static String BUNDLE = "bundle";
    private static Map<String, APIResponse> apiResponseMap = new HashMap<String, APIResponse>();//下载文件进度管理
    public final static String TEMP = "Temp";
    public final static int requestCode = 1110;
    public final static int camera = 5;
    public final static int importBitmap = 3;
    public final static String IPNUM = "PINUM";
    public final static String width = "width";
    public final static String height = "height";
    public final static String statusBarHeight = "statusBarHeight";

    public static void playLog(String str) {//全局log
        if (logStatus) {
            Log.i("wade", str);
        }
    }


    public static void playLog(String name, String str) {//全局log
        if (logStatus) {
            Log.i(name, str);
        }
    }

    public static void playELog(String name, String s) {
        if (logStatus) {
            Log.e(name, s);
        }
    }

    public static void playELog(String s) {
        if (logStatus) {
            Log.e("wade", s);
        }
    }

    /**
     * 创建本地图片的路径
     */
    public static File fetchImagePathFile(Context context, String imageUrl) {
        return new File(context.getCacheDir() + File.separator + fetchUrlDownName(imageUrl));
    }

    /**
     * 通过url获取下载之后的文件名
     */
    public static String fetchUrlDownName(String imageUrl) {
        if (!imageUrl.contains("/"))
            return imageUrl;
        return imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
    }


    public static void addDownFileProgress(String url, APIResponse apiResponse) {//管理文件下载进度
        apiResponseMap.put(url, apiResponse);
    }

    public static void removeDownFileProgress(String url) {//如果文件下载完成就删除进度
        if (url != null)
            apiResponseMap.remove(url);
    }


    public static APIResponse getDownFileProgress(String url) {
        return apiResponseMap.get(url);
    }

    /**
     * 文件重命名
     */
    public static void fileReName(File oldFile, File newFile) {//文件重命名
        oldFile.renameTo(newFile);
    }

    /**
     * 判断string是否为空
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 拨打电话
     */
    public static void CallPhone(Context context, String phone) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * .9文件转换bitmap
     */
    public static Bitmap getNinePatch(Context context, int id, int x, int y) {
        // id is a resource id for a valid ninepatch
        Bitmap bitmap = BitmapUtils.getInstance().decodeThumbBitmap(context.getResources(), id);
        byte[] chunk = bitmap.getNinePatchChunk();
        NinePatchDrawable np_drawable = new NinePatchDrawable(context.getResources(), bitmap,
                chunk, new Rect(), null);
        np_drawable.setBounds(0, 0, x, y);
        Bitmap output_bitmap = Bitmap.createBitmap(x, y, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output_bitmap);
        np_drawable.draw(canvas);
        return output_bitmap;
    }

    /**
     * 手机号验证
     *
     * @param mobiles
     * @return 验证通过返回true
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 更新imageView
     */
    public static void invalidate(ImageView imageView, Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

    /**
     * 重启程序
     */
    public static void restartApplication(Context context) {
        if (context instanceof ApplicationActivity) {
            ((ApplicationActivity) context).fetchApplication().clearROMData();
        }
        if (context instanceof BaseApplication) {
            ((BaseApplication) context).clearROMData();
        }
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }
}
