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
import android.os.Looper;
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
    private Bitmap mBitmap, mPlayingBitmap, mUserBitmap;
    private int mBitmapW = 50;
    private int mBitmapH;
    private Context mContext;
    private Exhibits[] exhibits;

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
    }

    public void setFloor(Floor floor) {
        mFloor = floor;
        if (floor != null) {
            int img = floor.getImg();
            Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), img);
            Looper.myQueue().addIdleHandler(() -> {
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
//                ViewGroup.LayoutParams layoutParams = getLayoutParams();
//                layoutParams.width = width;
//                layoutParams.height = height;
                setImageBitmap(bitmap);
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
        super.onDraw(canvas);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        canvas.drawPoint(point.x, point.y, paint);
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

    Point point = new Point();
    Paint paint = new Paint();

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if (exhibits != null) {
                    int x = (int) event.getX();
                    int y = (int) event.getY();
                    point.x = x;
                    point.y = y;
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
