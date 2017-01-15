package com.crunchcast.presentation.screen.statistics.model;

public class StatisticsViewModel {

    private StatisticsCommon mCommonData;

    private StatisticsGroup mAllData;

    private StatisticsGroup mMobileData;

    private StatisticsGroup mWifiData;

    public StatisticsCommon getCommonData() {
        return mCommonData;
    }

    public void setCommonData(StatisticsCommon commonData) {
        mCommonData = commonData;
    }

    public StatisticsGroup getAllData() {
        return mAllData;
    }

    public void setAllData(StatisticsGroup allData) {
        mAllData = allData;
    }

    public StatisticsGroup getMobileData() {
        return mMobileData;
    }

    public void setMobileData(StatisticsGroup mobileData) {
        mMobileData = mobileData;
    }

    public StatisticsGroup getWifiData() {
        return mWifiData;
    }

    public void setWifiData(StatisticsGroup wifiData) {
        mWifiData = wifiData;
    }

    public static class StatisticsCommon {
        private String mViews;

        private String mDuration;

        private String mUploads;

        public String getViews() {
            return mViews;
        }

        public void setViews(String views) {
            mViews = views;
        }

        public String getDuration() {
            return mDuration;
        }

        public void setDuration(String duration) {
            mDuration = duration;
        }

        public String getUploads() {
            return mUploads;
        }

        public void setUploads(String uploads) {
            mUploads = uploads;
        }

    }

    public static class StatisticsGroup {

        private StatisticsGroupData mSavingsData;

        private StatisticsGroupData mUsageData;

        public StatisticsGroup(StatisticsGroupData savingsData, StatisticsGroupData usageData) {
            mSavingsData = savingsData;
            mUsageData = usageData;
        }

        public StatisticsGroupData getSavingsData() {
            return mSavingsData;
        }

        public void setSavingsData(StatisticsGroupData savingsData) {
            mSavingsData = savingsData;
        }

        public StatisticsGroupData getUsageData() {
            return mUsageData;
        }

        public void setUsageData(StatisticsGroupData usageData) {
            mUsageData = usageData;
        }
    }

    public static class StatisticsGroupData {

        private StatisticsGroupDataElement mFirst;
        private StatisticsGroupDataElement mSecond;
        private String mTotalValue;

        public StatisticsGroupData(StatisticsGroupDataElement first, StatisticsGroupDataElement second, String totalValue) {
            mFirst = first;
            mSecond = second;
            mTotalValue = totalValue;
        }

        public StatisticsGroupDataElement getFirst() {
            return mFirst;
        }

        public void setFirst(StatisticsGroupDataElement first) {
            mFirst = first;
        }

        public StatisticsGroupDataElement getSecond() {
            return mSecond;
        }

        public void setSecond(StatisticsGroupDataElement second) {
            mSecond = second;
        }

        public String getTotalValue() {
            return mTotalValue;
        }

        public void setTotalValue(String totalValue) {
            mTotalValue = totalValue;
        }
    }

    public static class StatisticsGroupDataElement {

        private String mTitle;
        private String mValue;
        private int mPercent;

        public StatisticsGroupDataElement(String title, String value, int percent) {
            mTitle = title;
            mValue = value;
            mPercent = percent;
        }

        public String getTitle() {
            return mTitle;
        }

        public String getValue() {
            return mValue;
        }

        public int getPercent() {
            return mPercent;
        }
    }
}
