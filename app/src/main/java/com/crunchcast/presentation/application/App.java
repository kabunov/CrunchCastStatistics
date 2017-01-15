package com.crunchcast.presentation.application;

import android.app.Application;

import com.crunchcast.presentation.di.AppComponent;
import com.crunchcast.presentation.di.AppModule;
import com.crunchcast.presentation.di.DaggerAppComponent;

public class App extends Application {

    private static AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initComponent();
    }

    private void initComponent() {
        sAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }
}
