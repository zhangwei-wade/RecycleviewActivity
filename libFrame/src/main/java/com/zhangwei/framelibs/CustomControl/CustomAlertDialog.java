package com.zhangwei.framelibs.CustomControl;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import com.zhangwei.framelibs.Global.InterfaceClass.MyDialogInterface;

/**
 * Created by Administrator on 2014/4/8.
 * <p/>
 * 自定义警告提示Dialog
 */
public class CustomAlertDialog extends AlertDialog.Builder {
    public static final String TITLE = "title", MESSAGE = "message", ICON = "ICON",
            POSITIVEBUTTONTEXT = "positivebuttontext", NEGATIVEBUTTONTEXT = "negativebutton";
    private Bundle bundle;
    private MyDialogInterface myDialogInterface;


    public CustomAlertDialog(Context context, Bundle bundle, MyDialogInterface myDialogInterface) {
        super(context);
        this.bundle = bundle;
        this.myDialogInterface = myDialogInterface;
        Init();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public CustomAlertDialog(Context context, Bundle bundle, MyDialogInterface myDialogInterface, int style) {
        super(context, style);
        this.bundle = bundle;
        this.myDialogInterface = myDialogInterface;
        Init();
    }

    private void Init() {
        setTitle(bundle.getString(TITLE));
        setMessage(bundle.getString(MESSAGE));
        setIcon(bundle.getInt(ICON, 0));
        setCancelable(true);
        if (bundle.getString(POSITIVEBUTTONTEXT) != null) {
            setPositiveButton(bundle.getString(POSITIVEBUTTONTEXT), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (myDialogInterface != null)
                        myDialogInterface.onClickYes();
                    dialog.dismiss();
                }
            });
        }
        if (bundle.getString(NEGATIVEBUTTONTEXT) != null) {
            setNegativeButton(bundle.getString(NEGATIVEBUTTONTEXT), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (myDialogInterface != null)
                        myDialogInterface.onClickCancel();
                    dialog.dismiss();
                }
            });
        }
    }

}
