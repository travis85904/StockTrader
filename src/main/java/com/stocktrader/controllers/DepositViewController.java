package com.stocktrader.controllers;

import com.stocktrader.db.Deposit;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class DepositViewController {

    @FXML
    private TextField depositAmountTextField;
    private String username;
    private WelcomePageController welcomePageController;

    @FXML
    private void deposit() {
        double amount = Double.parseDouble(depositAmountTextField.getText());
        new Deposit(username, amount);
        welcomePageController.displayPortfolio();
        welcomePageController.closeDepositWindow();
    }

    void setup (String username, WelcomePageController welcomePageController){
        this.username = username;
        this.welcomePageController = welcomePageController;
    }

}
