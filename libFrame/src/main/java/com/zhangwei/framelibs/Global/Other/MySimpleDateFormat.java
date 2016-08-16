package com.zhangwei.framelibs.Global.Other;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 14-3-25.
 */
public class MySimpleDateFormat {

    /**
     * 获取现在时间
     *
     * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
     */
    public static Date getNowDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    /**
     * 获取现在时间
     *
     * @return返回短时间格式 yyyy-MM-dd  yyyy-MM-dd HH:mm:ss
     */
    public static String getNowDateShort(String str) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(str);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyyMMdd_HHmmss
     */
    public static String getStringAllDateToImageName() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getStringDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String DateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateString = formatter.format(date);
        return dateString;
    }

    // public static String format(Date date) {
    // if (date == null) {
    // return "";
    // }
    // long time = System.currentTimeMillis() - date.getTime();
    // if (time <= 0) {
    // return "5秒前";
    // }
    // if (time < 60 * 1000) {
    // return Math.round(time / 1000) + "秒前";
    // }
    // if (time >= 60 * 1000 && time < 60 * 1000 * 60) {
    // return Math.round(time / (60 * 1000)) + "分钟前";
    // }
    // if (time >= 60 * 1000 * 60 && time < 60 * 1000 * 60 * 24) {
    // return Math.round(time / (60 * 1000 * 60)) + "小时前";
    // }
    // if (time >= 60 * 1000 * 60 * 24) {
    // int day = Math.round(time / (60 * 1000 * 60 * 24));
    // return day > 28 ? format("yyyy-MM-dd HH:mm:s", date) : day + "天前";
    // }
    // return "";
    // }
}
