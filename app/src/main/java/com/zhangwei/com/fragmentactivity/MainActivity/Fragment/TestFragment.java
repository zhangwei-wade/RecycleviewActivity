package com.zhangwei.com.fragmentactivity.MainActivity.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zhangwei.com.fragmentactivity.BaseGlobal.BaseFragment.BaseFragment;
import com.zhangwei.com.fragmentactivity.BaseGlobal.BaseFragment.BaseTitleBarFragment;
import com.zhangwei.com.fragmentactivity.MainActivity.Adapter.TestAdapter;
import com.zhangwei.com.fragmentactivity.R;
import com.zhangwei.framelibs.CustomControl.CustomRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wade on 2016/7/29.
 */
public class TestFragment extends BaseFragment {
    private CustomRecyclerView mTestRecycler;//自定加载更多和下拉刷新的控件
    private RecyclerView mRecyclerView;//用来显示数据的
    private TestAdapter mTestAdapter;//显示数据的加载适配器
    private static List<String> data = new ArrayList<>();//数据源
    private LinearLayoutManager ltm;//用来管理数据的显示形式

    static {
        data.add("CoordinatorLayout");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setContentView(R.layout.fisrtlayout);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InitView();

    }

    /**
     * 初始化控件
     */
    private void InitView() {
        mTestRecycler = $.findViewById(R.id.testRecycler);
        mTestRecycler = $.findViewById(R.id.testRecycler);
        mTestRecycler = $.findViewById(R.id.testRecycler);
        mRecyclerView = mTestRecycler.getRecyclerView();//1231
        ltm = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(ltm);
        mTestAdapter = new TestAdapter(getActivity(), data);
        mRecyclerView.setAdapter(mTestAdapter);
    }
}
