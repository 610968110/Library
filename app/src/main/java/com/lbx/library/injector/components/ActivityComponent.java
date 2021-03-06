package com.lbx.library.injector.components;

import android.app.Activity;
import android.content.Context;

import com.lbx.library.injector.ContextLifeCycle;
import com.lbx.library.injector.ForActivity;
import com.lbx.library.injector.modules.ActivityModule;
import com.lbx.library.ui.activity.ExhibitsListActivity;
import com.lbx.library.ui.activity.FloorDetailedActivity;
import com.lbx.library.ui.activity.FriendsActivity;
import com.lbx.library.ui.activity.IntroductionSummaryActivity;
import com.lbx.library.ui.activity.LoginActivity;
import com.lbx.library.ui.activity.MainActivity;
import com.lbx.library.ui.activity.VideoActivity;

import dagger.Component;

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
@ForActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    @ContextLifeCycle()
    Context activityContext();

    @ContextLifeCycle("App")
    Context appContext();

    void inject(LoginActivity activity);

    void inject(MainActivity activity);

    void inject(FloorDetailedActivity activity);

    void inject(ExhibitsListActivity activity);

    void inject(VideoActivity activity);

    void inject(FriendsActivity activity);

    void inject(IntroductionSummaryActivity activity);
}
