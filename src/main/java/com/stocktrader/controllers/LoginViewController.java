package com.stocktrader.controllers;

import com.google.common.hash.Hashing;
import com.stocktrader.StocksApplication;
import com.stocktrader.db.UserAuth;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class LoginViewController {
    private static Stage registrationWindow;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    @FXML
    protected void onLoginButtonClick() {
        boolean isValidLoginInfo;
        try {
            String username = usernameField.getText();
            String password = Hashing.sha256()
                    .hashString(passwordField.getText(), StandardCharsets.UTF_8)
                    .toString();
            isValidLoginInfo = new UserAuth().authenticate(username, password);
            if (isValidLoginInfo) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("welcome-page.fxml"));
                Parent root = loader.load();
                WelcomePageController welcomePageController = loader.getController();
                welcomePageController.setup(username);
                Scene scene = new Scene(root);
                StocksApplication.getStage().setScene(root.getScene());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void loginFieldKeyListener(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER)
            onLoginButtonClick();
    }

    @FXML
    private void register() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("registration-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        registrationWindow = new Stage();
        registrationWindow.setScene(root.getScene());
        registrationWindow.show();
    }

    static void closeRegistrationWindow() {
        registrationWindow.close();
    }
}
