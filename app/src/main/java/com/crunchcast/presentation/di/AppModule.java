package com.crunchcast.presentation.di;

import android.content.Context;

import com.crunchcast.data.repository.StatisticsRepositoryImpl;
import com.crunchcast.domain.executor.PostExecutionThread;
import com.crunchcast.domain.executor.BackgroundThreadExecutor;
import com.crunchcast.domain.executor.ThreadExecutor;
import com.crunchcast.domain.repository.StatisticsRepository;
import com.crunchcast.presentation.application.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Context mContext;

    public AppModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mContext;
    }

    @Provides
    @Singleton
    StatisticsRepository provideStatisticsRepository(StatisticsRepositoryImpl statisticsRepository) {
        return statisticsRepository;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(BackgroundThreadExecutor backgroundThreadExecutor) {
        return backgroundThreadExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }
}
