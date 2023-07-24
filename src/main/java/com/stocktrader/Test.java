package com.stocktrader;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.stocktrader.db.DbConnection;
import com.stocktrader.db.StockPurchase;
import org.bson.Document;

import java.io.IOException;

import static com.mongodb.client.model.Filters.eq;

public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {



        new StockPurchase("user2", "TSLA", 1);
    }


}