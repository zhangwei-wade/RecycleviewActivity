package com.zhangwei.com.fragmentactivity.BaseGlobal.BaseAdapter;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhangwei.com.fragmentactivity.Module.Interface.DCItemClickInterface;
import com.zhangwei.com.fragmentactivity.Module.Interface.DCItemLongClickInterface;

/**
 * Created by DCSHA192 on 2016/8/17.
 */
public class DCBaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    private DCItemClickInterface itemClickInterface;
    private DCItemLongClickInterface itemLongClickInterface;

    public DCBaseViewHolder(View view, DCItemClickInterface clickInterface,
                            DCItemLongClickInterface longClickInterface) {
        super(view);
        onCreate(view);
        this.itemClickInterface = clickInterface;
        this.itemLongClickInterface = longClickInterface;
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
    }

    public void onCreate(View view) {
    }

    @Override
    public void onClick(View v) {
        if (itemClickInterface != null)
            itemClickInterface.onItemClick(v, getAdapterPosition());
    }

    @Override
    public boolean onLongClick(View v) {
        if (itemLongClickInterface != null)
            itemLongClickInterface.onLongItemClick(v, getAdapterPosition());
        return false;
    }
}
