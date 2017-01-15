package com.crunchcast.presentation.screen.statistics.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.crunchcast.R;
import com.crunchcast.presentation.application.App;
import com.crunchcast.presentation.screen.statistics.model.StatisticsViewModel;
import com.crunchcast.presentation.screen.statistics.presenter.StatisticsPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StatisticsActivity extends AppCompatActivity implements StatisticsView {

    @Inject
    StatisticsPresenter mPresenter;

    @BindView(R.id.progress)
    View mProgressView;
    @BindView(R.id.content)
    View mContentView;

    @BindView(R.id.views)
    TextView mViewsView;
    @BindView(R.id.duration)
    TextView mDurationView;
    @BindView(R.id.uploads)
    TextView mUploadsView;

    @BindView(R.id.savings_group)
    StatisticsGroupsView mSavingsView;
    @BindView(R.id.usage_group)
    StatisticsGroupsView mUsageView;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, StatisticsActivity.class);
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ButterKnife.bind(this);

        App.getAppComponent().injectStatisticsActivity(this);

        mPresenter.setView(this);

        if (savedInstanceState == null) {
            loadStatistics();
        }

        mSavingsView.setListener(tabType -> mPresenter.getStatisticsGroupData(StatisticsPresenter.GROUP_SAVINGS, tabType));
        mUsageView.setListener(tabType -> mPresenter.getStatisticsGroupData(StatisticsPresenter.GROUP_USAGE, tabType));
    }

    private void loadStatistics() {
        mPresenter.loadStatistics();
    }

    @Override
    public void toggleLoading(boolean showLoading) {
        mProgressView.setVisibility(showLoading ? View.VISIBLE : View.GONE);
        mContentView.setVisibility(showLoading ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showCommonData(StatisticsViewModel.StatisticsCommon commonData) {
        mViewsView.setText(commonData.getViews());
        mDurationView.setText(commonData.getDuration());
        mUploadsView.setText(commonData.getUploads());
    }

    @Override
    public void showSavingsData(StatisticsViewModel.StatisticsGroupData data) {
        mSavingsView.setData(data);
    }

    @Override
    public void showUsageData(StatisticsViewModel.StatisticsGroupData data) {
        mUsageView.setData(data);
    }

    @Override
    public void showFetchingDataError() {
        Toast.makeText(this, R.string.statistics_fetching_data_error, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
    }
}
