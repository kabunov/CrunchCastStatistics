package com.crunchcast.presentation.screen.statistics.presenter;

import android.support.annotation.IntDef;

import com.crunchcast.domain.entity.Statistics;
import com.crunchcast.domain.interactor.GetStatisticsInteractor;
import com.crunchcast.presentation.screen.statistics.model.StatisticsConverter;
import com.crunchcast.presentation.screen.statistics.model.StatisticsViewModel;
import com.crunchcast.presentation.screen.statistics.view.StatisticsView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Inject;

import rx.Subscriber;

public class StatisticsPresenter {

    private StatisticsView mView;
    private GetStatisticsInteractor mGetStatisticsInteractor;

    private StatisticsViewModel mViewModel;

    @Inject
    public StatisticsPresenter(GetStatisticsInteractor getStatisticsInteractor) {
        mGetStatisticsInteractor = getStatisticsInteractor;
    }

    public void setView(StatisticsView view) {
        mView = view;
    }

    public void loadStatistics() {
        mView.toggleLoading(true);
        mGetStatisticsInteractor.execute(mSubscriber);
    }

    private Subscriber<Statistics> mSubscriber = new Subscriber<Statistics>() {
        @Override
        public void onCompleted() {
            mView.toggleLoading(false);
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
            mView.showFetchingDataError();
        }

        @Override
        public void onNext(Statistics statistics) {

            mViewModel = StatisticsConverter.convert(statistics);

            mView.showCommonData(mViewModel.getCommonData());
            mView.showSavingsData(mViewModel.getAllData().getSavingsData());
            mView.showUsageData(mViewModel.getAllData().getUsageData());
        }
    };

    public void getStatisticsGroupData(@GroupType int groupType, @TabType int tabType) {
        StatisticsViewModel.StatisticsGroupData data = null;

        switch (groupType) {
            case GROUP_SAVINGS:
                switch (tabType) {
                    case TAB_ALL:
                        data = mViewModel.getAllData().getSavingsData();
                        break;
                    case TAB_MOBILE:
                        data = mViewModel.getMobileData().getSavingsData();
                        break;
                    case TAB_WIFI:
                        data = mViewModel.getWifiData().getSavingsData();
                        break;
                }
                mView.showSavingsData(data);
                break;
            case GROUP_USAGE:
                switch (tabType) {
                    case TAB_ALL:
                        data = mViewModel.getAllData().getUsageData();
                        break;
                    case TAB_MOBILE:
                        data = mViewModel.getMobileData().getUsageData();
                        break;
                    case TAB_WIFI:
                        data = mViewModel.getWifiData().getUsageData();
                        break;
                }
                mView.showUsageData(data);
                break;
        }
    }

    public void destroy() {
        mGetStatisticsInteractor.unsubscribe();
        mView = null;
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TAB_ALL, TAB_MOBILE, TAB_WIFI})
    public @interface TabType {
    }

    public static final int TAB_ALL = 0;
    public static final int TAB_MOBILE = 1;
    public static final int TAB_WIFI = 2;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({GROUP_SAVINGS, GROUP_USAGE})
    public @interface GroupType {
    }

    public static final int GROUP_SAVINGS = 0;
    public static final int GROUP_USAGE = 1;
}
