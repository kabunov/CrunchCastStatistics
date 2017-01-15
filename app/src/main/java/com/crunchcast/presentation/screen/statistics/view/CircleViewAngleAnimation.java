package com.crunchcast.presentation.screen.statistics.view;

import android.view.animation.Animation;
import android.view.animation.Transformation;

class CircleViewAngleAnimation extends Animation {

    private CircleView mCircleView;

    private float oldAngle;
    private float newAngle;

    CircleViewAngleAnimation(CircleView circleView, int newAngle) {
        this.oldAngle = circleView.getAngle();
        this.newAngle = newAngle;
        this.mCircleView = circleView;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation transformation) {
        float angle = oldAngle + ((newAngle - oldAngle) * interpolatedTime);

        mCircleView.setAngle(angle);
        mCircleView.requestLayout();
    }
}
