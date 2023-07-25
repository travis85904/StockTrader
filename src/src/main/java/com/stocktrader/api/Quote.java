package com.stocktrader.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



//@Generated("jsonschema2pojo")
public class Quote {

    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("exchange")
    @Expose
    private String exchange;
    @SerializedName("mic_code")
    @Expose
    private String micCode;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("datetime")
    @Expose
    private String datetime;
    @SerializedName("timestamp")
    @Expose
    private Integer timestamp;
    @SerializedName("open")
    @Expose
    private String open;
    @SerializedName("high")
    @Expose
    private String high;
    @SerializedName("low")
    @Expose
    private String low;
    @SerializedName("close")
    @Expose
    private String close;
    @SerializedName("volume")
    @Expose
    private String volume;
    @SerializedName("previous_close")
    @Expose
    private String previousClose;
    @SerializedName("change")
    @Expose
    private String change;
    @SerializedName("percent_change")
    @Expose
    private String percentChange;
    @SerializedName("average_volume")
    @Expose
    private String averageVolume;
    @SerializedName("is_market_open")
    @Expose
    private Boolean isMarketOpen;
    @SerializedName("fifty_two_week")
    @Expose
    private FiftyTwoWeek fiftyTwoWeek;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getMicCode() {
        return micCode;
    }

    public void setMicCode(String micCode) {
        this.micCode = micCode;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getPreviousClose() {
        return previousClose;
    }

    public void setPreviousClose(String previousClose) {
        this.previousClose = previousClose;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getPercentChange() {
        return percentChange;
    }

    public void setPercentChange(String percentChange) {
        this.percentChange = percentChange;
    }

    public String getAverageVolume() {
        return averageVolume;
    }

    public void setAverageVolume(String averageVolume) {
        this.averageVolume = averageVolume;
    }

    public Boolean getIsMarketOpen() {
        return isMarketOpen;
    }

    public void setIsMarketOpen(Boolean isMarketOpen) {
        this.isMarketOpen = isMarketOpen;
    }

    public FiftyTwoWeek getFiftyTwoWeek() {
        return fiftyTwoWeek;
    }

    public void setFiftyTwoWeek(FiftyTwoWeek fiftyTwoWeek) {
        this.fiftyTwoWeek = fiftyTwoWeek;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("symbol");
        sb.append('=');
        sb.append(((this.symbol == null)?"<null>":this.symbol));
        sb.append(", ");
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(", ");
        sb.append("exchange");
        sb.append('=');
        sb.append(((this.exchange == null)?"<null>":this.exchange));
        sb.append(", ");
        sb.append("mic_code");
        sb.append('=');
        sb.append(((this.micCode == null)?"<null>":this.micCode));
        sb.append(", ");
        sb.append("currency");
        sb.append('=');
        sb.append(((this.currency == null)?"<null>":this.currency));
        sb.append(", ");
        sb.append("datetime");
        sb.append('=');
        sb.append(((this.datetime == null)?"<null>":this.datetime));
        sb.append(", ");
        sb.append("timestamp");
        sb.append('=');
        sb.append(((this.timestamp == null)?"<null>":this.timestamp));
        sb.append(", ");
        sb.append("open");
        sb.append('=');
        sb.append(((this.open == null)?"<null>":this.open));
        sb.append(", ");
        sb.append("high");
        sb.append('=');
        sb.append(((this.high == null)?"<null>":this.high));
        sb.append(", ");
        sb.append("low");
        sb.append('=');
        sb.append(((this.low == null)?"<null>":this.low));
        sb.append(", ");
        sb.append("close");
        sb.append('=');
        sb.append(((this.close == null)?"<null>":this.close));
        sb.append(", ");
        sb.append("volume");
        sb.append('=');
        sb.append(((this.volume == null)?"<null>":this.volume));
        sb.append(", ");
        sb.append("previousClose");
        sb.append('=');
        sb.append(((this.previousClose == null)?"<null>":this.previousClose));
        sb.append(", ");
        sb.append("change");
        sb.append('=');
        sb.append(((this.change == null)?"<null>":this.change));
        sb.append(", ");
        sb.append("percentChange");
        sb.append('=');
        sb.append(((this.percentChange == null)?"<null>":this.percentChange));
        sb.append(", ");
        sb.append("averageVolume");
        sb.append('=');
        sb.append(((this.averageVolume == null)?"<null>":this.averageVolume));
        sb.append(", ");
        sb.append("isMarketOpen");
        sb.append('=');
        sb.append(((this.isMarketOpen == null)?"<null>":this.isMarketOpen));
        sb.append(", ");
        sb.append("fiftyTwoWeek");
        sb.append('=');
        sb.append(((this.fiftyTwoWeek == null)?"<null>":this.fiftyTwoWeek));
        sb.append(", ");
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
