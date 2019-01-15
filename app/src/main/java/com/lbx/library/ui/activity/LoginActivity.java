package com.lbx.library.ui.activity;

import android.content.Context;
import android.databinding.ViewDataBinding;
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
                    //设置语言
                    Config.setLanguage(LanguageType.getTypeByLocale((Locale) v.getTag()));
                    MainActivity.getIntent(this).start();
                    finish();
                } catch (Exception ignored) {
                }
                break;
            default:
                break;
        }
    }
}