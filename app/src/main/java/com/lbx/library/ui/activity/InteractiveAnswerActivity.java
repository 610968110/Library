package com.lbx.library.ui.activity;

import android.animation.ValueAnimator;
import android.content.Context;
import android.databinding.ViewDataBinding;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.lbx.library.R;
import com.lbx.library.base.BaseActivity;
import com.lbx.library.injector.components.AppComponent;
import com.lbx.library.ui.view.AnswerGroup;
import com.lbx.library.ui.view.AnswerItemView;
import com.lbx.library.ui.view.AnswerResultView;
import com.lbx.library.ui.view.TopBar;

import java.util.List;

import butterknife.BindView;
import lbx.xtoollib.XIntent;
import lbx.xtoollib.XTools;
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
 *         互动答题
 * @date 2019/3/18
 */
public class InteractiveAnswerActivity extends BaseActivity implements AnswerGroup.OnCommitClickListener {


    @BindView(R.id.tb_answer)
    TopBar mTopBar;
    @BindView(R.id.ll_answer)
    AnswerGroup mAnswerLayout;
    @BindView(R.id.sv_answer)
    ScrollView mScrollView;
    @BindView(R.id.arv_main)
    AnswerResultView mAnswerResultView;
    private static final int H = XTools.WindowUtil().dip2px(420);

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
        v.setOptions("1、青铜罍是商代晚期至春秋中期古人祭祀活动中重要的礼器之一，其主要是作为____来使用的。",
                2,
                "A 盛酒或盛水器",
                "B 温酒器",
                "C 盛食器",
                "D 烹煮器");
        AnswerItemView v1 = new AnswerItemView(this);
        v1.setOptions("2、三星堆出土的玉璋数量众多，可以大致被分为三类：一类为边璋，一类为牙璋，还有一类为鱼形璋。其中____是蜀地特有的器型，目前仅见于三星堆遗址和金沙遗址。",
                3,
                "A 边璋",
                "B 牙璋",
                "C 鱼形璋",
                "D 三种均不是");
        mAnswerLayout.setQuestionViews(v, v1);
    }

    @Override
    public void initListener() {
        super.initListener();
        mAnswerLayout.setOnCommitClickListener(this);
    }

    @Override
    public void commit(View view, boolean all, List<Boolean> right) {
        if (!all) {
            XTools.UiUtil().showToast("您有问题没有作答，请您答完再提交");
        } else {
            if (mAnswerResultView.getMeasuredHeight() != 0) {
                XTools.UiUtil().showToast("您已答题完毕");
                return;
            }
            xLogUtil.e("完成答题");
            //不可再答题
            mAnswerLayout.setSelectable(false);
            mScrollView.smoothScrollTo(0, 0);
            int rightCount = 0;
            for (boolean b : right) {
                if (b) {
                    rightCount++;
                }
            }
            int total = mAnswerLayout.getChildCount() - 1;
            mAnswerResultView.setResult(total + "",
                    rightCount + "",
                    total - rightCount + "");
            ValueAnimator animator = ValueAnimator.ofInt(0, H);
            animator.setDuration(500);
            animator.addUpdateListener(animation -> {
                ViewGroup.LayoutParams params = mAnswerResultView.getLayoutParams();
                params.height = (int) animation.getAnimatedValue();
                mAnswerResultView.setLayoutParams(params);
            });
            animator.start();
        }
    }
}
