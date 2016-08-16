package com.zhangwei.framelibs.Global.WebViewActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.zhangwei.framelibs.Global.AbstractClass.ApplicationTitleBarFragment;
import com.zhangwei.framelibs.Global.AbstractClass.BaseGlobal;
import com.zhangwei.framelibs.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2014/7/28.
 * <p/>
 * <p/>
 * Fragment      WebView
 * <p/>
 * <p/>
 * 目的是为了给FragmentActivity引用的
 */
public class WebViewFragment extends ApplicationTitleBarFragment implements View.OnKeyListener {
    private WebView webView;
    private String title;
    private String url;
    private boolean status = false;
    private ProgressBar progressBar;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentViewFun(R.layout.webview);
        title = getArguments().getString(BaseGlobal.title);
        url = getArguments().getString(BaseGlobal.url);
        status = getArguments().getBoolean(BaseGlobal.status, false);
        webView = $.findViewById(R.id.webView);
        webView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        webView.setWebChromeClient(new chromeClient());
        webView.setWebViewClient(new webViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAllowFileAccess(true);

        $.findViewById(R.id.showImg).setVisibility(View.VISIBLE);
        progressBar = $.findViewById(R.id.webViewPB);


        titleBar.setTitleText(title);
        webView.setOnKeyListener(this);
        webView.loadUrl(url);
        BaseGlobal.playLog(url);
       // titleBar.setTitleBackTextViewLeftVisible(false);
        if (status) {
            titleBar.setTitleBackTextViewLeftVisible(false);

        }
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {  //表示按返回键 时的操作
                webView.goBack();   //后退
                return true;    //已处理
            }
        }
        return false;
    }

    class chromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                progressBar.setVisibility(View.GONE);
            }
            super.onProgressChanged(view, newProgress);

        }
    }

    class webViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.contains("tel")) {
                String regEx = "[^0-9]";
                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(url);
//                phoneCallTask(m.replaceAll("").trim());
                return true;
            }
            view.loadUrl(url);
            //如果不需要其他对点击链接事件的处理返回true，否则返回false
            return true;
        }
    }
}
