package com.stocktrader.db;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;
import com.stocktrader.StocksApplication;
import com.stocktrader.api.GetApiResponse;
import com.stocktrader.parser.MongoParse;
import com.stocktrader.parser.Stock;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.IOException;
import java.util.List;

public class StockSell {
    private int symbolIndex;
    MongoParse mongoParse;
    List<Stock> stockList;

    public StockSell(String userName, String symbol, int orderAmount) throws IOException, InterruptedException {
        Document doc = DbConnection.getDocument("username", userName);
        mongoParse = new Gson().fromJson(doc.toJson(), MongoParse.class);
        stockList = mongoParse.getStocks();

        double pricePerShare = Double.parseDouble(new GetApiResponse().realTimePrice(symbol).price());
        double totalOrderCost = pricePerShare * orderAmount;
        double balance = (double) doc.get("balance");
        double balanceAfterSale = balance + totalOrderCost;
        int sharesLeftAfterSale = sharesLeftAfterSale(symbol, orderAmount);

        if (sharesLeftAfterSale == 0) {
            Bson delete = Updates.pull("stocks", new Document("symbol", symbol));
            DbConnection.getCollection().updateOne(doc, delete);
            DbConnection.getCollection().updateOne(doc, new Document("$pull", new Document("stocks", symbolIndex)));
            balanceUpdate(userName, balanceAfterSale);
            return;
        }
        if (sharesLeftAfterSale > -1) {
            Document sharesUpdate = new Document(String.format("stocks." + symbolIndex + ".shares"), sharesLeftAfterSale);
            performUpdate(sharesUpdate, userName);
            balanceUpdate(userName, balanceAfterSale);
        } else {
            StocksApplication.showAlert("You don't have that many shares");
        }
    }

    private void balanceUpdate(String userName, double balanceAfterSale) {
        Document balanceUpdate = new Document("balance", balanceAfterSale);
        performUpdate(balanceUpdate, userName);
    }

    int sharesLeftAfterSale(String symbol, int orderAmount) {
        for (Stock s : stockList) {
            if (s.getSymbol().equals(symbol)) {
                symbolIndex = stockList.indexOf(s);
                return s.getShares() - orderAmount;
            }
        }
        return -1;
    }

    private void performUpdate(Document update, String userName) {
        MongoCollection<Document> collection = DbConnection.getCollection();
        Document doc = DbConnection.getDocument("username", userName);
        collection.updateOne(doc, new BasicDBObject("$set", update));
    }
}
