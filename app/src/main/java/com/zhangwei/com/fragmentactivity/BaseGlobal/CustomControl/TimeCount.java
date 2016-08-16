package com.zhangwei.com.fragmentactivity.BaseGlobal.CustomControl;

import android.os.CountDownTimer;
import android.widget.TextView;


/**
 * Created by wade on 2016/1/13.
 *
 * 倒计时获取短信验证码
 *
 *
 */
public class TimeCount extends CountDownTimer {
    public static final int TIME_COUNT = 121 * 1000;//时间防止从119s开始显示（以倒计时120s为例子）
    private TextView btn;
    private String endStrRid;

    /**
     * 参数 millisInFuture         倒计时总时间（如60S，120s等）
     * 参数 countDownInterval    渐变时间（每次倒计1s）
     * <p/>
     * 参数 btn               点击的按钮(因为Button是TextView子类，为了通用我的参数设置为TextView）
     * <p/>
     * 参数 endStrRid   倒计时结束后，按钮对应显示的文字
     */
    public TimeCount(TextView btn, String endStrRid) {
        super(TIME_COUNT, 1000);
        this.btn = btn;
        this.endStrRid = endStrRid;
    }

    // 计时完毕时触发
    @Override
    public void onFinish() {
        btn.setText(endStrRid);
        btn.setEnabled(true);
    }

    // 计时过程显示
    @Override
    public void onTick(long millisUntilFinished) {
        btn.setEnabled(false);
        btn.setText(millisUntilFinished / 1000 + "s后再次获取");
    }

}
