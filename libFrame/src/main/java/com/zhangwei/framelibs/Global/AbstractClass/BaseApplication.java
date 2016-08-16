package com.zhangwei.framelibs.Global.AbstractClass;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;

import com.zhangwei.framelibs.Global.Other.BaseWebAPI;
import com.zhangwei.framelibs.Global.Sqlite.BaseDBSQLite;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 14-3-24.
 * <p/>
 * 全局的application，在整个程序中都可以调用的
 */
public abstract class BaseApplication extends Application {
    public final String[] baseUrlArray = new String[]{
            "http://uat.box.app.xuexuecan.com/api/v1"
    };
    protected int CurrentInternetType = 0;//表示 当前用的第几个IP；
    private List<FragmentActivity> activityList = new ArrayList<>();
    protected SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * 将参数和url拼接起来
     */
    public String InitUrl(String url) {
        return baseUrlArray[CurrentInternetType] + url;
    }

    /**
     * 获取当前的ip和端口
     */
    public String getUrl() {
        return baseUrlArray[CurrentInternetType];
    }

    /**
     * 获取本地创建数据库
     */
    public BaseDBSQLite fetchDBSQLite() {
        return BaseDBSQLite.getInstance(this);
    }


    public abstract BaseWebAPI fetchWebAPI();

    public void initSharePreferences() {
        sharedPreferences = getSharedPreferences("Setting", Context.MODE_PRIVATE);
    }


    /**
     * 根据包名打开其他应用程序
     */
    public void RunApp(String packageName) {
        PackageInfo pi;
        try {
            pi = getPackageManager().getPackageInfo(packageName, 0);
            Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
            resolveIntent.setPackage(pi.packageName);
            PackageManager pManager = getPackageManager();
            List apps = pManager.queryIntentActivities(
                    resolveIntent, 0);
            ResolveInfo ri = (ResolveInfo) apps.iterator().next();
            if (ri != null) {
                packageName = ri.activityInfo.packageName;
                String className = ri.activityInfo.name;
                Intent intent = new Intent(Intent.ACTION_MAIN);
                ComponentName cn = new ComponentName(packageName, className);
                intent.setComponent(cn);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }


    /**
     * 将属性添加到sp文件中
     */
    public void addSharedPreferences(String key, Object value) {
        if (sharedPreferences == null) {
            initSharePreferences();
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (value instanceof String)
            editor.putString(key, value.toString());
        if (value instanceof Integer)
            editor.putInt(key, Integer.parseInt(value.toString()));
        if (value instanceof Boolean)
            editor.putBoolean(key, Boolean.parseBoolean(value.toString()));
        editor.commit();
    }

    /**
     * 获取sp文件中的数据
     */
    public String getSPString(String key) {
        if (sharedPreferences == null) {
            initSharePreferences();
        }
        String values = sharedPreferences.getString(key, "");
        BaseGlobal.playLog(key + ":" + values);
        return values;
    }

    public boolean getSPBoolean(String key) {
        if (sharedPreferences == null) {
            initSharePreferences();
        }
        return sharedPreferences.getBoolean(key, false);
    }

    public int getSPInt(String key, int mDefault) {
        if (sharedPreferences == null) {
            initSharePreferences();
        }
        return sharedPreferences.getInt(key, mDefault);
    }

    /**
     * 清空默认的sharedPreferences文件
     */
    public void clearSharedPreferences() {
        if (sharedPreferences == null) {
            initSharePreferences();
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    public void addActivity(FragmentActivity activity) {
        activityList.add(activity);
    }

    public void removeThisActivity(Activity activity) {
        if (activityList.size() > 0)
            activityList.remove(activity);
    }

    /**
     * 清空数据
     */
    public void clearROMData() {
        clearSharedPreferences();
    }

    /**
     * 删除所有的Activity
     */
    public void exitActivity() {
        if (activityList.size() > 0) {
            activityList.get(0).finish();
            exitActivity();
        }
    }


    /**
     * 获取程序版本信息
     */
    public PackageInfo getPackageVersionInfoPackName(String packName) {
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(packName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packInfo;
    }


    /**
     * 获取安装包信息
     */
    public PackageInfo getPackageInfoPath(String path) {
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        PackageManager pm = getPackageManager();
        packInfo = pm.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);
        return packInfo;
    }

    /**
     * 安装apk
     *
     * @param file
     */
    public void installApk(File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //设置flag
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //设置action
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse("file://" + file.toString()), "application/vnd.android.package-archive");
        startActivity(intent);
    }

    /**
     * 判断服务是否正在运行
     * *
     */
    public boolean isServiceRunning(Context mContext, String className) {
        ActivityManager myManager = (ActivityManager) mContext
                .getApplicationContext().getSystemService(
                        Context.ACTIVITY_SERVICE);
        ArrayList<ActivityManager.RunningServiceInfo> runningService =
                (ArrayList<ActivityManager.RunningServiceInfo>) myManager
                        .getRunningServices(30);
        for (int i = 0; i < runningService.size(); i++) {
            if (runningService.get(i).service.getClassName().toString()
                    .equals(className)) {
                return true;
            }
        }
        return false;
    }
}
