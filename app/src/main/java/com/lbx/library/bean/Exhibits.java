package com.lbx.library.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;

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

public class Exhibits implements Parcelable {

    private final String name;
    private final String id;
    private final int floor;
    private final
    @DrawableRes
    int img;

    public Exhibits(String id, String name, int floor, int img) {
        this.name = name;
        this.id = id;
        this.floor = floor;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getFloor() {
        return floor;
    }

    public int getImg() {
        return img;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.id);
        dest.writeInt(this.floor);
        dest.writeInt(this.img);
    }

    protected Exhibits(Parcel in) {
        this.name = in.readString();
        this.id = in.readString();
        this.floor = in.readInt();
        this.img = in.readInt();
    }

    public static final Parcelable.Creator<Exhibits> CREATOR = new Parcelable.Creator<Exhibits>() {
        @Override
        public Exhibits createFromParcel(Parcel source) {
            return new Exhibits(source);
        }

        @Override
        public Exhibits[] newArray(int size) {
            return new Exhibits[size];
        }
    };
}
