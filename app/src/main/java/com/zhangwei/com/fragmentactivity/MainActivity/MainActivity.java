package com.zhangwei.com.fragmentactivity.MainActivity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.zhangwei.com.fragmentactivity.BaseGlobal.BaseActivity.BaseActivity;
import com.zhangwei.com.fragmentactivity.BaseGlobal.Global.Global;
import com.zhangwei.com.fragmentactivity.MainActivity.Fragment.TestFragment;
import com.zhangwei.com.fragmentactivity.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        TestFragment testFragment = new TestFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Global.url, "");
        bundle.putString(Global.title, "test");
        testFragment.setArguments(bundle);
        ft.add(R.id.mainFrameL, testFragment);
        ft.commit();
    }
}
