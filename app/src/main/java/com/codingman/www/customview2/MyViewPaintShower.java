package com.codingman.www.customview2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

/**
 * @function:
 */

public class MyViewPaintShower extends View {

    private Paint mPaint;
    private Path mPath;

    private int dx = 10;
    private int dy = 10;
    private int radius = 1;
    private int offset =5;

    private boolean isShadow = false;

    public MyViewPaintShower(Context context) {
        super(context);
        init();
    }

    public MyViewPaintShower(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyViewPaintShower(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPath = new Path();

        //禁用硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE,null);

        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(35);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int i =0;
        i++;

        Toast.makeText(getContext(),"onDraw:"+ i, Toast.LENGTH_SHORT).show();


        if (isShadow){
            //为画笔设置阴影的部分
            mPaint.setShadowLayer(radius, dx, dy, Color.RED);
        }else{
            mPaint.clearShadowLayer();
        }


        mPaint.setColor(Color.RED);
        String s = "中国欢迎你！！！";
        canvas.drawText(s, 10, 80, mPaint);

        mPaint.setColor(Color.GREEN);
        canvas.drawCircle(120, 150, 50, mPaint);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_launcher_round);

        canvas.drawBitmap(bitmap, null, new Rect(10, 250,
                bitmap.getWidth() + 10, bitmap.getHeight() + 250), mPaint);

        super.onDraw(canvas);

    }

    public void ChangeDx(){
        dx += offset;
        postInvalidate();
    }

    public void ChangeDy(){
        dy += offset;
        postInvalidate();
    }

    public void changeRadius(){
        radius +=1;
        postInvalidate();

    }

    public void clearShadow1(){
        isShadow = false;
        postInvalidate();
    }

    public void clearShadow2(){
        radius =0;
        postInvalidate();
    }

    public void addShadow(){
        isShadow = true;
        radius = 1;
        postInvalidate();
    }
}
