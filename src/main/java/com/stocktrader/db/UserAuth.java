package com.stocktrader.db;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.stocktrader.StocksApplication;
import org.bson.Document;

public class UserAuth {
    public boolean authenticate(String userName, String password) {
        MongoDatabase db = DbConnection.getMongoClient().getDatabase("db");
        MongoCollection<Document> collection = db.getCollection("users");

        Document doc = collection.find(Filters.eq("username", userName)).first();
        if (doc == null) {
            StocksApplication.showAlert("User not found");
        } else {
            if (doc.get("password").equals(password)) {
                return true;
            } else {
                StocksApplication.showAlert("Invalid password");
            }
        }
        return false;
    }
}
