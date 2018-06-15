package com.codingman.www.customview2;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @function:
 */

public class MyViewMaskFilter extends View {

    private Paint mPaint;


    public MyViewMaskFilter(Context context) {
        super(context);
        init();
    }

    public MyViewMaskFilter(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyViewMaskFilter(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //内发光
        mPaint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.INNER));
        canvas.drawCircle(120, 100, 50, mPaint);
        //仅显示发光效果
        mPaint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.OUTER));
        canvas.drawCircle(120, 300, 50, mPaint);
        //内外发光
        mPaint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.NORMAL));
        canvas.drawCircle(120, 500, 50, mPaint);
        //外发光
        mPaint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.SOLID));
        canvas.drawCircle(120, 700, 50, mPaint);

        //外发光
        /**
         *
         * direction 是一个含有三个float元素的数组，对应x、y、z三个方向上的值；用于指定光源方向

         ambient 环境光的因子 （0~1），0~1表示从暗到亮

         specular 镜面反射系数，越接近0，反射光越强

         blurRadius 模糊半径，值越大，模糊效果越明显
         */
        mPaint.setColor(Color.BLACK);
        mPaint.setMaskFilter(new EmbossMaskFilter(new float[]{100,100,100},1,0,50));
        canvas.drawCircle(120, 900, 50, mPaint);

        mPaint.reset();
        mPaint.setMaskFilter(new EmbossMaskFilter(new float[]{0,50,0},1,0,50));
        mPaint.setTextSize(100);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawText("中国欢迎您",100,1000,mPaint);
    }
}
