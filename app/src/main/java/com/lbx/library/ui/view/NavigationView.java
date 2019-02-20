package com.lbx.library.ui.view;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.lbx.library.R;
import com.lbx.library.bean.Exhibits;
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
    private Rect[] rects;

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

    public void setExhibits(Exhibits... exhibits) {
        int size = exhibits == null ? 0 : exhibits.length;
        mPoints = new Point[size];
        for (int i = 0; i < size; i++) {
            mPoints[i] = new Point(exhibits[i].getX(), exhibits[i].getY());
        }
        rects = new Rect[size];
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        canvas.drawPoint(point.x, point.y, paint);
        if (mPoints != null && mPoints.length > 0) {
            for (int i = 0; i < mPoints.length; i++) {
                Point point = mPoints[i];
                int left = point.x - mBitmapW / 2;
                int top = point.y - mBitmapH;
                canvas.drawBitmap(mBitmap, left, top, null);
                rects[i] = getRect(left, top);
            }
        }
    }

    @NonNull
    private Rect getRect(int left, int top) {
        return new Rect(left, top, left + mBitmapW, top + mBitmapH);
    }

    Point point = new Point();
    Paint paint = new Paint();

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if (rects != null) {
                    int x = (int) event.getX();
                    int y = (int) event.getY();
                    point.x = x;
                    point.y = y;
                    for (int i = 0; i < rects.length; i++) {
                        if (rects[i].contains(x, y)) {
                            if (mOnExhibitsLocationClickListener != null) {
                                mOnExhibitsLocationClickListener.onClick(mFloor, mFloor.getExhibitsList().get(i), i);
                            }
                            break;
                        }
                    }
                    invalidate();
                }
                break;
            default:
                break;
        }
        return true;
    }

    private OnExhibitsLocationClickListener mOnExhibitsLocationClickListener;

    public interface OnExhibitsLocationClickListener {
        void onClick(Floor floor, Exhibits exhibits, int pos);
    }

    public void setOnExhibitsLocationClickListener(OnExhibitsLocationClickListener onExhibitsLocationClickListener) {
        this.mOnExhibitsLocationClickListener = onExhibitsLocationClickListener;
    }

    @BindingAdapter("floor")
    public static void setFloor(NavigationView view, Floor floor) {
        view.setFloor(floor);
    }
}
