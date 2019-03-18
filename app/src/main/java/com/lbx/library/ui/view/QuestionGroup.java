package com.lbx.library.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

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
 * @date 2019/3/5.
 */

public class QuestionGroup extends LinearLayout {

    private Button mSureButton;

    public QuestionGroup(@NonNull Context context) {
        this(context, null);
    }

    public QuestionGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QuestionGroup(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
        mSureButton = new Button(context);
        mSureButton.setText("提  交");
        mSureButton.setTextColor(Color.parseColor("#9F0303"));
        mSureButton.setBackgroundResource(R.drawable.btn_commit);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(XTools.WindowUtil().dip2px(168),
                XTools.WindowUtil().dip2px(43));
        params.gravity = Gravity.CENTER_HORIZONTAL;
        params.topMargin = XTools.WindowUtil().dip2px(45);
        params.bottomMargin = XTools.WindowUtil().dip2px(95);
        addView(mSureButton, 0, params);
        mSureButton.setOnClickListener(new OnCommitClick());
    }

    public void setQuestionViews(View... children) {
        for (View v : children) {
            int childCount = getChildCount();
            int index = childCount - 1;
            super.addView(v, Math.max(0, index));
        }
    }

    private OnSureClickListener mOnSureClickListener;

    public interface OnSureClickListener {
        void click(View view, boolean all);
    }

    public void setOnSureClickListener(OnSureClickListener onSureClickListener) {
        this.mOnSureClickListener = onSureClickListener;
    }

    private class OnCommitClick implements OnClickListener {
        @Override
        public void onClick(View v) {
            if (mOnSureClickListener != null) {
                boolean all = true;
                for (int i = 0; i < getChildCount(); i++) {
                    View child = getChildAt(i);
                    if (child instanceof QuestionItemView) {
                        QuestionItemView itemView = (QuestionItemView) child;
                        if (!itemView.isAnswer()) {
                            all = false;
                            break;
                        }
                    }
                }
                mOnSureClickListener.click(mSureButton, all);
            }
        }
    }
}
