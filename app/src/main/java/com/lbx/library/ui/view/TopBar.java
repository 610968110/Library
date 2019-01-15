package com.lbx.library.ui.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lbx.library.R;

import lbx.xtoollib.XTools;

/**
 * .  ┏┓　　　┏┓
 * .┏┛┻━━━┛┻┓
 * .┃　　　　　　　┃
 * .┃　　　━　　　┃
 * .┃　┳┛　┗┳　┃
 * .┃　　　　　　　┃
 * .┃　　　┻　　　┃
 * .┃　　　　　　　┃
 * .┗━┓　　　┏━┛
 * .    ┃　　　┃        神兽保佑
 * .    ┃　　　┃          代码无BUG!
 * .    ┃　　　┗━━━┓
 * .    ┃　　　　　　　┣┓
 * .    ┃　　　　　　　┏┛
 * .    ┗┓┓┏━┳┓┏┛
 * .      ┃┫┫　┃┫┫
 * .      ┗┻┛　┗┻┛
 *
 * @author lbx
 * @date 2019/1/15.
 */

public class TopBar extends FrameLayout {

    private ImageView mLeftImageView, mRightImageView;
    private TextView mTitleView;

    public TopBar(@NonNull Context context) {
        this(context, null);
    }

    public TopBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TopBar(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = XTools.UiUtil().inflate(R.layout.view_bar);
        mLeftImageView = view.findViewById(R.id.iv_bar_left);
        mRightImageView = view.findViewById(R.id.iv_bar_right);
        mTitleView = view.findViewById(R.id.tv_bar_title);
        addView(view);
    }

    public void bind(Activity activity) {
        bind(activity, "");
    }

    public void bind(Activity activity, String title) {
        mLeftImageView.setImageResource(R.drawable.finish);
        mLeftImageView.setOnClickListener(v -> activity.finish());
        setTitle(title, Gravity.START);
    }

    public void setTitle(String title) {
        setTitle(title, Gravity.CENTER);
    }

    public void setTitle(String title, int gravity) {
        mTitleView.setGravity(gravity | Gravity.CENTER_VERTICAL);
        mTitleView.setText(title);
    }

    public void setLeftIconImg(@DrawableRes int img) {
        setLeftIconImg(img, null);
    }

    public void setLeftIconImg(@DrawableRes int img, OnClickListener listener) {
        mLeftImageView.setImageResource(img);
        mLeftImageView.setOnClickListener(listener);
    }

    public void setRightIconImg(@DrawableRes int img) {
        setLeftIconImg(img, null);
    }

    public void setRightIconImg(@DrawableRes int img, OnClickListener listener) {
        mRightImageView.setImageResource(img);
        mRightImageView.setOnClickListener(listener);
    }
}
