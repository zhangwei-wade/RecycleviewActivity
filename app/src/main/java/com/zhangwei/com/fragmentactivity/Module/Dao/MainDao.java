package com.zhangwei.com.fragmentactivity.Module.Dao;


import com.zhangwei.com.fragmentactivity.BaseGlobal.Global.MyApplication;
import com.zhangwei.com.fragmentactivity.Module.Interface.MainInterface;

/**
 * Created by wade on 2016/1/12.
 */
public class MainDao extends BaseDao implements MainInterface {
    public MainDao(MyApplication myApplication) {
        this.myApplication = myApplication;
    }

    @Override
    public String loadRequest(int page, int count) {
        String request = "?";
        request += "offset=" + page + "&";
        request += "limit=" + count;
        return request;
    }

    @Override
    public String UUIDInfoRequest(String uuid) {
        String request = "?";
        request += "uuid=" + uuid;
        return request;
    }

    @Override
    public String uuidRequest(String uuid) {
        String request = "uuid=" + uuid;
        return request;
    }

    @Override
    public String messageInfoRequest(String uuid, int pages, int pageCount) {
        String request = "?";
        request += "uuid=" + uuid;
        request += "&offset=" + pages;
        request += "&limit=" + pageCount;
        return request;
    }

    @Override
    public String uploadRequest(String type, String device_token) {
        String request = "";
        request += "device_type=" + type;
        request += "&device_token=" + device_token;
        return request;
    }
}