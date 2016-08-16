package com.zhangwei.framelibs.CustomControl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.WindowManager;

import com.makeramen.roundedimageview.RoundedImageView;
import com.zhangwei.framelibs.Global.AbstractClass.BaseGlobal;
import com.zhangwei.framelibs.Global.Other.AsyncImageLoader;
import com.zhangwei.framelibs.Global.Other.NativeImageLoader;

/**
 * 传入path加载图片
 */
public class MyImageView extends RoundedImageView {
    private OnMeasureListener onMeasureListener;
    private boolean status = true;
    /**
     * 图片的高度宽度
     */
    private Point mPoint = new Point(0, 0);
    private String url = null;

    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setOnMeasureListener(OnMeasureListener onMeasureListener) {
        this.onMeasureListener = onMeasureListener;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (getMeasuredWidth() > 0 && getMeasuredHeight() > 0 && status) {
            status = false;
            if (onMeasureListener != null) {
                onMeasureListener.onMeasureSize(getMeasuredWidth(), getMeasuredHeight());
            }
            mPoint.set(getMeasuredWidth(), getMeasuredHeight());
            loadingBitmap();
        }
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
    }

    public Point getPoint() {
        return mPoint;
    }

    public interface OnMeasureListener {
        void onMeasureSize(int width, int height);
    }

    public void setImageUrl(String url) {
        this.url = url;
    }

    public void loadingBitmap() {
        if (TextUtils.isEmpty(url))
            return;
        Bitmap bitmap = NativeImageLoader.getInstance().getBitmapFromMemCache(
                BaseGlobal.fetchUrlDownName(url));
        if (bitmap != null) {
//            BaseGlobal.invalidate(this, bitmap);
            return;
        }
        if (mPoint.x == 0 && mPoint.y == 0) {
            WindowManager wm = (WindowManager) getContext()
                    .getSystemService(Context.WINDOW_SERVICE);
            int width = wm.getDefaultDisplay().getWidth();
            int height = wm.getDefaultDisplay().getHeight();
            mPoint.set(width, height);
        }
        if (url.contains("http://")) {
            AsyncImageLoader.getInstance().fetchBitmap(getContext(), mPoint, this, url);
        } else {
            NativeImageLoader.getInstance().loadImage(url, mPoint, new NativeImageLoader.NativeImageCallBack() {
                @Override
                public void onImageLoader(Bitmap bitmap, String path) {
                    MyImageView.this.setImageBitmap(bitmap);
                }
            });
        }
        BaseGlobal.playLog(this.getClass().getName(), mPoint.toString() + url);
    }
}
