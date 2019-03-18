package com.lbx.library.ui.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import lbx.xtoollib.phone.xLogUtil;

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
 * @date 2019/3/18.
 */

public class AnswerItemView extends QuestionItemView {
    private int rightPos;

    public AnswerItemView(@NonNull Context context) {
        this(context, null);
    }

    public AnswerItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnswerItemView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Deprecated
    @Override
    public void setOptions(@NonNull String question, @NonNull String... options) {
        super.setOptions(question, options);
    }

    public void setOptions(@NonNull String question, int rightPos, @NonNull String... options) {
        this.rightPos = rightPos;
        super.setOptions(question, options);
    }

    public int getRightPos() {
        return rightPos;
    }

    public void showWrongItem() {
        int select = getSelect();
        int rightPos = getRightPos();
        if (rightPos != select) {
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                xLogUtil.e("childAt:" + childAt);
            }
        }
    }
}
