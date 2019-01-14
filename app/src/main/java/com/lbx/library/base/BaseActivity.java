package com.lbx.library.base;

import android.os.Bundle;

import com.lbx.library.App;
import com.lbx.library.injector.components.ActivityComponent;
import com.lbx.library.injector.components.AppComponent;


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

public abstract class BaseActivity extends lbx.xtoollib.base.BaseActivity {

    public ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        bindComponent(getApp().getAppComponent());
        super.onCreate(savedInstanceState);
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
}
