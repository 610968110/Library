package com.lbx.library.ui.activity;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.view.View;

import com.lbx.library.R;
import com.lbx.library.base.BaseActivity;
import com.lbx.library.databinding.ActivityLibraryIntroductionBinding;
import com.lbx.library.injector.ContextLifeCycle;
import com.lbx.library.injector.components.AppComponent;
import com.lbx.library.injector.components.DaggerActivityComponent;
import com.lbx.library.injector.modules.ActivityModule;
import com.lbx.library.ui.view.TopBar;

import butterknife.BindView;
import lbx.xtoollib.XIntent;
import lbx.xtoollib.XTools;

/**
 * @author lbx
 *         简介
 */
public class IntroductionSummaryActivity extends BaseActivity {

    @ContextLifeCycle
    Context mContext;
    @BindView(R.id.tb_introduction)
    TopBar mTopBar;
    private ActivityLibraryIntroductionBinding mBinding;

    public static XIntent getIntent(Context context) {
        return new XIntent(context, IntroductionSummaryActivity.class);
    }

    @Override
    public void bindComponent(AppComponent appComponent) {
        mActivityComponent = DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .activityModule(new ActivityModule(this))
                .build();
        mActivityComponent.inject(this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_library_introduction;
    }

    @Override
    public void getDataBinding(ViewDataBinding binding) {
        mBinding = (ActivityLibraryIntroductionBinding) binding;
    }

    @Override
    public void initView(View view) {
        mTopBar.bind(this, XTools.ResUtil().getString(R.string.introductionToLibrary));
        mBinding.setIntroduction(XTools.ResUtil().getString(R.string.introductionToLibrary_content));
    }
}
