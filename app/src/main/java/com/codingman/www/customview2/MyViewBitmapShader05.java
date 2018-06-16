package com.codingman.www.customview2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @function:
 */

public class MyViewBitmapShader05 extends View {

    private Paint mPaint;
    private Bitmap mSrcBitmap;
    private Bitmap mUpBitmap;

    private int mDx = -1;
    private int mDy = -1;

    public MyViewBitmapShader05(Context context) {
        super(context);
        init();
    }

    public MyViewBitmapShader05(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyViewBitmapShader05(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.scenery);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDx = (int) event.getX();
                mDy = (int) event.getY();

              break;
            case MotionEvent.ACTION_MOVE:
                mDx = mDx + (int) event.getX();
                mDy = mDy + (int) event.getY();
                break;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                mDx = -1;
                mDy = -1;
                break;
            default:
                break;
        }

        mDy = (int) event.getY();
        mDx = (int) event.getX();

        postInvalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 画出遮盖图
        if (mUpBitmap == null) {
            //            mPaint.setColor(Color.DKGRAY);
            mUpBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap
                    .Config.ARGB_8888);
            Canvas upCanvas = new Canvas(mUpBitmap);
            upCanvas.drawBitmap(mSrcBitmap, null, new Rect(0, 0, getWidth(), getHeight()), mPaint);

        }
        if (mDx != -1 && mDy != -1) {
            mPaint.setShader(new BitmapShader(mUpBitmap, Shader.TileMode.REPEAT, Shader.TileMode
                    .REPEAT));
            canvas.drawCircle(mDx, mDy, 150, mPaint);
        }

    }
}
