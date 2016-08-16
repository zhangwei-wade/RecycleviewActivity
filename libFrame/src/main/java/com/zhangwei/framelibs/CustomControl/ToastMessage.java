package com.zhangwei.framelibs.CustomControl;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.zhangwei.framelibs.Global.InitView;
import com.zhangwei.framelibs.R;

/**
 * Created by Administrator on 2014/11/19.
 */
public class ToastMessage {
    private Toast toast;
    private TextView message;
    private static ToastMessage toastMessage;

    public static ToastMessage getInstance() {
        if (toastMessage == null) {
            toastMessage = new ToastMessage();
        }
        return toastMessage;
    }


    public void showToast(Context context, String str) {
        showToast(context, str, 1000);
    }

    public void showToast(Context context, String str, int time) {
        if (toast == null) {
            toast = new Toast(context);
            InitView action_activity = new InitView(context, R.layout.custom_toast, null);
            message = action_activity.findViewById(R.id.message);
            toast.setView(action_activity.getView());
        }
        toast.setDuration(time);
        message.setText(str);
        toast.show();
    }

}
