package com.stocktrader.db;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.stocktrader.StocksApplication;
import com.stocktrader.api.GetApiResponse;
import com.stocktrader.parser.MongoParse;
import com.stocktrader.parser.Stock;
import org.bson.Document;

import java.util.List;

public class StockPurchase {
    private int symbolIndex;
    private boolean symbolFound = false;
    final MongoParse mongoParse;
    final List<Stock> stockList;

    public StockPurchase(String userName, String symbol, int orderAmount) throws Exception {
        Document doc = DbConnection.getDocument("username", userName);
        mongoParse = new Gson().fromJson(doc.toJson(), MongoParse.class);
        stockList = mongoParse.getStocks();

        double pricePerShare = Double.parseDouble(new GetApiResponse().realTimePrice(symbol).price());
        double totalOrderCost = pricePerShare * orderAmount;
        double balance = mongoParse.getBalance();

        if (balance >= totalOrderCost) {
            double newBalance = balance - totalOrderCost;
            int shares = sharesAfterPurchase(symbol, orderAmount);

            Document balanceUpdate = new Document("balance", newBalance);
            performUpdate(balanceUpdate, userName);

            if (!symbolFound) {
                Document newSymbol = new Document("stocks." + symbolIndex + ".symbol", symbol);
                performUpdate(newSymbol, userName);
            }
            Document sharesUpdate = new Document("stocks." + symbolIndex + ".shares", shares);
            performUpdate(sharesUpdate, userName);
        } else StocksApplication.showAlert("Your balance is too low");
    }

    //checks to see if the user already owns shares of the company
    private int sharesAfterPurchase(String symbol, int orderAmount) {
        for (Stock s : stockList) {
            if (s.getSymbol() != null) {
                if (s.getSymbol().equals(symbol)) {
                    symbolFound = true;
                    symbolIndex = stockList.indexOf(s);
                    return s.getShares() + orderAmount;
                }
            }
        }
        symbolIndex = stockList.size();
        return orderAmount;
    }

    private void performUpdate(Document update, String userName) {
        MongoCollection<Document> collection = DbConnection.getCollection();
        Document doc = DbConnection.getDocument("username", userName);
        collection.updateOne(doc, new BasicDBObject("$set", update));
    }
}
