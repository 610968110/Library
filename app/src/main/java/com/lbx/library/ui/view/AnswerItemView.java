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
    private boolean isAnswerRight;

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
        super.setOptions(question, options);
        this.rightPos = rightPos;
    }

    public int getRightPos() {
        return rightPos;
    }

    public void answerFinish() {
        int rightPos = getRightPos();
        xLogUtil.e("rightPos:" + rightPos);
        //不算题目的View
        int childCount = getChildCount();
        for (int i = 1; i < childCount; i++) {
            View childAt = getChildAt(i);
            OptionTextView optionTextView = (OptionTextView) childAt;
            if (i == rightPos) {
                //正确
                if (!optionTextView.isSelect()) {
                    xLogUtil.e("显示正确答案:" + optionTextView.getText());
                    optionTextView.setSelect(true);
                } else {
                    isAnswerRight = true;
                }
            } else {
                //错误
                if (optionTextView.isSelect()) {
                    xLogUtil.e("回答错误,选择的是:" + optionTextView.getText());
                    optionTextView.setShowWrong(true);
                }
            }
        }
    }

    public boolean isAnswerRight() {
        return isAnswerRight;
    }
}
