package com.lbx.library.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.view.View;

import com.lbx.library.R;
import com.lbx.library.base.BaseActivity;
import com.lbx.library.bean.Exhibits;
import com.lbx.library.bean.Floor;
import com.lbx.library.databinding.ActivityFloorDetailedBinding;
import com.lbx.library.injector.ContextLifeCycle;
import com.lbx.library.injector.components.AppComponent;
import com.lbx.library.injector.components.DaggerActivityComponent;
import com.lbx.library.injector.modules.ActivityModule;
import com.lbx.library.service.VoiceService;
import com.lbx.library.ui.view.NavigationView;
import com.lbx.library.ui.view.SwitchLayout;
import com.lbx.library.ui.view.TopBar;

import javax.inject.Inject;

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

public class FloorDetailedActivity extends BaseActivity implements SwitchLayout.OnSwitchListener, NavigationView.OnExhibitsLocationClickListener {

    @BindView(R.id.tb_floor_detailed)
    TopBar mTopBar;
    @BindView(R.id.sl_auto_play)
    SwitchLayout mPlayLayout;

    private Floor mFloor;
    private ActivityFloorDetailedBinding mBinding;
    private boolean mAutoPlay;
    @BindView(R.id.nv_main)
    NavigationView mNavigationView;
    @Inject
    @ContextLifeCycle
    Context mContext;
    private Exhibits[] mExhibitsArray;

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
        super.initData();
        mBinding.setFloor(mFloor);
        mExhibitsArray = mFloor.getExhibitsArray();
        mNavigationView.setExhibits(mExhibitsArray);
    }

    @Override
    public void initListener() {
        super.initListener();
        mTopBar.setLesserTitleClickListener((v) -> {
            xLogUtil.e(this, "展品列表");
            ExhibitsListActivity.getIntent(mContext, mFloor).start();
        });
        mPlayLayout.setOnSwitchListener(this);
        mNavigationView.setOnExhibitsLocationClickListener(this);
    }

    @Override
    public void onSwitch(boolean open) {
        xLogUtil.e(this, "自动播放:" + open);
        mAutoPlay = open;
    }

    /**
     * 点击坐标
     *
     * @param floor    floor
     * @param exhibits exhibits
     * @param pos      pos
     */
    @Override
    public void onClick(Floor floor, Exhibits exhibits, int pos) {
        xLogUtil.e("点击了location");
        VideoActivity.start(this, exhibits, null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (VoiceService.PLAYING_EXHIBITS != null) {
            for (Exhibits e : mExhibitsArray) {
                e.setPlaying(VoiceService.PLAYING_EXHIBITS.getId().equals(e.getId()));
            }
        }
        mNavigationView.invalidate();
    }
}
