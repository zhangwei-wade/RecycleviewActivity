package com.zhangwei.com.fragmentactivity.Module.Service;

import com.zhangwei.com.fragmentactivity.BaseGlobal.Global.MyApplication;
import com.zhangwei.com.fragmentactivity.BaseGlobal.Global.WebAPI;
import com.zhangwei.com.fragmentactivity.Module.Dao.MainDao;
import com.zhangwei.com.fragmentactivity.Module.Entity.MessageInfoEntity;
import com.zhangwei.com.fragmentactivity.Module.Entity.UserInfoEntity;
import com.zhangwei.com.fragmentactivity.Module.Interface.MainInterface;
import com.zhangwei.framelibs.Global.WebAPI.APIResponse;

import java.util.List;

/**
 * Created by wade on 2016/1/12.
 */
public class MainService extends BaseService {
    private MainInterface dao;

    public MainService(MyApplication myApplication) {
        this.myApplication = myApplication;
        dao = new MainDao(myApplication);
    }

    public void loadSchoolListData(int page, int count, APIResponse apiResponse) {
        String request = dao.loadRequest(page, count);
        myApplication.fetchWebAPI().onGetEntity(myApplication.
                InitUrl(WebAPI.schoolList), request, apiResponse);
    }

    public void getUUIDInfo(String uuid, APIResponse apiResponse) {
        String request = dao.UUIDInfoRequest(uuid);
        myApplication.fetchWebAPI().onGetEntity(myApplication.
                InitUrl(WebAPI.uuidInfo), request, apiResponse);
    }

    public void addUUID(String uuid, APIResponse apiResponse) {
        String request = dao.uuidRequest(uuid);
        myApplication.fetchWebAPI().onPostEntity(myApplication.
                InitUrl(WebAPI.addUUID), request, apiResponse);
    }

    public void deleteUUID(String uuid, APIResponse apiResponse) {
        String request = dao.uuidRequest(uuid);
        myApplication.fetchWebAPI().onPostEntity(myApplication.
                InitUrl(WebAPI.deleteUUID), request, apiResponse);
    }

    public void logout(APIResponse apiResponse) {
        myApplication.fetchWebAPI().onPostEntity(myApplication.
                InitUrl(WebAPI.logout), "", apiResponse);
    }

    public void onLoadMessageInfo(String uuid, int pages, int pageCount,
                                  APIResponse<List<MessageInfoEntity>> apiResponse) {
        String request = dao.messageInfoRequest(uuid, pages, pageCount);
        myApplication.fetchWebAPI().onGetEntity(myApplication.
                InitUrl(WebAPI.messageInfo), request, apiResponse);
    }

    public void onUploadDeviceToken(String type, String device_token, APIResponse apiResponse) {
        String request = dao.uploadRequest(type, device_token);
        myApplication.fetchWebAPI().onPostEntity(myApplication.
                InitUrl(WebAPI.deviceToken), request, apiResponse);
    }

    public void isValidUUID(String uuid, APIResponse<String> apiResponse) {
        String request = dao.uuidRequest(uuid);
        myApplication.fetchWebAPI().onPostEntity(myApplication.
                InitUrl(WebAPI.isValidUUID), request, apiResponse);
    }

    public void getUserInfo(APIResponse<List<UserInfoEntity>> apiResponse) {
        myApplication.fetchWebAPI().onGetEntity(myApplication.
                InitUrl(WebAPI.userInfo), "", apiResponse);
    }
}
