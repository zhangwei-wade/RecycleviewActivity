package com.zhangwei.com.fragmentactivity.MainActivity.Adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.zhangwei.com.fragmentactivity.R;

import java.util.List;

/**
 * Created by wade on 2016/4/5.
 * <p/>
 * 图片加载适配器
 */
public class TestAdapter extends RecyclerView.Adapter<TestAdapter.MyViewHolder> {
    private final Context context;
    private List<String> mData;

    public TestAdapter(Context context, List<String> mData) {
        this.context = context;
        this.mData = mData;
    }

    public void addData(int position) {
        notifyItemInserted(position);
        notifyItemRangeChanged(position, mData.size());
    }

    public void removeData(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mData.size());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.test_layout, parent,
                        false));
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

    class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView mName;

        public MyViewHolder(View view) {
            super(view);
            mName = (AppCompatTextView) view.findViewById(R.id.funName);
        }
    }

}
