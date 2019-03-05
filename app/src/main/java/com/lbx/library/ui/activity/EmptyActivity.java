package com.lbx.library.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.view.View;

import com.lbx.library.R;
import com.lbx.library.base.BaseActivity;
import com.lbx.library.databinding.ActivityEmptyBinding;
import com.lbx.library.injector.components.AppComponent;
import com.lbx.library.ui.view.TopBar;

import butterknife.BindView;
import lbx.xtoollib.XIntent;

/**
 * @author lbx
 */
public class EmptyActivity extends BaseActivity {

    private ActivityEmptyBinding mBinding;
    @BindView(R.id.tb_empty)
    TopBar mTopBar;
    private String mTitle;

    public static XIntent getIntent(Context context, String title) {
        XIntent intent = new XIntent(context, EmptyActivity.class);
        intent.putExtra("title", title);
        return intent;
    }

    @Override
    public void bindComponent(AppComponent appComponent) {

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_empty;
    }

    @Override
    public void initIntent(Intent intent) {
        super.initIntent(intent);
        mTitle = intent.hasExtra("title") ? intent.getStringExtra("title") : "测试";
    }

    @Override
    public void getDataBinding(ViewDataBinding binding) {
        mBinding = (ActivityEmptyBinding) binding;
    }

    @Override
    public void initView(View view) {
        mTopBar.bind(this, mTitle);
    }

    @Override
    public void initData() {
        mBinding.setTitle(mTitle);
    }
}
