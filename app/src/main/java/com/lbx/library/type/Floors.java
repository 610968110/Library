package com.lbx.library.type;

import com.lbx.library.R;
import com.lbx.library.bean.Floor;

import java.util.ArrayList;
import java.util.List;

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

public enum Floors {

    FIRST_FLOOR(new Floor("文明摇篮展厅", R.drawable.floor_1)),
    SECOND_FLOOR(new Floor("夏商踪迹展厅", R.drawable.floor_2)),
    THIRD_FLOOR(new Floor("晋国霸业展厅", R.drawable.floor_3)),
    FOURTH_FLOOR(new Floor("民族熔炉展厅", R.drawable.floor_4)),
    FIFTH_FLOOR(new Floor("佛风遗韵展厅", R.drawable.floor_5)),
    SIXTH_FLOOR(new Floor("戏曲故乡", R.drawable.floor_6)),
    SEVENTH_FLOOR(new Floor("明清晋商展厅", R.drawable.floor_7));

    private Floor floor;

    Floors(Floor floor) {
        this.floor = floor;
    }

    public static List<Floor> toList() {
        List<Floor> list = new ArrayList<>();
        for (Floors floor : values()) {
            list.add(floor.getFloor());
        }
        return list;
    }

    public Floor getFloor() {
        return floor;
    }
}
