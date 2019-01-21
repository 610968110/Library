package com.lbx.library.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.view.View;

import com.lbx.library.R;
import com.lbx.library.bean.Floor;
import com.lbx.library.databinding.ItemFloorBinding;
import com.lbx.library.type.Floors;

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
 * @date 2019/1/21.
 */

public class FloorAdapter extends BaseDataAdapter<Floor, ItemFloorBinding, BaseDataAdapter.BaseHolder> {

    public FloorAdapter(Context context) {
        super(context, Floors.toList());
    }

    @Override
    public BaseHolder getHolder(View view, ViewDataBinding binding) {
        return new BaseHolder(view, binding);
    }

    @Override
    public int itemLayout() {
        return R.layout.item_floor;
    }

    @Override
    public void dataBinding(ItemFloorBinding binding, int position, Floor entity, BaseHolder baseHolder) {
        binding.setFloor(entity);
    }
}
