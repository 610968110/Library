package com.lbx.library.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

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
 * @date 2019/3/5.
 */

public class OptionTextView extends LinearLayout {

    private static final int SELECT_COLOR = Color.parseColor("#9F0303");
    private static final int NORMAL_COLOR = Color.parseColor("#A07246");
    private static final int OPTIONS_TEXT_SIZE = 14;
    private static final int OPTIONS_BOX_SIZE = XTools.WindowUtil().dip2px(14);
    private TextView mTextView;
    private MyCheckBox mCheckBox;

    public OptionTextView(@NonNull Context context) {
        this(context, null);
    }

    public OptionTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OptionTextView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        mCheckBox = new MyCheckBox(context);
        mTextView = new TextView(context);
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, OPTIONS_TEXT_SIZE);
        mTextView.setGravity(Gravity.CENTER_VERTICAL);
        mTextView.setPadding(20, 0, 0, 0);
        addView(mCheckBox, new ViewGroup.LayoutParams(OPTIONS_BOX_SIZE, OPTIONS_BOX_SIZE));
        addView(mTextView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        setSelect(false);
    }

    public void setSelect(boolean select) {
        mTextView.setTextColor(select ? SELECT_COLOR : NORMAL_COLOR);
        mCheckBox.setSelect(select);
    }

    public void setOption(String option) {
        mTextView.setText(option);
    }
}
