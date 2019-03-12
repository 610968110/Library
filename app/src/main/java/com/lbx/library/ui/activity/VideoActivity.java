package com.lbx.library.ui.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.databinding.ViewDataBinding;
import android.media.AudioManager;
import android.os.IBinder;
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
import com.lbx.library.service.VoiceService;
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
public class VideoActivity extends BaseActivity implements MyVideoView.OnPlayListener, VoiceService.OnVoicePlayListener {

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
    private VoiceService mVoiceService;

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
        mTitleView.setText(mExhibits.getName());
        mLocationView.setText(mExhibits.getLocation());
        mContentView.setText(mExhibits.getContent());
        VoiceService.bindService(this).bindService(mConnection);
    }

    @Override
    public void initListener() {
        super.initListener();
        mVideoView.setOnPlayListener(this);
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mVoiceService = ((VoiceService.MyBinder) service).getService();
            mVoiceService.setOnVoicePlayListener(VideoActivity.this);
            mVideoView.setImage(mExhibits.getBigImage());
            String video = mExhibits.getVideo();
            if (!TextUtils.isEmpty(video)) {
                //视频
                mVideoView.setVideoPath(video);
            }
            //如果正在播放
            if (VoiceService.PLAYING_EXHIBITS != null) {
                //正在播放不是同一个文件
                if (!VoiceService.PLAYING_EXHIBITS.getId().equals(mExhibits.getId())) {
                    mVoiceService.pause();
                    mVoiceService.playVoice(mExhibits);
                } else {
                    //同一个文件
                    mVideoView.seekTo(mVoiceService.getCurrentPosition());
                }
            } else {
                mVoiceService.playVoice(mExhibits);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mVoiceService = null;
        }
    };

    @Override
    protected void onDestroy() {
        mVideoView.recycle();
        super.onDestroy();
    }

    @Override
    public void onVideoSeekTo(int progress) {
        if (mVoiceService != null) {
            if (mVoiceService.isPlaying()) {
                mVoiceService.pause();
            }
            mVoiceService.seekTo(progress);
            mVoiceService.play();
        }
    }

    @Override
    public void onVideoPrepared() {
        xLogUtil.e("onVideoPrepared:" + mVideoView.isPrepared() + "  " + mVoiceService.isPrepared());
        if (mVoiceService != null && mVoiceService.isPrepared()) {
            playVideoWithVoice();
        }
    }

    @Override
    public void onVideoPlay() {
        if (mVoiceService != null) {
            mVoiceService.play();
        }
    }

    @Override
    public void onVideoPause() {
        if (mVoiceService != null) {
            mVoiceService.pause();
        }
    }

    @Override
    public void onVideoCompletion() {
        if (mVoiceService != null) {
            mVoiceService.pause();
        }
    }

    @Override
    public void onVideoRePlay() {
        if (mVoiceService != null) {
            mVoiceService.seekTo(0);
        }
    }

    @Override
    public void onVoicePrepared(boolean noVoice) {
        xLogUtil.e("onVoicePrepared:" + mVideoView.isPrepared() + "  " + mVoiceService.isPrepared());
        if (mVoiceService != null) {
            mVoiceService.seekTo(0);
        }
        if (mVideoView.isPrepared()) {
            playVideoWithVoice();
        }
    }

    private void playVideoWithVoice() {
        if (mVoiceService != null) {
            mVoiceService.play();
        }
        mVideoView.startWhenPrepared();
    }
}
