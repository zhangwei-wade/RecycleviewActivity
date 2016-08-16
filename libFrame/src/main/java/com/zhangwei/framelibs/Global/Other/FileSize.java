package com.zhangwei.framelibs.Global.Other;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2015/4/2.
 * <p/>
 * 文件管理返回文件的大小值
 */
public class FileSize {
    private static FileSize fileSize;

    public static FileSize getInstance() {
        if (fileSize == null)
            fileSize = new FileSize();
        return fileSize;
    }

    /**
     * 返回文件的大小
     */
    public String fetchBToM(long fileLength) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString;
        if (fileLength < 1024) {
            fileSizeString = df.format((double) fileLength) + "B";
        } else if (fileLength < 1048576) {
            fileSizeString = df.format((double) fileLength / 1024) + "KB";
        } else if (fileLength < 1073741824) {
            fileSizeString = df.format((double) fileLength / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileLength / 1073741824) + "GB";
        }
        return fileSizeString;
    }
}
