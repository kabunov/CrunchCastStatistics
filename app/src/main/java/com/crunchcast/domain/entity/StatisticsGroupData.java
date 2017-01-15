package com.crunchcast.domain.entity;

public class StatisticsGroupData {

    private SavingsData mSavingsData;

    private UsageData mUsageData;

    public StatisticsGroupData(SavingsData savingsData, UsageData usageData) {
        mSavingsData = savingsData;
        mUsageData = usageData;
    }

    public SavingsData getSavingsData() {
        return mSavingsData;
    }

    public void setSavingsData(SavingsData savingsData) {
        mSavingsData = savingsData;
    }

    public UsageData getUsageData() {
        return mUsageData;
    }

    public void setUsageData(UsageData usageData) {
        mUsageData = usageData;
    }
}
