package com.stocktrader.controllers;

import com.stocktrader.StocksApplication;
import com.stocktrader.db.UserRegistration;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistrationController {
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField userNameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private TextField emailField;

    @FXML
    private void register() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String userName = userNameField.getText();
        String password = passwordField.getText();
        String email = emailField.getText();

        if (firstName.length() == 0) {
            StocksApplication.showAlert("Please enter a First Name");
            return;
        }
        if (lastName.length() == 0) {
            StocksApplication.showAlert("Please enter a Last Name");
            return;
        }
        if (userName.length() == 0) {
            StocksApplication.showAlert("Please enter a UserName");
            return;
        }
        if (email.length() == 0) {
            StocksApplication.showAlert("Please enter an Email");
            return;
        }
        if (password.length() == 0) {
            StocksApplication.showAlert("Please enter a Password");
            return;
        }
        if (!password.equals(confirmPasswordField.getText())) {
            StocksApplication.showAlert("Passwords do not match");
            return;
        }

        boolean registrationSuccess = new UserRegistration().register(firstName, lastName, userName, password, email);
        if (registrationSuccess) {
            LoginViewController.closeRegistrationWindow();
            return;
        }
    }
}
