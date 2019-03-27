package com.lbx.library;

import android.Manifest;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.lbx.library.bean.Floor;
import com.lbx.library.bean.Person;
import com.lbx.library.type.Floors;
import com.lbx.library.type.LanguageType;

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
 * @date 2019/1/14.
 */

public class Config {

    public static final boolean DEBUG = XTools.ApkUtil().isApkInDebug(XTools.getApplicationContext());

    private static LanguageType LANGUAGE = LanguageType.CHINESE;
    public static final String AUTO_PLAY_TAG = "auto_play";

    public static boolean isAutoPlay() {
        return XTools.SpUtil().getBoolean(AUTO_PLAY_TAG, true);
    }

    public static void init() {
        Context context = XTools.getApplicationContext();
        XTools.FileUtil().getCopyFileUtils()
                .copyRawFile2FilesDir(context, R.raw.test_video, "test_video.mp4");
        XTools.FileUtil().getCopyFileUtils()
                .copyRawFile2FilesDir(context, R.raw.test_voice, "test_voice.m4a");
        for (Floors f : Floors.values()) {
            Floor floor = f.getFloor();
            floor.clearExhibits();
            f.addExhibits(floor);
        }
    }

    private static final Person MINE = new Person("888", "我", 274, 600, true);
    private static final Person TEST_FRIEND = new Person("777", "测试朋友", 1670, 700);

    public static Person[] getPerson() {
        return new Person[]{MINE, TEST_FRIEND};
    }

    public static Person getMine() {
        return MINE;
    }

    public static Person getTestFriend() {
        return TEST_FRIEND;
    }

    public static LanguageType getLanguage() {
        return LANGUAGE;
    }

    public static void setLanguage(LanguageType language) {
        Config.LANGUAGE = language;
        Resources resources = XTools.getApplicationContext().getResources();
        // 获得设置对象
        Configuration config = resources.getConfiguration();
        config.setLocale(language.getLocale());
        DisplayMetrics dm = resources.getDisplayMetrics();
        resources.updateConfiguration(config, dm);
    }

    public static String[] PERMISSION = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.NFC,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.BLUETOOTH,
            Manifest.permission.BLUETOOTH_ADMIN,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ACCESS_COARSE_LOCATION};
}
