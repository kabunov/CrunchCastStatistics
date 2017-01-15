package com.crunchcast.data.datasource;

import android.content.Context;

import com.crunchcast.data.datasource.localjson.LocalJsonStatisticsConverter;
import com.crunchcast.data.datasource.localjson.LocalJsonStatisticsDataSource;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DataSourceFactory {

    private Context mContext;

    @Inject
    public DataSourceFactory(Context context) {
        mContext = context;
    }

    public DataSource createDataSource() {
        //now only local json data source is used.
        //in real life here we would use appropriate data source like api, database, preferences etc.
        return createLocalJsonDataSource();
    }

    private DataSource createLocalJsonDataSource() {
        return new LocalJsonStatisticsDataSource(mContext, new LocalJsonStatisticsConverter(), new Gson());
    }
}
