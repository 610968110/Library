package com.lbx.library.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.view.View;

import com.lbx.library.R;
import com.lbx.library.bean.Friend;
import com.lbx.library.databinding.ItemFriendListBinding;

import java.util.List;

import lbx.xtoollib.base.BaseDataAdapter;

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
 * @date 2019/2/18.
 */

public class FriendListAdapter extends BaseDataAdapter<Friend, ItemFriendListBinding, BaseDataAdapter.BaseHolder> {

    public FriendListAdapter(Context context, List<Friend> list) {
        super(context, list);
    }

    @Override
    public BaseHolder getHolder(View view, ViewDataBinding binding) {
        return new BaseHolder(view, binding);
    }

    @Override
    public int itemLayout() {
        return R.layout.item_friend_list;
    }

    @Override
    public void dataBinding(ItemFriendListBinding binding, int position, Friend entity, BaseHolder baseHolder) {
        binding.setFriend(entity);
        binding.ivDelete.setOnClickListener(v -> {
            if (mOnDeleteClick != null) {
                mOnDeleteClick.delete(position, entity);
            }
        });
    }

    private OnDeleteClick mOnDeleteClick;

    public interface OnDeleteClick {
        void delete(int pos, Friend friend);
    }

    public void setOnDeleteClick(OnDeleteClick onDeleteClick) {
        this.mOnDeleteClick = onDeleteClick;
    }
}
