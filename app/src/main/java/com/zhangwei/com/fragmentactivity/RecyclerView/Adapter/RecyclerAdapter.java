package com.zhangwei.com.fragmentactivity.RecyclerView.Adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhangwei.com.fragmentactivity.BaseGlobal.BaseAdapter.DCBaseViewHolder;
import com.zhangwei.com.fragmentactivity.Module.Interface.DCItemClickInterface;
import com.zhangwei.com.fragmentactivity.Module.Interface.DCItemLongClickInterface;
import com.zhangwei.com.fragmentactivity.R;

import java.util.List;

/**
 * Created by wade on 2016/4/5.
 * <p/>
 * 图片加载适配器
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private final Context context;
    private List<String> mData;
    private DCItemClickInterface itemClickInterface;
    private DCItemLongClickInterface itemLongClickInterface;

    public RecyclerAdapter(Context context, List<String> mData) {
        this.context = context;
        this.mData = mData;
    }


    /**
     * 设置item点击事件
     */
    public void setItemClickInterface(DCItemClickInterface itemClickInterface) {
        this.itemClickInterface = itemClickInterface;
    }

    /**
     * 设置item长按点击事件
     */
    public void setItemLongClickInterface(DCItemLongClickInterface itemLongClickInterface) {
        this.itemLongClickInterface = itemLongClickInterface;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.test_layout, parent,
                        false), itemClickInterface, itemLongClickInterface);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.mName.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends DCBaseViewHolder {
        AppCompatTextView mName;

        public MyViewHolder(View view, DCItemClickInterface clickInterface, DCItemLongClickInterface longClickInterface) {
            super(view, clickInterface, longClickInterface);
        }

        @Override
        public void onCreate(View view) {
            super.onCreate(view);
            mName = (AppCompatTextView) view.findViewById(R.id.funName);
        }
    }

}
