package com.zhangwei.com.fragmentactivity.Module.Entity;

import com.zhangwei.framelibs.Global.AbstractClass.AbstractBaseEntity;

/**
 * Created by wade on 2016/1/12.
 */
public class ParentEntity extends AbstractBaseEntity {
    /**
     * parent_id : 1
     * phone : 15079104225
     * name : null
     * avatar : null
     * gender : unknown
     * created_at : 2016-01-12 18:29:23
     * object_name : parent
     */
    private String parent_id;
    private String phone;
    private Object name;
    private Object avatar;
    private String gender;
    private String created_at;

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public void setAvatar(Object avatar) {
        this.avatar = avatar;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getParent_id() {
        return parent_id;
    }

    public String getPhone() {
        return phone;
    }

    public Object getName() {
        return name;
    }

    public Object getAvatar() {
        return avatar;
    }

    public String getGender() {
        return gender;
    }

    public String getCreated_at() {
        return created_at;
    }

}
