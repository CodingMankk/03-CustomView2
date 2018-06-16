package com.codingman.www.customview2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @function:
 */

public class MyViewCirclePic06 extends View {
    private Paint mPaint;
    private Bitmap mSrcBitmap;
    private BitmapShader mBitmapShader;

    public MyViewCirclePic06(Context context) {
        super(context);
        init();
    }

    public MyViewCirclePic06(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyViewCirclePic06(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

     private  void init(){
         mPaint = new Paint();
         mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.avator);
         mBitmapShader = new BitmapShader(mSrcBitmap, Shader.TileMode.CLAMP,
                 Shader.TileMode.CLAMP);
     }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //设置缩放
        Matrix matrix = new Matrix();
        int scale = getWidth() / mSrcBitmap.getWidth();
        matrix.setScale(scale,scale);
        mBitmapShader.setLocalMatrix(matrix);
        //画笔设置
        mPaint.setShader(mBitmapShader);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.CYAN);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(1);

        float half = getWidth() /2;
        canvas.drawCircle(half,half,getWidth()/2,mPaint);
//        canvas.drawRect(half,half,getWidth()/2,getHeight(),mPaint);

//        canvas.drawRoundRect(new RectF(0,0,getWidth(),getHeight()),50,50,mPaint);
    }
}

