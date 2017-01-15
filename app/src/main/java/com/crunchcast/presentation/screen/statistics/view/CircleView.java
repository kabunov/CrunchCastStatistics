package com.crunchcast.presentation.screen.statistics.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.crunchcast.R;

public class CircleView extends View {

    private final Paint mPaint;
    private final RectF mRect;

    private float mStartAngle = 0;
    private float mAngle;

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);

        int color = getColor(R.color.circle1);
        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        if (null != typedArray) {

            color = typedArray.getColor(R.styleable.CircleView_arcColor, getColor(R.color.circle1));

            typedArray.recycle();
        }

        final int strokeWidth = (int) getResources().getDimension(R.dimen.circle_inner_width);
        final int padding = (int) getResources().getDimension(R.dimen.circle_outer_border);
        final int size = (int) getResources().getDimension(R.dimen.circle_size);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(color);

        //area without inner circle
        mRect = new RectF(padding + strokeWidth / 2, padding + strokeWidth / 2, size - padding - strokeWidth / 2, size - padding - strokeWidth / 2);

        mAngle = 0;
    }

    private int getColor(int colorId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getResources().getColor(colorId, getContext().getTheme());
        } else {
            return getResources().getColor(colorId);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(mRect, mStartAngle, mAngle, false, mPaint);
    }

    public float getAngle() {

        return mAngle;
    }

    public void setStartAngle(float startAngle) {

        mStartAngle = startAngle;
    }

    public void setAngle(float angle) {
        mAngle = angle;
    }
}
