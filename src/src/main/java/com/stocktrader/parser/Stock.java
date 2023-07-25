
package com.stocktrader.parser;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//@Generated("jsonschema2pojo")
public class Stock {

    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("shares")
    @Expose
    private int shares;

    public Stock(String symbol, int shares) {
        this.symbol = symbol;
        this.shares = shares;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Stock withSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public Integer getShares() {
        return shares;
    }

    public void setShares(Integer shares) {
        this.shares = shares;
    }

    public Stock withShares(Integer shares) {
        this.shares = shares;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        sb.append("symbol: ");
        sb.append(((this.symbol == null) ? "<null>" : this.symbol));
        sb.append(" | ");
        sb.append("shares: ");
        sb.append(this.shares);
        sb.append("]\n");

        return sb.toString();
    }

}
