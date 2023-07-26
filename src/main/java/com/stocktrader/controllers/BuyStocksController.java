package com.stocktrader.controllers;

import com.stocktrader.StocksApplication;
import com.stocktrader.api.GetApiResponse;
import com.stocktrader.api.RealTimePrice;
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
import java.io.IOException;

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

    void displayPortfolio() {
        myPortfolioText.setText(StocksApplication.printBalance(username));
        myPortfolioText.appendText(StocksApplication.printPortfolio(username));
    }

    @FXML
    private void buyStocksButton(ActionEvent actionEvent) throws IOException {
        placeOrder("BUY");
    }

    @FXML
    private void sellStocksButton(ActionEvent actionEvent) throws IOException {
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

    void closeOrderAlert() {
        confirmOrderAlert.close();
        welcomePageController.displayPortfolio();
    }
    void setup(String username, WelcomePageController welcomePageController) {
        this.username = username;
        this.welcomePageController = welcomePageController;
        displayPortfolio();
        orderQuantityText.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
    }

}
