package com.zhangwei.framelibs.CustomControl;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zhangwei.framelibs.R;


/**
 * 消息提示Dialog
 */

public class MessageDialog extends Dialog {
    private TextView tv;
    private String txt;
    private boolean showProcess;
    private boolean canCanel = true;

    public MessageDialog(Context context, String text, boolean showProcess) {
        this(context, text, true, showProcess);
    }

    public MessageDialog(Context context, String text, boolean showProcess, boolean canCanel) {
        super(context, R.style.customDialog);
        this.txt = text;
        this.canCanel = canCanel;
        this.showProcess = showProcess;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_dialog_layout);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.messagePB);
        progressBar.setVisibility(showProcess ? View.VISIBLE : View.GONE);
        tv = (TextView) findViewById(R.id.tv);
        tv.setText(txt);
        setCanceledOnTouchOutside(false);
        setCancelable(canCanel);
    }
}
