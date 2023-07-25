package com.stocktrader.controllers;

import com.google.gson.Gson;
import com.stocktrader.api.GetApiResponse;
import com.stocktrader.api.RealTimePrice;
import com.stocktrader.db.DbConnection;
import com.stocktrader.parser.MongoParse;
import com.stocktrader.parser.Stock;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import org.bson.Document;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public class BuyStocksController {
    @FXML
    private TextArea responseText;
    @FXML
    private TextArea myPortfolioText;
    @FXML
    private TextField realTimePriceTextField;
    @FXML
    private TextField orderQuantityText;
    private WelcomePageController welcomePageController;
    private static Stage confirmOrderAlert;
    private String username;


    public void displayPortfolio() {
        Document doc = DbConnection.getDocument("username", username);
        MongoParse mongoParse = new Gson().fromJson(doc.toJson(), MongoParse.class);
        DecimalFormat df = new DecimalFormat("###,###,###.##");
        List<Stock> stockList = mongoParse.getStocks();
        myPortfolioText.setText(String.format("Balance: %s\n", df.format(doc.get("balance"))));
        for (int i = 1; i < stockList.size(); i++) {
            myPortfolioText.appendText(stockList.get(i).toString());
        }
    }

    public void buyStocksButton(ActionEvent actionEvent) throws IOException {
        placeOrder("BUY");
    }

    public void sellStocksButton(ActionEvent actionEvent) throws IOException {
        placeOrder("SELL");
    }
    private void placeOrder(String orderType) throws IOException {
        final int orderQuantity = Integer.parseInt(orderQuantityText.getText());
        final String symbol = realTimePriceTextField.getText();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("confirm-order-alert.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        ConfirmOrderAlertController confirmOrderAlertController = loader.getController();
        confirmOrderAlertController.setup(username, symbol.toUpperCase(), orderQuantity, orderType, this);

        confirmOrderAlert = new Stage();
        confirmOrderAlert.setScene(root.getScene());
        confirmOrderAlert.show();
        orderQuantityText.setText("");
    }

    @FXML
    private void getRealTimePrice() throws IOException, InterruptedException {
        String symbol = realTimePriceTextField.getText();
        RealTimePrice realTimePrice = new GetApiResponse().realTimePrice(symbol);
        responseText.setText(symbol + "\n" + realTimePrice.toString());
    }

    @FXML
    private void realTimePriceListener(KeyEvent e) throws IOException, InterruptedException {
        if (e.getCode() == KeyCode.ENTER)
            getRealTimePrice();
    }

    void setup(String username, WelcomePageController welcomePageController) {
        this.username = username;
        this.welcomePageController = welcomePageController;
        displayPortfolio();
        orderQuantityText.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
    }

    public void closeOrderAlert() {
        confirmOrderAlert.close();
        welcomePageController.displayPortfolio();
    }
}
