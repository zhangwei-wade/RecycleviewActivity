package com.zhangwei.framelibs.Global.WebViewActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.zhangwei.framelibs.Global.AbstractClass.ApplicationActivity;
import com.zhangwei.framelibs.Global.AbstractClass.BaseGlobal;
import com.zhangwei.framelibs.R;

/**
 * Created by Administrator on 2014/6/5.
 * <p>
 * 自定义webview
 */
public class WebViewActivityThis extends ApplicationActivity {
    private WebViewFragment webViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otheractivity);
        Bundle bundle = new Bundle();
        bundle.putString(BaseGlobal.title, getIntent().getStringExtra(BaseGlobal.title));
        bundle.putString(BaseGlobal.url, getIntent().getStringExtra(BaseGlobal.url));
        bundle.putString(BaseGlobal.context, getIntent().getStringExtra(BaseGlobal.context));
        bundle.putBoolean(BaseGlobal.status, getIntent().getBooleanExtra(BaseGlobal.status, false));
        webViewFragment = new WebViewFragment();
        webViewFragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.OtherContent, webViewFragment);
        transaction.commit();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        overridePendingTransitionOut();
    }
}
