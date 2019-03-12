package com.lbx.library.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.lbx.library.bean.Exhibits;

import java.io.IOException;

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
 * @date 2019/3/12.
 */

public class VoiceService extends Service implements MediaPlayer.OnPreparedListener {

    private MediaPlayer mMediaPlayer;
    private boolean isPrepared;
    public static Exhibits PLAYING_EXHIBITS;

    public static XIntent bindService(Context context) {
        return new XIntent(context, VoiceService.class);
    }

    public static void unbindService(Context context, ServiceConnection serviceConnection) {
        context.unbindService(serviceConnection);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder(this);
    }

    public class MyBinder extends Binder {
        VoiceService service;

        public MyBinder(VoiceService service) {
            this.service = service;
        }

        public VoiceService getService() {
            return service;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setLooping(false);
        mMediaPlayer.setOnPreparedListener(this);
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        isPrepared = true;
        if (mOnVoicePlayListener != null) {
            mOnVoicePlayListener.onVoicePrepared(false);
        }
    }

    public void playVoice(Exhibits exhibits) {
        PLAYING_EXHIBITS = exhibits;
        String voice = exhibits.getVoice();
        xLogUtil.e("音频:" + voice);
        try {
            mMediaPlayer.setDataSource(voice);
            mMediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e1) {
            xLogUtil.e("播放的音频为空");
            if (mOnVoicePlayListener != null) {
                mOnVoicePlayListener.onVoicePrepared(true);
            }
            stopSelf();
        }
    }

    private OnVoicePlayListener mOnVoicePlayListener;

    public interface OnVoicePlayListener {
        void onVoicePrepared(boolean noVoice);
    }

    public void setOnVoicePlayListener(OnVoicePlayListener onVoicePlayListener) {
        this.mOnVoicePlayListener = onVoicePlayListener;
    }

    public boolean isPrepared() {
        return isPrepared;
    }

    public void seekTo(int progress) {
        mMediaPlayer.seekTo(progress);
    }

    public void play() {
        mMediaPlayer.start();
    }

    public void pause() {
        mMediaPlayer.pause();
    }

    public int getCurrentPosition() {
        return mMediaPlayer.getCurrentPosition();
    }

    public int getDuration() {
        return mMediaPlayer.getDuration();
    }

    public boolean isPlaying() {
        return mMediaPlayer.isPlaying();
    }

    @Override
    public void onDestroy() {
        PLAYING_EXHIBITS = null;
        super.onDestroy();
    }
}
