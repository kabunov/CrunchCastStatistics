package com.crunchcast.data.datasource.localjson;


import com.crunchcast.domain.entity.SavingsData;
import com.crunchcast.domain.entity.Statistics;
import com.crunchcast.domain.entity.StatisticsGroupData;
import com.crunchcast.domain.entity.UsageData;

public class LocalJsonStatisticsConverter {

    public Statistics convertToDomainEntity(com.crunchcast.data.datasource.localjson.entity.Statistics statistics) {

        Statistics res = new Statistics();

        res.setViews(statistics.getViews());
        res.setDuration(statistics.getDuration());
        res.setUploads(statistics.getUploads());
        res.setMobileData(new StatisticsGroupData(
                new SavingsData(statistics.getSavings().getMobile().getPlayback(), statistics.getSavings().getMobile().getUploads()),
                new UsageData(statistics.getUsage().getMobile().getDownloads(), statistics.getUsage().getMobile().getUploads())
        ));
        res.setWifiData(new StatisticsGroupData(
                new SavingsData(statistics.getSavings().getWifi().getPlayback(), statistics.getSavings().getWifi().getUploads()),
                new UsageData(statistics.getUsage().getWifi().getDownloads(), statistics.getUsage().getWifi().getUploads())
        ));
        res.setAllData(new StatisticsGroupData(
                new SavingsData(statistics.getSavings().getMobile().getPlayback() + statistics.getSavings().getWifi().getPlayback(),
                        statistics.getSavings().getMobile().getUploads() + statistics.getSavings().getWifi().getUploads()),
                new UsageData(statistics.getUsage().getMobile().getDownloads() + statistics.getUsage().getWifi().getDownloads(),
                        statistics.getUsage().getMobile().getUploads() + statistics.getUsage().getWifi().getUploads())
        ));

        return res;
    }
}
