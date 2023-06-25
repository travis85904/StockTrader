package com.stocktrader.api;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

public class GetApiResponse {

    public TimeSeries timeSeries(String symbol) throws IOException, InterruptedException {
        String uri = "https://twelve-data1.p.rapidapi.com/time_series?interval=15min&symbol=" + symbol + "&format=json&outputsize=10";
        return new Gson().fromJson(getHttpResponse(uri), TimeSeries.class);
    }

    public RealTimePrice realTimePrice(String symbol) throws IOException, InterruptedException {
        String uri = "https://twelve-data1.p.rapidapi.com/price?symbol=" + symbol + "&outputsize=30&format=json";
        return new Gson().fromJson(getHttpResponse(uri), RealTimePrice.class);
    }

    public Quote quote(String symbol) throws IOException, InterruptedException {
        String uri = "https://twelve-data1.p.rapidapi.com/quote?interval=1day&symbol=" + symbol + "&format=json&outputsize=30";
        return new Gson().fromJson(getHttpResponse(uri), Quote.class);
    }

    private String getHttpResponse(String uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("X-RapidAPI-Key", getApiKey())
                .header("X-RapidAPI-Host", "twelve-data1.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    //You need to give the HTTP Request your own API key otherwise this function will not work
    //The API I used here is Twelve Data found on RapidAPI
    //https://rapidapi.com/twelvedata/api/twelve-data1/
    private static String getApiKey() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("private/config.properties");
        Properties properties = new Properties();
        properties.load(fileInputStream);
        return properties.getProperty("api.key");
    }
}