package com.stocktrader;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class Test {
    public static void main(String[] args) {
        final String hashed = Hashing.sha256()
                .hashString("your input", StandardCharsets.UTF_8)
                .toString();
    }
}




//    module com.stocks.stocktrader {
//        requires javafx.controls;
//        requires javafx.fxml;
//        requires java.net.http;
//        requires com.google.gson;
//        requires org.mongodb.driver.core;
//        requires org.mongodb.driver.sync.client;
//        requires org.mongodb.bson;
//
//        opens com.stocktrader.parser to com.google.gson;
//        opens com.stocktrader to javafx.fxml;
//        opens com.stocktrader.api to javafx.fxml, com.google.gson;
//        opens com.stocktrader.db to javafx.fxml;
//        exports com.stocktrader;
//        exports com.stocktrader.api;
//        exports com.stocktrader.db;
//        exports com.stocktrader.controllers;
//        opens com.stocktrader.controllers to javafx.fxml;
//
//        }