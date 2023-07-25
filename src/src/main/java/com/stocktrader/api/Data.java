package com.stocktrader.api;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//@Generated("jsonschema2pojo")
public class Data {

    @SerializedName("symbol")
    @Expose
    public String symbol;
    @SerializedName("instrument_name")
    @Expose
    public String instrumentName;
    @SerializedName("exchange")
    @Expose
    public String exchange;
    @SerializedName("mic_code")
    @Expose
    public String micCode;
    @SerializedName("exchange_timezone")
    @Expose
    public String exchangeTimezone;
    @SerializedName("instrument_type")
    @Expose
    public String instrumentType;
    @SerializedName("country")
    @Expose
    public String country;
    @SerializedName("currency")
    @Expose
    public String currency;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("symbol");
        sb.append('=');
        sb.append(((this.symbol == null)?"<null>":this.symbol));
        sb.append(", ");
        sb.append("instrumentName");
        sb.append('=');
        sb.append(((this.instrumentName == null)?"<null>":this.instrumentName));
        sb.append(", ");
        sb.append("exchange");
        sb.append('=');
        sb.append(((this.exchange == null)?"<null>":this.exchange));
        sb.append(", ");
        sb.append("micCode");
        sb.append('=');
        sb.append(((this.micCode == null)?"<null>":this.micCode));
        sb.append(", ");
        sb.append("exchangeTimezone");
        sb.append('=');
        sb.append(((this.exchangeTimezone == null)?"<null>":this.exchangeTimezone));
        sb.append(", ");
        sb.append("instrumentType");
        sb.append('=');
        sb.append(((this.instrumentType == null)?"<null>":this.instrumentType));
        sb.append(", ");
        sb.append("country");
        sb.append('=');
        sb.append(((this.country == null)?"<null>":this.country));
        sb.append(", ");
        sb.append("currency");
        sb.append('=');
        sb.append(((this.currency == null)?"<null>":this.currency));
        sb.append(", ");
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}