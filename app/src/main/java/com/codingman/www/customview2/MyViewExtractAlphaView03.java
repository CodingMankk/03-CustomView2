package com.codingman.www.customview2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @function:
 */

public class MyViewExtractAlphaView extends View{

    private Paint mPaint;
    private Bitmap mBitmap;
    private Bitmap mAlphaBitmap;


    public MyViewExtractAlphaView(Context context) {
        super(context);
        init();
    }

    public MyViewExtractAlphaView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyViewExtractAlphaView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();

        //禁用硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(35);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.dog);
        mAlphaBitmap = mBitmap.extractAlpha();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制Alpha图像；
//        drawAlphaBitmap(canvas);

        drawAlphaAndBlurMaskFilterBitmap(canvas);


    }

    private void drawAlphaBitmap(Canvas canvas){
        int width = 400;
        int height =  width * mAlphaBitmap.getHeight() / mAlphaBitmap.getWidth();

        //绘制原图像
        canvas.drawBitmap(mBitmap,null,
                new Rect(10,10,width + 10,height +10),mPaint);

        //绘制alpha的图像；
        mPaint.setColor(Color.RED);
        canvas.drawBitmap(mAlphaBitmap,null,
                new Rect(10,10+height,width + 10,height*2 +10),mPaint);

        mPaint.setColor(Color.GREEN);
        canvas.drawBitmap(mAlphaBitmap,null,
                new Rect(10,10+height*2,width + 10,height*3 +10),mPaint);
    }

    //绘制alpha加内外光的图像；
    private void drawAlphaAndBlurMaskFilterBitmap(Canvas canvas){
        int width = 400;
        int height =  width * mAlphaBitmap.getHeight() / mAlphaBitmap.getWidth();


        //绘制内外光的alpha图像
        mPaint.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.NORMAL));
        mPaint.setColor(Color.RED);
        canvas.drawBitmap(mAlphaBitmap,null,
                new Rect(10,10,width,height),mPaint);

        //绘制内外光的alpha图像
        mPaint.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.NORMAL));
        mPaint.setColor(Color.GREEN);
        canvas.drawBitmap(mAlphaBitmap,null,
                new Rect(10,height+10,width,height*2),mPaint);

        //绘制原图像
        mPaint.setMaskFilter(null); //设置画笔去除内外光的效果；
        canvas.drawBitmap(mBitmap,null,
                new Rect(0,0,width,height),mPaint);
        canvas.drawBitmap(mBitmap,null,
                new Rect(0,height,width,height*2),mPaint);
        canvas.drawBitmap(mBitmap,null,
                new Rect(0,height*2,width,height*3),mPaint);


    }
}
