package com.stocktrader;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.stocktrader.db.DbConnection;
import com.stocktrader.db.StockPurchase;
import org.bson.Document;

import java.io.IOException;

import static com.mongodb.client.model.Filters.eq;

public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {
//        String userName = "user2";
//
//        MongoDatabase db = DbConnection.getMongoClient().getDatabase("db");
//        MongoCollection<Document> collection = db.getCollection("users");
//
//        Document doc = collection.find(eq("username", userName)).first();
//
//        int balance = (int) doc.get("balance");
//
//        System.out.println(balance);

        new StockPurchase("user2", "TSLA", 1);
    }


}