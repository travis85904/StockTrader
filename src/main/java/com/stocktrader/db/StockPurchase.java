package com.stocktrader.db;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.stocktrader.api.GetApiResponse;
import org.bson.Document;
import java.io.IOException;
import static com.mongodb.client.model.Filters.eq;

public class StockPurchase {
    private final String userName;

    public StockPurchase(String userName, String symbol, int orderAmount) throws IOException, InterruptedException {
        MongoDatabase db = DbConnection.getMongoClient().getDatabase("db");
        MongoCollection<Document> collection = db.getCollection("users");
        Document doc = collection.find(eq("username", userName)).first();
        this.userName = userName;

        double pricePerShare = Double.parseDouble(new GetApiResponse().realTimePrice(symbol).price());
        double totalOrderCost = pricePerShare * orderAmount;
        double balance = (double) doc.get("balance");

        if (balance >= totalOrderCost) {
            double newBalance = balance - totalOrderCost;

            Document update = new Document(String.format("stocks.%s", symbol), purchase(symbol, orderAmount, doc));
            performUpdate(update);

            Document balanceUpdate = new Document("balance", newBalance);
            performUpdate(balanceUpdate);
        }

    }

    int purchase(String symbol, int orderAmount, Document doc) throws IndexOutOfBoundsException {
        int currentShares;
        Object stocks = doc.get("stocks");

        try {
            int symbolIndex = stocks.toString().indexOf(symbol);
            if (symbolIndex == -1) {
                return orderAmount;
            }
            int sharesBeginIndex = stocks.toString().indexOf("=", symbolIndex) + 1;
            int sharesEndIndex = stocks.toString().indexOf(",", sharesBeginIndex);

            currentShares = Integer.parseInt(stocks.toString().substring(sharesBeginIndex, sharesEndIndex));

            return currentShares + orderAmount;
        } catch (IndexOutOfBoundsException e) {
            int symbolIndex = stocks.toString().indexOf(symbol);
            int sharesBeginIndex = stocks.toString().indexOf("=", symbolIndex) + 1;
            int sharesEndIndex = stocks.toString().indexOf("}", sharesBeginIndex);
            currentShares = Integer.parseInt(stocks.toString().substring(sharesBeginIndex, sharesEndIndex));
            e.printStackTrace();
            return currentShares + orderAmount;
        }
    }

    void performUpdate(Document update) {
        MongoDatabase db = DbConnection.getMongoClient().getDatabase("db");
        MongoCollection<Document> collection = db.getCollection("users");
        Document doc = collection.find(eq("username", userName)).first();
        collection.updateOne(doc, new BasicDBObject("$set", update));
    }

}
