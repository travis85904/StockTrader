package com.stocktrader.db;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Updates.set;

public class Deposit {
    private final String userName;
    private final double amount;

    public Deposit(String userName, double amount) {
        this.userName = userName;
        this.amount = amount;
        deposit();
    }

    private void deposit() {
        MongoDatabase db = DbConnection.getMongoClient().getDatabase("db");
        MongoCollection<Document> collection = db.getCollection("users");
        Document doc = collection.find(Filters.eq("username", userName)).first();
        double currentBalance = (double) doc.get("balance");
        Bson updateOperation = set("balance", amount + currentBalance);
        collection.updateOne(doc, updateOperation);
    }
}
