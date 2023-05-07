package com.gdou.movieshop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.graphics.Matrix;

/**
 * User profile
 */
public class CircleImageView extends ImageView {

    //画笔
    private Paint mPaint;
    //圆形图片的半径
    private int mRadius;
    //图片的缩放比例
    private float mScale;

    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //Due to its circular shape, the width and height should be consistent
        int size = Math.min(getMeasuredWidth(), getMeasuredHeight());
        mRadius = size / 2;
        setMeasuredDimension(size, size);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {

        mPaint = new Paint();

        Drawable drawable = getDrawable();

        if (null != drawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

            //Initialize BitmapShader, pass in Bitmap object
            BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            //Calculate zoom ratio
            mScale = (mRadius * 2.0f) / Math.min(bitmap.getHeight(), bitmap.getWidth());

            Matrix matrix = new Matrix();
            matrix.setScale(mScale, mScale);
            bitmapShader.setLocalMatrix(matrix);
            mPaint.setShader(bitmapShader);
            //Draw a circle, specify coordinates, radius, and brush
            canvas.drawCircle(mRadius, mRadius, mRadius, mPaint);
        } else {
            super.onDraw(canvas);
        }
    }

}
