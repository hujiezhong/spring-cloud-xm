package com.peanut.entity;


import java.io.Serializable;

/**
 * 折扣表
 */
public class Rate implements Serializable {

    private int rateId;
    private int ratio;  //折率  1：一折 2：二折 10:不打折  这样
    private String rateName;  //活动名称

    public int getRateId() {
        return rateId;
    }

    public void setRateId(int rateId) {
        this.rateId = rateId;
    }

    public int getRatio() {
        return ratio;
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
    }

    public String getRateName() {
        return rateName;
    }

    public void setRateName(String rateName) {
        this.rateName = rateName;
    }
}
