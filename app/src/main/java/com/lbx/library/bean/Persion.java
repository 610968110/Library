package com.lbx.library.bean;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;

import com.lbx.library.R;

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
 * @date 2019/3/24.
 */

public class Persion extends Exhibits {

    private static final Context mAPpContext = XTools.getApplicationContext();
    private static final Bitmap USER_NORMAL_BITMAP = XTools.BitmapUtil().zoomBmp(
            BitmapFactory.decodeResource(mAPpContext.getResources(),
                    R.drawable.location_friend), getBitmapW());

    public Persion(String id, String name) {
        super(id, name, 0, 0, "", "");
    }

    public Persion(String id, String name, int x, int y) {
        super(id, name, 0, 0, "", "", x, y);
    }

    protected Persion(Parcel in) {
        super(in);
    }
}
