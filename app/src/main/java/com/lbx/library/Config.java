package com.lbx.library;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

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

    public static void init() {

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
}