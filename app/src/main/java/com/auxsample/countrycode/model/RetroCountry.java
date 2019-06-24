package com.auxsample.countrycode.model;

import com.google.gson.annotations.SerializedName;

import javax.inject.Inject;

public class RetroCountry {


    @SerializedName("flag")
    private String flag;
    @SerializedName("name")
    private String name;
    @SerializedName("callingCodes")
    private String callingCodes[];

    @Inject
    public RetroCountry(String flag, String name, String callingCodes[]) {

        this.flag = flag;
        this.name = name;
        this.callingCodes = callingCodes;
    }

    public String getFlag() {
        return flag;
    }
    public String getName() {
        return name;
    }
    public String[] getCallingCodes() {
        return callingCodes;
    }
}
