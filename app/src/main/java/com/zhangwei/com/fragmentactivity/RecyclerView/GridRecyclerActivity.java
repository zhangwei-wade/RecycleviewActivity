package com.zhangwei.com.fragmentactivity.RecyclerView;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.zhangwei.com.fragmentactivity.BaseGlobal.BaseActivity.BaseActivity;
import com.zhangwei.com.fragmentactivity.BaseGlobal.DividerGridItemDecoration;
import com.zhangwei.com.fragmentactivity.BaseGlobal.DividerItemDecoration;
import com.zhangwei.com.fragmentactivity.R;
import com.zhangwei.com.fragmentactivity.RecyclerView.Adapter.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DCSHA192 on 2016/8/18.
 */
public class GridRecyclerActivity extends BaseActivity {
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private RecyclerAdapter adapter;//recyclerView适配器
    private List<String> data = new ArrayList<>();//显示的数据源
    private DividerGridItemDecoration listDivider;//垂直显示分割线

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_Base);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_layout);
        listDivider = new DividerGridItemDecoration(this, R.drawable.listdividerg);
        Init();
        BindData();
    }


    private void Init() {
        mToolbar = $.findViewById(R.id.toolbar);
        mRecyclerView = $.findViewById(R.id.recyclerView);
        mToolbar.setTitle("GridRecycler");// 标题的文字需在setSupportActionBar之前，不然会无效
        setSupportActionBar(mToolbar);
        GridLayoutManager ltm = new GridLayoutManager(this, 4);
        mRecyclerView.setLayoutManager(ltm);//设置recycleview的显示方式
        mRecyclerView.addItemDecoration(listDivider);//添加垂直分割线
        adapter = new RecyclerAdapter(this, data);
        mRecyclerView.setAdapter(adapter);
    }

    /**
     * 添加数据
     */
    private void BindData() {
        int count = 20;
        for (int i = 0; i < count; i++) {
            data.add("DCTest000" + i);
        }
        adapter.notifyItemRangeInserted(data.size() - count, count);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                data.add(2, "DCTest000" + data.size());
                adapter.notifyItemInserted(2);
//                data.add(2, "DCTest000" + data.size());
//                data.add(2, "DCTest000" + data.size());
//                adapter.notifyItemRangeInserted(2, 3);
//                adapter.notifyDataSetChanged();
                break;
            case R.id.remove:
                data.remove(2);
                adapter.notifyItemRemoved(2);
//                adapter.notifyDataSetChanged();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
