package com.stocktrader;

public class DbStock {
    private final String symbol;
    private final String shares;

    public DbStock(String symbol, String shares) {
        this.symbol = symbol;
        this.shares = shares;
    }
}
