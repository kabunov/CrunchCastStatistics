package com.crunchcast.data.datasource.localjson.entity;

import com.google.gson.annotations.SerializedName;

public class Statistics {

    @SerializedName("views")
    private int mViews;

    @SerializedName("duration")
    private int mDuration;

    @SerializedName("uploads")
    private int mUploads;

    @SerializedName("savings")
    private Savings mSavings;

    @SerializedName("usage")
    private Usage mUsage;

    public int getViews() {
        return mViews;
    }

    public int getDuration() {
        return mDuration;
    }

    public int getUploads() {
        return mUploads;
    }

    public Savings getSavings() {
        return mSavings;
    }

    public Usage getUsage() {
        return mUsage;
    }
}
