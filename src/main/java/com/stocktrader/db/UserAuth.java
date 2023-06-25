package com.stocktrader.db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.stocktrader.StocksApplication;
import org.bson.Document;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserAuth {
    public boolean authenticate(String userName, String password) {
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
            Document doc = collection.find(Filters.eq("username", userName)).first();
            if (doc.get("password").equals(password)) {
                System.out.println("authentication successful");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            StocksApplication.showAlert("Invalid Password");
            return false;
        }

        return false;
    }

}
