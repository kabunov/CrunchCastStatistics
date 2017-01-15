package com.crunchcast.presentation.screen.statistics.model;

import com.crunchcast.domain.entity.SavingsData;
import com.crunchcast.domain.entity.Statistics;
import com.crunchcast.domain.entity.StatisticsGroupData;
import com.crunchcast.domain.entity.UsageData;

import java.util.concurrent.TimeUnit;

public class StatisticsConverter {

    public static StatisticsViewModel convert(Statistics statistics) {
        StatisticsViewModel result = new StatisticsViewModel();

        StatisticsViewModel.StatisticsCommon commonData = new StatisticsViewModel.StatisticsCommon();
        commonData.setViews(convertIntToString(statistics.getViews()));
        commonData.setDuration(convertDurationToString(statistics.getDuration()));
        commonData.setUploads(convertIntToString(statistics.getUploads()));
        result.setCommonData(commonData);

        result.setMobileData(convertGroup(statistics.getMobileData()));
        result.setWifiData(convertGroup(statistics.getWifiData()));
        result.setAllData(convertGroup(statistics.getAllData()));

        return result;
    }

    private static StatisticsViewModel.StatisticsGroup convertGroup(StatisticsGroupData groupData) {
        //Savings
        StatisticsViewModel.StatisticsGroupDataElement uploads1 = convertUploads(groupData.getSavingsData());
        StatisticsViewModel.StatisticsGroupDataElement playback = convertPlayback(groupData.getSavingsData());

        StatisticsViewModel.StatisticsGroupData savings = new StatisticsViewModel.StatisticsGroupData(
                uploads1,
                playback,
                formatSize(groupData.getSavingsData().getUploads() + groupData.getSavingsData().getPlayback()));

        //Usage
        StatisticsViewModel.StatisticsGroupDataElement uploads2 = convertUploads(groupData.getUsageData());
        StatisticsViewModel.StatisticsGroupDataElement downloads = convertDownloads(groupData.getUsageData());

        StatisticsViewModel.StatisticsGroupData usage = new StatisticsViewModel.StatisticsGroupData(
                uploads2,
                downloads,
                formatSize(groupData.getUsageData().getUploads() + groupData.getUsageData().getDownloads()));

        return new StatisticsViewModel.StatisticsGroup(savings, usage);
    }

    private static StatisticsViewModel.StatisticsGroupDataElement convertUploads(SavingsData savingsData) {
        int percent = formatPercent(savingsData.getUploads(), savingsData.getUploads() + savingsData.getPlayback());
        return new StatisticsViewModel.StatisticsGroupDataElement(
                String.format("Uploads (%d%%)", percent),//TODO move to strings.xml
                formatSize(savingsData.getUploads()),
                percent
        );
    }

    private static StatisticsViewModel.StatisticsGroupDataElement convertUploads(UsageData usageData) {
        int percent = formatPercent(usageData.getUploads(), usageData.getUploads() + usageData.getDownloads());
        return new StatisticsViewModel.StatisticsGroupDataElement(
                String.format("Uploads (%d%%)", percent),
                formatSize(usageData.getUploads()),
                percent
        );
    }

    private static StatisticsViewModel.StatisticsGroupDataElement convertPlayback(SavingsData savingsData) {
        int percent = formatPercent(savingsData.getPlayback(), savingsData.getUploads() + savingsData.getPlayback());
        return new StatisticsViewModel.StatisticsGroupDataElement(
                String.format("Playback (%d%%)", percent),
                formatSize(savingsData.getPlayback()),
                percent
        );
    }

    private static StatisticsViewModel.StatisticsGroupDataElement convertDownloads(UsageData usageData) {
        int percent = formatPercent(usageData.getDownloads(), usageData.getUploads() + usageData.getDownloads());
        return new StatisticsViewModel.StatisticsGroupDataElement(
                String.format("Downloads (%d%%)", percent),
                formatSize(usageData.getDownloads()),
                percent
        );
    }

    private static String convertDurationToString(int duration) {
        //TODO add case if shorter than 1 hr (1 min)
        long hours = TimeUnit.SECONDS.toHours(duration);
        duration -= TimeUnit.HOURS.toSeconds(hours);
        long minutes = TimeUnit.SECONDS.toMinutes(duration);
        duration -= TimeUnit.MINUTES.toSeconds(minutes);
        long seconds = TimeUnit.SECONDS.toSeconds(duration);

        return String.format("%dh %dm %ds", hours, minutes, seconds);
    }

    private static String convertIntToString(int value) {
        return String.format("%,2d", value);
    }

    private static String formatSize(int filesize) {
        //TODO add converting to GB, KB etc
        return String.format("%d MB", filesize);
    }

    private static int formatPercent(int value, int totalValue) {
        return Math.round(value * 100f / totalValue);
    }
}
