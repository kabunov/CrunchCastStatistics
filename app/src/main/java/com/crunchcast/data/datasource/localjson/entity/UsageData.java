package com.crunchcast.data.datasource.localjson.entity;

import com.google.gson.annotations.SerializedName;

public class UsageData {

    @SerializedName("downloads")
    private int mDownloads;

    @SerializedName("uploads")
    private int mUploads;

    public int getDownloads() {
        return mDownloads;
    }

    public int getUploads() {
        return mUploads;
    }
}
