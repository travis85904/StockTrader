package com.stocktrader.db;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.stocktrader.api.GetApiResponse;
import org.bson.Document;

import java.io.IOException;

public class StockPurchase {
    private final String userName;

    public StockPurchase(String userName, String symbol, int orderAmount) throws Exception {
        Document doc = DbConnection.getDocument("username", userName);
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

        } catch (IndexOutOfBoundsException e) {
            int symbolIndex = stocks.toString().indexOf(symbol);
            int sharesBeginIndex = stocks.toString().indexOf("=", symbolIndex) + 1;
            int sharesEndIndex = stocks.toString().indexOf("}", sharesBeginIndex);
            currentShares = Integer.parseInt(stocks.toString().substring(sharesBeginIndex, sharesEndIndex));

        }
        return currentShares + orderAmount;
    }

    private void performUpdate(Document update) {
        MongoCollection<Document> collection = DbConnection.getCollection();
        Document doc = DbConnection.getDocument("username", userName);
        collection.updateOne(doc, new BasicDBObject("$set", update));
    }

}
