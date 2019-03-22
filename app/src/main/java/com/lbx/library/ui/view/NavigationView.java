package com.lbx.library.ui.view;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Looper;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.lbx.library.R;
import com.lbx.library.bean.Exhibits;
import com.lbx.library.bean.Floor;

import java.io.IOException;

import lbx.xtoollib.XTools;
import lbx.xtoollib.phone.xLogUtil;

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

public class NavigationView extends View {

    private Floor mFloor;
    private Bitmap mBitmap, mPlayingBitmap, mUserBitmap;
    private int mBitmapW = 50;
    private int mBitmapH;
    private Context mContext;
    private Exhibits[] exhibits;
    private boolean invide;
    BitmapRegionDecoder mBitmapRegionDecoder;
    private Rect mRect;
    private float mScale;
    private int mBmpW;

    public NavigationView(@NonNull Context context) {
        this(context, null);
    }

    public NavigationView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavigationView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mBitmap = XTools.BitmapUtil().zoomBmp(
                BitmapFactory.decodeResource(context.getResources(),
                        R.drawable.location_zp), mBitmapW);
        mPlayingBitmap = XTools.BitmapUtil().zoomBmp(
                BitmapFactory.decodeResource(context.getResources(),
                        R.drawable.location_zp_playing), mBitmapW + 18);
        mUserBitmap = XTools.BitmapUtil().zoomBmp(
                BitmapFactory.decodeResource(context.getResources(),
                        R.drawable.location_friend), mBitmapW);
        mBitmapH = mBitmap.getHeight();
        mRect = new Rect();
    }

    public void setFloor(Floor floor) {
        mFloor = floor;
        if (floor != null) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(getResources(), floor.getBigImg(), options);
            mBmpW = options.outWidth;
            int bmpH = options.outHeight;
            Looper.myQueue().addIdleHandler(() -> {
                mScale = getMeasuredHeight() * 1.0f / bmpH;
                xLogUtil.e("mScale:" + mScale);
                mRect.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
                try {
                    mBitmapRegionDecoder = BitmapRegionDecoder.newInstance(
                            getResources().openRawResource(floor.getBigImg()), false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                invide = true;
                invalidate();
                return false;
            });
        }
    }

    public void setExhibits(Exhibits... exhibits) {
        this.exhibits = exhibits;
        int size = exhibits == null ? 0 : exhibits.length;
        for (int i = 0; i < size; i++) {
            exhibits[i].setPoint(new Point(exhibits[i].getX(), exhibits[i].getY()));
        }
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!invide) {
            return;
        }
        Bitmap bitmap = mBitmapRegionDecoder.decodeRegion(mRect, null);
        canvas.drawBitmap(bitmap, 0, 0, null);
        if (exhibits != null && exhibits.length > 0) {
            for (Exhibits exhibit : exhibits) {
                Point point = exhibit.getPoint();
                int left = point.x - mBitmapW / 2;
                int top = point.y - mBitmapH;
                canvas.drawBitmap(exhibit.isPlaying() ? mPlayingBitmap : mBitmap, left, top, null);
                exhibit.setRect(getRect(left, top));
            }
        }
    }

    @NonNull
    private Rect getRect(int left, int top) {
        return new Rect(left, top, left + mBitmapW, top + mBitmapH);
    }

    private boolean isMove;
    private int downX, downY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) event.getRawX();
                downY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = downX - (int) event.getRawX();
                int dy = downY - (int) event.getRawY();
                if (Math.abs(dx) >= 10) {
                    isMove = true;
                    if (mRect.left < -100) {
                        xLogUtil.e("超出l边界");
                        mRect.left = -100;
                        mRect.right = mRect.left + getMeasuredWidth();
                    }
                    if (mRect.right > mBmpW * mScale) {
                        xLogUtil.e("超出r边界");
                        mRect.right = (int) (mBmpW * mScale) ;
                        mRect.left = mRect.right - getMeasuredWidth();
                    }
                    mRect.offset(dx, 0);
                    invalidate();
                } else if (Math.abs(dy) >= 10) {
                    isMove = true;
                }
                downX = (int) event.getRawX();
                downY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                //如果是点击
                if (exhibits != null && !isMove) {
                    int x = (int) event.getX();
                    int y = (int) event.getY();
                    for (int i = 0; i < exhibits.length; i++) {
                        if (exhibits[i].getRect().contains(x, y)) {
                            if (mOnExhibitsLocationClickListener != null) {
                                mOnExhibitsLocationClickListener.onClick(mFloor, mFloor.getExhibitsList().get(i), i);
                            }
                            break;
                        }
                    }
                    invalidate();
                }
                isMove = false;
                break;
            default:
                break;
        }
        return true;
    }

    public void refreshImageState() {
        invalidate();
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
