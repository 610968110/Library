package com.lbx.library.ui.view;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.lbx.library.R;
import com.lbx.library.bean.Floor;

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

public class NavigationView extends android.support.v7.widget.AppCompatImageView {

    private Floor mFloor;
    private Bitmap mBitmap;
    private Point[] mPoints;
    private int mBitmapW = 50;
    private int mBitmapH;

    public NavigationView(@NonNull Context context) {
        this(context, null);
    }

    public NavigationView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavigationView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.location_zp);
        mBitmap = XTools.BitmapUtil().zoomBmp(bitmap, mBitmapW);
        mBitmapH = mBitmap.getHeight();
    }

    public void setFloor(Floor floor) {
        mFloor = floor;
        if (floor != null) {
            int img = floor.getImg();
            setImageResource(img);
        }
    }

    public void setLocations(Point... points) {
        mPoints = points;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mPoints != null && mPoints.length > 0) {
            for (Point point : mPoints) {
                canvas.drawBitmap(mBitmap, point.x - mBitmapW / 2, point.y - mBitmapH, null);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @BindingAdapter("floor")
    public static void setFloor(NavigationView view, Floor floor) {
        view.setFloor(floor);
    }
}
