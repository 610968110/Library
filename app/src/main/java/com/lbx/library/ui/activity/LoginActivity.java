package com.lbx.library.ui.activity;

import android.Manifest;
import android.content.Context;
import android.databinding.ViewDataBinding;
import android.graphics.Rect;
import android.view.View;
import android.widget.Button;

import com.lbx.library.Config;
import com.lbx.library.R;
import com.lbx.library.base.BaseActivity;
import com.lbx.library.databinding.ActivityLoginBinding;
import com.lbx.library.injector.components.AppComponent;
import com.lbx.library.injector.components.DaggerActivityComponent;
import com.lbx.library.injector.modules.ActivityModule;
import com.lbx.library.type.LanguageType;

import java.util.Locale;

import butterknife.BindView;
import lbx.xtoollib.XIntent;
import lbx.xtoollib.XTools;

/**
 * @author lbx
 */
public class LoginActivity extends BaseActivity {

    private ActivityLoginBinding mBindging;
    @BindView(R.id.btn_lng_chinese)
    Button mBtnLngChinese;
    @BindView(R.id.btn_lng_english)
    Button mBtnLngEnglish;
    @BindView(R.id.btn_lng_japanese)
    Button mBtnLngJapanese;
    @BindView(R.id.btn_lng_korean)
    Button mBtnLngKorean;

    public static XIntent getIntent(Context context) {
        return new XIntent(context, LoginActivity.class);
    }

    @Override
    public void bindComponent(AppComponent appComponent) {
        mActivityComponent = DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .activityModule(new ActivityModule(this))
                .build();
        mActivityComponent.inject(this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    public void getDataBinding(ViewDataBinding binding) {
        mBindging = (ActivityLoginBinding) binding;
    }

    @Override
    public void initView(View view) {
        XTools.PermissionUtil().checkPermission(this, 0,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        super.initListener();
        mBtnLngChinese.setOnClickListener(this);
        mBtnLngEnglish.setOnClickListener(this);
        mBtnLngJapanese.setOnClickListener(this);
        mBtnLngKorean.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_lng_chinese:
            case R.id.btn_lng_english:
            case R.id.btn_lng_japanese:
            case R.id.btn_lng_korean:
                try {
                    Rect rect = new Rect();
                    v.getGlobalVisibleRect(rect);
                    //设置语言
                    Config.setLanguage(LanguageType.getTypeByLocale((Locale) v.getTag()));
                    startActivity(MainActivity.getIntent(this, rect.centerX(), rect.centerY()));
                    XTools.UiUtil().getHandlerByActivity(this).postDelayed(this::finish, 1000);
                } catch (Exception ignored) {
                }
                break;
            default:
                break;
        }
    }
}
