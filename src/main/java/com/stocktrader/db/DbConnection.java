package com.stocktrader.db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import java.io.FileInputStream;
import java.util.Properties;

public class DbConnection {
    static MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017/");
//    static MongoClient mongoClient;
//    static Properties properties = new Properties();
//    static {
//        try {
//            FileInputStream fileInputStream = new FileInputStream("private/config.properties");
//            properties.load(fileInputStream);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String connectionString = String.format(properties.getProperty("mongoConnectionString"));
//        mongoClient = MongoClients.create(connectionString);
//    }

    public static MongoClient getMongoClient() {
        return mongoClient;
    }
}
