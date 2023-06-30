package com.stocktrader;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.stocktrader.api.*;
import com.stocktrader.db.DbConnection;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.IOException;

import static com.mongodb.client.model.Updates.set;

public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {
//        MongoDatabase db = DbConnection.getMongoClient().getDatabase("db");
//        MongoCollection<Document> collection = db.getCollection("users");
//        Document doc = collection.find(Filters.eq("username", "user2")).first();
//      //  int amount = 100;
//
//        int currentBalance = (int) doc.get("balance");
//        Bson updateOperation = set("balance", amount+currentBalance);
//        collection.updateOne(doc,updateOperation);
    }
}