package com.crunchcast.domain.repository;


import com.crunchcast.domain.entity.Statistics;

import rx.Observable;

public interface StatisticsRepository {
    Observable<Statistics> getStatistics();
}
