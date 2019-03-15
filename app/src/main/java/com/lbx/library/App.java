package com.lbx.library;

import android.app.Application;
import android.content.Context;

import com.lbx.library.injector.components.AppComponent;
import com.lbx.library.injector.components.DaggerAppComponent;
import com.lbx.library.injector.modules.AppModule;
import com.lbx.library.service.VoiceService;

import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.powersave.BackgroundPowerSaver;
import org.altbeacon.beacon.startup.BootstrapNotifier;
import org.altbeacon.beacon.startup.RegionBootstrap;

import lbx.xtoollib.XTools;

import static lbx.xtoollib.phone.xLogUtil.LEVEL_NONE;
import static lbx.xtoollib.phone.xLogUtil.LEVEL_VERBOSE;

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
 * @date 2019/1/14.
 */

public class App extends Application implements BootstrapNotifier {

    private Context mContext;
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this.getApplicationContext();
        boolean debug = XTools.ApkUtil().isApkInDebug(mContext);
        new XTools.Builder()
                .log(/*是否打印log*/true)
                .logTag(/*设置log的tag*/"xys")
                .logLevel(/*设置显示log的级别*/debug ? LEVEL_VERBOSE : LEVEL_NONE)
                .errLogFilePath(/*设置crashLog的文件存储路径*/"Library")
                .errLogFileName(/*设置crashLog的文件存储名*/"ERR")
                .errLogFile(
                            /*是否打印到文件*/true,
                            /*是否打印到log*/debug)
                .logPrintFile(
                            /*是否打印log到文件*/true,
                            /*打印log文件在sd卡下的路径*/"Library/log",
                            /*打印到file的log是否加密(des+base64对称加密), null为不加密*/ null)
                .build(this)
                .init();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        mAppComponent.inject(this);
        BeaconManager beaconManager = org.altbeacon.beacon.BeaconManager.getInstanceForApplication(this);
        beaconManager.getBeaconParsers().clear();
        beaconManager.getBeaconParsers().add(new BeaconParser()
                .setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));
        Region region = new Region("all-region-beacon", null, null, null);
        RegionBootstrap regionBootstrap = new RegionBootstrap(this, region);
        BackgroundPowerSaver backgroundPowerSaver = new BackgroundPowerSaver(this);
        VoiceService.start(this);
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    @Override
    public void didEnterRegion(Region region) {

    }

    @Override
    public void didExitRegion(Region region) {

    }

    @Override
    public void didDetermineStateForRegion(int i, Region region) {

    }
}
