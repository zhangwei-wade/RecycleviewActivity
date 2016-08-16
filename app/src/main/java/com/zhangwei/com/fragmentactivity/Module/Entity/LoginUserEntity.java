package com.zhangwei.com.fragmentactivity.Module.Entity;

import com.zhangwei.framelibs.Global.AbstractClass.AbstractBaseEntity;

/**
 * Created by wade on 2015/12/4.
 */
public class LoginUserEntity extends AbstractBaseEntity {
    public String userName;
    public String mobileNumber;
    public String result;
    public String successful;
    public String userId;
    public String msgFlg;//判断是不是有新的广告   其中1-true(标识有新的通知)，0-false(没有)

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getSuccessful() {
        return successful;
    }

    public void setSuccessful(String successful) {
        this.successful = successful;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMsgFlg() {
        return msgFlg;
    }

    public void setMsgFlg(String msgFlg) {
        this.msgFlg = msgFlg;
    }
}
