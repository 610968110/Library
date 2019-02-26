package com.lbx.library.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.lbx.library.R;
import com.lbx.library.base.BaseActivity;
import com.lbx.library.injector.components.AppComponent;
import com.lbx.library.injector.components.DaggerActivityComponent;
import com.lbx.library.injector.modules.ActivityModule;
import com.lbx.library.ui.fragment.FloorFragment;
import com.lbx.library.ui.view.TopBar;

import butterknife.BindView;
import lbx.xtoollib.XTools;
import lbx.xtoollib.phone.xLogUtil;
import lbx.xview.views.circular_reveal.CircularRevealUtils;
import lbx.xview.views.circular_reveal.ICircularReveal;

/**
 * @author lbx
 */
public class MainActivity extends BaseActivity implements ICircularReveal {

    @BindView(R.id.nv_main)
    NavigationView mNavigationView;
    @BindView(R.id.dl_main)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.tb_main)
    TopBar mTopBar;
    private FragmentManager mFragmentManager;

    public static Intent getIntent(Context context, int x, int y) {
        return CircularRevealUtils.ActivityCircularReveal()
                .makeCircularRevealIntent(context, MainActivity.class, x, y);
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
        return R.layout.activity_main;
    }

    @Override
    public void getDataBinding(ViewDataBinding binding) {

    }

    @Override
    public void initView(View view) {
        CircularRevealUtils.ActivityCircularReveal().setCircularRevealAnim(this, false);
        ViewGroup.LayoutParams params = mNavigationView.getLayoutParams();
        params.width = XTools.WindowUtil().getScreenWidth() -
                XTools.ResUtil().getDimen(R.dimen.menu_offset);
        mNavigationView.setLayoutParams(params);
        mTopBar.setLeftIconImg(R.drawable.menu, v -> mDrawerLayout.openDrawer(Gravity.START));
        mTopBar.setTitle(R.string.guide);
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction()
                .replace(R.id.fl_main, FloorFragment.newInstance())
                .commitAllowingStateLoss();
    }

    @Override
    public void initData() {

    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public View getRootView() {
        return mDrawerLayout;
    }

    public void introductionToLibrary(MenuItem item) {
        xLogUtil.e(this, "图书馆简介");
        mFragmentManager.beginTransaction()
                .replace(R.id.fl_main, FloorFragment.newInstance())
                .commitAllowingStateLoss();
    }

    public void myFriends(MenuItem item) {
        xLogUtil.e(this, "我的伙伴");
        FriendsActivity.getIntent(this).start();
    }

    public void guideLanguage(MenuItem item) {
        xLogUtil.e(this, "导览语言");
        LoginActivity.getIntent(this).start();
        finish();
    }

    public void questionnaireInvestigation(MenuItem item) {
        xLogUtil.e(this, "问卷调查");
        EmptyActivity.getIntent(this, XTools.ResUtil().getString(R.string.questionnaireInvestigation)).start();
    }

    public void interactiveAnswer(MenuItem item) {
        xLogUtil.e(this, "互动答题");
        EmptyActivity.getIntent(this, XTools.ResUtil().getString(R.string.interactiveAnswer)).start();
    }

    public void deviceInformation(MenuItem item) {
        xLogUtil.e(this, "设备信息");
        EmptyActivity.getIntent(this, XTools.ResUtil().getString(R.string.deviceInformation)).start();
    }
}
