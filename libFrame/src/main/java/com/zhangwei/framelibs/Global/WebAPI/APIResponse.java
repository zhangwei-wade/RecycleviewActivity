package com.zhangwei.framelibs.Global.WebAPI;

import android.content.Context;
import android.os.Message;

import com.google.gson.Gson;
import com.zhangwei.framelibs.CustomControl.MessageDialog;
import com.zhangwei.framelibs.CustomControl.ToastMessage;
import com.zhangwei.framelibs.Global.AbstractClass.BaseGlobal;
import com.zhangwei.framelibs.Global.InterfaceClass.APIHttpInterface;
import com.zhangwei.framelibs.R;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Administrator on 2014/5/8.
 * <p/>
 * 访问接口后的回调类
 */
public class APIResponse<T> extends BaseAPIResponse implements APIHttpInterface<T> {
    private Gson gson = new Gson();
    private Type entityClass = String.class;
    private Context context;
    private MessageDialog messageDialog;
    private boolean status = false;
    private APIResponseDownLoadInterface apiResponseDownLoadInterface;

    public APIResponse(Context context) {
        this.context = context;
        Init();
    }

    public APIResponse(Context context, boolean status) {
        this.status = status;
        this.context = context;
        Init();
    }

    private void Init() {
        try {
            Type genType = getClass().getGenericSuperclass();//获取当前类的类型
            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
            if (params != null && params.length > 0)
                entityClass = params[0];//得到T这个对象的type
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void handleMessage(Message msg) {
        try {
            switch (msg.what) {
                case start:
                    onStart();
                    break;
                case success:
                    HandlerSuccess(msg);
                    break;
                case failure:
                    onFailure(msg.obj + "");
                    break;
                case progress:
                    onProgress(msg.arg1, msg.arg2);
                    break;
                case finish:
                    onFinish();
                    if (messageDialog != null && status) {
                        messageDialog.dismiss();
                    }
                    break;
            }
        } catch (Exception e) {
            BaseGlobal.playLog(e.toString() + context.getClass().toString());
            onFailure(e.toString());//数据转换失败的话
        } finally {

        }
    }


    /**
     * 数据请求开始调用
     * <p/>
     * 显示Dialog加载数据，如果不需要 就在重写的时候不要调用父方法
     */
    @Override
    public void onStart() {
        if (status) {
            messageDialog = new MessageDialog(context, context.getResources().getString(R.string.PleaseWait), true);
            messageDialog.show();
        }
    }

    /**
     * 返回的不是数组数据就调用这个方法,
     * 如果调用的是getPostEntity 返回的
     * 数据就在这个方法里面
     */
    @Override
    public void onSuccess(T object) {

    }

    /**
     * 处理失败就返回这个方法
     */
    @Override
    public void onFailure(String str) {
        if (str.equals("您还未登录，请登录")) {
            BaseGlobal.restartApplication(context);
        }
        if (!BaseGlobal.isEmpty(str)) {
            ToastMessage.getInstance().showToast(context, str);
        }
        if (str.equals(BaseGlobal.InternetConnectionFailure)) {
            ToastMessage.getInstance().showToast(context, context.getResources().getString(R.string.networkDisabled));
        }
        if (str.equals(BaseGlobal.ConnectionTimeOut)) {
            ToastMessage.getInstance().showToast(context, context.getResources().getString(R.string.ConnectionTimeOut));
        }
        if (str.equals(BaseGlobal.NoService)) {
            ToastMessage.getInstance().showToast(context, context.getResources().getString(R.string.networkDataFailure));
        }
        if (str.contains("error")) {
            ToastMessage.getInstance().showToast(context, context.getResources().getString(R.string.networkDataFailure));
        }
        if (str.contains("com.google.gson")) {
            ToastMessage.getInstance().showToast(context, "数据解析出错");
        }
    }


    /**
     * 数据请求结束调用
     */

    @Override
    public void onFinish() {

    }

    public void setApiResponseDownLoadInterface(APIResponseDownLoadInterface apiResponseDownLoadInterface) {
        this.apiResponseDownLoadInterface = apiResponseDownLoadInterface;
    }

    @Override
    public void onProgress(int current, int fileSize) {//current 的直接 就是百分比
        if (apiResponseDownLoadInterface != null) {
            apiResponseDownLoadInterface.Progress(current, fileSize);
        }
    }


    private void HandlerSuccess(Message msg) throws ClassNotFoundException {
        if (entityClass.equals(String.class)) {
            onSuccess((T) msg.obj.toString());
            return;
        }
        if (msg.obj.equals("") || msg.obj.equals("\"\"")) {
            onSuccess(null);
        } else
            onSuccess((T) gson.fromJson(msg.obj.toString(), entityClass));
    }

    public interface APIResponseDownLoadInterface {
        void Progress(int current, int fileLength);
    }
}
