package com.zhangwei.framelibs.CustomControl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhangwei.framelibs.Global.InitView;
import com.zhangwei.framelibs.Global.Other.DisplayUtil;
import com.zhangwei.framelibs.R;

/**
 * Created by Administrator on 2014/4/28.
 */
public class CustomTitleBarBackControl extends LinearLayout implements View.OnClickListener {
    private TextView title_back_tv_text;
    private ImageView title_back_BTN_left;
    private OnTitleBarBackListenClick onTitleBarBackListenClick;
    private LinearLayout titleBackGroup;
    private ImageView imageView_share;

    public CustomTitleBarBackControl(Context context) {
        super(context, null);
    }


    public CustomTitleBarBackControl(Context context, AttributeSet attrs) {
        super(context, attrs);
        Init(context);
    }


    private void Init( final Context context) {
        InitView $ = new InitView(context, R.layout.custom_titlebarback, this);
        if (isInEditMode()) {//如果只是显示视图的话就不运行下面代码以免查看视图时报错
            return;
        }

        title_back_BTN_left = $.findViewById(R.id.title_back_BTN_left);
        imageView_share = $.findViewById(R.id.imageView_share);
        title_back_BTN_left.setOnClickListener(this);
        title_back_tv_text = $.findViewById(R.id.title_back_tv_text);
        title_back_tv_text.setTextSize(DisplayUtil.pt2sp(36f));
        titleBackGroup = $.findViewById(R.id.titleBackGroup);


       /* imageView_share.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "消息详情";

                String title = (String) mapMerchData.get("Name_cn");
                String imgUrl = (String) mapMerchData.get("DefaultImage");
                String Description = (String) mapMerchData.get("Description");
                UMImage image = new UMImage(MerchDetailActivity2.this, urlInfo.getBASE_URL()+imgUrl);

                UMImage image = new UMImage(context, "http://www.umeng.com/images/pic/social/integrated_3.png");


                final SHARE_MEDIA[] displaylist = new SHARE_MEDIA[]
                        {
                                SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.SINA,
                                SHARE_MEDIA.QQ
                        };
                new ShareAction((Activity) context).setDisplayList(displaylist)
                        .withText("呵呵")
                        .withTitle("title")
                        .withTargetUrl("http://www.baidu.com")
                        .withMedia(image)
                        .setListenerList(umShareListener, umShareListener)
                        .setShareboardclickCallback(shareBoardlistener)
                        .open();
                Log.d("syso", "分享");
          *//*      String appId = "wxbaecac6ac5769e25";
                String appSecret = "d6f38e197be80c4c3a1ed5f1f242eaf7";
// 添加微信平台
                UMWXHandler wxHandler = new UMWXHandler(getContext(),appId,appSecret);
                wxHandler.addToSocialSDK();
// 添加微信朋友圈
                UMWXHandler wxCircleHandler = new UMWXHandler(getActivity(),appId,appSecret);
                wxCircleHandler.setToCircle(true);
                wxCircleHandler.addToSocialSDK();*//*
            }
        });*/
        $.getView().setLayoutParams(new ViewGroup.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtil.pt2sp(100f)));

    }



    public void setOnTitleBarBackListenClick(OnTitleBarBackListenClick onTitleBarBackListenClick) {
        this.onTitleBarBackListenClick = onTitleBarBackListenClick;
    }

    public void setTitleText(String text) {
        title_back_tv_text.setText(text);
    }

    public void setTitleBackTextViewLeftVisible(boolean status) {
        if (status) {
            title_back_BTN_left.setVisibility(VISIBLE);

        } else {
            title_back_BTN_left.setVisibility(VISIBLE);

        }
    }

    public void setTitleBackGroupRightVisible(boolean status) {
        if (status) {
            titleBackGroup.setVisibility(VISIBLE);
        } else {
            titleBackGroup.setVisibility(INVISIBLE);
        }
    }

    public void addRightGroupView(View view) {
        titleBackGroup.addView(view);
    }

    @Override
    public void onClick(View v) {
        if (onTitleBarBackListenClick != null)
            if (v.getId() == R.id.title_back_BTN_left) {
                if (onTitleBarBackListenClick != null)
                    onTitleBarBackListenClick.onLeftClick();
            }
    }

    public  void setLeftSrc(int res){
        title_back_BTN_left.setImageResource(res);
    }
    public interface OnTitleBarBackListenClick {
        void onLeftClick();
    }



}
