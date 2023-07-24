package com.stocktrader.db;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.stocktrader.StocksApplication;
import org.bson.Document;

public class UserRegistration {
    MongoDatabase db = DbConnection.getMongoClient().getDatabase("db");
    MongoCollection<Document> collection = db.getCollection("users");

    public boolean register(String firstName, String lastName, String userName, String password, String email) {
        Document doc = collection.find(Filters.eq("username", userName)).first();
        if (doc == null) {
            Document document = new Document();
            document.put("first name", firstName);
            document.put("last name", lastName);
            document.put("username", userName);
            document.put("password", password);
            document.put("email", email);
            document.put("balance", 0);
            collection.insertOne(document);
            StocksApplication.showAlert("Registration Successful");
            return true;
        } else StocksApplication.showAlert("Username already in use");
        return false;
    }
}
