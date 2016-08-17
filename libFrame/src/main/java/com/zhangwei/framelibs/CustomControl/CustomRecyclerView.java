package com.zhangwei.framelibs.CustomControl;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.zhangwei.framelibs.Global.AbstractClass.BaseGlobal;
import com.zhangwei.framelibs.Global.InitView;
import com.zhangwei.framelibs.R;

import javax.microedition.khronos.opengles.GL;

/**
 * Created by wade on 2016/3/29.
 */
public class CustomRecyclerView extends LinearLayout implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout swipeRefreshLayout;//下拉刷新view
    private RecyclerView recyclerView;//显示数据控件
    private LinearLayoutManager mLayoutManager;//recyclerView layout管理工具
    private int lastVisibleItem;//最后显示item索引
    private OnMoreClickListener onMoreClickListener;//下拉刷新加载更新回调接口

    public CustomRecyclerView(Context context) {
        super(context);
        Init(context);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Init(context);
    }

    /**
     * 初始化view
     */
    private void Init(Context context) {
        InitView initView = new InitView(context, R.layout.custom_recyclerview, this);
        swipeRefreshLayout = initView.findViewById(R.id.swipeRefresh1);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = initView.findViewById(R.id.recycler1);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView,
                                             int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == recyclerView.getAdapter().getItemCount()) {
                    BaseGlobal.playLog("MeasuredHeight:" + recyclerView.getMeasuredHeight() +
                            "Height:" + recyclerView.getHeight());
//                    swipeRefreshLayout.setRefreshing(true);
                    // 此处在现实项目中，请换成网络请求数据代码，sendRequest .....
                    handler.sendEmptyMessageDelayed(1, 500);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.getLayoutManager() instanceof LinearLayoutManager)
                    lastVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).
                            findLastVisibleItemPosition();
                if (recyclerView.getLayoutManager() instanceof GridLayoutManager)
                    lastVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).
                            findLastVisibleItemPosition();
            }
        });
    }


    /**
     * 设置下拉更新加载更多回调接口
     */
    public void setOnMoreClickListener(OnMoreClickListener onMoreClickListener) {
        this.onMoreClickListener = onMoreClickListener;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    /**
     * 获取SwipeRefreshLayout
     */
    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return swipeRefreshLayout;
    }

    /**
     * 获取RecyclerView
     */
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    @Override
    public void onRefresh() {
        handler.sendEmptyMessageAtTime(0, 6000);
    }

    public interface OnMoreClickListener {
        void onRefresh();

        void onMore();
    }

    /**
     * 0代表更新，1代表加载更多
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                swipeRefreshLayout.setRefreshing(false);
                if (onMoreClickListener != null)
                    onMoreClickListener.onRefresh();
            } else if (onMoreClickListener != null)
                onMoreClickListener.onMore();
        }
    };
}
