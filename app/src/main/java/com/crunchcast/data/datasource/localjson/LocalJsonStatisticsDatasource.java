package com.crunchcast.data.datasource.localjson;

import android.content.Context;

import com.crunchcast.data.datasource.DataSource;
import com.crunchcast.domain.entity.Statistics;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

import rx.Observable;

public class LocalJsonStatisticsDataSource implements DataSource {

    private Context mContext;
    private LocalJsonStatisticsConverter mConverter;
    private Gson mGson;

    public LocalJsonStatisticsDataSource(Context context, LocalJsonStatisticsConverter converter, Gson gson) {
        mContext = context;
        mConverter = converter;
        mGson = gson;
    }

    @Override
    public Observable<Statistics> getStatistics() {

        return Observable.create(subscriber -> {
            String json = loadJSONFromAsset("data.json");

            com.crunchcast.data.datasource.localjson.entity.Statistics statistics =
                    mGson.fromJson(json, com.crunchcast.data.datasource.localjson.entity.Statistics.class);

            Statistics result = mConverter.convertToDomainEntity(statistics);

            //imitation of network delay
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (result != null) {
                subscriber.onNext(result);
                subscriber.onCompleted();
            } else {
                subscriber.onError(new Exception());
            }
        });
    }

    private String loadJSONFromAsset(String fileName) {
        String json;
        try {

            InputStream is = mContext.getAssets().open(fileName);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }
}
