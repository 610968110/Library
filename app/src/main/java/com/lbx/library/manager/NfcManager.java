package com.lbx.library.manager;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.provider.Settings;

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
 * @date 2019/2/26.
 */

public class NfcManager {

    private static NfcManager INSTANCE;
    private Activity mActivity;
    private NfcAdapter mNfcAdapter;
    private INfcTask mINfcTask;
    private PendingIntent mPendingIntent;

    public static NfcManager getInstance() {
        if (INSTANCE == null) {
            synchronized (NfcManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NfcManager();
                }
            }
        }
        return INSTANCE;
    }

    private NfcManager() {
    }

    public void init(Activity activity) {
        this.mActivity = activity;
        Intent nfcIntent = new Intent(activity, getClass());
        nfcIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        mNfcAdapter = NfcAdapter.getDefaultAdapter(activity);
        mPendingIntent = PendingIntent.getActivity(activity, 0, nfcIntent, 0);
        mINfcTask = null == mNfcAdapter ? new NfcDisEnableImpl() : new NfcEnableImpl();
    }

    public boolean isEnable() {
        return mINfcTask.isEnable();
    }

    public void autoOpenSettingPager() {
        mINfcTask.autoOpenSettingPager();
    }

    public void onResume(Activity activity) {
        mINfcTask.onResume(activity);
    }

    public void onPause(Activity activity) {
        mINfcTask.onPause(activity);
    }

    private interface INfcTask {
        boolean isEnable();

        void autoOpenSettingPager();

        void onResume(Activity activity);

        void onPause(Activity activity);
    }

    private class NfcEnableImpl implements INfcTask {

        @Override
        public boolean isEnable() {
            return mNfcAdapter.isEnabled();
        }

        @Override
        public void autoOpenSettingPager() {
            if (!isEnable()) {
                Intent setNfc = new Intent(Settings.ACTION_NFC_SETTINGS);
                mActivity.startActivity(setNfc);
            }
        }

        @Override
        public void onResume(Activity activity) {
            mNfcAdapter.enableForegroundDispatch(activity, mPendingIntent, null, null);
            xLogUtil.e("enableForegroundDispatch:");
        }

        @Override
        public void onPause(Activity activity) {
            mNfcAdapter.disableForegroundDispatch(activity);
        }
    }

    private class NfcDisEnableImpl implements INfcTask {

        private void toastDisEnableNfc() {
            XTools.UiUtil().toastInUI("该设备不支持NFC");
        }

        @Override
        public boolean isEnable() {
            return false;
        }

        @Override
        public void autoOpenSettingPager() {
            toastDisEnableNfc();
        }

        @Override
        public void onResume(Activity activity) {

        }

        @Override
        public void onPause(Activity activity) {

        }
    }
}
