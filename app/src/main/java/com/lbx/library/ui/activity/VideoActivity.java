package com.lbx.library.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.media.AudioManager;
import android.net.Uri;
import android.support.v4.util.Pair;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.lbx.library.R;
import com.lbx.library.base.BaseActivity;
import com.lbx.library.bean.Exhibits;
import com.lbx.library.injector.ContextLifeCycle;
import com.lbx.library.injector.components.AppComponent;
import com.lbx.library.injector.components.DaggerActivityComponent;
import com.lbx.library.injector.modules.ActivityModule;
import com.lbx.library.ui.view.MyVideoView;
import com.lbx.library.ui.view.TopBar;

import javax.inject.Inject;

import butterknife.BindView;
import lbx.xtoollib.XIntent;
import lbx.xtoollib.XTools;
import lbx.xtoollib.phone.xLogUtil;

/**
 * @author lbx
 */
public class VideoActivity extends BaseActivity {

    @BindView(R.id.tb_video)
    TopBar mTopBar;
    @BindView(R.id.vv_main)
    MyVideoView mVideoView;
    @BindView(R.id.tv_title)
    TextView mTitleView;
    @BindView(R.id.tv_content)
    TextView mContentView;
    @BindView(R.id.tv_location)
    TextView mLocationView;
    @Inject
    @ContextLifeCycle
    Context mContext;
    private Exhibits mExhibits;

    public static void start(Activity activity, Exhibits exhibits, View view) {
        String name = "img";
        if (view == null) {
            view = new View(activity);
            view.setTransitionName(name);
        }
        XIntent intent = new XIntent(activity, VideoActivity.class);
        intent.putExtra("exhibits", exhibits);
        XTools.ActivityUtil().startActivityWithTransition(activity, intent, new Pair<>(view, name));
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
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        return R.layout.activity_video_activity;
    }

    @Override
    public void initIntent(Intent intent) {
        super.initIntent(intent);
        mExhibits = intent.getParcelableExtra("exhibits");
    }

    @Override
    public void getDataBinding(ViewDataBinding binding) {

    }

    @Override
    public void initView(View view) {
        mTopBar.bind(this);
    }

    @Override
    public void initData() {
        super.initData();
        int playFile = mExhibits.getVoice();
        String videoUrl = mExhibits.getVideoUrl();
        if (playFile != -1) {
            //音频
            String uri = "android.resource://" + getPackageName() + "/" + playFile;
            xLogUtil.e("音频");
            mVideoView.setVideoUri(Uri.parse(uri), mExhibits.getImg());
        } else if (!TextUtils.isEmpty(videoUrl)) {
            //视频
            xLogUtil.e("视频");
            mVideoView.setVideoPath(videoUrl);
        }
        mTitleView.setText(mExhibits.getName());
        mLocationView.setText(mExhibits.getLocation());
        mContentView.setText(mExhibits.getContent());
    }

    @Override
    protected void onDestroy() {
        mVideoView.recycle();
        super.onDestroy();
    }
}
