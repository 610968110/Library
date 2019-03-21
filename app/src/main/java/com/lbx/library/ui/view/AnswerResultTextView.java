package com.lbx.library.ui.view;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
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
 * @date 2019/3/20
 */
public class AnswerResultTextView extends LinearLayout {

    private TextView mTextView;
    private String textStart, textEnd;

    public AnswerResultTextView(@NonNull Context context) {
        this(context, null);
    }

    public AnswerResultTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnswerResultTextView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        ImageView imageView = new ImageView(context);
        imageView.setBackgroundResource(R.drawable.answer_point_bg);
        int dip2px = XTools.WindowUtil().dip2px(2);
        addView(imageView, new ViewGroup.LayoutParams(dip2px, dip2px));
        mTextView = new TextView(context);
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        mTextView.setTextColor(Color.BLACK);
        mTextView.setPadding(20, 0, 20, 0);
        mTextView.setGravity(Gravity.CENTER_VERTICAL);
        addView(mTextView, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, 1000));
    }

    public void setOtherText(String textStart, String textEnd) {
        this.textStart = textStart;
        this.textEnd = textEnd;
    }

    public void setText(String s) {
        mTextView.setText(textStart + s + textEnd);
    }

    @BindingAdapter({"textStart", "textEnd"})
    public static void attrs(AnswerResultTextView view, String textStart, String textEnd) {
        view.setOtherText(textStart, textEnd);
    }
}