package com.codingman.www.customview2;

import android.content.Context;
import android.content.res.TypedArray;
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

public class MyViewAlphaCunstomView04 extends View {
    private Paint mPaint;
    private Bitmap mBitmap;
    private Bitmap mAlphaBitmap;


    private int mShadowDx = 0;
    private int mShadowDy = 0;
    private int mShadowColor;
    private float mShadowRadius = 0;


    public MyViewAlphaCunstomView04(Context context, @Nullable AttributeSet attrs) throws
            Exception {
        super(context, attrs);
        init(context, attrs);
    }

    public MyViewAlphaCunstomView04(Context context, @Nullable AttributeSet attrs, int
            defStyleAttr) throws Exception {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) throws Exception {

        setLayerType(LAYER_TYPE_SOFTWARE, null);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable
                .MyViewAlphaCunstomView04);
        int id = a.getResourceId(R.styleable.MyViewAlphaCunstomView04_src, -1);
        if (id == -1) {
            throw new Exception("需要设置Src属性，须是图像");
        }

        mBitmap = BitmapFactory.decodeResource(getResources(), id);
        mShadowDx = a.getInt(R.styleable.MyViewAlphaCunstomView04_ShadowDx, 0);
        mShadowDy = a.getInt(R.styleable.MyViewAlphaCunstomView04_ShadowDy, 0);
        mShadowColor = a.getColor(R.styleable.MyViewAlphaCunstomView04_ShadowColor, 0);
        mShadowRadius = a.getFloat(R.styleable.MyViewAlphaCunstomView04_ShadowRadius, Color.BLACK);

        a.recycle();

        mPaint = new Paint();

        mAlphaBitmap = mBitmap.extractAlpha();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawAlphaAndBlurMaskFilterBitmap(canvas);
    }


    //绘制alpha加内外光的图像；
    private void drawAlphaAndBlurMaskFilterBitmap(Canvas canvas) {
        int width = getWidth() - mShadowDx;  //图片宽度和控件宽度一致，要将阴影宽度留出来；
        int height = width * mAlphaBitmap.getHeight() / mAlphaBitmap.getWidth();

        //绘制内外光的alpha图像
        mPaint.setMaskFilter(new BlurMaskFilter(mShadowRadius, BlurMaskFilter.Blur.NORMAL));
        mPaint.setColor(mShadowColor);
        canvas.drawBitmap(mAlphaBitmap, null,
                new Rect(mShadowDx, mShadowDy, width, height), mPaint);

        //绘制原图像
        mPaint.setMaskFilter(null); //设置画笔去除内外光的效果；
        canvas.drawBitmap(mBitmap, null,
                new Rect(0, 0, width, height), mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

//        int measureWidthSize = MeasureSpec.getSize(widthMeasureSpec);
//        int measureHeightSize = MeasureSpec.getSize(heightMeasureSpec);
//
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//
//        int bitmapWidth = mBitmap.getWidth();
//        int bitmapHeight = mBitmap.getHeight();
//
//        setMeasuredDimension(
//                (widthMode == MeasureSpec.EXACTLY) ? measureWidthSize + getPaddingLeft() +
//                        getPaddingRight() : bitmapWidth,
//                (heightMode == MeasureSpec.EXACTLY) ? measureHeightSize + getPaddingTop() +
//                        getPaddingBottom() : bitmapHeight );


        // 声明一个临时变量来存储计算出的测量值
        int resultWidth = 0;

        // 获取宽度测量规格中的mode
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);

        // 获取宽度测量规格中的size
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        /*
         * 如果爹心里有数
         */
        if (modeWidth == MeasureSpec.EXACTLY) {
            // 那么儿子也不要让爹难做就取爹给的大小吧
            resultWidth = mBitmap.getWidth() + getPaddingLeft() + getPaddingRight();
        }
        /*
         * 如果爹心里没数
         */
        else {
            // 那么儿子可要自己看看自己需要多大了
            resultWidth = mBitmap.getWidth() + getPaddingLeft() + getPaddingRight();

            /*
             * 如果爹给儿子的是一个限制值
             */
            if (modeWidth == MeasureSpec.AT_MOST) {
                // 那么儿子自己的需求就要跟爹的限制比比看谁小要谁
                resultWidth = Math.min(resultWidth, sizeWidth);
            }
        }

        int resultHeight = 0;
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        if (modeHeight == MeasureSpec.EXACTLY) {
            resultHeight = sizeHeight;
        } else {
            resultHeight = mBitmap.getHeight() + getPaddingTop() + getPaddingBottom();
            if (modeHeight == MeasureSpec.AT_MOST) {
                resultHeight = mBitmap.getHeight() + getPaddingTop() + getPaddingBottom();
            }
        }

        // 设置测量尺寸
        setMeasuredDimension(resultWidth, resultHeight);



    }
}
