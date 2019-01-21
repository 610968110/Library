package com.lbx.library.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.view.View;

import com.lbx.library.R;
import com.lbx.library.base.BaseActivity;
import com.lbx.library.bean.Floor;
import com.lbx.library.databinding.ActivityFloorDetailedBinding;
import com.lbx.library.injector.components.AppComponent;
import com.lbx.library.injector.components.DaggerActivityComponent;
import com.lbx.library.injector.modules.ActivityModule;
import com.lbx.library.ui.view.SwitchLayout;
import com.lbx.library.ui.view.TopBar;

import butterknife.BindView;
import lbx.xtoollib.XIntent;
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
 * @date 2019/1/21.
 */

public class FloorDetailedActivity extends BaseActivity implements SwitchLayout.OnSwitchListener {

    @BindView(R.id.tb_floor_detailed)
    TopBar mTopBar;
    @BindView(R.id.sl_auto_play)
    SwitchLayout mPlayLayout;

    private Floor mFloor;
    private ActivityFloorDetailedBinding mBinding;

    public static XIntent getIntent(Context context, Floor floor) {
        XIntent intent = new XIntent(context, FloorDetailedActivity.class);
        intent.putExtra("floor", floor);
        return intent;
    }

    @Override
    public void bindComponent(AppComponent appComponent) {
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .appComponent(appComponent)
                .build();
        mActivityComponent.inject(this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_floor_detailed;
    }

    @Override
    public void initIntent(Intent intent) {
        super.initIntent(intent);
        mFloor = intent.getParcelableExtra("floor");
    }

    @Override
    public void getDataBinding(ViewDataBinding binding) {
        mBinding = (ActivityFloorDetailedBinding) binding;
    }

    @Override
    public void initView(View view) {
        mTopBar.bind(this, mFloor.getName());
        mTopBar.setLesserTitle(R.string.exhibits_list);
    }

    @Override
    public void initData() {
        mBinding.setFloor(mFloor);
    }

    @Override
    public void initListener() {
        super.initListener();
        mTopBar.setLesserTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xLogUtil.e(this, "展品列表");
            }
        });
        mPlayLayout.setOnSwitchListener(this);
    }

    @Override
    public void onSwitch(boolean open) {
        xLogUtil.e(this, "自动播放:" + open);
    }
}
