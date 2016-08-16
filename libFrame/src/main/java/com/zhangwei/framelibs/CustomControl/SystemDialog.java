package com.zhangwei.framelibs.CustomControl;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import com.zhangwei.framelibs.Global.InterfaceClass.MyDialogInterface;

/**
 * Created by Administrator on 2014/11/19.
 * <p/>
 * 用法
 * Bundle bundle = new Bundle();
 * bundle.putString(CustomAlertDialog.TITLE, getString(R.string.reminder));
 * bundle.putString(CustomAlertDialog.MESSAGE, getString(R.string.delete_message));
 * bundle.putString(CustomAlertDialog.POSITIVEBUTTONTEXT, getString(R.string.OK));
 * bundle.putString(CustomAlertDialog.NEGATIVEBUTTONTEXT, getString(R.string.Cancel_No_Spacing));
 */
public class SystemDialog {
    private static SystemDialog systemDialog;

    public static SystemDialog getInstance() {
        if (systemDialog == null) {
            systemDialog = new SystemDialog();
        }
        return systemDialog;
    }

    //系统自定义Dialog样式
    public AlertDialog showSystemDialog(Context context, Bundle bundle,
                                        MyDialogInterface myDialogInterface) {
        return showSystemDialog(context, bundle, myDialogInterface, true, true);
    }


    //系统自定义Dialog样式
    public AlertDialog showSystemDialog(Context context, Bundle bundle,
                                        MyDialogInterface myDialogInterface,
                                        boolean cancelOutside, boolean cancelable) {
        AlertDialog customAlertDialog;
        customAlertDialog = new CustomAlertDialog(context, bundle,
                myDialogInterface).create();
        customAlertDialog.setCanceledOnTouchOutside(cancelOutside);
        customAlertDialog.setCancelable(cancelable);
        customAlertDialog.show();
        return customAlertDialog;
    }


}
