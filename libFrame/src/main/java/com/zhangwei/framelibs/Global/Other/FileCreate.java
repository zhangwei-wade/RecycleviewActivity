package com.zhangwei.framelibs.Global.Other;

import android.content.Context;
import android.os.Environment;

import com.zhangwei.framelibs.CustomControl.ToastMessage;

import java.io.File;
import java.io.IOException;

/**
 * Created by wade on 2015/12/3.
 */
public class FileCreate {
    private final String filePath = File.separator + "sandMarket" + File.separator;
    private static FileCreate fileCreate;

    public static FileCreate getInstance() {
        if (fileCreate == null) {
            fileCreate = new FileCreate();
        }
        return fileCreate;
    }


    /**
     * 如果内存卡不存在使用安裝目录下的路径
     */
    public String onBasePath(Context context) {
        String path = "";
        if (checkSDCard()) {
            path = Environment.getExternalStorageDirectory() + filePath;
            validatePath(path);
        } else {
            ToastMessage.getInstance().showToast(context, "SD卡初始化失败");
        }
        return path;
    }

    /**
     * 监测路径是否存在，如果不存在则进行创建
     *
     * @param dir 待检测地址
     * @return 生成的路径
     */
    private String validatePath(String dir) {
        File fileDir = new File(dir);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        return dir;
    }

    /**
     * 创建文件
     */
    public File createDownloadFile(Context context, String filePath) {
        filePath = onBasePath(context) + filePath;
        File file = new File(filePath);
        validatePath(file.getParent());
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * 判断SD卡是否存在
     *
     * @return boolean
     */
    private boolean checkSDCard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }
}
