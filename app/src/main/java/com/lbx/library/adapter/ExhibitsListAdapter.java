package com.lbx.library.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.view.View;

import com.lbx.library.R;
import com.lbx.library.bean.Exhibits;
import com.lbx.library.databinding.ItemExhibitsListBinding;

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
 * @date 2019/1/21.
 */

public class ExhibitsListAdapter extends BaseDataAdapter<Exhibits, ItemExhibitsListBinding, BaseDataAdapter.BaseHolder> {

    public ExhibitsListAdapter(Context context, List<Exhibits> list) {
        super(context, list);
    }

    @Override
    public BaseHolder getHolder(View view, ViewDataBinding binding) {
        return new BaseHolder(view, binding);
    }

    @Override
    public int itemLayout() {
        return R.layout.item_exhibits_list;
    }

    @Override
    public void dataBinding(ItemExhibitsListBinding binding, int position, Exhibits entity, BaseHolder baseHolder) {
        binding.setExhibits(entity);
    }
}
