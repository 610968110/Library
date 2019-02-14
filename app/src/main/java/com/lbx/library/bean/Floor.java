package com.lbx.library.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;

import java.util.ArrayList;

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

public class Floor implements Parcelable {

    private final String name;
    private final String id;
    private final int floor;
    /**
     * 展品列表
     */
    private ArrayList<Exhibits> mExhibitsList;
    private final
    @DrawableRes
    int img;

    public Floor(String id, String name, int floor, int img) {
        this.id = id;
        this.name = name;
        this.floor = floor;
        this.img = img;
        mExhibitsList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Exhibits> addExhibits(Exhibits exhibits) {
        exhibits.setFloor(floor);
        mExhibitsList.add(exhibits);
        return mExhibitsList;
    }

    public ArrayList<Exhibits> clearExhibits() {
        mExhibitsList.clear();
        return mExhibitsList;
    }

    public ArrayList<Exhibits> getExhibitsList() {
        return mExhibitsList;
    }

    public int getImg() {
        return img;
    }

    public String getId() {
        return id;
    }

    public int getFloor() {
        return floor;
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
        dest.writeTypedList(this.mExhibitsList);
        dest.writeInt(this.img);
    }

    protected Floor(Parcel in) {
        this.name = in.readString();
        this.id = in.readString();
        this.floor = in.readInt();
        this.mExhibitsList = in.createTypedArrayList(Exhibits.CREATOR);
        this.img = in.readInt();
    }

    public static final Creator<Floor> CREATOR = new Creator<Floor>() {
        @Override
        public Floor createFromParcel(Parcel source) {
            return new Floor(source);
        }

        @Override
        public Floor[] newArray(int size) {
            return new Floor[size];
        }
    };
}
