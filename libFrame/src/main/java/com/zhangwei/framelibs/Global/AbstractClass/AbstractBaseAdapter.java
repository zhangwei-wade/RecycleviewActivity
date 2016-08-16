package com.zhangwei.framelibs.Global.AbstractClass;

import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;

/**
 * Created by Administrator on 2014/4/23.
 * <p/>
 * CustomListView的setMyOnScrollListener也是滚动事件
 */
public abstract class AbstractBaseAdapter extends BaseAdapter implements AbsListView.OnScrollListener {
    private boolean scrollStatue = false;//当滚动停止时的状态
    protected AbsListView absListView;
    private boolean isFirst = true;
    private AbsListView.OnScrollListener scrollListener;

    protected AbstractBaseAdapter(AbsListView absListView) {
        this.absListView = absListView;
        InitScrollListener();
    }

    protected void InitScrollListener() {
        absListView.setOnScrollListener(this);
    }

    public void setScrollListener(AbsListView.OnScrollListener scrollListener) {
        this.scrollListener = scrollListener;
    }

    /**
     * 如果是其它AbsListView就重写以下两个方法
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (scrollListener != null)
            scrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
        if (totalItemCount > 0 && isFirst) {
            isFirst = false;
            refreshImageView();
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollListener != null)
            scrollListener.onScrollStateChanged(view, scrollState);
        switch (scrollState) {
            case SCROLL_STATE_IDLE://已停止
                scrollStatue = false;
                refreshImageView();
                break;
            case SCROLL_STATE_FLING://开始滚动
                scrollStatue = true;
                break;
            case SCROLL_STATE_TOUCH_SCROLL://正在滚动
                scrollStatue = true;
                break;
        }
    }

    /**
     * 更新item里面的控件数据
     */
    public void refreshImageView() {
        try {
            if (absListView == null)
                return;
            int start;
            int end;
            if (absListView instanceof ListView) {
                if (((ListView) absListView).getHeaderViewsCount() != 0 &&
                        absListView.getFirstVisiblePosition() > 0) {
                    start = absListView.getFirstVisiblePosition() - 1;
                } else {
                    start = 0;
                }
                end = absListView.getLastVisiblePosition();
            } else {
                start = absListView.getFirstVisiblePosition();
                end = absListView.getLastVisiblePosition();
            }
//            if (end == getCount() && getCount() > 2) {
//                end--;
//            }
//            if (absListView.getLastVisiblePosition() + 1 == absListView.getChildCount()) {
//                end--;
//            }
            refreshImageView(absListView, start, end);
            BaseGlobal.playELog(start + "   " + end);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract void refreshImageView(AbsListView view, int start, int end) throws Exception;

    public boolean isScroll() {
        return scrollStatue;
    }
}
