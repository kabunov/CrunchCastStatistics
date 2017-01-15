package com.crunchcast.data.datasource.localjson.entity;

import com.google.gson.annotations.SerializedName;

public class SavingsData {

    @SerializedName("playback")
    private int mPlayback;

    @SerializedName("uploads")
    private int mUploads;

    public int getPlayback() {
        return mPlayback;
    }

    public int getUploads() {
        return mUploads;
    }
}
