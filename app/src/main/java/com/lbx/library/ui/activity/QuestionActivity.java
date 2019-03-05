package com.lbx.library.ui.activity;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.view.View;

import com.lbx.library.R;
import com.lbx.library.base.BaseActivity;
import com.lbx.library.injector.ContextLifeCycle;
import com.lbx.library.injector.components.AppComponent;
import com.lbx.library.ui.view.QuestionGroup;
import com.lbx.library.ui.view.QuestionItemView;
import com.lbx.library.ui.view.TopBar;

import butterknife.BindView;
import lbx.xtoollib.XIntent;
import lbx.xtoollib.XTools;

/**
 * @author lbx
 *         问卷
 */
public class QuestionActivity extends BaseActivity implements QuestionGroup.OnSureClickListener {

    @ContextLifeCycle
    Context mContext;
    @BindView(R.id.tb_question)
    TopBar mTopBar;
    @BindView(R.id.ll_question)
    QuestionGroup mQuestionGroup;

    public static XIntent getIntent(Context context) {
        return new XIntent(context, QuestionActivity.class);
    }

    @Override
    public void bindComponent(AppComponent appComponent) {

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_question;
    }

    @Override
    public void getDataBinding(ViewDataBinding binding) {

    }

    @Override
    public void initView(View view) {
        mTopBar.bind(this, XTools.ResUtil().getString(R.string.questionnaireInvestigation));
        QuestionItemView v = new QuestionItemView(this);
        v.setOptions("1. 您在使用山西博物馆导览机时是否流畅？",
                "流畅，可以满足参观时的需要",
                "偶尔会有卡顿情况出现，但不影响使用",
                "经常出现卡顿、闪退的情况，影响到参观使用",
                "出现过导览机无法打开的情况");
        QuestionItemView v1 = new QuestionItemView(this);
        v1.setOptions("2. 您是否能快速找到需要的功能？",
                "能",
                "一般，需要摸索",
                "不能，步骤繁琐",
                "找不到");
        QuestionItemView v2 = new QuestionItemView(this);
        v2.setOptions("3. 您在参观时的导览定位效果体验如何？",
                "定位快速、准确",
                "偶尔出现没有反应、定位错误的情况，但会自动纠正",
                "不能，步骤繁琐",
                "找不到");
        mQuestionGroup.addView(v, v1, v2);
    }

    @Override
    public void initListener() {
        super.initListener();
        mQuestionGroup.setOnSureClickListener(this);
    }

    /**
     * 提交按钮点击事件
     *
     * @param view view
     * @param all  是否全部答完
     */
    @Override
    public void click(View view, boolean all) {
        if (all) {
            XTools.UiUtil().showToast("您的问卷已提交，感谢您的参与");
            finish();
        } else {
            XTools.UiUtil().showToast("您有问题没有作答，请您答完再提交");
        }
    }
}
