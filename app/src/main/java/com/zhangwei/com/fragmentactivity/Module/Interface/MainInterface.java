package com.zhangwei.com.fragmentactivity.Module.Interface;

/**
 * Created by wade on 2016/1/14.
 */
public interface MainInterface {
    String loadRequest(int page, int count);

    String UUIDInfoRequest(String uuid);

    String uuidRequest(String uuid);

    String messageInfoRequest(String uuid, int pages, int pageCount);

    String uploadRequest(String type, String device_token);
}
