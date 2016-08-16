package com.zhangwei.framelibs.CustomControl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.zhangwei.framelibs.Global.InitView;
import com.zhangwei.framelibs.R;

/**
 * Created by Administrator on 2014/5/24.
 */
public class ImageLoading extends LinearLayout {
    private Context context;
    private ImageView imageView;
    private ProgressBar progressBar;

    public ImageLoading(Context context) {
        super(context);
        this.context = context;
        Init();
    }

    public ImageLoading(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        Init();
    }

    private void Init() {
        InitView InitView = new InitView(context, R.layout.custom_control_imageview, this);
        this.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView = InitView.findViewById(R.id.iv_loading);
        progressBar = InitView.findViewById(R.id.pb_loading);
    }

    public void setImageView(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
        progressBar.setVisibility(INVISIBLE);
    }

    public void setImageView(Drawable drawable) {
        imageView.setImageDrawable(drawable);
        progressBar.setVisibility(INVISIBLE);
    }
}
