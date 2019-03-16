package com.lbx.library.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lbx.library.R;

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
 * @date 2019/3/16.
 */

public class MyToastView extends LinearLayout {

    private TextView textView;
    private TextView textView2;
    public static final int P = 50;

    public MyToastView(@NonNull Context context) {
        this(context, null);
    }

    public MyToastView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyToastView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        setPadding(P, 0, P, 0);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyToastView);
        int color = a.getColor(R.styleable.MyToastView_MyToastView_textColor, Color.BLACK);
        String string1 = a.getString(R.styleable.MyToastView_MyToastView_text1);
        String string2 = a.getString(R.styleable.MyToastView_MyToastView_text2);
        a.recycle();
        textView = makeTextView(context, color, string1);
        textView2 = makeTextView(context, color, string2);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        params.weight = 1;
        addView(textView, params);
        View view = new View(context);
        view.setBackgroundColor(Color.parseColor("#CDA680"));
        addView(view, new ViewGroup.LayoutParams(2, ViewGroup.LayoutParams.MATCH_PARENT));
        textView2.setPadding(P, 0, 0, 0);
        addView(textView2, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    private TextView makeTextView(@NonNull Context context, int color, String string1) {
        TextView textView = new TextView(context);
        textView.setText(string1);
        textView.setTextColor(color);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        return textView;
    }

    public void setText1(String text1) {
        textView.setText(text1);
    }

    public void setText2(String text2, OnClickListener listener) {
        textView2.setText(text2);
        textView2.setOnClickListener(listener);
    }
}
