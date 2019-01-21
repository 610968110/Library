package com.lbx.library.type;

import com.lbx.library.R;
import com.lbx.library.bean.Exhibits;
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

    FIRST_FLOOR(new Floor("1", "文明摇篮展厅", 1, R.drawable.floor_1)) {
        @Override
        public Floor addExhibits(Floor floor) {
            for (int i = 0; i < 12; i++) {
                floor.addExhibits(new Exhibits("展品" + i, "展品" + i, i + 1, i % 2 == 0 ? R.drawable.play : R.mipmap.ic_launcher_round));
            }
            return floor;
        }
    },
    SECOND_FLOOR(new Floor("2", "夏商踪迹展厅", 2, R.drawable.floor_2)) {
        @Override
        public Floor addExhibits(Floor floor) {
            for (int i = 0; i < 12; i++) {
                floor.addExhibits(new Exhibits("展品" + i, "展品" + i, i + 1, i % 2 == 0 ? R.drawable.play : R.mipmap.ic_launcher_round));
            }
            return floor;
        }
    },
    THIRD_FLOOR(new Floor("3", "晋国霸业展厅", 3, R.drawable.floor_3)) {
        @Override
        public Floor addExhibits(Floor floor) {
            for (int i = 0; i < 12; i++) {
                floor.addExhibits(new Exhibits("展品" + i, "展品" + i, i + 1, i % 2 == 0 ? R.drawable.play : R.mipmap.ic_launcher_round));
            }
            return floor;
        }
    },
    FOURTH_FLOOR(new Floor("4", "民族熔炉展厅", 4, R.drawable.floor_4)) {
        @Override
        public Floor addExhibits(Floor floor) {
            for (int i = 0; i < 12; i++) {
                floor.addExhibits(new Exhibits("展品" + i, "展品" + i, i + 1, i % 2 == 0 ? R.drawable.play : R.mipmap.ic_launcher_round));
            }
            return floor;
        }
    },
    FIFTH_FLOOR(new Floor("5", "佛风遗韵展厅", 5, R.drawable.floor_5)) {
        @Override
        public Floor addExhibits(Floor floor) {
            for (int i = 0; i < 12; i++) {
                floor.addExhibits(new Exhibits("展品" + i, "展品" + i, i + 1, i % 2 == 0 ? R.drawable.play : R.mipmap.ic_launcher_round));
            }
            return floor;
        }
    },
    SIXTH_FLOOR(new Floor("6", "戏曲故乡", 6, R.drawable.floor_6)) {
        @Override
        public Floor addExhibits(Floor floor) {
            for (int i = 0; i < 12; i++) {
                floor.addExhibits(new Exhibits("展品" + i, "展品" + i, i + 1, i % 2 == 0 ? R.drawable.play : R.mipmap.ic_launcher_round));
            }
            return floor;
        }
    },
    SEVENTH_FLOOR(new Floor("7", "明清晋商展厅", 7, R.drawable.floor_7)) {
        @Override
        public Floor addExhibits(Floor floor) {
            for (int i = 0; i < 12; i++) {
                floor.addExhibits(new Exhibits("展品" + i, "展品" + i, i + 1, i % 2 == 0 ? R.drawable.play : R.mipmap.ic_launcher_round));
            }
            return floor;
        }
    };

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

    public abstract Floor addExhibits(Floor floor);
}
