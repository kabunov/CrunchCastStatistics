package com.crunchcast.presentation.di;

import com.crunchcast.presentation.screen.statistics.view.StatisticsActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void injectStatisticsActivity(StatisticsActivity statisticsActivity);
}
