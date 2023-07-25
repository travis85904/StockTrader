package com.stocktrader.api;

public record Meta(String symbol, String interval, String currency, String exchange_timezone, String exchange,
                   String mic_code, String type) {
}


