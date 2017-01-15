package com.crunchcast.domain.entity;

public class Statistics {

    private int mViews;

    private int mDuration;

    private int mUploads;

    private StatisticsGroupData mAllData;

    private StatisticsGroupData mMobileData;

    private StatisticsGroupData mWifiData;

    public int getViews() {
        return mViews;
    }

    public void setViews(int views) {
        mViews = views;
    }

    public int getDuration() {
        return mDuration;
    }

    public void setDuration(int duration) {
        mDuration = duration;
    }

    public int getUploads() {
        return mUploads;
    }

    public void setUploads(int uploads) {
        mUploads = uploads;
    }

    public StatisticsGroupData getAllData() {
        return mAllData;
    }

    public void setAllData(StatisticsGroupData allData) {
        mAllData = allData;
    }

    public StatisticsGroupData getMobileData() {
        return mMobileData;
    }

    public void setMobileData(StatisticsGroupData mobileData) {
        mMobileData = mobileData;
    }

    public StatisticsGroupData getWifiData() {
        return mWifiData;
    }

    public void setWifiData(StatisticsGroupData wifiData) {
        mWifiData = wifiData;
    }
}
