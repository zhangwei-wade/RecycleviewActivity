package com.zhangwei.framelibs.Global.AbstractClass;

import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.zhangwei.framelibs.Global.InitView;
import com.zhangwei.framelibs.Global.InterfaceClass.OnRefreshViewListener;
import com.zhangwei.framelibs.Global.Other.RefreshViewReceiver;
import com.zhangwei.framelibs.R;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 14-3-21.
 */
public abstract class ApplicationActivity extends AppCompatActivity implements OnRefreshViewListener {
    protected InitView $ = new InitView(this);
    private BaseApplication baseApplication;
    private RefreshViewReceiver refreshViewReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseApplication = (BaseApplication) getApplication();
        baseApplication.addActivity(this);
    }

    /**
     * 注册广播更新View
     */
    protected void onRegisterViewReceiver() {
        refreshViewReceiver = new RefreshViewReceiver();
        refreshViewReceiver.setOnRefreshViewListener(this);
        refreshViewReceiver.onRegisterReceiver(getApplicationContext());
    }

    /**
     * 将图片扫描到数据库中
     */
    public void scanAddFile(String path) {
//        Uri data_uri = Uri.parse("file://" + path);
//        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, data_uri));
        File f = new File(path);
        try {
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(f);
            mediaScanIntent.setData(uri);
            sendBroadcast(mediaScanIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 广播刷新全局View
     * 如果要实现fragment的 onRefreshView
     * 必须重写 super.onRefreshView(intent);
     */
    @Override
    public void onRefreshView(Intent intent) {
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            List<Fragment> fragments = fragmentManager.getFragments();
            if (fragments != null) {
                for (Fragment fragment : fragments) {
                    if (fragment instanceof ApplicationFragment) {
                        ((ApplicationFragment) fragment).onRefreshView(intent);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送广播消息
     */
    public void sendBroadReceiver() {
        sendBroadReceiver(null);
    }

    /**
     * 发送广播消息
     */
    public void sendBroadReceiver(Intent intent) {
        if (intent == null) {
            intent = new Intent();
        }
        intent.setAction(BaseGlobal.VIEW_REFRESH_RECEIVED_ACTION);
        sendBroadcast(intent);
    }

    /**
     * 调用友盟session统计
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 调用友盟session统计
     */
    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * 将删除的文件存入数据库中
     */
    public void scanDeleteFile(String path) {
        Uri data_uri = Uri.parse("file://" + path);
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, data_uri));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    public <T extends BaseApplication> T fetchApplication() {
        if (baseApplication == null) {
            baseApplication = (BaseApplication) getApplication();
        }
        return (T) baseApplication;
    }

    /**
     * 删除所有上传图片
     */
    public void deleteImage(String path) {
        File file = new File(path);
        file.delete();
        scanDeleteFile(file.getPath());
    }


    @NonNull
    private DisplayMetrics getDisplayMetrics() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }

    /**
     * 获取屏幕的宽度
     */
    public int getScreenHeight() {
        int height = fetchApplication().getSPInt(BaseGlobal.height, 0);
        if (height == 0) {
            DisplayMetrics metrics = getDisplayMetrics();
            height = metrics.heightPixels;
            fetchApplication().addSharedPreferences(BaseGlobal.height, height);
        }
        return height;
    }

    /**
     * 获取屏幕的高度
     */
    public int getScreenWidth() {
        int width = fetchApplication().getSPInt(BaseGlobal.width, 0);
        if (width == 0) {
            DisplayMetrics metrics = getDisplayMetrics();
            width = metrics.widthPixels;
            fetchApplication().addSharedPreferences(BaseGlobal.width, width);
        }
        return width;
    }

    /**
     * 获取状态栏的高度
     */
    public int getStatusBarHeight() {
        int statusBar = fetchApplication().getSPInt(BaseGlobal.statusBarHeight, 0);
        if (statusBar == 0) {
            Rect frame = new Rect();
            getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
            statusBar = frame.top;
            fetchApplication().addSharedPreferences(BaseGlobal.statusBarHeight, statusBar);
        }
        return statusBar;
    }

    @Override
    public boolean onTouchEvent(android.view.MotionEvent event) {
        hideSoftKeyBoard();
        return super.onTouchEvent(event);
    }

    /**
     * 隐藏键盘
     */
    public void hideSoftKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null && (getCurrentFocus() instanceof EditText)) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    /**
     * 界面进入动画
     */
    public void overridePendingTransitionIn() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH)
            overridePendingTransition(R.anim.pull_in_right, R.anim.pull_out_left);
    }

    /**
     * 界面退出动画
     */
    public void overridePendingTransitionOut() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH)
            overridePendingTransition(R.anim.pull_in_left, R.anim.pull_out_right);
    }


    @Override
    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
//        overridePendingTransitionIn();
    }

    @Override
    public void finish() {
        super.finish();
        baseApplication.removeThisActivity(this);
//        overridePendingTransitionOut();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (refreshViewReceiver != null)
            refreshViewReceiver.UnRegisterReceiver(getApplicationContext());
    }
}
