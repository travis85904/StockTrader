package com.stocktrader;

import com.stocktrader.api.GetApiResponse;
import com.stocktrader.db.UserAuth;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(GetApiResponse.stockPrice("AAPL"));

    }
}
