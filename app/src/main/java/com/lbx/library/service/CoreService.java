package com.lbx.library.service;

import android.app.Activity;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.lbx.library.Config;
import com.lbx.library.base.BaseActivity;
import com.lbx.library.bean.Exhibits;
import com.lbx.library.type.Floors;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lbx.xtoollib.XTools;
import lbx.xtoollib.phone.xLogUtil;

import static org.altbeacon.beacon.BeaconManager.DEFAULT_BACKGROUND_BETWEEN_SCAN_PERIOD;
import static org.altbeacon.beacon.BeaconManager.DEFAULT_BACKGROUND_SCAN_PERIOD;

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

public class CoreService extends Service implements BeaconConsumer, RangeNotifier {

    private BeaconManager mBeaconManager;
    private Exhibits mBLEPlayExhibits;

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
        BluetoothManager mBluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        BluetoothAdapter adapter = mBluetoothManager.getAdapter();
        if (adapter == null || !adapter.isEnabled()) {
            try {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                enableBtIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(enableBtIntent);
            } catch (Exception e) {
                XTools.UiUtil().toastInUI("请您手动进入设置页面打开蓝牙");
            }
        }
        mBeaconManager = BeaconManager.getInstanceForApplication(this);
        //设置前台扫描时长
        mBeaconManager.setForegroundScanPeriod(1000);
        //设置前台扫描间隔
        mBeaconManager.setForegroundBetweenScanPeriod(500);
        //设置每个蓝牙LE扫描周期的持续时间（以毫秒为单位）以查找信标。
        // 此函数用于设置调用bind（org.altbeacon.beacon.BeaconConsumer）之前的时间段或在背景/前景之间切换的时间段。
        // 要使其在已经运行的扫描（在下一个周期开始时）生效，请调用updateScanPeriods（）
        //设置后台扫描时长
        mBeaconManager.setBackgroundScanPeriod(DEFAULT_BACKGROUND_SCAN_PERIOD);

        //当没有测距/监控客户端位于前台时，设置每个蓝牙LE扫描周期之间不扫描的时间（以毫秒为单位）
        //设置后台扫描间隔
        mBeaconManager.setBackgroundBetweenScanPeriod(DEFAULT_BACKGROUND_BETWEEN_SCAN_PERIOD);
        mBeaconManager.bind(this);
    }

    @Override
    public void onBeaconServiceConnect() {
        Region region = new Region("all-region-beacon", null, null, null);
        mBeaconManager.addRangeNotifier(this);
        try {
            mBeaconManager.startRangingBeaconsInRegion(region);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void didRangeBeaconsInRegion(Collection<Beacon> collection, Region region) {
        if (!Config.isAutoPlay()) {
            return;
        }
        List<Beacon> list = new ArrayList<>();
        list.addAll(collection);
//        xLogUtil.e("didRangeBeaconsInRegion:" + list.size());
        int i = 0;
        for (Beacon b : list) {
            String uuid = b.getId1().toString();
            String major = b.getId2().toString();
            String minor = b.getId3().toString();
            double distance = b.getDistance();
            xLogUtil.e("beacon" + i++ + ":"
                    + "uuid:" + uuid + ","
                    + "major:" + major + ","
                    + "minor:" + minor + ","
                    + "distance:" + distance);
        }
        //TODO 播放
        if (list.size() > 0) {
            Beacon beacon = list.get(0);
            if (mBLEPlayExhibits != null && mBLEPlayExhibits.getId()
                    .equals(Floors.FIRST_FLOOR.getFloor().getExhibitsList().get(0).getId())) {
                xLogUtil.e("BLE播放和当前播放的展品是一个");
                return;
            }
            mBLEPlayExhibits = Floors.FIRST_FLOOR.getFloor().getExhibitsList().get(0);
            ArrayList<Activity> activities = XTools.ActivityUtil().getActivities();
            BaseActivity activity = (BaseActivity) activities.get(activities.size() - 1);
            activity.playVoice(mBLEPlayExhibits);
        }
    }

    @Override
    public void onDestroy() {
        if (mBeaconManager != null) {
            mBeaconManager.removeRangeNotifier(this);
            mBeaconManager.unbind(this);
        }
        mBLEPlayExhibits = null;
        super.onDestroy();
    }
}
