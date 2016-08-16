package com.zhangwei.com.fragmentactivity.Module.Dao;


import com.zhangwei.com.fragmentactivity.BaseGlobal.Global.MyApplication;
import com.zhangwei.com.fragmentactivity.Module.Interface.LoginInterface;

/**
 * Created by wade on 2016/1/12.
 */
public class LoginDao extends BaseDao implements LoginInterface {
    public LoginDao(MyApplication myApplication) {
        this.myApplication = myApplication;
    }

    @Override
    public String loginRequest(String tel, String pwd) {
        return "phone_num=" + tel + "&password=" + pwd;
    }

    @Override
    public String codeRequest(String tel, int type) {
        return "phone_num=" + tel + "&template_id=" + type;
    }

    @Override
    public String forgetPWDRequest(String tel, String code, String pwd) {
        return "phone_num=" + tel + "&sms_code=" + code + "&password=" + pwd;
    }

    @Override
    public String replaceRequest(String oldPWD, String newPWD) {
        return "old_password=" + oldPWD + "&new_password=" + newPWD;
    }

    @Override
    public String wetChatRequest(String code, String sign) {
        return "code=" + code + "&sign=" + sign;
    }

    @Override
    public String wetChatFastRequest(String token, String sign) {
        return "token=" + token + "&sign=" + sign;
    }
}
