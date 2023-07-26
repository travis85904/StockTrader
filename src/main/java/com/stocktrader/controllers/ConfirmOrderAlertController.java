package com.stocktrader.controllers;

import com.stocktrader.db.StockPurchase;
import com.stocktrader.db.StockSell;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class ConfirmOrderAlertController {
    @FXML
    private Text confirmationText;
    private int orderQuantity;
    private String symbol;
    private String username;
    private BuyStocksController buyStocksController;
    private String orderType;

    void setup(String username, String symbol, int orderQuantity, String orderType, BuyStocksController buyStocksController) {
        this.symbol = symbol;
        this.orderQuantity = orderQuantity;
        this.username = username;
        this.buyStocksController = buyStocksController;
        this.orderType = orderType;

        confirmationText.setText(String.format("%s %s shares of %s at Market Price?", orderType, orderQuantity, symbol));
    }

    @FXML
    private void yesButtonClick() throws Exception {
        if (orderType.equals("BUY")) {
            new StockPurchase(username, symbol, orderQuantity);
        } else if (orderType.equals("SELL")) {
            new StockSell(username, symbol, orderQuantity);
        }
        buyStocksController.displayPortfolio();
        buyStocksController.closeOrderAlert();
    }

    @FXML
    private void noButtonClick() {
        buyStocksController.closeOrderAlert();
    }

}
