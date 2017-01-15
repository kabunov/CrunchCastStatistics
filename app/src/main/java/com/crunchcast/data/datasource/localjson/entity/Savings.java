package com.crunchcast.data.datasource.localjson.entity;

import com.google.gson.annotations.SerializedName;

public class Savings {

    @SerializedName("mobile")
    private SavingsData mMobile;

    @SerializedName("wifi")
    private SavingsData mWifi;

    public SavingsData getMobile() {
        return mMobile;
    }

    public SavingsData getWifi() {
        return mWifi;
    }
}
