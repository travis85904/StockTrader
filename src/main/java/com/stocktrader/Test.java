package com.stocktrader;

import com.stocktrader.api.*;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {
        TimeSeries timeSeries = new GetApiResponse().timeSeries("AAPL", TimeSeriesInterval._1h);
        System.out.println(timeSeries.toString());

       // System.out.println(new GetApiResponse().realTimePrice("TSLA"));
//
//        Symbol symbol = new GetApiResponse().symbol("tesla");
//        System.out.println(symbol);
    }

}
