package com.stocktrader.db;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class UserRegistration {
    MongoDatabase db = DbConnection.getMongoClient().getDatabase("db");
    MongoCollection<Document> collection = db.getCollection("users");
    public void register(String firstName, String lastName, String userName, String password, String email) {
        Document document = new Document();
        document.put("first name", firstName);
        document.put("last name", lastName);
        document.put("username", userName);
        document.put("password", password);
        document.put("email", email);
        collection.insertOne(document);
    }
}
