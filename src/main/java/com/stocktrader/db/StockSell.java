package com.stocktrader.db;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.stocktrader.api.GetApiResponse;
import org.bson.Document;

import java.io.IOException;

public class StockSell {
    private final String userName;

    public StockSell(String userName, String symbol, int orderAmount) throws IOException, InterruptedException {
        Document doc = DbConnection.getDocument("username", userName);
        this.userName = userName;

        double pricePerShare = Double.parseDouble(new GetApiResponse().realTimePrice(symbol).price());
        double totalOrderCost = pricePerShare * orderAmount;
        double balance = (double) doc.get("balance");
        double balanceAfterSale = balance + totalOrderCost;
        int sharesLeftAfterSale = getSharesLeftAfterSale(symbol, orderAmount, doc);

        if (sharesLeftAfterSale != -1) {
            Document sharesUpdate = new Document(String.format("stocks.%s", symbol), sharesLeftAfterSale);
            Document balanceUpdate = new Document("balance", balanceAfterSale);

            performUpdate(sharesUpdate);
            performUpdate(balanceUpdate);
        }
    }

    int getSharesLeftAfterSale(String symbol, int orderAmount, Document doc) {
        int currentShares;
        Object stocks = doc.get("stocks");

        try {
            int symbolIndex = stocks.toString().indexOf(symbol);
            if (symbolIndex == -1) {
                //System.out.println("You don't own any shares of " + symbol);
                return -1;
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

        if (currentShares < orderAmount) {
            //System.out.println("You can't sell more shares than you have");
            return -1;
        }
        return currentShares - orderAmount;
    }

    private void performUpdate(Document update) {
        MongoCollection<Document> collection = DbConnection.getCollection();
        Document doc = DbConnection.getDocument("username", userName);
        collection.updateOne(doc, new BasicDBObject("$set", update));
    }

}
