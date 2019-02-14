package com.lbx.library.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
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

/**
 * @author lbx
 */
public class VideoActivity extends BaseActivity {

    @BindView(R.id.tb_video)
    TopBar mTopBar;
    @BindView(R.id.vv_main)
    MyVideoView mVideoView;
    @BindView(R.id.iv_video)
    ImageView mImageView;
    @BindView(R.id.tv_title)
    TextView mTitleView;
    @BindView(R.id.tv_content)
    TextView mContentView;
    @BindView(R.id.tv_location)
    TextView mLocationView;
    @Inject
    @ContextLifeCycle
    Context mContext;
    private boolean isVideo;
    private Exhibits mExhibits;

    public static XIntent getIntent(Context context, Exhibits exhibits) {
        XIntent xIntent = new XIntent(context, VideoActivity.class);
        xIntent.putExtra("exhibits", exhibits);
        return xIntent;
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
        return R.layout.activity_video_activity;
    }

    @Override
    public void initIntent(Intent intent) {
        super.initIntent(intent);
        mExhibits = intent.getParcelableExtra("exhibits");
        isVideo = !TextUtils.isEmpty(mExhibits.getVideoUrl());
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
        if (isVideo) {
            String path = mExhibits.getVideoUrl();
            mVideoView.setVisibility(View.VISIBLE);
//                String path = Environment.getExternalStorageDirectory().getPath() + File.separator + "video.mp4";
            if (!TextUtils.isEmpty(path)) {
                //视频
                mVideoView.setVideoPath(path);
            }
        } else {
            //图片
            mImageView.setVisibility(View.VISIBLE);
            mImageView.setBackgroundResource(mExhibits.getImg());
        }
        mTitleView.setText(mExhibits.getName());
        mLocationView.setText(mExhibits.getLocation());
        mContentView.setText(mExhibits.getContent());
    }
}
