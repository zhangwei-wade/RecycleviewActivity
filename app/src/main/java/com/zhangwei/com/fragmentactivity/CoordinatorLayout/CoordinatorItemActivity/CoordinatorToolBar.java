package com.zhangwei.com.fragmentactivity.CoordinatorLayout.CoordinatorItemActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.zhangwei.com.fragmentactivity.BaseGlobal.BaseActivity.BaseActivity;
import com.zhangwei.com.fragmentactivity.BaseGlobal.DividerItemDecoration;
import com.zhangwei.com.fragmentactivity.Module.Interface.DCItemClickInterface;
import com.zhangwei.com.fragmentactivity.R;
import com.zhangwei.com.fragmentactivity.RecyclerView.Adapter.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DCSHA192 on 2016/8/26.
 */
public class CoordinatorToolBar extends BaseActivity implements DCItemClickInterface, View.OnClickListener {
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private DividerItemDecoration listDivider;//垂直显示分割线
    private RecyclerAdapter adapter;
    private List<String> data = new ArrayList<>();//显示的数据源
    private String[] arry;
    private FloatingActionButton mFloatAB;
    private CoordinatorLayout mCoordinator;
    private CollapsingToolbarLayout mToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_Base);
        setContentView(R.layout.coordinator_toolbarlayout);
        arry = getResources().getStringArray(R.array.array);
        listDivider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        Init();
        BindData();
    }

    private void Init() {
        mCoordinator = $.findViewById(R.id.TBlCoordinatorMain);
        mToolbar = $.findViewById(R.id.TBLToolbar);
        mRecyclerView = $.findViewById(R.id.recyclerView);
        mToolbarLayout = $.findViewById(R.id.toolbarLayout);
        mToolbar.setTitle("CollapsingToolbarLayout");// 标题的文字需在setSupportActionBar之前，不然会无效
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        LinearLayoutManager ltm = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(ltm);//设置recycleview的显示方式
        mRecyclerView.addItemDecoration(listDivider);//添加垂直分割线
        adapter = new RecyclerAdapter(this, data);
        mRecyclerView.setAdapter(adapter);
        adapter.setItemClickInterface(this);
        mFloatAB = $.findViewById(R.id.floatAB);
        mFloatAB.setOnClickListener(this);
        mToolbarLayout.setTitle("CollapsingToolbarLayout");
        //通过CollapsingToolbarLayout修改字体颜色
        mToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
        mToolbarLayout.setCollapsedTitleTextColor(Color.RED);//设置收缩后Toolbar上字体的颜色
    }

    /**
     * 添加数据
     */
    private void BindData() {
        int count = 20;
        for (int i = 0; i < arry.length; i++) {
            data.add(arry[i]);
        }
        adapter.notifyItemRangeInserted(data.size() - count, count);
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floatAB:
                TextView tv = new TextView(this);
                tv.setText("赵兵兵就是一个2B");
                Snackbar.make(mCoordinator, tv.getText(), Snackbar.LENGTH_LONG).show();
                break;

        }
    }
}
