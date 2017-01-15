package com.crunchcast.data.datasource.localjson.entity;

import com.google.gson.annotations.SerializedName;

public class Usage {

    @SerializedName("mobile")
    private UsageData mMobile;

    @SerializedName("wifi")
    private UsageData mWifi;

    public UsageData getMobile() {
        return mMobile;
    }

    public UsageData getWifi() {
        return mWifi;
    }
}
