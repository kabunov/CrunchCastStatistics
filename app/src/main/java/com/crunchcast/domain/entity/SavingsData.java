package com.crunchcast.domain.entity;

public class SavingsData {

    private int mPlayback;

    private int mUploads;

    public SavingsData(int playback, int uploads) {
        mPlayback = playback;
        mUploads = uploads;
    }

    public int getPlayback() {
        return mPlayback;
    }

    public void setPlayback(int playback) {
        mPlayback = playback;
    }

    public int getUploads() {
        return mUploads;
    }

    public void setUploads(int uploads) {
        mUploads = uploads;
    }
}
