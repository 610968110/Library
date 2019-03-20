package com.lbx.library.ui.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

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

public class AnswerGroup extends QuestionGroup implements QuestionGroup.OnSureClickListener {

    public AnswerGroup(@NonNull Context context) {
        this(context, null);
    }

    public AnswerGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnswerGroup(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnSureClickListener(this);
    }

    @Override
    public void click(View view, boolean all) {
        int childCount = getChildCount();
        List<Boolean> b = new ArrayList<>();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof AnswerItemView) {
                AnswerItemView answerItemView = (AnswerItemView) childAt;
                answerItemView.answerFinish();
                b.add(answerItemView.isAnswerRight());
            }
        }
        if (mOnCommitClickListener != null) {
            mOnCommitClickListener.commit(view, all, b);
        }
    }

    private OnCommitClickListener mOnCommitClickListener;

    public interface OnCommitClickListener {
        void commit(View view, boolean all, List<Boolean> right);
    }

    public void setOnCommitClickListener(OnCommitClickListener onCommitClickListener) {
        this.mOnCommitClickListener = onCommitClickListener;
    }
}
