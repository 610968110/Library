package com.lbx.library.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
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
 * @date 2019/1/21.
 */

public class BlendImageButton extends FrameLayout {

    public BlendImageButton(@NonNull Context context) {
        this(context, null);
    }

    public BlendImageButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BlendImageButton(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View v = XTools.UiUtil().inflate(R.layout.view_blend_btn);
        addView(v);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BlendImageButton);
        String string = a.getString(R.styleable.BlendImageButton_text);
        Drawable drawable = a.getDrawable(R.styleable.BlendImageButton_image);
        a.recycle();

        TextView textView = v.findViewById(R.id.tv_s);
        ImageView imageView = v.findViewById(R.id.iv_s);
        textView.setText(string);
        imageView.setImageDrawable(drawable);
    }
}
