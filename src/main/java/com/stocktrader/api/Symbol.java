package com.stocktrader.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

//@Generated("jsonschema2pojo")
public class Symbol {

    @SerializedName("data")
    @Expose
    public List<Data> data;
    @SerializedName("status")
    @Expose
    public String status;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("data");
        sb.append('=');
        for (Data d: data) {
            sb.append(d).append("\n");
        }
        sb.append(", ");
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
        sb.append(", ");
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}