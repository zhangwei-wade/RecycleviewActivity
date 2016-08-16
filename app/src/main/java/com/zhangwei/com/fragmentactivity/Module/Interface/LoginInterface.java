package com.zhangwei.com.fragmentactivity.Module.Interface;

/**
 * Created by wade on 2016/1/12.
 */
public interface LoginInterface {
    /**
     * 登录上传的数据
     */
    String loginRequest(String tel, String pwd);

    /**
     * 获取短信验证码上传的数据
     */
    String codeRequest(String tel, int type);

    /**
     * 找回密码上传的数据
     */
    String forgetPWDRequest(String tel, String code, String pwd);

    /**
     * 修改密码上传的参数
     */
    String replaceRequest(String oldPWD, String newPWD);

    /**
     * 微信登录上传参数
     */
    String wetChatRequest(String code, String sign);

    String wetChatFastRequest(String token, String sign);
}
