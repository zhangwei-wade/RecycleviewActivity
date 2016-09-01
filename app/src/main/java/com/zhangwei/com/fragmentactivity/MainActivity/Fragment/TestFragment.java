package com.zhangwei.com.fragmentactivity.MainActivity.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhangwei.com.fragmentactivity.BaseGlobal.BaseFragment.BaseFragment;
import com.zhangwei.com.fragmentactivity.CoordinatorLayout.CoordinatorActivity;
import com.zhangwei.com.fragmentactivity.MainActivity.Adapter.TestAdapter;
import com.zhangwei.com.fragmentactivity.Module.Interface.DCItemClickInterface;
import com.zhangwei.com.fragmentactivity.Module.Interface.DCItemLongClickInterface;
import com.zhangwei.com.fragmentactivity.R;
import com.zhangwei.com.fragmentactivity.RecyclerView.RecyclerActivity;
import com.zhangwei.framelibs.CustomControl.CustomRecyclerView;
import com.zhangwei.framelibs.CustomControl.ToastMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wade on 2016/7/29.
 */
public class TestFragment extends BaseFragment implements DCItemClickInterface,
        DCItemLongClickInterface {
    private CustomRecyclerView mTestRecycler;//自定加载更多和下拉刷新的控件
    private RecyclerView mRecyclerView;//用来显示数据的
    private TestAdapter mTestAdapter;//显示数据的加载适配器
    private List<String> data = new ArrayList<>();//数据源
    private LinearLayoutManager ltm;//用来管理数据的显示形式

    private void initData() {
        data.add("RecyclerView");
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
        initData();
        InitView();
    }

    /**
     * 初始化控件
     */
    private void InitView() {
        mTestRecycler = $.findViewById(R.id.testRecycler);
        mRecyclerView = mTestRecycler.getRecyclerView();
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        ltm = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(ltm);
        mTestAdapter = new TestAdapter(getActivity(), data);
        mRecyclerView.setAdapter(mTestAdapter);
        mTestAdapter.setItemClickInterface(this);
        mTestAdapter.setItemLongClickInterface(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        ToastMessage.getInstance().showToast(getActivity(), position + "");
        switch (position) {
            case 0:
                Intent intent = new Intent(getActivity(), RecyclerActivity.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(getActivity(), CoordinatorActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onLongItemClick(View view, int position) {
        ToastMessage.getInstance().showToast(getActivity(), position + "");
    }
}
