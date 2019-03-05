package com.lbx.library.service;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.lbx.library.utils.StringUtil;

import java.util.LinkedHashMap;
import java.util.Map;

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
 * @date 2019/3/4.
 */

public class CoreService extends Service {

    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothManager mBluetoothManager;
    private Map<String, BluetoothDevice> mMap = new LinkedHashMap<>();


    public static void start(Context context) {
        try {
            Intent intent = new Intent(context, CoreService.class);
            context.startService(intent);
        } catch (Exception ignored) {

        }
    }

    public static void stop(Context context) {
        try {
            Intent intent = new Intent(context, CoreService.class);
            context.startService(intent);
        } catch (Exception ignored) {
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder(this);
    }

    public class MyBinder extends Binder {
        private CoreService mCoreService;

        public MyBinder(CoreService coreService) {
            this.mCoreService = coreService;
        }

        public CoreService getService() {
            return mCoreService;
        }
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mBluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = mBluetoothManager.getAdapter();
        if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            enableBtIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(enableBtIntent);
        }
        mBluetoothAdapter.startLeScan(mCallback);
    }

    private BluetoothAdapter.LeScanCallback mCallback = (device, rssi, scanRecord) -> {
        mMap.put(device.getAddress(), device);
        String str = StringUtil.bytesToHexString(scanRecord);
        //??
        StringBuilder uuid = new StringBuilder();
        try {
            uuid.append(str.substring(0, 8));
            uuid.append("-");
            uuid.append(str.substring(8, 12));
            uuid.append("-");
            uuid.append(str.substring(12, 16));
            uuid.append("-");
            uuid.append(str.substring(16, 20));
            uuid.append("-");
            uuid.append(str.substring(20, 32));
        } catch (Exception ignored) {
        }
        xLogUtil.e("onLeScan:" + rssi
                + " device:"
                + device.getName()
                + "  "
                + device.getAddress()
                + "  "
                + str);
    };

    @Override
    public void onDestroy() {
        if (mBluetoothAdapter != null) {
            mBluetoothAdapter.startLeScan(mCallback);
        }
        super.onDestroy();
    }
}
