package com.zhangwei.com.fragmentactivity.Module.Entity;

/**
 * Created by wade on 2016/1/18.
 */
public class WeChatEntity {
    /**
     * parent_id : 3
     * name : 张炜
     * avatar : http://wx.qlogo.cn/mmopen/qGusKyb0IEexa5qvpYsJBbPmUUs1vS3LR3LGNFb7zEttX5KBof4a1bns0JdNDn79eMa2KZHIylbAcNhonfnHicUR4dUj81HdA/0
     * gender : man
     * created_at : 2016-01-15 11:03:06
     * object_name : parent
     * we_chat_fast_login_token : ccd01e71584849ee91bc432e43ad9b5d
     */

    private String parent_id;
    private String name;
    private String avatar;
    private String gender;
    private String created_at;
    private String we_chat_fast_login_token;

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setWe_chat_fast_login_token(String we_chat_fast_login_token) {
        this.we_chat_fast_login_token = we_chat_fast_login_token;
    }

    public String getParent_id() {
        return parent_id;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getGender() {
        return gender;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getWe_chat_fast_login_token() {
        return we_chat_fast_login_token;
    }
}
