package com.lbx.library.ui.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
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
 * @date 2019/3/5.
 */

public class QuestionItemView extends LinearLayout {

    private TextView mQuestionView;
    private OptionTextView[] mOptionsView;
    private Context mContext;
    private static final int OPTIONS_ITEM_HEIGHT = XTools.WindowUtil().dip2px(40);
    private int mSelectPos = -1;

    public QuestionItemView(@NonNull Context context) {
        this(context, null);
    }

    public QuestionItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QuestionItemView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        setOrientation(VERTICAL);
        int p = XTools.WindowUtil().dip2px(20);
        setPadding(p, p, p, p);
    }

    public void setOptions(@NonNull String question, @NonNull String... options) {
        removeAllViews();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_qusetion, this, true);
        mQuestionView = view.findViewById(R.id.tv_question);
        mQuestionView.setText(question);
        mOptionsView = new OptionTextView[options.length];
        for (int i = 0; i < mOptionsView.length; i++) {
            OptionTextView qv = getOptionsView(options[i]);
            mOptionsView[i] = qv;
            int finalI = i;
            qv.setOnClickListener(v -> selectOptions(finalI));
            addView(qv, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, OPTIONS_ITEM_HEIGHT));
        }
    }

    @NonNull
    private OptionTextView getOptionsView(String option) {
        OptionTextView optionTextView = new OptionTextView(mContext);
        optionTextView.setOption(option);
        return optionTextView;
    }

    private void selectOptions(int pos) {
        mSelectPos = pos;
        for (int i = 0; i < mOptionsView.length; i++) {
            mOptionsView[i].setSelect(i == pos);
        }
    }

    public boolean isAnswer() {
        return mSelectPos != -1;
    }

    public int getSelect() {
        return mSelectPos;
    }
}
