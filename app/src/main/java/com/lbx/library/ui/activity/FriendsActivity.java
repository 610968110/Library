package com.lbx.library.ui.activity;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lbx.library.R;
import com.lbx.library.adapter.FriendListAdapter;
import com.lbx.library.base.BaseActivity;
import com.lbx.library.bean.Friend;
import com.lbx.library.bean.event.GuideFriendTest;
import com.lbx.library.databinding.ActivityFriendsBinding;
import com.lbx.library.injector.ContextLifeCycle;
import com.lbx.library.injector.components.AppComponent;
import com.lbx.library.injector.components.DaggerActivityComponent;
import com.lbx.library.injector.modules.ActivityModule;
import com.lbx.library.type.Floors;
import com.lbx.library.ui.view.TopBar;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
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
 *         我的伙伴
 * @date 2019/2/15
 */
public class FriendsActivity extends BaseActivity implements BaseDataAdapter.OnItemClickListener<Friend>, FriendListAdapter.OnDeleteClick {

    @Inject
    @ContextLifeCycle
    Context mContext;
    @BindView(R.id.tb_friends)
    TopBar mTopBar;
    private Friend mFriend;
    @BindView(R.id.rv_friends_list)
    RecyclerView mRecyclerView;
    private ActivityFriendsBinding mBinding;
    private List<Friend> mList = new ArrayList<>();
    private FriendListAdapter mAdapter;

    public static XIntent getIntent(Context context) {
        return new XIntent(context, FriendsActivity.class);
    }

    @Override
    public void bindComponent(AppComponent appComponent) {
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .appComponent(appComponent)
                .build();
        mActivityComponent.inject(this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_friends;
    }

    @Override
    public void getDataBinding(ViewDataBinding binding) {
        mBinding = (ActivityFriendsBinding) binding;
    }

    @Override
    public void initView(View view) {
        mBinding.setFriendList(mList);
        mTopBar.bind(this, XTools.ResUtil().getString(R.string.my_friends2));
    }

    @Override
    public void initData() {
        super.initData();
        mFriend = new Friend();
        mBinding.setFriend(mFriend);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new FriendListAdapter(mContext, mList);
        mAdapter.setOnDeleteClick(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initListener() {
        super.initListener();
        mAdapter.setOnItemClickListener(this);
    }

    @OnClick(R.id.btn_friend_sure)
    public void onSureClick() {
        xLogUtil.e(this, "添加好友:" + mFriend);
        if (!mFriend.checkAddFriend()) {
            XTools.UiUtil().toastInUI("请您填全伙伴信息");
            return;
        }
        if (mList.contains(mFriend)) {
            XTools.UiUtil().toastInUI("伙伴已存在与列表中");
            return;
        }
        mList.add(mFriend);
        mBinding.setFriendList(mList);
        mAdapter.notifyDataSetChanged();
        mFriend = new Friend();
        mBinding.setFriend(mFriend);
        mBinding.etFriendId.requestFocus();
    }

    @Override
    public void onItemClick(RecyclerView recyclerView, int id, int position, Friend entity) {
        XTools.UiUtil().getSystemDialog(FriendsActivity.this,
                "导航",
                "您是否要导航到" + entity.getName() + "?",
                (dialog, which) -> {
                    FloorDetailedActivity.getIntent(FriendsActivity.this, Floors.FIRST_FLOOR.getFloor()).start();
                    EventBus.getDefault().postSticky(new GuideFriendTest(true, entity.getName() + " " + entity.getId()));
                },
                (dialog, which) -> dialog.dismiss());
//                .show();
    }

    @Override
    public void onItemLongClick(RecyclerView recyclerView, int id, int position, Friend entity) {

    }

    @Override
    public void delete(int pos, Friend friend) {
        xLogUtil.e(this, "删除了:" + friend);
        mList.remove(pos);
        mAdapter.notifyDataSetChanged();
        mBinding.setFriendList(mList);
    }
}
