package com.lbx.library.ui.fragment;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lbx.library.R;
import com.lbx.library.adapter.FloorAdapter;
import com.lbx.library.base.BaseFragment;
import com.lbx.library.bean.Floor;
import com.lbx.library.injector.ContextLifeCycle;
import com.lbx.library.injector.components.ActivityComponent;
import com.lbx.library.injector.components.DaggerFragmentComponent;
import com.lbx.library.ui.activity.FloorDetailedActivity;

import javax.inject.Inject;

import butterknife.BindView;
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

public class FloorFragment extends BaseFragment {

    @BindView(R.id.rv_floor)
    RecyclerView mFloorListView;
    private FloorAdapter mAdapter;
    @Inject
    @ContextLifeCycle
    Context mContext;

    public static FloorFragment newInstance() {
        Bundle args = new Bundle();
        FloorFragment fragment = new FloorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void bindComponent(ActivityComponent activityComponent) {
        mFragmentComponent = DaggerFragmentComponent.builder()
                .activityComponent(activityComponent)
                .build();
        mFragmentComponent.inject(this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_floor;
    }

    @Override
    public void getDataBinding(ViewDataBinding binding) {

    }

    @Override
    public void initView(View view) {
        mFloorListView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new FloorAdapter(getContext());
        mFloorListView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        super.initListener();
        mAdapter.setOnItemClickListener(new BaseDataAdapter.OnItemClickListener<Floor>() {
            @Override
            public void onItemClick(RecyclerView recyclerView, int id, int position, Floor entity) {
                xLogUtil.e(this, "点击，进入Floor详情页");
                FloorDetailedActivity.getIntent(mContext, entity).start();
            }

            @Override
            public void onItemLongClick(RecyclerView recyclerView, int id, int position, Floor entity) {

            }
        });
    }
}
