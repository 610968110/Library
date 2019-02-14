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
    private int floor;
    private String location;
    private String content;
    private String videoUrl;
    private final
    @DrawableRes
    int img;
    @DrawableRes
    int icon;

    public Exhibits(String id, String name, @DrawableRes int icon,
                    @DrawableRes int img, String location, String content) {
        this.name = name;
        this.id = id;
        this.icon = icon;
        this.img = img;
        this.location = location;
        this.content = content;
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

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getImg() {
        return img;
    }

    public String getLocation() {
        return location;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public int getIcon() {
        return icon;
    }

    public String getContent() {
        return content;
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
        dest.writeString(this.location);
        dest.writeString(this.content);
        dest.writeString(this.videoUrl);
        dest.writeInt(this.img);
        dest.writeInt(this.icon);
    }

    protected Exhibits(Parcel in) {
        this.name = in.readString();
        this.id = in.readString();
        this.floor = in.readInt();
        this.location = in.readString();
        this.content = in.readString();
        this.videoUrl = in.readString();
        this.img = in.readInt();
        this.icon = in.readInt();
    }

    public static final Creator<Exhibits> CREATOR = new Creator<Exhibits>() {
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
