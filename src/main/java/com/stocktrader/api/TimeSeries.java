package com.stocktrader.api;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//@Generated("jsonschema2pojo")
public class TimeSeries {

    @SerializedName("meta")
    @Expose
    public Meta meta;
    @SerializedName("values")
    @Expose
    public List<Value> values;
    @SerializedName("status")
    @Expose
    public String status;

    public TimeSeries() {
    }

    public TimeSeries(Meta meta, List<Value> values, String status) {
        super();
        this.meta = meta;
        this.values = values;
        this.status = status;
    }

    public TimeSeries withMeta(Meta meta) {
        this.meta = meta;
        return this;
    }

    public TimeSeries withValues(List<Value> values) {
        this.values = values;
        return this;
    }

    public TimeSeries withStatus(String status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(((this.meta == null) ? "<null>" : this.meta));
        sb.append(',');
        sb.append("values");
        sb.append('=');
        for (Value v: values) {
            sb.append(v).append("\n");
        }
        sb.append(",");
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null) ? "<null>" : this.status));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
