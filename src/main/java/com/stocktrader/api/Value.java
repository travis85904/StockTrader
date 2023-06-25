package com.stocktrader.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Value {

    @SerializedName("datetime")
    @Expose
    public String datetime;
    @SerializedName("open")
    @Expose
    public String open;
    @SerializedName("high")
    @Expose
    public String high;
    @SerializedName("low")
    @Expose
    public String low;
    @SerializedName("close")
    @Expose
    public String close;
    @SerializedName("volume")
    @Expose
    public String volume;

    /**
     * No args constructor for use in serialization
     */
    public Value() {
    }

    /**
     * @param volume
     * @param datetime
     * @param high
     * @param low
     * @param close
     * @param open
     */
    public Value(String datetime, String open, String high, String low, String close, String volume) {
        super();
        this.datetime = datetime;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
    }

    public Value withDatetime(String datetime) {
        this.datetime = datetime;
        return this;
    }

    public Value withOpen(String open) {
        this.open = open;
        return this;
    }

    public Value withHigh(String high) {
        this.high = high;
        return this;
    }

    public Value withLow(String low) {
        this.low = low;
        return this;
    }

    public Value withClose(String close) {
        this.close = close;
        return this;
    }

    public Value withVolume(String volume) {
        this.volume = volume;
        return this;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("datetime");
        sb.append('=');
        sb.append(((this.datetime == null)?"<null>":this.datetime));
        sb.append(',');
        sb.append("open");
        sb.append('=');
        sb.append(((this.open == null)?"<null>":this.open));
        sb.append(',');
        sb.append("high");
        sb.append('=');
        sb.append(((this.high == null)?"<null>":this.high));
        sb.append(',');
        sb.append("low");
        sb.append('=');
        sb.append(((this.low == null)?"<null>":this.low));
        sb.append(',');
        sb.append("close");
        sb.append('=');
        sb.append(((this.close == null)?"<null>":this.close));
        sb.append(',');
        sb.append("volume");
        sb.append('=');
        sb.append(((this.volume == null)?"<null>":this.volume));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
}

