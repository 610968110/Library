package com.lbx.library.ui.view;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.lbx.library.R;

import java.util.Formatter;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import lbx.xtoollib.XTools;

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
 * @date 2019/1/28.
 */

public class MyVideoView extends FrameLayout implements MediaPlayer.OnPreparedListener,
        View.OnClickListener, MediaPlayer.OnCompletionListener, SeekBar.OnSeekBarChangeListener {

    private VideoView mVideoView;
    private ImageView mPlayView;
    private SeekBar mSeekBar;
    private Disposable mSubscribe;
    private TextView mCurrentPositionView, mMaxDurationView;
    private boolean isCompletion;
    private boolean userTouch;

    public MyVideoView(@NonNull Context context) {
        this(context, null);
    }

    public MyVideoView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyVideoView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = XTools.UiUtil().inflate(R.layout.view_video);
        addView(view);
        mVideoView = view.findViewById(R.id.vv_main);
        mPlayView = view.findViewById(R.id.iv_play);
        mSeekBar = view.findViewById(R.id.sb_video);
        mCurrentPositionView = view.findViewById(R.id.tv_currentPosition);
        mMaxDurationView = view.findViewById(R.id.tv_max_duration);
        mVideoView.setOnPreparedListener(this);
        mPlayView.setOnClickListener(this);
        mSeekBar.setOnSeekBarChangeListener(this);
        mVideoView.setOnCompletionListener(this);
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
        refreshPlayButton();
        mp.setOnSeekCompleteListener(MediaPlayer::start);
        if (mSubscribe != null) {
            mSubscribe.dispose();
            mSubscribe = null;
        }
        mSubscribe = Observable.interval(0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(aLong -> {
                    int duration = mVideoView.getDuration();
                    mSeekBar.setMax(duration);
                    mMaxDurationView.setText(stringForTime(duration));
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    if (mVideoView != null && !userTouch) {
                        refreshPlayButton();
                        int currentPosition;
                        if (!isCompletion) {
                            currentPosition = mVideoView.getCurrentPosition();
                        } else {
                            currentPosition = mSeekBar.getMax();
                        }
                        mSeekBar.setProgress(currentPosition);
                        mCurrentPositionView.setText(stringForTime(currentPosition));
                    }
                });
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mSubscribe != null) {
            mSubscribe.dispose();
            mSubscribe = null;
        }
    }

    private void refreshPlayButton() {
        mPlayView.setImageResource(isPlaying() ? R.drawable.pause : R.drawable.play);
    }

    public boolean isPlaying() {
        return mVideoView.isPlaying();
    }

    public boolean canPause() {
        return mVideoView.canPause();
    }

    public void setVideoPath(String path) {
        mVideoView.setVideoPath(path);
    }

    public void start() {
        mVideoView.start();
    }

    public void pause() {
        mVideoView.pause();
    }

    /**
     * 将长度转换为时间
     *
     * @param time 毫秒
     * @return 时间
     */
    private String stringForTime(int time) {
        StringBuilder mFormatBuilder = new StringBuilder();
        Formatter mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());
        int tSecond = (int) Math.ceil(time / 1000F);
        int seconds = tSecond % 60;
        int minutes = (tSecond / 60) % 60;
        int hours = tSecond / 3600;
        mFormatBuilder.setLength(0);
        if (hours > 0) {
            return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString();
        } else {
            return mFormatter.format("%02d:%02d", minutes, seconds).toString();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_play:
                if (isPlaying()) {
                    pause();
                } else {
                    if (isCompletion) {
                        mVideoView.seekTo(0);
                        isCompletion = false;
                    }
                    start();
                }
                refreshPlayButton();
                break;
            default:
                break;
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        isCompletion = true;
        mSeekBar.setProgress(mSeekBar.getMax());
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            if (isPlaying() && canPause()) {
                pause();
            }
            mVideoView.seekTo(seekBar.getProgress());
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        isCompletion = false;
        userTouch = true;
        refreshPlayButton();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        refreshPlayButton();
        userTouch = false;
    }
}
