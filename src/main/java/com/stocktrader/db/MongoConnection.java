package com.stocktrader.db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

//
//FOR TESTING PURPOSES ONLY
//
//
public class MongoConnection {
    public static void main(String[] args) throws IOException {
        String user = "another ser";


        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("private/config.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String connectionString = String.format(properties.getProperty("mongoConnectionString"));

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            List<Document> databases = mongoClient.listDatabases().into(new ArrayList<>());
            MongoDatabase db = mongoClient.getDatabase("db");
            MongoCollection<Document> collection = db.getCollection("users");
            try {
                collection.find(Filters.eq("_id", user)).forEach(doc -> System.out.println(doc.toJson()));
                Document doc = collection.find(Filters.eq("username", "testUer")).first();
                System.out.println(doc.toJson());
            } catch (NullPointerException e) {
                System.out.println("user not found");
            }
        }
    }
}
