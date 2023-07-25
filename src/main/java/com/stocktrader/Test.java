package com.stocktrader;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.stocktrader.db.DbConnection;
import com.stocktrader.db.StockPurchase;
import com.stocktrader.db.StockSell;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class Test {
    public static void main(String[] args) throws Exception {
        String userName = "user2";

        MongoDatabase db = DbConnection.getMongoClient().getDatabase("db");
        MongoCollection<Document> collection = db.getCollection("users");
        Document doc = collection.find(eq("username", userName)).first();

        new StockPurchase("user2", "AMC", 14);
        new StockPurchase("user2", "GME", 42);
        new StockPurchase("user2", "TSLA", 4);
        new StockPurchase("user2", "AAPL", 9);
        new StockPurchase("user2", "MSFT", 7);

    }
}