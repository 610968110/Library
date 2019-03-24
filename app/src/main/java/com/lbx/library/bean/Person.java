package com.lbx.library.bean;

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

public class Person extends Exhibits {

    private static final Bitmap USER_NORMAL_BITMAP = XTools.BitmapUtil().zoomBmp(
            BitmapFactory.decodeResource(XTools.getApplicationContext().getResources(),
                    R.drawable.location_friend), getBitmapW());
    private static final Bitmap USER_GUIDING_BITMAP = XTools.BitmapUtil().zoomBmp(
            BitmapFactory.decodeResource(XTools.getApplicationContext().getResources(),
                    R.drawable.location_friend_guiding), getBitmapW());
    private static final Bitmap USER_MINE_BITMAP = XTools.BitmapUtil().zoomBmp(
            BitmapFactory.decodeResource(XTools.getApplicationContext().getResources(),
                    R.drawable.location_mine), getBitmapW());
    private boolean isMine;

    private Person(String id, String name) {
        super(id, name, 0, 0, "", "");
    }

    public Person(String id, String name, int x, int y) {
        super(id, name, 0, 0, "", "", x, y);
    }

    public Person(String id, String name, int x, int y, boolean isMine) {
        super(id, name, 0, 0, "", "", x, y);
        this.isMine = isMine;
    }

    @Override
    public Bitmap getCurrentBitmap() {
        return isMine ? USER_MINE_BITMAP : isGuiding() ? USER_GUIDING_BITMAP : USER_NORMAL_BITMAP;
    }

    @Override
    public boolean isPlaying() {
        return isGuiding();
    }

    public boolean isGuiding() {
        return false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeByte(this.isMine ? (byte) 1 : (byte) 0);
    }

    protected Person(Parcel in) {
        super(in);
        this.isMine = in.readByte() != 0;
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}
