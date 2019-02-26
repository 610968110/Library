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
    private int x = -1, y = -1;
    private String content;
    private String videoUrl;
    private int voice = -1;
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

    public Exhibits(String id, String name, @DrawableRes int icon,
                    @DrawableRes int img, String location, String content, int x, int y) {
        this.name = name;
        this.id = id;
        this.icon = icon;
        this.img = img;
        this.location = location;
        this.content = content;
        this.x = x;
        this.y = y;
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getVoice() {
        return voice;
    }

    public void setVoice(int voice) {
        this.voice = voice;
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
        dest.writeInt(this.x);
        dest.writeInt(this.y);
        dest.writeString(this.content);
        dest.writeString(this.videoUrl);
        dest.writeInt(this.voice);
        dest.writeInt(this.img);
        dest.writeInt(this.icon);
    }

    protected Exhibits(Parcel in) {
        this.name = in.readString();
        this.id = in.readString();
        this.floor = in.readInt();
        this.location = in.readString();
        this.x = in.readInt();
        this.y = in.readInt();
        this.content = in.readString();
        this.videoUrl = in.readString();
        this.voice = in.readInt();
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
