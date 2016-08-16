package com.zhangwei.com.fragmentactivity.Module.Service;

import android.util.Log;

import com.zhangwei.com.fragmentactivity.BaseGlobal.Global.MyApplication;
import com.zhangwei.com.fragmentactivity.BaseGlobal.Global.WebAPI;
import com.zhangwei.com.fragmentactivity.Module.Dao.LoginDao;
import com.zhangwei.com.fragmentactivity.Module.Interface.LoginInterface;
import com.zhangwei.framelibs.Global.WebAPI.APIResponse;

/**
 * Created by wade on 2016/1/12.
 */
public class LoginService extends BaseService {
    private LoginInterface dao;

    public LoginService(MyApplication myApplication) {
        this.myApplication = myApplication;
        dao = new LoginDao(myApplication);
    }

    /**
     * 登录接口
     */
    public void onLogin(String tel, String pwd, APIResponse apiResponse) {
        String loginReq = dao.loginRequest(tel, pwd);
        myApplication.fetchWebAPI().onPostEntity(myApplication.InitUrl(WebAPI.login),
                loginReq, apiResponse);
    }

    public void onWetChatLogin(String code, String sign, APIResponse apiResponse) {
        String loginReq = dao.wetChatRequest(code, sign);
        myApplication.fetchWebAPI().onPostEntity(myApplication.InitUrl(WebAPI.wetChatLogin),
                loginReq, apiResponse);
    }

    public void onWetparentBindWeChat(String code, String sign, APIResponse apiResponse) {
        String loginReq = dao.wetChatRequest(code, sign);
        myApplication.fetchWebAPI().onPostEntity(myApplication.InitUrl(WebAPI.parentBindWeChat),
                loginReq, apiResponse);
        Log.d("MyCode", "走了快6");
    }

    /**
     * 短信获取验证码
     */
    public void onFetchCode(String tel, int type, APIResponse apiResponse) {
        String codeRequest = dao.codeRequest(tel, type);
        myApplication.fetchWebAPI().onPostEntity(myApplication.InitUrl(WebAPI.requestCode),
                codeRequest, apiResponse);
    }

    /**
     * 找回密码
     */
    public void onForgetPWD(String tel, String code, String pwd, APIResponse apiResponse) {
        String forgetRequest = dao.forgetPWDRequest(tel, code, pwd);
        myApplication.fetchWebAPI().onPostEntity(myApplication.InitUrl(WebAPI.forgetPWD),
                forgetRequest, apiResponse);
    }

    /**
     * 用户注册
     */
    public void onRegister(String tel, String code, String pwd, APIResponse apiResponse) {
        String registerRequest = dao.forgetPWDRequest(tel, code, pwd);
        myApplication.fetchWebAPI().onPostEntity(myApplication.InitUrl(WebAPI.register),
                registerRequest, apiResponse);
    }

    /**
     * 修改密码功能
     */
    public void onReplace(String oldPWD, String newPWD, APIResponse apiResponse) {
        String request = dao.replaceRequest(oldPWD, newPWD);
        myApplication.fetchWebAPI().onPostEntity(myApplication.InitUrl(WebAPI.replacePWD),
                request, apiResponse);
    }

    public void onWetChatFastLogin(String token, String sign, APIResponse apiResponse) {
        String loginReq = dao.wetChatFastRequest(token, sign);
        myApplication.fetchWebAPI().onPostEntity(myApplication.InitUrl(WebAPI.wetChatFastLogin),
                loginReq, apiResponse);
    }
}
