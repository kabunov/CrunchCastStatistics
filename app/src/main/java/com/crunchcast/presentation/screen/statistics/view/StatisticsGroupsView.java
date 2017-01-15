package com.crunchcast.presentation.screen.statistics.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.crunchcast.R;
import com.crunchcast.presentation.screen.statistics.model.StatisticsViewModel;
import com.crunchcast.presentation.screen.statistics.presenter.StatisticsPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StatisticsGroupsView extends LinearLayout {

    @BindView(R.id.header)
    TextView mHeaderView;
    @BindView(R.id.circle_text)
    TextView mCircleTextView;


    @BindView(R.id.arc1)
    CircleView mArc1View;
    @BindView(R.id.arc2)
    CircleView mArc2View;
    @BindView(R.id.label_top)
    StatisticsLabel labelTopView;
    @BindView(R.id.label_bottom)
    StatisticsLabel labelBottomView;

    @BindView(R.id.statistics_all)
    TabButton mAllView;
    @BindView(R.id.statistics_mobile)
    TabButton mMobileView;
    @BindView(R.id.statistics_wifi)
    TabButton mWifiView;


    private StatisticsGroupsViewListener mListener;

    public StatisticsGroupsView(final Context context) {
        super(context);
    }

    public StatisticsGroupsView(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StatisticsGroupsView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View.inflate(getContext(), R.layout.layout_statistics_groups, this);

        ButterKnife.bind(this);

        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.StatisticsGroupsView);
        if (null != typedArray) {

            final int okStringResource = typedArray.getResourceId(R.styleable.StatisticsGroupsView_headerText, R.string.empty);
            mHeaderView.setText(okStringResource);

            typedArray.recycle();
        }

        moveLabels();
    }

    public void setListener(StatisticsGroupsViewListener listener) {
        mListener = listener;
    }

    public void setData(StatisticsViewModel.StatisticsGroupData data) {
        mCircleTextView.setText(data.getTotalValue());

        int angle1 = Math.round(data.getFirst().getPercent() * 360f / 100);
        int angle2 = Math.round(data.getSecond().getPercent() * 360f / 100);

        final int arcAnimationDuration = 1500;
        initCircles(angle1, angle2, arcAnimationDuration);

        final int labelAnimationDuration = 1000;
        labelBottomView.setHeader(data.getFirst().getValue());
        labelBottomView.setText(data.getFirst().getTitle());
        labelBottomView.setAlpha(0f);
        labelBottomView.animate().setStartDelay(arcAnimationDuration).setDuration(labelAnimationDuration).alpha(1.0f);
        labelTopView.setHeader(data.getSecond().getValue());
        labelTopView.setText(data.getSecond().getTitle());
        labelTopView.setAlpha(0f);
        labelTopView.animate().setStartDelay(arcAnimationDuration * 2).setDuration(labelAnimationDuration).alpha(1.0f);
    }

    private void initCircles(int angle1, int angle2, int arcAnimationDuration) {

        final int edgeOffset = 15;//is needed to avoid circles overlap
        final int minAngle = 35;//if one data item is too small, show it otherwise

        mArc1View.setAngle(0);
        mArc2View.setAngle(0);
        if (angle1 < minAngle) {
            angle2 -= minAngle - angle1;
            angle1 = minAngle;
        }
        if (angle2 < minAngle) {
            angle1 -= minAngle - angle2;
            angle2 = minAngle;
        }

        mArc1View.setStartAngle(edgeOffset);
        CircleViewAngleAnimation animation = new CircleViewAngleAnimation(mArc1View, angle1 - edgeOffset * 2);
        animation.setDuration(arcAnimationDuration);
        mArc1View.startAnimation(animation);

        mArc2View.setStartAngle(angle1 + edgeOffset);
        CircleViewAngleAnimation animation2 = new CircleViewAngleAnimation(mArc2View, angle2 - edgeOffset * 2);
        animation2.setStartOffset(arcAnimationDuration);
        animation2.setDuration(arcAnimationDuration);
        mArc2View.startAnimation(animation2);
    }

    private void moveLabels() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);

        int x = metrics.widthPixels / 2 + (int) getResources().getDimension(R.dimen.circle_label_margin_from_center);

        labelBottomView.setTranslationX(x);
        labelBottomView.setTranslationY((int) getResources().getDimension(R.dimen.circle_label_bottom_y));

        labelTopView.setTranslationX(x);
        labelTopView.setTranslationY((int) getResources().getDimension(R.dimen.circle_label_top_y));
    }

    @OnClick({R.id.statistics_all, R.id.statistics_mobile, R.id.statistics_wifi})
    void onTabClick(View view) {
        if (mListener != null) {
            @StatisticsPresenter.TabType int tabType;
            switch (view.getId()) {
                case R.id.statistics_mobile:
                    tabType = StatisticsPresenter.TAB_MOBILE;
                    mAllView.setChecked(false);
                    mWifiView.setChecked(false);
                    break;
                case R.id.statistics_wifi:
                    tabType = StatisticsPresenter.TAB_WIFI;
                    mAllView.setChecked(false);
                    mMobileView.setChecked(false);
                    break;
                default:
                case R.id.statistics_all:
                    tabType = StatisticsPresenter.TAB_ALL;
                    mMobileView.setChecked(false);
                    mWifiView.setChecked(false);
                    break;
            }
            mListener.onTabSelected(tabType);
        }
    }

    public interface StatisticsGroupsViewListener {
        void onTabSelected(@StatisticsPresenter.TabType int tabType);
    }
}
