package com.crunchcast.domain.interactor;

import com.crunchcast.domain.entity.Statistics;
import com.crunchcast.domain.executor.PostExecutionThread;
import com.crunchcast.domain.executor.BackgroundThreadExecutor;
import com.crunchcast.domain.repository.StatisticsRepository;

import javax.inject.Inject;

import rx.Observable;

public class GetStatisticsInteractor extends Interactor<Statistics> {

    private StatisticsRepository mStatisticsRepository;

    @Inject
    GetStatisticsInteractor(StatisticsRepository statisticsRepository, BackgroundThreadExecutor backgroundThreadExecutor,
                PostExecutionThread postExecutionThread) {
        super(backgroundThreadExecutor, postExecutionThread);
        mStatisticsRepository = statisticsRepository;
    }

    @Override
    Observable<Statistics> getObservable() {
        return mStatisticsRepository.getStatistics();
    }
}
