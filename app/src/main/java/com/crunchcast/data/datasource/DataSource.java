package com.crunchcast.data.datasource;

import com.crunchcast.domain.entity.Statistics;

import rx.Observable;

public interface DataSource {

    Observable<Statistics> getStatistics();
}
