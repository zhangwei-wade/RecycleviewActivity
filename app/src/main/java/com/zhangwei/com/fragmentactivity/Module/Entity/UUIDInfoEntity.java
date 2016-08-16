package com.zhangwei.com.fragmentactivity.Module.Entity;

import com.zhangwei.framelibs.Global.AbstractClass.AbstractBaseEntity;

/**
 * Created by wade on 2016/1/15.
 */
public class UUIDInfoEntity extends AbstractBaseEntity {

    /**
     * uuid : TRXQ3
     * student_id : 5
     * student_name : 老-33536
     * organization_id : 5
     * organization_name : org-7300
     * class_id : 5
     * class_name : 8580班
     * admission_time : 2016-01-04
     * teacher_id : 5
     * message_unreadCount : 1
     */

    private String uuid;
    private String student_id;
    private String student_name;
    private int student_age;
    private String organization_id;
    private String organization_name;
    private String class_id;
    private String class_name;
    private String admission_time;
    private String teacher_id;
    private String teacher_name;
    private String teacher_gender;
    private String teacher_avatar;
    private int message_unreadCount;

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public void setOrganization_id(String organization_id) {
        this.organization_id = organization_id;
    }

    public void setOrganization_name(String organization_name) {
        this.organization_name = organization_name;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public void setAdmission_time(String admission_time) {
        this.admission_time = admission_time;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public void setMessage_unreadCount(int message_unreadCount) {
        this.message_unreadCount = message_unreadCount;
    }

    public String getUuid() {
        return uuid;
    }

    public String getStudent_id() {
        return student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public String getOrganization_id() {
        return organization_id;
    }

    public String getOrganization_name() {
        return organization_name;
    }

    public String getClass_id() {
        return class_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public String getAdmission_time() {
        return admission_time;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public int getMessage_unreadCount() {
        return message_unreadCount;
    }

    public int getStudent_age() {
        return student_age;
    }

    public void setStudent_age(int student_age) {
        this.student_age = student_age;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getTeacher_gender() {
        return teacher_gender;
    }

    public void setTeacher_gender(String teacher_gender) {
        this.teacher_gender = teacher_gender;
    }

    public String getTeacher_avatar() {
        return teacher_avatar;
    }

    public void setTeacher_avatar(String teacher_avatar) {
        this.teacher_avatar = teacher_avatar;
    }
}
