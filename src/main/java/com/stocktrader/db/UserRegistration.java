package com.stocktrader.db;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.stocktrader.StocksApplication;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;

public class UserRegistration {
    final MongoDatabase db = DbConnection.getMongoClient().getDatabase("db");
    final MongoCollection<Document> collection = db.getCollection("users");

    public boolean register(String firstName, String lastName, String userName, String password, String email) {
        Document doc = collection.find(Filters.eq("username", userName)).first();
        if (doc == null) {
            Document document = new Document();
            //Stock[] stocks = new Stock[]{new Stock("NONE", 0)};
            document.put("first name", firstName);
            document.put("last name", lastName);
            document.put("username", userName);
            document.put("password", password);
            document.put("email", email);
            document.put("balance", 0);
            document.put("stocks", new ArrayList<>(
                    Arrays.asList(
                            new BasicDBObject("symbol", "NONE"),
                            new BasicDBObject("shares", 0)
                    )));

            collection.insertOne(document);
            StocksApplication.showAlert("Registration Successful");
            return true;
        } else StocksApplication.showAlert("Username already in use");
        return false;
    }
}
