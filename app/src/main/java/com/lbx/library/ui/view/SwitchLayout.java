package com.lbx.library.ui.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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

public class SwitchLayout extends LinearLayout {

    private SwitchButton mSwitchButton;
    private TextView mTextView;

    public SwitchLayout(@NonNull Context context) {
        this(context, null);
    }

    public SwitchLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwitchLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

        mTextView = new TextView(context);
        mTextView.setText(R.string.auto_play);
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
        mTextView.setTextColor(XTools.ResUtil().getColor(R.color.colorAccent2));
        mTextView.setGravity(Gravity.CENTER);
        mSwitchButton = new SwitchButton(context);
        int m = XTools.WindowUtil().dip2px(12);
        addView(mTextView);
        MarginLayoutParams params = (MarginLayoutParams) mTextView.getLayoutParams();
        params.setMargins(m, 0, m, 0);
        int w = XTools.WindowUtil().dip2px(40);
        addView(mSwitchButton, new ViewGroup.LayoutParams(w, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    public void setOnSwitchListener(OnSwitchListener listener) {
        mSwitchButton.setOnSwitchListener(open -> {
            if (listener != null) {
                listener.onSwitch(open);
            }
        });
    }

    public interface OnSwitchListener {
        void onSwitch(boolean open);
    }
}
