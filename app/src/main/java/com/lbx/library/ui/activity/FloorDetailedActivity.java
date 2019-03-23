package com.lbx.library.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.view.View;

import com.lbx.library.Config;
import com.lbx.library.R;
import com.lbx.library.base.BaseActivity;
import com.lbx.library.bean.Exhibits;
import com.lbx.library.bean.Floor;
import com.lbx.library.bean.event.PlayingVoiceBean;
import com.lbx.library.databinding.ActivityFloorDetailedBinding;
import com.lbx.library.injector.ContextLifeCycle;
import com.lbx.library.injector.components.AppComponent;
import com.lbx.library.injector.components.DaggerActivityComponent;
import com.lbx.library.injector.modules.ActivityModule;
import com.lbx.library.service.VoiceService;
import com.lbx.library.ui.view.MyToastView;
import com.lbx.library.ui.view.NavigationView;
import com.lbx.library.ui.view.SwitchLayout;
import com.lbx.library.ui.view.TopBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

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
    @BindView(R.id.mtv_voice)
    MyToastView mVoiceView;
    @Inject
    @ContextLifeCycle
    Context mContext;
    private VoiceService mVoiceService;
    private Exhibits[] mExhibitsArray;
    private Exhibits mPlayingExhibits;

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
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        mTopBar.bind(this, mFloor.getName());
        mTopBar.setLesserTitle(R.string.exhibits_list);
        xLogUtil.e("mAutoPlay:" + mAutoPlay);
        mPlayLayout.setAutoPlay(mAutoPlay = Config.isAutoPlay());
    }

    @Override
    public void initData() {
        super.initData();
        mBinding.setFloor(mFloor);
        mExhibitsArray = mFloor.getExhibitsArray();
        mNavigationView.setExhibits(mExhibitsArray);
        mPlayingExhibits = VoiceService.PLAYING_EXHIBITS;
        if (mPlayingExhibits != null) {
            playLogo(mPlayingExhibits);
        }
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
        XTools.SpUtil().putBoolean(Config.AUTO_PLAY_TAG, open);
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

    private void playLogo(Exhibits exhibits) {
        for (Exhibits e : mExhibitsArray) {
            e.setPlaying(exhibits != null && exhibits.getId().equals(e.getId()));
        }
        mNavigationView.refreshImageState();
        playToast(exhibits);
    }

    private void playToast(Exhibits exhibits) {
        if (exhibits != null) {
            mVoiceView.setText1("正在讲解：" + exhibits.getName());
            mVoiceView.setText2("关闭", v -> {
                if (mVoiceService != null) {
                    mVoiceService.pause();
                }
            });
            mVoiceView.setVisibility(View.VISIBLE);
        } else {
            mVoiceView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onServiceConnected(VoiceService service) {
        super.onServiceConnected(service);
        mVoiceService = service;
    }

    @Override
    protected void onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }

    @Subscribe
    public void playLogo(PlayingVoiceBean bean) {
        playLogo(bean.getExhibits());
    }
}
