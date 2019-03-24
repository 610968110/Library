package com.lbx.library.ui.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Paint;
import android.graphics.Shader;

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
 * @date 2019/3/24.
 */

public class GuidePaint extends Paint {

    private BitmapShader mBottomShader, mRightShader, mTopShader, mLeftShader;
    private Context mContext;

    public GuidePaint(Context context) {
        super(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mContext = context;
        setStrokeWidth(16);
        setStrokeCap(Paint.Cap.ROUND);
        Resources resources = context.getResources();
        Bitmap shaderBitmapB = XTools.BitmapUtil().zoomBmp(
                BitmapFactory.decodeResource(resources, R.drawable.path_b), getStrokeWidth());
        mBottomShader = new BitmapShader(shaderBitmapB, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        Bitmap shaderBitmapR = XTools.BitmapUtil().zoomBmp(
                BitmapFactory.decodeResource(resources, R.drawable.path_r), getStrokeWidth());
        mRightShader = new BitmapShader(shaderBitmapR, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        mBottomShader = new BitmapShader(shaderBitmapB, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        Bitmap shaderBitmapT = XTools.BitmapUtil().zoomBmp(
                BitmapFactory.decodeResource(resources, R.drawable.path_t), getStrokeWidth());
        mTopShader = new BitmapShader(shaderBitmapT, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        Bitmap shaderBitmapL = XTools.BitmapUtil().zoomBmp(
                BitmapFactory.decodeResource(resources, R.drawable.path_l), getStrokeWidth());
        mLeftShader = new BitmapShader(shaderBitmapL, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
    }

    public void changeLeft() {
        setShader(mLeftShader);
    }

    public void changeTop() {
        setShader(mTopShader);
    }

    public void changeRight() {
        setShader(mRightShader);
    }

    public void changeBottom() {
        setShader(mBottomShader);
    }
}
