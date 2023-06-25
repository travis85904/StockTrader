package com.stocktrader.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//@Generated("jsonschema2pojo")
public class FiftyTwoWeek {

    @SerializedName("low")
    @Expose
    private String low;
    @SerializedName("high")
    @Expose
    private String high;
    @SerializedName("low_change")
    @Expose
    private String lowChange;
    @SerializedName("high_change")
    @Expose
    private String highChange;
    @SerializedName("low_change_percent")
    @Expose
    private String lowChangePercent;
    @SerializedName("high_change_percent")
    @Expose
    private String highChangePercent;
    @SerializedName("range")
    @Expose
    private String range;

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLowChange() {
        return lowChange;
    }

    public void setLowChange(String lowChange) {
        this.lowChange = lowChange;
    }

    public String getHighChange() {
        return highChange;
    }

    public void setHighChange(String highChange) {
        this.highChange = highChange;
    }

    public String getLowChangePercent() {
        return lowChangePercent;
    }

    public void setLowChangePercent(String lowChangePercent) {
        this.lowChangePercent = lowChangePercent;
    }

    public String getHighChangePercent() {
        return highChangePercent;
    }

    public void setHighChangePercent(String highChangePercent) {
        this.highChangePercent = highChangePercent;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("low");
        sb.append('=');
        sb.append(((this.low == null)?"<null>":this.low));
        sb.append(", ");
        sb.append("high");
        sb.append('=');
        sb.append(((this.high == null)?"<null>":this.high));
        sb.append(", ");
        sb.append("lowChange");
        sb.append('=');
        sb.append(((this.lowChange == null)?"<null>":this.lowChange));
        sb.append(", ");
        sb.append("highChange");
        sb.append('=');
        sb.append(((this.highChange == null)?"<null>":this.highChange));
        sb.append(", ");
        sb.append("lowChangePercent");
        sb.append('=');
        sb.append(((this.lowChangePercent == null)?"<null>":this.lowChangePercent));
        sb.append(", ");
        sb.append("highChangePercent");
        sb.append('=');
        sb.append(((this.highChangePercent == null)?"<null>":this.highChangePercent));
        sb.append(", ");
        sb.append("range");
        sb.append('=');
        sb.append(((this.range == null)?"<null>":this.range));
        sb.append(", ");
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}