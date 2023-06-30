package com.stocktrader.controllers;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.stocktrader.db.DbConnection;
import com.stocktrader.db.Deposit;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Updates.set;

public class DepositViewController {

    @FXML
    private TextField depositAmountTextField;
    private String userName;

    @FXML
    private void deposit() {
        int amount = Integer.parseInt(depositAmountTextField.getText());

        MongoDatabase db = DbConnection.getMongoClient().getDatabase("db");
        MongoCollection<Document> collection = db.getCollection("users");

        Document doc = collection.find(Filters.eq("username", userName)).first();

        int currentBalance = (int) doc.get("balance");
        Bson updateOperation = set("balance", amount + currentBalance);
        collection.updateOne(doc, updateOperation);

        WelcomePageController.closeDepositWindow();
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
