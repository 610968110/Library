package com.lbx.library.type;

import java.util.Locale;

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
 * @date 2019/1/15.
 */

public enum LanguageType {

    CHINESE(Locale.SIMPLIFIED_CHINESE),
    ENGLISH(Locale.ENGLISH),
    JAPANESE(Locale.JAPANESE),
    KOREAN(Locale.KOREAN);

    private Locale locale;

    LanguageType(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }

    public static LanguageType getTypeByLocale(Locale locale) {
        for (LanguageType type : LanguageType.values()) {
            if (type.getLocale() == locale) {
                return type;
            }
        }
        return CHINESE;
    }
}
