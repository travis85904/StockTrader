package com.stocktrader.controllers;

import com.google.common.hash.Hashing;
import com.stocktrader.StocksApplication;
import com.stocktrader.db.UserRegistration;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.nio.charset.StandardCharsets;

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
        String password = Hashing.sha256()
                .hashString(passwordField.getText(), StandardCharsets.UTF_8)
                .toString();
        String passwordConfirmation = Hashing.sha256()
                .hashString(confirmPasswordField.getText(), StandardCharsets.UTF_8)
                .toString();
        String email = emailField.getText();

        if (firstName.isEmpty()) {
            StocksApplication.showAlert("Please enter a First Name");
            return;
        }
        if (lastName.isEmpty()) {
            StocksApplication.showAlert("Please enter a Last Name");
            return;
        }
        if (userName.isEmpty()) {
            StocksApplication.showAlert("Please enter a UserName");
            return;
        }
        if (email.isEmpty()) {
            StocksApplication.showAlert("Please enter an Email");
            return;
        }
        if (password.isEmpty()) {
            StocksApplication.showAlert("Please enter a Password");
            return;
        }
        if (!password.equals(passwordConfirmation)) {
            StocksApplication.showAlert("Passwords do not match");
            return;
        }

        boolean registrationSuccess = new UserRegistration().register(firstName, lastName, userName, password, email);
        if (registrationSuccess) {
            LoginViewController.closeRegistrationWindow();
        }
    }
}
