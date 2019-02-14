package com.lbx.library.type;

import android.os.Environment;

import com.lbx.library.R;
import com.lbx.library.bean.Exhibits;
import com.lbx.library.bean.Floor;

import java.io.File;
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
            floor.addExhibits(new Exhibits("玻璃碗", "玻璃碗", R.drawable.boliwan_f, R.drawable.boliwan, "北魏",
                    "高7.5厘米，口径10.3厘米。1988年大同市南郊出土。波斯萨珊王朝饮器。外壁磨饰出纵横排列有序的椭圆形凹面，圜底，饰7个较大的圆形凹面。"));
            floor.addExhibits(new Exhibits("程哲碑", "程哲碑", R.drawable.chengzhebei_f, R.drawable.chengzhebei, "东魏",
                    "高120厘米，宽68厘米，厚25厘米。长治市袁家漏村征集。碑圆首，碑阳正中圆拱龛内浮雕释迦坐像，方额隆颐，身形略显瘦削，厚重的袈裟下摆长长地披覆于座前。龛之内外线刻飞天、胁侍、讲经说法图及护法狮等，线条刚柔相济，飘逸飞动。碑额有东魏天平元年（534）的题记。碑阴刊有程哲发愿文，记叙程氏家族的源流功勋。这一年，北魏初分东、西，故造像中北魏晚期的余韵仍相当浓重。此碑应是带有供养佛像意味的墓碑或墓志。"));
            return floor;
        }
    },
    SECOND_FLOOR(new Floor("2", "夏商踪迹展厅", 2, R.drawable.floor_2)) {
        @Override
        public Floor addExhibits(Floor floor) {
            Exhibits exhibits = new Exhibits("展品", "展品", R.mipmap.ic_launcher_round, R.mipmap.ic_launcher, "a", "b");
            String path = Environment.getExternalStorageDirectory().getPath() + File.separator + "video.mp4";
            exhibits.setVideoUrl(path);
            floor.addExhibits(exhibits);
            return floor;
        }
    },
    THIRD_FLOOR(new Floor("3", "晋国霸业展厅", 3, R.drawable.floor_3)) {
        @Override
        public Floor addExhibits(Floor floor) {
//            for (int i = 0; i < 12; i++) {
//                floor.addExhibits(new Exhibits("展品" + i, "展品" + i, i + 1, i % 2 == 0 ? R.drawable.play : R.mipmap.ic_launcher_round));
//            }
            return floor;
        }
    },
    FOURTH_FLOOR(new Floor("4", "民族熔炉展厅", 4, R.drawable.floor_4)) {
        @Override
        public Floor addExhibits(Floor floor) {
//            for (int i = 0; i < 12; i++) {
//                floor.addExhibits(new Exhibits("展品" + i, "展品" + i, i + 1, i % 2 == 0 ? R.drawable.play : R.mipmap.ic_launcher_round));
//            }
            return floor;
        }
    },
    FIFTH_FLOOR(new Floor("5", "佛风遗韵展厅", 5, R.drawable.floor_5)) {
        @Override
        public Floor addExhibits(Floor floor) {
//            for (int i = 0; i < 12; i++) {
//                floor.addExhibits(new Exhibits("展品" + i, "展品" + i, i + 1, i % 2 == 0 ? R.drawable.play : R.mipmap.ic_launcher_round));
//            }
            return floor;
        }
    },
    SIXTH_FLOOR(new Floor("6", "戏曲故乡", 6, R.drawable.floor_6)) {
        @Override
        public Floor addExhibits(Floor floor) {
//            for (int i = 0; i < 12; i++) {
//                floor.addExhibits(new Exhibits("展品" + i, "展品" + i, i + 1, i % 2 == 0 ? R.drawable.play : R.mipmap.ic_launcher_round));
//            }
            return floor;
        }
    },
    SEVENTH_FLOOR(new Floor("7", "明清晋商展厅", 7, R.drawable.floor_7)) {
        @Override
        public Floor addExhibits(Floor floor) {
//            for (int i = 0; i < 12; i++) {
//                floor.addExhibits(new Exhibits("展品" + i, "展品" + i, i + 1, i % 2 == 0 ? R.drawable.play : R.mipmap.ic_launcher_round));
//            }
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
