package com.crunchcast.presentation.screen.statistics.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.crunchcast.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StatisticsLabel extends LinearLayout {

    @BindView(R.id.header)
    TextView mHeaderView;
    @BindView(R.id.text)
    TextView mTextView;

    public StatisticsLabel(final Context context) {
        super(context);
    }

    public StatisticsLabel(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StatisticsLabel(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        int direction = 0;
        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.StatisticsLabelView);
        if (null != typedArray) {

            direction = typedArray.getInt(R.styleable.StatisticsLabelView_direction, 0);

            typedArray.recycle();
        }

        View.inflate(getContext(), direction == 0 ? R.layout.layout_statistics_label_top : R.layout.layout_statistics_label_bottom, this);

        ButterKnife.bind(this);

    }

    public void setHeader(String value) {
        mHeaderView.setText(value);
    }

    public void setText(String value) {
        mTextView.setText(value);
    }
}
