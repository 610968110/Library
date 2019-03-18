package com.lbx.library.ui.activity;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.view.View;

import com.lbx.library.R;
import com.lbx.library.base.BaseActivity;
import com.lbx.library.injector.components.AppComponent;
import com.lbx.library.ui.view.AnswerGroup;
import com.lbx.library.ui.view.AnswerItemView;
import com.lbx.library.ui.view.TopBar;

import butterknife.BindView;
import lbx.xtoollib.XIntent;
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
 *         互动答题
 * @date 2019/3/18
 */
public class InteractiveAnswerActivity extends BaseActivity implements AnswerGroup.OnCommitClickListener {


    @BindView(R.id.tb_answer)
    TopBar mTopBar;
    @BindView(R.id.ll_answer)
    AnswerGroup mAnswerLayout;

    public static XIntent getIntent(Context context) {
        return new XIntent(context, InteractiveAnswerActivity.class);
    }

    @Override
    public void bindComponent(AppComponent appComponent) {

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_interactive_answer;
    }

    @Override
    public void getDataBinding(ViewDataBinding binding) {

    }

    @Override
    public void initView(View view) {
        mTopBar.bind(this, XTools.ResUtil().getString(R.string.interactiveAnswer));
        AnswerItemView v = new AnswerItemView(this);
        v.setOptions("0",
                1,
                "0",
                "0",
                "0",
                "0");
        AnswerItemView v1 = new AnswerItemView(this);
        v1.setOptions("",
                2,
                "0",
                "0",
                "0",
                "0");
        mAnswerLayout.setQuestionViews(v, v1);
    }

    @Override
    public void initListener() {
        super.initListener();
        mAnswerLayout.setOnCommitClickListener(this);
    }

    @Override
    public void commit(View view, boolean all, boolean[] right) {
        if (!all) {
            XTools.UiUtil().showToast("您有问题没有作答，请您答完再提交");
        } else {

        }
    }
}
