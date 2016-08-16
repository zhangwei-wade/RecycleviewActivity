package com.zhangwei.com.fragmentactivity.BaseGlobal.Global;


import com.zhangwei.framelibs.Global.Other.BaseWebAPI;

/**
 * Created by Administrator on 2014/4/2.
 */
public class WebAPI extends BaseWebAPI {
    private final static WebAPI webAPI = new WebAPI();
    public static String login = "/user/parent/login.json";
    public static String requestCode = "/sms/requestCode.json";
    public static String forgetPWD = "/user/parent/findPassword.json";
    public static String register = "/user/parent/register.json";
    public static String replacePWD = "/user/parent/changePassword.json";
    public static String schoolList = "/user/uuid/getUuidRelationList.json";
    public static String wetChatLogin = "/weChat/weChatAppLogin.json";//微信登录

    public static String userInfo = "/user/whoAmI.json";//獲取微信名
    public static String parentBindWeChat = "/weChat/parentBindWeChat.json";//微信绑定
    public static String uuidInfo = "/user/uuid/getUuidInfo.json";//获取课程信息
    public static String addUUID = "/user/uuid/addUuidRelation.json";//添加课程
    public static String isValidUUID = "/user/uuid/isValidUuid.json";//验证uuid的正确性
    public static String deleteUUID = "/user/uuid/delUuidRelation.json";//删除课程
    public static String wetChatFastLogin = "/weChat/weChatAppFastLogin.json";//微信快捷登录
    public static String logout = "/user/logout.json";//登出界面
    public static String messageInfo = "/message/getMessageInfoList.json";
    public static String deviceToken="/user/sign/updateDeviceToken.json";

    public static WebAPI getInstance() {
        return webAPI;
    }
}
