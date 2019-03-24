package com.lbx.library.ui.view;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Looper;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.lbx.library.Config;
import com.lbx.library.bean.Exhibits;
import com.lbx.library.bean.Floor;
import com.lbx.library.bean.Person;

import java.io.IOException;

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
    private Context mContext;
    private Exhibits[] exhibits;
    private boolean invide;
    BitmapRegionDecoder mBitmapRegionDecoder;
    private Rect mRect;
    private int mBmpW;
    private int mBmpH;
    /**
     * 如果View大小大于图片高度，Y不可滚动
     */
    private boolean scrollableY;
    private boolean isGuide;
    private GuidePaint mGuidePaint;
    private int mCanvasOffsetY;
    private Person[] mPseson;

    public NavigationView(@NonNull Context context) {
        this(context, null);
    }

    public NavigationView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavigationView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mRect = new Rect();
        mGuidePaint = new GuidePaint(context);
    }

    public void setFloor(Floor floor) {
        mFloor = floor;
        if (floor != null) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(getResources(), floor.getBigImg(), options);
            mBmpW = options.outWidth;
            mBmpH = options.outHeight;
            Looper.myQueue().addIdleHandler(() -> {
                mRect.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
                scrollableY = mBmpH > getMeasuredHeight();
                mCanvasOffsetY = scrollableY ? 0 : getMeasuredHeight() / 2 - mBmpH / 2;
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
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!invide) {
            return;
        }
        int save = -1;
        if (!scrollableY) {
            save = canvas.save();
            //使图片Y居中
            canvas.translate(0, mCanvasOffsetY);
        }
        drawBg(canvas);
        drawExhibits(canvas);
        drawPerson(canvas);
        if (isGuide) {
            drawTestGuide(canvas);
        }
        if (save != -1) {
            canvas.restoreToCount(save);
        }
    }

    private void drawPerson(Canvas canvas) {
        if (mPseson != null && mPseson.length > 0) {
            for (Person person : mPseson) {
                int left = person.getX() - Exhibits.getBitmapW() / 2 - mRect.left;
                int top = person.getY() - Exhibits.getBitmapH() - mRect.top;
                canvas.drawBitmap(person.getCurrentBitmap(), left, top, null);
                person.setRect(getRect(left, top));
            }
        }
    }

    private void drawBg(Canvas canvas) {
        Bitmap bitmap = mBitmapRegionDecoder.decodeRegion(mRect, null);
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, 0, 0, null);
        }
    }

    private void drawExhibits(Canvas canvas) {
        if (exhibits != null && exhibits.length > 0) {
            for (Exhibits exhibit : exhibits) {
                int left = exhibit.getX() - Exhibits.getBitmapW() / 2 - mRect.left;
                int top = exhibit.getY() - Exhibits.getBitmapH() - mRect.top;
                canvas.drawBitmap(exhibit.getCurrentBitmap(), left, top, null);
                exhibit.setRect(getRect(left, top));
            }
        }
    }

    @NonNull
    private Rect getRect(int left, int top) {
        return new Rect(left, top, left + Exhibits.getBitmapW(), top + Exhibits.getBitmapH());
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
                    mRect.offset(dx, 0);
                    if (mRect.left < 0) {
                        xLogUtil.e("超出l边界");
                        mRect.left = 0;
                        mRect.right = mRect.left + getMeasuredWidth();
                    }
                    if (mRect.right > mBmpW) {
                        xLogUtil.e("超出r边界");
                        mRect.right = mBmpW;
                        mRect.left = mRect.right - getMeasuredWidth();
                    }
                }
                if (Math.abs(dy) >= 10 && scrollableY) {
                    isMove = true;
                    mRect.offset(0, dy);
                    if (mRect.top < 0) {
                        xLogUtil.e("超出t边界");
                        mRect.top = 0;
                        mRect.bottom = mRect.top + getMeasuredHeight();
                    }
                    if (mRect.bottom > mBmpH) {
                        xLogUtil.e("超出b边界");
                        mRect.bottom = mBmpH - 10;
                        mRect.top = mRect.bottom - getMeasuredHeight();
                    }
                }
                invalidate();
                downX = (int) event.getRawX();
                downY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                //如果是点击
                if (exhibits != null && !isMove) {
                    int x = (int) event.getX();
                    int y = (int) event.getY() - mCanvasOffsetY;
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

    public void testGuide(boolean isGuide) {
        this.isGuide = isGuide;
        invalidate();
    }

    private void drawTestGuide(Canvas canvas) {
        try {
            int tempY = 740;
            int startX = Config.getMine().getX();
            int startY = Config.getMine().getY();
            int stopX = exhibits[1].getX();
            int stopY = exhibits[1].getY();
            mGuidePaint.changeBottom();
            canvas.drawLine(startX - mRect.left, startY - mRect.top,
                    startX - mRect.left, tempY - mRect.top, mGuidePaint);
            mGuidePaint.changeRight();
            canvas.drawLine(startX - mRect.left, tempY - mRect.top,
                    stopX - mRect.left, tempY - mRect.top, mGuidePaint);
            mGuidePaint.changeTop();
            canvas.drawLine(stopX - mRect.left, tempY - mRect.top,
                    stopX - mRect.left, stopY - mRect.top, mGuidePaint);
        } catch (Exception ignored) {
        }
    }

    public void refreshImageState() {
        invalidate();
    }

    private OnExhibitsLocationClickListener mOnExhibitsLocationClickListener;

    public void setPerson(Person... person) {
        mPseson = person;
    }

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
