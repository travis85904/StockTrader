package com.stocktrader.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Meta {

    @SerializedName("symbol")
    @Expose
    public String symbol;
    @SerializedName("interval")
    @Expose
    public String interval;
    @SerializedName("currency")
    @Expose
    public String currency;
    @SerializedName("exchange_timezone")
    @Expose
    public String exchangeTimezone;
    @SerializedName("exchange")
    @Expose
    public String exchange;
    @SerializedName("mic_code")
    @Expose
    public String micCode;
    @SerializedName("type")
    @Expose
    public String type;

    /**
     * No args constructor for use in serialization
     *
     */
    public Meta() {
    }

    /**
     *
     * @param symbol
     * @param micCode
     * @param interval
     * @param currency
     * @param exchange
     * @param exchangeTimezone
     * @param type
     */
    public Meta(String symbol, String interval, String currency, String exchangeTimezone, String exchange, String micCode, String type) {
        super();
        this.symbol = symbol;
        this.interval = interval;
        this.currency = currency;
        this.exchangeTimezone = exchangeTimezone;
        this.exchange = exchange;
        this.micCode = micCode;
        this.type = type;
    }

    public Meta withSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public Meta withInterval(String interval) {
        this.interval = interval;
        return this;
    }

    public Meta withCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public Meta withExchangeTimezone(String exchangeTimezone) {
        this.exchangeTimezone = exchangeTimezone;
        return this;
    }

    public Meta withExchange(String exchange) {
        this.exchange = exchange;
        return this;
    }

    public Meta withMicCode(String micCode) {
        this.micCode = micCode;
        return this;
    }

    public Meta withType(String type) {
        this.type = type;
        return this;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
       // sb.append(Meta.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("symbol");
        sb.append('=');
        sb.append(((this.symbol == null)?"<null>":this.symbol));
        sb.append(',');
        sb.append("interval");
        sb.append('=');
        sb.append(((this.interval == null)?"<null>":this.interval));
        sb.append(',');
        sb.append("currency");
        sb.append('=');
        sb.append(((this.currency == null)?"<null>":this.currency));
        sb.append(',');
        sb.append("exchangeTimezone");
        sb.append('=');
        sb.append(((this.exchangeTimezone == null)?"<null>":this.exchangeTimezone));
        sb.append(',');
        sb.append("exchange");
        sb.append('=');
        sb.append(((this.exchange == null)?"<null>":this.exchange));
        sb.append(',');
        sb.append("micCode");
        sb.append('=');
        sb.append(((this.micCode == null)?"<null>":this.micCode));
        sb.append(',');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
