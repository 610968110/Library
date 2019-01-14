package com.lbx.library.injector.modules;

import android.content.Context;

import com.lbx.library.App;
import com.lbx.library.Config;
import com.lbx.library.injector.ContextLifeCycle;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

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
 * @date 2018/5/31.
 */
@Module
public class AppModule {

    private final App mApp;

    public AppModule(App mApp) {
        this.mApp = mApp;
        Config.init();
    }

    @Provides
    @Singleton
    App getApp() {
        return mApp;
    }

    @Provides
    @Singleton
    @ContextLifeCycle("App")
    Context getActivityContext() {
        return mApp.getApplicationContext();
    }
}
