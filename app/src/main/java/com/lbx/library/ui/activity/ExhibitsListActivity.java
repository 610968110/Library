package com.lbx.library.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.lbx.library.R;
import com.lbx.library.adapter.ExhibitsListAdapter;
import com.lbx.library.base.BaseActivity;
import com.lbx.library.bean.Exhibits;
import com.lbx.library.bean.Floor;
import com.lbx.library.databinding.ActivityExhibitsListBinding;
import com.lbx.library.injector.ContextLifeCycle;
import com.lbx.library.injector.components.AppComponent;
import com.lbx.library.injector.components.DaggerActivityComponent;
import com.lbx.library.injector.modules.ActivityModule;
import com.lbx.library.ui.view.BlendImageButton;
import com.lbx.library.ui.view.ExhibitsTitleView;
import com.lbx.library.ui.view.TopBar;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import lbx.xtoollib.XIntent;
import lbx.xtoollib.XTools;
import lbx.xtoollib.base.BaseDataAdapter;
import lbx.xtoollib.phone.xLogUtil;

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
 * @date 2019/1/21.
 */

public class ExhibitsListActivity extends BaseActivity implements BaseDataAdapter.OnItemClickListener<Exhibits> {

    @BindView(R.id.tb_exhibits)
    TopBar mTopBar;
    @BindView(R.id.etv_title)
    ExhibitsTitleView mListTitleView;
    @BindView(R.id.btn_guide)
    BlendImageButton mGuideButton;
    @BindView(R.id.rv_exhibits)
    RecyclerView mRecycleView;
    @BindView(R.id.iv_exhibits)
    ImageView mExhibitsImageView;
    private Floor mFloor;
    private ActivityExhibitsListBinding mBinding;
    @Inject
    @ContextLifeCycle
    Context mContext;
    private ArrayList<Exhibits> mList;
    private ExhibitsListAdapter mAdapter;
    private Exhibits mSelectExhibits;

    public static XIntent getIntent(Context context, Floor floor) {
        XIntent intent = new XIntent(context, ExhibitsListActivity.class);
        intent.putExtra("floor", floor);
        return intent;
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
        return R.layout.activity_exhibits_list;
    }

    @Override
    public void initIntent(Intent intent) {
        super.initIntent(intent);
        mFloor = intent.getParcelableExtra("floor");
    }

    @Override
    public void getDataBinding(ViewDataBinding binding) {
        mBinding = (ActivityExhibitsListBinding) binding;
        mBinding.setPos(1);
    }

    @Override
    public void initView(View view) {
        mTopBar.bind(this);
        mRecycleView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    public void initData() {
        mBinding.setFloor(mFloor);
        mList = mFloor.getExhibitsList();
        mAdapter = new ExhibitsListAdapter(mContext, mList);
        mRecycleView.setAdapter(mAdapter);
        if (mList != null && !mList.isEmpty()) {
            mSelectExhibits = mList.get(0);
            mExhibitsImageView.setImageResource(mSelectExhibits.getBigImage());
        } else {
            XTools.UiUtil().showToast("展品为空");
            finish();
        }
    }

    @Override
    public void initListener() {
        super.initListener();
        mGuideButton.setOnClickListener((v) -> xLogUtil.e(this, "导航到这里"));
        mAdapter.setOnItemClickListener(this);
        if (mSelectExhibits != null) {
            xLogUtil.e(this, "点击大图跳入视频页");
            mExhibitsImageView.setOnClickListener((v) ->
                    VideoActivity.start(this, mSelectExhibits, mExhibitsImageView));
        }
    }

    @Override
    public void onItemClick(RecyclerView recyclerView, int id, int position, Exhibits entity) {
        xLogUtil.e(this, "点击了展品:" + position);
        mSelectExhibits = entity;
        mExhibitsImageView.setImageResource(entity.getBigImage());
        mBinding.setPos(position + 1);
    }

    @Override
    public void onItemLongClick(RecyclerView recyclerView, int id, int position, Exhibits entity) {

    }
}
