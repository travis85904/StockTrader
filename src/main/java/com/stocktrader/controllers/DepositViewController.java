package com.stocktrader.controllers;

import com.stocktrader.db.Deposit;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class DepositViewController {

    @FXML
    private TextField depositAmountTextField;
    private String userName;

    @FXML
    private void deposit() {
        int amount = Integer.parseInt(depositAmountTextField.getText());
        new Deposit(userName, amount);
        WelcomePageController.closeDepositWindow();
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
