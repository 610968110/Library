package com.lbx.library.base;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.CallSuper;

import com.lbx.library.App;
import com.lbx.library.bean.Exhibits;
import com.lbx.library.injector.components.ActivityComponent;
import com.lbx.library.injector.components.AppComponent;
import com.lbx.library.service.VoiceService;
import com.lbx.library.type.Floors;
import com.lbx.library.utils.ServiceUtil;

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
 * @date 2018/8/24.
 */

public abstract class BaseActivity extends lbx.xtoollib.base.BaseActivity implements VoiceService.OnVoicePlayListener {

    public ActivityComponent mActivityComponent;
    private NfcAdapter mNfcAdapter;
    private PendingIntent mPendingIntent;
    private VoiceService mVoiceService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        bindComponent(getApp().getAppComponent());
        super.onCreate(savedInstanceState);
        restartService();
    }

    void restartService() {
        if (!ServiceUtil.isServiceRunning(this, VoiceService.class.getName())) {
            VoiceService.start(this);
        }
    }

    @CallSuper
    @Override
    public void initData() {
        Intent nfcIntent = new Intent(this, getClass());
        nfcIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mNfcAdapter != null) {
            mPendingIntent = PendingIntent.getActivity(this, 0, nfcIntent, 0);
        } else {
            XTools.UiUtil().showToast("您的设备不支持nfc");
        }
        if (connectService()) {
            VoiceService.getIntent(this).bindService(mConnection);
        }
    }

    public boolean connectService() {
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mNfcAdapter != null) {
            mNfcAdapter.enableForegroundDispatch(this, mPendingIntent, null, null);
        }
        if (mVoiceService != null) {
            mVoiceService.setOnVoicePlayListener(BaseActivity.this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mNfcAdapter != null) {
            mNfcAdapter.disableForegroundDispatch(this);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        xLogUtil.e("onNewIntent");
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())) {
            Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            String CardId = ByteArrayToHexString(tagFromIntent.getId());
            xLogUtil.e("CardId:" + CardId);
            XTools.UiUtil().toastInUI("CardId:" + CardId);
            if (mVoiceService != null) {
                //TODO 测试的第一个
                mVoiceService.setExhibits(Floors.FIRST_FLOOR.getFloor().getExhibitsList().get(0));
            }
        }
    }

    /**
     * 设置注入
     *
     * @param appComponent 应用的Component
     */
    public abstract void bindComponent(AppComponent appComponent);

    private App getApp() {
        return (App) getApplication();
    }

    private String ByteArrayToHexString(byte[] inarray) {
        int i, j, in;
        String[] hex = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A",
                "B", "C", "D", "E", "F"};
        String out = "";
        for (j = 0; j < inarray.length; ++j) {
            in = (int) inarray[j] & 0xff;
            i = (in >> 4) & 0x0f;
            out += hex[i];
            i = in & 0x0f;
            out += hex[i];
        }
        return out;
    }

    @Override
    public void onVoicePrepared(boolean noVoice) {
        if (!noVoice) {
            mVoiceService.play();
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mVoiceService = ((VoiceService.MyBinder) service).getService();
            mVoiceService.setOnVoicePlayListener(BaseActivity.this);
            BaseActivity.this.onServiceConnected(mVoiceService);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mVoiceService = null;
            BaseActivity.this.onServiceDisconnected();
        }
    };

    public void onServiceConnected(VoiceService service) {
    }

    public void onServiceDisconnected() {

    }

    public void playVoice(Exhibits exhibits) {
        if (mVoiceService != null) {
            mVoiceService.setExhibits(exhibits);
        }
    }

    @Override
    protected void onDestroy() {
        if (connectService()) {
            VoiceService.getIntent(this).unbindService(mConnection);
        }
        super.onDestroy();
    }
}
