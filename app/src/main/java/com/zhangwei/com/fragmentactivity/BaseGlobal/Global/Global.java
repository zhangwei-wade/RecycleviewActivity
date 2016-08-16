package com.zhangwei.com.fragmentactivity.BaseGlobal.Global;

import android.os.Environment;

import com.zhangwei.framelibs.Global.AbstractClass.BaseGlobal;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Administrator on 2014/4/3.
 */
public class Global extends BaseGlobal {
    public static final String APP_ID = "wxbaecac6ac5769e25";
    public final static String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";
    private static Global global;
    public static String loginFlag = "LoginFlag";

    public static final String BASE_PATH = Environment
            .getExternalStorageDirectory().getAbsolutePath();
    /**
     * 视频存放路径
     */
    public final static String VIDEO_PATH = BASE_PATH
            + "/sand/video/";
    /**
     * 文件存放路径
     */
    public final static String FILE_PATH = BASE_PATH
            + "/sand/file/";
    public static String macID = "macID";
    public static String sms = "sms";
    public static String intentShop = "shop";
    public static String shopType = "shopType";
    //是否为修改手势
    public static String replace = "replace";
    public static String shopID = "shopID";
    public static String result = "result";
    public static String name = "user";
    public static String passwrod = "pwd";
    public static String weChatToken = "weChatToken";
    public static String entity = "ENTITY";
    public static String deviceToken = "deviceToken";

    public static Global getInstance() {
        if (global == null) {
            global = new Global();
        }
        return global;
    }

    /**
     * 电话号码验证
     *
     * @param phone
     * @return 验证通过返回true
     */
    public static boolean isPhone(String phone) {
        Pattern p1, p2;
        Matcher m;
        boolean b;
        p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");  // 验证带区号的
        p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");         // 验证没有区号的
        if (phone.length() > 9) {
            m = p1.matcher(phone);
            b = m.matches();
        } else {
            m = p2.matcher(phone);
            b = m.matches();
        }
        return b;
    }

    /**
     * md5加密
     */
    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

}
