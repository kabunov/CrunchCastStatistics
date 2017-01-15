package com.crunchcast.data.repository;

import com.crunchcast.data.datasource.DataSourceFactory;
import com.crunchcast.domain.entity.Statistics;
import com.crunchcast.domain.repository.StatisticsRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

@Singleton
public class StatisticsRepositoryImpl implements StatisticsRepository {

    private DataSourceFactory mDataSourceFactory;

    @Inject
    public StatisticsRepositoryImpl(DataSourceFactory dataSourceFactory) {
        mDataSourceFactory = dataSourceFactory;
    }

    @Override
    public Observable<Statistics> getStatistics() {
        return mDataSourceFactory.createDataSource().getStatistics();
    }
}
