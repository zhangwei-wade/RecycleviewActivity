package com.zhangwei.com.fragmentactivity.CoordinatorLayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zhangwei.com.fragmentactivity.BaseGlobal.BaseActivity.BaseActivity;
import com.zhangwei.com.fragmentactivity.BaseGlobal.DividerItemDecoration;
import com.zhangwei.com.fragmentactivity.CoordinatorLayout.CoordinatorItemActivity.CoordinatorSnackBar;
import com.zhangwei.com.fragmentactivity.CoordinatorLayout.CoordinatorItemActivity.CoordinatorToolBar;
import com.zhangwei.com.fragmentactivity.Module.Interface.DCItemClickInterface;
import com.zhangwei.com.fragmentactivity.R;
import com.zhangwei.com.fragmentactivity.RecyclerView.Adapter.RecyclerAdapter;
import com.zhangwei.com.fragmentactivity.RecyclerView.RecyclerItemActivity.GridRecyclerActivity;
import com.zhangwei.com.fragmentactivity.RecyclerView.RecyclerItemActivity.HorizontalRecyclerActivity;
import com.zhangwei.com.fragmentactivity.RecyclerView.RecyclerItemActivity.StaggeredGridHorizontalActivity;
import com.zhangwei.com.fragmentactivity.RecyclerView.RecyclerItemActivity.StaggeredGridVerticalActivity;
import com.zhangwei.com.fragmentactivity.RecyclerView.RecyclerItemActivity.VerticalRecyclerActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DCSHA192 on 2016/8/26.
 */
public class CoordinatorActivity extends BaseActivity implements DCItemClickInterface {
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private RecyclerAdapter adapter;//recyclerView适配器
    private List<String> data = new ArrayList<>();//显示的数据源
    private DividerItemDecoration listDivider;//垂直显示分割线
    private String[] arry = new String[]{
            "CoordinatorSnackBar", "CollapsingToolbarLayout"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_Base);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_layout);
        listDivider = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST);
        Init();
        BindData();
    }


    private void Init() {
        mToolbar = $.findViewById(R.id.toolbar);
        mRecyclerView = $.findViewById(R.id.recyclerView);
        mToolbar.setTitle("RecyclerView");// 标题的文字需在setSupportActionBar之前，不然会无效
        setSupportActionBar(mToolbar);
        LinearLayoutManager ltm = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(ltm);//设置recycleview的显示方式
        mRecyclerView.addItemDecoration(listDivider);//添加垂直分割线
        adapter = new RecyclerAdapter(this, data);
        mRecyclerView.setAdapter(adapter);
        adapter.setItemClickInterface(this);
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
        Intent intent = null;
        switch (position) {
            case 0:
                intent = new Intent(this, CoordinatorSnackBar.class);
                break;
            case 1:
                intent = new Intent(this, CoordinatorToolBar.class);
                break;
        }
        if (intent != null)
            startActivity(intent);
    }

}
