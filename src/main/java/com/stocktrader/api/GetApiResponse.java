package com.stocktrader.api;

import com.google.gson.Gson;
import javafx.scene.Parent;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

public class GetApiResponse {
    public static TimeSeries stockPrice(String symbol) throws IOException, InterruptedException {
        FileInputStream fileInputStream = new FileInputStream("private/config.properties");
        Properties properties = new Properties();
        properties.load(fileInputStream);

        //You need to give the HTTP Request your own API key otherwise this function will not work
        //The API I used here is Twelve Data found on RapidAPI
        //https://rapidapi.com/twelvedata/api/twelve-data1/
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://twelve-data1.p.rapidapi.com/time_series?interval=15min&symbol="+symbol+"&format=json&outputsize=10"))
                .header("X-RapidAPI-Key", properties.getProperty("api.key"))
                .header("X-RapidAPI-Host", "twelve-data1.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return new Gson().fromJson(response.body(), TimeSeries.class);
    }
}