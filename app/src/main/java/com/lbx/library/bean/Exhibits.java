package com.lbx.library.bean;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;

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
 * @date 2019/1/21.
 */

public class Exhibits implements Parcelable {

    static final int BITMAP_OFFSET = 18;
    private final String name;
    private final String id;
    private int floor;
    private String location;
    private int x = -1, y = -1;
    private String content;
    private final
    @DrawableRes
    int bigImage;
    private final
    @DrawableRes
    int smallImage;
    private String voice;
    private String video;
    private boolean isPlaying;
    private Rect rect;
    private static final int BitmapW = 50;
    private static final Context mAppContext = XTools.getApplicationContext();
    public static Bitmap NORMAL_BITMAP = XTools.BitmapUtil().zoomBmp(
            BitmapFactory.decodeResource(mAppContext.getResources(),
                    R.drawable.location_zp), BitmapW);
    public static Bitmap PLAYING_BITMAP = XTools.BitmapUtil().zoomBmp(
            BitmapFactory.decodeResource(mAppContext.getResources(),
                    R.drawable.location_zp_playing), BitmapW + BITMAP_OFFSET);

    public static int getBitmapW() {
        return BitmapW;
    }

    public static int getBitmapH() {
        return NORMAL_BITMAP.getHeight();
    }

    public Bitmap getCurrentBitmap() {
        return isPlaying() ? PLAYING_BITMAP : NORMAL_BITMAP;
    }

    public Exhibits(String id, String name, @DrawableRes int smallImage,
                    @DrawableRes int bigImage, String location, String content) {
        this.name = name;
        this.id = id;
        this.smallImage = smallImage;
        this.bigImage = bigImage;
        this.location = location;
        this.content = content;
    }

    public Exhibits(String id, String name, @DrawableRes int smallImage,
                    @DrawableRes int bigImage, String location, String content, int x, int y) {
        this.name = name;
        this.id = id;
        this.smallImage = smallImage;
        this.bigImage = bigImage;
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

    public int getBigImage() {
        return bigImage;
    }

    public String getLocation() {
        return location;
    }

    public String getVideoUrl() {
        return video;
    }

    public int getSmallImage() {
        return smallImage;
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

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
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
        dest.writeInt(this.bigImage);
        dest.writeInt(this.smallImage);
        dest.writeString(this.voice);
        dest.writeString(this.video);
        dest.writeByte(this.isPlaying ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.rect, flags);
    }

    protected Exhibits(Parcel in) {
        this.name = in.readString();
        this.id = in.readString();
        this.floor = in.readInt();
        this.location = in.readString();
        this.x = in.readInt();
        this.y = in.readInt();
        this.content = in.readString();
        this.bigImage = in.readInt();
        this.smallImage = in.readInt();
        this.voice = in.readString();
        this.video = in.readString();
        this.isPlaying = in.readByte() != 0;
        this.rect = in.readParcelable(Rect.class.getClassLoader());
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
