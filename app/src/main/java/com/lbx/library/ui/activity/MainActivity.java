package com.lbx.library.ui.activity;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.lbx.library.R;
import com.lbx.library.base.BaseActivity;
import com.lbx.library.injector.components.AppComponent;
import com.lbx.library.injector.components.DaggerActivityComponent;
import com.lbx.library.injector.modules.ActivityModule;

import butterknife.BindView;
import lbx.xtoollib.XIntent;
import lbx.xtoollib.XTools;
import lbx.xtoollib.phone.xLogUtil;

/**
 * @author lbx
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.nv_main)
    NavigationView mNavigationView;
    @BindView(R.id.dl_main)
    DrawerLayout mDrawerLayout;

    public static XIntent getIntent(Context context) {
        return new XIntent(context, MainActivity.class);
    }

    @Override
    public void bindComponent(AppComponent appComponent) {
        mActivityComponent = DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .activityModule(new ActivityModule(this))
                .build();
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public void getDataBinding(ViewDataBinding binding) {

    }

    @Override
    public void initView(View view) {
        ViewGroup.LayoutParams params = mNavigationView.getLayoutParams();
        params.width = XTools.WindowUtil().getScreenWidth() -
                XTools.ResUtil().getDimen(R.dimen.menu_offset);
        mNavigationView.setLayoutParams(params);
    }

    @Override
    public void initData() {

    }

    public void introductionToLibrary(MenuItem item) {
        xLogUtil.e(this, "图书馆简介");
    }

    public void myFriends(MenuItem item) {
        xLogUtil.e(this, "我的伙伴");
    }

    public void guideLanguage(MenuItem item) {
        xLogUtil.e(this, "导览语言");
        LoginActivity.getIntent(this).start();
        finish();
    }

    public void questionnaireInvestigation(MenuItem item) {
        xLogUtil.e(this, "问卷调查");
    }

    public void interactiveAnswer(MenuItem item) {
        xLogUtil.e(this, "互动答题");
    }

    public void deviceInformation(MenuItem item) {
        xLogUtil.e(this, "设备信息");
    }
}
