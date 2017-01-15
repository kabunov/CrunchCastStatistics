package com.crunchcast.domain.entity;

public class UsageData {

    private int mDownloads;

    private int mUploads;

    public UsageData(int downloads, int uploads) {
        mDownloads = downloads;
        mUploads = uploads;
    }

    public int getDownloads() {
        return mDownloads;
    }

    public void setDownloads(int downloads) {
        mDownloads = downloads;
    }

    public int getUploads() {
        return mUploads;
    }

    public void setUploads(int uploads) {
        mUploads = uploads;
    }
}
