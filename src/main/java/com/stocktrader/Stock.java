package com.stocktrader;

public record Stock(String symbol, String interval, String currency, String exchange_timezone, String exchange, String mic_code, String type) {

}
