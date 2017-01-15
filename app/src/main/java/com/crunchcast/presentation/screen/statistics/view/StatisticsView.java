package com.crunchcast.presentation.screen.statistics.view;

import com.crunchcast.presentation.screen.statistics.model.StatisticsViewModel;

public interface StatisticsView {

    void toggleLoading(boolean showLoading);

    void showCommonData(StatisticsViewModel.StatisticsCommon commonData);

    void showSavingsData(StatisticsViewModel.StatisticsGroupData data);

    void showUsageData(StatisticsViewModel.StatisticsGroupData data);

    void showFetchingDataError();
}
