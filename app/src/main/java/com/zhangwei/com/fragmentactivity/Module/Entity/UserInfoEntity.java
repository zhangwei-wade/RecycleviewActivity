package com.zhangwei.com.fragmentactivity.Module.Entity;

import com.zhangwei.framelibs.Global.AbstractClass.AbstractBaseEntity;

/**
 * Created by 氏 on 2016/4/8.
 */
public class UserInfoEntity extends AbstractBaseEntity {
   /* "object_name": "UserInfo",
            "user_id": "38",
            "parent_id": "15",
            "teacher_id": null,
            "phone_num": "18756120075",
            "weChat_nickName": "耿龙",
            "identity": "parent"*/
    private  String user_id;
    private  String parent_id;
    private  String teacher_id;
    private  String phone_num;
    private  String weChat_nickName;
    private  String identity;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getWeChat_nickName() {
        return weChat_nickName;
    }

    public void setWeChat_nickName(String weChat_nickName) {
        this.weChat_nickName = weChat_nickName;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}
