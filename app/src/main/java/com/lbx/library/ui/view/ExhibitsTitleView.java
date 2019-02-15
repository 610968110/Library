package com.lbx.library.ui.view;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.lbx.library.R;
import com.lbx.library.bean.Floor;

import lbx.xtoollib.XTools;

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

public class ExhibitsTitleView extends FrameLayout {

    private TextView mFloorView, mCountView;
    private Floor mFloor;

    public ExhibitsTitleView(@NonNull Context context) {
        this(context, null);
    }

    public ExhibitsTitleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExhibitsTitleView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = XTools.UiUtil().inflate(R.layout.view_exhibits_title);
        addView(view);
        mFloorView = view.findViewById(R.id.tv_floor);
        mCountView = view.findViewById(R.id.tv_count);
    }

    private void bindFloor(Floor floor, int selectPos) {
        mFloor = floor;
        if (floor != null) {
            mFloorView.setText(String.valueOf(floor.getFloor()));
            mFloorView.append(XTools.ResUtil().getString(R.string.f));
            mCountView.setText(String.format("%s/%s件展品", selectPos, floor.getExhibitsList().size()));
        }
    }

    @BindingAdapter({"floor", "selectPos"})
    public static void floor(ExhibitsTitleView view, Floor floor, int selectPos) {
        view.bindFloor(floor, selectPos);
    }
}
